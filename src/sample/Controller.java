package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.List;

public class Controller {
    public Label lblLot;
    public Label lblLat;
    public CheckBox ckbxMore;
    public Label lblSunrise;
    public Label lblSunset;
    public Label lblWindSpeed;
    public Button button;
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

        lblVis.setVisible(false);
        lblLot.setVisible(false);
        lblLat.setVisible(false);
        lblSunrise.setVisible(false);
        lblSunset.setVisible(false);
        lblWindSpeed.setVisible(false);


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
                lblPop.setText(formatPopString(c.getPopulation()));
                lblCity.setText(c.getName());
                lblCountry.setText(c.getCountry());
                Weather weather = new WebWeather().getData(c.getName(), c.getCode2());

                if (weather != null) {
                    lblTemp.setText("Temperature: " + String.valueOf(weather.getTemp()));
                    lblHum.setText("Humidity: " + String.valueOf(weather.getHumidity() + "%"));
                    lblVis.setText("Visibility: " + String.valueOf(weather.getVisibility()));
                    lblLot.setText("Lot: " + String.valueOf(weather.getLon()));
                    lblLat.setText("Lat: " + String.valueOf(weather.getLat()));
                    lblWindSpeed.setText("Wind speed: " + String.valueOf(weather.getWindSpeed()));

                } else {

                    lblTemp.setText("Temperature: ---");
                    lblHum.setText("Humidity: ---");
                    lblVis.setText("Visibility: ---");
                    lblLot.setText("Lot: ---");
                    lblLat.setText("Lat: ---");
                    lblWindSpeed.setText("Wind speed: ---");

                }


                lblSunrise.setText("Sunrise: " + getTimeFormat(weather.getSunrise()));
                System.out.println(getTimeFormat(weather.getSunrise()));

                lblSunset.setText("Sunset: " + getTimeFormat(weather.getSunset()));
                System.out.println(getTimeFormat(weather.getSunset()));


            }
        }
        if (cities == null) {
            return;
        }


    }

    public String getTimeFormat(long number) {
        return new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(number * 1000));
    }


    public void showMoreInfo() {
        if (ckbxMore.isSelected()) {
            lblVis.setVisible(true);
            lblLot.setVisible(true);
            lblLat.setVisible(true);
            lblSunrise.setVisible(true);
            lblSunset.setVisible(true);
            lblWindSpeed.setVisible(true);


        } else if (!ckbxMore.isSelected()) {

            lblVis.setVisible(false);
            lblLot.setVisible(false);
            lblLat.setVisible(false);
            lblSunrise.setVisible(false);
            lblSunset.setVisible(false);
            lblWindSpeed.setVisible(false);
        }

    }

    private String formatPopString(int population) {
        return String.format("%,d", Integer.parseInt(String.valueOf(population)));


    }


    public void openBrowser() {


        String cityName = cmbCity.getValue();
        City cities = null;
        for (City c : city) {
            if (c.getName().equalsIgnoreCase(cityName)) {
                cities = c;
                Weather weather = new WebWeather().getData(c.getName(), c.getCode2());
                Maps webInfo = new Maps();
                webInfo.openBrowser(weather.getLat(), weather.getLon());


            }


        }


    }


}



