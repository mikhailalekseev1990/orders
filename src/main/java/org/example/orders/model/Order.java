package org.example.orders.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Order.ALL_SORTED_BY_OPERATOR, query = "SELECT r FROM Order r WHERE r.status<>'DRAFT' ORDER BY r.registration DESC"),
        @NamedQuery(name = Order.ALL_SORTED_BY_USER, query = "SELECT o FROM Order o WHERE o.user.id=:userId ORDER BY o.registration DESC"),
        @NamedQuery(name = Order.CHANGE_STATUS, query = "UPDATE Order o SET o.status=:status WHERE o.id=:id")})

@Entity
@Table(name = "ordrs")
public class Order extends AbstractBaseEntity {
    public static final String ALL_SORTED_BY_OPERATOR = "Order.getAllByOperator";
    public static final String ALL_SORTED_BY_USER = "Order.getAllByUser";
    public static final String CHANGE_STATUS = "Order.changeStatus";
    private String status; //  draft, sent, received, rejected

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

    public Order(Integer id, String status, String text, Date registration) {
        super(id);
        this.status = status;
        this.text = text;
        this.registration = registration;
    }

    public Order(String status, String text) {
        this(null, status, text);
    }

    public Order(String status, String text, Date registration) {
        super(null);
        this.status = status;
        this.text = text;
        this.registration = registration;
    }

    public Order(Integer id, String status, String text) {
        super(id);
        this.status = status;
        this.text = text;
        this.registration = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }
}
