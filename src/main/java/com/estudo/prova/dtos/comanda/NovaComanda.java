package com.estudo.prova.dtos.comanda;

import com.estudo.prova.entities.Produto;
import lombok.*;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NovaComanda {

    @NotNull(message = "Obrigatorio informar o usuario!")
    private Long idUsuario;
    @NotNull(message = "Obrigatorio informar ao menos um produto!")
    @Size(min = 1, message = "Obrigatorio informar ao menos um produto!")
    private Set<Produto> produtos = new HashSet<>();
}
