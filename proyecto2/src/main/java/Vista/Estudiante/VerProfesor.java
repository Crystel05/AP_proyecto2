package Vista.Estudiante;

import Controlador.ControladorEstudiante;
import Modelo.Curso;
import Modelo.Docente;
import Modelo.Grado;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
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
    private Integer calificacion = 0;
    private Docente docenteActual;
    private ControladorEstudiante estudiante = ControladorEstudiante.getInstance();

    public VerProfesor() {
        ventana();
    }

    private void ventana() {
        cursos = new Tabs();
        for (Curso c : estudiante.getCursosActuales()) {
            cursos.add(new Tab(c.getNombre()));
        }
        cursos.setWidthFull();
        cursos.setWidthFull();

        H4 detalles = new H4("Detalles del docente");
        Button calificarB = new Button("CALIFICAR");
        calificarB.setIcon(VaadinIcon.STAR.create());
        calificarB.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        calificarB.addClickListener(e->{
           if (calificacion>0 && docenteActual != null){
               boolean calificado = estudiante.calificarDocente(docenteActual.getCedula(), calificacion);
               if (calificado){
                   Notification.show("Docente calificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
               }else{
                   Notification.show("Error calificando docente").addThemeVariants(NotificationVariant.LUMO_ERROR);
               }
           }
        });

        HorizontalLayout nombreDets = new HorizontalLayout();
        H5 nombreD = new H5("Nombre");
        H5 nombre = new H5();
        nombreDets.add(nombreD, nombre);

        HorizontalLayout cedulaDets = new HorizontalLayout();
        H5 cedulaD = new H5("Cédula");
        H5 cedula = new H5();
        cedulaDets.add(cedulaD, cedula);

        HorizontalLayout correoDets = new HorizontalLayout();
        H5 correoD = new H5("Correo electrónico");
        H5 correo = new H5();
        correoDets.add(correoD, correo);

        HorizontalLayout califiacion = new HorizontalLayout();
        H5 calificacionPromedioD = new H5("Calificación promedio");
        H5 calificacionPromedio = new H5();
        califiacion.add(calificacionPromedioD, calificacionPromedio);

        cursos.addSelectedChangeListener(event -> {
            String channelName = event.getSelectedTab().getLabel();
            for (Curso curso : estudiante.getCursosActuales()){
                if (curso.getNombre().equals(channelName)){
                    Docente d = estudiante.detallesProfesor(curso.getID(), convertirGrado(curso.getGrado()));
                    docenteActual = d;
                    if (d != null) {
                        nombre.setText(d.getNombre());
                        cedula.setText(d.getCedula());
                        correo.setText(d.getCorreo());
                        calificacionPromedio.setText(String.valueOf(((int)d.getCalificacion())));
                    }
                }
            }
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

    private String convertirGrado(Grado grado) {
        String gra = "";
        switch (grado){
            case Primero:
                gra = "1";
                break;
            case Segundo:
                gra = "2";
                break;
            case Cuarto:
                gra = "4";
                break;
            case Quinto:
                gra = "5";
                break;
            case Sexto:
                gra = "6";
                break;
            case Septimo:
                gra = "7";
                break;
            case Preparatoria:
                gra = "prepa";
                break;
            case Undecimo:
                gra = "11";
                break;
            case Tercero:
                gra = "3";
                break;
        }
        return gra;
    }
}
