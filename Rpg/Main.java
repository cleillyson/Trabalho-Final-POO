package Projetos.Rpg;
import java.util.Random;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException{
        //Variaveis

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

        //Seleção de raça e trilha (classe)
        Utilitarios.print(0);
        escolha = Utilitarios.escolha(false,3);
        racaEscolhida = switch (escolha) {
            case 1 -> Racas.HUMANO;
            case 2 -> Racas.ELFO;
            case 3 -> Racas.ORC;
            default -> throw new IllegalStateException("Valor invalido: " + escolha);
        };

        Utilitarios.print(1);
        escolha = Utilitarios.escolha(false,3);
        trilhaEscolhida = switch (escolha) {
            case 1 -> Trilhas.Warrior;
            case 2 -> Trilhas.Archer;
            case 3 -> Trilhas.Mage;
            default -> throw new IllegalStateException("Valor invalido: " + escolha);
        };

        Utilitarios.print(2);
        nome = Utilitarios.input.next();
        personagem = new Heroi(nome, racaEscolhida, trilhaEscolhida);

        Utilitarios.limparTela();


        while (gameover){

            int valor = random.nextInt(1,4);
            racaInimigo = switch (valor) {
                case 1 -> Racas.GOBLIN;
                case 2 -> Racas.LOBO;
                default -> Racas.SLIME;
            };

            Utilitarios.print(3);

            escolha = Utilitarios.escolha(false,5);
            if (escolha == 1){
                inimigo = new Inimigo(racaInimigo, random.nextInt(personagem.getAndar()-1, personagem.getAndar()+2));
                while (batalhaOn) {

                    Utilitarios.print(inimigo.getRaca(),personagem.getNome(),inimigo.getVidaAtual(),personagem.getVidaAtual(),inimigo.getVidaMAx(),personagem.getVidaMAx(),inimigo.getLevel(),personagem.getLevel());
                    Utilitarios.input.nextLine();

                    if(inimigo.getVelocidadeAtual() == 0 && personagem.getVelocidadeAtual() == 0){
                        inimigo.setVelocidadeAtual(inimigo.getVelocidadeMax());
                        personagem.setVelocidadeAtual(personagem.getVelocidadeMax());
                    }
                    if(personagem.getVidaAtual() == 0 || inimigo.getVidaAtual() == 0){
                        if (personagem.getVidaAtual() == 0) System.out.println("Você perdeu");
                        if (inimigo.getVidaAtual() == 0) System.out.println("Você ganhou");
                        Utilitarios.input.nextLine();
                        batalhaOn = false;
                    }

                    if (personagem.getVelocidadeAtual() >= inimigo.getVelocidadeAtual()){
                        Utilitarios.print(4);
                        escolha = Utilitarios.escolha(false,4);

                        personagem.atacar(inimigo,escolha,random);
                        personagem.setVelocidadeAtual(personagem.getVelocidadeAtual() - inimigo.getVelocidadeMax());
                    }
                    else if (personagem.getVelocidadeAtual() <= inimigo.getVelocidadeAtual()) {
                        Utilitarios.print(5);
                        inimigo.atacar(personagem,random);
                        inimigo.setVelocidadeAtual(inimigo.getVelocidadeAtual() - personagem.getVelocidadeMax());
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
