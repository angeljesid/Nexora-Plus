package com.clasedominio.service.proveedor;

import com.clasedominio.domain.Proveedor;
import java.util.List;

public interface IProveedorService {
    public List<Proveedor> listarProveedores();

    public void guardarProveedor(Proveedor proveedor);

    public void eliminarProveedor(Integer id);

    public Proveedor buscarProveedor(Integer id);

    public List<Proveedor> buscarPorNombre(String nombre);
    
}
