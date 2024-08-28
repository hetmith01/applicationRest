package com.application.rest.SpringBootRest.service.impl;

import com.application.rest.SpringBootRest.entities.Fabricante;
import com.application.rest.SpringBootRest.persistence.IFabricanteDAO;
import com.application.rest.SpringBootRest.service.IFabricanteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FabricanteServiceImpl implements IFabricanteService {

    private IFabricanteDAO fabricanteDAO;

    @Override
    public List<Fabricante> findAll() {
        return (List<Fabricante>) fabricanteDAO.findAll();
    }

    @Override
    public Optional<Fabricante> findById(Long id) {
        return fabricanteDAO.findById(id);
    }

    @Override
    public void save(Fabricante fabricante) {
        fabricanteDAO.save(fabricante);
    }

    @Override
    public void deleteById(Long id) {
        fabricanteDAO.deleteById(id);
    }
}
