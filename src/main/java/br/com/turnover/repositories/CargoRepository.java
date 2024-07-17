package br.com.turnover.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CargoRepository extends JpaRepository<CargoRepository, UUID> {
}
