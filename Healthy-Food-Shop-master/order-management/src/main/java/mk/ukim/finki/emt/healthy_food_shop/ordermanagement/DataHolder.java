package mk.ukim.finki.emt.healthy_food_shop.ordermanagement;


import lombok.Getter;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.User;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model.UserRole;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.repository.UserRepository;
import mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.repository.UserRoleRepository;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.location.Address;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataHolder {
    public static final List<User> users = new ArrayList<>();
    public final UserRepository userRepository;
    public final UserRoleRepository userRoleRepository;

    public final PasswordEncoder passwordEncoder;


    public DataHolder(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (this.userRoleRepository.count()==0) {
            UserRole adminRole = new UserRole("ROLE_ADMIN");
            UserRole customerRole = new UserRole("ROLE_CUSTOMER");
            userRoleRepository.saveAndFlush(adminRole);
            userRoleRepository.saveAndFlush(customerRole);
        }

        users.add(new User("Magdalena","Janevska", new Address("Jani Lukrovski","162","Skopje","Macedonia","1000"),
                "janevskam@gmail.com","admin",passwordEncoder.encode("admin"),userRoleRepository.findById("ROLE_ADMIN")
                .orElseThrow(RuntimeException::new)));

        users.add(new User("Petar","Petrovski",new Address("Ilindenska", "24", "Skopje", "Macedonia", "1000"),
                "ppetrovski@gmail.com", "customer",passwordEncoder.encode("customer"),userRoleRepository.findById("ROLE_CUSTOMER")
                .orElseThrow(RuntimeException::new)));

        // Initial save of all objects in relational database
        if (userRepository.count()==0) {
            this.userRepository.saveAll(users);
        }
    }
}
