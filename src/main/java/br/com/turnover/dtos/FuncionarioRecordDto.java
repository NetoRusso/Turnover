package br.com.turnover.dtos;

import java.util.Date;

public record FuncionarioRecordDto(
        String nome,
        Date nascimento,
        Date contratacao,
        String email,
        String turno,
        String modalidade,
        String cpf,
        String senha,
        String tipoDeAcesso,
        UsuarioRecordDto usuario,
        CargoRecordDto cargo,
        DepartamentoRecordDto departamento
) {
}
