package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Optional;

public class ControleurIHMFX {
    Controleur controleur;
    VueIHMFX vue;
    Menu vueMenu;
    Stage primaryStage;
    VueNbCoupIHMFX vueNbCoup;
    Game vueGame;


    ControleurIHMFX(Controleur controleur,Stage primaryStage) {
        this.controleur = controleur;
        this.primaryStage=primaryStage;

    }

    public void goToNewGame() throws FileNotFoundException {
        vueGame=Game.newGame(controleur,this,primaryStage);
        vueGame.vue.reset.setOnAction(new ActionReset());
        vueGame.vue.undo.setOnAction(new ActionUndo());
        vueGame.vue.redo.setOnAction(new ActionRedo());
    }

    public void goToMenu(){
        vueMenu=Menu.creerEtAfficher(this,this.primaryStage);
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

    }

    public void winornot() {
        if(controleur.facadeModele.winornot()==true){
            System.out.println("WIN");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("");
            alert.setHeaderText("Vous avez gagné !     VOTRE SCORE : "+ controleur.facadeModele.nbCoup());
            alert.setContentText("Que voulez vous faire ?");

            ButtonType buttonNext = new ButtonType("Niveau Suivant");
            ButtonType buttonReset = new ButtonType("Rejouer");
            ButtonType buttonMenu = new ButtonType("Menu principal");

            alert.getButtonTypes().setAll(buttonNext,buttonReset, buttonMenu);

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
                controleur.facadeModele.nextNiveau();
                vueGame.vue.initMatrice();
                controleur.notifie();
            }

        }
    }
}