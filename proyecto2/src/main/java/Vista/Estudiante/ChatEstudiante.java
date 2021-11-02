package Vista.Estudiante;

import java.util.ArrayList;
import com.vaadin.collaborationengine.CollaborationMessageList;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.messages.MessageListItem;
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

    public ChatEstudiante() {
        addClassName("chat-view");
        ventana();
    }

    private void ventana() {

        setSpacing(false);

        userInfo = new UserInfo("10", "Prueba"); //cambiar por los usarios de la base de datos

        tabs = new Tabs(new Tab("#Curso mat"));
        tabs.setWidthFull();

        list = new CollaborationMessageList(userInfo, "chat/#Curso mat");
        listaMensajes(null);
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
            listaMensajes(enviado);
            mensaje.clear();
        });

        add(tabs, list, enviarMensajes);
        setSizeFull();
        expand(list);

        tabs.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
            list.setTopic("chat/" + channelName);
        });
    }

    private void listaMensajes(MessageListItem enviado){
        ArrayList<MessageListItem> mensajes = new ArrayList<>();
        MessageListItem mensaje1 = new MessageListItem(); // agregar los mensajes de la base de datos
        mensaje1.setUserName("Pablito");
        mensaje1.setUserColorIndex(6); //7 es la cantidad máxima
        mensaje1.setText("Hola");
        MessageListItem mensaje2 = new MessageListItem();
        mensaje2.setUserName("Juan");
        mensaje2.setText("Hola");
        mensaje2.setUserColorIndex(5);
        mensajes.add(mensaje1);
        mensajes.add(mensaje2);
        if (enviado != null){
            mensajes.add(enviado);
        }
        list.getContent().setItems(mensajes);
    }

}