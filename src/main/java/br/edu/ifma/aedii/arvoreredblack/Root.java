package br.edu.ifma.aedii.arvoreredblack;

import br.edu.ifma.aedii.entrada.Entrada;

public class Root {
    public static Arvore a = new Arvore(1);

    public static void main (String[] args) {
        Entrada e = new Entrada();
        String caminho = "src/main/java/br/edu/ifma/aedii/entrada/dados.txt";

        for (int i = 0; i < 9; i++) {
            a.insercao(e.lerArquivo(caminho));
        }

        a.grafico();
    }
}
