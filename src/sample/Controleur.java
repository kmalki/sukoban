package sample;

import java.util.ArrayList;

public class Controleur implements Sujet {
    private static Controleur singleton;

    public static Controleur getControleur() {
        if (singleton == null)
            singleton = new Controleur(new FacadeModele());
        return singleton;
    }

    FacadeModele facadeModele;
    ArrayList<Observateur> observateurs = new ArrayList<Observateur>();

    private Controleur(FacadeModele facadeModele) {
        this.facadeModele = facadeModele;
    }

    public void abonne(Observateur observateur) {
        observateurs.clear();observateurs.add(observateur);
    }

    @Override
    public void notifie(int j) {
        for (Observateur observateur:observateurs)
            observateur.actualise(j);
    }

    public void move(int x) {
        facadeModele.move(x);
        notifie(0);
    }

    public void reset() {
        facadeModele.reset();
        notifie(0);
    }

    public void undo() {
        facadeModele.undo();
        notifie(0);
    }

    public CommandeInt commandeNbCoup() {
        return new CommandeInt() {
            @Override
            public int exec() {
                return facadeModele.nbCoup();
            }
        };
    }

    public CommandeTabInt commandeGetEtat() {
        return new CommandeTabInt() {
            @Override
            public int[] exec() {
                return facadeModele.getEtat();
            }
        };
    }

    public CommandeInt getLineNumber(){
        return new CommandeInt() {
            @Override
            public int exec() {
                return facadeModele.getLineNumber();
            }
        };    }



    public boolean canUndo() {
        return facadeModele.canUndo();
    }

    public void redo() {
        facadeModele.redo();
        notifie(0);
    }

    public boolean canRedo() {
        return facadeModele.canRedo();

    }


    public void initNiveaux() {
        facadeModele.initNiveaux();
    }

    public ArrayList<Niveau> getNiveaux() {
        return facadeModele.getNiveaux();
    }


    public void setNiveau(int i) {
        facadeModele.setNiveau(i);
    }

    public int getNiveau() {
        return facadeModele.getNiveau();
    }

    public String getNomNiveau() {
        return facadeModele.getNomNiveau();
    }

    public void replay() throws InterruptedException {
        facadeModele.freeCoups();
        for(int i=0;i<facadeModele.getAllCoups().size();i++){
            facadeModele.replay();
            notifie(1);

        }
    }
}
