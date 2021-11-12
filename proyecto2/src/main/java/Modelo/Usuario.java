package Modelo;

import java.util.ArrayList;

public class Usuario {

    private String nombre;
    private String correo;
    private String contrasenna;
    private String cedula;
    private ArrayList<Curso> cursos;

    public Usuario(String nombre, String correo, String contrasenna, String cedula, ArrayList<Curso> cursos) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.cedula = cedula;
        this.cursos = cursos;
    }

    public Usuario(String nombre, String correo, String contrasenna, String cedula) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenna = contrasenna;
        this.cedula = cedula;
    }

    public Usuario(String nombre, String correo, String cedula) {
        this.nombre = nombre;
        this.correo = correo;
        this.cedula = cedula;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }
}
