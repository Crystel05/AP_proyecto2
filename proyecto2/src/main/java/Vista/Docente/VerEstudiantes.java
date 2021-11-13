package Vista.Docente;

import Controlador.ControladorProfesor;
import Controlador.DummyMethods;
import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Grado;
import Modelo.Tarea_Noticia;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Estudiantes curso")
@Route(value = "estduiantesCurso", layout = MenuDocente.class)
public class VerEstudiantes extends VerticalLayout {

    private Tabs cursos;
    private Dialog cursosEst;
    private Grid<Estudiante> estudiantes;
    private ArrayList<Curso> cursosBD = new ArrayList<>();
    private ArrayList<Estudiante> estudiantesCurso = new ArrayList<>();
    private ControladorProfesor profesor = ControladorProfesor.getInstance();
    private DummyMethods dummyMethods = new DummyMethods();

    public VerEstudiantes() {
        addClassName("ver-estudiantes-docente-view");
        ventana();
    }

    private void ventana() {
        cursos = new Tabs();
        for (Curso c : profesor.getCursosActuales()){
            cursos.add(new Tab(c.getNombre()));
        }
        cursos.setWidthFull();

        estudiantes = new Grid<>(Estudiante.class, false);
        estudiantes.setColumns("nombre", "cedula", "correo", "grado");
        cursosEst = new Dialog();
        List<Estudiante> estudianteList = new ArrayList<>();

        estudiantes.setItems(estudianteList);
        estudiantes.addItemDoubleClickListener(e->{
            cursosEst.removeAll();
            Estudiante estudiante = e.getItem();
            Grid<Curso> cursoEst = new Grid<>(Curso.class, false);
            cursoEst.setColumns("ID", "nombre");
            List<Curso> cursosEstDB = new ArrayList<>(profesor.cursosEstudiante(estudiante.getCedula()));
            cursoEst.setItems(cursosEstDB);
            cursoEst.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
            cursosEst.add(cursoEst);
            cursoEst.setWidth("400px");
            cursoEst.setHeight("200px");
            cursosEst.open();
        });
        estudiantes.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        estudiantes.setWidthFull();
        estudiantes.setHeightFull();
        cursos.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
            for (Curso curso : profesor.getCursosActuales()){
                if (curso.getNombre().equals(channelName)){
                    estudiantesCurso = profesor.estudiantesCurso(curso.getID(), dummyMethods.convertirGrado(curso.getGrado()));
                    estudianteList.addAll(estudiantesCurso);
                    estudiantes.setItems(estudianteList);
                    System.out.println(estudiantesCurso.size());
                }
            }
        });

        add(cursos, estudiantes);
        setSizeFull();
    }
}
