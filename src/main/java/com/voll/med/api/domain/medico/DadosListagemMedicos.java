package com.voll.med.api.domain.medico;

public record DadosListagemMedicos(
        Long id,
        String nome,
        String crm) {

    public DadosListagemMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getCrm());
    }
}
