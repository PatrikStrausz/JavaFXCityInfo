package sample;





import com.mysql.cj.xdevapi.JsonParser;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class WebWeather {


    public Weather getData(String city, String code2) {
        Weather weather = null;

        try {

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city +","
                    +code2+"&units=metric&appid=f65003a0d623815d1640e876d848b2ee");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            JSONParser parse = new JSONParser();
            JSONArray jsonArr = new JSONArray();
            JSONArray jsonArr1 = new JSONArray();
            JSONArray jsonArr2 = new JSONArray();
            JSONArray jsonArr3 = new JSONArray();

            if(conn.getResponseCode() == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String output = br.readLine();

                String name  = null;
                String country = null;
                double temperature = 0;
               long humidity = 0;
                double lon = 0;

              double lat = 0;


                JSONObject jsonObj = (JSONObject)parse.parse(output);

                //System.out.println(json_obj.get("sys"));

                jsonArr.add(jsonObj.get("main") );

                for(int i=0; i<jsonArr.size(); i++) {
                    JSONObject jsonObjTemp = (JSONObject)jsonArr.get(i);

                    System.out.println("\nTemperature: " +jsonObjTemp.get("temp"));
                    System.out.println("\nHumidity: " +jsonObjTemp.get("humidity"));

                   temperature = (double) jsonObjTemp.get("temp");
                   humidity = (long) jsonObjTemp.get("humidity");

                }

                jsonArr1.add(jsonObj.get("coord"));
                for(int i=0; i<jsonArr1.size(); i++) {
                    JSONObject jsonObjTemp = (JSONObject)jsonArr1.get(i);
                    System.out.println("lat: " + jsonObjTemp.get("lat"));
                    System.out.println("lot: " + jsonObjTemp.get("lon"));

                    lon = (double) jsonObjTemp.get("lon");
                    lat = (double) jsonObjTemp.get("lat");

                }

                jsonArr2.add(jsonObj.get("sys"));
                for(int i=0; i<jsonArr2.size(); i++) {
                    JSONObject jsonObjTemp = (JSONObject) jsonArr2.get(i);
                    System.out.println("country: " + jsonObjTemp.get("country"));

                    country = (String) jsonObjTemp.get("country");


                }

                System.out.println(jsonObj.get("name"));
                city = (String) jsonObj.get("name");



                weather = new Weather(name, country, temperature, humidity, lon, lat);




            }


           conn.disconnect();
            return weather;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
