package com.estudo.prova.dtos.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Setter
@Getter
public class NovoUsuario {
    @NotNull(message = "Obrigatorio informar o nome!")
    @NotBlank(message = "Obrigatorio informar o nome!")
    private String nome;
    @NotBlank(message = "Obrigatorio informar o telefone!")
    @Size(min = 11, max = 14, message = "O tamanho deve ser de 11 a 14 caracteres")
    private String telefone;
    @NotBlank(message = "Obrigatorio informar o telefone!")
    private String senha;
}


