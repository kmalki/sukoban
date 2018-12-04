package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurIHMFX {
    Controleur controleur;
    VueIHMFX vue;

    ControleurIHMFX(Controleur controleur, VueIHMFX vue) {
        this.controleur = controleur;
        this.vue = vue;
        this.vue.reset.setOnAction(new ActionReset());
        this.vue.undo.setOnAction(new ActionUndo());
        this.vue.redo.setOnAction(new ActionRedo());
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


}