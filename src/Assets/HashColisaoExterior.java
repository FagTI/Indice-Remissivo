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
        String palavra = elemento.toLowerCase();

        char primeiroCaractere = palavra.charAt(0);

        int valorDaASCII = (int) primeiroCaractere;

        return valorDaASCII - 97;
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
