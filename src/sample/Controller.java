package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.util.List;

public class Controller {

    public Button btnOk;
    public ComboBox <String> cmbCountry;
    public ComboBox <String> cmbCity;

    public Label lblPop;
    List countries;
    List cities;

    public Controller(){

        Database database = new Database();
       countries =  database.getCountries();
    }


public void initialize(){
        cmbCity.setDisable(true);
        btnOk.setDisable(true);

    Database database = new Database();
    countries = database.getCountries();

    cmbCountry.getItems().setAll(countries);
}



public void bm (){
//       Database database = new Database();
//       countries = database.getCountries();
//       cmbCountry.getItems().setAll(countries);
        if(!cmbCountry.getSelectionModel().isSelected(1)) {
               cmbCity.setDisable(false);

   }

}

public void getCity(){

    Database database = new Database();
    String city = cmbCountry.getValue();

    cities = database.getCities(city);

    cmbCity.getItems().setAll(cities);

    if(!cmbCity.getSelectionModel().isSelected(1)) {
        btnOk.setDisable(false);
    }
}

public void getInfo(){

    Database database = new Database();
    String city = cmbCity.getValue();
    String country = cmbCountry.getValue();

   lblPop.setText(database.getPop(city, country));


}


}
