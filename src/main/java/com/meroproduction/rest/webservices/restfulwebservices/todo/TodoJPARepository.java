package com.meroproduction.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.meroproduction.rest.webservices.restfulwebservices.AbstractJpaRepository;

@Repository
public interface TodoJPARepository extends AbstractJpaRepository<Todo> {

    List<Todo> findByUsername(String username);
}
