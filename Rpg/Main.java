package Projetos.Rpg;

import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
        //Variaveis
        Scanner input = new Scanner(System.in);
        Heroi personagem;
        Inimigo inimigo;
        Racas racaEscolhida;
        Racas racaInimigo;
        Trilhas trilhaEscolhida;
        int escolha = 0;
        boolean escolhaValida = false;
        String nome;
        boolean gameover = true;
        Random random = new Random();
        boolean batalhaOn = true;

        //Seleção de raça e trilha (classe)
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
        trilhaEscolhida = switch (escolha) {
            case 1 -> Trilhas.Warrior;
            case 2 -> Trilhas.Archer;
            case 3 -> Trilhas.Mage;
            default -> throw new IllegalStateException("Valor invalido: " + escolha);
        };

        System.out.println("Digite seu nome: ");
        nome = input.next();
        personagem = new Heroi(nome, racaEscolhida, trilhaEscolhida);

        while (gameover){

            int valor = random.nextInt(1,4);
            racaInimigo = switch (valor) {
                case 1 -> Racas.GOBLIN;
                case 2 -> Racas.LOBO;
                default -> Racas.SLIME;
            };

            System.out.println("Selecione uma opção[1 a 5]: ");
            System.out.println("1. Batalha\n2. Bolsa\n3. Subir andar\n4. Descer andar\n5. Descansar");

            escolhaValida = false;
            escolha = 0;

            while (!escolhaValida) {
                try {
                    escolha = input.nextInt();
                    if (escolha >= 1 && escolha <= 5) {
                        escolhaValida = true;
                    } else {
                        System.out.println("Escolha inválida. Digite novamente.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Entrada inválida. Digite novamente.");
                }
            }

            if (escolha == 1){
                inimigo = new Inimigo(racaInimigo, random.nextInt(personagem.getAndar()-1, personagem.getAndar()+2));
                while (batalhaOn) {

                    System.out.printf("%s\t     hp:%.2f/%.2f    Level: %d\n\n", inimigo.getRaca(), inimigo.getVidaAtual(), inimigo.getVidaMAx(), inimigo.getLevel());
                    System.out.printf("%s\t     hp:%.2f/%.2f    Level: %d\n\n", personagem.getNome(), personagem.getVidaAtual(), personagem.getVidaMAx(), personagem.getLevel());

                    if (personagem.getVelocidadeAtual() >= inimigo.getVelocidadeAtual()){
                        escolhaValida = false;
                        System.out.println("Selecione uma opção[1 a 4]");
                        System.out.println("1. Ataque leve\n2. Ataque normal\n3. Ataque pesado");
                        while (!escolhaValida) {
                            try {
                                escolha = input.nextInt();
                                if (escolha >= 1 && escolha <= 4) {
                                    escolhaValida = true;
                                } else {
                                    System.out.println("Escolha inválida. Digite novamente.");
                                }
                            } catch (java.util.InputMismatchException e) {
                                System.out.println("Entrada inválida. Digite novamente.");
                            }
                        }
                        personagem.atacar(inimigo,escolha,random);
                        personagem.setVelocidadeAtual(personagem.getVelocidadeAtual() - inimigo.getVelocidadeMax());
                    }
                    else if (personagem.getVelocidadeAtual() <= inimigo.getVelocidadeAtual()) {
                        inimigo.atacar(personagem,random);
                        inimigo.setVelocidadeAtual(inimigo.getVelocidadeAtual() - personagem.getVelocidadeMax());
                    }

                    if(inimigo.getVelocidadeAtual() == 0 && personagem.getVelocidadeAtual() == 0){
                        inimigo.setVelocidadeAtual(inimigo.getVelocidadeMax());
                        personagem.setVelocidadeAtual(personagem.getVelocidadeMax());
                    }
                    if(personagem.getVidaAtual() == 0 || inimigo.getVidaAtual() == 0){
                        batalhaOn = false;
                    }

                }
                } else if (escolha == 2){
                gameover = false;
            } else if (escolha == 3){
                gameover = false;
            } else if (escolha == 4){

            }else {

            }
        }

    }
}
