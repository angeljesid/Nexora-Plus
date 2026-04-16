package com.clasedominio.service.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clasedominio.domain.Empleado;
import org.springframework.transaction.annotation.Transactional;

import com.clasedominio.dao.EmpleadoDao;
import java.util.List;


@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados() {
        return (List<Empleado>) empleadoDao.findAll();
    }

    @Override
    @Transactional
    public void guardarEmpleado(Empleado empleado) {
        empleadoDao.save(empleado);
    }
    @Override
    @Transactional
    public void eliminarEmpleado(Integer id) {
        empleadoDao.deleteById(id);
}
    @Override
    @Transactional(readOnly = true)
    public Empleado buscarEmpleado(Integer id) {
        return empleadoDao.findById(id).orElse(null);
    }

    @Override
@Transactional(readOnly = true)
public List<Empleado> buscarPorNombre(String nombre) {
    return (List<Empleado>) empleadoDao.findByNombreContainingIgnoreCase(nombre);
}

    @Override
    @Transactional(readOnly = true)
    public Empleado buscarPorUsername(String username) {
        return empleadoDao.findByUsername(username);
    }
    
}
