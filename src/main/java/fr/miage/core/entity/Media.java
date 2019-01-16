package fr.miage.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="MEDIA")
public class Media {

    @Id
    @Column(name="mediaId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long mediaId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.media")
    private Set<UserMedia> userMedias = new HashSet<>(0);

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

    @ManyToOne
    @JoinColumn(name = "typeId", nullable = false)
    private MediaType mediaType;

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
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

    public Set<UserMedia> getUserMedias() {
        return userMedias;
    }

    public void setUserMedias(Set<UserMedia> userMedias) {
        this.userMedias = userMedias;
    }




    public Media() {}

    public Media(String mediaTitle, String mediaDescription, String mediaAuthor, MediaType type, Set<UserMedia> userMedias) {
        this.mediaTitle = mediaTitle;
        this.mediaDescription=mediaDescription;
        this.mediaAuthor= mediaAuthor;
        this.mediaStatus=true;
        this.mediaType= type;
        this.userMedias=userMedias;
    }




    @Override
    public String toString() {
        return String.format("Media[id=%d, name='%s']", mediaId, mediaTitle);
    }

}