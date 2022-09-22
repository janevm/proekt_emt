package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.repository;

import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
