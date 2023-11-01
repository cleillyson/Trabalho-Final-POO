package Projetos.Rpg;
public enum Racas {
    HUMANO("Humano", 5*2, 5,5, 0),
    ELFO("Elfo", 3*2, 5,7, 0),
    ORC("Orc", 7*2, 6,2, 0),
    GOBLIN("Goblin",4*2,5,6, 3),
    LOBO("Lobo",4*2,4,4, 2),
    SLIME("Slime",8*2,1,1, 1);
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
