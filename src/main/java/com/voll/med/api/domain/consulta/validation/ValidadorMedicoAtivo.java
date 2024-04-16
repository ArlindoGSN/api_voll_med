package com.voll.med.api.domain.consulta.validation;

import com.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.voll.med.api.domain.medico.MedicoRepository;
import com.voll.med.api.infra.exception.ValidacaoException;
import org.hibernate.annotations.Cache;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsultas {
    private MedicoRepository medicoRepository;

    public ValidadorMedicoAtivo(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public void validar(DadosAgendamentoConsulta dados) {
         if (dados.idMedico() == null) {
            return;
        }

        var medicoAtivo =medicoRepository.findAtivoById(dados.idMedico());

        if (!medicoAtivo) {
            throw new ValidacaoException("Medico inativo");

        }
    }
}
