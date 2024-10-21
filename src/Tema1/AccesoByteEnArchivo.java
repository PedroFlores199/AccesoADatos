package Tema1;

import java.io.RandomAccessFile;
import java.io.IOException;
public class AccesoByteEnArchivo {
    public static void main(String[] args) {
// Nombre del archivo que contiene el texto
        String nombreArchivo = "miarchivo.txt";
        try {
// Crear una instancia de RandomAccessFile en modo lectura ("r")
            RandomAccessFile archivo = new RandomAccessFile(nombreArchivo, "r");
// Mover el puntero de lectura al byte número 5
            archivo.seek(5);
// Leer el byte en la posición 5
            int byteLeido = archivo.read();
// Mostrar el byte leído como carácter
            System.out.println("Byte número 5 en el archivo: " + (char) byteLeido);
// Cerrar el archivo
            archivo.close();
        } catch (IOException e) {
            System.err.println("Ocurrió un error al acceder al archivo: " +
                    e.getMessage());
        }
    }
}