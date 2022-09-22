package mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId extends DomainObjectId {

    private String id;

    private OrderItemId() {
        super(DomainObjectId.randomId(OrderItemId.class).toString());
    }

    @JsonCreator
    public OrderItemId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
