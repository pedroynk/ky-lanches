package com.kylanches.paineldecontrole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "ativo")
    private Boolean ativo;

    @NotNull
    @Size(min = 3, max = 80)
    @Column(nullable = false, name = "nome")
    private String nome;

    @NotNull
    @Size(min = 10, max = 80)
    @Email
    @Column(nullable = false, unique = true, name = "email")
    private String email;

    @NotNull
    @Column(nullable = false, name = "perfil")
    private String perfil;

    @NotNull
    @Column(nullable = false, name = "senha")
    private String senha;

    @Column(name = "foto_perfil_path")
    private String fotoPerfilPath;
}
