package io.github.rluu.flashcards.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="flshcrd_lst")
public class FlashCardList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="flshcrd_lst_id")
    @JsonProperty
    private Long id;

    @Column(name="crte_dttm_utc")
    @JsonProperty
    private Date createdDateTimeUtc;
    
    @Column(name="lst_mdfd_dttm_utc")
    @JsonProperty
    private Date lastModifiedDateTimeUtc;

    @Column(name="nm")
    @JsonProperty
    private String name;

    @Column(name="dscrptn")
    @JsonProperty
    private String description;

    @OneToMany
    @JoinTable(name="flshcrd_lst_flshcrd", 
    	       joinColumns=@JoinColumn(name="flshcrd_lst_id"), 
    	       inverseJoinColumns=@JoinColumn(name="flshcrd_id"))
    @JsonProperty
    private List<FlashCard> flashCards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FlashCard> getFlashCards() {
        return flashCards;
    }

    public void setFlashCards(List<FlashCard> flashCards) {
        this.flashCards = flashCards;
    }

    @Override
    public String toString() {
       return ToStringBuilder.reflectionToString(this);
    }
}
