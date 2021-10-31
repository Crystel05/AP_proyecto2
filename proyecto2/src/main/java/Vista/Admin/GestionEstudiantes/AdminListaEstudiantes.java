package Vista.Admin.GestionEstudiantes;


import Modelo.Docente;
import Modelo.Estudiante;
import Modelo.Grado;
import Vista.Admin.GestionDocentes.AgregarDocente;
import Vista.Admin.GestionDocentes.ModificarDocente;
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
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Estudiantes activos")
@Route(value = "estudiantesA", layout = MenuAdmin.class)
public class AdminListaEstudiantes extends VerticalLayout {

    private Grid<Estudiante> estudiantes;
    private Button agregarNuevo;
    private List<Estudiante> listaEstudiantes = new ArrayList<>();

    public AdminListaEstudiantes() {
        ventana();
    }

    private void ventana() {
        Dialog cursosD = new Dialog();
        //poner los de verdad
        String mensaje = new String("mat\t\tMamaticas\ncien\t\tCiencias"); //ver como hacer para que haga saltos de línea
        cursosD.add(mensaje);
        Dialog eliminar_editar = new Dialog();
        estudiantes = new Grid<>(Estudiante.class, false);
        listaEstudiantes.add(new Estudiante("Crystel Montero", "305290866", "crysvane05@gmail.com", Grado.Primero));
        listaEstudiantes.add(new Estudiante("Juan Perez", "554564546", "crysvane05@gmail.com", Grado.Segundo));
        estudiantes.setItems(listaEstudiantes);
        estudiantes.setColumns("nombre", "cedula", "correo", "grado");
        estudiantes.addColumn(new NativeButtonRenderer<>("ver cursos", e->{
            cursosD.open();
        }));
        eliminar_editar.add("¿Desea eliminar o editar el estudiante?");
        Button eliminar = new Button("ElIMINAR");
        eliminar.setIcon(VaadinIcon.CLOSE_SMALL.create());
        eliminar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button modificar = new Button("MODIFICAR");
        modificar.setIcon(VaadinIcon.EDIT.create());
        modificar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        eliminar_editar.add(new Div( modificar, eliminar));
        eliminar.addClickListener(e->{
            //Hacer cosas
            Notification.show("Estudiante eliminado con éxito").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            eliminar_editar.close();
        });
        modificar.addClickListener(e->{
            //enviar datos
            UI.getCurrent().navigate(ModificarEstudiante.class);
            eliminar_editar.close();
        });
        estudiantes.addItemDoubleClickListener(e->{
            Estudiante estudiante = e.getItem();
            eliminar_editar.open();
        });
        estudiantes.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        agregarNuevo = new Button("AGREGAR ESTUDIANTE");
        agregarNuevo.setIcon(VaadinIcon.ADD_DOCK.create());
        agregarNuevo.setIconAfterText(true);
        agregarNuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agregarNuevo.addClickListener(e-> UI.getCurrent().navigate(AgregarEstudiante.class));

        add(estudiantes, agregarNuevo);
        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
    }

}
