package com.voll.med.api.domain.medico;

import com.voll.med.api.domain.Endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedicos(
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        DadosEndereco endereco) {

}
