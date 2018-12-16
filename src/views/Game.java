package views;

import controlers.Controleur;
import controlers.ControleurIHMFX;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.classMetiers.MonteurScene;
import models.classMetiers.Observateur;

import java.io.IOException;
import java.net.URL;

public class Game implements Observateur {
    Controleur controleur;
    VueNbCoupIHMFX vueNbCoup;
    public VueIHMFX vue;
    ControleurIHMFX cont;

    @FXML
    Button precedent;
    @FXML
    Button replay;
    @FXML
    Button redo;
    @FXML
    Button reset;
    @FXML
    Button undo;
    @FXML
    Button menu;
    @FXML
    Button suivant;


    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vueNbCoup.dessine();
                vue.dessine();
                cont.winornot();
                if (!controleur.canUndo()) {
                    disableUndo();
                } else {
                   enableUndo();
                }
                if (!controleur.canRedo()) {
                    disableRedo();
                } else {
                   enableRedo();
                }
            }
        });
    };

    public static Game newGame(Controleur controleur, ControleurIHMFX c, Stage laStageUnique,int niveau){
        URL location = Menu.class.getResource("/fxmlViews/Game.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Game vueG = fxmlLoader.getController();
        vueG.setMonControleurIHMFX(c);
        vueG.setMonControleur(controleur);
        controleur.abonne(vueG);
        vueG.controleur.setNiveau(niveau);
        vueG.vueNbCoup=new VueNbCoupIHMFX(controleur);
        vueG.vue = new VueIHMFX(controleur);
        vueG.vueNbCoup.dessineNom(controleur.getNomNiveau());
        vueG.vueNbCoup.dessine();
        MonteurScene monteurScene = new MonteurScene();
        vueG.disableRedo();
        vueG.disableUndo();
        if(vueG.controleur.getNiveau()==vueG.controleur.getNiveauMax()-1){
            vueG.disableSuivant();
        }
        else{
            vueG.enableSuivant();
        }
        if(vueG.controleur.getNiveau()==0){
            vueG.disablePrecedent();
        }
        else{
            vueG.enablePrecedent();
        }
        Scene scene = monteurScene.
                setLargeur((int)laStageUnique.getWidth()).
                setHauteur((int)laStageUnique.getHeight()).
                ajoutBas(vueG.vueNbCoup.label2).
                ajoutBas(vueG.menu).
                ajoutBas(vueG.precedent).
                ajoutBas(vueG.suivant).
                ajoutBas(vueG.replay).
                ajoutBas(vueG.reset).
                ajoutBas(vueG.undo).
                ajoutBas(vueG .redo).
                ajoutBas(vueG.vueNbCoup.label).
                setCentre(vueG.vue .gridPane).
                retourneScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    controleur.move(2); break;
                    case DOWN:  controleur.move(3); break;
                    case LEFT:  controleur.move(-1) ; break;
                    case RIGHT: controleur.move(1)  ; break;
                }
            }
        });
        laStageUnique.setScene(scene);
        laStageUnique.setTitle("Sukoban");
        laStageUnique.setMaximized(true);
        laStageUnique.show();
        return vueG;
    }

    public void reset() {
        controleur.reset();
    }


    private void setMonControleurIHMFX(ControleurIHMFX c) {
        cont=c;
    }
    private void setMonControleur(Controleur c) {
        controleur=c;
    }
    public void disableUndo(){
        undo.setDisable(true);
    }
    public void enableUndo(){
        undo.setDisable(false);
    }

    public void disableSuivant(){
        suivant.setDisable(true);
    }
    public void enableSuivant(){
        suivant.setDisable(false);
    }

    public void disablePrecedent(){
        precedent.setDisable(true);
    }
    public void enablePrecedent(){
        precedent.setDisable(false);
    }

    public void disableRedo() { redo.setDisable(true);}
    public void enableRedo() { redo.setDisable(false);}



        public void replay() {
            controleur.replay();
        }

        public void precedent() {
            if(controleur.getNiveau()>0) {
                controleur.facadeModele.precNiveau();
                cont.goToNewGame(controleur.getNiveau());
            }
        }


        public void menu() {
            cont.goToMenu();
        }



        public void suivant() {
            if(controleur.getNiveau()<controleur.getNiveauMax()){
                cont.goToNewGame(controleur.getNiveau()+1);
            }
        }

        public void undo(){controleur.undo();}


        public void redo(){controleur.redo();}

}
