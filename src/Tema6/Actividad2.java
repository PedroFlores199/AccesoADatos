package Tema6;


// Hay que hacer una consulta a la base de datos y visualizar el contenido

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Actividad2 {
    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/personas";
        String user = "Peanf";
        String pass = "admin";

        try (Connection conexion = DriverManager.getConnection(url, user, pass)) {

            Statement statement = conexion.createStatement();

            String consultaSQL = "SELECT * FROM personas";

            ResultSet resultSet = statement.executeQuery(consultaSQL);

            while (resultSet.next()){
                System.out.println(resultSet.getString("NOMBRE"));
                System.out.println(resultSet.getString("APELLIDOS"));
                System.out.println(resultSet.getString("FECHA"));

            }
        }catch (Exception e) {
            System.out.println("Error");

        }

    }
}
