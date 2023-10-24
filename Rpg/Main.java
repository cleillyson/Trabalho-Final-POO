package Projetos.Rpg;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Personagem personagem;
        int escolha = 0;
        boolean escolhaValida = false;

        System.out.println("Escolha uma raça[1 a 3]: ");
        System.out.println("1. Humano\n2. Elfo\n3. Orc");

        while (!escolhaValida) {
            try {
                escolha = input.nextInt();
                if (escolha >= 1 && escolha <= 3) {
                    escolhaValida = true;
                } else {
                    System.out.println("Escolha inválida. Digite novamente.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Digite novamente.");
                input.nextLine(); // Limpa o buffer de entrada
            }
        }

        switch (escolha) {
            case 1:
                personagem = new Humano();
                break;
            case 2:
                personagem = new Elfo();
                break;
            case 3:
                personagem = new Orc();
                break;
            default:
                throw new IllegalStateException("Valor invalido: " + escolha);
        }

        System.out.println("Vida do personagem: " + personagem.getVida());
    }
}
