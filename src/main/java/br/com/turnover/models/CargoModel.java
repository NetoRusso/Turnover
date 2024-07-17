package br.com.turnover.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCBO() {
        return CBO;
    }

    public void setCBO(String CBO) {
        this.CBO = CBO;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getPisoSalarial() {
        return pisoSalarial;
    }

    public void setPisoSalarial(double pisoSalarial) {
        this.pisoSalarial = pisoSalarial;
    }

    public double getTetoSalarial() {
        return tetoSalarial;
    }

    public void setTetoSalarial(double tetoSalarial) {
        this.tetoSalarial = tetoSalarial;
    }

    public Set<FuncionarioModel> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Set<FuncionarioModel> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
