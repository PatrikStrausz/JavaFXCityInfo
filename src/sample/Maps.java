package sample;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Maps {

    public void openBrowser(double lat, double lon) {


        try {


            Desktop.getDesktop().browse(new URI("https://www.google.com/maps/place/" + lat + "," + lon));


        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
    }


}

