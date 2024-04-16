package com.voll.med.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voll.med.api.domain.medico.DadosCadastroMedicos;
import com.voll.med.api.domain.medico.DadosDetalhamentoMedico;
import com.voll.med.api.domain.medico.DadosListagemMedicos;
import com.voll.med.api.domain.medico.Medico;
import com.voll.med.api.domain.medico.MedicoRepository;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	MedicoRepository repository;

	public MedicoController(MedicoRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<DadosDetalhamentoMedico> CadastrarMedicos(@RequestBody DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder)  {

		Medico medico = new Medico(dados);
		repository.save(medico);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}


	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> DetalharMedico(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedicos>> ListAllMedicos(Pageable pageable) {
		return ResponseEntity.ok().body(repository.findAll(pageable).map(DadosListagemMedicos::new));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> atualizarMedico(@RequestBody DadosDetalhamentoMedico dados) {

		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		repository.save(medico);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletarMedico(@PathVariable Long id) {
		repository.findById(id).ifPresent(Medico::inativar);
		return ResponseEntity.noContent().build();
	}

}
