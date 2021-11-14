package Vista.Admin.GestionDocentes;

import Controlador.Controlador;
import Modelo.Curso;
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
import org.json.simple.JSONObject;

@PageTitle("Modificar docente")
@Route(value = "docenteMod", layout = MenuAdmin.class)
public class ModificarDocente extends VerticalLayout {

    private H2 titulo;
    private TextField nombre;
    private TextField cedula;
    private TextField correo;
    private Button modificar;
    Controlador controlador = Controlador.getInstance();

    public ModificarDocente() {
        ventana();
    }

    private void ventana() {
        titulo = new H2("Modificar docente");
        nombre = new TextField("Nombre y apellido");
        nombre.setWidth("300px");
        cedula = new TextField("Cédula");
        cedula.setWidth("300px");
        correo = new TextField("Correo");
        correo.setWidth("300px");
        modificar = new Button("MODIFICAR DOCENTE");

        setValues();

        modificar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modificar.setIcon(VaadinIcon.EXTERNAL_BROWSER.create());
        modificar.addClickListener(e->{
            modificar();
            Notification.show("Docente modificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        add(titulo, nombre, cedula, correo, modificar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private void setValues(){
        if (controlador.getDocente().getNombre() != null){
            nombre.setValue(controlador.getDocente().getNombre());
        }
        if (controlador.getDocente().getCedula() != null){
            cedula.setValue(controlador.getDocente().getCedula());
        }
        if (controlador.getDocente().getCorreo() != null){
            correo.setValue(controlador.getDocente().getCorreo());
        }
    }

    private void modificar(){
        String cedvieja = controlador.getDocente().getCedula();
        String cedula = this.cedula.getValue();

        String correo = this.correo.getValue();
        String contra = controlador.getDocente().getContrasenna();
        String string = this.nombre.getValue();
        String[] parts = string.split(" ");
        String nombre = parts[0];
        String apellido = parts[1];


        if (Controlador.ModificarDocentes(cedvieja, cedula, nombre, correo, contra, apellido)){
            Notification.show("Curso modificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }
        else{
            Notification.show("Curso no modificado : error en db").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }

}
