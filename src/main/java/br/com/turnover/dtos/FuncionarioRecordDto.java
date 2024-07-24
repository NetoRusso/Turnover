package br.com.turnover.dtos;

import java.util.Date;
import java.util.UUID;

public record FuncionarioRecordDto(
        String nome,
        Date nascimento,
        java.time.LocalDate contratacao,
        String email,
        String turno,
        String modalidade,
        UsuarioRecordDto usuario,
        UUID cargo,
        UUID departamento
) {
}
