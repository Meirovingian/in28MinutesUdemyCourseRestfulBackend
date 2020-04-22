package com.meroproduction.rest.webservices.restfulwebservices.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TodoHardcodedService {

    private static final List<Todo> TODOS = new ArrayList<>();
    private static long counter = 0;

    static {
	TODOS.add(new Todo(counter++, "Mero", "Learn to dance Lindy up !", new Date(), false));
	TODOS.add(new Todo(counter++, "Mero", "Learn ReactJS !", new Date(), false));
	TODOS.add(new Todo(counter++, "Mero", "Become a fullstack developer TEST", new Date(), false));
    }

    public Todo save(Todo todo) {
	if (todo != null) {
	    return todo.getId() > -1L ? updateTodo(todo) : createTodo(todo);
	}
	return null;
    }

    private Todo createTodo(Todo todo) {
	if (todo != null) {
	    todo.setId(getNewIdentifier());
	    TODOS.add(todo);
	    return todo;
	}
	return null;
    }

    private static long getNewIdentifier() {
	return counter++;
    }

    private Todo updateTodo(Todo todo) {
	if (todo != null) {
	    long id = todo.getId();
	    if (id > -1L) {
		Todo toUpdate = findById(id);
		if (toUpdate != null) {
		    toUpdate.setDescription(todo.getDescription());
		    toUpdate.setTargetDate(todo.getTargetDate());
		    return findById(id);
		}
	    }
	}
	return null;
    }

    public List<Todo> findAll() {
	return TODOS;
    }

    public List<Todo> findUserTodoList(String username) {
	if (StringUtils.hasText(username)) {
	    return TODOS.stream().filter(filterByUsername(username)).collect(Collectors.toList());
	}
	return new ArrayList<>();
    }

    public Todo findUserSpecificTodo(String username, long id) {
	if (StringUtils.hasText(username) && id > -1L) {
	    Optional<Todo> first = TODOS.stream().filter(filterByUsernameAndId(username, id)).findFirst();
	    return first.isPresent() ? first.get() : null;
	}
	return null;
    }

    private Predicate<? super Todo> filterByUsernameAndId(String username, long id) {
	return todo -> {
	    String todoUsername = todo.getUsername();
	    long todoId = todo.getId();
	    return todoId > -1L && todoId == id && StringUtils.hasText(todoUsername)
		    && todoUsername.compareTo(username) == 0;
	};
    }

    public Todo deleteById(long id) {
	Todo todo = findById(id);
	if (todo != null && TODOS.remove(todo)) {
	    return todo;
	}
	return null;
    }

    private Todo findById(long id) {
	if (id > -1L) {
	    Optional<Todo> first = TODOS.stream().filter(filterById(id)).findFirst();
	    return first.isPresent() ? first.get() : null;
	}
	return null;
    }

    private Predicate<? super Todo> filterById(long id) {
	return todo -> {
	    long todoId = todo.getId();
	    return todoId > -1L && todoId == id;
	};
    }

    private Predicate<? super Todo> filterByUsername(String username) {
	return todo -> {
	    String todoUsername = todo.getUsername();
	    return StringUtils.hasText(todoUsername) && todoUsername.compareTo(username) == 0;
	};
    }

}
