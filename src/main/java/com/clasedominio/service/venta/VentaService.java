package com.clasedominio.service.venta;

import com.clasedominio.dao.VentaDao;
import com.clasedominio.dao.ProductoDao;
import com.clasedominio.domain.DetalleVenta;
import com.clasedominio.domain.Producto;
import com.clasedominio.domain.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaDao ventaDao;

    @Autowired
    private ProductoDao productoDao; 

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarVentas() {
        return (List<Venta>) ventaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Venta venta) {
        if (venta.getDetalles() != null) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                Producto p = detalle.getProducto();
                if (p != null && p.getIdP() != null) {
                    Producto productoBD = productoDao.findById(p.getIdP()).orElse(null);
                    if (productoBD != null) {
                        productoBD.setStock(productoBD.getStock() - detalle.getCantidad());
                        productoDao.save(productoBD);
                    }
                }
            }
        }
        ventaDao.save(venta);
    }

    @Override
    @Transactional
    public void eliminar(Venta venta) {
        ventaDao.delete(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Venta encontrarVenta(Venta venta) {
        return ventaDao.findById(venta.getIdVenta()).orElse(null);
    }
}