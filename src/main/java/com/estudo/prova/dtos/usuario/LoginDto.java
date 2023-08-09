package com.estudo.prova.dtos.usuario;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
    private String nome;
    private String senha;
}
