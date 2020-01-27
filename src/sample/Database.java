package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {


    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://itsovy.sk:3306/world_x?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "student", "kosice2019");
    }


    public List getCountries() {
        try {
            Connection connection = getConnection();

            List<String> country = new ArrayList<>();
            String select = "Select code,  name from country ";

            PreparedStatement statement = connection.prepareStatement(select);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                country.add(resultSet.getString(2));
            }

            return country;


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public List getCities(String country) {

        try {
            Connection connection = getConnection();
            List<String> cities = new ArrayList<>();
            String select = "select city.name from country inner join city on country.code = city.countrycode where country.name like ? ";

            PreparedStatement statement = connection.prepareStatement(select);
            statement.setString(1, country);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                cities.add(resultSet.getString(1));
            }
            System.out.println(cities);
            return cities;

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

}
