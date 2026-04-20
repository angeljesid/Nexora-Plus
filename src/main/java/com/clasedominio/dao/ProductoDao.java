package com.clasedominio.dao;

import com.clasedominio.domain.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoDao extends CrudRepository<Producto, Integer> {

    Optional<Producto> findByCodigoBarras(String codigoBarras);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByNombreContaining(String keyword);
    
    List<Producto> findByCodigoBarrasContaining(String codigoBarras);

    // Busca todos los productos ordenados por stock ascendente
List<Producto> findTop3ByOrderByStockAsc();
    
    long countByStockLessThan(int limite);
}
