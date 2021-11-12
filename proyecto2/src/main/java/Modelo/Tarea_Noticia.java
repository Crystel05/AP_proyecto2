package Modelo;

public class Tarea_Noticia {

    private String titulo;
    private String contenido;

    public Tarea_Noticia(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public Tarea_Noticia() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
