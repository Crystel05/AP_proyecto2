package Vista.Docente;

import Controlador.ControladorProfesor;
import Modelo.Curso;
import Modelo.Grado;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Docente Asignar Tarea")
@Route(value = "tareasDocs", layout = MenuDocente.class)

public class AsignarTarea extends VerticalLayout {

    private ComboBox<String> idCursos;
    private H2 tituloPagina;
    private TextField tituloNoticia;
    private TextField ID;
    private TextArea contenido;
    private Button enviar;
    private ControladorProfesor profesor = ControladorProfesor.getInstance();

    public AsignarTarea() {
        ventana();
    }

    private void ventana() {
        tituloPagina = new H2("ASIGNAR TAREA");
        idCursos = new ComboBox<>("Cursos disponibles");
        List<String> ids = new ArrayList<>();
        for (Curso c: profesor.getCursosActuales()){
            ids.add(c.getID());
        }
        idCursos.setItems(ids);
        idCursos.setWidth("300px");
        ID = new TextField("ID");
        ID.setWidth("300px");
        tituloNoticia = new TextField("Titulo");
        tituloNoticia.setWidth("300px");
        contenido = new TextArea("Contenido");
        contenido.setWidth("500px");
        contenido.setHeight("400px");
        enviar = new Button("ASIGNAR");
        enviar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        enviar.setWidth("150px");
        enviar.setIcon(VaadinIcon.BOOK.create());
        enviar.addClickListener(e->{
            for (Curso c : profesor.getCursosActuales()){
                if (idCursos.getValue() != null && c.getID().equals(idCursos.getValue())){
                    if (profesor.enviarTarea(c.getID(), convertirGrado(c.getGrado()), ID.getValue(), tituloNoticia.getValue(), contenido.getValue())){
                        Notification.show("Tarea asignada con éxito!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }else{
                        Notification.show("Sucedió un error enviando la tarea, intente de nuevo").addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                }
            }
        });
        add(tituloPagina, idCursos, ID, tituloNoticia, contenido, enviar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
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
