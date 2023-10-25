package Projetos.Rpg;

abstract class Personagem {
    String nome;
    double vida;
    double strength;
    double velocidade;
    String raca;

    protected String getNome(){
        return nome;
    }
    protected double getVelocidade() {return velocidade;}
    protected void setVelocidade(double valor) {this.velocidade = valor;}
    protected double getStrength() {return strength;}
    protected void setStrength(double valor) {this.strength = valor;}
    protected double getVida(){return this.vida;}
    protected void setVida(double valor) {this.vida = valor;}
}

class Warrior extends Personagem{
    //Construtor
    Warrior(String nome,Racas raca){
        this.nome = nome;
        this.vida = raca.getHpBase() * (1.5);
        this.strength = raca.getStrengthBase() * (1.5);
        this.velocidade = raca.getSpeedBase() * (1.5);
        this.raca = raca.getNome();
    }
}
class Archer extends Personagem{
    //Construtor
    Archer(String nome,Racas raca){
        this.nome = nome;
        this.vida = raca.getHpBase() * (1.25);
        this.strength = raca.getStrengthBase() * (1.25);
        this.velocidade = raca.getSpeedBase() * (2);
        this.raca = raca.getNome();
    }
}

class Mage extends Personagem{
    //Construtor
    Mage(String nome,Racas raca){
        this.nome = nome;
        this.vida = raca.getHpBase() * (1);
        this.strength = raca.getStrengthBase() * (1.5);
        this.velocidade = raca.getSpeedBase() * (2);
        this.raca = raca.getNome();
    }
}
