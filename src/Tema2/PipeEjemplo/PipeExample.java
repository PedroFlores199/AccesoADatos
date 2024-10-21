package Tema2.PipeEjemplo;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipeExample {

    public static void main(String[] args) throws IOException {

        // Creamos un PipedOutputStream (tubería de salida) para la escritura de datos
        final PipedOutputStream salida = new PipedOutputStream();

        // Creamos un PipedInputStream (tubería de entrada) conectado al PipedOutputStream
        // Todo lo que se escriba en 'salida' será leído desde 'entrada'
        final PipedInputStream entrada = new PipedInputStream(salida);

        // Creamos el primer hilo que se encargará de escribir datos en el PipedOutputStream
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Escribimos una cadena de texto "Hola, que tal?" en el PipedOutputStream
                    // Convertimos la cadena a un array de bytes para escribirla en el stream
                    salida.write("Hola, que tal?".getBytes());
                    
                    // Cerramos el PipedOutputStream para indicar que no se escribirán más datos
                    // Esto es crucial para que el otro hilo pueda saber que se ha terminado la escritura
                    salida.close();
                } catch (IOException e) {
                    // Si ocurre un error durante la escritura, lo manejamos aquí
                    e.printStackTrace();
                }
            }
        });

        // Creamos el segundo hilo que se encargará de leer los datos desde el PipedInputStream
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Leemos el primer byte desde el PipedInputStream
                    int unByte = entrada.read();
                    
                    // Continuamos leyendo mientras el valor no sea -1 (indica fin del stream)
                    while (unByte != -1) {
                        // Convertimos el byte leído en un carácter y lo imprimimos en la consola
                        System.out.print((char) unByte);
                        
                        // Leemos el siguiente byte para continuar el proceso
                        unByte = entrada.read();
                    }

                    // Cerramos el PipedInputStream una vez hayamos terminado de leer los datos
                    entrada.close();
                } catch (IOException e) {
                    // Si ocurre un error durante la lectura, lo manejamos aquí
                    e.printStackTrace();
                }
            }
        });

        // Iniciamos ambos hilos para que empiecen a ejecutarse en paralelo
        thread1.start();  // El primer hilo escribe en el PipedOutputStream
        thread2.start();  // El segundo hilo lee del PipedInputStream
    }
}
