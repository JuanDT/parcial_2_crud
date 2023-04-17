package com.parcial.demo.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.demo.model.entities.Comercial;
import com.parcial.demo.model.repository.ComercialRepository;

@Service
public class ComercialServiceImpl implements ComercialService {

   
    @Autowired
    private ComercialRepository comercialRepository;

    @Override
    public List<Comercial> listarComerciales() {
        return comercialRepository.findAll();
    }

    @Override
    public Comercial guardarComercial(Comercial comercial) {
        return comercialRepository.save(comercial);
    }

    @Override
    public Comercial actualizarComercial(Comercial comercial) {
        return comercialRepository.save(comercial);
    }

    @Override
    public void eliminarComercial(long id) {
        comercialRepository.deleteById(id);
    }

    @Override
    public Comercial buscarComercialPorId(long id) {
       return comercialRepository.findById(id).orElse(null);
        
    }

    @Override
    public List<Comercial> buscarPorNombreApellidoCiudad(String parametro) {       
        return comercialRepository.findByNombreContainsOrApellido1ContainsOrApellido2ContainsCiudadcontains(parametro, parametro, parametro, parametro);
    }  

    
}







