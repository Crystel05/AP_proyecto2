package Controlador;

import Modelo.Curso;
import Modelo.Estudiante;
import Modelo.Grado;
import Modelo.Usuario;
import elemental.json.Json;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladorProfesor {

    private static ControladorProfesor controladorProfesor;
    private Controlador controlador = Controlador.getInstance();
    private Usuario profesorActual;
    private ArrayList<Curso> cursosActuales = new ArrayList<>();
    private DummyMethods dummyMethods = new DummyMethods();

    public static ControladorProfesor getInstance(){
        if (controladorProfesor == null){
            controladorProfesor = new ControladorProfesor();
        }
        return controladorProfesor;
    }

    public ControladorProfesor() {}

    public Usuario getProfesorActual() {
        return profesorActual;
    }

    public ArrayList<Curso> getCursosActuales() {
        return cursosActuales;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public ArrayList<Curso> listaCursosProfesor(){
        ArrayList<Curso> cursos = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/cursos/profesor/" + controlador.getUsuarioActual().getCorreo();
        profesorActual = controlador.getUsuarioActual();
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                for (Object o : array) {
                    JSONObject data = (JSONObject) o;
                    Curso c = controlador.detallesCurso(data.get("codigo").toString(), data.get("clase").toString());
                    cursos.add(c);
                }
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        this.cursosActuales = cursos;
        return cursos;
    }

    public boolean enviarNoticia(String cod, String clase, String titulo, String cont){
        String restoLink = transformarLink(cod, clase, null, titulo, cont);
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/nuevaNoticia/" + restoLink;

        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) array.get(0);
                if (data.get("insertarnoticia").toString().equals("0")){
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean enviarTarea(String cod, String clase, String codTarea, String titulo, String cont){
        String restoLink = transformarLink(cod, clase,codTarea ,titulo, cont);
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/nuevaTarea/" + restoLink;
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) array.get(0);
                return data.get("insertartarea").toString().equals("0");
            }else {
                return false;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Estudiante> estudiantesCurso(String cod, String clase){
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/estudiantesCur/" + cod + "/" + clase;

        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                for (Object o: array){
                    JSONObject data = (JSONObject) o;
                    estudiantes.add(detalles(data.get("cedula").toString()));
                }
            }else {
                return null;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        return estudiantes;
    }

    private String transformarLink(String cod, String clase, String codTarea,String titulo, String contenido) {
        String tit = titulo.replace(" ", "_");
        String cont = contenido.replace(" ", "_");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String fechaActual = dtf.format(now);
        if (codTarea != null){
            return cod + "/" + clase + "/"+ codTarea + "/" + tit + "/" +cont + "/" + fechaActual;
        }else{
            return cod + "/" + clase + "/" + tit + "/" +cont + "/" + fechaActual;
        }

    }

    private Estudiante detalles(String ced){
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/estudiantesCed/" + ced;

        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) array.get(0);
                return new Estudiante(data.get("nombre").toString() + " " +data.get("apellido").toString(), data.get("correo").toString(), ced, dummyMethods.convertirGradoBD(data.get("clase").toString()));
            }else {
                return null;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Curso> cursosEstudiante(String ced){
        ArrayList<Curso> cursos = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/cursos/estudiante/" + ced;
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                for (Object o : array) {
                    JSONObject data = (JSONObject) o;
                    Curso c = new Curso(data.get("codigo").toString(), data.get("nombre").toString());
                    cursos.add(c);
                }
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        this.cursosActuales = cursos;
        return cursos;
    }

    public static void main(String[] args){
        ControladorProfesor controladorProfesor = ControladorProfesor.getInstance();
        System.out.println(controladorProfesor.estudiantesCurso("soc", "3"));
//        System.out.println(controladorProfesor.detalles("4684184318"));
    }
}
