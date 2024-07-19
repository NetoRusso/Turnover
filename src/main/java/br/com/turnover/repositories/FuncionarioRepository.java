package br.com.turnover.repositories;

import br.com.turnover.models.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {

    boolean existsByCpf(String cpf);

    Optional<FuncionarioModel> findByCpf(String cpf);

    String deleteByCpf(String cpf);
}
