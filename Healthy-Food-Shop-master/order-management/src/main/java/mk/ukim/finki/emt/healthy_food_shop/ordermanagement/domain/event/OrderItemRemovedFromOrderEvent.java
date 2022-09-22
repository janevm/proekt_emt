package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.event;

import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.OrderItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class OrderItemRemovedFromOrderEvent implements DomainEvent {

    public String orderId;
    public OrderItem item;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderItemRemovedFromOrderEvent(String orderId, OrderItem item, Instant occurredOn) {
        this.orderId = orderId;
        this.item = item;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}