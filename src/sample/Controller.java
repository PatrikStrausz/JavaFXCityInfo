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

    public Label lblCity;
    public Label lblCountry;
    private Database database = new Database();
    @FXML
    private Button btnOk;
    @FXML
    private ComboBox<String> cmbCountry;
    @FXML
    private ComboBox<String> cmbCity;
    @FXML
    private Label lblPop;
    @FXML
    private List countries;

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
         for(City c: city){
             if(c.getName().equalsIgnoreCase(cityName)) {
                 cities = c;
                 System.out.println();
                 System.out.println(c.getName() + " " + c.getPopulation());
                 lblPop.setText(formatPopString((c.getPopulation())));
                 lblCity.setText(c.getName());
                 lblCountry.setText(c.getCountry());
                 break;
             }
         }
         if(cities==null) {
             return;
         }


    }

    private String formatPopString(int population){
        String data = Integer.toString(population);

        String text="";

        for(int i=data.length()-1;i>=0;i--) {
            if(i%3==0)
                text = " " + text;
                text = data.charAt(i) + text;

        }
        return text.trim();



    }


}
