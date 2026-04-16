package com.clasedominio.dao;

import com.clasedominio.domain.Proveedor;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProveedorDao extends CrudRepository<Proveedor, Integer> {
    
    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
}
