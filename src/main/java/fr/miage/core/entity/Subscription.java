package fr.miage.core.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    @NotNull
    @Column(name="titre")
    private String titre;

    @NotNull
    @Column(name="beginningDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime beginningDate;

    @NotNull
    @Column(name="endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "subscription")
    private final List<User> users = new LinkedList<>();

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDateTime getBeginningDate() {
        return beginningDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public Subscription(LocalDateTime beginningDate, LocalDateTime endDate) {
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setBeginningDate(LocalDateTime beginningDate) {
        this.beginningDate = beginningDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Subscription(LocalDateTime beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Subscription() {
    }
}
