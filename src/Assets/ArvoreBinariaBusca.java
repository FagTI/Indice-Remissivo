package Assets;

public class ArvoreBinariaBusca
{
    class Nodo {
        public PalavraChave palavraChave = new PalavraChave();
        public Nodo esquerdo;
        public Nodo direito;

        public Nodo(String elemento) {
            this.palavraChave.chave = elemento;
            this.esquerdo = null;
            this.direito = null;
        }
    }

    public Nodo raiz;
    public int nElementos;

    public ArvoreBinariaBusca() {
        this.raiz = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.raiz == null;
    }

    public void imprimeEmLargura() {

        FilaDinamica<Nodo> fila = new FilaDinamica();

        fila.enfileira(this.raiz);
        while (!fila.estaVazia()) {

            Nodo cursor = fila.desenfileira();

            System.out.print(cursor.palavraChave + " ");

            if (cursor.esquerdo != null) {
                fila.enfileira(cursor.esquerdo);
            }

            if (cursor.direito != null) {
                fila.enfileira(cursor.direito);
            }
        }

        System.out.println();

    }

    public void imprimePreOrdem() {
        this.preOrdem(this.raiz);
        System.out.println();
    }

    public void imprimePosOrdem() {
        this.posOrdem(this.raiz);
        System.out.println();
    }

    public void imprimeEmOrdem() {
        this.emOrdem(this.raiz);
        //System.out.println();
    }

    public void preOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        System.out.print(nodo.palavraChave + " ");
        this.preOrdem(nodo.esquerdo);
        this.preOrdem(nodo.direito);
    }

    public void posOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.posOrdem(nodo.esquerdo);
        this.posOrdem(nodo.direito);
        System.out.print(nodo.palavraChave + " ");
    }

    public void emOrdem(Nodo nodo) {

        if (nodo == null)
            return;

        this.emOrdem(nodo.esquerdo);
        System.out.print(nodo.palavraChave.chave + " ");
        nodo.palavraChave.ocorrencias.imprime();
        this.emOrdem(nodo.direito);
    }

    public String tratamentoArvore(String elemento) {
        String str = elemento.toLowerCase();

        str = str.replaceAll("[^-a-zA-Z0-9À-ÿ]", " ");
        str = str.replaceAll("à|á|â|ã|ä|å", "a");
        str = str.replaceAll("è|é|ê|ë", "e");
        str = str.replaceAll("ì|í|î|ï", "i");
        str = str.replaceAll("ð|ò|ó|ô|õ|ö|ø", "o");
        str = str.replaceAll("ù|ú|û|ü", "u");
        str = str.replaceAll("ý|ÿ", "y");
        str = str.replaceAll("þ", "p");
        str = str.replaceAll("ñ", "n");

        return str;
    }

    public void insere(String elemento) {
        this.insere(elemento, this.raiz);
    }

    public void insere(String elemento, Nodo nodo) {

        String str = tratamentoArvore(elemento);

        Nodo novo = new Nodo(str);

        if (nodo == null) {
            this.raiz = novo;
            this.nElementos++;
            return;
        }

        if (str.compareTo(nodo.palavraChave.chave) < 0) {
            if (nodo.esquerdo == null) {
                nodo.esquerdo = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(str, nodo.esquerdo);
            }
        }

        if (str.compareTo(nodo.palavraChave.chave) > 0) {
            if (nodo.direito == null) {
                nodo.direito = novo;
                this.nElementos++;
                return;
            } else {
                this.insere(str, nodo.direito);
            }
        }

    }

    private Nodo maiorElemento(Nodo nodo) {
        while (nodo.direito != null) {
            nodo = nodo.direito;
        }
        return nodo;
    }

    private Nodo menorElemento(Nodo nodo) {
        while (nodo.esquerdo != null) {
            nodo = nodo.esquerdo;
        }
        return nodo;
    }

    public boolean remove(String elemento) {
        return this.remove(elemento, this.raiz) != null;
    }

    private Nodo remove(String elemento, Nodo nodo) {

        if (nodo == null) {
            System.out.println("Valor não encontrado");
            return null;
        }

        if (elemento.compareTo(nodo.palavraChave.chave) < 0) {
            nodo.esquerdo = this.remove(elemento, nodo.esquerdo);
        } else if (elemento.compareTo(nodo.palavraChave.chave) > 0) {
            nodo.direito = this.remove(elemento, nodo.direito);
        } else {

//	    	if(nodo.esquerdo == null && nodo.direito == null) {
//	    		return null;
//	    	}

            if (nodo.esquerdo == null) {
                this.nElementos--;
                return nodo.direito;
            } else if (nodo.direito == null) {
                this.nElementos--;
                return nodo.esquerdo;
            } else {
                Nodo substituto = this.menorElemento(nodo.direito);
                nodo.palavraChave = substituto.palavraChave;
                this.remove(substituto.palavraChave.chave, nodo.direito);
            }
        }

        return nodo;
    }

    public boolean busca(String elemento) {
        return this.busca(elemento, this.raiz);
    }

    public boolean busca(String elemento, Nodo nodo) {

        if (nodo == null) {
            return false;
        }

        if (elemento.compareTo(nodo.palavraChave.chave) < 0) {
            return this.busca(elemento, nodo.esquerdo);
        } else if (elemento.compareTo(nodo.palavraChave.chave) > 0) {
            return this.busca(elemento, nodo.direito);
        } else {
            return true;
        }
    }

    public void inserePosicaoLinhaHash(String elemento, int linha) {
        inserePosicaoLinha(elemento, this.raiz, linha);
    }

    public void inserePosicaoLinha(String elemento, Nodo nodo, int linha) {

        if (nodo == null) {
            return;
        }

        if (elemento.compareTo(nodo.palavraChave.chave) < 0) {
            this.inserePosicaoLinha(elemento, nodo.esquerdo, linha);
        } else if (elemento.compareTo(nodo.palavraChave.chave) > 0) {
            this.inserePosicaoLinha(elemento, nodo.direito, linha);
        } else {
            if(nodo.palavraChave.ocorrencias.contem(linha) == false) {
                nodo.palavraChave.ocorrencias.enfileira(linha);
            }
        }
    }

    private int altura(Nodo nodo) {

        if (nodo == null) {
            return -1;
        }

        int alturaEsquerda = this.altura(nodo.esquerdo) + 1;
        int alturaDireita = this.altura(nodo.direito) + 1;

        int altura = alturaEsquerda > alturaDireita ? alturaEsquerda : alturaDireita;

        return altura;

    }

    public int altura() {
        return this.altura(this.raiz);
    }

    public int verificarValorDaString(String elemento) {

        int somatorio = 0;

        for (int i = 0; i < elemento.length(); i++) {
            char caractere = elemento.charAt(i);
            int valorASCII = (int) caractere;
            somatorio += valorASCII;
        }

        return somatorio;
    }
}
