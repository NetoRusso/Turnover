package br.com.turnover.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record FuncionarioRecordDto(
         String nome,
         String cpf,
         String senha,
         LocalDate nascimento,
         LocalDate contratacao,
         String email,
         boolean remotoOuHibrido,
         boolean pontoDiarioEntrada,
         boolean pontroDiarioSaida) {
}
