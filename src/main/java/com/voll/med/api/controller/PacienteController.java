package com.voll.med.api.controller;

import com.voll.med.api.domain.paciente.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    PacienteRepository repository;

    public PacienteController(PacienteRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPaciente> cadastroPaciente(@RequestBody DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder){

        Paciente paciente = new Paciente(dados);
        repository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));


    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPaciente> detalharPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoPaciente>> listarPacientes(Pageable pageable){
        return ResponseEntity.ok().body(repository.findAll(pageable).map(DadosDetalhamentoPaciente::new));
    }
    @PutMapping
    public ResponseEntity<DadosDetalhamentoPaciente> atualizarPaciente(@RequestBody DadosAtualizarPaciente dados){

        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.atualizarPaciente(dados);
        repository.save(paciente);
        return ResponseEntity.ok().body(new DadosDetalhamentoPaciente(paciente));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id){
        repository.findById(id).ifPresent(Paciente::inativar);
        return ResponseEntity.noContent().build();
    }
}
