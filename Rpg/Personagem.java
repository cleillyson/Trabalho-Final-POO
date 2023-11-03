package Projetos.Rpg.src;
import java.util.Random;
abstract class Personagem {

    protected String raca;
    protected double vidaAtual;
    protected double vidaMax;
    protected double strength;
    protected double velocidadeMax;
    protected double velocidadeAtual;
    protected int level = 0;

    //Level
    protected int getLevel(){return  level;}
    protected void  setLevel(int level){this.level = level;}

    //Status
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

    protected void bolsa(){

    }
}

class Heroi extends Personagem {
    protected String nome;
    private double expAtual = 0;
    private double expUp = 10;
    private String trilha;
    private int andar = 1;
    //Construtor
    Heroi(String nome, Racas raca, Trilhas trilha) {
        this.nome = nome;
        this.vidaMax = this.vidaAtual = raca.getHpBase() * trilha.getHpMult();
        this.strength = raca.getStrengthBase() * trilha.getStrengthMult();
        this.velocidadeMax = raca.getSpeedBase() * trilha.getSpeedMult();
        this.raca = raca.getNome();
        this.trilha = trilha.getNome();
    }
    public String getNome(){return nome;}
    public void setNome(String nome){this.nome = nome;}
    public String getTrilha() {
        return trilha;
    }

    public double getExpAtual() {
        return expAtual;
    }

    public void setExpAtual(double ExpAtual) {
        this.expAtual = ExpAtual;
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
        this.expUp = expUp * level;
        this.vidaMax += vidaMax * 0.25;
        setVidaAtual(getVidaMAx());
        this.strength += strength * 0.25;
        this.velocidadeMax += velocidadeMax * 0.25;

    }

    public int getAndar() {
        return this.andar;
    }


    void atacar(Inimigo inimigo, int golpe,Random random) {
        switch (golpe) {
            case 1:
                inimigo.setVidaAtual(inimigo.getVidaAtual() - strength / 3);
                System.out.printf("%s Atacou leve\n",nome);
                break;
            case 2:

                if (random.nextInt(0, 11) <= 7) {
                    inimigo.setVidaAtual(inimigo.getVidaAtual() - strength / 2);
                    System.out.printf("%s Atacou normalmente\n",nome);
                }
                else {System.out.printf("%s Falhou\n",nome);}
                break;
            default:
                if (random.nextInt(0, 11) <= 5) {
                    inimigo.setVidaAtual(inimigo.getVidaAtual() - strength);
                    System.out.printf("%s Atacou Forte\n",nome);
                }
                else {System.out.printf("%s Falhou\n",nome);}
                break;
        }
    }
}
class Inimigo extends Personagem{
    private double expDrop;
    Inimigo(Racas raca,int valor){
        setLevel(valor);
        this.vidaMax = raca.getHpBase() + raca.getHpBase() * level * 0.26;
        this.vidaAtual = getVidaMAx();
        this.strength = raca.getStrengthBase() + raca.getStrengthBase() * level * 0.26;
        this.velocidadeMax = raca.getSpeedBase() + raca.getSpeedBase() * level * 0.26;
        this.raca = raca.getNome();
        this.expDrop = raca.getExpDrop() * this.level;
    }


    public void atacar(Heroi heroi, Random random) {
        switch (random.nextInt(1,4)) {
            case 1:
                heroi.setVidaAtual(heroi.getVidaAtual() - strength / 3);
                System.out.printf("%s Atacou leve\n",raca);
                break;
            case 2:
                if (random.nextInt(0, 11) <= 7){
                    heroi.setVidaAtual(heroi.getVidaAtual() - strength / 2);
                    System.out.printf("%s Atacou Normalmente\n",raca);
                } else {
                    System.out.printf("%s Falhou\n",raca);
                }
                break;
            default:
                if (random.nextInt(0, 11) <= 5)
                {
                    heroi.setVidaAtual(heroi.getVidaAtual() - strength);
                    System.out.printf("%s Atacou Forte\n",raca);
                }
                else {System.out.printf("%s Falhou\n",raca);}
                break;
        }
    }
}

