package com.clasedominio.service.individuo;

import com.clasedominio.domain.Individuo;
import java.util.List;

public interface IIndividuoService {
    public List<Individuo> listarIndividuos();
    public void guardarIndividuo(Individuo individuo);
    public void eliminarIndividuo(Long id);
    public Individuo buscarIndividuo(Individuo individuo);
    Individuo buscarPorCedula(String cedula);
}


//IIndividuoService (El Contrato): Aquí defines qué se puede hacer 
// (ej. guardarIndividuo). Si no está aquí, el sistema no sabe que esa "orden" existe legalmente.