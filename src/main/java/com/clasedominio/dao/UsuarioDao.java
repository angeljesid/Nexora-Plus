package com.clasedominio.dao;

import com.clasedominio.domain.Usuario;
import org.springframework.data.repository.*;
//interfas Dao Usuario
public interface UsuarioDao extends CrudRepository<Usuario,Integer>{
Usuario findByUsername(String username); //este metodo buscará el usuario por el nombre
    
}
