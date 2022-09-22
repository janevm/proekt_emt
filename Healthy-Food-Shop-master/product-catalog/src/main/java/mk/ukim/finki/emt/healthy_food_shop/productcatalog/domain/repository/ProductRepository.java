package mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.repository;

import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.Product;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, ProductId> {
}
