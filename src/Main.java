import Assets.HashColisaoExterior;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        leituraDosArquivos();
    }
    public static void leituraDosArquivos() {
        HashColisaoExterior Hash = new HashColisaoExterior(26);
        try {
            // Abrindo oarquivo das chave
            File chaves = new File("C:\\Users\\yango\\OneDrive\\Área de Trabalho\\Unifor\\3° semestre\\Estrutura de Dados\\Índice Remissivo\\chaves.txt");
            Scanner scannerDasChaves = new Scanner(chaves);

            // Lendo o arquivo das chaves linha por linha
            while (scannerDasChaves.hasNextLine()) {
                PalavraChave palavraChave = new PalavraChave();
                palavraChave.chave = scannerDasChaves.next();
                Hash.insere(palavraChave.chave);
            }

            Hash.imprimeIndice();
            scannerDasChaves.close();

            // Abrindo o arquivo do texto
            File texto = new File("C:\\Users\\yango\\OneDrive\\Área de Trabalho\\Unifor\\3° semestre\\Estrutura de Dados\\Índice Remissivo\\texto.txt");
            Scanner scannerDoTexto = new Scanner(texto);

            // Lendo o arquivo do texto linha por linha
            while (scannerDoTexto.hasNextLine()) {
                String linha = scannerDoTexto.nextLine();
                //System.out.println("Texto: " + linha);
            }
            scannerDoTexto.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
}