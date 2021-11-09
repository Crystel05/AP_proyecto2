package Controlador;

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

    public Controlador(){}

    public static Controlador getInstance(){
        if(controlador == null){
            controlador = new Controlador();
        }
        return controlador;
    }

    public TipoUsuario iniciarSesion(String correo, String contrasena) {

        String get = "https://nodejsclusters-57268-0.cloudclusters.net/logIn/"+correo;
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
