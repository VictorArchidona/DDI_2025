package com.ddi.models;

import jakarta.persistence.*;

@Entity
public class Estudiante {

    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    private String nombre;

    private String email;

    public Estudiante(){
    }

    public  Estudiante(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }
    public String setNombre(String nombre) {
        this.nombre = nombre;
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String setEmail(String email) {
        this.email = email;
        return email;
    }

    @Override
    public String toString() {
        return "Estudiante: " +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", email: '" + email;
    }
}
