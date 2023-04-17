package com.parcial.demo.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parcial.demo.model.entities.Comercial;

@Service
public interface ComercialService {
    List<Comercial> listarComerciales();

    Comercial guardarComercial(Comercial comercial);

    Comercial actualizarComercial(Comercial comercial);

    void eliminarComercial(long id);

    Comercial buscarComercialPorId(long id);

    List<Comercial> buscarPorNombreApellidoCiudad(String parametro);
}
