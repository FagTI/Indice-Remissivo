package Assets;

public class FilaPrioridadeDinamica
{
    class Nodo {

        public int elemento;
        public Nodo proximo;
        public Nodo anterior;

        public Nodo(int elemento) {
            this.elemento = elemento;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private Nodo inicio;
    private Nodo fim;
    private int nElementos;

    public FilaPrioridadeDinamica() {
        this.inicio = null;
        this.fim = null;
        this.nElementos = 0;
    }

    public int tamanho() {
        return this.nElementos;
    }

    public boolean estaVazia() {
        return this.nElementos == 0;
    }

    public void imprime() {
        System.out.print("[");
        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            System.out.print(cursor.elemento + " ");
            cursor = cursor.proximo;
        }
        System.out.println("]");
    }

    public void insere(int elemento) {

        Nodo novo = new Nodo(elemento);

        int pos = -1;

        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            if (cursor.elemento > elemento) {
                pos = i;
                break;
            }
            cursor = cursor.proximo;
        }

        if (this.estaVazia()) {
            this.inicio = novo;
            this.fim = novo;

        } else {

            if (pos == 0) {
                novo.proximo = this.inicio;

                this.inicio.anterior = novo;
                this.inicio = novo;
            } else if (pos == -1) {
                novo.anterior = this.fim;

                this.fim.proximo = novo;
                this.fim = novo;
            } else {
                novo.proximo = cursor;
                novo.anterior = cursor.anterior;

                cursor.anterior.proximo = novo;
                cursor.anterior = novo;
            }
        }

        this.nElementos++;
    }

    public Integer remove() {

        if (this.estaVazia()) {
            System.out.println("Lista vazia! Não é possível remover.");
            return null;
        }

        Nodo maiorPrioridade = this.fim;

        if (this.nElementos == 1) {

            this.inicio = null;
            this.fim = null;
        } else {

            this.fim = maiorPrioridade.anterior;

            this.fim.proximo = null;

            maiorPrioridade.anterior = null;
            maiorPrioridade.proximo = null;
        }

        this.nElementos--;

        return maiorPrioridade.elemento;
    }

    public Integer espia() {

        if (this.estaVazia()) {
            System.out.println("Lista vazia! Não é possível espiar.");
            return null;
        }

        Nodo maiorPrioridade = this.fim;

        return maiorPrioridade.elemento;
    }

    public boolean contem(int elemento) {

        Nodo cursor = this.inicio;
        for (int i = 0; i < this.nElementos; i++) {
            if (cursor.elemento == elemento) {
                return true;
            }
            cursor = cursor.proximo;
        }

        return false;
    }
}
