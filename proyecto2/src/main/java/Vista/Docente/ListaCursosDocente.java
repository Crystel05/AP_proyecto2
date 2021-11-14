package Vista.Docente;

import Controlador.ControladorProfesor;
import Modelo.Curso;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Cursos docente")
@Route(value = "docenteCursos", layout = MenuDocente.class)
public class ListaCursosDocente extends VerticalLayout {

    private Grid<Curso> cursos;
    private ControladorProfesor profesor = ControladorProfesor.getInstance();
    private List<Curso> listaCursos = new ArrayList<>();

    public ListaCursosDocente() {
        addClassName("lista-cursos-docente-view");
        ventana();
    }

    private void ventana() {
        ArrayList<Curso> cursosLista = profesor.listaCursosProfesor();
        listaCursos.addAll(cursosLista);
        cursos = new Grid<>(Curso.class, false);
        cursos.setItems(listaCursos);
        cursos.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        cursos.setColumns("ID", "nombre", "grado", "horario");
        add(cursos);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
