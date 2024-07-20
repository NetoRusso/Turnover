package br.com.turnover.services;

import br.com.turnover.models.UsuarioModel;
import br.com.turnover.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuarioModel> findByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}
