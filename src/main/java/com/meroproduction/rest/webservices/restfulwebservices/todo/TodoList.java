package com.meroproduction.rest.webservices.restfulwebservices.todo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.meroproduction.rest.webservices.restfulwebservices.AbstractEntity;

@Entity
public class TodoList extends AbstractEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Todo> list = null;

    public TodoList() {
	super();
    }

    public List<Todo> getList() {
	return list;
    }

    public void setList(List<Todo> list) {
	this.list = list;
    }

}
