package com.estudo.prova.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;



    // ex de numero: 47 9 9999-9999
    @Column(name = "telefone_usuario", nullable = false, length = 14)
    private String telefoneUsuario;

    public Usuario(String nome, String senha, String telefone) {
        this.nome = nome;
        this.senha = senha;
        this.telefoneUsuario = telefone;
    }
}
