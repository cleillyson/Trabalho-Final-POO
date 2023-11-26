package Projetos.Rpg.src;
import java.io.IOException;
import java.util.Random;
abstract class Personagem {
    //Atributos
    protected String nome;
    protected String raca;
    protected double vidaAtual;
    protected double vidaMax;
    protected double strength;
    protected double velocidadeMax;
    protected double velocidadeAtual;
    protected int level = 0;

    //Mudanças de Level
    protected int getLevel(){return  level;}
    protected void  setLevel(int level){this.level = level;}

    //Stats do personagem
    protected void setRaca(String nome){
        raca = nome;
    }
    protected String getRaca(){return raca;}
    protected double getVelocidadeMax() {return velocidadeMax;}
    protected void setVelocidadeMax(double velocidadeMax) {this.velocidadeMax = velocidadeMax;}
    protected double getVelocidadeAtual() {return velocidadeAtual;}
    protected void setVelocidadeAtual(double velocidade) {
        if (velocidade <= 0) {this.velocidadeAtual = 0;}
        else {this.velocidadeAtual = velocidade;}
    }
    protected double getStrength() {return strength;}
    protected void setStrength(double strength) {this.strength = strength;}
    protected double getVidaAtual(){return this.vidaAtual;}
    protected void setVidaAtual(double vida) {
        if (vida <= 0) {this.vidaAtual = 0;}
        else {this.vidaAtual = vida;}
    }
    protected double getVidaMAx(){return this.vidaMax;}
    protected void setVidaMax(double vidaMax) {this.vidaMax = vidaMax;}

    void atacar(Personagem personagem, int golpe, Random random) {
        switch (golpe) {
            case 1:
                personagem.setVidaAtual(personagem.getVidaAtual() - strength / 3);
                System.out.printf("%s Atacou leve\n", nome);
                break;
            case 2:
                if (random.nextInt(0, 11) <= 7) {
                    personagem.setVidaAtual(personagem.getVidaAtual() - strength / 2);
                    System.out.printf("%s Atacou normalmente\n",nome);
                }
                else {System.out.printf("%s Falhou\n",nome);}
                break;
            default:
                if (random.nextInt(0, 11) <= 5) {
                    personagem.setVidaAtual(personagem.getVidaAtual() - strength);
                    System.out.printf("%s Atacou Forte\n",nome);
                }
                else {System.out.printf("%s Falhou\n",nome);}
                break;
        }
    }
    void batalhar(Heroi personagem, Inimigo inimigo, Random random) throws IOException, InterruptedException {
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
                var escolha = Utilitarios.escolha(false, 4);
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
}
class Heroi extends Personagem {
    //Atributos do Heroi
    private double expAtual = 0;
    private double expUp = 10;
    private String trilha;
    private int andar = 1;
    //Construtor do heroi onde recebe diversos dados da raça e da trilha
    Heroi(String nome, Racas raca, Trilhas trilha) {
        this.nome = nome;
        this.vidaMax = this.vidaAtual = raca.getHpBase() + raca.getHpBase() * trilha.getHpMult();
        this.strength = raca.getStrengthBase() + raca.getStrengthBase() * trilha.getStrengthMult();
        this.velocidadeMax = raca.getSpeedBase() + raca.getSpeedBase() * trilha.getSpeedMult();
        this.raca = raca.getNome();
        this.trilha = trilha.getNome();
    }
    Heroi() {
        //Construtor vazio para caso o jogador queira carregar dados
    }

    //Metodos especificos

    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getTrilha() {
        return trilha;
    }
    public void setTrilha(String dado) {
        trilha = dado;
    }
    public double getExpAtual() {
        return expAtual;
    }

    public void setExpAtual(double ExpAtual) {
        if (ExpAtual == expUp){
            levelUp();
        }
        else if (ExpAtual > expUp) {
            var expSobra = ExpAtual - expUp;
            levelUp();
            expAtual += expSobra;
        }
        else {
            expAtual = ExpAtual;
        }
    }

    public double getExpUp() {
        return expUp;
    }

    public void setExpUp(double expUp) {
        this.expUp = expUp;
    }

