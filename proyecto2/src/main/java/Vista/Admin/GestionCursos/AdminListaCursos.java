package Vista.views.Admin;

import Modelo.Curso;
import Modelo.Grado;
import Vista.views.Menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Lista de cursos")
@Route(value = "cursos", layout = Menu.class)

public class AdminListaCursos extends VerticalLayout {

    private Grid<Curso> cursos;
    private Button agregarNuevo;
    private List<Curso> listaCursos = new ArrayList<>();

    public AdminListaCursos() {

        ventana();
    }

    private void ventana() {
        addClassName("lista-cursos-view");
        cursos = new Grid<>(Curso.class, false);
        listaCursos.add(new Curso("mat", "Matemáticas", Grado.cuarto, "Martes a las 7"));
        cursos.setItems(listaCursos);
        cursos.setColumns("ID", "nombre", "grado", "horario");
        cursos.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        agregarNuevo = new Button("Agregar curso");
        agregarNuevo.setIcon(VaadinIcon.ADD_DOCK.create());
        agregarNuevo.setIconAfterText(true);

        add(cursos, agregarNuevo);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }


}
