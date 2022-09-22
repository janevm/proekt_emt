package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model;

import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;

@Embeddable
public class UserId extends DomainObjectId {

    private UserId() {
        super("");
    }

    public UserId(@NonNull String id) {
        super(id);
    }

}
