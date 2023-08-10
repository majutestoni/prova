package com.estudo.prova.dtos.usuario;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data // faz getter, setter, toString e mais uns
public class LoginDto {
    @NotNull(message = "É obrigatorio informar seu nome ao realizar login!")
    @NotBlank(message = "É obrigatorio informar seu nome ao realizar login!")
    private String nome;
    @NotNull(message = "É obrigatorio informar sua senha ao realizar login!")
    @NotBlank(message = "É obrigatorio informar sua senha ao realizar login!")
    private String senha;
}
