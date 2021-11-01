package Controlador;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class prueba {

    public static void main(String[] args){
        try{
            URL url = new URL("https://nodejsclusters-55995-0.cloudclusters.net/logIn/titovare@gmail.com");
//            URL url = new URL("https://nodejsclusters-55995-0.cloudclusters.net/usuarios");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == 200){
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    info.append(scanner.nextLine());
                }

                scanner.close();

                JSONParser parser = new JSONParser();
                JSONArray array = (JSONArray) parser.parse(String.valueOf(info));
                JSONObject data = (JSONObject) array.get(0);

                System.out.println(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
