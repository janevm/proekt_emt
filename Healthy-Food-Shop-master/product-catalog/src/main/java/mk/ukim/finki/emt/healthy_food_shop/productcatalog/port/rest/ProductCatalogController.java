package mk.ukim.finki.emt.healthy_food_shop.productcatalog.port.rest;

import mk.ukim.finki.emt.healthy_food_shop.productcatalog.application.ProductCatalog;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.Product;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.ProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
class ProductCatalogController {

    private final ProductCatalog productCatalog;

    ProductCatalogController(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String productId) {
        return productCatalog.findById(new ProductId(productId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Product> findAll() {
        return productCatalog.findAll();
    }
}
