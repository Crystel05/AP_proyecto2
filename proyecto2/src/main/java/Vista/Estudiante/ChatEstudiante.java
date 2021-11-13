package Vista.Estudiante;

import java.util.ArrayList;

import Controlador.ControladorEstudiante;
import Controlador.DummyMethods;
import Modelo.Curso;
import Modelo.Mensaje;
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

@PageTitle("Chat Estudiante")
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
    private DummyMethods dummyMethods = new DummyMethods();

    public ChatEstudiante() {
        addClassName("chat-view");
        ventana();
    }

    private void ventana() {

        setSpacing(false);

        if (estudiante.getEstudianteActual() != null)
            userInfo = new UserInfo(estudiante.getEstudianteActual().getCedula(), estudiante.getEstudianteActual().getNombre()); //cambiar por los usarios de la base de datos
        else
            userInfo = new UserInfo("1", "a");
        tabs = new Tabs();
        for (Curso c : estudiante.getCursosActuales()) {
            tabs.add(new Tab(c.getNombre()));
        }
        tabs.setWidthFull();

        list = new CollaborationMessageList(userInfo, "chat/#Curso mat");
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
            if(estudiante.getControlador().enviarMensaje(cursoActual.getID(), dummyMethods.convertirGrado(cursoActual.getGrado()),
                    estudiante.getEstudianteActual().getCorreo(), mensaje.getValue())){
                enviado.setText(mensaje.getValue());
                dummyMethods.listaMensajes(enviado, mensajesDB, list);
            }else{
                Notification.show("Error al enviar el mensaje").addThemeVariants(NotificationVariant.LUMO_ERROR);
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
                    String grado = dummyMethods.convertirGrado(curso.getGrado());
                    mensajesDB = estudiante.getControlador().listaMensajesCurso(curso.getID(), grado);
                    dummyMethods.listaMensajes(null, mensajesDB, list);
                    cursoActual = curso;
                }
            }
        });
    }

}