package mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ProductId extends DomainObjectId {

    private ProductId() {
        super(DomainObjectId.randomId(ProductId.class).toString());
    }

    @JsonCreator
    public ProductId(String id) {
        super(id);
    }

}
