package br.com.turnover.services;

import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<FuncionarioModel> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioModel> findById(UUID id) {
        return funcionarioRepository.findById(id);
    }

    public void saveFuncionario(FuncionarioModel funcionarioModel) {

        if (funcionarioRepository.existsByCpf(funcionarioModel.getCpf())) {
            throw new RuntimeException("CPF já existe");
        }


        funcionarioRepository.save(funcionarioModel);
    }

    public void deleteById(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }
}


