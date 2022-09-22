package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.UserId;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainEvent;

import java.time.Instant;

public class UserLinkedToOrderEvent implements DomainEvent {

    private final UserId userId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    public UserLinkedToOrderEvent(UserId userId, Instant occurredOn) {
        this.userId = userId;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }

    public UserId getUserId() {
        return userId;
    }
}