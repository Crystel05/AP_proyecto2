package Modelo;

public class Mensaje {

    private Usuario usuario;
    private String contenido;

    public Mensaje(Usuario usuario, String contenido) {
        this.usuario = usuario;
        this.contenido = contenido;
    }

    public Mensaje() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
