package Controlador;

import Modelo.Grado;
import Modelo.Mensaje;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.flow.component.messages.MessageListItem;

import java.util.ArrayList;

public class DummyMethods {

    public DummyMethods() {}

    public void listaMensajes(MessageListItem enviado, ArrayList<Mensaje> mensajesDB, CollaborationMessageList list){
        ArrayList<MessageListItem> mensajes = new ArrayList<>();
        if (mensajesDB.size()>0){
            for (Mensaje m: mensajesDB){
                MessageListItem mensajeNuevo = new MessageListItem();
                mensajeNuevo.setUserName(m.getUsuario().getNombre());
                mensajeNuevo.setText(m.getContenido());
                mensajeNuevo.setUserColorIndex(generarColor());
                mensajes.add(mensajeNuevo);
            }
        }
        if (enviado != null){
            mensajes.add(enviado);
        }
        list.getContent().setItems(mensajes);
    }

    private Integer generarColor(){
        int num = (int) (Math.random()* 7+1);
        while(num == 2){
            num = (int) (Math.random()* 7+1);
        }
        return num;
    }

    public String convertirGrado(Grado grado) {
        String gra = "";
        switch (grado){
            case Primero:
                gra = "1";
                break;
            case Segundo:
                gra = "2";
                break;
            case Cuarto:
                gra = "4";
                break;
            case Quinto:
                gra = "5";
                break;
            case Sexto:
                gra = "6";
                break;
            case Septimo:
                gra = "7";
                break;
            case Preparatoria:
                gra = "prepa";
                break;
            case Undecimo:
                gra = "11";
                break;
            case Tercero:
                gra = "3";
                break;
        }
        return gra;
    }

    public Grado convertirGradoBD(String gradoBD){
        switch (gradoBD){
            case "prepa":
                return Grado.Preparatoria;
            case "1":
                return Grado.Primero;
            case "2":
                return Grado.Segundo;
            case "3":
                return Grado.Tercero;
            case "4":
                return Grado.Cuarto;
            case "5":
                return Grado.Quinto;
            case "6":
                return Grado.Sexto;
            case "7":
                return Grado.Septimo;
            case "11":
                return Grado.Undecimo;
            default:
                return null;
        }
    }
}
