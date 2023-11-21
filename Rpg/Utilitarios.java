package Projetos.Rpg.src;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class Utilitarios {
    static Path currentDir = Paths.get("");
    static Path file = currentDir.resolve("src/Dados.txt");
    static Charset charset = StandardCharsets.UTF_8;
    static Scanner input = new Scanner(System.in);
    public static int escolha(Boolean escolhaValida,int max){
        int escolha = 0;
        while (!escolhaValida) {
            try {
                escolha = input.nextInt();
                if (escolha >= 1 && escolha <= max) {
                    escolhaValida = true;
                } else {
                    System.out.println("\nEscolha inválida. Digite novamente.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("\nEntrada inválida. Digite novamente.");
                input.nextLine();
            }
        }
        return escolha;
    }
    public static void limparTela() throws IOException, InterruptedException {
        System.out.print("\033c");
    }
    //Print aperte enter e uma entrada vazia
    public static void print() {
        System.out.println("Pressione Enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print(int valor){
        switch (valor){
            case 0:
                System.out.println("\nEscolha uma raça[1 a 3]: ");
                System.out.println("1. Humano\n2. Elfo\n3. Orc");
                break;
            case 1:
                System.out.println("\nEscolha uma Classe[1 a 3]: ");
                System.out.println("1. Guerreiro\n2. Arqueiro\n3. Elfo");
                break;
            case 2:
                System.out.println("\nDigite seu nome: ");
                break;
            case 3:
                System.out.println("\nSeu turno: ");
                System.out.println("Selecione uma opção[1 a 3]");
                System.out.println("1. Ataque leve\n2. Ataque normal\n3. Ataque pesado");
                break;
            case 4:
                System.out.println("\nTurno inimigo: ");
                break;
            case 5:
                System.out.println("Selecione uma opção[1 a 3]: ");
                System.out.println("1. Start\n2. Load game\n3. Exit");
                break;
            default:
                break;
        }
    }
    public static void print(String iRaca,String hNome,double Ivida,double Hvida,double iVidaMax,double hVidaMax, int iLevel,int hLevel){
        System.out.printf("%s\t     hp:%.2f/%.2f    Level: %d\n\n", iRaca, Ivida, iVidaMax, iLevel);
        System.out.printf("%s\t     hp:%.2f/%.2f    Level: %d\n", hNome, Hvida, hVidaMax, hLevel);

    }
    public static void print(String hNome, int andar){
        System.out.printf("%s\t                 Andar: %d\n", hNome, andar);
        System.out.println("Selecione uma opção[1 a 5]: ");
        System.out.println("1. Batalha\n2. menu\n3. Subir andar\n4. Descer andar\n5. Descansar");
    }

    public static void carregarDados(Heroi personagem) throws IOException {
        long fileSize = Files.size(file);
        if (fileSize != 0){
        var lineNumber = 1;
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(",");
                String nome = dados[0].trim();
                int level = Integer.parseInt(dados[6].trim()); // Considerando que o level está na posição 6
                System.out.println( "Save "+ lineNumber + " Nome: " + nome + ", Level: " + level);
                lineNumber++;
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException x) {
            System.err.format("Erro ao processar o arquivo: %s%n", x);
        }

            var escolha = escolha(false,lineNumber);
            lineNumber = 1;
            try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (lineNumber == escolha) {
                        String[] dados = (line.split(","));
                        personagem.setNome(dados[0].trim());
                        personagem.setRaca(dados[1].trim());
                        personagem.setVidaAtual(Double.parseDouble(dados[2].trim()));
                        personagem.setVidaMax(Double.parseDouble(dados[3].trim()));
                        personagem.setStrength(Double.parseDouble(dados[4].trim()));
                        personagem.setVelocidadeMax(Double.parseDouble(dados[5].trim()));
                        personagem.setVelocidadeAtual(Double.parseDouble(dados[5].trim()));
                        personagem.setLevel(Integer.parseInt((dados[6].trim())));
                        personagem.setExpUp(Integer.parseInt((dados[8].trim())));
                        personagem.setExpAtual(Integer.parseInt((dados[7].trim())));
                        personagem.setTrilha(dados[9].trim());
                        personagem.setAndar(Integer.parseInt(((dados[10].trim()))));
                        break;
                    }
                    lineNumber++;
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }else {
            System.out.println("Nenhum dado salvo");
            print();
        }
    }
}