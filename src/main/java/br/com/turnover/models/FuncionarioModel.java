package br.com.turnover.models;

import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TurnoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_FUNCIONARIO")
public class FuncionarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    private Date nascimento;

    private Date contratacao;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private TurnoEnum turno;

    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidade;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private CargoModel cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private DepartamentoModel departamento;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @OneToMany(mappedBy = "funcionario")
    private Set<AlocacaoModel> historicoAlocacao;


//   private historico_locacao <Alocacao>;
}
