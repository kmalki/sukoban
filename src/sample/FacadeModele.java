package sample;

import java.util.ArrayList;
import java.util.BitSet;

public class FacadeModele {
    ModeleNbCoup modele = new ModeleNbCoup(new ModeleConcret());

    public void move(int x) {
        modele.move(x);
    }

    public void reset() {
        modele.reset();
    }

    public int nbCoup() {
        return modele.nbCoup;
    }

    public int[] getEtat() {
        return modele.getEtat();
    }

    public int getLineNumber() {return modele.getLineNumber(); }

    public boolean winornot() {
        return modele.winornot();
    }

    public void undo(){modele.undo();}

    public boolean canUndo() {
        return modele.canUndo();
    }

    public void redo() {
        modele.redo();
    }

    public boolean canRedo() {
        return modele.canRedo();
    }


    public void initNiveaux() {
         modele.traitement();
    }

    public void nextNiveau() {
        modele.nextNiveau();
    }

    public ArrayList<Niveau> getNiveaux() {
        return modele.getNiveaux();
    }

    public void setNiveau(int i) {
        modele.setNiveau(i);
    }

    public int getNiveau() {
        return modele.getLevel();
    }

    public String getNomNiveau() {
        return modele.getNomNiveau();
    }

    public ArrayList<int[]> getAllCoups() {
        return modele.getAllCoups();
    }

    public void replay() {
        modele.replay();
    }
    public void freeCoups(){
        modele.freeCoups();
    }
}
