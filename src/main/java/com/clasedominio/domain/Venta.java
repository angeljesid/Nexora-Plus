package com.clasedominio.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data 
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta") // <--- CAMBIO CRÍTICO: Debe ser id_venta, no idVenta
    private Integer idVenta;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta = LocalDateTime.now();

    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Individuo cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venta")
    private List<DetalleVenta> detalles;
}