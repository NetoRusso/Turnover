package br.com.turnover.services;

import br.com.turnover.models.DepartamentoModel;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.repositories.DepartamentoRepository;
import br.com.turnover.repositories.FuncionarioRepository;
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

    @Autowired
    private FuncionarioRepository funcionarioRepository;

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
        List<FuncionarioModel> listaFuncionarios = funcionarioRepository.findAllByDepartamentoId(id);
        for (FuncionarioModel funcionarioModel : listaFuncionarios) {
            funcionarioModel.setDepartamento(null);
            funcionarioRepository.save(funcionarioModel);
        }
        departamentoRepository.deleteById(id);
    }

}