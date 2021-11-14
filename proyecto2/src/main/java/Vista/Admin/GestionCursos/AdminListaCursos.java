package Vista.Admin.GestionCursos;

import Controlador.Controlador;
import Modelo.Curso;
import Modelo.Grado;
import Vista.Admin.MenuAdmin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Cursos activos")
@Route(value = "activos", layout = MenuAdmin.class)

public class AdminListaCursos extends VerticalLayout {

    private Grid<Curso> cursos;
    private Button agregarNuevo;
    private List<Curso> listaCursos = new ArrayList<>();
    Controlador controlador = Controlador.getInstance();

    public AdminListaCursos() {
        ventana();
    }

    private void ventana() {
        Dialog eliminar_editar = new Dialog();
        addClassName("lista-cursos-view");
        cursos = new Grid<>(Curso.class, false);
        //listaCursos.add(new Curso("mat", "Matemáticas", Grado.Cuarto, "Martes a las 7"));
        //listaCursos.add(new Curso("cien", "Ciencias", Grado.Primero, "Miércoles 8:00am-9:00am"));

        listaCursos = loadCursos();
        cursos.setItems(listaCursos);
        cursos.setColumns("ID", "nombre", "grado", "horario");
        eliminar_editar.add("¿Desea eliminar o editar el curso?");
        Button eliminar = new Button("ElIMINAR");
        eliminar.setIcon(VaadinIcon.CLOSE_SMALL.create());
        eliminar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button modificar = new Button("MODIFICAR");
        modificar.setIcon(VaadinIcon.EDIT.create());
        modificar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        eliminar_editar.add(new Div( modificar, eliminar));
        eliminar.addClickListener(e->{
            if (Controlador.DeleteCurso(controlador.getCurso().getID(), controlador.getCurso().getGrado().getClase())){
                Notification.show("Curso eliminado con éxito").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                listaCursos = loadCursos();
                cursos.setItems(listaCursos);
            } else{
                Notification.show("Curso no eliminado").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            eliminar_editar.close();
        });
        modificar.addClickListener(e->{
            UI.getCurrent().navigate(ModificarCurso.class);
            eliminar_editar.close();
        });
        cursos.addItemDoubleClickListener(e->{
            controlador.getCurso().setGrado(e.getItem().getGrado());
            controlador.getCurso().setID(e.getItem().getID());
            eliminar_editar.open();
        });
        cursos.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        agregarNuevo = new Button("AGREGAR CURSO");
        agregarNuevo.setIcon(VaadinIcon.ADD_DOCK.create());
        agregarNuevo.setIconAfterText(true);
        agregarNuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agregarNuevo.addClickListener(e-> UI.getCurrent().navigate(AgregarCurso.class));

        add(cursos, agregarNuevo);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private List<Curso> loadCursos(){

        return Controlador.loadCursos();
    }

}
