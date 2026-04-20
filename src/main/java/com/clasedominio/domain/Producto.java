package com.clasedominio.domain;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "producto")
// Esta anotación es el "escudo" contra el error 500 de Hibernate Lazy
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_p")
    private Integer idP;

    private String nombre;
    
    private BigDecimal precio;
    
    private BigDecimal iva;
    
    private int stock;

    @Column(name = "codigoBarras") 
    private String codigoBarras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prov_fk")
    // @JsonIgnore es la solución definitiva: evita que el historial 
    // intente cargar al proveedor, que no es necesario en la tabla de ventas
    @JsonIgnore 
    private Proveedor proveedor; 

    @Transient
    public BigDecimal getPrecioPublico() {
        if (precio == null) return BigDecimal.ZERO;
        if (iva == null) return precio;
        return precio.add(precio.multiply(iva));
    }
}