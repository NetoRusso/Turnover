package br.com.turnover.repositories;

import br.com.turnover.models.DepartamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<DepartamentoModel, UUID> {
}
