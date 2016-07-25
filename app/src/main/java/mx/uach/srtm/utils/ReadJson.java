package mx.uach.srtm.utils;

import android.util.Log;

import com.bluebite.android.eddystone.Url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by dani on 5/07/16.
 */
public class ReadJson {

//    private final static String DOMAIN = "http://jsonplaceholder.typicode.com/users";

    public static void setUrlUsuario(List<Url> urls) {

    }

    public static String read(String url){
        String json = "{}";
        try {
            URL ruta = new URL(url);
            System.out.println("Aqui toy");
            HttpURLConnection con = (HttpURLConnection) ruta.openConnection();
            System.out.println("mi mama me mima");
            json = transformBuffer(con.getInputStream());
        }catch (Exception ex){
            System.out.println("ex = " + ex);
            System.out.println("ex.getMessage() = " + ex.getMessage());
            Log.w("Error", "No se puede leer el servicio.");
            json = "null";
        }
        return json;
    }

    public static String transformBuffer(InputStream in){
        String linea = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            //El buffer devuelve -1 cuando ya no hay nada que leer
            int value = -1;
            while ((value = reader.read()) != -1){
                char c = (char) value;
                linea = String.format("%s%s", linea, c);
            }
        }catch (Exception ex){
            Log.e("Error", "No se puede leer el JSON.");
        }finally {
            try {
                if (reader != null){
                    reader.close();
                }
            }catch (IOException e){
                Log.e("Error","No se pudo cerrar el Buffer.");
            }
        }

        return linea;
    }

}
