package br.com.turnover.config;

import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TipoDeAcessoEnum;
import br.com.turnover.enums.TurnoEnum;
import br.com.turnover.models.FuncionarioModel;
import br.com.turnover.models.UsuarioModel;
import br.com.turnover.repositories.CargoRepository;
import br.com.turnover.repositories.DepartamentoRepository;
import br.com.turnover.repositories.FuncionarioRepository;
import br.com.turnover.repositories.UsuarioRepository;
import br.com.turnover.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Date;

@Configuration
public class StartupConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            String ceoCpf = "001.236.950-07"; // Defina o CPF do usuário CEO
            if (!usuarioRepository.existsByCpf(ceoCpf)) {
                // Criação do Cargo
//                CargoModel cargoCeo = new CargoModel();
//                cargoCeo.setNome("CEO");
//                cargoCeo.setDescricao("Chief Executive Officer");
//                cargoCeo.setCargaHoraria(40);
//                cargoCeo.setSalario(10000.0);
//                cargoRepository.save(cargoCeo);

                // Criação do Departamento
//                DepartamentoModel departamentoCeo = new DepartamentoModel();
//                departamentoCeo.setNomeDepartamento("Administração");
//                departamentoCeo.setLocalizacao("Sede");
//                departamentoCeo.setDescricao("Administração da Empresa");
//                departamentoRepository.save(departamentoCeo);

                // Criação do Usuário
                UsuarioModel usuarioCeo = new UsuarioModel();
                usuarioCeo.setCpf(ceoCpf);
                usuarioCeo.setSenha(new BCryptPasswordEncoder().encode("1234")); // Defina a senha padrão para o usuário CEO
                usuarioCeo.setTipoDeAcessoEnum(TipoDeAcessoEnum.CEO);
                usuarioRepository.save(usuarioCeo);

                // Criação do Funcionário
                FuncionarioModel funcionarioCeo = new FuncionarioModel();
                funcionarioCeo.setNome("Thiago Michel Ariça");
                funcionarioCeo.setNascimento(new Date(90, 0, 1)); // Definindo a data de nascimento
                funcionarioCeo.setContratacao(LocalDate.of(2020, 1, 1));
                funcionarioCeo.setEmail("thiago.michel@example.com");
                funcionarioCeo.setTurno(TurnoEnum.TURNO_A);
                funcionarioCeo.setModalidade(ModalidadeEnum.PRESENCIAL);
//                funcionarioCeo.setCargo(cargoCeo);
//                funcionarioCeo.setDepartamento(departamentoCeo);
                funcionarioCeo.setUsuario(usuarioCeo);
                funcionarioRepository.save(funcionarioCeo);

                // Associar o Funcionário ao Usuário
                usuarioCeo.setFuncionario(funcionarioCeo);
                usuarioRepository.save(usuarioCeo);

                System.out.println("Usuário e Funcionário CEO criados com sucesso.");
            }
        };
    }
}
