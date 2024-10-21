package Tema1;
import java.io.File;
import java.io.IOException;

public class EjemploClaseFile {
    public static void main(String[] args) {
// Crear un objeto File que representa un archivo en el directorio actual
        File archivo = new File("miarchivo2.txt");
// Crear un archivo si no existe
        try {
            if (archivo.createNewFile()) {
                System.out.println("El archivo se ha creado con éxito.");
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
// Verificar si el archivo existe
        if (archivo.exists()) {
            System.out.println("El archivo existe.");
        } else {
            System.out.println("El archivo no existe.");
        }
// Obtener la ruta absoluta del archivo
        String rutaAbsoluta = archivo.getAbsolutePath();
        System.out.println("Ruta absoluta del archivo: " + rutaAbsoluta);
// Obtener el nombre del archivo
        String nombreArchivo = archivo.getName();
        System.out.println("Nombre del archivo: " + nombreArchivo);
// Obtener el directorio padre del archivo
        String directorioPadre = archivo.getParent();
        System.out.println("Directorio padre del archivo: " + directorioPadre);
// Crear un objeto File que representa un directorio
        File directorio = new File("mi_directorio");
// Crear un directorio si no existe
        if (!directorio.exists()) {
            if (directorio.mkdir()) {
                System.out.println("Directorio creado con éxito.");
            } else {
                System.out.println("Error al crear el directorio.");
            }
        }
// Listar archivos y directorios en un directorio
        File[] archivosEnDirectorio = directorio.listFiles();
        if (archivosEnDirectorio != null) {
            System.out.println("Archivos y directorios en el directorio:");
            for (File archivoODirectorio : archivosEnDirectorio) {
                System.out.println(archivoODirectorio.getName());
            }
        } else {
            System.out.println("El directorio está vacío.");
        }
// Eliminar un archivo
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("El archivo se ha eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el archivo.");
            }
        }
// Eliminar un directorio
        if (directorio.exists()) {
            if (directorio.delete()) {
                System.out.println("El directorio se ha eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el directorio.");
            }
        }
    }
}
