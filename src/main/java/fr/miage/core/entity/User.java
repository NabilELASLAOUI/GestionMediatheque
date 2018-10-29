package fr.miage.core.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="USER")
public class User {
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="userName")
    private String userName;

    @Column(name="userMail")
    private String userMail;

    @Column(name="userPassword")
    private String userPassword;

    @Column(name="userAddress")
    private String userAddress;

    @Column(name="userPhone")
    private String userPhone;

    @ManyToOne
    @JoinColumn(name="roleId")
    private Role roles;

    @ManyToOne
    @JoinColumn(name="subscriptionId")
    private Subscription subscription;

    public User() { }

    public User(Long userid, String username,String usermail ,String userpassword,String useraddress,String userphone, Role roles) {
        this.userId = userid;
        this.userName = username;
        this.userMail = usermail;
        this.userPassword = userpassword;
        this.userAddress = useraddress;
        this.userPhone = userphone ;
        this.roles = roles;
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

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public Role getRoles() {
        return roles;
    }

    public Subscription getSubscription() {
        return subscription;
    }
}