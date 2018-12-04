package sample;

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
}
