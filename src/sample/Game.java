package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Game implements Observateur {
    Controleur controleur;
    VueNbCoupIHMFX vueNbCoup;
    VueIHMFX vue;
    ControleurIHMFX cont;


    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vueNbCoup.dessine();
                vue.dessine();
                try {
                    cont.winornot();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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

    public static Game newGame(Controleur controleur, ControleurIHMFX c, Stage laStageUnique,int niveau) throws FileNotFoundException {
        URL location = Menu.class.getResource("/views/Game.fxml");
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
        MonteurScene monteurScene = new MonteurScene();
        Scene scene = monteurScene.
                setLargeur((int)laStageUnique.getWidth()).
                setHauteur((int)laStageUnique.getHeight()).
                setCentre(vueG.vue .gridPane).
                ajoutBas(vueG.vue.menu).
                ajoutBas(vueG.vue .reset).
                ajoutBas(vueG.vue .undo).
                ajoutBas(vueG.vue .redo).
                ajoutBas(vueG.vueNbCoup.label).

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


    private void setMonControleurIHMFX(ControleurIHMFX c) {
        cont=c;
    }
    private void setMonControleur(Controleur c) {
        controleur=c;
    }

}
