package com.clasedominio.service.producto;

import com.clasedominio.domain.Producto;
import java.util.List;


public interface IProductoService {
    public List<Producto> listarProductos();

    public void guardarProducto(Producto producto);

    public void eliminarProducto(Integer id);

    public Producto buscarProducto(Integer id);

    public List<Producto> buscarProducto(String keyword);
    
    public long contarStockBajo();

    public List<Producto> obtenerTopStockBajo();



}
