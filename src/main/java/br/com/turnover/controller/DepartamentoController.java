package br.com.turnover.controller;

import br.com.turnover.models.DepartamentoModel;
import br.com.turnover.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
    // ... GET, POST, DELETE, UPDATE methods
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public List<DepartamentoModel> getAllDepartamentos() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoModel> getDepartamentoById(@PathVariable UUID id) {
        Optional<DepartamentoModel> departamento = departamentoService.findById(id);
        return departamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public DepartamentoModel createDepartamento(@RequestBody DepartamentoModel departamento) {
        return departamentoService.save(departamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable UUID id) {
        departamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

