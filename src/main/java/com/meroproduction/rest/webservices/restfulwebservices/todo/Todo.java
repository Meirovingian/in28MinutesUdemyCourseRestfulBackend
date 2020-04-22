package com.meroproduction.rest.webservices.restfulwebservices.todo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.meroproduction.rest.webservices.restfulwebservices.AbstractEntity;

@Entity
public class Todo extends AbstractEntity {

    @Column(name = "user_name", nullable = false)
    private String username = null;
    private String description = null;
    private Date targetDate = null;
    private boolean done = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private TodoList parent = null;

    public Todo() {
	super();
    }

    public Todo(Long id, String username, String description, Date targetDate, boolean done) {
	super(id);
	this.username = username;
	this.description = description;
	this.targetDate = targetDate;
	this.done = done;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Date getTargetDate() {
	return targetDate;
    }

    public void setTargetDate(Date targetDate) {
	this.targetDate = targetDate;
    }

    public boolean isDone() {
	return done;
    }

    public void setDone(boolean done) {
	this.done = done;
    }

    public TodoList getParent() {
	return parent;
    }

    public void setParent(TodoList parent) {
	this.parent = parent;
    }

}
