package Vista.Docente;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Docente asignarTarea")
@Route(value = "tareasDocs", layout = MenuDocente.class)

public class AsignarTarea extends VerticalLayout {

    private ComboBox<String> idCursos;
    private H2 tituloPagina;
    private TextField tituloNoticia;
    private TextField ID;
    private TextArea contenido;
    private Button enviar;

    public AsignarTarea() {
        ventana();
    }

    private void ventana() {
        tituloPagina = new H2("ASIGNAR TAREA");
        idCursos = new ComboBox<>("Cursos disponibles");
        List<String> ids = new ArrayList<>();
        ids.add("mat"); ids.add("cien"); ids.add("est");
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
        add(tituloPagina, ID, idCursos, tituloNoticia, contenido, enviar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
