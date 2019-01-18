package fr.miage.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="USER")
public class User {
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(name="userName")
    private String userName;

    @NotNull
    @Column(name="userMail")
    private String userMail;
    @NotNull
    @Column(name="Password")
    private String Password;

    @NotNull
    @Column(name="userAddress")
    private String userAddress;

    @NotNull
    @Column(name="userPhone")
    private String userPhone;

    @Column(name = "enabled")
    private boolean enabled;

    @NotNull
    @ManyToOne
    private Role roles;

    /*
    @ManyToOne
    @JoinColumn(name="subscriptionId")
    private Subscription subscription;
    */
    @OneToMany(mappedBy = "user_sub")
    private List<Subscription> subscriptions;

    public User() {
    }
    User(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userMail = user.getUserMail();
        this.Password = user.getPassword();
        this.userAddress = user.getUserAddress();
        this.userPhone = user.getUserPhone();
        this.roles = user.getRoles();
        this.enabled=user.isEnabled();
    }

    public User(Long userid, String username,String usermail ,String userpassword,String useraddress,String userphone, Role roles) {
        this.userId = userid;
        this.userName = username;
        this.userMail = usermail;
        this.Password = userpassword;
        this.userAddress = useraddress;
        this.userPhone = userphone ;
        this.roles = roles;
        this.enabled=false;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setPassword(String userPassword) {
        this.Password = userPassword;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean setEnabled(boolean enabled) {
       return this.enabled = enabled;
    }
/*
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Subscription getSubscription() {
        return subscription;
    }
*/

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}