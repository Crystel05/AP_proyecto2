package Controlador;

import Modelo.Curso;
import Modelo.Grado;
import Modelo.Mensaje;
import Modelo.Usuario;
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

public class Controlador {

    private static Controlador controlador;
    private Usuario usuarioActual;
    private DummyMethods dummyMethods = new DummyMethods();

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
                Grado grado = dummyMethods.convertirGradoBD(data.get("clase").toString());
                String horario = data.get("diaSemana") + " desde " + data.get("horaInicio") + " hasta " +data.get("horaFin");
                return new Curso(cod, data.get("nombre").toString(), grado, horario);
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException | NullPointerException e){
            return null;
        }
        return null;
    }

    public ArrayList<Mensaje> listaMensajesCurso(String codigo, String grado){
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/chat/"+ codigo + "/"+ grado;
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
                    mensajes.add(new Mensaje(new Usuario(data.get("nombre").toString()), data.get("texto").toString()));
                }
            }else{
                return null;
            }
        }catch (IOException | ParseException | IndexOutOfBoundsException e){
            e.printStackTrace();
            return null;
        }
        Collections.reverse(mensajes);
        return mensajes;
    }

    public boolean enviarMensaje(String codigo, String grado, String correo, String mensaje){
        String get = "https://nodejsclusters-57268-0.cloudclusters.net/publicaMsg/"+ codigo + "/"+ grado + "/" + correo + "/" + mensaje;
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

    public static void main(String[] args) throws IOException, ParseException {
        Controlador controlador = Controlador.getInstance();
//        System.out.println(controlador.iniciarSesion("hector@gmail.com", "123456"));
        System.out.println(controlador.detallesCurso("esp", "4"));
    }
}
