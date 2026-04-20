package com.clasedominio.domain;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_p")
    private Integer idP;

    private String nombre;
    
    private BigDecimal precio;
    
    private BigDecimal iva;
    
    private int stock;

    // ESPECIFICA EL NOMBRE DE LA COLUMNA EXACTO DE TU BASE DE DATOS
    @Column(name = "codigoBarras") 
    private String codigoBarras;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prov_fk")
    // IMPORTANTE: Evita errores de recursividad al convertir a JSON para el buscador
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    private Proveedor proveedor; 

    @Transient
    public BigDecimal getPrecioPublico() {
        if (precio == null) return BigDecimal.ZERO;
        if (iva == null) return precio;
        
        // Si el IVA en tu DB es 0.19, esto funciona: precio + (precio * 0.19)
        return precio.add(precio.multiply(iva));
    }
}