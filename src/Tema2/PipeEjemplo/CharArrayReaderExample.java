package Tema2.PipeEjemplo;

import java.io.CharArrayReader;
import java.io.IOException;

public class CharArrayReaderExample {
    public static void main(String[] args) throws IOException {
        // Convertimos la cadena "hola amigo" en un array de caracteres
        char[] chars = "hola amigo".toCharArray();

        // Creamos un CharArrayReader que leerá del array de caracteres
        CharArrayReader charArrayReader = new CharArrayReader(chars);

        int data = 0;  // Variable para almacenar los datos leídos

        try {
            // Leemos el primer carácter del array
            data = charArrayReader.read();

            // Mientras no lleguemos al final del array (read() devuelve -1)
            while (data != -1) {
                // OPERACIONES: Imprimimos el carácter leído convirtiendo el entero a carácter
                System.out.println((char) data);

                // Leemos el siguiente carácter
                data = charArrayReader.read();
            }
        } catch (IOException e) {
            // En caso de que ocurra una excepción de E/S, la imprimimos
            e.printStackTrace();
        } finally {
            charArrayReader.close();  // Intentamos cerrar el lector
        }
    }
}

