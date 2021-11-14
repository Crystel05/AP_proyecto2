package Modelo;

public class Curso {

    private String ID;
    private String nombre;
    private Grado grado;
    private String horario;


    public Curso(){}
    public Curso(String ID, String nombre, Grado grado, String horario) {
        this.ID = ID;
        this.nombre = nombre;
        this.grado = grado;
        this.horario = horario;
    }

    public Curso(String codigo, String nombre) {
        this.ID = codigo;
        this.nombre = nombre;
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

    public static  Grado getGradoEnum(String num) {
        if (Grado.Prepa.getClase().equals(num)){
            return Grado.Prepa;
        } else if (Grado.Primero.getClase().equals(num)){
            return Grado.Primero;
        } else if (Grado.Segundo.getClase().equals(num)){
            return Grado.Segundo;
        } else if (Grado.Tercero.getClase().equals(num)){
            return Grado.Tercero;
        } else if (Grado.Cuarto.getClase().equals(num)){
            return Grado.Cuarto;
        } else if (Grado.Sexto.getClase().equals(num)){
            return Grado.Sexto;
        } else if (Grado.Septimo.getClase().equals(num)){
            return Grado.Septimo;
        } else if (Grado.Undecimo.getClase().equals(num)){
            return Grado.Undecimo;
        }
        return null;
    }
    public static Dia getDiaEnum(String dia) {
        if (Dia.Lunes.getDia().equals(dia)){
            return Dia.Lunes;
        } else if (Dia.Martes.getDia().equals(dia)){
            return Dia.Martes;
        } else if (Dia.Miércoles.getDia().equals(dia)){
            return Dia.Miércoles;
        } else if (Dia.Jueves.getDia().equals(dia)){
            return Dia.Jueves;
        } else if (Dia.Viernes.getDia().equals(dia)){
            return Dia.Viernes;
        } else {
            return Dia.Sábado;
        }
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
