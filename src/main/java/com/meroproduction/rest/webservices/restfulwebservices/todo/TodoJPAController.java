package com.meroproduction.rest.webservices.restfulwebservices.todo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/jpa/users/{username}/todos")
public class TodoJPAController {

    @Autowired
    private TodoJPARepository todoRepo;

    @GetMapping
    public List<Todo> getAllTodos(@PathVariable String username) {
	return todoRepo.findByUsername(username);
    }

    @GetMapping("/{id}")
    public Todo getSpecificTodo(@PathVariable String username, @PathVariable long id) {
	Optional<Todo> element = todoRepo.findById(id);
	return element.isPresent() ? element.get() : null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
	todoRepo.deleteById(id);
	return ResponseEntity.noContent().build();
//	return todoService.deleteById(id) != null ? ResponseEntity.noContent().build()
//		: ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
	    @RequestBody Todo todo) {
	return new ResponseEntity<>(todoRepo.save(todo), HttpStatus.OK);
//	return new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@PathVariable String username, @RequestBody Todo todo) {
	todo.setUsername(username);
	Todo save = todoRepo.save(todo);
	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(save.getId()).toUri();
	return ResponseEntity.created(uri).build();
    }

}
