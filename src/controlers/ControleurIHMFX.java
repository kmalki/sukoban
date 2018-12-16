package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class ControleurIHMFX {
    Controleur controleur;
    Menu vueMenu;
    Stage primaryStage;
    Game vueGame;
    Selection vueSelection;


    ControleurIHMFX(Controleur controleur,Stage primaryStage) {
        this.controleur = controleur;
        this.primaryStage=primaryStage;
        controleur.load(new File("Microcosmos.txt"));
    }

    public void goToNewGame(int i) throws FileNotFoundException {
        vueGame=Game.newGame(controleur,this,this.primaryStage,i);
        vueGame.vue.reset.setOnAction(new ActionReset());
        vueGame.vue.undo.setOnAction(new ActionUndo());
        vueGame.vue.redo.setOnAction(new ActionRedo());
        vueGame.vue.menu.setOnAction(new ActionMenu());
        vueGame.vue.replay.setOnAction(new ActionReplay());
        vueGame.vue.precedent.setOnAction(new ActionPrec());
       // controleur.notifie(0);
    }

    public void goToMenu(){
        vueMenu=Menu.creerEtAfficher(this,this.primaryStage);
    }

    public void goToSelection(){
        vueSelection=Selection.selectionner(this.controleur,this,this.primaryStage);
    }

    public void jouer(int numLevel) throws FileNotFoundException {
        goToNewGame(numLevel);
    }

    class ActionReplay implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                controleur.replay();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    class ActionPrec implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                if(controleur.getNiveau()>0) {
                    controleur.facadeModele.precNiveau();
                    goToNewGame(controleur.getNiveau());
                }            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    class ActionMenu implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            goToMenu();
        }

    }

    class ActionReset implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            controleur.reset();
        }

    }

    class ActionUndo implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){controleur.undo();}
    }

    class ActionRedo implements EventHandler<ActionEvent>{
        public void handle(ActionEvent event){controleur.redo();}
    }
    public void quitter(){
        primaryStage.close();
    }

    public void winornot() throws FileNotFoundException {
        if(controleur.facadeModele.winornot()){
            System.out.println("WIN");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText(controleur.getNomNiveau()+" : Réussi !     VOTRE SCORE : "+ controleur.facadeModele.nbCoup());
            alert.setContentText("Que voulez vous faire ?");

            ButtonType buttonPrec = new ButtonType("Niveau Précédent");
            ButtonType buttonNext = new ButtonType("Niveau Suivant");
            ButtonType buttonReset = new ButtonType("Rejouer");
            ButtonType buttonMenu = new ButtonType("Menu principal");

            if(controleur.getNiveau()<1){
                alert.getButtonTypes().setAll(buttonNext, buttonReset, buttonMenu);
            }
            else{
                alert.getButtonTypes().setAll(buttonPrec, buttonNext, buttonReset, buttonMenu);
            }

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonReset)
            {
                controleur.reset();
            }
            else if(result.get() == buttonMenu)
            {
                goToMenu();
            }
            else if(result.get()==buttonNext){
                System.out.println("NEXT");
                nextLevel();
            }
            else if(result.get()==buttonPrec){
                if(controleur.getNiveau()>0) {
                    controleur.facadeModele.precNiveau();
                    goToNewGame(controleur.getNiveau());
                }
            }
        }
    }

    public void nextLevel() throws FileNotFoundException {
        controleur.facadeModele.nextNiveau();
        goToNewGame(controleur.getNiveau());
    }
}