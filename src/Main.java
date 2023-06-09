import Assets.HashColisaoExterior;
import Assets.PalavraChave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        leituraDosArquivos();
    }
    public static void leituraDosArquivos() {

        int contador = 0;

        HashColisaoExterior Hash = new HashColisaoExterior(26);
        try {
            // Abrindo oarquivo das chave
            File chaves = new File("C:\\Users\\yango\\OneDrive\\Área de Trabalho\\Unifor\\3° semestre\\Estrutura de Dados\\Índice Remissivo Exemplos\\chaves.txt");
            Scanner scannerDasChaves = new Scanner(chaves);

            // Lendo o arquivo das chaves linha por linha
            while (scannerDasChaves.hasNextLine()) {
                String chave = scannerDasChaves.next();
                Hash.insere(chave);
            }
            scannerDasChaves.close();

            // Abrindo o arquivo do texto
            File texto = new File("C:\\Users\\yango\\OneDrive\\Área de Trabalho\\Unifor\\3° semestre\\Estrutura de Dados\\Índice Remissivo Exemplos\\texto.txt");
            Scanner scannerDoTexto = new Scanner(texto);

            // Lendo o arquivo do texto linha por linha
            while (scannerDoTexto.hasNextLine()) {
                String linha = scannerDoTexto.nextLine();
                contador++;

                Scanner scannerDaLinha = new Scanner(linha);
                while (scannerDaLinha.hasNext()) {
                    String palavrasDaLinha = scannerDaLinha.next();
                    palavrasDaLinha = palavrasDaLinha.replace(",","");

                    if(Hash.busca(palavrasDaLinha)){
                        Hash.colocarLinha(palavrasDaLinha, contador);
                    }

                }
                //System.out.println("Texto: " + linha);
            }
            Hash.imprimeIndice();
            scannerDoTexto.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
}