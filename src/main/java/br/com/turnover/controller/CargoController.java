package br.com.turnover.controller;

import java.util.List;
import br.com.turnover.models.CargoModel;
import br.com.turnover.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cargos")
public class CargoController {
    // ... GET, POST, DELETE, UPDATE methods
    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<CargoModel> getAllCargos() {
        return cargoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoModel> getCargoById(@PathVariable UUID id) {
        Optional<CargoModel> cargo = cargoService.findById(id);
        return cargo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CargoModel createCargo(@RequestBody CargoModel cargo) {
        return cargoService.save(cargo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCargo(@PathVariable UUID id) {
        cargoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

