package Projetos.Rpg.src;
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
    Inimigo(Racas raca,int valor){
        setLevel(valor);
        this.vidaAtual = this.vidaMax = 1.5*(raca.getHpBase() + raca.getHpBase() * level * 0.5);
        this.strength = 1.5*(raca.getStrengthBase() + raca.getStrengthBase() * level * 0.5);
        this.velocidadeMax = 1.5*(raca.getSpeedBase() + raca.getSpeedBase() * level * 0.5);
        this.nome = this.raca = raca.getNome();
        this.expDrop = raca.getExpDrop() + raca.getExpDrop() * this.level;
    }
    public double getExpDrop() {
        return expDrop;
    }
}