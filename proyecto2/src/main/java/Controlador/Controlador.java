package Controlador;

import Modelo.Curso;
import Modelo.Docente;
import Modelo.Estudiante;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.apache.catalina.util.ToStringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInput;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Controlador {

    private static Controlador controlador;
    private static String path = "https://nodejsclusters-57268-0.cloudclusters.net";
    private Curso curso = new Curso();
    private Docente docente;
    private Estudiante estudiante;

    public Controlador(){}

    public static Controlador getInstance(){
        if(controlador == null){
            controlador = new Controlador();
        }
        return controlador;
    }

    public Docente getDocente(){
        return this.docente;
    }

    public void setDocente(Docente docente){
        this.docente = docente;
    }

    public Curso getCurso(){
        return this.curso;
    }

    public void  setCurso(Curso curso){
        this.curso = curso;
    }

    public Estudiante getEstudiante(){
        return this.estudiante;
    }

    public void  setEstudiante(Estudiante estudiante){
        this.estudiante = estudiante;
    }

    //region Password

    public static String createPassword(){
        String str = "";
        int min = 8;
        int max = 14;

        //Generate random int value from 50 to 100
        System.out.println("Random value in int from "+min+" to "+max+ ":");
        int lenPassword = (int)Math.floor(Math.random()*(max-min+1)+min);
        for (int i = 0; i < lenPassword; i++) {
            str += getRandomLetter();
        }

        return str;
    }

    private static String getRandomLetter(){
        String letter = "";
        Random r = new Random();
        //increase 26 to add uppercase and numbers
        char c = (char)(r.nextInt(26) + 'a');//97
        return String.valueOf(c);
    }

    //endregion Password

    //region Cursos conection
    public static boolean DeleteCurso(String cod, String grad) {
        String get = path + "/elimCurso";
        get += "/" + cod;
        get += "/" + grad;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));

                return true;
            }
            System.out.println("noooo");

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    public static boolean ModifyCurso(String codviejo, String gradviejo, String nombre, String codigo, String diaSemana
            , String horaInicio, String horaFin, String gradoId){
        String get = path + "/updateCurso";
        get += "/" + codviejo;
        get += "/" + gradviejo;
        get += "/" + nombre;
        get += "/" + codigo;
        get += "/" + diaSemana;
        get += "/" + horaInicio;
        get += "/" + horaFin;
        get += "/" + gradoId;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) json.get(0);
                System.out.println(data);
                return true;
            }
            System.out.println("noooo");

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    public static List<Curso> loadCursos(){
        String get = path + "/cursos";
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                List<Curso> cursos = createCursos(json);
                return addHorario (cursos);

            }

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
    
    private static List<Curso> addHorario (List<Curso> cursos){
        List<Curso> nuevos = new ArrayList<Curso>();
        for (Curso curso: cursos) {
            Curso curso2 = new Curso();
            JSONObject data = getCursoInfo(curso.getID(), curso.getGrado().getClase());
            if (! (data == null)){
                curso2.setID((String) data.get("codigo"));
                curso2.setNombre((String) data.get("nombre"));
                curso2.setGrado((String) data.get("clase"));
                String horario = data.get("diaSemana") + " : " +  data.get("horaInicio") + " - " +  data.get("horaFin");
                curso2.setHorario(horario);
                nuevos.add(curso2);
            } else{
                System.out.println("es null : " + curso.getID() + " " + curso.getGrado().getClase());
            }
        }
        return nuevos;
    }

    public static JSONObject getCursoInfo(String cod, String clase){

        String get = path + "/cursos/info";
        get += "/" + cod;
        get += "/" + clase;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                JSONObject data = (JSONObject) json.get(0);
                System.out.println(data);

                return data;

            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    private static List<Curso> createCursos(JSONArray jsonArray){
        List<Curso> cursos = new ArrayList<>();
        for (Object data : jsonArray) {
            JSONObject jsonObject = (JSONObject) data;
            Curso curso = new Curso();
            curso.setID((String) jsonObject.get("codigo"));
            curso.setNombre((String) jsonObject.get("nombre"));
            curso.setGrado((String) jsonObject.get("clase"));
            cursos.add(curso);
        }
        return cursos;
    }

    public static boolean addCurso(String codigo, String nombre, String gradoId, String diaSemana, String horaInicio, String horaFin){
        String get = path + "/nuevoCurso";
        get += "/" + codigo;
        get += "/" + nombre;
        get += "/" + gradoId;
        get += "/" + diaSemana;
        get += "/" + horaInicio;
        get += "/" + horaFin;
        System.out.println(get);
        try {
            System.out.println("A");
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            System.out.println("b");
            int responseCode = connection.getResponseCode();
            System.out.println("C");
            if (responseCode == 200) {
                System.out.println("D");
                StringBuilder info = new StringBuilder();
                System.out.println("E");
                Scanner scanner;
                System.out.println("F");
                scanner = new Scanner(url.openStream());
                System.out.println("G");
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(array);
                JSONObject data = (JSONObject) array.get(0);
                System.out.println(data);
                return true;
            }
            System.out.println("noooo");

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }
    //endregion

    //region Docentes

    //region CargarDocentes
    public static List<Docente> CargarDocentes(){
        String get = path + "/profesores";
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                List<Docente> docentes = createDocentes(json);
                return addDatosDocentes (docentes);

            }

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    private static List<Docente> createDocentes(JSONArray jsonArray){
        List<Docente> docentes = new ArrayList<>();
        for (Object data : jsonArray) {
            JSONObject jsonObject = (JSONObject) data;
            Docente docente = new Docente();
            String nombreCompleto = jsonObject.get("nombre") + " " + jsonObject.get("apellido");
            docente.setNombre(nombreCompleto);
            docente.setCedula((String) jsonObject.get("cedula"));
            docentes.add(docente);
        }
        return docentes;
    }

    private static List<Docente> addDatosDocentes(List<Docente> docentes) {

        for (Docente docente : docentes) {

            JSONObject data = getDocenteInfo(docente.getCedula());
            System.out.println(data);
            if (!(data == null)) {
                docente.setCorreo((String) data.get("correo"));
                docente.setContrasenna((String) data.get("contrasenna"));
                Object obj = data.get("calificacion");
                if (obj instanceof Long){
                    long calificacion = (long) data.get("calificacion");
                    docente.setCalificacion((float) calificacion);
                }  else if (obj instanceof  Double){
                    double calificacion = (double) data.get("calificacion");
                    docente.setCalificacion((float) calificacion);
                }
            } else {
                System.out.println("es null : " + docente.getCedula() + " " + docente.getNombre());
            }
        }
        return docentes;
    }

    private static JSONObject getDocenteInfo (String cedula){
        String get = path + "/profesores";
        get += "/" + cedula;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                JSONObject data = (JSONObject) json.get(0);
                System.out.println(data);

                return data;

            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    //endregion CargarDocentes

    //region ModificarDocentes
    public static boolean ModificarDocentes(String cedvieja, String cedula, String nombre, String correo, String contra, String apellido){
        String get = path + "/updateDocente";
        get += "/" + cedvieja;
        get += "/" + cedula;
        get += "/" + nombre;
        get += "/" + correo;
        get += "/" + contra;
        get += "/" + apellido;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) json.get(0);
                //String todoBien = (String) data.get("actualizardocente");
                System.out.println(data);
                return true;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    //endregion ModificarDocentes

    //region AgregarDocente
    public static boolean AgregarDocente(String cedula, String nombre, String correo, String contra, String apellido){
        String get = path + "/nuevoDocente";
        get += "/" + cedula;
        get += "/" + nombre;
        get += "/" + correo;
        get += "/" + contra;
        get += "/" + apellido;
        System.out.println(get);
        try {
            System.out.println("A");
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            System.out.println("b");
            int responseCode = connection.getResponseCode();
            System.out.println("C");
            if (responseCode == 200) {
                System.out.println("D");
                StringBuilder info = new StringBuilder();
                System.out.println("E");
                Scanner scanner;
                System.out.println("F");
                scanner = new Scanner(url.openStream());
                System.out.println("G");
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                JSONObject data = (JSONObject) json.get(0);
                //String todoBien = (String) data.get("insertardocente");
                System.out.println(data);
                return true;
            }
            System.out.println("noooo");

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    //endregion AgregarDocente

    //region EliminarDocente
    public static boolean EliminarDocente(String cedula) {
        String get = path + "/elimDocente";
        get += "/" + cedula;
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                JSONObject data = (JSONObject) json.get(0);
                System.out.println(data);
                return true;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    //endregion

    //region CursosXProfesor


    public static List<Curso> CursosXProfesor(  String correo){
        String get = path + "/cursos/profesor";
        get += "/" + correo;
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(jsonArray);
                return createCursos(jsonArray);

            }

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    //endregion CursosXProfesor

    //endregion Docentes

    //region Estudiantes

    //region CargarEstudiantes
    public static List<Estudiante> CargarEstudiantes(){
        String get = path + "/estudiantes";
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                List<Estudiante> estudiantes = createEstudiante(json);
                return addDatosEstudiantes (estudiantes);

            }

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    private static List<Estudiante> createEstudiante(JSONArray jsonArray){
        List<Estudiante> estudiantes = new ArrayList<>();
        for (Object data : jsonArray) {
            JSONObject jsonObject = (JSONObject) data;
            Estudiante estudiante = new Estudiante();
            String nombreCompleto = jsonObject.get("nombre") + " " + jsonObject.get("apellido");
            estudiante.setNombre(nombreCompleto);
            estudiante.setGrado((String) jsonObject.get("clase"));
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }

    private static List<Estudiante> addDatosEstudiantes(List<Estudiante> estudiantes) {

        for (Estudiante estudiante : estudiantes) {
            String string = estudiante.getNombre();
            String[] parts = string.split(" ");
            String nombre = parts[0];
            String apellido = parts[1];
            JSONObject data = getEstudianteInfo(nombre, apellido);
            if (!(data == null)) {
                estudiante.setCedula((String) data.get("cedula"));
                estudiante.setContrasenna((String) data.get("contrasenna"));
                estudiante.setCorreo((String) data.get("correo"));
            } else {
                System.out.println("es null : " + estudiante.getCedula() + " " + estudiante.getNombre());
            }
        }
        return estudiantes;
    }

    private static JSONObject getEstudianteInfo (String nombre, String apellido){
        String get = path + "/estudiantes";
        get += "/" + nombre;
        get += "/" + apellido;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                JSONObject data = (JSONObject) json.get(0);
                System.out.println(data);

                return data;

            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    //endregion CargarEstudiantes

    //region AgregarEstudiante

    public static boolean AgregarEstudiante(String cedula, String nombre, String correo, String contra, String apellido, String grado){
        String get = path + "/nuevoAlumno";
        get += "/" + cedula;
        get += "/" + nombre;
        get += "/" + correo;
        get += "/" + contra;
        get += "/" + apellido;
        get += "/" + grado;
        System.out.println(get);
        try {
            System.out.println("A");
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            System.out.println("b");
            int responseCode = connection.getResponseCode();
            System.out.println("C");
            if (responseCode == 200) {
                System.out.println("D");
                StringBuilder info = new StringBuilder();
                System.out.println("E");
                Scanner scanner;
                System.out.println("F");
                scanner = new Scanner(url.openStream());
                System.out.println("G");
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                JSONObject data = (JSONObject) json.get(0);
                //String todoBien = (String) data.get("insertaralumno");
                System.out.println(data);
                return true;
            }
            System.out.println("noooo");

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }
    //endregion AgregarEstudiante

    //region ModificarEstudiante

    public static boolean ModificarEstudiante(String nombviejo, String apeviejo, String cedula, String nombre, String correo, String contra, String apellido, String grado){
        String get = path + "/updateAlumno";
        get += "/" + nombviejo;
        get += "/" + apeviejo;
        get += "/" + cedula;
        get += "/" + nombre;
        get += "/" + correo;
        get += "/" + contra;
        get += "/" + apellido;
        get += "/" + grado;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) json.get(0);
                //String todoBien = (String) data.get("actualizaralumno");
                System.out.println(data);
                return true;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }

    //endregion ModificarEstudiante

    //region EliminarEstudiante
    public static boolean EliminarEstudiante(String cedula) {
        String get = path + "/elimAlumno";
        get += "/" + cedula;
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(json);
                JSONObject data = (JSONObject) json.get(0);
                //String todoBien = (String) data.get("eliminaralumno");
                System.out.println(data);
                return true;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;
    }


    //endregion EliminarEstudiante

    //region CursosXEstudiante

    public static List<Curso> CursosXEstudiante(  String cedula){
        String get = path + "/cursos/estudiante";
        get += "/" + cedula;
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray jsonArray = (JSONArray) parser.parse(String.valueOf(info));
                System.out.println(jsonArray);
                return createCursos(jsonArray);

            }

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }

    //endregion CursosXEstudiante

    //endregion Estudiantes

    //region AsignacionCursos

    //region AsignarDocentes

    public static boolean  AsignarDocentes (String cedula, String codigo, String grado){
        String get = path + "/asignarProfe";
        get += "/" + cedula;
        get += "/" + codigo;
        get += "/" + grado;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) json.get(0);
                //String todoBien = (String) data.get("asignarprofe");
                System.out.println(data);
                return true;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;

    }

    //endregion AsignarDocentes

    //region AsignarEstudiantes

    public static boolean  AsignarEstudiantes (String nombre, String apellido, String codigo, String grado){
        String get = path + "/asignarAlumno";
        get += "/" + nombre;
        get += "/" + apellido;
        get += "/" + codigo;
        get += "/" + grado;
        System.out.println(get);
        try {
            URL url = new URL(get);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                StringBuilder info = new StringBuilder();
                Scanner scanner;
                scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray json = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) json.get(0);
                //String todoBien = (String) data.get("asignaralumno");
                System.out.println(data);
                return true;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return false;
        }
        return false;

    }

    //endregion AsignarEstudiantes

    //endregion AsignacionCursos


    public TipoUsuario iniciarSesion(String correo, String contrasena) {

        String get = "https://nodejsclusters-57268-0.cloudclusters.net/logIn/"+correo;
        System.out.println(get);
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
                String contrasenaCorrecta = (String) data.get("contrasenna");
                boolean contrasennaAceptada = contrasenaCorrecta.equals(contrasena);

                if (contrasennaAceptada){
                    return verificarTipo(Integer.parseInt(data.get("ID").toString()));
                }
            }

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    private TipoUsuario verificarTipo (int ID){
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/tipoUsuario/"+ID;
        try{
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
                String tipo = data.get("tipousuario").toString();
                return TipoUsuario.valueOf(tipo);
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ParseException {
        Controlador controlador = Controlador.getInstance();
        System.out.println(controlador.iniciarSesion("titovare@gmail.com", "pelea"));
    }
}
