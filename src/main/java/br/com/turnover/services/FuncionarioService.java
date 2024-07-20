package br.com.turnover.services;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TipoDeAcessoEnum;
import br.com.turnover.enums.TurnoEnum;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.models.UsuarioModel;
import br.com.turnover.repositories.FuncionarioRepository;
import br.com.turnover.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, UsuarioRepository usuarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public List<FuncionarioModel> findAll() {
        return funcionarioRepository.findAll();
    }


    public Optional<FuncionarioModel> findById(UUID id) {
        return funcionarioRepository.findById(id);
    }


    public Optional<FuncionarioModel> findByUsuarioByCpf(String cpf) {
        return funcionarioRepository.findByUsuarioCpf(cpf);
    }


    public void saveFuncionario(FuncionarioRecordDto funcionarioRecordDto) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        UsuarioModel usuarioModel = new UsuarioModel();
        funcionarioModel.setNome(funcionarioRecordDto.nome());
        funcionarioModel.setNascimento(funcionarioRecordDto.nascimento());
        funcionarioModel.setContratacao(funcionarioRecordDto.contratacao());
        funcionarioModel.setEmail(funcionarioRecordDto.email());
        funcionarioModel.setTurno(TurnoEnum.valueOf(funcionarioRecordDto.turno().toUpperCase()));
        funcionarioModel.setModalidade(ModalidadeEnum.valueOf(funcionarioRecordDto.modalidade().toUpperCase()));
        usuarioModel.setCpf(funcionarioRecordDto.cpf());
        usuarioModel.setSenha(passwordEncoder.encode(funcionarioRecordDto.senha()));
        usuarioModel.setTipoDeAcessoEnum(TipoDeAcessoEnum.valueOf(funcionarioRecordDto.tipoDeAcesso().toUpperCase()));
        funcionarioRepository.save(funcionarioModel);
        usuarioRepository.save(usuarioModel);
    }


    public void deleteById(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

}