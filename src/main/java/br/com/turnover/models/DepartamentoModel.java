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
@Table(name = "TB_DEPARTAMENTO")
public class DepartamentoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nomeDepartamento;

    private String localizacao;

    private String senhaDepartamento;

    private String descricao;

    private int lotacaoMax;

    private int turnoA;

    private int turnoB;

    private int turnoC;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private Set<FuncionarioModel> funcionarios = new HashSet<>();
}
