package com.voll.med.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAutencicacao(
    @NotBlank
    String login,
    @NotBlank
    String senha
) {
}
