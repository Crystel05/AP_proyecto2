package Vista.Admin.GestionEstudiantes;

import Modelo.Grado;
import Vista.Admin.MenuAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Agregar estudiante")
@Route(value = "estudianteAdd", layout = MenuAdmin.class)
public class AgregarEstudiante extends VerticalLayout {

    private H2 titulo;
    private TextField nombre;
    private TextField cedula;
    private EmailField correo;
    private ComboBox<Grado> grado;
    private Button agregar;

    public AgregarEstudiante() {
        ventana();
    }

    private void ventana() {
        titulo = new H2("Agregar estudiante nuevo");
        nombre = new TextField("Nombre");
        nombre.setWidth("300px");
        cedula = new TextField("Cédula");
        cedula.setWidth("300px");
        correo = new EmailField("Correo");
        correo.setWidth("300px");
        grado = new ComboBox<>("Grado");
        grado.setItems(Grado.values());
        grado.setWidth("300px");
        agregar = new Button("AGREGAR NUEVO ESTUDIANTE");
        agregar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        agregar.setIcon(VaadinIcon.EXTERNAL_BROWSER.create());
        agregar.addClickListener(e->{
            //if de cosas bien
            Notification.show("Estudiante agregado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        add(titulo, nombre, cedula, correo, grado, agregar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
