package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.dto;

import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.financial.Money;

@Getter
public class Product {

    private ProductId id;

    private String name;

    private Money price;

    private int quantity;

}
