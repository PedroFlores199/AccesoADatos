package Tema6;

// Crear una base de datos de nombre personas con campos Nombre, Apellido y Fecha nacimiento
// luego de crearla habra que insertar datos, modificar estos datos por otros nuevos y luego borrarla

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Actividad1 {
    public static void main(String[] args) {

        String url = "jdbc:mariadb://localhost:3306/personas";
        String user = "Peanf";
        String pass = "admin";



        try (Connection connection = DriverManager.getConnection(url, user, pass)) {

            String crearSQL = "CREATE TABLE IF NOT EXISTS personas (NOMBRE VARCHAR (100), APELLIDOS VARCHAR (100), FECHA DATE)";

            try (PreparedStatement crearStmt = connection.prepareStatement(crearSQL)){
                crearStmt.executeUpdate();
                System.out.println("Tabla creada exitosamente");
            }

            String insertarSQL = "INSERT INTO personas (NOMBRE, APELLIDOS, FECHA) VALUES (?,?,?)";

            try (PreparedStatement insertarStmt = connection.prepareStatement(insertarSQL)){
                insertarStmt.setString(1, "Pedro");
                insertarStmt.setString(2, "Flores");
                insertarStmt.setDate(3, java.sql.Date.valueOf("1999-07-17"));
                insertarStmt.executeUpdate();
            }

            String modificarSQL = " UPDATE personas SET NOMBRE = ?, APELLIDOS = ?, FECHA = ? WHERE NOMBRE = ?";
            try (PreparedStatement modificarStmt = connection.prepareStatement(modificarSQL)){
                modificarStmt.setString(1, "Antonio");
                modificarStmt.setString(2, "Flores");
                modificarStmt.setDate(3, java.sql.Date.valueOf("2000-01-20"));
                modificarStmt.setString(4, "Pedro");

                modificarStmt.executeUpdate();
            }

            /*
            String borrarTabla = "DROP TABLE personas";
            try (PreparedStatement borrarStmt = connection.prepareStatement(borrarTabla)){
                borrarStmt.executeUpdate();
                System.out.println("Inserciones borradas con exito");
            }
            */

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
