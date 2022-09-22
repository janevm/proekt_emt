package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application;

//import lombok.var;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.form.OrderForm;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.event.OrderCreatedEvent;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.event.OrderItemAddedToOrderEvent;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.*;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.location.Address;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class OrderCatalog {

    private final OrderRepository orderRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    //@Resource(name="localValidatorFactoryBean")
    private final Validator validator;

    private ProductCatalog productCatalog;

    public OrderCatalog(OrderRepository orderRepository,
                        ProductCatalog productCatalog,
                        Validator validator,
                        ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.validator = validator;
        this.productCatalog = productCatalog;
    }

    public OrderId createOrder(@NonNull OrderForm order) {
        Objects.requireNonNull(order,"order must not be null");
        var constraintViolations = validator.validate(order);

        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The OrderForm is not valid", constraintViolations);
        }

        var newOrder = orderRepository.saveAndFlush(toDomainModel(order));
        applicationEventPublisher.publishEvent(new OrderCreatedEvent(newOrder.id(),newOrder.getOrderedOn()));
        newOrder.getItems().forEach(orderItem -> applicationEventPublisher.publishEvent(new OrderItemAddedToOrderEvent(newOrder.id(),orderItem.id(),orderItem.getProductId(),orderItem.getQuantity(), Instant.now())));
        return newOrder.id();
    }

    @NonNull
    public Optional<Order> findById(@NonNull OrderId orderId) {
        Objects.requireNonNull(orderId, "orderId must not be null");
        System.out.println(orderId);
        return orderRepository.findById(orderId);
    }

    @NonNull
    private Order toDomainModel(@NonNull OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency(),
                toDomainModel(orderForm.getBillingAddress()));
        orderForm.getItems().forEach(item -> order.addItem(item.getProduct(), item.getQuantity()));
        return order;
    }

    @NonNull
    private Address toDomainModel(@NonNull RecipientAddressForm form) {
        return new Address(form.getStreet(), form.getStreetNumber(),form.getCity(), form.getCountry(), form.getZipCode());
    }



}

