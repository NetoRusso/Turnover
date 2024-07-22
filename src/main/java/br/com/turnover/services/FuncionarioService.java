package br.com.turnover.services;

import br.com.turnover.dtos.CargoRecordDto;
import br.com.turnover.dtos.DepartamentoRecordDto;
import br.com.turnover.dtos.FuncionarioRecordDto;
import br.com.turnover.dtos.UsuarioRecordDto;
import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TipoDeAcessoEnum;
import br.com.turnover.enums.TurnoEnum;
import br.com.turnover.models.CargoModel;
import br.com.turnover.models.DepartamentoModel;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.models.UsuarioModel;
import br.com.turnover.repositories.CargoRepository;
import br.com.turnover.repositories.DepartamentoRepository;
import br.com.turnover.repositories.FuncionarioRepository;
import br.com.turnover.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CargoRepository cargoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository, UsuarioRepository usuarioRepository, DepartamentoRepository departamentoRepository, CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.departamentoRepository = departamentoRepository;
        this.cargoRepository = cargoRepository;
    }

//    public List<FuncionarioModel> getAllFuncionariosWithDetails() {
//        return funcionarioRepository.findAllWithDetails();
//    }


    public List<FuncionarioModel> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<FuncionarioModel> findById(UUID id) {
        return funcionarioRepository.findById(id);
    }


    public Optional<FuncionarioModel> findByUsuarioCpf(String cpf) {
        return funcionarioRepository.findByUsuarioCpf(cpf);
    }

    public List<FuncionarioModel> findAllByDepartamentoId(UUID departamentoId) {
        return funcionarioRepository.findAllByDepartamentoId(departamentoId);

    }

    /*public Optional<FuncionarioRecordDto> findFuncionarioDetailsById(UUID id) {
        Optional<FuncionarioModel> funcionarioOptional = funcionarioRepository.findById(id);
        if (funcionarioOptional.isEmpty()) {
            return Optional.empty();
        }

        FuncionarioModel funcionario = funcionarioOptional.get();
        UsuarioModel usuario = funcionario.getUsuario();
        CargoModel cargo = funcionario.getCargo();
        DepartamentoModel departamento = funcionario.getDepartamento();

        FuncionarioRecordDto funcionarioDto = new FuncionarioRecordDto(
                funcionario.getNome(),
                funcionario.getNascimento(),
                funcionario.getContratacao(),
                funcionario.getEmail(),
                funcionario.getTurno().name(),
                funcionario.getModalidade().name(),
                usuario.getCpf(),
                usuario.getSenha(),
                usuario.getTipoDeAcessoEnum().name(),
                new UsuarioRecordDto(usuario.getCpf(), usuario.getSenha(), usuario.getTipoDeAcessoEnum(), null),
                new CargoRecordDto(cargo.getNome(), cargo.getDescricao(), cargo.getCargaHoraria(), cargo.getSalario()),
                new DepartamentoRecordDto(departamento.getNomeDepartamento(), departamento.getLocalizacao(), departamento.getDescricao())
        );

        return Optional.of(funcionarioDto);
    }*/

    public Optional<FuncionarioRecordDto> findFuncionarioDetailsById(UUID id) {
        Optional<FuncionarioModel> funcionarioOptional = funcionarioRepository.findById(id);
        if (funcionarioOptional.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(convertToDto(funcionarioOptional.get()));
    }

    public List<FuncionarioRecordDto> findAllFuncionarioDetails() {
        List<FuncionarioModel> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private FuncionarioRecordDto convertToDto(FuncionarioModel funcionario) {
        UsuarioModel usuario = funcionario.getUsuario();
        CargoModel cargo = funcionario.getCargo();
        DepartamentoModel departamento = funcionario.getDepartamento();

        return new FuncionarioRecordDto(
                funcionario.getNome(),
                funcionario.getNascimento(),
                funcionario.getContratacao(),
                funcionario.getEmail(),
                funcionario.getTurno() != null ? funcionario.getTurno().name() : null,
                funcionario.getModalidade() != null ? funcionario.getModalidade().name() : null,
                usuario != null ? usuario.getCpf() : null,
                usuario != null ? usuario.getSenha() : null,
                usuario != null ? usuario.getTipoDeAcessoEnum().name() : null,
                usuario != null ? new UsuarioRecordDto(usuario.getCpf(), usuario.getSenha(), usuario.getTipoDeAcessoEnum(), null) : null,
                cargo != null ? new CargoRecordDto(cargo.getNome(), cargo.getDescricao(), cargo.getCargaHoraria(), cargo.getSalario()) : null,
                departamento != null ? new DepartamentoRecordDto(departamento.getNomeDepartamento(), departamento.getLocalizacao(), departamento.getDescricao()) : null
        );
    }


    public void saveFuncionario(FuncionarioRecordDto funcionarioRecordDto) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        UsuarioModel usuarioModel = new UsuarioModel();
        funcionarioModel.setNome(funcionarioRecordDto.nome());
        funcionarioModel.setNascimento(funcionarioRecordDto.nascimento());
        //funcionarioModel.setContratacao(funcionarioRecordDto.contratacao());
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