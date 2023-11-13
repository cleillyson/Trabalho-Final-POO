package Projetos.Rpg.src;
import java.util.Random;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Variaveis do main
        Utilitarios utilitarios = new Utilitarios();
        Random random = new Random();
        Heroi personagem;
        Inimigo inimigo;
        Racas racaEscolhida;
        Racas racaInimigo;
        Trilhas trilhaEscolhida;
        int escolha = 0;
        String nome;
        boolean gameover = true;
        boolean batalhaOn = true;
        boolean game = true;
        utilitarios.limparTela();

        //laço game on acredito que possa ser removido e adicionado if
        while (game) {
            utilitarios.print(5);
            escolha = utilitarios.escolha(false, 3);

            //if Seleção do menu
            if (escolha == 1) {
                utilitarios.limparTela();
                //Seleção de raça, trilha (classe) e nome
                utilitarios.print(0);
                escolha = utilitarios.escolha(false, 3);
                utilitarios.limparTela();
                racaEscolhida = switch (escolha) {
                    case 1 -> Racas.HUMANO;
                    case 2 -> Racas.ELFO;
                    case 3 -> Racas.ORC;
                    default -> throw new IllegalStateException("Valor invalido: " + escolha);
                };

                utilitarios.print(1);
                escolha = utilitarios.escolha(false, 3);
                utilitarios.limparTela();
                trilhaEscolhida = switch (escolha) {
                    case 1 -> Trilhas.Warrior;
                    case 2 -> Trilhas.Archer;
                    case 3 -> Trilhas.Mage;
                    default -> throw new IllegalStateException("Valor invalido: " + escolha);
                };

                utilitarios.print(2);
                nome = utilitarios.input.next();
                //criação do personagem
                personagem = new Heroi(nome, racaEscolhida, trilhaEscolhida);
                utilitarios.limparTela();
                //Começo do jogo
                while (gameover) {
                    int valor = random.nextInt(1, 4);
                    racaInimigo = switch (valor) {
                        case 1 -> Racas.GOBLIN;
                        case 2 -> Racas.LOBO;
                        default -> Racas.SLIME;
                    };
                    batalhaOn = true;
                    utilitarios.print(personagem.getNome(),personagem.getAndar());
                    escolha = utilitarios.escolha(false, 5);
                    utilitarios.limparTela();

                    //Seleção de escolha do jogo
                    if (escolha == 1) {
                        //inicio batalha
                        inimigo = new Inimigo(racaInimigo, random.nextInt(personagem.getAndar() - 1, personagem.getAndar() + 2));
                        while (batalhaOn) {
                            //print da batalha
                            utilitarios.print(inimigo.getRaca(), personagem.getNome(), inimigo.getVidaAtual(),
                            personagem.getVidaAtual(), inimigo.getVidaMAx(), personagem.getVidaMAx(),
                            inimigo.getLevel(), personagem.getLevel());

                            //se ambas as velocidades forem iguais a zero, volta ao max.
                            if (inimigo.getVelocidadeAtual() == 0 && personagem.getVelocidadeAtual() == 0) {
                                inimigo.setVelocidadeAtual(inimigo.getVelocidadeMax());
                                personagem.setVelocidadeAtual(personagem.getVelocidadeMax());
                            }
                            //se uma das vidas for igual a 0, fim da batalha
                            if (personagem.getVidaAtual() == 0 || inimigo.getVidaAtual() == 0) {
                                if (personagem.getVidaAtual() == 0) System.out.println("Você perdeu");
                                if (inimigo.getVidaAtual() == 0) {
                                    System.out.println("Você ganhou");
                                    System.out.printf("Você ganhou %.2f exp\n",inimigo.getExpDrop());
                                    personagem.setExpAtual(personagem.getExpAtual()+inimigo.getExpDrop());
                                    System.out.printf("exp atual: %.2f/%.2f\n",personagem.getExpAtual(),personagem.getExpUp());
                                }
                                    utilitarios.print();
                                utilitarios.limparTela();
                                batalhaOn = false;
                                break;
                            }

                            //se a velocidade do personagem for maior ou igual o personagem começa
                            if (personagem.getVelocidadeAtual() >= inimigo.getVelocidadeAtual()) {
                                utilitarios.print(3);
                                escolha = utilitarios.escolha(false, 4);
                                //seleciona o ataque
                                personagem.atacar(inimigo, escolha, random);
                                //seta a velocidade atual do personagem -= velocidade inimigo max
                                //melhorar esse sistema
                                personagem.setVelocidadeAtual(personagem.getVelocidadeAtual() - inimigo.getVelocidadeMax());

                            } else if (personagem.getVelocidadeAtual() <= inimigo.getVelocidadeAtual()) {
                                utilitarios.print(4);
                                inimigo.atacar(personagem, random);
                                //seta a velocidade atual do inimigo -= velocidade do personagem max
                                //melhorar esse sistema
                                inimigo.setVelocidadeAtual(inimigo.getVelocidadeAtual() - personagem.getVelocidadeMax());
                            }
                            //tentando continuar caso aperte enter para limpar a tela
                            //possui bugs
                            utilitarios.input.nextLine();
                            utilitarios.print();
                            utilitarios.limparTela();
                        }

                    } else if (escolha == 2) {
                        gameover = false;
                    } else if (escolha == 3) {
                        personagem.subirAndar();
                        utilitarios.input.nextLine();
                        utilitarios.print();
                        utilitarios.limparTela();
                    } else if (escolha == 4) {
                        personagem.descerAndar();
                        utilitarios.input.nextLine();
                        utilitarios.print();
                        utilitarios.limparTela();
                    } else {
                        personagem.descansar();
                        utilitarios.input.nextLine();
                        utilitarios.print();
                        utilitarios.limparTela();
                    }
                }
            }
            else if (escolha == 2) {
                game = false;
            }
            else {
                game = false;
                System.exit(1);
            }
        }
    }
}