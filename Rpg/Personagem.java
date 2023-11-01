package Projetos.Rpg;

abstract class Personagem {

    protected String raca;
    protected double vidaAtual;
    protected double vidaMax;
    protected double strength;
    protected double velocidade;
    protected int level = 1;
    //Level
    protected int getLevel(){return  level;}
    protected void  setLevel(int level){this.level = level;}

    //Status
    protected String getRaca(){return raca;}
    protected double getVelocidade() {return velocidade;}
    protected void setVelocidade(double velocidade) {this.velocidade = velocidade;}
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

    //Construtor
    Heroi(String nome, Racas raca, Trilhas trilha) {
        this.nome = nome;
        this.vidaMax = this.vidaAtual = raca.getHpBase() * trilha.getHpMult();
        this.strength = raca.getStrengthBase() * trilha.getStrengthMult();
        this.velocidade = raca.getSpeedBase() * trilha.getSpeedMult();
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

    public void Batalha(Heroi heroi, Inimigo inimigo) {

    }
}
class Inimigo extends Personagem{
    private double expDrop;
    Inimigo(Racas raca){
        this.vidaAtual = raca.getHpBase();
        this.vidaMax = raca.getHpBase();
        this.strength = raca.getStrengthBase();
        this.velocidade = raca.getSpeedBase();
        this.raca = raca.getNome();
        this.expDrop = raca.getExpDrop() * this.level;
    }
}

