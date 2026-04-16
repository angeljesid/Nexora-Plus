package com.clasedominio.dao;

import com.clasedominio.domain.Empleado;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoDao extends CrudRepository<Empleado, Integer> {
    Empleado findByUsername(String username);
    // Spring genera automáticamente el: SELECT * FROM empleado WHERE para buscar por nombre, ignorando mayúsculas y minúsculas
    List<Empleado> findByNombreContainingIgnoreCase(String nombre);
}