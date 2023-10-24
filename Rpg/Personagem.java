package Projetos.Rpg;

abstract class Personagem {
    double vida;
    double strength;
    double velocidade;


    protected double getVelocidade() {return velocidade;}
    protected void setVelocidade(double valor) {this.velocidade = valor;}
    protected double getStrength() {return strength;}
    protected void setStrength(double valor) {this.strength = valor;}
    protected double getVida(){return this.vida;}
    protected void setVida(double valor) {this.vida = valor;}
}

class Humano extends Personagem{
    //Construtor
    Humano() {
        setVida(50);
        setVelocidade(50);
        setStrength(50);
    }
}
class Elfo extends Personagem{
    //Construtor
    Elfo() {
        setVida(30);
        setVelocidade(70);
        setStrength(50);
    }
}

class Orc extends Personagem{
    //Construtor
    Orc() {
        setVida(70);
        setVelocidade(20);
        setStrength(60);
    }
}
