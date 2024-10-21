package Tema2.EjerciciosFicheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RellenarFichero {
    public static void main(String[] args) {
        // Especifica la ruta del archivo
        String rutaArchivo = "fichero3.txt"; // Cambia esto según tu necesidad

        // Edades a escribir en el archivo
        int[] edades = {25, 30, 18, 45, 50};

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Recorremos el array de edades y escribimos cada una en el archivo
            for (int edad : edades) {
                writer.write(String.valueOf(edad)); // Convertimos la edad a String
                writer.newLine(); // Añadimos una nueva línea
            }
            System.out.println("Edades escritas en el archivo " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace(); // Manejamos cualquier error que ocurra
        }
    }
}
