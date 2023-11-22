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
        boolean gameover = true;
        boolean game = true;
        Utilitarios.limparTela();

        Utilitarios.print(7);
        Utilitarios.print();
        Utilitarios.limparTela();
        
        //laço game on
        while (game) {
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

                Utilitarios.print(personagem.getNome(),personagem.getAndar());
                escolha = Utilitarios.escolha(false, 5);
                Utilitarios.limparTela();

                //Seleção de escolha do jogo
                if (escolha == 1) {
                    //inicio batalha
                    inimigo = new Inimigo(racaInimigo, random.nextInt(personagem.getAndar() - 1, personagem.getAndar() + 2));
                    while (true) {
                        //print da batalha
                        Utilitarios.print(inimigo.getRaca(), personagem.getNome(), inimigo.getVidaAtual(),
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
                            Utilitarios.print();
                            Utilitarios.limparTela();
                            break;
                        }

                        //se a velocidade do personagem for maior ou igual o personagem começa
                        if (personagem.getVelocidadeAtual() >= inimigo.getVelocidadeAtual()) {
                            Utilitarios.print(3);
                            escolha = Utilitarios.escolha(false, 4);
                            //seleciona o ataque
                            personagem.atacar(inimigo, escolha, random);
                            //seta a velocidade atual do personagem -= velocidade do inimigo max
                            //melhorar esse sistema
                            personagem.setVelocidadeAtual(personagem.getVelocidadeAtual() - inimigo.getVelocidadeMax());

                        } else if (personagem.getVelocidadeAtual() <= inimigo.getVelocidadeAtual()) {
                            Utilitarios.print(4);
                            inimigo.atacar(personagem, random.nextInt(1,3),random);
                            //seta a velocidade atual do inimigo -= velocidade do personagem max
                            //melhorar esse sistema
                            inimigo.setVelocidadeAtual(inimigo.getVelocidadeAtual() - personagem.getVelocidadeMax());
                        }

                        Utilitarios.print();
                        Utilitarios.limparTela();
                    }

                }
                else if (escolha == 2) {
                    while (true){
                        Utilitarios.print(personagem);
                        Utilitarios.print(6);
                        escolha = Utilitarios.escolha(false, 5);
                        if (escolha == 1){
                            Utilitarios.salvarDados(personagem);
                        }
                        if (escolha == 2){
                            Utilitarios.carregarDados(personagem);
                        }
                        if (escolha == 3){
                            System.out.println("Não tem essa função ainda");
                            Utilitarios.print();
                        }
                        if (escolha == 4){
                            System.out.println("Também não tem essa função");
                            Utilitarios.print();
                        }
                        if (escolha == 5){
                            break;
                        }
                        Utilitarios.limparTela();
                    }
                }
                else if (escolha == 3) {
                    personagem.subirAndar();
                    Utilitarios.print();
                    Utilitarios.limparTela();
                }
                else if (escolha == 4) {
                    personagem.descerAndar();
                    Utilitarios.print();
                    Utilitarios.limparTela();
                }
                else {
                    personagem.descansar();
                    Utilitarios.print();
                    Utilitarios.limparTela();
                }
            }
        }
    }
}
