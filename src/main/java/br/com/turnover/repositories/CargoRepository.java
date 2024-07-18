package br.com.turnover.repositories;

import br.com.turnover.models.CargoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CargoRepository extends JpaRepository<CargoModel, UUID> {
    // database transaction methods
}
