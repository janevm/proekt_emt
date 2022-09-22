package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class RecipientAddressForm implements Serializable {

    @NotEmpty
    private String street;
    @NotEmpty
    private String streetNumber;
    @NotEmpty
    private String city;
    @NotEmpty
    private String country;
    @NotEmpty
    private String zipCode;

}
