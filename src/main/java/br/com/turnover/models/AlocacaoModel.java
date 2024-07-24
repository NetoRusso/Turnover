package br.com.turnover.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_ALOCACAO")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class AlocacaoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String alteracao;

    private LocalDateTime dataAtualizacao;

    @ManyToOne//(mappedBy = "usuario")
    @JoinColumn(name = "funcionario_id")
    //@JsonIgnoreProperties("funcionario")
    //@JsonBackReference
    @JsonIdentityReference(alwaysAsId = true)
    private FuncionarioModel funcionario;

}
