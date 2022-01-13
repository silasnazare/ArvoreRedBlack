package br.edu.ifma.aedii.arvoreredblack;

import static br.edu.ifma.aedii.entrada.Entrada.leituraDeArquivo;

public class Root {
    public static Arvore a = new Arvore(1);

    public static void main (String[] args) {

        String caminho = "src/main/java/br/edu/ifma/aedii/entrada/dados.txt";
        leituraDeArquivo(caminho);
        a.grafico();
    }
}
