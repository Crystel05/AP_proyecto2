package Modelo;

import java.util.ArrayList;

public class Estudiante extends Usuario {

    private Grado grado;

    public Estudiante(String nombre, String correo, String contrasenna, String cedula, ArrayList<Curso> cursos, Grado grado) {
        super(nombre, correo, contrasenna, cedula, cursos);
        this.grado = grado;
    }

    public Estudiante(String nombre, String correo, String contrasenna, String cedula, Grado grado) {
        super(nombre, correo, contrasenna, cedula);
        this.grado = grado;
    }

    public Estudiante(String nombre, String correo, String cedula, Grado grado) {
        super(nombre, correo, cedula);
        this.grado = grado;
    }

    public Estudiante() {
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }
}
