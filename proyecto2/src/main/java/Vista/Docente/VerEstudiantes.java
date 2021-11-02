package Vista.Docente;

import Modelo.Estudiante;
import Modelo.Grado;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
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

    public VerEstudiantes() {
        addClassName("ver-estudiantes-docente-view");
        ventana();
    }

    private void ventana() {
        cursos = new Tabs(new Tab("Curso mat"));
        cursos.setWidthFull();

        estudiantes = new Grid<>(Estudiante.class, false);
        estudiantes.setColumns("nombre", "cedula", "correo", "grado");
        cursosEst = new Dialog();
        List<Estudiante> estudianteList = new ArrayList<>();
        estudianteList.add(new Estudiante("Luis", "5654564", "correo@correo.com", Grado.Primero));
        estudiantes.setItems(estudianteList);
        estudiantes.addItemDoubleClickListener(e->{
            Estudiante estudiante = e.getItem();
            cursosEst.add("cien ciencias");
            cursosEst.open();
        });
        estudiantes.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        estudiantes.setWidthFull();
        estudiantes.setHeightFull();
        cursos.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
            //llenar la tabla con diferentes datos según el nombre
        });

        add(cursos, estudiantes);
        setSizeFull();
    }
}
