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


}
