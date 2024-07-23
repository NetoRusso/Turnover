package br.com.turnover.config;

import br.com.turnover.dtos.UsuarioRecordDto;
import br.com.turnover.enums.TipoDeAcessoEnum;
import br.com.turnover.repositories.UsuarioRepository;
import br.com.turnover.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public CommandLineRunner init() {
        return args -> {
            String ceoCpf = "11772536660"; // Defina o CPF do usuário CEO
            if (!usuarioRepository.existsByCpf(ceoCpf)) {
                UsuarioRecordDto usuarioCeo = new UsuarioRecordDto(
                        ceoCpf,
                        "1234", // Defina a senha padrão para o usuário CEO
                        TipoDeAcessoEnum.CEO,
                        null // Inicialmente sem FuncionarioModel
                );
                usuarioService.save(usuarioCeo);
                System.out.println("Usuário CEO criado com sucesso.");
            }
        };
    }
}