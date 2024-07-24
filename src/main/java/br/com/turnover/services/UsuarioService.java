package br.com.turnover.services;

import br.com.turnover.dtos.UsuarioRecordDto;
import br.com.turnover.models.UsuarioModel;
import br.com.turnover.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    public Optional<UsuarioModel> findByCpf(String cpf) {
//        return usuarioRepository.findByCpf(cpf);
//    }
//
    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }

//    public Optional<UsuarioModel> findById(UUID id) {
//        return usuarioRepository.findById(id);
//    }

    public UsuarioModel save(UsuarioRecordDto usuario) {
        if (usuarioRepository.existsByCpf(usuario.cpf())) {
            throw new RuntimeException("CPF já existe");
        }
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setCpf(usuario.cpf());
        usuarioModel.setSenha(passwordEncoder.encode(usuario.senha())); // Criptografar a senha antes de salvar
        usuarioModel.setTipoDeAcessoEnum(usuario.tipoDeAcesso());
        //usuarioModel.setFuncionario(usuario.funcionario()); // Pode ser null no início
        return usuarioRepository.save(usuarioModel);
    }

//    public void deleteById(UUID id) {
//        usuarioRepository.deleteById(id);
//    }
}
