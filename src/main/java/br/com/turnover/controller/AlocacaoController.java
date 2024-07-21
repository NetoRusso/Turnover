package br.com.turnover.controller;


import br.com.turnover.dtos.AlocacaoRecordDto;
import br.com.turnover.models.AlocacaoModel;
import br.com.turnover.services.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alocacoes")
public class AlocacaoController {
    @Autowired
    private AlocacaoService alocacaoService;

    @GetMapping
    public List<AlocacaoModel> getAllAlocacoes() {
        return alocacaoService.findAll();
    }

//    //@GetMapping("/{id}")
//    public <Alocacao> Alocacao getAlocacaoById(@PathVariable Long id) {
//        return (Alocacao) alocacaoService.findById(id);
//    }

    @PostMapping
    public AlocacaoModel createAlocacao(@RequestBody AlocacaoRecordDto alocacao) {
        return alocacaoService.save(alocacao);
    }

//    @PutMapping("/{id}")
//    public <Alocacao> Alocacao updateAlocacao(@PathVariable Long id, @RequestBody Alocacao alocacao) throws InterruptedException {
//        alocacao.wait(id);
//        return alocacaoService.save(alocacao);
//    }

//    @DeleteMapping("/{id}")
//    public void deleteAlocacao(@PathVariable Long id) {
//        alocacaoService.deleteById(id);
//    }
}