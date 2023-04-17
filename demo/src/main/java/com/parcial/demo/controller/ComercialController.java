package com.parcial.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcial.demo.model.entities.Comercial;
import com.parcial.demo.model.service.ComercialService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/comerciales")
public class ComercialController {

    @Autowired
    private ComercialService comercialService;

    @GetMapping("/listar")
    public String listarComerciales(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        List<Comercial> listaComerciales = new ArrayList<>();
        Comercial comercialId = new Comercial();
             
        if (nombre != null && nombre.matches("^[0-9]+$")) {
            
            Long id = Long.parseLong(nombre);
            comercialId = comercialService.buscarComercialPorId(id);
            if(comercialId != null){
                listaComerciales.add(comercialId);
                
            }else{              
                model.addAttribute("listaComerciales", listaComerciales);
                model.addAttribute("comercial", comercialId);
                model.addAttribute("nombre", nombre);
                return "comerciales/listar";
            }            
        }else{

            if(nombre != null && !nombre.isEmpty()){
             listaComerciales = comercialService.buscarPorNombreApellidoCiudad(nombre);
             }else{
                 listaComerciales = comercialService.listarComerciales();
             }
        }
     
            model.addAttribute("listaComerciales", listaComerciales);
            model.addAttribute("idComercial", comercialId);
            model.addAttribute("nombre", nombre);
            return "comerciales/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioAgregarComercial(Model model) {
        model.addAttribute("comercial", new Comercial());
        return "comerciales/nuevo";
    }

    @PostMapping("/guardar")
    public String guardarComercial(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult result) {
        if (result.hasErrors()) {
            return "comerciales/guardar";
        } else {
            comercialService.guardarComercial(comercial);
            return "redirect:/comerciales/listar";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarComercial(@PathVariable("id") Long id, Model model) {
        Comercial comercial = comercialService.buscarComercialPorId(id);
        model.addAttribute("comercial", comercial);
        return "comerciales/editar";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarComercial(@PathVariable Long id, @Valid @ModelAttribute("comercial") Comercial comercial,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "comerciales/editar";
        } else {
            Comercial comercialExistente = comercialService.buscarComercialPorId(id);
            if (comercialExistente == null) {
                return "redirect:/comerciales/listar?error=Comercial no encontrado con id=" + id;
            } else {
                comercialExistente.setNombre(comercial.getNombre());
                comercialExistente.setApellido1(comercial.getApellido1());
                comercialExistente.setApellido2(comercial.getApellido2());
                comercialExistente.setCiudad(comercial.getCiudad());
                comercialExistente.setComision(comercial.getComision());
                comercialService.actualizarComercial(comercialExistente);
                return "redirect:/comerciales/listar";
            }
        }
    }








    @GetMapping("/eliminar/{id}")
    public String eliminarComercial(@PathVariable("id") Long id, RedirectAttributes attributes) {
        List<Comercial> comercial = comercialService.findComercialWithPedidosById(id);

        if (comercial.size() == 0) {
            comercialService.eliminarComercial(id);;
            attributes.addFlashAttribute("mensaje", "Cliente comercial correctamente");
        } else {
            attributes.addFlashAttribute("error", "El comercial tiene pedidos asociados y no se puede eliminar");
        }

        return "redirect:/comerciales/listar";
    }
}
