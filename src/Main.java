import Assets.HashColisaoExterior;
import Assets.PalavraChave;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        leituraDosArquivos();
    }
    public static void leituraDosArquivos() {
        HashColisaoExterior Hash = new HashColisaoExterior(26);

        String fileName = "meuIndice.txt";

        int contador = 0;
        try {
            // Criar um FileOutputStream com o nome do arquivo
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            // Criar um PrintStream para redirecionar a saída para o arquivo
            PrintStream printStream = new PrintStream(fileOutputStream);

            // Redirecionar a saída para o PrintStream
            System.setOut(printStream);

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

                    palavrasDaLinha = palavrasDaLinha.replaceAll("[^-a-zA-Z0-9À-ÿ]","");
                    palavrasDaLinha = palavrasDaLinha.toLowerCase();

                    if(Hash.busca(palavrasDaLinha)){
                        Hash.colocarLinha(palavrasDaLinha, contador);
                    }

                }
                //System.out.println("Texto: " + linha);
            }

            Hash.imprimeIndice();

            scannerDoTexto.close();
            printStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}