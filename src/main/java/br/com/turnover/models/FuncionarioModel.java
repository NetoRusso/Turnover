package br.com.turnover.models;

import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TurnoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
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

    private LocalDate contratacao;

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

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioModel usuario;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "funcionario_id")
    private Set<AlocacaoModel> historicoAlocacao;
}
