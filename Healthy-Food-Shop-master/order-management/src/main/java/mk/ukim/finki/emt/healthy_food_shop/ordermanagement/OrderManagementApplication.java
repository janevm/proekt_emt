package mk.ukim.finki.emt.healthy_food_shop.ordermanagement;

import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.SharedConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.xml.validation.Validator;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@Import(SharedConfig.class)
public class OrderManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApplication.class, args);
    }

}
