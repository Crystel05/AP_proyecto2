package Modelo;

public class Curso {

    private String ID;
    private String nombre;
    private Grado grado;
    private String horario;

    public Curso(String ID, String nombre, Grado grado, String horario) {
        this.ID = ID;
        this.nombre = nombre;
        this.grado = grado;
        this.horario = horario;
    }

    public Curso() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
