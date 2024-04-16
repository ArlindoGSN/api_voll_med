package com.voll.med.api.domain.Endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String uf;
    private String numero;
    private String complemento;
    private String cidade;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.uf = endereco.uf();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
    }

    public void atualizarInformacoes(Endereco endereco) {
        if (endereco.logradouro != null) {
            this.logradouro = endereco.logradouro;
        }

        if (endereco.bairro != null) {
            this.bairro = endereco.bairro;
        }
        if (endereco.cep != null) {
            this.cep = endereco.cep;
        }
        if (endereco.uf != null) {
            this.uf = endereco.uf;
        }
        if (endereco.cidade != null) {
            this.cidade = endereco.cidade;
        }
        if (endereco.complemento != null) {
            this.complemento = endereco.complemento;
        }
        if (endereco.numero != null) {
            this.numero = endereco.numero;
        }

    }
}
