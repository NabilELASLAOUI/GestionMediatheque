package fr.miage.core.entity;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "user_media", catalog = "miage")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "userId")),
        @AssociationOverride(name = "pk.media",
                joinColumns = @JoinColumn(name = "mediaId")) })
public class UserMedia implements java.io.Serializable {

    private UserMediaId pk = new UserMediaId();
    private Date borrowingDate;
    private Date theoriticalReturnDate;
    private Date effectiveReturnDate;

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

    public void setUser(User stock) {
        getPk().setUser(stock);
    }

    @Transient
    public Media getMedia() {
        return getPk().getMedia();
    }

    public void setMedia(Media category) {
        getPk().setMedia(category);
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BORROWING_DATE", nullable = false, length = 10)
    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "THEORITICALRETURN_DATE", nullable = false, length = 10)
    public Date getTheoriticalReturnDate() {
        return theoriticalReturnDate;
    }

    public void setTheoriticalReturnDate(Date theoriticalReturnDate) {
        this.theoriticalReturnDate = theoriticalReturnDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECTIVERETURN_DATE", nullable = false, length = 10)
    public Date getEffectiveReturnDate() {
        return effectiveReturnDate;
    }

    public void setEffectiveReturnDate(Date effectiveReturnDate) {
        this.effectiveReturnDate = effectiveReturnDate;
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