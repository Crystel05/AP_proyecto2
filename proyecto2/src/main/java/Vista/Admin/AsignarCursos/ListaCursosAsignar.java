package Vista.Admin.AsignarCursos;

import Modelo.Curso;
import Controlador.Controlador;
import Modelo.Docente;
import Modelo.Estudiante;
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
    private List<String> docents = new ArrayList<>();
    private List<String> students = new ArrayList<>();
    private List<Docente> profesores;
    private List<Estudiante> estudiantesInstances;
    private Button confirmar;
    private ComboBox<String> estudiantes;
    Controlador controlador = Controlador.getInstance();


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
        listaCursos = Controlador.loadCursos();
        cursos.setItems(listaCursos);
        cursos.setColumns("ID", "nombre", "grado", "horario");
        cursos.addItemDoubleClickListener(e->{
            controlador.setCurso(e.getItem());
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
        profesores = Controlador.CargarDocentes();
        for (Docente docente: profesores) {
            String str = docente.getNombre() +"/"+ docente.getCedula();
            docents.add(str);
        }
        docentes.setItems(docents);
        confirmar = new Button("ASIGNAR");
        confirmar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        asignarDocente.add(new Div(docentes, confirmar));
        asignarDocente.open();
        asignarP.close();
        docentes.addValueChangeListener(event->{
            if(!event.getValue().isEmpty()){
                confirmar.addClickListener(ev->{
                    String[] parts = docentes.getValue().split("/");
                    String cedula = parts[1];
                    Controlador.AsignarDocentes(cedula, controlador.getCurso().getID(), controlador.getCurso().getGrado().getClase());
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
        estudiantes = new ComboBox<>("Estudiantes");
        students = new ArrayList<>();
        estudiantesInstances = Controlador.CargarEstudiantes();
        for (Estudiante estudiante: estudiantesInstances) {
            students.add(estudiante.getNombre());
        }
        estudiantes.setItems(students);
        confirmar = new Button("ASIGNAR");
        confirmar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        asignarEstudiantes.add(new Div(estudiantes, confirmar));
        asignarEstudiantes.open();
        asignarP.close();
        estudiantes.addValueChangeListener(event->{
            if(!event.getValue().isEmpty()){
                confirmar.addClickListener(ev->{
                    String[] parts = estudiantes.getValue().split(" ");
                    String nombre = parts[0];
                    String apellido = parts[1];

                    Controlador.AsignarEstudiantes(nombre, apellido, controlador.getCurso().getID(), controlador.getCurso().getGrado().getClase());
                    Notification.show("Estudiantes asignados con éxito!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    asignarEstudiantes.close();
                    asignarP.open();
                });
            }
        });
    }

}
