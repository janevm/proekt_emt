package mk.ukim.finki.emt.healthy_food_shop.productcatalog.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.OrderId;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.OrderItemId;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.ProductId;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

@Getter
public class OrderItemAddedEvent implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;

    @JsonProperty("orderItemId")
    private final OrderItemId orderItemId;

    @JsonProperty("productId")
    private final ProductId productId;

    @JsonProperty("quantity")
    private final int quantity;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public OrderItemAddedEvent(OrderId orderId, OrderItemId orderItemId, ProductId productId, int quantity, Instant occurredOn) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.productId = productId;
        this.quantity = quantity;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

}
