package mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.location.Address;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufacturer")
@Getter
public class Manufacturer extends AbstractEntity<ManufacturerId> {

    private String name;

    @Embedded
    private Address address;

    public Manufacturer(){
    }

    public Manufacturer(ManufacturerId manufacturerId, String name, Address address) {
        super(manufacturerId);
        this.name = name;
        this.address = address;
    }

    public Manufacturer(String name, Address address) {
        super(DomainObjectId.randomId(ManufacturerId.class));
        this.name = name;
        this.address = address;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "manufacturer")
    private List<Product> productList;

    @Override
    public ManufacturerId id() {
        return id;
    }
}
