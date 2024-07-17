package br.com.turnover.model;

import jakarta.persistence.*;

import java.io.Serializable;
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


}
