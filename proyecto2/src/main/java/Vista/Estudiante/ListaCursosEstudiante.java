package Vista.Estudiante;

import Modelo.Curso;
import Modelo.Grado;
import Vista.Docente.MenuDocente;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Lista cursos estudiante")
@Route(value = "cursosEstudiante", layout = MenuEstudiante.class)
public class ListaCursosEstudiante extends VerticalLayout {

    private Grid<Curso> cursos;
    private List<Curso> listaCursos = new ArrayList<>();
    public ListaCursosEstudiante() {
        ventana();
    }

    private void ventana() {
        cursos = new Grid<>(Curso.class, false);
        listaCursos.add(new Curso("mat", "Matemáticas", Grado.Cuarto, "Martes a las 7"));
        listaCursos.add(new Curso("cien", "Ciencias", Grado.Primero, "Miércoles 8:00am-9:00am"));
        cursos.setItems(listaCursos);
        cursos.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        cursos.setColumns("ID", "nombre", "grado", "horario");
        add(cursos);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}