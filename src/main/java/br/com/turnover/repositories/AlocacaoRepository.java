package br.com.turnover.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlocacaoRepository<Alocacao> extends JpaRepository<Alocacao, Long> {
    <Alocacao> Optional<Alocacao> findById(Long id);
}
