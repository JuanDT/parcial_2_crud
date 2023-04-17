package com.parcial.demo.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comercial")
public class Comercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre debe tener como máximo 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 100, message = "El primer apellido debe tener como máximo 100 caracteres")
    @Column(name = "apellido1")
    private String apellido1;

    @NotBlank(message = "El segundo apellido es obligatorio")
    @Size(max = 100, message = "El segundo apellido debe tener como máximo 100 caracteres")
    @Column(name = "apellido2")
    private String apellido2;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100, message = "La ciudad debe tener como máximo 100 caracteres")
    @Column(name = "ciudad")
    private String ciudad;

    @DecimalMin(value = "0", message = "La comisión no puede ser negativa")
    @DecimalMax(value = "100", message = "La comisión no puede ser mayor a 100")
    @Column(name = "comision")
    private Float comision;

    public Comercial() {}

    public Comercial(String nombre, String apellido1, String apellido2, String ciudad, Float comision) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.ciudad = ciudad;
        this.comision = comision;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Float getComision() {
        return comision;
    }

    public void setComision(Float comision) {
        this.comision = comision;
    }
   
}

