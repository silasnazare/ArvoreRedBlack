package br.edu.ifma.aedii.arvoreredblack;

public class No {
    public Integer v;// Um valor inteiro, que será a chave para busca, inserção, remoção.
    public No p, esq, dir;//Caminho para o Pai, filho a Esquerda e Filho a Direita
    public boolean Cor;//Boolean que vai definir as cores, sendo True Vermelha e False Preta

    public No(int i, boolean c) {
        this.v = i;
        this.Cor = c;
        this.p = this.esq = this.dir = Arvore.nil;

    }

    public No encontra(int n) {
        if (n < this.v && this.esq != Arvore.nil) return this.esq.encontra(n);
        else if (n > this.v && this.dir != Arvore.nil) return this.dir.encontra(n);
        else return this;
    }

    public void encontra50(Integer q, int aux, Arvore res) {
        if(q >= 50) return;

        if (this.esq != Arvore.nil) {
            this.esq.encontra50(q, aux, res);
        }
        if (this.v > aux && q < 50) {
            res.insercao(this.v);
            q++;
        }
        if (this.dir != Arvore.nil) {
            this.dir.encontra50(q, aux, res);
        }
    }

    public No minimo() {
        if (this.esq != Arvore.nil) return esq.minimo();
        else return this;
    }

    public No maximo() {
        if (this.dir != Arvore.nil) return dir.maximo();
        else return this;
    }

    public void printFullNodos() {
        if (this.esq != Arvore.nil) this.esq.printFullNodos();
        System.out.println(this.v);
        if (this.dir != Arvore.nil) this.dir.printFullNodos();
    }

    public No predecessor() {
        if (this.esq != Arvore.nil) return this.esq.maximo();
        else return this;
    }

    public No sucessor(){
        if (this.dir != Arvore.nil) return this.dir.minimo();
        else return this;
    }

    public void grafico() {
        if (this.Cor) {
            System.out.println("\t" + this.v + " [style = filled, fillcolor = red];");
        } else {
            System.out.println("\t" + this.v + " [style = filled, fillcolor = black, fontcolor = white];");
        }

        if (this.esq != Arvore.nil) {
            System.out.println("\t" + this.v + " -> " + this.esq.v + " [label = \" esq\"];");
            this.esq.grafico();
        }
        else {
            System.out.println("\t" + this.v + " -> nil [label = \" esq\"];");
        }

        if (this.dir != Arvore.nil) {
            System.out.println("\t" + this.v + " -> " + this.dir.v + " [label = \" dir\"];");
            this.dir.grafico();
        }
        else {
            System.out.println("\t" + this.v + " -> nil [label = \" dir\"];");
        }
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public No getP() {
        return p;
    }

    public void setP(No p) {
        this.p = p;
    }

    public No getEsq() {
        return esq;
    }

    public void setEsq(No esq) {
        this.esq = esq;
    }

    public No getDir() {
        return dir;
    }

    public void setDir(No dir) {
        this.dir = dir;
    }

    public boolean isCor() {
        return Cor;
    }

    public void setCor(boolean cor) {
        Cor = cor;
    }
}
