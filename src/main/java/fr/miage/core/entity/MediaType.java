package fr.miage.core.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="MEDIATYPE")
public class MediaType {
    @Id
    @Column(name="typeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    @Column(name="typeName")
    private String typeName;

    @OneToMany(mappedBy = "type")
    private final List<User> users;

    public Long getMediaTypeId() {
        return typeId;
    }

    public String getMediaTypeName() {
        return typeName;
    }


    public List<User> getUsers() {
        return users;
    }
}
