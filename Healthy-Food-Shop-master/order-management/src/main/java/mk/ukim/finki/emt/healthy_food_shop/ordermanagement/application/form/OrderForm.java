package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.form;

import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderForm implements Serializable {

    @NotNull
    private Currency currency;

    @Valid
    @NotNull
    private RecipientAddressForm billingAddress = new RecipientAddressForm();

    @Valid
    @NotEmpty
    private List<OrderItemForm> items = new ArrayList<>();

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public RecipientAddressForm getBillingAddress() {
        return billingAddress;
    }

    public List<OrderItemForm> getItems() {
        return items;
    }
}
