package Vista.Admin.GestionDocentes;

import Vista.Admin.MenuAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Modificar docente")
@Route(value = "docenteMod", layout = MenuAdmin.class)
public class ModificarDocente extends VerticalLayout {

    private H2 titulo;
    private TextField nombre;
    private TextField cedula;
    private TextField correo;
    private Button modificar;

    public ModificarDocente() {
        ventana();
    }

    private void ventana() {
        titulo = new H2("Modificar docente");
        nombre = new TextField("Nombre");
        nombre.setWidth("300px");
        cedula = new TextField("Cédula");
        cedula.setWidth("300px");
        correo = new TextField("Correo");
        correo.setWidth("300px");
        modificar = new Button("MODIFICAR DOCENTE");
        modificar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modificar.setIcon(VaadinIcon.EXTERNAL_BROWSER.create());
        modificar.addClickListener(e->{
            //if de cosas bien
            Notification.show("Docente modificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        add(titulo, nombre, cedula, correo, modificar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
