package br.com.turnover.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_FUNCIONARIO")
public class FuncionarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    private Date nascimento;

    private Date contratacao;

    @Email
    private String email;

    private boolean remotoOuHibrido;

    private boolean pontoDiarioEntrada;
//    @Column(nullable = true)
    private boolean pontoDiarioSaida;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private CargoModel cargo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private DepartamentoModel departamento;

}
