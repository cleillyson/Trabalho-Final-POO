package Projetos.Rpg;

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
    protected void setVelocidadeAtual(double velocidadeMax) {this.velocidadeAtual = velocidadeMax;}
    protected double getStrength() {return strength;}
    protected void setStrength(double strength) {this.strength = strength;}
    protected double getVidaAtual(){return this.vidaAtual;}

    protected void setVidaAtual(double vida) {this.vidaAtual = vida;}
    protected double getVidaMAx(){return this.vidaMax;}
    protected void setVidaMax(double vidaMax) {this.vidaMax = vidaMax;}
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
}
class Inimigo extends Personagem{
    private double expDrop;
    Inimigo(Racas raca,int valor){
        setLevel(valor);
        this.vidaMax += raca.getHpBase() * level * 0.26;
        this.vidaAtual = getVidaMAx() * level * 0.26;
        this.strength += raca.getStrengthBase() * level * 0.26;
        this.velocidadeMax += raca.getSpeedBase() * level * 0.26;
        this.raca = raca.getNome();
        this.expDrop = raca.getExpDrop() * this.level;
    }
}

