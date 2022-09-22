package mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.location;

import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.ValueObject;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Address implements ValueObject {

    private String street;

    private String streetNumber;

    private String city;

    private String country;

    private String zipCode;

    protected Address(){
    }

    public Address(@NonNull String street, @NonNull String streetNumber, @NonNull String city, @NonNull String country,
                   @NonNull String zipCode) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) &&
                streetNumber.equals(address.streetNumber) &&
                city.equals(address.city) &&
                country.equals(address.country) &&
                zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, city, country, zipCode);
    }

    @Override
    public String toString() {
        return street + " " + streetNumber + ", " + city + ", " + country + ", " + zipCode;
    }
}
