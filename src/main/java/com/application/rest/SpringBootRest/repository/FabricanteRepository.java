package com.application.rest.SpringBootRest.repository;

import com.application.rest.SpringBootRest.entities.Fabricante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends CrudRepository<Fabricante, Long> {
}