    public void levelUp() {
        this.level ++;
        this.expAtual = 0;
        this.expUp *= level;
        this.vidaMax += vidaMax * 0.25;
        setVidaAtual(getVidaMAx());
        this.strength += strength * 0.25;
        this.velocidadeMax += velocidadeMax * 0.25;

    }

    public int getAndar() {
        return this.andar;
    }

    public void setAndar(int andar) {
        this.andar = andar;
    }
    //Sistema de ataque
    public void subirAndar() {
        if (andar <10){
        this.andar ++;
            System.out.println("Você subiu um andar");
        }
        else {
            System.out.println("Não é possível subir mais");
        }
    }
    public void descerAndar() {
        if (andar > 1)
        {
        this.andar --;
            System.out.println("Você desceu um andar");
        }
        else {
            System.out.println("Não é possível descer mais");
        }
    }
    public void descansar(){
        if (vidaAtual + getVidaAtual()+getVidaMAx()*1/10 <= vidaMax){
            setVidaAtual(getVidaAtual()+getVidaMAx()*1/10);
            System.out.printf("Você recuperou %.2f de hp\n",getVidaAtual()+getVidaMAx()*1/10);
        }
        else{
            setVidaAtual(getVidaMAx());
            System.out.printf("Você recuperou %.2f de hp\n",getVidaMAx()-getVidaAtual());
        }
    }
}
class Inimigo extends Personagem{
    //Atributo do inimigo
    private double expDrop;
    //construtor do inimigo que recebe a valores da raça e o level
    Inimigo(Racas raca,int valor,Random random) {
        setLevel(valor);
        this.vidaAtual = this.vidaMax = 1.5 * (raca.getHpBase() + raca.getHpBase() * level * 0.5);
        this.strength = 1.5 * (raca.getStrengthBase() + raca.getStrengthBase() * level * 0.5);
        this.velocidadeMax = 1.5 * (raca.getSpeedBase() + raca.getSpeedBase() * level * 0.5);
        this.nome = this.raca = raca.getNome();
        this.expDrop = raca.getExpDrop() + raca.getExpDrop() * this.level;
    }
    public double getExpDrop() {
        return expDrop;
    }
}
class Boss extends Personagem{
    Boss() {
            this.level = 999;
            this.vidaAtual = this.vidaMax = 1.5 * (20 + 20 * 10 * 0.5);
            this.strength = 1.5 * (20 + 20 * 10 * 0.5);
            this.velocidadeMax = 1.5 * (20 + 20 * 10 * 0.5);
            setRaca("???");
            nome = "???";
        }
    void atacar(Personagem personagem, int golpe, Random random) {
        switch (golpe) {
            case 1:
                personagem.setVidaAtual(personagem.getVidaAtual() - strength / 3);
                System.out.printf("%s Atacou usando investida \n", nome);
                break;
            case 2:
                if (random.nextInt(0, 11) <= 7) {
                    personagem.setVidaAtual(personagem.getVidaAtual() - strength / 2);
                    System.out.printf("%s Atacou usando terremoto\n",nome);
                }
                else {System.out.printf("%s Falhou\n",nome);}
                break;
            default:
                if (random.nextInt(0, 11) <= 5) {
                    personagem.setVidaAtual(personagem.getVidaAtual() - strength);
                    System.out.printf("%s Atacou usando bola de fogo\n",nome);
                }
                else {System.out.printf("%s Falhou\n",nome);}
                break;
        }
    }
    void batalhar(Heroi personagem, Boss inimigo, Random random) throws IOException, InterruptedException {
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
                if (personagem.getVidaAtual() == 0) System.out.println("Você perdeu para o boss");
                if (inimigo.getVidaAtual() == 0) {
                    System.out.println("Parabéns, Você completou o jogo.");
                    System.out.println("Obrigado por jogar.");
                }
                Utilitarios.print();
                Utilitarios.limparTela();
                break;
            }

            //se a velocidade do personagem for maior ou igual o personagem começa
            if (personagem.getVelocidadeAtual() >= inimigo.getVelocidadeAtual()) {
                Utilitarios.print(3);
                var escolha = Utilitarios.escolha(false, 4);
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
}