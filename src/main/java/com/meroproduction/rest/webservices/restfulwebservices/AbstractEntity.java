package com.meroproduction.rest.webservices.restfulwebservices;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    public AbstractEntity() {
	super();
    }

    public AbstractEntity(Long id) {
	super();
	this.id = id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public Long getVersion() {
	return version;
    }

    public void setVersion(Long version) {
	this.version = version;
    }

    public Date getCreatedOn() {
	return createdOn;
    }

    public Date getUpdatedOn() {
	return updatedOn;
    }

    @PrePersist
    private void onPersist() {
	this.createdOn = new Date();
    }

    @PreUpdate
    private void onUpdate() {
	this.updatedOn = new Date();
    }

}
