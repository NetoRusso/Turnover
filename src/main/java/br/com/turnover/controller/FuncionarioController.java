package br.com.turnover.controller;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<FuncionarioModel> getAllFuncionarios() {
        return funcionarioService.findAll();
    }

    //busca um funcionario especifico pelo seu cpf na rota "funcionario/cpf?cpf={valor_do_cpf}"
    @GetMapping("/cpf")
    public ResponseEntity<FuncionarioModel> getFuncionarioByCpf(@RequestParam("cpf") String cpf) {
        Optional<FuncionarioModel> funcionario = funcionarioService.findByCpf(cpf);
        return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //salva um funcionario pela rota "funcionario/salvar"
    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> saveFuncionario(@RequestBody @Valid FuncionarioRecordDto funcionarioDto) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioDto, funcionarioModel);
        funcionarioService.saveFuncionario(funcionarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário criado com sucesso");
    }

    //deleta um funcionario especifico pelo seu cpf na rota "funcionario/salvar"
    @DeleteMapping("/cpf")
    public ResponseEntity<String> deleteByCpf(@RequestParam("cpf") String cpf) {
        funcionarioService.deleteByCpf(cpf);
        return ResponseEntity.ok("Funcionário deletado com sucesso");
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