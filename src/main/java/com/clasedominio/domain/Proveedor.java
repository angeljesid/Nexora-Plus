package com.clasedominio.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prov")
    private Integer idProv;
    
    private String nombre;
    private String empresa;
    private String telefono;
    private String correo;
    
}
