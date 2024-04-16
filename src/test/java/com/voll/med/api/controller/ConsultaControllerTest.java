package com.voll.med.api.controller;

import com.voll.med.api.domain.consulta.AgendaDeConsultas;
import com.voll.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.voll.med.api.domain.consulta.DadosDetalhamentoConsulta;
import com.voll.med.api.domain.consulta.StatusConsulta;
import com.voll.med.api.domain.medico.Especialidade;
import lombok.With;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;
    @MockBean
    private AgendaDeConsultas agenda;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando dados forem invalidos")
    void agendar() throws Exception {
        var response = mockMvc.perform(post("/consultas")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando dados forem validos")
    void agendarComSucesso() throws Exception {


        var data = LocalDateTime.now().plusDays(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var status = StatusConsulta.AGENDADA;

        when(agenda.agendar(any())).thenReturn(new DadosDetalhamentoConsulta(null, 1L, 1L, data, status));


        var response = mockMvc.perform(post("/consultas").contentType(MediaType.APPLICATION_JSON)
                .content(dadosAgendamentoConsultaJson.write(new DadosAgendamentoConsulta(1L, 1L,data, especialidade)).getJson())
        ).andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamentoConsultaJson.write(new DadosDetalhamentoConsulta( null ,1L, 1L,data, status )).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}