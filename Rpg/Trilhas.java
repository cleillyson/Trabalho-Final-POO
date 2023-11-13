package Projetos.Rpg;
public enum Trilhas {
    //Enumeração da trilha
    Warrior("Warrior", 1.5, 1.5,1.5),
    Mage("Mage", 1.25, 1.25,2),
    Archer("Archer", 1, 1.5,2);

    //Atributos da trilha
    private String nomeTrilha;
    private double hpMult;
    private double strengthMult;
    private double speedMult;

    //Construtor da trilha
    Trilhas(String nome,double hpMult, double strengthMult, double speedMult) {
        this.nomeTrilha = nome;
        this.hpMult = hpMult;
        this.strengthMult = strengthMult;
        this.speedMult = speedMult;
    }

    //Metodos da trilha
    public String getNome() {
        return nomeTrilha;
    }

    public double getHpMult() {return hpMult;}

    public double getStrengthMult() {return strengthMult;}

    public double getSpeedMult() {return speedMult;}
}
