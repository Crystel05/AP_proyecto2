package Vista.Estudiante;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Ver profesor")
@Route(value = "verProfesor", layout = MenuEstudiante.class)
public class VerProfesor extends VerticalLayout {

    private Tabs cursos;
    private Integer calificacion;

    public VerProfesor() {
        ventana();
    }

    private void ventana() {
        cursos = new Tabs(new Tab("Curso mat"));
        cursos.setWidthFull();

        H4 detalles = new H4("Detalles del docente");
        Button calificarB = new Button("CALIFICAR");
        calificarB.setIcon(VaadinIcon.STAR.create());
        calificarB.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout nombreDets = new HorizontalLayout();
        H5 nombreD = new H5("Nombre");
        H5 nombre = new H5("Luis perez");
        nombreDets.add(nombreD, nombre);

        HorizontalLayout cedulaDets = new HorizontalLayout();
        H5 cedulaD = new H5("Cédula");
        H5 cedula = new H5("454546546");
        cedulaDets.add(cedulaD, cedula);

        HorizontalLayout correoDets = new HorizontalLayout();
        H5 correoD = new H5("Correo electrónico");
        H5 correo = new H5("correo@correo.com");
        correoDets.add(correoD, correo);

        HorizontalLayout califiacion = new HorizontalLayout();
        H5 calificacionPromedioD = new H5("Calificación promedio");
        H5 calificacionPromedio = new H5("5");
        califiacion.add(calificacionPromedioD, calificacionPromedio);

        cursos.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
        });

        H4 calificar = new H4("Calificar profesor");
        HorizontalLayout estrellas = new HorizontalLayout();
        Icon primeraEstrellaNM = new Icon(VaadinIcon.STAR_O);
        Icon primeraEstrellaSM = new Icon(VaadinIcon.STAR);

        Icon segundaEstrellaNM = new Icon(VaadinIcon.STAR_O);
        Icon segundaEstrellaSM = new Icon(VaadinIcon.STAR);

        Icon terceraEstrellaNM = new Icon(VaadinIcon.STAR_O);
        Icon terceraEstrellaSM = new Icon(VaadinIcon.STAR);

        Icon cuartaEstrellaNM = new Icon(VaadinIcon.STAR_O);
        Icon cuartaEstrellaSM = new Icon(VaadinIcon.STAR);

        Icon quintaEstrellaNM = new Icon(VaadinIcon.STAR_O);
        Icon quintaEstrellaSM = new Icon(VaadinIcon.STAR);

        primeraEstrellaSM.setVisible(false);
        primeraEstrellaNM.addClickListener(e->{
            primeraEstrellaNM.setVisible(false);
            primeraEstrellaSM.setVisible(true);
            calificar(1);
        });
        primeraEstrellaSM.addClickListener(e->{
            primeraEstrellaNM.setVisible(true);
            primeraEstrellaSM.setVisible(false);
            segundaEstrellaNM.setVisible(true);
            segundaEstrellaSM.setVisible(false);
            terceraEstrellaNM.setVisible(true);
            terceraEstrellaSM.setVisible(false);
            cuartaEstrellaNM.setVisible(true);
            cuartaEstrellaSM.setVisible(false);
            quintaEstrellaNM.setVisible(true);
            quintaEstrellaSM.setVisible(false);
            calificar(1);
        });

        segundaEstrellaSM.setVisible(false);
        segundaEstrellaNM.addClickListener(e-> {
            primeraEstrellaNM.setVisible(false);
            primeraEstrellaSM.setVisible(true);
            segundaEstrellaNM.setVisible(false);
            segundaEstrellaSM.setVisible(true);
            calificar(2);
        });
        segundaEstrellaSM.addClickListener(e->{
            segundaEstrellaNM.setVisible(true);
            segundaEstrellaSM.setVisible(false);
            terceraEstrellaNM.setVisible(true);
            terceraEstrellaSM.setVisible(false);
            cuartaEstrellaNM.setVisible(true);
            cuartaEstrellaSM.setVisible(false);
            quintaEstrellaNM.setVisible(true);
            quintaEstrellaSM.setVisible(false);
            calificar(2);
        });

        terceraEstrellaSM.setVisible(false);
        terceraEstrellaNM.addClickListener(e->{
            primeraEstrellaNM.setVisible(false);
            primeraEstrellaSM.setVisible(true);
            segundaEstrellaNM.setVisible(false);
            segundaEstrellaSM.setVisible(true);
            terceraEstrellaNM.setVisible(false);
            terceraEstrellaSM.setVisible(true);
            calificar(3);
        });

        terceraEstrellaSM.addClickListener(e->{
            terceraEstrellaNM.setVisible(true);
            terceraEstrellaSM.setVisible(false);
            cuartaEstrellaNM.setVisible(true);
            cuartaEstrellaSM.setVisible(false);
            quintaEstrellaNM.setVisible(true);
            quintaEstrellaSM.setVisible(false);
            calificar(3);
        });

        cuartaEstrellaSM.setVisible(false);
        cuartaEstrellaNM.addClickListener(e->{
            primeraEstrellaNM.setVisible(false);
            primeraEstrellaSM.setVisible(true);
            segundaEstrellaNM.setVisible(false);
            segundaEstrellaSM.setVisible(true);
            terceraEstrellaNM.setVisible(false);
            terceraEstrellaSM.setVisible(true);
            cuartaEstrellaNM.setVisible(false);
            cuartaEstrellaSM.setVisible(true);
            calificar(4);
        });

        cuartaEstrellaSM.addClickListener(e->{
            cuartaEstrellaNM.setVisible(true);
            cuartaEstrellaSM.setVisible(false);
            quintaEstrellaNM.setVisible(true);
            quintaEstrellaSM.setVisible(false);
            calificar(4);
        });

        quintaEstrellaSM.setVisible(false);
        quintaEstrellaNM.addClickListener(e->{
            primeraEstrellaNM.setVisible(false);
            primeraEstrellaSM.setVisible(true);
            segundaEstrellaNM.setVisible(false);
            segundaEstrellaSM.setVisible(true);
            terceraEstrellaNM.setVisible(false);
            terceraEstrellaSM.setVisible(true);
            cuartaEstrellaNM.setVisible(false);
            cuartaEstrellaSM.setVisible(true);
            quintaEstrellaNM.setVisible(false);
            quintaEstrellaSM.setVisible(true);
            calificar(5);
        });
        quintaEstrellaSM.addClickListener(e->{
            quintaEstrellaNM.setVisible(true);
            quintaEstrellaSM.setVisible(false);
            calificar(5);
        });

        estrellas.add(primeraEstrellaNM, primeraEstrellaSM, segundaEstrellaNM, segundaEstrellaSM, terceraEstrellaNM, terceraEstrellaSM,
                cuartaEstrellaNM, cuartaEstrellaSM, quintaEstrellaNM, quintaEstrellaSM);

        add(cursos, detalles, nombreDets, cedulaDets, correoDets, califiacion, calificar, estrellas, calificarB);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
    }

    private void calificar(int cant) {
        calificacion = cant;
    }
}
