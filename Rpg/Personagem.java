package Projetos.Rpg;

abstract class Personagem {
    String nome;
    String raca;
    String trilha;
    double vida;
    double strength;
    double velocidade;
    int level = 1;
    double expAtual = 0;
    double expUp = 10;
    double expGanho = 2;
    //Level
    protected double getLevel(){return  level;}
    protected void  setLevel(int level){this.level = level;}
    protected double getExpAtual(){return  expAtual;}
    protected void setExpAtual(double ExpAtual){this.expAtual = ExpAtual;}
    protected double getExpUp(){return  expUp;}
    protected void setExpUp(double expUp){this.expUp= expUp;}
    protected double getExpGanho(){return  expGanho;}
    protected void setExpGanho(double expGanho){this.expGanho = expGanho;}
    //Status
    protected String getNome(){
        return nome;
    }
    protected void setNome(String nome){this.nome = nome;}
    protected String getRaca(){
        return raca;
    }
    protected String getTrilha(){return trilha;}
    protected double getVelocidade() {return velocidade;}
    protected void setVelocidade(double valor) {this.velocidade = valor;}
    protected double getStrength() {return strength;}
    protected void setStrength(double valor) {this.strength = valor;}
    protected double getVida(){return this.vida;}
    protected void setVida(double valor) {this.vida = valor;}
}

class Heroi extends Personagem{
    //Construtor
    Heroi(String nome, Racas raca, Trilhas trilha){
        this.nome = nome;
        this.vida = raca.getHpBase() * trilha.getHpMult();
        this.strength = raca.getStrengthBase() * trilha.getStrengthMult();
        this.velocidade = raca.getSpeedBase() * trilha.getSpeedMult();
        this.raca = raca.getNome();
        this.trilha = trilha.getNome();
    }
}