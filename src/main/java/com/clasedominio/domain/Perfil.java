package com.clasedominio.domain;

import jakarta.persistence.*;
import jakarta.persistence.Column;
// Importación de anotaciones de JPA
import lombok.Data; // Importación de anotación de Lombok para generar getters, setters, etc.

@Data // Anotación de Lombok para generar automáticamente métodos como getters, setters, toString, etc.
@Entity // Anotación de JPA para indicar que esta clase es una entidad que se mapeará a una tabla en la base de datos
@Table(name = "perfil") 
public class Perfil {
    // Anotación de JPA para especificar el nombre de la tabla en la base de datos
    @Id //será la llave primaria 
    @GeneratedValue (strategy=GenerationType.IDENTITY) //el generatedValue sirve paraque el Id se genere automaticamente
    @Column(name = "id_perfil")
    private Integer id_perfil; //define el nombre de la columna tabla

    private String nombre; //guarda el atributo el nombre el perfil o rol :v

    
}
