package util;

import java.io.*;

public class Teclado {

    /**
     * Este metodo e responsavel pela leitura de dados do teclado.
     * 
     * @return String com dados lidos do teclado
     * @throws IOException
     */
    public static String le() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String texto = reader.readLine();
        return texto;
    }
}