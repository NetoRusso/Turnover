package br.com.turnover.controller;

import br.com.turnover.models.CargoModel;
import br.com.turnover.services.CargoService;
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
@RequestMapping("/cargos")
public class CargoController {
    // TODOS ESTES METODOS FORAM TESTADOS NO POSTMAN COM AUTENTICAÇAO E ESTAO FUNCIONANDO

    @Autowired
    private CargoService cargoService;

    @PreAuthorize("hasAnyRole('ROLE_CEO', 'ROLE_RH', 'ROLE_GESTOR')")
    @GetMapping
    public List<CargoModel> getAllCargos() {
        return cargoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoModel> getCargoById(@PathVariable UUID id) {
        Optional<CargoModel> cargo = cargoService.findById(id);
        return cargo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO')")
    @PostMapping
    public CargoModel createCargo(@RequestBody CargoModel cargo) {
        return cargoService.save(cargo);
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCargo(@PathVariable UUID id) {
        cargoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO')")
    @PatchMapping("/{id}")
    public ResponseEntity<CargoModel> patchCargo(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        Optional<CargoModel> cargoOptional = cargoService.findById(id);
        if (!cargoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CargoModel cargo = cargoOptional.get();
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(CargoModel.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, cargo, value);
            }
        });
        CargoModel updatedCargo = cargoService.save(cargo);
        return ResponseEntity.ok(updatedCargo);
    }
}

