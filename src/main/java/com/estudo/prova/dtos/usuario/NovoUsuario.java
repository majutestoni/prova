package com.estudo.prova.dtos.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Setter
@Getter
public class NovoUsuario {
    private String nome;
    @Length(min = 14, max = 14, message = "O telefone deve conter 14 caracters, ex: 47 9 9999-9999")
    private String telefone;
}


