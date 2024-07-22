package br.com.turnover.controller;

import br.com.turnover.models.DepartamentoModel;
import br.com.turnover.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    // TODOS ESTES METODOS FORAM TESTADOS NO POSTMAN COM AUTENTICAÇAO E ESTAO FUNCIONANDO

    @Autowired
    private DepartamentoService departamentoService;

    // Somente o CEO e o RH podem ver TODOS os departamentos
    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH')")
    @GetMapping
    public List<DepartamentoModel> getAllDepartamentos() {
        return departamentoService.findAll();
    }

    // TODOS podem Pesquisar um Departamento pelo ID
    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR', 'ROLE_FUNCIONARIO')")
    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoModel> getDepartamentoById(@PathVariable UUID id) {
        Optional<DepartamentoModel> departamento = departamentoService.findById(id);
        return departamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Somente o CEO pode Criar um Departamento
    @PreAuthorize("hasAnyRole('ROLE_CEO')")
    @PostMapping
    public DepartamentoModel createDepartamento(@RequestBody DepartamentoModel departamento) {
        return departamentoService.save(departamento);
    }

    // Somente o CEO pode Atualizar um Departamento a partir do seu ID
    @PreAuthorize("hasAnyRole('ROLE_CEO')")
    @PatchMapping("/{id}")
    public ResponseEntity<DepartamentoModel> patchDepartamento(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        Optional<DepartamentoModel> departamentoOptional = departamentoService.findById(id);
        if (departamentoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        DepartamentoModel departamento = departamentoOptional.get();
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(DepartamentoModel.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, departamento, value);
            }
        });
        DepartamentoModel updatedDepartamento = departamentoService.save(departamento);
        return ResponseEntity.ok(updatedDepartamento);
    }

    // Somente o CEO pode Deletar um Departamento
    @PreAuthorize("hasAnyRole('ROLE_CEO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable UUID id) {
        departamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

