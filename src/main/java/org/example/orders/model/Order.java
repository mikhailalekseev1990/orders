package org.example.orders.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Order.ALL_SORTED, query = "SELECT o FROM Order o ORDER BY o.registration DESC"),
        @NamedQuery(name = Order.ALL_SORTED_BY_USER, query = "SELECT o FROM Order o WHERE o.user.id=:userId ORDER BY o.registration DESC"),
        @NamedQuery(name = Order.DELETE, query = "DELETE FROM Order o WHERE o.id=:id AND o.user.id=:userId")})

@Entity
@Table(name = "order")
public class Order extends AbstractBaseEntity {
    public static final String ALL_SORTED = "Order.getAll";
    public static final String ALL_SORTED_BY_USER = "Order.getAllByUser";
    public static final String DELETE = "Order.delete";
    private String state; //  draft, sent, received, rejected

    private String text;

    @Column(name = "registration", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private Date registration = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    public Order() {
    }

    public Order(Integer id, String state, String text, Date registration) {
        super(id);
        this.state = state;
        this.text = text;
        this.registration = registration;
    }

    public Order(String state, String text, Date registration) {
        super(null);
        this.state = state;
        this.text = text;
        this.registration = registration;
    }

    public Order(Integer id, String state, String text) {
        super(id);
        this.state = state;
        this.text = text;
        this.registration = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
