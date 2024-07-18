package br.com.turnover.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Date;

public record FuncionarioRecordDto(
         String nome,
         String cpf,
         String senha,
         Date nascimento,
         Date contratacao,
         String email,
         boolean remotoOuHibrido,
         boolean pontoDiarioEntrada,
         boolean pontroDiarioSaida
) {
}
