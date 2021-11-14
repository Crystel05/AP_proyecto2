package Vista.Admin.GestionDocentes;

import Controlador.Controlador;
import Modelo.Curso;
import Modelo.Docente;
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

@PageTitle("Docentes activos")
@Route(value = "docentesA", layout = MenuAdmin.class)
public class AdminListaDocentes extends VerticalLayout {

    private Grid<Docente> docentes;
    private Button agregarNuevo;
    private List<Docente> listaDocentes = new ArrayList<>();
    Controlador controlador = Controlador.getInstance();

    public AdminListaDocentes() {
        addClassName("lista-docentes-view");
        ventana();
    }

    private void ventana() {
        Dialog cursosD = new Dialog();
        //poner los de verdad
        String mensaje = "mat\t\tMamaticas\ncien\t\tCiencias"; //ver como hacer para que haga saltos de línea
        Dialog eliminar_editar = new Dialog();
        docentes = new Grid<>(Docente.class, false);

        listaDocentes = Controlador.CargarDocentes();
        //listaDocentes.add(new Docente("Luis Montero", "luis@correo.com", "333333333", (float) 1.6));
        //listaDocentes.add(new Docente("Carla Rodriguez", "carla@correo.com", "4565464", (float) 5));
        docentes.setItems(listaDocentes);
        docentes.setColumns("nombre", "cedula", "correo", "calificacion");

        NativeButtonRenderer verCursos = new NativeButtonRenderer<>("ver cursos");
        docentes.addColumn(verCursos);
        verCursos.addItemClickListener(e->{
            cursosD.removeAll();
            //primero dar dovbel click y luego ver cursos
            Grid<Curso> cursosEsts = new Grid<>(Curso.class, false);
            cursosEsts.setColumns("ID", "nombre");
            if (controlador.getDocente() != null) {
                cursosEsts.setItems(Controlador.CursosXProfesor(controlador.getDocente().getCorreo()));
                cursosEsts.setWidth("400px");
                cursosEsts.setHeight("200px");
                cursosEsts.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
                cursosD.add(cursosEsts);
                cursosD.open();
                cursosD.open();
            }

        });
        eliminar_editar.add("¿Desea eliminar o editar el docente?");
        Button eliminar = new Button("ElIMINAR");
        eliminar.setIcon(VaadinIcon.CLOSE_SMALL.create());
        eliminar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Button modificar = new Button("MODIFICAR");
        modificar.setIcon(VaadinIcon.EDIT.create());
        modificar.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        eliminar_editar.add(new Div( modificar, eliminar));
        eliminar.addClickListener(e->{
            if (Controlador.EliminarDocente(controlador.getDocente().getCedula())){
                Notification.show("Docente " + controlador.getDocente().getNombre() + " eliminado con éxito").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                listaDocentes = Controlador.CargarDocentes();
                docentes.setItems(listaDocentes);
            } else{
                Notification.show("Docente no eliminado").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
            Notification.show("Docente eliminado con éxito").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            eliminar_editar.close();
        });
        modificar.addClickListener(e->{
            //enviar datos
            UI.getCurrent().navigate(ModificarDocente.class);
            eliminar_editar.close();
        });
        docentes.addItemDoubleClickListener(e->{
            controlador.setDocente(e.getItem());
            eliminar_editar.open();
        });
        docentes.addThemeVariants(GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        agregarNuevo = new Button("AGREGAR DOCENTE");
        agregarNuevo.setIcon(VaadinIcon.ADD_DOCK.create());
        agregarNuevo.setIconAfterText(true);
        agregarNuevo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agregarNuevo.addClickListener(e-> UI.getCurrent().navigate(AgregarDocente.class));

        add(docentes, agregarNuevo);
        setSizeFull();
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
    }

    private String cargarCursosXProfesor(){
        String cursosStr = "";
        System.out.println("tomar cursos");
        if (controlador.getDocente() != null){
            List<Curso> cursos = Controlador.CursosXProfesor(controlador.getDocente().getCorreo());
            for (Curso curso: cursos) {
                cursosStr += curso.getNombre() + "\t" + curso.getGrado().getClase() + "\n";
            }
        }
        return cursosStr;
    }
}
