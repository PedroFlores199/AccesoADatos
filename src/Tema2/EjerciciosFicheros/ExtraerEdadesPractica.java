package Tema2.EjerciciosFicheros;

import java.io.FileReader;
import java.io.Reader;
import java.io.StreamTokenizer;

public class ExtraerEdadesPractica {

    public static void main(String[] args) {

        try {
            Reader reader = new FileReader("fichero.txt");
            StreamTokenizer streamTokenizer = new StreamTokenizer(reader);

            //Mientras streamTokenizer no este en el final (TT_EOF) que el bucle no pare
            while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF){

                //Si el token (ttype) es un numero sacamos por pantalla el valor del token con nval (number value)
                if (streamTokenizer.ttype == StreamTokenizer.TT_NUMBER) {
                    System.out.println("Numero: " + streamTokenizer.nval);
                }

                //Lo mismo para las letras
                else if (streamTokenizer.ttype == StreamTokenizer.TT_WORD){
                    System.out.println("Palabra: " + streamTokenizer.sval);
                }
            }

            reader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
