package Controlador;

import Modelo.Curso;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladorEstudiante {

    private static ControladorEstudiante controladorEstudiante;
    private Controlador controlador = Controlador.getInstance();
    private String get = "https://nodejsclusters-57268-0.cloudclusters.net/";

    private ControladorEstudiante() {}

    public static ControladorEstudiante getInstance(){
        if (controladorEstudiante == null){
            controladorEstudiante = new ControladorEstudiante();
        }
        return controladorEstudiante;
    }

    public ArrayList<Curso> cursosEstudiante(){
        ArrayList<Curso> cursos = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/cursos/estudiante/" + controlador.getUsuarioActual().getCedula();
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
        return cursos;
    }

    public static void main(String[] args){
        ControladorEstudiante estudiante = ControladorEstudiante.getInstance();
        estudiante.cursosEstudiante();
    }

}
