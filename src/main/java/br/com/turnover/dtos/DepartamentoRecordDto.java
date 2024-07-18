package br.com.turnover.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record DepartamentoRecordDto() {

    private static String nomeDepartamento;
    @Column(nullable = false, unique = true)
    private static String localizacao;
    @Column(nullable = false)
    private static String senhaDepartamento;
    @Column(nullable = false)
    private static String descricao;
    @Column(nullable = false)
    private static int lotacaoMax;
    @Column(nullable = false)
    private static int turnoA;
    @Column(nullable = false)
    private static int turnoB;
    @Column(nullable = false)
    private static int turnoC;

}
