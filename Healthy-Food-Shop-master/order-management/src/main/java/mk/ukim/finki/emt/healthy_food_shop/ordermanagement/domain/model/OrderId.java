package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode

public class OrderId extends DomainObjectId {

    private OrderId() {
        super("");
    }

    public OrderId(@NonNull String id) {
        super(id);
    }

}
