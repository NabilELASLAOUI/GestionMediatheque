package fr.miage.core.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="ROLE")
public class Role {
    @Id
    @Column(name="roleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name="roleName")
    private String roleName;

    @OneToMany(mappedBy = "roles")
    private final List<User> users = new LinkedList<>();

    public Role() { }
    public Role(Long roleId,String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
