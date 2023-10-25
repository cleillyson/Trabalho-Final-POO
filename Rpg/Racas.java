package Projetos.Rpg;
public enum Racas {
    HUMANO("Humano", 50, 50,50),
    ELFO("Elfo", 30, 50,70),
    ORC("Orc", 70, 60,20);

    private String nomeRaca;
    private int HpBase;
    private int stregthBase;
    private int speedBase;

    Racas(String nome, int hp, int atk, int speed) {
        this.nomeRaca = nome;
        this.HpBase = hp;
        this.stregthBase = atk;
        this.speedBase = speed;
    }

    public String getNome() {
        return nomeRaca;
    }

    public int getHpBase() {
        return HpBase;
    }

    public int getStrengthBase() {
        return stregthBase;
    }

    public int getSpeedBase() {
        return speedBase;
    }

}
