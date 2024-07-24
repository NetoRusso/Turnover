package br.com.turnover.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String descricao;

    private int cargaHoraria = 8;

    private double salario;

    @JsonBackReference
    @OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
    private Set<FuncionarioModel> funcionarios = new HashSet<>();
}
