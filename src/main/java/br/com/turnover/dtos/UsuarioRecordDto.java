package br.com.turnover.dtos;

import br.com.turnover.enums.TipoDeAcessoEnum;
import br.com.turnover.models.FuncionarioModel;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioRecordDto(
        @CPF
        String cpf,
        String senha,
        TipoDeAcessoEnum tipoDeAcessoEnum,
        FuncionarioModel funcionario
) {
}
