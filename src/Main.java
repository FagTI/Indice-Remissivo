import Assets.HashColisaoExterior;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        leituraDosArquivos();
    }
    public static void leituraDosArquivos() {
        HashColisaoExterior Hash = new HashColisaoExterior(26);

        String fileName = "meuIndice.txt";

        int contadorDaLinha = 0;
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
                contadorDaLinha++;

                Scanner scannerDaLinha = new Scanner(linha);
                while (scannerDaLinha.hasNext()) {
                    String palavrasDaLinha = scannerDaLinha.next();

                    palavrasDaLinha = palavrasDaLinha.toLowerCase();
                    palavrasDaLinha = palavrasDaLinha.replaceAll("[^-a-zA-ZÀ-ÿ]","");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("à|á|â|ã|ä|å", "a");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("è|é|ê|ë", "e");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("ì|í|î|ï", "i");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("ð|ò|ó|ô|õ|ö|ø", "o");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("ù|ú|û|ü", "u");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("ý|ÿ", "y");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("þ", "p");
                    palavrasDaLinha = palavrasDaLinha.replaceAll("ñ", "n");

                    if(palavrasDaLinha != "") {
                        if(Hash.busca(palavrasDaLinha)){
                            Hash.colocarLinha(palavrasDaLinha, contadorDaLinha);
                        }
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
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Palavra errada: " + e.getLocalizedMessage());
        }
    }
}