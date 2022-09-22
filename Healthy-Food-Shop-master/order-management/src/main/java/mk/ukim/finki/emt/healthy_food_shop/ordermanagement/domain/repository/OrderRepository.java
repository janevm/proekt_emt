package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.repository;

import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}

