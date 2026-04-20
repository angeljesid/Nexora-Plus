package com.clasedominio.service.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clasedominio.domain.Producto;
import com.clasedominio.dao.ProductoDao;

import java.util.List;

@Service

public class ProductoService implements IProductoService {
    @Autowired
    private ProductoDao productoDao;

    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    public void guardarProducto(Producto producto) {
        productoDao.save(producto);
    }
    @Override
    public void eliminarProducto(Integer id) {
        productoDao.deleteById(id);
}
    @Override
    public Producto buscarProducto(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    public List<Producto> buscarProducto(String keyword) {
        return productoDao.findByNombreContainingIgnoreCase(keyword);
    }

    @Override
    public long contarStockBajo() {
    return productoDao.countByStockLessThan(6);
}

    @Override
    public List<Producto> obtenerTopStockBajo() {
    return productoDao.findTop3ByOrderByStockAsc();
}

    @Override
    public Producto buscarPorCodigo(String codigo) {
    return productoDao.findByCodigoBarras(codigo).orElse(null);
}

}
