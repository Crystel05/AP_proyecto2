package Vista.Admin.GestionCursos;


import Modelo.Dia;
import Modelo.Grado;
import Vista.Admin.MenuAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Agregar Curso")
@Route(value = "cursoAdd", layout = MenuAdmin.class)
public class AgregarCurso extends VerticalLayout {

    private H2 titulo;
    private TextField id;
    private TextField nombre;
    private ComboBox<Grado> grado;
    private ComboBox<Dia> dia;
    private TimePicker horaInicio;
    private TimePicker horaFin;
    private HorizontalLayout horario;
    private HorizontalLayout datos;
    private H3 horas;
    private Button agregar;

    public AgregarCurso() {
        ventana();
    }

    private void ventana() {
        datos = new HorizontalLayout();
        titulo = new H2("Agregar curso nuevo");
        id = new TextField("ID");
        id.setWidth("300px");
        nombre = new TextField("Nombre");
        nombre.setWidth("300px");
        grado = new ComboBox<>("Grado");
        grado.setItems(Grado.values());
        grado.setWidth("300px");
        datos.add(id, nombre, grado);

        horas = new H3("Horario");
        dia = new ComboBox<>("Día");
        dia.setItems(Dia.values());
        dia.setWidth("300px");

        horario = new HorizontalLayout();
        horaInicio = new TimePicker("Hora de inicio");
        horaInicio.setWidth("300px");
        horaFin = new TimePicker("Hora de fin");
        horaFin.setWidth("300px");
        horario.add(horaInicio, horaFin);

        agregar = new Button("AGREGAR NUEVO CURSO");
        agregar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agregar.setIcon(VaadinIcon.EXTERNAL_BROWSER.create());
        agregar.addClickListener(e->{
            //if de cosas bien
            Notification.show("Curso agregado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        add(titulo, datos, horas, dia, horario, agregar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}