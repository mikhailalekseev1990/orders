package org.example.orders.model;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.id DESC"),
})


@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class User extends AbstractBaseEntity{
    public static final String ALL_SORTED = "User.getAll";
    public static final String SET_ROLE = "User.setRole";
    public static final String DELETE_ROLE = "User.deleteRole";

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 4, max = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("id")
    private List<Request> request;

    public User(){
    }

    public User(User u) {
        this(u.getId(), u.getName(),u.getPassword(), u.getRoles());
    }

    public User(String name,  String password,   Role role, Role... roles) {
        this(null, name,  password, EnumSet.of(role, roles));
    }

    public User(Integer id, String name,  String password, Collection<Role> roles){
        super(id);
        this.name = name;
        this.password = password;
        setRoles(roles);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Request> getOrders() {
        return request;
    }

    public void setOrders(List<Request> orders) {
        this.request = orders;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }
}
