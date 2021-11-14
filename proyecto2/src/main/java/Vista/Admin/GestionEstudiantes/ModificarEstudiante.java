package Vista.Admin.GestionEstudiantes;

import Controlador.Controlador;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Modificar estudiante")
@Route(value = "estudianteMod", layout = MenuAdmin.class)
public class ModificarEstudiante extends VerticalLayout {

    private H2 titulo;
    private TextField nombre;
    private TextField cedula;
    private TextField correo;
    private ComboBox<Grado> grado;
    private Button modificar;
    Controlador controlador = Controlador.getInstance();

    public ModificarEstudiante() {
        ventana();
    }

    private void ventana() {
        titulo = new H2("Modificar estudiante");
        nombre = new TextField("Nombre y apellido");
        nombre.setWidth("300px");
        cedula = new TextField("Cédula");
        cedula.setWidth("300px");
        correo = new TextField("Correo");
        correo.setWidth("300px");
        grado = new ComboBox<>("Grado");
        grado.setItems(Grado.values());
        grado.setWidth("300px");

        setValues();

        modificar = new Button("MODIFICAR ESTUDIANTE");
        modificar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modificar.setIcon(VaadinIcon.EXTERNAL_BROWSER.create());
        modificar.addClickListener(e->{
            modificar();
            Notification.show("Estudiante modificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        add(titulo, nombre, cedula, correo, grado, modificar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private void setValues(){
        if (controlador.getEstudiante().getNombre() != null){
            nombre.setValue(controlador.getEstudiante().getNombre());
        }
        if (controlador.getEstudiante().getCedula() != null){
            cedula.setValue(controlador.getEstudiante().getCedula());
        }
        if (controlador.getEstudiante().getCorreo() != null){
            correo.setValue(controlador.getEstudiante().getCorreo());
        }
        if (controlador.getEstudiante().getGrado() != null){
            grado.setValue(controlador.getEstudiante().getGrado());
        }
    }

    private void modificar(){
        String stringViejos = controlador.getEstudiante().getNombre();
        String[] parts = stringViejos.split(" ");
        String nombviejo = parts[0];
        String apeviejo = parts[1];
        String cedula = this.cedula.getValue();
        String stringNuevo = nombre.getValue();
        String[] partsNuevo = stringNuevo.split(" ");
        String nombre = partsNuevo[0];
        String apellido = partsNuevo[1];
        String correo = this.correo.getValue();
        String contra = controlador.getEstudiante().getContrasenna();
        String grado = this.grado.getValue().getClase();

        if (Controlador.ModificarEstudiante(nombviejo, apeviejo, cedula, nombre, correo, contra, apellido, grado)){
            Notification.show("Curso modificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }
        else{
            Notification.show("Curso no modificado : error en db").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }

}
