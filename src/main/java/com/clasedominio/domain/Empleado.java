package com.clasedominio.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
@Data 
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    private Integer edad;

    // Aquí centralizamos cargo y permisos en una sola columna
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol; 

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    private String telefono;
    
    private String correo;

    private Boolean activo = true;

    // --- NUEVOS CAMPOS PARA LA CUENTA ---
    
    @Column(unique = true)
    private String username;

    private String password;

    // Enumeración única de Roles/Cargos
    public enum Rol { ADMIN, SECRETARIA, VENDEDOR, BODEGUERO
    }

    @PrePersist
    protected void onCreate() {
        if (this.fechaIngreso == null) {
            this.fechaIngreso = LocalDate.now();
        }
    }
}