package Controlador;

public class ControladorPorfesor {

    private static ControladorPorfesor controladorPorfesor;

    public static ControladorPorfesor getInstance(){
        if (controladorPorfesor == null){
            controladorPorfesor = new ControladorPorfesor();
        }
        return controladorPorfesor;
    }

    public ControladorPorfesor() {}

    public boolean enviarNoticia(){
        
    }
}
