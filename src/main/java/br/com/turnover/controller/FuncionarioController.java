package br.com.turnover.controller;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR')")
    @GetMapping
    public ResponseEntity<List<FuncionarioModel>> getAllFuncionarios() {
        List<FuncionarioModel> funcionarios = funcionarioService.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR', 'ROLE_FUNCIONARIO')")
    @PostMapping("/cpf/{cpf}")//LOGIN
    public ResponseEntity<FuncionarioModel> loginUsuarioCpf(@PathVariable String cpf) {
        Optional<FuncionarioModel> funcionario = funcionarioService.findByUsuarioCpf(cpf);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR', 'ROLE_FUNCIONARIO')")
    @GetMapping("/buscarCPF/{cpf}")
    public ResponseEntity<FuncionarioModel> findByUsuarioCpf(@PathVariable String cpf) {
        Optional<FuncionarioModel> funcionario = funcionarioService.findByUsuarioCpf(cpf);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR', 'ROLE_FUNCIONARIO')")
    @GetMapping("/departamento")
    public ResponseEntity<List<FuncionarioModel>> findByDepartamentoIsNull() {
        List<FuncionarioModel> funcionarios = funcionarioService.findByDepartamentoIsNull();
        return ResponseEntity.ok(funcionarios);
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR', 'ROLE_FUNCIONARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioModel> getFuncionarioById(@PathVariable UUID id) {
        Optional<FuncionarioModel> funcionario = funcionarioService.findById(id);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //@PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR', 'ROLE_FUNCIONARIO')")
    //@Secured({"ROLE_CEO", "ROLE_RH"})
    @PostMapping("/salvar")
    public ResponseEntity<Void> saveFuncionario(@RequestBody FuncionarioRecordDto funcionarioDto) {
        funcionarioService.saveFuncionario(funcionarioDto);
        return ResponseEntity.created(URI.create("/funcionario/salvar")).build();
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_GESTOR')")
    @GetMapping("/departamento/{departamentoId}")
    public List<FuncionarioModel> getAllFuncionarioByDepartamentoId(@PathVariable UUID departamentoId) {
        return funcionarioService.findAllByDepartamentoId(departamentoId);
    }

    //deleta um funcionario especifico pelo seu id na rota "funcionario/{id}"
    @PreAuthorize("hasAnyRole('ROLE_RH', 'ROLE_CEO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable UUID id) {
        funcionarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_RH', 'ROLE_CEO', 'ROLE_GESTOR')")
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<Void> updateFuncionario(@PathVariable UUID id, @RequestBody FuncionarioRecordDto funcionarioDto) {
        funcionarioService.updateFuncionario(id, funcionarioDto);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content para atualização parcial
    }
}