package com.clasedominio.service.empleado;

import com.clasedominio.domain.Empleado;
import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();

    public void guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Integer id);

    public Empleado buscarEmpleado(Integer id);
    
    List<Empleado> buscarPorNombre(String nombre);

    public Empleado buscarPorUsername(String username);
    
}
