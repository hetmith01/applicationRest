package com.application.rest.SpringBootRest.service;

import com.application.rest.SpringBootRest.entities.Fabricante;

import java.util.List;
import java.util.Optional;

public interface IFabricanteService {

    List<Fabricante> findAll();

    Optional<Fabricante> findById(Long id);

    void save(Fabricante fabricante);

    void deleteById(Long id);
}
