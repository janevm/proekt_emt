package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.repository;

import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole,String> {
}
