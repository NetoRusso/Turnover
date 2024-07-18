package br.com.turnover.services;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<FuncionarioModel> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioModel> findById(UUID id) {
        return funcionarioRepository.findById(id);
    }

    public void saveFuncionario(FuncionarioModel funcionario) {
         funcionarioRepository.save(funcionario);
    }

    public void deleteById(UUID id) {
        funcionarioRepository.deleteById(id);
    }

    public FuncionarioModel createFuncionario(FuncionarioModel funcionario) {
            return funcionarioRepository.save(funcionario);

    }
}

