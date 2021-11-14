package Vista.Admin.GestionCursos;


import Modelo.Dia;
import Modelo.Grado;
import Controlador.Controlador;
import Modelo.SendMail;
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
            addCurso();
        });
        add(titulo, datos, horas, dia, horario, agregar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private void addCurso(){
        System.out.println("va a agregar el curso");
        //toma los valores de los textfield
        String codigoStr = id.getValue();//es el codigo
        String nombreStr = nombre.getValue();
        String gradoIdStr = grado.getValue().getClase();//se mapea a la tabla grado
        String diaSemanaStr = dia.getValue().toString();
        String horaInicioStr = horaInicio.getValue().toString();
        String horaFinStr = horaFin.getValue().toString();
        if(codigoStr.length() > 0 & nombreStr.length() > 0 &
            gradoIdStr.length() > 0 & diaSemanaStr.length() > 0 &
            horaInicioStr.length() > 0 & horaFinStr.length() > 0){
            System.out.println("datos correctos");
            nombreStr = nombreStr.replace(" ", "_");
            System.out.println( codigoStr + " " + nombreStr + " " +  gradoIdStr + " " +  diaSemanaStr + " " +  horaInicioStr + " " +  horaFinStr);
            if (Controlador.addCurso(codigoStr, nombreStr, gradoIdStr, diaSemanaStr, horaInicioStr, horaFinStr)){
                Notification.show("Curso agregado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
            else{
                Notification.show("Curso no agregado : error en db").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
        else{
            Notification.show("Curso no agregado : Datos incorrectos").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
