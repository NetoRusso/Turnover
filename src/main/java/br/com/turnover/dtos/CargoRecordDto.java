package br.com.turnover.dtos;

public record CargoRecordDto(
        String nome,
        String descricao,
        int cargaHoraria,
        double salario
) {
}
