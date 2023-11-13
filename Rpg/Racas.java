package Projetos.Rpg;
public enum Racas {
    HUMANO("Humano", 5*3, 5,5, 0),
    ELFO("Elfo", 3*3, 5,7, 0),
    ORC("Orc", 7*3, 6,2, 0),
    GOBLIN("Goblin",6*3,7,8, 3),
    LOBO("Lobo",6*3,6,6, 2),
    SLIME("Slime",9*3,3,3, 1);
    private String nomeRaca;
    private int hpBase;
    private int stregthBase;
    private int speedBase;
    private double expDrop;

    Racas(String nome, int hp, int atk, int speed,double expDrop) {
        this.nomeRaca = nome;
        this.hpBase = hp;
        this.stregthBase = atk;
        this.speedBase = speed;
        this.expDrop = expDrop;
    }

    public String getNome() {
        return nomeRaca;
    }

    public int getHpBase() {
        return hpBase;
    }

    public int getStrengthBase() {
        return stregthBase;
    }

    public int getSpeedBase() {
        return speedBase;
    }

    public double getExpDrop() {return expDrop;}
}
