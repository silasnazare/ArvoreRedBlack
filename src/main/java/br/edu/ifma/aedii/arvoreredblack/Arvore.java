package br.edu.ifma.aedii.arvoreredblack;

public class Arvore {
    public No raiz;
    public static No nil = new No(0, false);
    public Integer cont;

    public Arvore() {
        this.raiz = Arvore.nil;
    }

    public Arvore(int v) {
        this.raiz = new No(v, false);
    }

    public void insercao (int n) {

        if (this.raiz == Arvore.nil) {

            this.raiz = new No (n, false);
        }
        else {
            No a = this.encontra(n);
            if (n < a.v) {

                a.esq = new No(n, true);
                a.esq.p = a;
                this.balanceamentoInsercao(a.esq);
            }
            else if (n > a.v) {
                a.dir = new No(n, true);
                a.dir.p = a;
                this.balanceamentoInsercao(a.dir);
            }
        }
    }

    public Arvore encontra50(int n) {
        Arvore res = new Arvore();
        cont = 0;

        this.raiz.encontra50(cont, n, res);

        return res;
    }

	/*public void insercao(int i, No r) {
		//No aux = new No(i,false);

		if (r.getV() > i) {
			//esquerda
			if (r.getEsq() != null) insercao(i, r.getEsq());
			else {
				r.setEsq(new No(i,false));
				r.getEsq().setP(r);
				balanceamentoInsercao(r.getEsq());
			}
		} else if(r.getV() < i) {
			//Direita
			if (r.getDir() != null) insercao(i, r.getDir());
			else {
				r.setDir(new No(i, false));
				r.getDir().setP(r);
				balanceamentoInsercao(r.getDir());
			}
		} else return;
	}*/

/*	//Forma mais Visual de Entendimento do Codigo
	public void balanceamentoEspecifico(No filho) {
		//Caso01
		if (filho.getP().getP().getEsq().isCor() && filho.getP().getP().getDir().isCor()) {
		    filho.getP().getP().getEsq().setCor(false);
			filho.getP().getP().getDir().setCor(false);
			filho.setCor(true);
			if (filho.getP().getP() != raiz)
				filho.getP().getP().setCor(true);
			balanceamentoEspecifico(filho.getP());
		}

		if (filho.getP().getEsq() == filho) {
			//Caso02
			if (filho.getP().getP().getEsq() == filho.getP()) {
				if (!filho.getP().getP().getDir().isCor() || filho.getP().getP().getDir() == null && filho.getP().isCor()) {
					rotacao_dir(filho.getP());
					balanceamentoEspecifico(filho.getP());
				}
			}
			else {
			    if (!filho.getP().getP().getEsq().isCor() || filho.getP().getP().getEsq() == null && filho.getP().isCor()) {
					rotacao_dir(filho);
					rotacao_esq(filho.getP());
					balanceamentoEspecifico(filho.getP());
				}
			}

			} else if (filho.getP().getDir() == filho) {
				if (filho.getP().getP().getEsq() == filho.getP()) {
					if (!filho.getP().getP().getDir().isCor() || filho.getP().getP().getDir() == null && filho.getP().isCor()) {
						rotacao_esq(filho);
						rotacao_dir(filho.getP());
						balanceamentoEspecifico(filho.getP());
					}
				}
				else {
					if (!filho.getP().getP().getEsq().isCor() || filho.getP().getP().getEsq() == null && filho.getP().isCor()) {
						rotacao_dir(filho.getP());
						balanceamentoEspecifico(filho.getP());
					}
				}
			}
	}*/


    public void transplant(No x, No y) {
        if (x.p == Arvore.nil) this.raiz = y;
        else if (x == x.p.esq) x.p.esq = y;
        else x.p.dir = y;
        y.p = x.p;
    }

    public void remove(int n) {
        No z = this.encontra(n);
        No x, y = z;
        boolean cordey = y.Cor;

        if (z.v == n) {
            if (z.esq == Arvore.nil) {
                x = z.dir;
                this.transplant(z, z.dir);
            } else if (z.dir == Arvore.nil) {
                x = z.esq;
                this.transplant(z, z.esq);
            } else {
                y = z.sucessor();
                cordey = y.Cor;
                x = y.dir;

                if (y.p == z) x.p = y;
                else {
                    this.transplant(y, y.dir);
                    y.dir = z.dir;
                    y.dir.p = y;
                }
                this.transplant(z, y);
                y.esq = z.esq;
                y.esq.p = y;
                y.Cor = z.Cor;
            }

            if (!cordey) this.BalanceamentoRemocao(x);
        }
    }

