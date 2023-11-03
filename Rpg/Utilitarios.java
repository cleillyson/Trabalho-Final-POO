package Projetos.Rpg.src;
import java.io.IOException;
import java.util.Scanner;
public class Utilitarios {
    Scanner input = new Scanner(System.in);
    public int escolha(Boolean escolhaValida,int max){
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
    public void limparTela() throws IOException, InterruptedException {
        System.out.print("\033c");
    }
    public void print(int valor){
        if (valor == 0){
            System.out.println("\nEscolha uma raça[1 a 3]: ");
            System.out.println("1. Humano\n2. Elfo\n3. Orc");
        }
        else if (valor == 1) {
            System.out.println("\nEscolha uma Classe[1 a 3]: ");
            System.out.println("1. Guerreiro\n2. Arqueiro\n3. Elfo");
        }
        else if (valor == 2) {
            System.out.println("\nDigite seu nome: ");
        }
        else if (valor == 3) {
            System.out.println("\nSelecione uma opção[1 a 5]: ");
            System.out.println("1. Batalha\n2. Bolsa\n3. Subir andar\n4. Descer andar\n5. Descansar");
        }
        else if (valor == 4) {
            System.out.println("\nSeu turno: ");
            System.out.println("Selecione uma opção[1 a 4]");
            System.out.println("1. Ataque leve\n2. Ataque normal\n3. Ataque pesado");
        }
        else if (valor == 5) {
            System.out.println("\nTurno inimigo: ");
        }
    }
    public void print(String iRaca,String hNome,double Ivida,double Hvida,double iVidaMax,double hVidaMax, int iLevel,int hLevel){
        System.out.printf("%s\t     hp:%.2f/%.2f    Level: %d\n\n", iRaca, Ivida, iVidaMax, iLevel);
        System.out.printf("%s\t     hp:%.2f/%.2f    Level: %d\n", hNome, Hvida, hVidaMax, hLevel);

    }
}

