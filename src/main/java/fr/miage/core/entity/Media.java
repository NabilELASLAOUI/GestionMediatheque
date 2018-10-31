package fr.miage.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="MEDIA")
public class Media {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, max=30)
    @Column(name="mediaTitle",nullable = false)
    private String mediaTitle;

    @NotNull
    @Size(min=5, max=255)
    @Column(name="mediaDescription",nullable = false)
    private String mediaDescription;

    @NotNull
    @Column(name="mediaStatus", nullable = false)
    private boolean mediaStatus;

    @NotNull
    @Column(name="mediaAuthor",nullable = false)
    @Size(min=5, max=30)
    private String mediaAuthor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public void setMediaTitle(String mediaTitle) {
        this.mediaTitle = mediaTitle;
    }

    public String getMediaDescription() {
        return mediaDescription;
    }

    public void setMediaDescription(String mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    public boolean isMediaStatus() {
        return mediaStatus;
    }

    public void setMediaStatus(boolean mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public String getMediaAuthor() {
        return mediaAuthor;
    }

    public void setMediaAuthor(String mediaAuthor) {
        this.mediaAuthor = mediaAuthor;
    }

    @ManyToOne
    @JoinColumn(name = "typeId", nullable = false)
    private MediaType type;

    public Media() {}

    public Media(String mediaTitle, String mediaDescription, String mediaAuthor, MediaType type) {
        this.mediaTitle = mediaTitle;
        this.mediaDescription=mediaDescription;
        this.mediaAuthor= mediaAuthor;
        this.mediaStatus=true;
        this.type= type;
    }




    @Override
    public String toString() {
        return String.format("Media[id=%d, name='%s']", id, mediaTitle);
    }

}