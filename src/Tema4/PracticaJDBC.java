package Tema4;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PracticaJDBC {

    public static void main(String[] args) {

        try {

            String url = "jdbc:sqlite:empresa.db";
            String user = "admin";
            String pass = "admin";

            // Creamos la connexion metiendo la url de la localhost, el usuario y la contraseña
            Connection conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("conesion exitosa");

            // Creamos el statement para ejecutar las consultas
            Statement statement = conexion.createStatement();

            // Con statement.executeUpdate insertamos consulatas
            statement.executeUpdate("INSERT INTO empleados (nombre, apellidos, salario)" + "VALUES ('juan', 'perez', '3000')");

            // Con statement.executeQuery ejecutamos comandos o datos de la db y lo guardamos en la clase Resulset para poder verlas cuando iteremos en el bucle
            // en este caso para visualizar los datos que hay en la tabla
            ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLEADOS");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellidos = resultSet.getString("apellidos");
                double salario = resultSet.getDouble("salario");

                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellidos);
                System.out.println("Salario: " + salario);
            }

            // Cerramos los flujos
            statement.close();
            conexion.close();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
