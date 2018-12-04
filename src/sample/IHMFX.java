package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.EventHandler;


public class IHMFX extends Application implements Observateur {
    VueNbCoupIHMFX vueNbCoup;
    VueIHMFX vue;
    Controleur controleur;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vueNbCoup.dessine();
                vue.dessine();
                controleur.winornot();
                if(!controleur.canUndo()){
                    vue.disableUndo();
                }
                else{
                    vue.enableUndo();
                }
                if(!controleur.canRedo()){
                    vue.disableRedo();
                }
                else{
                    vue.enableRedo();
                }
            }
        });
    };

    @Override
    public void start(Stage primaryStage) throws Exception{
        controleur = Controleur.getControleur();
        controleur.abonne(this);

        vue = new VueIHMFX(controleur);
        ControleurIHMFX controleurIHMFX = new ControleurIHMFX(controleur,vue);
        vue.gridPane.setAlignment(Pos.CENTER);
        vueNbCoup= new VueNbCoupIHMFX(controleur);
        vueNbCoup.label.setAlignment(Pos.CENTER);
        /* montage de la scene */
        MonteurScene monteurScene = new MonteurScene();

        Scene scene = monteurScene.
                setCentre(vue.gridPane).
                ajoutBas(vue.reset).
                ajoutBas(vue.undo).
                ajoutBas(vue.redo).
                ajoutBas(vueNbCoup.label).
                setLargeur(800).
                setHauteur(800).
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
        primaryStage.setScene(scene);

        primaryStage.setTitle("HUBOCAN");
        primaryStage.show();
    }

    public void lance() {
        launch(new String[]{});
    }
}

