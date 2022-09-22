package mk.ukim.finki.emt.healthy_food_shop.ordermanagement.domain.model;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.healthy_food_shop.sharedkernel.domain.location.Address;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name="users")
@Getter
public class User extends AbstractEntity<UserId> implements UserDetails {

    @Version
    private Long version;

    private String name;

    private String surname;

    @Embedded
    private Address address;

    private String eMail;

    private String username;

    private String password;

    @ManyToOne
    private UserRole userRole;

    private boolean isActive = true;

    public User() {
    }

    public User(@NonNull String name, @NonNull String surname, @NonNull Address address, @NonNull String eMail,
                @NonNull String username, @NonNull String password, UserRole role) {
        super(DomainObjectId.randomId(UserId.class));
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.eMail = eMail;
        this.username = username;
        this.password = password;
        this.userRole = role;
        this.isActive = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userRole==null) {
            return Collections.emptyList();
        }
        List<GrantedAuthority> auths = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(this.userRole.getName());
        auths.add(authority);
        return auths;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public UserId id() {
        return id;
    }
}
