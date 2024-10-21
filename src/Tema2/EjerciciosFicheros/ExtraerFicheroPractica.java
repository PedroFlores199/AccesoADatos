package Tema2.EjerciciosFicheros;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ExtraerFicheroPractica {

    public static void main(String[] args) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("numeros.txt"));

            int[] numeros = {25, 30, 18, 45, 50};

            for (int numero : numeros){
                bw.write(String.valueOf(numero));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
