package br.com.turnover.services;


import br.com.turnover.models.CargoModel;
import br.com.turnover.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CargoService {
    // business rules
    @Autowired
    private CargoRepository cargoRepository;

    public List<CargoModel> findAll() {
        return cargoRepository.findAll();
    }

    public Optional<CargoModel> findById(UUID id) {
        return cargoRepository.findById(id);
    }

    public CargoModel save(CargoModel cargo) {
        return cargoRepository.save(cargo);
    }

    public void deleteById(UUID id) {
        cargoRepository.deleteById(id);
    }
}
