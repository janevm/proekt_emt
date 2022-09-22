package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainEvent;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.Objects;

//@Getter
//public class OrderCreatedEvent implements DomainEvent {
//    //ne cuvame duplikati na entiteti - da ne ja gusime bazata
//    // gi cuvame samo identifikatorite
//
//    private final OrderId orderId;
//    private final Instant occuredOn;
//
//    public OrderCreatedEvent(OrderId orderId, Instant occuredOn) {
//        this.orderId = orderId;
//        this.occuredOn = occuredOn;
//    }
//
//    @Override
//    public Instant occurredOn(){
//        return occuredOn;
//    };
//
//
//    /*public String orderId;
//    public LocalDateTime orderExpiry;
//    public Product product;
//
//    public OrderCreatedEvent(String orderId, LocalDateTime orderExpiry, Product product) {
//        this.orderId = orderId;
//        this.orderExpiry = orderExpiry;
//        this.product = product;
//    }*/
//}
public class OrderCreatedEvent implements DomainEvent {

    @JsonProperty("orderId")
    private final OrderId orderId;
    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public OrderCreatedEvent(@JsonProperty("orderId") @NonNull OrderId orderId,
                        @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
        this.orderId = Objects.requireNonNull(orderId, "orderId must not be null");
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }

    @NonNull
    public OrderId orderId() {
        return orderId;
    }

    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }
}
