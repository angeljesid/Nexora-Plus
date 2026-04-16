package com.clasedominio.domain;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
//se importa para el uso de Data, que es una anotación de Lombok que genera automáticamente
//  los métodos getters, setters, toString, equals y hashCode para la clase.
@Data
@Entity
@Table(name = "individuo")


public class Individuo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idIndividuo;
    @NotEmpty//no permirmite que el espacio sea vacio
    private String nombre;
    @NotEmpty//no permirmite que el espacio sea vacio
    private String apellido;
    @NotNull//no permirmite que el espacio sea nulo
    private int edad;
    @NotEmpty//no permirmite que el espacio sea vacio
    private String correo;
    @NotEmpty//no permirmite que el espacio sea vacio
    private String telefono;    
}

