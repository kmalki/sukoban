package views;

public class Niveau {
    private String name;
    private int[] plateau;
    private int[] finish;
    private int modulo;

    public Niveau(String n, int[] plateau, int[] finish, int modulo){
        this.name=n;this.plateau=plateau;this.finish=finish;this.modulo=modulo;
    }

    public int[] getPlateau(){
        return this.plateau;
    }

    public int[] getFinish(){return this.finish;}

    public String getName(){
        return this.name;
    }

    public int getModulo() {
        return modulo;
    }
}
