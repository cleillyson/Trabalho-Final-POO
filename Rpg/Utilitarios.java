package Projetos.Rpg.src;
import java.io.IOException;
import java.util.Scanner;
public class Utilitarios {
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
            }
        }
        return escolha;
    }
    public static void limparTela() throws IOException, InterruptedException {
        System.out.print("\033c");
    }
    //Print aperte enter e uma entrada vazia
    public static void print(){
        System.out.println("Aperte enter: ");
        input.nextLine();
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
                System.out.println("\nSelecione uma opção[1 a 5]: ");
                System.out.println("1. Batalha\n2. Bolsa\n3. Subir andar\n4. Descer andar\n5. Descansar");
                break;
            case 4:
                System.out.println("\nSeu turno: ");
                System.out.println("Selecione uma opção[1 a 3]");
                System.out.println("1. Ataque leve\n2. Ataque normal\n3. Ataque pesado");
                break;
            case 5:
                System.out.println("\nTurno inimigo: ");
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
}