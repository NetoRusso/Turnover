package br.com.turnover.services;

import br.com.turnover.dtos.AlocacaoRecordDto;
import br.com.turnover.models.AlocacaoModel;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.repositories.AlocacaoRepository;
import br.com.turnover.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AlocacaoService {
    @Autowired
    private FuncionarioRepository FuncionarioRepository;
    @Autowired
    private AlocacaoRepository alocacaoRepository;

    public List<AlocacaoModel> findAll() {
        return alocacaoRepository.findAll();
    }

    public List<AlocacaoModel> findAllByFuncionarioId(UUID id) {
        return alocacaoRepository.findAllByFuncionarioId(id);
    }

    public AlocacaoModel save(AlocacaoRecordDto alocacao) {
        FuncionarioModel funcionarioModel = FuncionarioRepository.findById(alocacao.funcionarioId()).orElse(null);
        if (funcionarioModel == null) {
            throw new RuntimeException("Funcionario não encontrado");
        }
        AlocacaoModel alocacaoModel = new AlocacaoModel();
        alocacaoModel.setAlteracao(alocacao.alteracao());
        alocacaoModel.setFuncionario(funcionarioModel);
        alocacaoModel.setDataAtualizacao(LocalDateTime.now());
        return alocacaoRepository.save(alocacaoModel);
    }

    public void deleteById(UUID funcionarioId) {
        List<AlocacaoModel> alocacoes = alocacaoRepository.findAllByFuncionarioId(funcionarioId);
        alocacaoRepository.deleteAll(alocacoes);
    }

}