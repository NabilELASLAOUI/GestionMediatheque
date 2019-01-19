package fr.miage.core.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/* l'annotation @Entity et @Table pour utiliser JPA dans la gérération de la table Role dans la base de données  */
@Entity
@Table(name="ROLE")
public class Role {

    /* l'annotation @ID pour spécifer que le champ est un identifiant*/
    /* l'annotation @Column pour spécifier le nom de l'attribut dans la table*/
    /* l'annotation @GeneratedValue pour générer l'id automatiquement*/
    @Id
    @Column(name="roleId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name="roleName")
    private String roleName;

    /* l'annotation @OneToMany pour utiliser la relation 1:n et mappedBy pour indiquer le champ dans l'autre entité */
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

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}