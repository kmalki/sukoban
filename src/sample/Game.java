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

    VBox topNiveau;

    public void actualise(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vueNbCoup.dessine();
                vue.dessine();
                cont.winornot();
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

    public static Game newGame(Controleur controleur, ControleurIHMFX c, Stage laStageUnique) throws FileNotFoundException {
        URL location = Menu.class.getResource("/views/Game.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        Parent root = null;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Game vueG = fxmlLoader.getController();
        laStageUnique.setTitle("HUBOKAN");
        laStageUnique.setScene(new Scene(root, 800, 600));
        laStageUnique.show();
        vueG.setMonControleurIHMFX(c);
        vueG.setMonControleur(controleur);
        controleur.abonne(vueG);
        vueG.vueNbCoup=new VueNbCoupIHMFX(controleur);
        vueG.vue = new VueIHMFX(controleur);
        MonteurScene monteurScene = new MonteurScene();
        Scene scene = monteurScene.
                setCentre(vueG.vue .gridPane).
                ajoutBas(vueG.vue .reset).
                ajoutBas(vueG.vue .undo).
                ajoutBas(vueG.vue .redo).
                ajoutBas(vueG.vueNbCoup.label).
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
        laStageUnique.setScene(scene);
        laStageUnique.setTitle("HUBOCAN");
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
