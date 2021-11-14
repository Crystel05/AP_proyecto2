package Modelo;

public enum Grado {
    Prepa(1, "prepa"),
    Primero(2, "1"),
    Segundo(3,"2"),
    Tercero(4,"3"),
    Cuarto(5,"4"),
    Quinto(6,"5"),
    Sexto(7,"6"),
    Septimo(8,"7"),
    Undecimo(9,"11");

    private int id;
    private String clase;

    private Grado(int id, String clase){
        this.id = id;
        this.clase = clase;
    }

    public int getId(){
        return this.id;
    }

    public String getClase(){
        return this.clase;
    }
}
