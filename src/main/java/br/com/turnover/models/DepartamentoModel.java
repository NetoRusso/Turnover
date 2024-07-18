package br.com.turnover.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getSenhaDepartamento() {
        return senhaDepartamento;
    }

    public void setSenhaDepartamento(String senhaDepartamento) {
        this.senhaDepartamento = senhaDepartamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLotacaoMax() {
        return lotacaoMax;
    }

    public void setLotacaoMax(int lotacaoMax) {
        this.lotacaoMax = lotacaoMax;
    }

    public int getTurnoA() {
        return turnoA;
    }

    public void setTurnoA(int turnoA) {
        this.turnoA = turnoA;
    }

    public int getTurnoB() {
        return turnoB;
    }

    public void setTurnoB(int turnoB) {
        this.turnoB = turnoB;
    }

    public int getTurnoC() {
        return turnoC;
    }

    public void setTurnoC(int turnoC) {
        this.turnoC = turnoC;
    }

    public Set<FuncionarioModel> getFuncionario() {
        Set<FuncionarioModel> funcionario = Set.of();
        return funcionario;
    }

}
