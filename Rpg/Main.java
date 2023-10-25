package Projetos.Rpg;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Personagem personagem;
        Racas racaEscolhida;
        int escolha = 0;
        boolean escolhaValida = false;
        String nome;

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
            }
        }

        racaEscolhida = switch (escolha) {
            case 1 -> Racas.HUMANO;
            case 2 -> Racas.ELFO;
            case 3 -> Racas.ORC;
            default -> throw new IllegalStateException("Valor invalido: " + escolha);
        };

        escolha = 0;
        escolhaValida = false;
        System.out.println("Escolha uma Classe[1 a 3]: ");
        System.out.println("1. Guerreiro\n2. Arqueiro\n3. Elfo");

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
            }
        }

        System.out.println("Digite seu nome: ");
        nome = input.next();
        personagem = switch (escolha) {
            case 1 -> new Warrior(nome,racaEscolhida);
            case 2 -> new Archer(nome,racaEscolhida);
            case 3 -> new Mage(nome,racaEscolhida);
            default -> throw new IllegalStateException("Valor invalido: " + escolha);
        };

        System.out.println("Vida do personagem: " + personagem.getVida());
    }
}
