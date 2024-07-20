package br.com.turnover.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository<Usuario> extends JpaRepository<Usuario, Long> {
}
