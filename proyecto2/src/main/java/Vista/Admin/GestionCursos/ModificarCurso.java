package Vista.Admin.GestionCursos;


import Controlador.Controlador;
import Modelo.Curso;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.json.simple.JSONObject;

@PageTitle("Modificar curso")
@Route(value = "modCurso", layout = MenuAdmin.class)
public class ModificarCurso extends VerticalLayout {

    private H2 titulo;
    private TextField id;
    private TextField nombre;
    private ComboBox<Grado> grado;
    private ComboBox<Dia> dia;
    private TextField horaInicio;
    private TextField horaFin;
    private HorizontalLayout horario;
    private HorizontalLayout datos;
    private H3 horas;
    private Button modificar;
    Controlador controlador = Controlador.getInstance();

    public ModificarCurso() {
        ventana();
    }

    private void ventana() {
        datos = new HorizontalLayout();
        titulo = new H2("Modificar curso");
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
        horaInicio = new TextField("Hora de inicio");
        horaInicio.setWidth("300px");
        horaFin = new TextField("Hora de fin");
        horaFin.setWidth("300px");
        horario.add(horaInicio, horaFin);

        setValues();

        modificar = new Button("MODIFICAR CURSO");
        modificar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modificar.setIcon(VaadinIcon.EDIT.create());
        modificar.addClickListener(e->{
            modificar();
            Notification.show("Curso modificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });
        add(titulo, datos, horas, dia, horario, modificar);
        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }

    private void setValues(){
        JSONObject data = Controlador.getCursoInfo(controlador.getCurso().getID(), controlador.getCurso().getGrado().getClase());
        id.setValue((String) data.get("codigo"));
        nombre.setValue((String) data.get("nombre"));
        grado.setValue(Curso.getGradoEnum((String) data.get("clase")));
        dia.setValue(Curso.getDiaEnum((String) data.get("diaSemana")));
        horaInicio.setValue((String) data.get("horaInicio"));
        horaFin.setValue((String) data.get("horaFin"));
    }

    private void modificar(){
        String codViejo = controlador.getCurso().getID();
        String gradviejo = controlador.getCurso().getGrado().getClase();
        String nombre = this.nombre.getValue();
        String codigo = id.getValue();
        String diaSemana = dia.getValue().getDia();
        String horaInicio = this.horaInicio.getValue();
        String horaFin = this.horaFin.getValue();
        String gradoId = grado.getValue().getClase();


        if (Controlador.ModifyCurso(codViejo, gradviejo, nombre, codigo, diaSemana, horaInicio, horaFin, gradoId)){
            Notification.show("Curso modificado exitosamente!").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        }
        else{
            Notification.show("Curso no modificado : error en db").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }
}
