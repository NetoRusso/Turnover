package br.com.turnover.services;

import br.com.turnover.models.DepartamentoModel;
import br.com.turnover.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartamentoService {
    // business rules
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<DepartamentoModel> findAll() {
        return departamentoRepository.findAll();
    }

    public Optional<DepartamentoModel> findById(UUID id) {
        return departamentoRepository.findById(id);
    }

    public DepartamentoModel save(DepartamentoModel departamento) {
        return departamentoRepository.save(departamento);
    }

    public void deleteById(UUID id) {
        departamentoRepository.deleteById(id);
    }
}

