package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model;

import lombok.Getter;
//import lombok.var;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.dto.Product;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Money;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.location.Address;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name="orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    @Version
    private Long version;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private Address address;

    private Instant orderedOn;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> items;

    @ManyToOne
    private User user;

    private Order() {

    }

    public Order(@NonNull Instant orderedOn, @NonNull Currency currency, @NonNull Address address) {
        super(DomainObjectId.randomId(OrderId.class));
        this.items = new HashSet<>();
        setCurrency(currency);
        setOrderedOn(orderedOn);
        setStatus(OrderStatus.RECEIVED);
        setAddress(address);
    }

    public Stream<OrderItem> getItems() {
        return items.stream();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setOrderedOn(Instant orderedOn) {
        this.orderedOn = orderedOn;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Money total(){
        return items.stream().map(OrderItem::total).reduce(new Money(0D, currency), Money::add);
    }

    public OrderItem addItem(@NonNull Product product, int qty) {
        Objects.requireNonNull(product, "product must not be null");
        var item = new OrderItem(product.getId(), product.getPrice(), qty);
        item.setQuantity(qty);
        items.add(item);
        return item;
    }

    @Override
    public OrderId id() {
        return id;
    }
}
