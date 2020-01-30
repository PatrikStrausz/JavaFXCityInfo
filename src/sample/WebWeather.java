package sample;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WebWeather {


    public Weather getData(String city, String code2) {
        Weather weather = null;

        try {

            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + ","
                    + code2 + "&units=metric&appid=f65003a0d623815d1640e876d848b2ee");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String output = br.readLine();


                JSONObject jsonObject = new JSONObject(output);
                JSONObject main = jsonObject.getJSONObject("main");
                JSONObject coord = jsonObject.getJSONObject("coord");
                JSONObject sys = jsonObject.getJSONObject("sys");


                String name = jsonObject.getString("name");
                String country = sys.getString("country");
                double temperature = main.getDouble("temp");
                long humidity = main.getLong("humidity");
                double lon = coord.getLong("lon");
                double lat = coord.getLong("lat");
                long visibility = jsonObject.getLong("visibility");
                long sunrise = sys.getLong("sunrise");
                long sunset = sys.getLong("sunset");


                weather = new Weather(name, country, temperature, humidity, lon, lat, visibility, sunrise, sunset);


            }


            conn.disconnect();
            return weather;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
