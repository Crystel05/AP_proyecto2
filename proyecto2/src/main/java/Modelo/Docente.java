package Modelo;

import java.util.ArrayList;

public class Docente extends Usuario {


    private float calificacion;

    public Docente(String nombre, String correo, String contrasenna, String cedula, ArrayList<Curso> cursos, float calificacion) {
        super(nombre, correo, contrasenna, cedula, cursos);
        this.calificacion = calificacion;
    }

    public Docente(String nombre, String correo, String contrasenna, String cedula, float calificacion) {
        super(nombre, correo, contrasenna, cedula);
        this.calificacion = calificacion;
    }

    public Docente(String nombre, String correo, String cedula, float calificacion) {
        super(nombre, correo, cedula);
        this.calificacion = calificacion;
    }

    public Docente() {
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }
}
