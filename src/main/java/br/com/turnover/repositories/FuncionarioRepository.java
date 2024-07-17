package br.com.turnover.repositories;

import br.com.turnover.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {

}
