package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Getter
@Table(name="user_role")
public class UserRole {

    @Id
    private String name;

    private UserRole() {    }

    public UserRole(String name) {
        this.name = name;
    }
}
