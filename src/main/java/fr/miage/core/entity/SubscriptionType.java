package fr.miage.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SUBSCRIPTIONTYPE")
public class SubscriptionType {
    @Id
    @Column(name="typeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(name="typeName")
    private String typeName;

    @Column(name="typePrix")
    private String typePrix;

    @OneToMany(mappedBy = "subscriptionType")
    private List<Subscription> subscriptions;

    public SubscriptionType(){}

    public SubscriptionType(String typeName, List<Subscription> subscriptions) {
        this.typeName = typeName;
        this.subscriptions = subscriptions;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getTypePrix() {
        return typePrix;
    }

    public void setTypePrix(String typePrix) {
        this.typePrix = typePrix;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
