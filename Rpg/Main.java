package Projetos.Rpg.src;
import java.util.Random;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Variaveis do main
        Random random = new Random();
        Heroi personagem = null;
        Inimigo inimigo;
        Racas racaEscolhida;
        Racas racaInimigo;
        Trilhas trilhaEscolhida;
        int escolha = 0;
        String nome;
        boolean game = true;
        Utilitarios.limparTela();
        Utilitarios.print(7);
        Utilitarios.print();
        Utilitarios.limparTela();
        
        //laço game on
        while (game) {
            boolean gameover = true;
            Utilitarios.print(5);
            escolha = Utilitarios.escolha(false, 3);

            //if Seleção do menu inicial
            if (escolha == 1) {
                Utilitarios.limparTela();
                //Seleção de raça, trilha (classe) e nome
                Utilitarios.print(0);
                escolha = Utilitarios.escolha(false, 3);
                Utilitarios.limparTela();
                racaEscolhida = switch (escolha) {
                    case 1 -> Racas.HUMANO;
                    case 2 -> Racas.ELFO;
                    case 3 -> Racas.ORC;
                    default -> throw new IllegalStateException("Valor invalido: " + escolha);
                };

                Utilitarios.print(1);
                escolha = Utilitarios.escolha(false, 3);
                Utilitarios.limparTela();
                trilhaEscolhida = switch (escolha) {
                    case 1 -> Trilhas.Warrior;
                    case 2 -> Trilhas.Archer;
                    case 3 -> Trilhas.Mage;
                    default -> throw new IllegalStateException("Valor invalido: " + escolha);
                };

                Utilitarios.print(2);
                nome = Utilitarios.input.next();
                //criação do personagem
                personagem = new Heroi(nome, racaEscolhida, trilhaEscolhida);
                Utilitarios.limparTela();
            }
            else if (escolha == 2) {
                personagem = new Heroi();
                Utilitarios.carregarDados(personagem);
                Utilitarios.limparTela();
            }
            else {
                game = false;
                System.exit(1);
            }

            //Começo do jogo caso não saia do programa
            while (gameover & personagem.nome != null) {
                int valor = random.nextInt(1, 4);
                racaInimigo = switch (valor) {
                    case 1 -> Racas.GOBLIN;
                    case 2 -> Racas.LOBO;
                    default -> Racas.SLIME;
                };
                inimigo = new Inimigo(racaInimigo, random.nextInt(personagem.getAndar() - 1, personagem.getAndar() + 2),random);

                Utilitarios.print(personagem.getNome(),personagem.getAndar());
                escolha = Utilitarios.escolha(false, 5);
                Utilitarios.limparTela();

                //Seleção de escolha do jogo
                //inicio batalha
                if (escolha == 1) {
                    if (personagem.getAndar() != 10){
                    personagem.batalhar(personagem, inimigo, random);
                    }
                    else {
                        System.out.println("Você batalhará com o boss. \n1. Batalhar\n2. voltar");
                        escolha = Utilitarios.escolha(false,2);
                        Utilitarios.limparTela();
                        if (escolha == 1){
                        Boss boss = new Boss();
                        boss.batalhar(personagem, boss, random);
                        }
                    }
                }
                //Menu
                else if (escolha == 2) {
                    while (true){
                        Utilitarios.print(personagem);
                        Utilitarios.print(6);
                        escolha = Utilitarios.escolha(false, 4);
                        if (escolha == 1){
                            Utilitarios.salvarDados(personagem);
                        }
                        if (escolha == 2){
                            Utilitarios.carregarDados(personagem);
                        }
                        if (escolha == 3){
                            gameover = false;
                            Utilitarios.limparTela();
                            break;
                        }
                        if (escolha == 4){
                            Utilitarios.limparTela();
                            break;
                        }
                        Utilitarios.limparTela();
                    }
                }
                //Subir Andar
                else if (escolha == 3) {
                    personagem.subirAndar();
                    Utilitarios.print();
                    Utilitarios.limparTela();
                }
                //Descer Andar
                else if (escolha == 4) {
                    personagem.descerAndar();
                    Utilitarios.print();
                    Utilitarios.limparTela();
                }
                //Descansar
                else {
                    if (random.nextInt(0,10) >=3){
                        personagem.descansar();
                        Utilitarios.print();
                        Utilitarios.limparTela();
                    }
                    else {
                        personagem.batalhar(personagem, inimigo, random);
                    }
                }
                if (personagem.getVidaAtual() == 0){
                    Utilitarios.print(8);
                    Utilitarios.print();
                    Utilitarios.limparTela();
                    gameover = false;
                }
            }
        }
    }
}
