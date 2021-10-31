package Modelo;

import java.util.ArrayList;

public class Estudiante {

    private String nombre;
    private String cedula;
    private String correo;
    private String contrasenna;
    private ArrayList<Curso> cursos;
    private Grado grado;

    public Estudiante(String nombre, String cedula, String correo, String contrasenna, ArrayList<Curso> cursos, Grado grado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.cursos = cursos;
        this.grado = grado;
    }

    public Estudiante(String nombre, String cedula, String correo, Grado grado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.grado = grado;
    }

    public Estudiante() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }
}