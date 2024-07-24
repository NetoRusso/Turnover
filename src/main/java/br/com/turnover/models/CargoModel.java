package br.com.turnover.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CARGO")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CargoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;

    private int cargaHoraria = 8;

    private double salario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(/*mappedBy = "cargo",*/ fetch = FetchType.LAZY)
    private Set<FuncionarioModel> funcionarios = new HashSet<>();
}
