package Vista.Estudiante;

import Controlador.ControladorEstudiante;
import Controlador.DummyMethods;
import Modelo.Curso;
import Modelo.Grado;
import Modelo.Tarea_Noticia;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

@PageTitle("Ver noticias")
@Route(value = "verNoticias", layout = MenuEstudiante.class)
public class VerNoticias extends VerticalLayout {

    private Tabs cursos;
    private ControladorEstudiante estudiante = ControladorEstudiante.getInstance();
    private DummyMethods dummyMethods = new DummyMethods();

    public VerNoticias() {
        ventana();
    }

    private void ventana() {
        cursos = new Tabs();
        for (Curso c : estudiante.getCursosActuales()){
            cursos.add(new Tab(c.getNombre()));
        }
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

        ArrayList<TextField> titulos = new ArrayList<>();
        titulos.add(titulo);titulos.add(titulo2);titulos.add(titulo3);titulos.add(titulo4);titulos.add(titulo5);titulos.add(titulo6);
        ArrayList<TextArea> descripciones = new ArrayList<>();
        descripciones.add(contenido);descripciones.add(contenido2);descripciones.add(contenido3);descripciones.add(contenido4);descripciones.add(contenido5);descripciones.add(contenido6);

        for (int i = 0; i< titulos.size(); i++){
            titulos.get(i).setVisible(false);
            descripciones.get(i).setVisible(false);
        }

        cursos.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
            for (Curso curso : estudiante.getCursosActuales()){
                if (curso.getNombre().equals(channelName)){
                    String grado = dummyMethods.convertirGrado(curso.getGrado());
                    ArrayList<Tarea_Noticia> noticias = estudiante.noticias(curso.getID(), grado);
                    if (noticias != null && noticias.size()>0){
                        int cant = noticias.size()-1;
                        if (noticias.size()>6)
                            cant = 6;
                        for (int i = 0; i < cant; i++){
                            titulos.get(i).setValue(noticias.get(i).getTitulo());
                            titulos.get(i).setVisible(true);
                            descripciones.get(i).setValue(noticias.get(i).getContenido());
                            descripciones.get(i).setVisible(true);
                        }
                    }else{
                        for (int i = 0; i < titulos.size()-1; i++){
                            titulos.get(i).setVisible(false);
                            descripciones.get(i).setVisible(false);
                        }
                        Notification.show("No hay noticias sin leer :)!", 5000, Notification.Position.MIDDLE).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    }
                }
            }
        });

        add(cursos, tareas, tareas2);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
    }

}
