package com.clasedominio.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario") 
    private Integer idUsuario;

    private String username;
    private String password;
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;
}

//perfil guarda el nombre del perfil del usuario 
//guarda lo del usuario y de paso se relaciona con el perfil