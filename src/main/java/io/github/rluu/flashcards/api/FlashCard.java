package io.github.rluu.flashcards.api;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="flshcrd")
public class FlashCard implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="flshcrd_id")
    @JsonProperty
    private Long id;

    @Column(name="crte_dttm_utc")
    @JsonProperty
    private Date createdDateTimeUtc;
    
    @Column(name="lst_mdfd_dttm_utc")
    @JsonProperty
    private Date lastModifiedDateTimeUtc;

    @Column(name="sd1")
    @JsonProperty
    private String textSide1;

    @Column(name="sd2")
    @JsonProperty
    private String textSide2;

    @Column(name="dscrptn")
    @JsonProperty
    private String userNotes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDateTimeUtc() {
        return createdDateTimeUtc;
    }

    public void setCreatedDateTimeUtc(Date createdDateTimeUtc) {
        this.createdDateTimeUtc = createdDateTimeUtc;
    }

    public Date getLastModifiedDateTimeUtc() {
        return lastModifiedDateTimeUtc;
    }

    public void setLastModifiedDateTimeUtc(Date lastModifiedDateTimeUtc) {
        this.lastModifiedDateTimeUtc = lastModifiedDateTimeUtc;
    }

    public String getTextSide1() {
        return textSide1;
    }

    public void setTextSide1(String textSide1) {
        this.textSide1 = textSide1;
    }

    public String getTextSide2() {
        return textSide2;
    }

    public void setTextSide2(String textSide2) {
        this.textSide2 = textSide2;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
}
