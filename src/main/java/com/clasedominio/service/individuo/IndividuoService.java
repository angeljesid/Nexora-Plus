package com.clasedominio.service.individuo;

import com.clasedominio.dao.IndividuoDao;
import com.clasedominio.domain.Individuo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IndividuoService implements IIndividuoService {

    @Autowired
    private IndividuoDao individuoDao;

    @Override// es como decirle al sistema que voy a realizar esta tarea
    @Transactional(readOnly = true)//le dice al sql que solo va a leer
    public List<Individuo> listarIndividuos() {
        return (List<Individuo>) individuoDao.findAll();
    }// devuelve una lista de individuos, casteada a List para evitar problemas de tipo

    @Override// es como decirle al sistema que voy a realizar esta tarea
    @Transactional//le dice al sql que solo va a leer 
    public void guardarIndividuo(Individuo individuo) {
        individuoDao.save(individuo);
    }// guarda o actualiza, dependiendo si el id es null o no

    @Override// es como decirle al sistema que voy a realizar esta tarea
    @Transactional//le dice al sql que solo va a leer
    public void eliminarIndividuo(Long id) {
        individuoDao.deleteById(id);
    }// elimina por id, no por objeto

    @Override// es como decirle al sistema que voy a realizar esta tarea
    @Transactional(readOnly = true)//le dice al sql que solo va a leer
    public Individuo buscarIndividuo(Individuo individuo) {
        return individuoDao.findById(individuo.getIdIndividuo()).orElse(null);
    }// buca por id, si no lo encuentra devuelve null
}



//IndividuoService (El Obrero): Aquí escribes cómo se hace la tarea.
//  Si lo mencionas en la Interfaz pero no aquí, Java te dará un error
//  de "Clase no implementada" porque el obrero no sabe qué hacer con esa orden.