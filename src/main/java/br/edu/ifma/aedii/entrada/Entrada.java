package br.edu.ifma.aedii.entrada;

import br.edu.ifma.aedii.arvoreredblack.Arvore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Entrada {
    public static void leituraDeArquivo(String caminho) {
        String linha = new String();
        File arg = new File(caminho);
        Arvore a = new Arvore(1);

        if (arg.exists()) {
            try{
                FileReader leitordeArquivo = new FileReader(caminho);
                BufferedReader bufferdeArquivo = new BufferedReader(leitordeArquivo);
                while(true) {
                    linha = bufferdeArquivo.readLine();
                    if(linha==null)
                        break;

                    a.insercao(Integer.parseInt(linha));
                }
            } catch(Exception e) {
            }
        }
    }
}
