package Vista;

import Controlador.Controlador;
import Controlador.TipoUsuario;
import Vista.Admin.MenuAdmin;
import Vista.Docente.MenuDocente;
import Vista.Estudiante.MenuEstudiante;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import javax.naming.ldap.Control;

@PageTitle("Inicio de sesión")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class LogIn extends VerticalLayout {

    private Controlador controlador = Controlador.getInstance();
    private H1 nombre;
    private H2 inicio;
    private EmailField correo;
    private PasswordField contrasena;
    private Button iniciar;

    public LogIn() {
        ventana();
    }

    private void ventana() {
        addClassName("Login-view");
        nombre = new H1("AULA VIRTUAL");
        inicio = new H2("Inicio de sesión");

        correo = new EmailField("Correo");
        correo.setWidth("400px");
        Component mail = VaadinIcon.ENVELOPE_O.create();
        correo.setPrefixComponent(mail);

        contrasena = new PasswordField("Contraseña");
        contrasena.setRevealButtonVisible(true);
        Component contra = VaadinIcon.LOCK.create();
        contrasena.setPrefixComponent(contra);
        contrasena.setWidth("400px");

        //borrar luego
//        correo.setValue("titovare@gmail.com");
//        contrasena.setValue("pelea");

        iniciar = new Button("INICIAR SESIÓN", VaadinIcon.UNLOCK.create());
        iniciar.setIconAfterText(true);
        iniciar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        iniciar.addClickListener(e-> iniciarSesion());
        add(nombre, inicio, correo, contrasena, iniciar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private void iniciarSesion() {

        TipoUsuario tipoUsuario = controlador.iniciarSesion(correo.getValue(), contrasena.getValue());
        if (tipoUsuario!=null) {
            switch (tipoUsuario) {
                case administrador:
                    UI.getCurrent().navigate(MenuAdmin.class);
                    break;
                case estudiante:
                    UI.getCurrent().navigate(MenuEstudiante.class);
                    break;
                case profesor:
                    UI.getCurrent().navigate(MenuDocente.class);
                    break;
            }
        }else{
            Notification.show("Usuario o contraseña inválidos, intente de nuevo", 5000, Notification.Position.MIDDLE).
                    addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }
}
