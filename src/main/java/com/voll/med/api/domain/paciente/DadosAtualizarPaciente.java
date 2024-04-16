package com.voll.med.api.domain.paciente;

import com.voll.med.api.domain.Endereco.Endereco;

public record DadosAtualizarPaciente(
        Long id,
        String nome,
        String telefone,
        Endereco endereco
) {
}
