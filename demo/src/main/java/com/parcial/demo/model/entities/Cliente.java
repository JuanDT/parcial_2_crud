package com.parcial.demo.model.entities;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El primer apellido debe tener entre 2 y 100 caracteres")
    private String apellido1;

    @Size(max = 100, message = "El segundo apellido no debe tener más de 100 caracteres")
    private String apellido2;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(min = 2, max = 100, message = "La ciudad debe tener entre 2 y 100 caracteres")
    private String ciudad;

    @NotNull(message = "La categoría es obligatoria")
    private Integer categoria;


// Constructor, getters y setters

    public Cliente(Long id,
            @NotBlank(message = "El nombre es obligatorio") @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres") String nombre,
            @NotBlank(message = "El primer apellido es obligatorio") @Size(min = 2, max = 100, message = "El primer apellido debe tener entre 2 y 100 caracteres") String apellido1,
            @Size(max = 100, message = "El segundo apellido no debe tener más de 100 caracteres") String apellido2,
            @NotBlank(message = "La ciudad es obligatoria") @Size(min = 2, max = 100, message = "La ciudad debe tener entre 2 y 100 caracteres") String ciudad,
            @NotNull(message = "La categoría es obligatoria") @Min(value = 1, message = "La categoría debe ser mayor o igual a 1") @Max(value = 5, message = "La categoría debe ser menor o igual a 5") Integer categoria) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.ciudad = ciudad;
        this.categoria = categoria;
    }

    public Cliente() {
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

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    
    
}

