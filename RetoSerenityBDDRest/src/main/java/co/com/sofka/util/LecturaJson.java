package co.com.sofka.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;


public class LecturaJson {

    static Object objetoJason;
    public static String leer(String rutaArchivoJason) throws IOException, ParseException {
        objetoJason = new JSONParser().parse(new FileReader(rutaArchivoJason));
        JSONObject js = (JSONObject) objetoJason;
        return js.toString();
    }
}
