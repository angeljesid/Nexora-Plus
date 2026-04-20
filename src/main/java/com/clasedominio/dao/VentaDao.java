    package com.clasedominio.dao;

    import com.clasedominio.domain.Venta;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface VentaDao extends CrudRepository<Venta, Integer> {
    }
