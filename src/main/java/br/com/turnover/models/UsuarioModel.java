package br.com.turnover.models;

import br.com.turnover.enums.TipoDeAcesso;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    private String senha = "1234";

    @Enumerated(EnumType.STRING)
    private TipoDeAcesso tipoDeAcesso;

    @OneToOne
    private FuncionarioModel funcionario;
}
