package br.com.turnover.dtos;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record FuncionarioRecordDto(
        String nome,
        Date nascimento,
        LocalDate contratacao,
        String email,
        String turno,
        String modalidade,
        UsuarioRecordDto usuario,
        UUID cargo,
        UUID departamento
) {
}
