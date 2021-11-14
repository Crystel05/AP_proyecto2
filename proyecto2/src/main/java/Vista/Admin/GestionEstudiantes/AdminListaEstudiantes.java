package Vista.Admin.GestionEstudiantes;


import Controlador.Controlador;
import Modelo.Curso;
import Modelo.Docente;
import Modelo.Estudiante;
import Modelo.Grado;
import Vista.Admin.GestionDocentes.AgregarDocente;
import Vista.Admin.GestionDocentes.ModificarDocente;
import Vista.Admin.MenuAdmin;
import com.vaadin.flow.component.Html;
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
    Controlador controlador = Controlador.getInstance();

    public AdminListaEstudiantes() {
        ventana();
    }

    private void ventana() {
        Dialog cursosD = new Dialog();
        //poner los de verdad
        String mensaje = new String("mat\t\tMamaticas\ncien\t\tCiencias"); //ver como hacer para que haga saltos de línea

        Dialog eliminar_editar = new Dialog();
        estudiantes = new Grid<>(Estudiante.class, false);
        listaEstudiantes = Controlador.CargarEstudiantes();
        estudiantes.setItems(listaEstudiantes);
        estudiantes.setColumns("nombre", "cedula", "correo", "grado");
        NativeButtonRenderer verCursos = new NativeButtonRenderer<>("ver cursos");
        estudiantes.addColumn(verCursos);
        verCursos.addItemClickListener(e->{
            //primero dar dovbel click y luego ver cursos
            cursosD.removeAll();
            Grid<Curso> cursosEsts = new Grid<>(Curso.class, false);
            cursosEsts.setColumns("ID", "nombre");
            if (controlador.getEstudiante() != null) {
                cursosEsts.setItems(Controlador.CursosXEstudiante(controlador.getEstudiante().getCedula()));
                cursosEsts.setWidth("400px");
                cursosEsts.setHeight("200px");
                cursosEsts.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
                cursosD.add(cursosEsts);
                cursosD.open();
            }
        });
        eliminar_editar.add("¿Desea eliminar o editar el estudiante?");
        Button eliminar = new Button("ElIMINAR");
        eliminar.setIcon(VaadinIcon.CLOSE_SMALL.create());
        eliminar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button modificar = new Button("MODIFICAR");
        modificar.setIcon(VaadinIcon.EDIT.create());
        modificar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        eliminar_editar.add(new Div( modificar, eliminar));
        eliminar.addClickListener(e->{
            if (Controlador.EliminarEstudiante(controlador.getEstudiante().getCedula())){
                Notification.show("Docente " + controlador.getEstudiante().getNombre() + " eliminado con éxito").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                listaEstudiantes = Controlador.CargarEstudiantes();
                estudiantes.setItems(listaEstudiantes);
            } else{
                Notification.show("Docente no eliminado").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            Notification.show("Estudiante eliminado con éxito").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            eliminar_editar.close();
        });
        modificar.addClickListener(e->{
            //enviar datos
            UI.getCurrent().navigate(ModificarEstudiante.class);
            eliminar_editar.close();
        });
        estudiantes.addItemDoubleClickListener(e->{
            controlador.setEstudiante(e.getItem());
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

    private String cargarCursosXEstudiante(){
        String cursosStr = "";
        System.out.println("tomar cursos");
        if (controlador.getEstudiante() != null){
            List<Curso> cursos = Controlador.CursosXEstudiante(controlador.getEstudiante().getCedula());
            for (Curso curso: cursos) {
                cursosStr += curso.getNombre() + "\t" + curso.getGrado().getClase() + "\n";
            }
        }
        else{
            System.out.println("no entra");
        }
        return cursosStr;
    }

}
