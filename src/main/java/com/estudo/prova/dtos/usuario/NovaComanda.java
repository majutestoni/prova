package com.estudo.prova.dtos.usuario;

import com.estudo.prova.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NovaComanda {
    private Long idUsuario;
    private Set<Produto> produtos = new HashSet<>();
}
