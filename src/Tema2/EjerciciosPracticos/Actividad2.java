package Tema2.EjerciciosPracticos;

import java.io.FileReader;
import java.io.PushbackReader;

public class Actividad2 {
    public static <IOException> void main(String[] args) {
        try {
            // Ruta al archivo de texto
            String rutaArchivo = "datos.txt";
            // Crear un objeto FileReader para leer el archivo
            FileReader fileReader = new FileReader(rutaArchivo);
            // Crear un objeto PushbackReader que envuelve elFileReader
            PushbackReader pushbackReader = new PushbackReader(fileReader);
            int caracterLeido;
            int contadorCaracteres = 0;
            // Leer caracteres del archivo
            while ((caracterLeido = pushbackReader.read()) != -1) {
                contadorCaracteres++;
                // Mostrar el caracter leído
                System.out.print("Caracter leido: " + (char) caracterLeido);
                // Devolver el caracter al flujo de datos
                pushbackReader.unread(caracterLeido);
                // Mostrar el caracter devuelto
                int caracterDevuelto = pushbackReader.read();
                System.out.println(" | (Devuelto: " + (char)
                        caracterDevuelto + ") ");
                // Leer el caracter / avanzar en el flujo pushbackReader.read();
            }

            // Cerrar el PushbackReader y el FileReader
            pushbackReader.close();
            fileReader.close();
            System.out.println("\nTotal de caracteres leídos: " +
                    contadorCaracteres);
        } catch (Exception e) {
            System.err.println("Error al procesar el archivo: " +
                    e.getMessage());
        }
    }

}
