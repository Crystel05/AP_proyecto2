package Modelo;

public enum Dia {
    Lunes("Lunes"),
    Martes("Martes"),
    Miércoles("Miercoles"),
    Jueves("Jueves"),
    Viernes("Viernes"),
    Sábado("Sábado");

    private String dia;

    private Dia (String dia){
        this.dia = dia;
    }

    public String getDia(){
        return this.dia;
    }
}
