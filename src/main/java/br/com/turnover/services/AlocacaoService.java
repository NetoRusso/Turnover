package br.com.turnover.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.turnover.repositories.AlocacaoRepository;

import java.util.List;

@Service
public class AlocacaoService {
    @Autowired
    private AlocacaoRepository alocacaoRepository;

    public List findAll() {
        return alocacaoRepository.findAll();
    }

    public Object findById(Long id) {
        return alocacaoRepository.findById(id).orElse(null);
    }

    public <Alocacao> Alocacao save(Alocacao alocacao) {

        Alocacao save = (Alocacao) alocacaoRepository.save(alocacao);
        return save;
    }
    public void deleteById(Long id) {
        alocacaoRepository.deleteById(id);
    }
}


