package br.com.turnover.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_CARGO")
public class CargoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String CBO;

    private int cargaHoraria;

    private double salario;

    private double pisoSalarial;

    private double tetoSalarial;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
    private Set<FuncionarioModel> funcionarios = new HashSet<>();

    public Set<FuncionarioModel> getFuncionario() {
        Set<FuncionarioModel> funcionario = Set.of();
        return funcionario;
    }

}
