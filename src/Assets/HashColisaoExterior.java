package Assets;

public class HashColisaoExterior {
    public ArvoreBinariaBusca vetor[];
    public int nElementos;

    public HashColisaoExterior(int capacidade) {
        this.vetor = new ArvoreBinariaBusca[capacidade];
        for (int i = 0; i < vetor.length; i++) {
            this.vetor[i] = new ArvoreBinariaBusca();
        }
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public void imprimeIndice() {
        for(int i = 0; i < vetor.length; i++) {
            this.vetor[i].imprimeEmOrdem();
        }
    }

    public void imprime() {
        System.out.println("Chave\tValor");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(i + " -->\t");
            vetor[i].imprimeEmOrdem();
        }
    }

    private int funcaoHashDiv(String elemento) {
        String str = tratamentoHash(elemento);

        char primeiroCaractere = str.charAt(0);

        int valorDaASCII = (int) primeiroCaractere;

        return valorDaASCII - 97;
    }

    public String tratamentoHash(String elemento) {
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
        str = str.replaceAll("ç", "c");

        return str;
    }

    public void insere(String elemento) {
        int endereco = funcaoHashDiv(elemento);
        this.vetor[endereco].insere(elemento);
        this.nElementos++;
    }

    public boolean remove(String elemento) {
        int endereco = funcaoHashDiv(elemento);
        boolean removeu = this.vetor[endereco].remove(elemento);

        if(removeu) this.nElementos--;

        return removeu;
    }

    public boolean busca(String elemento) {
        int endereco = funcaoHashDiv(elemento);
        return this.vetor[endereco].busca(elemento);
    }

    public void colocarLinha(String elemento, int linha) {
        int endereco = funcaoHashDiv(elemento);
        this.vetor[endereco].inserePosicaoLinhaHash(elemento, linha);
    }
}
