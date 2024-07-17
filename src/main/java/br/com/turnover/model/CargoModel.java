package br.com.turnover.model;

import jakarta.persistence.*;

import java.io.Serializable;
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

}
