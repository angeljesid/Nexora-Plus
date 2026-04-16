package com.clasedominio.dao;

import com.clasedominio.domain.Individuo;
import org.springframework.data.repository.CrudRepository;
import java.util.List; 

public interface IndividuoDao extends CrudRepository<Individuo, Long> {
    
    // Spring leerá este nombre y creará la consulta SQL automáticamente
    List<Individuo> findByNombreContainingIgnoreCase(String nombre);
    
}