package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.port.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.OrderCatalog;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.ProductCatalog;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.form.OrderItemForm;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.dto.Product;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Currency;
import org.springframework.security.access.prepost.PreAuthorize;


@Route("create-order")
@PageTitle("Create Order")
public class CreateOrderView extends VerticalLayout {

    private final ProductCatalog productCatalog;
    private final OrderCatalog orderCatalog;
    private final Binder<OrderForm> binder;
    private final Grid<OrderItemForm> itemGrid;

    public CreateOrderView(ProductCatalog productCatalog, OrderCatalog orderCatalog) {
        this.productCatalog = productCatalog;
        this.orderCatalog = orderCatalog;

        setSizeFull();

        binder = new Binder<>();

        var title = new Html("<h3>Create Order</h3>");
        add(title);

        var tabs = new Tabs();
        tabs.setWidth("100%");
        add(tabs);
        var tabContainer = new TabContainer(tabs);
        tabContainer.setWidth("100%");
        tabContainer.setHeight("100%");
        add(tabContainer);

        itemGrid = new Grid<>();
        itemGrid.addColumn(form -> form.getProduct().getName()).setHeader("Product");
        itemGrid.addColumn(OrderItemForm::getQuantity).setHeader("Qty");

        var orderItemLayout = new OrderItemLayout();
        tabContainer.addTab("Items", new Div(itemGrid, orderItemLayout));


        var billingAddress = new AddressLayout();
        billingAddress.bind(binder, OrderForm::getBillingAddress);
        tabContainer.addTab("Billing Address", billingAddress);

        var currency = new ComboBox<>("Currency", Currency.values());
        binder.forField(currency)
                .asRequired()
                .bind(OrderForm::getCurrency, OrderForm::setCurrency);
        tabContainer.addTab("Order Info", currency);


        var createOrder = new Button("Create Order", evt -> createOrder());
        createOrder.setEnabled(false);
        createOrder.getElement().getThemeList().set("primary", true);

        add(createOrder);

        binder.setBean(new OrderForm());
        binder.addValueChangeListener(evt -> createOrder.setEnabled(binder.isValid()));
        tabs.setSelectedIndex(0);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    private void addItem(OrderItemForm item) {
        binder.getBean().getItems().add(item);
        itemGrid.setItems(binder.getBean().getItems());
    }

    private void createOrder() {
        try {
            var orderId = orderCatalog.createOrder(binder.getBean());
            getUI().ifPresent(ui -> ui.navigate(OrderDetailsView.class, orderId.getId()));
        } catch (Exception ex) {
            Notification.show(ex.getMessage());
        }
    }

    class AddressLayout extends VerticalLayout {

        private TextField street;
        private TextField streetNumber;
        private TextField city;
        private TextField country;
        private TextField zipCode;

        AddressLayout() {
            setPadding(false);
            setWidth("630px");

            street = createTextField("Street");
            streetNumber = createTextField("Street Number");
            city = createTextField("City");
            country = createTextField("Country");
            zipCode = createTextField("Zip Code");

            var line1 = new HorizontalLayout(street, streetNumber);
            line1.setWidth("100%");

            var line2 = new HorizontalLayout(city, country, zipCode);
            line2.setWidth("100%");

            add(line1, line2);
        }

        private TextField createTextField(String caption) {
            var field = new TextField(caption);
            field.setWidth("100%");
            return field;
        }

        private void bind(Binder<OrderForm> binder, ValueProvider<OrderForm, RecipientAddressForm> parentProvider) {
            binder.forField(street)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getStreet), setter(parentProvider, RecipientAddressForm::setStreet));
            binder.forField(streetNumber)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getStreetNumber), setter(parentProvider, RecipientAddressForm::setStreetNumber));

            binder.forField(city)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getCity), setter(parentProvider, RecipientAddressForm::setCity));
            binder.forField(country)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getCountry), setter(parentProvider, RecipientAddressForm::setCountry));
            binder.forField(zipCode)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getZipCode), setter(parentProvider, RecipientAddressForm::setZipCode));
        }

        private <V> ValueProvider<OrderForm, V> getter(ValueProvider<OrderForm, RecipientAddressForm> parentProvider, ValueProvider<RecipientAddressForm, V> valueProvider) {
            return orderForm -> valueProvider.apply(parentProvider.apply(orderForm));
        }

        private <V> Setter<OrderForm, V> setter(ValueProvider<OrderForm, RecipientAddressForm> parentProvider, Setter<RecipientAddressForm, V> setter) {
            return (orderForm, value) -> setter.accept(parentProvider.apply(orderForm), value);
        }
    }

    class OrderItemLayout extends HorizontalLayout {

        private Binder<OrderItemForm> binder;
        private ComboBox<Product> product;
        private TextField quantity;
        private TextField itemPrice;
        //        private TextField tax;
        private Button addItem;

        OrderItemLayout() {
            setWidth("630px");

            setAlignItems(Alignment.END);
            product = new ComboBox<>("Product", productCatalog.findAll());
            product.setItemLabelGenerator(Product::getName);
            add(product);

            itemPrice = new TextField("Price");
            itemPrice.setReadOnly(true);
            itemPrice.setWidth("100%");
            add(itemPrice);

            quantity = new TextField("Qty");
            quantity.setWidth("50px");
            add(quantity);

            addItem = new Button("Add", evt -> {
                addItem(binder.getBean());
                binder.setBean(new OrderItemForm());
                addItem.setEnabled(false);
            });
            addItem.setEnabled(false);
            add(addItem);

            product.addValueChangeListener(evt -> {
                var p = evt.getValue();
                if (p == null) {
                    itemPrice.setValue("");
                } else {
                    itemPrice.setValue(p.getPrice().toString());
                }
            });

            binder = new Binder<>();
            binder.forField(product)
                    .asRequired()
                    .bind(OrderItemForm::getProduct, OrderItemForm::setProduct);
            binder.forField(quantity)
                    .asRequired()
                    .withConverter(new StringToIntegerConverter("Please enter a valid quantity"))
                    .bind(OrderItemForm::getQuantity, OrderItemForm::setQuantity);
            binder.addValueChangeListener(evt -> addItem.setEnabled(binder.isValid()));
            binder.setBean(new OrderItemForm());
        }
    }
}
