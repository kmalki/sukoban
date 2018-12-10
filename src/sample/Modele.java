package sample;

import java.util.ArrayList;

public interface Modele {
    public int[] getEtat();
    public int getLineNumber();
    public void move(int indice);
    public void reset();
    public ArrayList<int[]> getCoups();
    public void undo();
    public boolean canUndo();

    int[] getFinish();

    void redo();

    boolean canRedo();

    void traitement();

    void nextNiveau();

    ArrayList<Niveau> getNiveaux();

    void setNiveau(int i);


    int getLevel();
}
