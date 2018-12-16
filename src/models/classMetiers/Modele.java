package models.classMetiers;

import views.Niveau;

import java.io.File;
import java.util.ArrayList;

public interface Modele {
    int[] getEtat();
    int getLineNumber();
    void move(int indice);
    void reset();
    ArrayList<int[]> getCoups();
    void undo();
    boolean canUndo();

    int[] getFinish();

    void redo();

    boolean canRedo();

    void nextNiveau();

    ArrayList<Niveau> getNiveaux();

    void setNiveau(int i);


    int getLevel();

    String getNomNiveau();

    ArrayList<int[]> getAllCoups();

    void replay();

    void freeCoups();

    void precNiveau();

    void loadLevels(File file);

    int getNombreNiveaux();
}
