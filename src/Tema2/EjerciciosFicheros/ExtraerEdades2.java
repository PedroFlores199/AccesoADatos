package Tema2.EjerciciosFicheros;

import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.IOException;

public class ExtraerEdades2 {
    public static void main(String[] args) {
        try {
            // Cambia la ruta si el fichero está en una subcarpeta como "resources"
            Reader reader = new FileReader("fichero2.txt");
            StreamTokenizer streamTokenizer = new StreamTokenizer(reader);

            // Recorrer el fichero en busca de edades
            while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                // Comprobar si el token es un número
                if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    // Aquí puedes procesar la edad (por ejemplo, imprimirla)
                    System.out.println("Edad encontrada: " + streamTokenizer.nval);
                }
            }

            // Cerrar el reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Imprimir el error en caso de excepción
        }
    }
}


