package com.voll.med.api.domain.consulta;

import com.voll.med.api.domain.consulta.validation.ValidadorAgendamentoConsultas;
import com.voll.med.api.domain.medico.Medico;
import com.voll.med.api.domain.medico.MedicoRepository;
import com.voll.med.api.domain.paciente.PacienteRepository;
import com.voll.med.api.infra.exception.ValidacaoException;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    private ConsultaRepository consultaRepository;

    private MedicoRepository medicoRepository;

    private PacienteRepository pacienteRepository;

    private List<ValidadorAgendamentoConsultas> validadores;

    public AgendaDeConsultas(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, PacienteRepository pacienteRepository, List<ValidadorAgendamentoConsultas> validadores) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.consultaRepository = consultaRepository;
        this.validadores = validadores;
    }


    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("ID Paciente não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("ID Medico não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        var consulta = new Consulta(medico , paciente , dados.data());

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }



    public void cancelar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.id());

        if (!consulta.podeSerCancelada()) {
            throw new ValidacaoException("Consulta não pode ser cancelada");
        }
        consulta.cancelar(dados.motivo());

        consultaRepository.save(consulta);
    }

    public void confirmar(Long id) {
        if (id == null)
            throw new ValidacaoException("Id da consulta é obrigatoria");
        if (!consultaRepository.existsById(id))
            throw new ValidacaoException("Id da consulta inválido");

        var consulta = consultaRepository.findById(id).get();
        consulta.confirmar();
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Id medico ou especialidade é obrigatorio");
        }
        return medicoRepository.findMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
