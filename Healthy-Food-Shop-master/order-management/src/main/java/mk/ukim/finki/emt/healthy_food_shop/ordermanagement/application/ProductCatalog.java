package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.application;

import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.dto.Product;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.dto.ProductId;

import java.util.List;

public interface ProductCatalog {

    List<Product> findAll();

    Product findById(ProductId id);

}
