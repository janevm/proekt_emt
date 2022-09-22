package mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.repository;

import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.Manufacturer;
import mk.ukim.finki.emt.healthy_food_shop.productcatalog.domain.model.ManufacturerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, ManufacturerId> {
}

