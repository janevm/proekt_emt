package mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial;

import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
@Getter
public class Money implements ValueObject {

    private final Double price;

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    protected Money(){
        this.currency=null;
        this.price = 0d;
    }

    public Money(@NonNull Double price,@NonNull Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    public static Money valueOf(Currency currency, Double price){
        return new Money(price, currency);
    }

    public Money add(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(price + money.price, currency);
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(price - money.price, currency);
    }

    public Money multiply(int m)  {
        return new Money(price * m, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return price.equals(money.price) &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, currency);
    }

    @Override
    public String toString() {
        return price + " " + currency;
    }
}
