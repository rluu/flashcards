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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="usr")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="usr_id")
    @JsonProperty
    private Long id;

    @Column(name="crte_dttm_utc")
    @JsonProperty
    private Date createdDateTimeUtc;
    
    @Column(name="lst_mdfd_dttm_utc")
    @JsonProperty
    private Date lastModifiedDateTimeUtc;

    @Column(name="fst_nm")
    @JsonProperty
    private String firstName;

    @Column(name="lst_nm")
    @JsonProperty
    private String lastName;

    @Column(name="usr_nm")
    @JsonProperty
    private String username;
    
    @Column(name="pswd")
    @JsonProperty
    private String password;

    @Column(name="eml_addr")
    @JsonProperty
    private String emailAddress;

    @OneToMany
    @JoinTable(name="usr_role", 
    	       joinColumns=@JoinColumn(name="usr_id"), 
    	       inverseJoinColumns=@JoinColumn(name="role_id"))
    @JsonProperty
    private List<Role> roles;

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
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public boolean isUserInRole(String roleToCheck) {
	if (roles != null) {
	    for (Role role : roles) {
		if (role != null && 
		    role.getName() != null && 
		    role.getName().equals(roleToCheck)) {

		    return true;
		}
	    }
	}
	return false;
    }
}
