package br.com.turnover.repositories;

import br.com.turnover.models.AlocacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AlocacaoRepository extends JpaRepository<AlocacaoModel, UUID> {
    // Métodos de consulta personalizados, se necessário
    List<AlocacaoModel> findAllByFuncionarioId(UUID funcionarioId);
}
