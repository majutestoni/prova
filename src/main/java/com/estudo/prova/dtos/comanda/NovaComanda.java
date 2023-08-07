package com.estudo.prova.dtos.comanda;

import com.estudo.prova.entities.Produto;
import com.estudo.prova.exception.ExceptionHandlerAdvice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NovaComanda {

    @NotNull(message = "Obrigatorio informar o usuario!")
    private Long idUsuario;
    @NotNull(message = "Obrigatorio informar ao menos um produto!")
    @Size(min = 1, message = "Obrigatorio informar ao menos um produto!")
    private Set<Produto> produtos = new HashSet<>();
}
