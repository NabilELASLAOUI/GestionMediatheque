package fr.miage.core.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "user_media")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "userId")),
        @AssociationOverride(name = "pk.media",
                joinColumns = @JoinColumn(name = "mediaId")) })
public class UserMedia{

    private UserMediaId pk = new UserMediaId();
    private LocalDateTime borrowingDate = LocalDateTime.now();
    private LocalDateTime returnDate= borrowingDate.plusDays(30);
    private boolean status= false;


    public UserMedia() {
    }

    @EmbeddedId
    public UserMediaId getPk() {
        return pk;
    }

    public void setPk(UserMediaId pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Media getMedia() {
        return getPk().getMedia();
    }

    public void setMedia(Media media) {
        getPk().setMedia(media);
    }


    public LocalDateTime getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDateTime borrowingDtae) {
        this.borrowingDate = borrowingDtae;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserMedia that = (UserMedia) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}