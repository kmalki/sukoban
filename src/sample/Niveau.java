package sample;

public class Niveau {
    private String name;
    private int[] plateau;

    private Niveau(String n, int[] plateau){
        this.name=n;this.plateau=plateau;
    }

    public int[] getPlateau(){
        return this.plateau;
    }

    public String getName(){
        return this.name;
    }
}
