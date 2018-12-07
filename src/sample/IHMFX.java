package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;


public class IHMFX extends Application  {
    VueNbCoupIHMFX vueNbCoup;
    VueIHMFX vue;
    Controleur controleur;
    ControleurIHMFX controleurIHMFX;
    Menu vueMenu;
    Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        controleur = Controleur.getControleur();
//        vue = new VueIHMFX(controleur);
//        vue.gridPane.setAlignment(Pos.CENTER);
//        vueNbCoup= new VueNbCoupIHMFX(controleur);
//        vueNbCoup.label.setAlignment(Pos.CENTER);
        controleurIHMFX = new ControleurIHMFX(controleur,primaryStage);
        /* montage de la scene */
        this.primaryStage=primaryStage;
        this.goToMenu();

    }

    public void lance() {
        launch(new String[]{});
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }
    public void goToMenu(){
        vueMenu = Menu.creerEtAfficher(controleurIHMFX,primaryStage);
    }

    public void goToNewGame(){

    }
}

