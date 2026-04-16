package com.clasedominio.service.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clasedominio.domain.Empleado;
import com.clasedominio.domain.Proveedor;
import com.clasedominio.dao.ProveedorDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ProveedorService implements IProveedorService {
    @Autowired
    private ProveedorDao proveedorDao;

    @Override
    public List<Proveedor> listarProveedores() {
        return (List<Proveedor>) proveedorDao.findAll();
    }

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        proveedorDao.save(proveedor);
    }
    @Override
    public void eliminarProveedor(Integer id) {
        proveedorDao.deleteById(id);
}
    @Override
    public Proveedor buscarProveedor(Integer id) {
        return proveedorDao.findById(id).orElse(null);
    }

    @Override
@Transactional(readOnly = true)
public List<Proveedor> buscarPorNombre(String nombre) {
    return (List<Proveedor>) proveedorDao.findByNombreContainingIgnoreCase(nombre);
}
    
}
