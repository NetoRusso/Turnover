package br.com.turnover.models;

import br.com.turnover.enums.ModalidadeEnum;
import br.com.turnover.enums.TurnoEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
    @JsonIdentityReference(alwaysAsId = true)
    private CargoModel cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @JsonIdentityReference(alwaysAsId = true)
    private DepartamentoModel departamento;

    //@JsonIgnoreProperties("funcionario")
    //@JsonBackReference
    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonIdentityReference(alwaysAsId = true)
    private UsuarioModel usuario;

    //@JsonManagedReference
    @OneToMany(/*mappedBy = "funcionario",*/ fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    private Set<AlocacaoModel> historicoAlocacao;

//   private historico_locacao <Alocacao>;
}
