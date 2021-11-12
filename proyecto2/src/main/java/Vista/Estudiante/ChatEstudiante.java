package Vista.Estudiante;

import java.util.ArrayList;

import Controlador.ControladorEstudiante;
import Modelo.Curso;
import Modelo.Grado;
import Modelo.Mensaje;
import Modelo.Tarea_Noticia;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Chat estudiante")
@Route(value = "chatE", layout = MenuEstudiante.class)
public class ChatEstudiante extends VerticalLayout {

    private UserInfo userInfo;
    private Tabs tabs;
    private CollaborationMessageList list;
    private HorizontalLayout enviarMensajes;
    private TextField mensaje;
    private Button enviar;
    private MessageListItem enviado;
    private ControladorEstudiante estudiante = ControladorEstudiante.getInstance();
    private ArrayList<Mensaje> mensajesDB = new ArrayList<>();
    private Curso cursoActual = null;

    public ChatEstudiante() {
        addClassName("chat-view");
        ventana();
    }

    private void ventana() {

        setSpacing(false);

        userInfo = new UserInfo("10", "Prueba"); //cambiar por los usarios de la base de datos

        tabs = new Tabs();
        for (Curso c : estudiante.getCursosActuales()) {
            tabs.add(new Tab(c.getNombre()));
        }
        tabs.setWidthFull();

        list = new CollaborationMessageList(userInfo, "chat/#Curso mat");
      //  listaMensajes(null, mensajesDB);
        list.setWidthFull();

        list.addClassNames("chat-view-message-list");

        enviarMensajes = new HorizontalLayout();
        mensaje = new TextField();
        mensaje.setWidthFull();
        enviar = new Button(VaadinIcon.PAPERPLANE.create());
        enviar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        enviarMensajes.setWidthFull();
        enviarMensajes.add(mensaje, enviar);
        enviado = new MessageListItem();
        enviado.setUserName(userInfo.getName());
        enviado.setUserColorIndex(2);
        enviar.addClickListener(e->{
            enviado.setText(mensaje.getValue());
            listaMensajes(enviado, mensajesDB);
            Notification.show(cursoActual.getID() + " " + convertirGrado(cursoActual.getGrado()) + " " +
                    estudiante.getEstudianteActual().getCorreo() + mensaje.getValue());
            boolean var = estudiante.enviarMensaje(cursoActual.getID(), convertirGrado(cursoActual.getGrado()),
                    estudiante.getEstudianteActual().getCorreo(), mensaje.getValue());
            if (var){
                Notification.show("todo good");
            }
            mensaje.clear();
        });

        add(tabs, list, enviarMensajes);
        setSizeFull();
        expand(list);

        tabs.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
            list.setTopic("chat/" + channelName);
            for (Curso curso : estudiante.getCursosActuales()){
                if (curso.getNombre().equals(channelName)){
                    String grado = convertirGrado(curso.getGrado());
                    mensajesDB = estudiante.listaMensajesCurso(curso.getID(), grado);
                    listaMensajes(null, mensajesDB);
                    cursoActual = curso;
                }
            }
        });
    }

    private void listaMensajes(MessageListItem enviado, ArrayList<Mensaje> mensajesDB){
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

    private String convertirGrado(Grado grado) {
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

}