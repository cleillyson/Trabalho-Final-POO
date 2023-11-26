package Projetos.Rpg.src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
public class Utilitarios {
    static int limiteSaves = 5;
    static Path currentDir = Paths.get("");
    static Path file = currentDir.resolve("Dados.txt");
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
                System.out.println("Escolha uma raça[1 a 3]: ");
                System.out.println("1. Humano\n2. Elfo\n3. Orc");
                break;
            case 1:
                System.out.println("Escolha uma Classe[1 a 3]: ");
                System.out.println("1. Guerreiro\n2. Arqueiro\n3. Mago");
                break;
            case 2:
                System.out.println("Digite seu nome: ");
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
            case 6:
                System.out.println("Selecione uma opção[1 a 5]");
                System.out.println("1. Salvar\n2. Carregar\n3. Menu inicial\n4. Voltar");
                break;
            case 7:
                System.out.println("Este jogo é uma simulação de um RPG \n- Crie seu personagem escolhendo uma classe e uma raça. \n- Derrote os inimigos em cada andar da torre e chegue até o final!\n- Descansar pode chamar inimigos, cuidado!!");
                break;
            case 8:
                System.out.println("Game over");
            default:
                break;
        }
    }
    public static void print(Heroi personagem){
        System.out.printf("Nome: %s\t\tRaça: %s\t\tTrilha: %s\nAndar: %d\t\tLevel: %d\t\tExp: %.2f/%.2f\nHp: %.2f/%.2f\t\tStrength: %.2f\t\tVelocidade: %.2f\n",
                personagem.getNome(),personagem.getRaca(),personagem.getTrilha(),personagem.getAndar(), personagem.getLevel(),personagem.getExpAtual(),personagem.getExpUp(),
                personagem.getVidaAtual(), personagem.getVidaMAx(),personagem.getStrength(),personagem.getVelocidadeMax());
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
    private static int mostrarDados(Heroi personagem){
        var lineNumber = 1;
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] dados = line.split(";");
                String nome = dados[0].trim();
                int level = Integer.parseInt(dados[6].trim()); // Considerando que o level está na posição 6
                System.out.println( "Save "+ lineNumber + " Nome: " + nome + ", Level: " + level);
                lineNumber++;
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException x) {
            System.err.format("Erro ao processar o arquivo: %s%n", x);
        }
        return lineNumber;
    }
    public static void carregarDados(Heroi personagem) throws IOException {
        long fileSize = Files.size(file);
        var lineNumber = mostrarDados(personagem);
        if (fileSize != 0){
            var escolha = escolha(false,lineNumber);
            lineNumber = 1;
            try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (lineNumber == escolha) {
                        String[] dados = (line.split(";"));
                        personagem.setNome(dados[0].trim());
                        personagem.setRaca(dados[1].trim());
                        personagem.setVidaAtual(Double.parseDouble(dados[2].replace(',', '.').trim()));
                        personagem.setVidaMax(Double.parseDouble(dados[3].replace(',', '.').trim()));
                        personagem.setStrength(Double.parseDouble(dados[4].replace(',', '.').trim()));
                        personagem.setVelocidadeMax(Double.parseDouble(dados[5].replace(',', '.').trim()));
                        personagem.setVelocidadeAtual(Double.parseDouble(dados[5].replace(',', '.').trim()));
                        personagem.setLevel(Integer.parseInt((dados[6].trim())));
                        personagem.setExpUp(Double.parseDouble((dados[7].replace(',', '.').trim())));
                        personagem.setExpAtual(Double.parseDouble((dados[8].replace(',', '.').trim())));
                        personagem.setTrilha(dados[9].trim());
                        personagem.setAndar(Integer.parseInt(((dados[10].trim()))));
                        break;
                    }
                    lineNumber++;
                }
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        else {
            System.out.println("Nenhum dado salvo");
            print();
        }
    }
    public static void salvarDados(Heroi personagem) {
        try {
            long lineNumber = Files.lines(file).count(); // Conta o número de linhas no arquivo
            if (lineNumber == 5) {
                removerDado(personagem);
            }
            String conteudo = criarConteudo(personagem); // Cria o conteúdo a ser salvo
            salvarNoArquivo(conteudo); // Salva no arquivo
            System.out.println("Salvo.");
            print();

        } catch (IOException e) {
            System.err.println("Erro ao processar o arquivo: " + e.getMessage());
        }
    }
    private static String criarConteudo(Heroi personagem) {
        return String.format("%s;%s;%.2f;%.2f;%.2f;%.2f;%d;%.2f;%.2f;%s;%d\n",
                personagem.getNome(), personagem.getRaca(),
                personagem.getVidaAtual(), personagem.getVidaMAx(),
                personagem.getStrength(), personagem.getVelocidadeMax(), personagem.getLevel(), personagem.getExpUp(), personagem.getExpAtual(),
                personagem.getTrilha(), personagem.getAndar());
    }
    private static void salvarNoArquivo(String conteudo) {
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset, StandardOpenOption.APPEND)) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar no arquivo: " + e.getMessage());
        }
    }
    private static void removerDado(Heroi personagem) throws IOException {
            var limiteEscolha = mostrarDados(personagem);
            System.out.println("Escolha um save para remover ");
            var escolha = escolha(false,limiteEscolha);

            List<String> linhas = Files.readAllLines(file, StandardCharsets.UTF_8);

            if (escolha >= 0 && escolha < linhas.size()) {
                linhas.remove(escolha); // Remove a linha especificada
            } else {
                System.out.println("Índice inválido.");
            }
            // Escreve o novo conteúdo de volta no arquivo
            Files.write(file, linhas, StandardCharsets.UTF_8);
            mostrarDados(personagem); // Mostra os dados após a remoção

    }
}