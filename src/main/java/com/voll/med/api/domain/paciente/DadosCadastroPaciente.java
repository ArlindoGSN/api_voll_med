package com.voll.med.api.domain.paciente;

public record DadosCadastroPaciente(
        String nome,
        String email,
        String telefone,
        String cpf,
        com.voll.med.api.domain.Endereco.DadosEndereco endereco
) {
}
