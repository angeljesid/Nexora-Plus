package com.clasedominio.domain;

import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.Data;

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
    private String codigoBarras;

    // --- NUEVA RELACIÓN ---
    @ManyToOne(fetch = FetchType.LAZY) // Lazy para que solo cargue el proveedor
    @JoinColumn(name = "id_prov_fk") // El nombre de la columna en la tabla SQL
    private Proveedor proveedor; 

    @Transient
    public BigDecimal getPrecioPublico() {
        if (precio == null || iva == null) {
            return BigDecimal.ZERO;
        }
        // Multiplica precio * (1 + iva) si iva es un porcentaje decimal
        return precio.add(precio.multiply(iva));

    }


}