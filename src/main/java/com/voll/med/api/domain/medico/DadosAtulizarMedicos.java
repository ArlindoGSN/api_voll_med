package com.voll.med.api.domain.medico;

import com.voll.med.api.domain.Endereco.Endereco;

public record DadosAtulizarMedicos(
    Long id,
    String nome,
    String email,
    Endereco endereco
) {
}
