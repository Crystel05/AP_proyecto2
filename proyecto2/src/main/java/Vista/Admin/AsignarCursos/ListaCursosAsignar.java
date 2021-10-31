package Vista.Admin.AsignarCursos;

import Modelo.Curso;
import Modelo.Grado;
import Vista.Admin.GestionCursos.AgregarCurso;
import Vista.Admin.MenuAdmin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Asignar cursos")
@Route(value = "asignarP", layout = MenuAdmin.class)
public class ListaCursosAsignar extends VerticalLayout {

    private Grid<Curso> cursos;
    private Dialog asignarP;
    private Dialog asignarDocente;
    private Dialog asignarEstudiantes;
    private List<Curso> listaCursos = new ArrayList<>();
    private Button asignarProf;
    private Button asignarEsts;
    private ComboBox<String> docentes;
    private List<String> docents;
    private Button confirmar;
    private MultiSelectListBox<String> estudiantes;

    public ListaCursosAsignar() {
        addClassName("lista-cursos-asignar-view");
        ventana();
    }

    private void ventana() {
        asignarProf = new Button("PROFESOR");
        asignarProf.setIcon(VaadinIcon.USERS.create());
        asignarProf.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        asignarEsts = new Button("ESTUDIANTES");
        asignarEsts.setIcon(VaadinIcon.DIPLOMA_SCROLL.create());
        asignarEsts.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        asignarProf.addClickListener(e-> asignarProfesor());
        asignarEsts.addClickListener(e-> asignarEstudiantes());
        asignarP = new Dialog();
        asignarP.add("Asignar profesor o estudiantes al curso");
        asignarP.add(new Div(asignarProf, asignarEsts));
        cursos = new Grid<>(Curso.class, false);
        listaCursos.add(new Curso("mat", "Matemáticas", Grado.Cuarto, "Martes a las 7"));
        listaCursos.add(new Curso("cien", "Ciencias", Grado.Primero, "Miércoles 8:00am-9:00am"));
        cursos.setItems(listaCursos);
        cursos.setColumns("ID", "nombre", "grado", "horario");
        cursos.addItemDoubleClickListener(e->{
            Curso curso = e.getItem();
            asignarP.open();
        });

        cursos.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        add(cursos);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private void asignarProfesor(){
        asignarDocente = new Dialog();
        asignarDocente.add("Asignar profesor al curso");
        docentes = new ComboBox<>("Docentes");
        docents = new ArrayList<>();
        docents.add("Luis");
        docents.add("Mario");
        docentes.setItems(docents);
        confirmar = new Button("ASIGNAR");
        confirmar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        asignarDocente.add(new Div(docentes, confirmar));
        asignarDocente.open();
        asignarP.close();
        docentes.addValueChangeListener(event->{
            if(!event.getValue().isEmpty()){
                confirmar.addClickListener(ev->{
                    Notification.show("Docente asignado con éxito!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    asignarDocente.close();
                    asignarP.open();
                });
            }
        });
    }

    private void asignarEstudiantes(){
        asignarEstudiantes = new Dialog();
        asignarEstudiantes.add("Asignar estudiantes al curso");
        estudiantes = new MultiSelectListBox<>();
        docents = new ArrayList<>();
        docents.add("Crystel");
        docents.add("Juan");
        docents.add("Manchas");
        estudiantes.setItems(docents);
        confirmar = new Button("ASIGNAR");
        confirmar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        asignarEstudiantes.add(new Div(estudiantes, confirmar));
        asignarEstudiantes.open();
        asignarP.close();
        estudiantes.addValueChangeListener(event->{
            if(!event.getValue().isEmpty()){
                confirmar.addClickListener(ev->{
                    Notification.show("Estudiantes asignados con éxito!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    asignarEstudiantes.close();
                    asignarP.open();
                });
            }
        });
    }

}
