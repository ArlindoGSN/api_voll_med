package com.voll.med.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosNovoUsuario(
    @NotBlank
    String login,
    @NotBlank
    String senha) {
}
