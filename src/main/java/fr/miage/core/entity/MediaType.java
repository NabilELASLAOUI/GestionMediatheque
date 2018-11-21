package fr.miage.core.entity;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="MEDIATYPE")
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="typeName")
    private String typeName;

    @OneToMany(mappedBy = "type")
    private List<Media> medias;

    public MediaType(){}

    public MediaType(String typeName, List<Media> medias) {
        this.typeName = typeName;
        this.medias = medias;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    public List<Media> getMedias() {
        return medias;
    }
}
