package Vista.Estudiante;

import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Ver tareas")
@Route(value = "verTareas", layout = MenuEstudiante.class)
public class VerTareas extends VerticalLayout {

    private Tabs cursos;

    public VerTareas() {
        ventana();
    }

    private void ventana() {
        cursos = new Tabs(new Tab("Curso mat"));
        cursos.setWidthFull();

        VerticalLayout tarea  = new VerticalLayout();
        TextField titulo = new TextField();
        titulo.setValue("Tarea 1");
        titulo.setReadOnly(true);
        titulo.setWidth("300px");
        TextArea contenido = new TextArea();
        contenido.setValue("La tarea se escsdjfkjdskfjsdkfjskfjkdjfsdjfksdjfl");
        contenido.setHeight("200px");
        contenido.setWidth("300px");
        contenido.setReadOnly(true);
        tarea.add(titulo, contenido);

        VerticalLayout tarea2  = new VerticalLayout();
        TextField titulo2 = new TextField();
        titulo2.setValue("Tarea 2");
        titulo2.setWidth("300px");
        titulo2.setReadOnly(true);
        TextArea contenido2 = new TextArea();
        contenido2.setValue("sfjdskfjskdjggggggggggglsdjkgkdsjg");
        contenido2.setWidth("300px");
        contenido2.setHeight("200px");
        contenido2.setReadOnly(true);
        tarea2.add(titulo2, contenido2);

        VerticalLayout tarea3  = new VerticalLayout();
        TextField titulo3 = new TextField();
        titulo3.setWidth("300px");
        titulo3.setValue("Tarea 3");
        titulo3.setReadOnly(true);
        TextArea contenido3 = new TextArea();
        contenido3.setValue("askfjkjdsjfjkdshfjsdhfjhdsjgdsjgf");
        contenido3.setReadOnly(true);
        contenido3.setHeight("200px");
        contenido3.setWidth("300px");
        tarea3.add(titulo3, contenido3);

        HorizontalLayout tareas = new HorizontalLayout();
        tareas.add(tarea, tarea2, tarea3);

        VerticalLayout tarea4  = new VerticalLayout();
        TextField titulo4 = new TextField();
        titulo4.setValue("Tarea 1");
        titulo4.setReadOnly(true);
        titulo4.setWidth("300px");
        TextArea contenido4 = new TextArea();
        contenido4.setValue("La tarea se escsdjfkjdskfjsdkfjskfjkdjfsdjfksdjfl");
        contenido4.setHeight("200px");
        contenido4.setWidth("300px");
        contenido4.setReadOnly(true);
        tarea4.add(titulo4, contenido4);

        VerticalLayout tarea5  = new VerticalLayout();
        TextField titulo5 = new TextField();
        titulo5.setValue("Tarea 2");
        titulo5.setWidth("300px");
        titulo5.setReadOnly(true);
        TextArea contenido5 = new TextArea();
        contenido5.setValue("sfjdskfjskdjggggggggggglsdjkgkdsjg");
        contenido5.setWidth("300px");
        contenido5.setHeight("200px");
        contenido5.setReadOnly(true);
        tarea5.add(titulo5, contenido5);

        VerticalLayout tarea6  = new VerticalLayout();
        TextField titulo6 = new TextField();
        titulo6.setWidth("300px");
        titulo6.setValue("Tarea 3");
        titulo6.setReadOnly(true);
        TextArea contenido6 = new TextArea();
        contenido6.setValue("askfjkjdsjfjkdshfjsdhfjhdsjgdsjgf");
        contenido6.setReadOnly(true);
        contenido6.setHeight("200px");
        contenido6.setWidth("300px");
        tarea6.add(titulo6, contenido6);

        HorizontalLayout tareas2 = new HorizontalLayout();
        tareas2.add(tarea4, tarea5, tarea6);

        cursos.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
        });

        add(cursos, tareas, tareas2);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
    }
}
