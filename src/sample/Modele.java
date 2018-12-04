package sample;

import java.util.ArrayList;

public interface Modele {
    public int[] getEtat();
    public int getLineNumber();
    public void move(int indice);
    public void reset();
    public void setEtat(int[] etats);
    public ArrayList<int[]> getCoups();
    public void undo();
    public boolean canUndo();

    int[] getFinish();

    void redo();

    boolean canRedo();
}
