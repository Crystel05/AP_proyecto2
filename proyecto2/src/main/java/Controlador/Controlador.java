package Controlador;

import Modelo.Curso;
import Modelo.Grado;
import Modelo.Usuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Controlador {

    private static Controlador controlador;
    private Usuario usuarioActual;

    public Controlador(){}

    public static Controlador getInstance(){
        if(controlador == null){
            controlador = new Controlador();
        }
        return controlador;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public TipoUsuario iniciarSesion(String correo, String contrasena) {
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/logIn/" + correo;
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
                System.out.println(data);
                String contrasenaCorrecta = (String) data.get("contrasenna");
                boolean contrasennaAceptada = contrasenaCorrecta.equals(contrasena);

                if (contrasennaAceptada){
                    usuarioActual = new Usuario();
                    usuarioActual.setCedula(data.get("cedula").toString());
                    usuarioActual.setNombre(data.get("nombre").toString());
                    usuarioActual.setCorreo(correo);
                    System.out.println(data.get("ID"));
                    return verificarTipo(Integer.parseInt(data.get("ID").toString()));
                }
            }

        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            return null;
        }
        return null;
    }

    private TipoUsuario verificarTipo (int ID){
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/tipoUsuario/" + ID;
        System.out.println(get);
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
        }catch (IOException | ParseException | IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
        return null;
    }

    public Curso detallesCurso(String cod, String clase){
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/cursos/info/"+cod+"/"+clase;
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
                System.out.println(data);
                Grado grado = null;
                switch (data.get("clase").toString()){
                    case "prepa":
                    case "1":
                        grado = Grado.Preparatoria;
                        break;
                    case "2":
                        grado = Grado.Primero;
                        break;
                    case "3":
                        grado = Grado.Segundo;
                        break;
                    case "4":
                        grado = Grado.Cuarto;
                        break;
                    case "5":
                        grado = Grado.Quinto;
                        break;
                    case "6":
                        grado = Grado.Sexto;
                        break;
                    case "7":
                        grado = Grado.Septimo;
                        break;
                    case "11":
                        grado = Grado.Undecimo;
                        break;
                }
                String horario = data.get("diaSemana") + " desde " + data.get("horaInicio") + " hasta " +data.get("horaFin");
                return new Curso(cod, data.get("nombre").toString(), grado, horario);
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ParseException {
        Controlador controlador = Controlador.getInstance();
//        System.out.println(controlador.iniciarSesion("hector@gmail.com", "123456"));
        System.out.println(controlador.detallesCurso("esp", "4"));
    }
}
