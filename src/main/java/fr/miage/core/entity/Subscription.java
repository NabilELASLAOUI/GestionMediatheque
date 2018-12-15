package fr.miage.core.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Subscription")
public class Subscription {


    @Id
    @Column(name="subscriptionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @Column(name="beginningDate")
    private Date beginningDate;

    @Column(name="endDate")
    private Date endDate;

    @OneToMany(mappedBy = "subscription")
    private final List<User> users = new LinkedList<>();

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public Subscription(Date beginningDate, Date endDate) {
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Subscription(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Subscription() {
    }
}
