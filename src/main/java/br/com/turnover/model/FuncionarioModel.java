package br.com.turnover.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    private LocalDate nascimento;

    private LocalDate contratacao;

    private String email;

    private int horasTrabalhadasDia;

    private boolean pontoDiarioEntrada;

    private boolean pontroDiarioSaida;

    private double cargoSalario;

    @ManyToOne
    private CargoModel cargo;

    @ManyToOne
    private DepartamentoModel departamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getHorasTrabalhadasDia() {
        return horasTrabalhadasDia;
    }

    public void setHorasTrabalhadasDia(int horasTrabalhadasDia) {
        this.horasTrabalhadasDia = horasTrabalhadasDia;
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

    public double getCargoSalario() {
        return cargoSalario;
    }

    public void setCargoSalario(double cargoSalario) {
        this.cargoSalario = cargoSalario;
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