    private void BalanceamentoRemocao(No n) {
        No x;

        while (n != this.raiz && !n.Cor) {
            if (n == n.p.esq) {
                x = n.p.dir;

                if (x.Cor) { // caso 1
                    x.Cor = false;
                    n.p.Cor = true;
                    this.rotacao_esq(n.p);
                    x = n.p.dir;
                }
                if (!x.esq.Cor && !x.dir.Cor) { // caso 2
                    x.Cor = true;
                    n = n.p;
                } else {
                    if (!x.dir.Cor) { // caso 3
                        x.esq.Cor = false;
                        x.Cor = true;
                        this.rotacao_dir(x);
                        x = n.p.dir;
                    }
                    // caso 4
                    x.Cor = n.p.Cor;
                    n.p.Cor = false;
                    x.dir.Cor = false;
                    this.rotacao_esq(n.p);
                    n = this.raiz;
                }
            } else {
                x = n.p.esq;

                if (x.Cor) { // caso 1
                    x.Cor = false;
                    n.p.Cor = true;
                    this.rotacao_dir(n.p);
                    x = n.p.esq;
                }
                if (!x.esq.Cor && !x.dir.Cor) { // caso 2
                    x.Cor = true;
                    n = n.p;
                } else {
                    if (!x.esq.Cor) { // caso 3
                        x.dir.Cor = false;
                        x.Cor = true;
                        this.rotacao_esq(x);
                        x = n.p.esq;
                    }
                    // caso 4
                    x.Cor = n.p.Cor;
                    n.p.Cor = false;
                    x.esq.Cor = false;
                    this.rotacao_dir(n.p);
                    n = this.raiz;
                }
            }
        }
        n.Cor = false;
    }

    private void balanceamentoInsercao(No z) {
        No y;
        while (z.p.Cor) {
            if (z.p == z.p.p.esq) {
                y = z.p.p.dir;
                if (y.Cor) { // caso 1 (tio Vermelho):
                    // muda a cor do pai e do tio para preto e dos avos para vermelho.
                    // Entao, sobe dois niveis na arvore.
                    z.p.Cor = false;
                    y.Cor = false;
                    z.p.p.Cor = true;
                    z = z.p.p;
                } else { // Ou seja, tio � preto
                    if (z == z.p.dir) { // caso 2
                        z = z.p;
                        this.rotacao_esq(z);
                    }
                    // caso 3
                    z.p.Cor = false;
                    z.p.p.Cor = true;
                    this.rotacao_dir(z.p.p);
                }
            } else {
                y = z.p.p.esq;
                if (y.Cor) { // caso 1
                    y.Cor = z.p.Cor = false;
                    z.p.p.Cor = true;
                    z = z.p.p;
                } else {
                    if (z == z.p.esq) { // caso 2
                        z = z.p;
                        this.rotacao_dir(z);
                    }
                    // caso 3
                    z.p.Cor = false;
                    z.p.p.Cor = true;
                    this.rotacao_esq(z.p.p);
                }
            }
        }
        this.raiz.Cor = false;
    }


	/*Ess
	public void rotacao_dir(No x) {
		if (x.getDir() == null) {
			x.setCor(false);
			x.getP().setEsq(null);
			x.setDir(x.getP());
			x.getDir().setCor(true);
			x.getEsq().setCor(true);
			if(x.getDir() != raiz) {
				x.setP(x.getDir().getP());
				x.getDir().setP(x);
			}
			else {
				x.setP(null);
				x.getDir().setP(x);
				this.raiz = x;
			}
		}
		else {
			x.setCor(false);
			x.getP().setEsq(x.getDir());
			x.setDir(x.getP());
			x.getDir().setCor(true);
			x.getEsq().setCor(true);
			if(x.getDir() != raiz) {
				x.setP(x.getDir().getP());
				x.getDir().setP(x);
			}
			else {
				x.setP(null);
				x.getDir().setP(x);
				this.raiz = x;
			}
		}
	}


	public void rotacao_esq(No x) {

		if (x.getDir() == null) {
			x.setCor(false);
			x.getP().setDir(null);
			x.setEsq(x.getP());
			x.getDir().setCor(true);
			x.getEsq().setCor(true);
			if(x.getDir() != raiz) {
				x.setP(x.getEsq().getP());
				x.getEsq().setP(x);
			}
			else {
				x.setP(null);
				x.getEsq().setP(x);
				this.raiz = x;
			}
		}
		else {
			x.setCor(false);
			x.getP().setDir(x.getEsq());
			x.setEsq(x.getP());
			x.getDir().setCor(true);
			x.getEsq().setCor(true);
			if(x.getEsq() != raiz) {
				x.setP(x.getEsq().getP());
				x.getEsq().setP(x);
			}
			else {
				x.setP(null);
				x.getEsq().setP(x);
				this.raiz = x;
			}
		}
	}*/

    private void rotacao_esq(No x) {
        No y = x.dir;
        x.dir = y.esq;
        if (y.esq != Arvore.nil) y.esq.p = x;
        y.p = x.p;
        if (x.p == Arvore.nil) this.raiz = y;
        else if (x == x.p.esq) x.p.esq = y;
        else x.p.dir = y;
        y.esq = x;
        x.p = y;
    }

    private void rotacao_dir(No x) {
        No y = x.esq;
        x.esq = y.dir;
        if (y.dir != Arvore.nil) y.dir.p = x;
        y.p = x.p;
        if (x.p == Arvore.nil) this.raiz = y;
        else if (x == x.p.esq) x.p.esq = y;
        else x.p.dir = y;
        y.dir = x;
        x.p = y;
    }

    public void grafico() {
        System.out.println("digraph Arvore {");
        this.raiz.grafico();
        System.out.println("\tnil [style = filled, fillcolor = black, fontcolor = white];");
        System.out.println("}");
    }

    public void inorderWalk() {
        this.raiz.printFullNodos();
    }

    public No minimo() {
        return this.raiz.minimo();
    }

    public No maximo() {
        return this.raiz.maximo();
    }

    public No encontra (int n) {
        return this.raiz.encontra(n);
    }
}