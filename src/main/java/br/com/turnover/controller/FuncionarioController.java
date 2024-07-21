package br.com.turnover.controller;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    //busca todos os funcionarios pela rota "/funcionario"
    @GetMapping
    @PreAuthorize("hasRole('ROLE_RH')")
    public List<FuncionarioModel> getAllFuncionarios() {
        return funcionarioService.findAll();
    }

    //salva um funcionario pela rota "funcionario/salvar"
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> saveFuncionario(@RequestBody @Valid FuncionarioRecordDto funcionarioDto) {
        funcionarioService.saveFuncionario(funcionarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário criado com sucesso");
    }

    //busca um funcionario especifico pelo seu id na rota "funcionario/{id}"
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioModel> getFuncionarioById(@PathVariable UUID id) {
        Optional<FuncionarioModel> funcionario = funcionarioService.findById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //deleta um funcionario especifico pelo seu id na rota "funcionario/{id}"
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable UUID id) {
        funcionarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}