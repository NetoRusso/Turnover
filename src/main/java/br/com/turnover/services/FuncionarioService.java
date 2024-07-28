package br.com.turnover.services;

import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TurnoEnum;
import br.com.turnover.models.*;
import br.com.turnover.repositories.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    private static final Logger log = LoggerFactory.getLogger(FuncionarioService.class);
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

        atualizarCargoDepartamentoQuandoDiferenteNulo(funcionarioRecordDto, funcionarioModel);

        funcionarioRepository.save(funcionarioModel);

        usuarioModel.setCpf(funcionarioRecordDto.usuario().cpf());
        usuarioModel.setSenha(passwordEncoder.encode(funcionarioRecordDto.usuario().senha()));
        usuarioModel.setTipoDeAcessoEnum(funcionarioRecordDto.usuario().tipoDeAcesso());
        usuarioModel.setFuncionario(funcionarioModel);
        usuarioRepository.save(usuarioModel);

        funcionarioModel.setUsuario(usuarioModel);
        funcionarioRepository.save(funcionarioModel);
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }

        FuncionarioModel funcionario = funcionarioRepository.findById(id).get();
        funcionario.setCargo(null);
        funcionario.setDepartamento(null);

        usuarioRepository.deleteById(funcionario.getUsuario().getId());
//        funcionarioRepository.deletarFuncionario(funcionario.getId());
    }

    public List<FuncionarioModel> findByDepartamentoIsNull() {
        return funcionarioRepository.findByDepartamentoIsNull();
    }

    public FuncionarioModel updateFuncionario(UUID id, FuncionarioRecordDto funcionarioRecordDto) {
        FuncionarioModel funcionarioModel = funcionarioRepository.getReferenceById(id);

        // Atualiza apenas os campos que foram enviados na requisição
        if (funcionarioRecordDto.nome() != null) {
            funcionarioModel.setNome(funcionarioRecordDto.nome());
        }
        if (funcionarioRecordDto.nascimento() != null) {
            funcionarioModel.setNascimento(funcionarioRecordDto.nascimento());
        }
        if (funcionarioRecordDto.contratacao() != null) {
            funcionarioModel.setContratacao(funcionarioRecordDto.contratacao());
        }
        if (funcionarioRecordDto.email() != null) {
            funcionarioModel.setEmail(funcionarioRecordDto.email());
        }

        salvarHistoricoAlocacao(funcionarioRecordDto, funcionarioModel);

        if (funcionarioRecordDto.turno() != null) {
            funcionarioModel.setTurno(TurnoEnum.valueOf(funcionarioRecordDto.turno().toUpperCase()));
        }
        if (funcionarioRecordDto.modalidade() != null) {
            funcionarioModel.setModalidade(ModalidadeEnum.valueOf(funcionarioRecordDto.modalidade().toUpperCase()));
        }

        atualizarCargoDepartamentoQuandoDiferenteNulo(funcionarioRecordDto, funcionarioModel);

        funcionarioRepository.save(funcionarioModel);
        UsuarioModel usuarioModel = atualizarUsuario(funcionarioRecordDto, funcionarioModel);

        funcionarioModel.setUsuario(usuarioModel);
        funcionarioRepository.save(funcionarioModel);
        return funcionarioModel;
    }

    private UsuarioModel atualizarUsuario(FuncionarioRecordDto funcionarioRecordDto, FuncionarioModel funcionarioModel) {
        UsuarioModel usuarioModel = usuarioRepository.getReferenceById(funcionarioModel.getUsuario().getId());
        if (funcionarioRecordDto.usuario().cpf() != null) {
            usuarioModel.setCpf(funcionarioRecordDto.usuario().cpf());
        }
        if (funcionarioRecordDto.usuario().senha() != null) {
            usuarioModel.setSenha(passwordEncoder.encode(funcionarioRecordDto.usuario().senha()));
        }
        if (funcionarioRecordDto.usuario().tipoDeAcesso() != null) {
            usuarioModel.setTipoDeAcessoEnum(funcionarioRecordDto.usuario().tipoDeAcesso());
        }
        usuarioModel.setFuncionario(funcionarioModel);
        usuarioRepository.save(usuarioModel);
        return usuarioModel;
    }

    private void atualizarCargoDepartamentoQuandoDiferenteNulo(FuncionarioRecordDto funcionarioRecordDto, FuncionarioModel funcionarioModel) {
        if (funcionarioRecordDto.cargo() != null) {
            CargoModel cargoModel = cargoRepository.getReferenceById(funcionarioRecordDto.cargo());
            funcionarioModel.setCargo(cargoModel);
        }
        if (funcionarioRecordDto.departamento() != null) {
            DepartamentoModel departamentoModel = departamentoRepository.getReferenceById(funcionarioRecordDto.departamento());
            funcionarioModel.setDepartamento(departamentoModel);
        }
    }

    private void salvarHistoricoAlocacao(FuncionarioRecordDto funcionarioRecordDto, FuncionarioModel funcionarioModel) {
        if (funcionarioRecordDto.turno() != null && funcionarioModel.getTurno() != TurnoEnum.valueOf(funcionarioRecordDto.turno().toUpperCase())) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getTurno().toString());
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }
        if (funcionarioRecordDto.modalidade() != null && funcionarioModel.getModalidade() != ModalidadeEnum.valueOf(funcionarioRecordDto.modalidade().toUpperCase())) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getModalidade().toString());
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }
        if (funcionarioRecordDto.cargo() != null && (funcionarioModel.getCargo() == null || !funcionarioModel.getCargo().getId().equals(funcionarioRecordDto.cargo()))) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getCargo() != null ? funcionarioModel.getCargo().getNome() : "null");
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }
        if (funcionarioRecordDto.departamento() != null && (funcionarioModel.getDepartamento() == null || !funcionarioModel.getDepartamento().getId().equals(funcionarioRecordDto.departamento()))) {
            AlocacaoModel alocacaoModel = new AlocacaoModel();
            alocacaoModel.setAlteracao(funcionarioModel.getDepartamento() != null ? funcionarioModel.getDepartamento().getNomeDepartamento() : "null");
            alocacaoModel.setFuncionario(funcionarioModel);
            alocacaoModel.setDataAtualizacao(LocalDateTime.now());
            alocacaoRepository.save(alocacaoModel);
        }
    }

}