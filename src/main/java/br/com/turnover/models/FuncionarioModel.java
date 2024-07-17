package br.com.turnover.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class FuncionarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    private LocalDate nascimento;

    private LocalDate contratacao;

    private String email;

    private boolean remotoOuHibrido;

    private boolean pontoDiarioEntrada;

    private boolean pontroDiarioSaida;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private CargoModel cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private DepartamentoModel departamento;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public LocalDate getContratacao() {
        return contratacao;
    }

    public void setContratacao(LocalDate contratacao) {
        this.contratacao = contratacao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRemotoOuHibrido() {
        return remotoOuHibrido;
    }

    public void setRemotoOuHibrido(boolean remotoOuHibrido) {
        this.remotoOuHibrido = remotoOuHibrido;
    }

    public boolean isPontoDiarioEntrada() {
        return pontoDiarioEntrada;
    }

    public void setPontoDiarioEntrada(boolean pontoDiarioEntrada) {
        this.pontoDiarioEntrada = pontoDiarioEntrada;
    }

    public boolean isPontroDiarioSaida() {
        return pontroDiarioSaida;
    }

    public void setPontroDiarioSaida(boolean pontroDiarioSaida) {
        this.pontroDiarioSaida = pontroDiarioSaida;
    }

    public CargoModel getCargo() {
        return cargo;
    }

    public void setCargo(CargoModel cargo) {
        this.cargo = cargo;
    }

    public DepartamentoModel getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoModel departamento) {
        this.departamento = departamento;
    }
}
