package mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model;

import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class ManufacturerId extends DomainObjectId {

    private ManufacturerId() {
        super("");
    }

    public ManufacturerId(String id) {
        super(id);
    }
}
