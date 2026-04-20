package com.clasedominio.service.venta;

import com.clasedominio.domain.Venta;
import java.util.List;

public interface IVentaService {
    public List<Venta> listarVentas();
    public void guardar(Venta venta);
    public void eliminar(Venta venta);
    public Venta encontrarVenta(Venta venta);
}