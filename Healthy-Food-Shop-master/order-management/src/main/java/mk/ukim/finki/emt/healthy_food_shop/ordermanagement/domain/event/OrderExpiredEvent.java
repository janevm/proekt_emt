package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;
import java.time.LocalDateTime;

public class OrderExpiredEvent implements DomainEvent {
    private final LocalDateTime when;
    public String orderId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public OrderExpiredEvent(String orderId, Instant occurredOn) {
        this.occurredOn = occurredOn;
        this.when = LocalDateTime.now();
        this.orderId = orderId;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}