package com.meroproduction.rest.webservices.restfulwebservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbstractJpaRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

}
