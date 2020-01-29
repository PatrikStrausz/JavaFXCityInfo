package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public Label lblLot;
    public Label lblLat;
    private Database database = new Database();

    @FXML
    private Button btnOk;

    @FXML
    private ComboBox<String> cmbCountry;
    @FXML
    private ComboBox<String> cmbCity;

    @FXML
    private List countries;

    @FXML
    private Label lblPop;
    public Label lblCity;
    public Label lblCountry;
    public Label lblTemp;
    public Label lblHum;
    public Label lblVis;


    private List<City> city = null;

    public Controller() {

        countries = database.getCountries();


    }


    public void initialize() {
        countries = database.getCountries();

        cmbCountry.getItems().setAll(countries);
    }


    public void bm() {
        if (!cmbCountry.getSelectionModel().isSelected(1)) {
            cmbCity.setDisable(false);

        }

    }

    public void getCity() {
        String country = cmbCountry.getValue();

        if (country != null) {
            city = database.getCities(country);
            cmbCity.getItems().clear();
            for (City s : city) {
                System.out.print(s.getName() + ", ");
                cmbCity.getItems().add(s.getName());

            }
            btnOk.setDisable(false);

        }

    }

    public void getInfo() {

        String cityName = cmbCity.getValue();
        City cities = null;
        for (City c : city) {
            if (c.getName().equalsIgnoreCase(cityName)) {
                cities = c;
                System.out.println();
                System.out.println(c.getName() + " " + c.getPopulation());
                lblPop.setText(formatPopString((c.getPopulation())));
                lblCity.setText(c.getName());
                lblCountry.setText(c.getCountry());
               Weather weather = new WebWeather().getData(c.getName(), c.getCode2());


//               lblTemp.setText("Temperature: " +String.valueOf(weather.getTemp()));
//               lblHum.setText("Humidity: " +String.valueOf(weather.getHumidity()));
                lblLot.setText("Lot: " +String.valueOf(weather.getLon()));
                lblLat.setText("Lat: "+String.valueOf(weather.getLat()));
            }
        }
        if (cities == null) {
            return;
        }


    }

    private String formatPopString(int population) {
        String data = Integer.toString(population);

        String text = "";

        for (int i = data.length() - 1; i >= 0; i--) {
            if (i % 3 == 0)
                text = " " + text;
            text = data.charAt(i) + text;

        }
        return text.trim();


    }


}
