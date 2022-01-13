package br.edu.ifma.aedii.entrada;

import java.io.*;

public class Entrada {
    public static int lerArquivo(String caminho) {
        File arquivo = new File(caminho);
        int conteudo = 0;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
            String linha = "";
            linha = br.readLine();
            conteudo = Integer.parseInt(linha);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conteudo;
    }
}
