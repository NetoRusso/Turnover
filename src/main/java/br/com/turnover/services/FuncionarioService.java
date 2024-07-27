package br.com.turnover.services;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TurnoEnum;
import br.com.turnover.models.*;
import br.com.turnover.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CargoRepository cargoRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private AlocacaoRepository alocacaoRepository;

    public List<FuncionarioModel> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioModel> findById(UUID id) {
        return funcionarioRepository.findById(id);
    }

    public List<FuncionarioModel> findAllByDepartamentoId(UUID id) {
        return funcionarioRepository.findAllByDepartamentoId(id);
    }

    public Optional<FuncionarioModel> findByUsuarioCpf(String cpf) {
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

        funcionarioModel.setCargo(null);
        if (funcionarioRecordDto.cargo() != null) {
            CargoModel cargoModel = cargoRepository.getReferenceById(funcionarioRecordDto.cargo());
            funcionarioModel.setCargo(cargoModel);
        }

        funcionarioModel.setDepartamento(null);
        if (funcionarioRecordDto.departamento() != null) {
            DepartamentoModel departamentoModel = departamentoRepository.getReferenceById(funcionarioRecordDto.departamento());
            funcionarioModel.setDepartamento(departamentoModel);
        }

        funcionarioRepository.save(funcionarioModel);

        usuarioModel.setCpf(funcionarioRecordDto.usuario().cpf());
        usuarioModel.setSenha(passwordEncoder.encode(funcionarioRecordDto.usuario().senha()));
        usuarioModel.setTipoDeAcessoEnum(funcionarioRecordDto.usuario().tipoDeAcesso());
        usuarioModel.setFuncionario(funcionarioModel);
        usuarioRepository.save(usuarioModel);

        funcionarioModel.setUsuario(usuarioModel);
        funcionarioRepository.save(funcionarioModel);
    }

    public void deleteById(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    public List<FuncionarioModel> findByDepartamentoIsNull() {
        return funcionarioRepository.findByDepartamentoIsNull();
    }

    public FuncionarioModel updateFuncionario(UUID id, FuncionarioRecordDto funcionarioRecordDto) {
        FuncionarioModel funcionarioModel = funcionarioRepository.getReferenceById(id);
        funcionarioModel.setNome(funcionarioRecordDto.nome());
        funcionarioModel.setNascimento(funcionarioRecordDto.nascimento());
        funcionarioModel.setContratacao(funcionarioRecordDto.contratacao());
        funcionarioModel.setEmail(funcionarioRecordDto.email());

        if (funcionarioModel.getTurno() != TurnoEnum.valueOf(funcionarioRecordDto.turno().toUpperCase())) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getTurno().toString());
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }
        if (funcionarioModel.getModalidade() != ModalidadeEnum.valueOf(funcionarioRecordDto.modalidade().toUpperCase())) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getModalidade().toString());
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }

        if (funcionarioModel.getCargo() == null || !funcionarioModel.getCargo().getId().equals(funcionarioRecordDto.cargo())) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getCargo() != null ? funcionarioModel.getCargo().getNome() : "null");
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }

        if (funcionarioModel.getDepartamento() == null || !funcionarioModel.getDepartamento().getId().equals(funcionarioRecordDto.departamento())) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getDepartamento() != null ? funcionarioModel.getDepartamento().getNomeDepartamento() : "null");
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }

        funcionarioModel.setTurno(TurnoEnum.valueOf(funcionarioRecordDto.turno().toUpperCase()));
        funcionarioModel.setModalidade(ModalidadeEnum.valueOf(funcionarioRecordDto.modalidade().toUpperCase()));

        funcionarioModel.setCargo(null);
        if (funcionarioRecordDto.cargo() != null) {
            CargoModel cargoModel = cargoRepository.getReferenceById(funcionarioRecordDto.cargo());
            funcionarioModel.setCargo(cargoModel);
        }

        funcionarioModel.setDepartamento(null);
        if (funcionarioRecordDto.departamento() != null) {
            DepartamentoModel departamentoModel = departamentoRepository.getReferenceById(funcionarioRecordDto.departamento());
            funcionarioModel.setDepartamento(departamentoModel);
        }

        funcionarioRepository.save(funcionarioModel);
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(funcionarioModel.getUsuario().getId());
        usuarioModel.setCpf(funcionarioRecordDto.usuario().cpf());
        usuarioModel.setSenha(passwordEncoder.encode(funcionarioRecordDto.usuario().senha()));
        usuarioModel.setTipoDeAcessoEnum(funcionarioRecordDto.usuario().tipoDeAcesso());
        usuarioModel.setFuncionario(funcionarioModel);
        usuarioRepository.save(usuarioModel);

        funcionarioModel.setUsuario(usuarioModel);
        funcionarioRepository.save(funcionarioModel);
        return funcionarioModel;
    }

}