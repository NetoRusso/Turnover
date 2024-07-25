package br.com.turnover.dtos;

import java.util.UUID;

public record AlocacaoRecordDto(
        String alteracao,
        UUID funcionarioId
) {
}
