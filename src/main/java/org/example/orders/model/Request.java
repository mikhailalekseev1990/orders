package org.example.orders.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = Request.ALL_SORTED_BY_OPERATOR, query = "SELECT r FROM Request r WHERE r.status<>'DRAFT' ORDER BY r.registration DESC"),
        @NamedQuery(name = Request.ALL_SORTED_BY_USER, query = "SELECT o FROM Request o WHERE o.user.id=:userId ORDER BY o.registration DESC"),
        @NamedQuery(name = Request.CHANGE_STATUS, query = "UPDATE Request o SET o.status=:status WHERE o.id=:id")})

@Entity
@Table(name = "requests")
public class Request extends AbstractBaseEntity {
    public static final String ALL_SORTED_BY_OPERATOR = "Request.getAllByOperator";
    public static final String ALL_SORTED_BY_USER = "Request.getAllByUser";
    public static final String CHANGE_STATUS = "Request.changeStatus";
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

    public Request() {
    }

    public Request(Integer id, String status, String text, Date registration) {
        super(id);
        this.status = status;
        this.text = text;
        this.registration = registration;
    }

    public Request(String status, String text) {
        this(null, status, text);
    }

    public Request(String status, String text, Date registration) {
        super(null);
        this.status = status;
        this.text = text;
        this.registration = registration;
    }

    public Request(Integer id, String status, String text) {
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
