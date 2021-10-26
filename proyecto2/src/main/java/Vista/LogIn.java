package Vista.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Inicio de sesión")
@Route(value = "login", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)

public class LogIn extends VerticalLayout {

    private H1 nombre;
    private H2 inicio;
    private TextField correo;
    private PasswordField contrasena;
    private Button iniciar;

    public LogIn() {
        ventana();
    }

    private void ventana() {
        addClassName("Login-view");
        nombre = new H1("AULA VIRTUAL");
        inicio = new H2("Inicio de sesión");

        correo = new TextField("Correo");
        correo.setWidth("400px");
        Component mail = VaadinIcon.ENVELOPE_O.create();
        correo.setPrefixComponent(mail);

        contrasena = new PasswordField("Contraseña");
        contrasena.setRevealButtonVisible(true);
        Component contra = VaadinIcon.LOCK.create();
        contrasena.setPrefixComponent(contra);
        contrasena.setWidth("400px");

        iniciar = new Button("Iniciar sesión", VaadinIcon.UNLOCK.create());
        iniciar.setIconAfterText(true);
        iniciar.addClickListener(e->{
            UI.getCurrent().navigate(Menu.class);
        });
        add(nombre, inicio, correo, contrasena, iniciar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
