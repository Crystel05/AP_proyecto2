package Controlador;

import Modelo.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ControladorEstudiante {

    private static ControladorEstudiante controladorEstudiante;
    private Controlador controlador = Controlador.getInstance();
    private ArrayList<Curso> cursosActuales;
    private Usuario estudianteActual = null;
    private ControladorEstudiante() {}

    public static ControladorEstudiante getInstance(){
        if (controladorEstudiante == null){
            controladorEstudiante = new ControladorEstudiante();
        }
        return controladorEstudiante;
    }

    public Usuario getEstudianteActual() {
        return estudianteActual;
    }

    public ArrayList<Curso> getCursosActuales() {
        return cursosActuales;
    }

    public Controlador getControlador() {
        return controlador;
    }

    public ArrayList<Curso> cursosEstudiante(){
        ArrayList<Curso> cursos = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/cursos/estudiante/" + controlador.getUsuarioActual().getCedula();
        estudianteActual = controlador.getUsuarioActual();
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

    public ArrayList<Tarea_Noticia> tareas(String codigo, String clase){
        ArrayList<Tarea_Noticia> tareas = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/tareas/"+ codigo + "/" + clase;
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
                    String titulo = data.get("titulo").toString().replace("_", " ");
                    String contenido = data.get("descripcion").toString().replace("_", " ");
                    Tarea_Noticia tarea = new Tarea_Noticia(titulo, contenido);
                    tareas.add(tarea);
                }
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        return tareas;
    }

    public ArrayList<Tarea_Noticia> noticias(String id, String grado) {
        ArrayList<Tarea_Noticia> noticias = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/noticias/"+ id + "/" + grado;
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
                    String titulo = data.get("titulo").toString().replace("_", " ");
                    String contenido = data.get("descripcion").toString().replace("_", " ");
                    Tarea_Noticia noticia = new Tarea_Noticia(titulo, contenido);
                    noticias.add(noticia);
                }
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        System.out.println("Cantidad: " + noticias.size());
        return noticias;
    }

    private String cedulaProfesor(String cod, String clase){
        String cedula = "";
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/CedPorCurso/"+ cod + "/" + clase;
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
                cedula = (String) data.get("obtenercedprof");
                if (cedula.equals("null")){
                    return null;
                }
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        return cedula;
    }

    public Docente detallesProfesor(String cod, String clase){
        Docente docente = new Docente();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/profesores/"+ cedulaProfesor(cod, clase);
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
                docente.setCalificacion(Float.parseFloat(data.get("calificacion").toString()));
                docente.setCedula(data.get("cedula").toString());
                docente.setNombre(data.get("nombre") + " " + data.get("apellido"));
                docente.setCorreo(data.get("correo").toString());
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        return docente;
    }

    public boolean calificarDocente(String ced, int nota){
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/votarNota/"+ ced + "/"+ nota;
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

            }else{
                return false;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void main(String[] args){
//        ControladorEstudiante estudiante = ControladorEstudiante.getInstance();
//        ArrayList<Mensaje> mes = estudiante.listaMensajesCurso("biooo", "4");
//        for (Mensaje m: mes){
//            System.out.println(m.getUsuario().getNombre());
//            System.out.println(m.getContenido());
//        }
        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(1); ints.add(2); ints.add(3);
        Collections.reverse(ints);

        for (Integer i: ints){
            System.out.println(i);
        }
    }

}
