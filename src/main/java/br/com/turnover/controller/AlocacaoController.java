package br.com.turnover.controller;


import br.com.turnover.dtos.AlocacaoRecordDto;
import br.com.turnover.models.AlocacaoModel;
import br.com.turnover.services.AlocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alocacoes")
public class AlocacaoController {
    @Autowired
    private AlocacaoService alocacaoService;

    @GetMapping
    public List<AlocacaoModel> getAllAlocacoes() {
        return alocacaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<AlocacaoModel>> findAllByFuncionarioId(@PathVariable UUID id) {
        return ResponseEntity.ok(alocacaoService.findAllByFuncionarioId(id));
    }

    @DeleteMapping("/{funcionarioId}")
    public ResponseEntity<Void> deleteAlocacoesByFuncionarioId(@PathVariable UUID funcionarioId) {
        alocacaoService.deleteById(funcionarioId);
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public AlocacaoModel createAlocacao(@RequestBody AlocacaoRecordDto alocacao) {
        return alocacaoService.save(alocacao);
    }



//    @PutMapping("/{id}")
//    public <Alocacao> Alocacao updateAlocacao(@PathVariable Long id, @RequestBody Alocacao alocacao) throws InterruptedException {
//        alocacao.wait(id);
//        return alocacaoService.save(alocacao);
//    }


}