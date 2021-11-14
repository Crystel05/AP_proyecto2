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

    public void setGrado(String num) {
        if (Grado.Prepa.getClase().equals(num)){
            this.grado = Grado.Prepa;
        } else if (Grado.Primero.getClase().equals(num)){
            this.grado = Grado.Primero;
        } else if (Grado.Segundo.getClase().equals(num)){
            this.grado = Grado.Segundo;
        } else if (Grado.Tercero.getClase().equals(num)){
            this.grado = Grado.Tercero;
        } else if (Grado.Cuarto.getClase().equals(num)){
            this.grado = Grado.Cuarto;
        } else if (Grado.Sexto.getClase().equals(num)){
            this.grado = Grado.Sexto;
        } else if (Grado.Septimo.getClase().equals(num)){
            this.grado = Grado.Septimo;
        } else if (Grado.Undecimo.getClase().equals(num)){
            this.grado = Grado.Undecimo;
        }
    }
}
