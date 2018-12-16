package controlers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import models.classMetiers.CommandeInt;
import models.classMetiers.CommandeTabInt;
import models.classMetiers.Observateur;
import models.classMetiers.Sujet;
import models.facade.FacadeModele;
import views.Niveau;

import java.io.File;
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
    public void notifie() {
        for (Observateur observateur:observateurs)
            observateur.actualise();
    }

    public void move(int x) {
        facadeModele.move(x);
        notifie();
    }

    public void reset() {
        facadeModele.reset();
        notifie();
    }

    public void undo() {
        facadeModele.undo();
        notifie();
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
        notifie();
    }

    public boolean canRedo() {
        return facadeModele.canRedo();

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

    public void replay() {
        facadeModele.freeCoups();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            // TODO do something meaningful here
            facadeModele.replay();
           notifie();
        }));
        timeline.setCycleCount(facadeModele.getAllCoups().size());
        timeline.play();
    }
    public void load(File file) {
        facadeModele.loadLevels(file);
    }

    public int getNombreNiveaux() {
        return facadeModele.getNombreNiveaux();
    }
}
