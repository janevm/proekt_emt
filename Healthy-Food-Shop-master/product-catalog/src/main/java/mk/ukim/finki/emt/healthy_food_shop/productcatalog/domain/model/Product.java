package mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Money;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "product")
@Where(clause = "deleted=false")
public class Product extends AbstractEntity<ProductId> {

    @Version
    private long version;

    private String name;

    @Embedded
    private Money price;

    private int quantity;

    private String category;

    private String imgUrl;

    private boolean deleted;

    @ManyToOne
    private Manufacturer manufacturer;

    public Product() {
    }

    public Product(ProductId productId, String name, Money price, int quantity, String category, String imgUrl, Manufacturer manufacturer) {
        super(productId);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imgUrl = imgUrl;
        this.manufacturer = manufacturer;
        deleted = false;
    }

    public Product(String name, Money price, int quantity, String category, String imgUrl, Manufacturer manufacturer) {
        super(DomainObjectId.randomId(ProductId.class));
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imgUrl = imgUrl;
        this.manufacturer = manufacturer;
        deleted = false;
    }

    public void subtractQuantity(int qnt) {
        if (qnt > this.quantity) {
            throw new RuntimeException("unsupported quantity");
        }
        this.quantity -= qnt;
    }

    public void addQuantity(int qnt) {
        this.quantity += qnt;
    }

    @Override
    public ProductId id() {
        return id;
    }

}
