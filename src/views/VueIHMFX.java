package models;

import controlers.Controleur;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import sample.CommandeInt;
import sample.CommandeTabInt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueIHMFX {
    Button precedent;
    Button replay;
    Button redo;
    Button reset;
    Button undo;
    Button menu;
    CommandeTabInt commandeGetEtat;
    CommandeInt commandeLineNumber;
    ImageView[] images;
    GridPane gridPane = new GridPane();

    Image character = new Image(new FileInputStream("character.png"),80,80,false,true);
    Image caissebrown = new Image(new FileInputStream("caissebrown.png"),80,80,false,true);
    Image wall = new Image(new FileInputStream("wall.png"),80,80,false,true);
    Image sand = new Image(new FileInputStream("sand.png"),80,80,false,true);
    Image empty = new Image(new FileInputStream("empty.png"),80,80,false,true);
    Image endbrown = new Image(new FileInputStream("endbrown.png"),80,80,false,true);


    public VueIHMFX(Controleur controleur) throws FileNotFoundException {
        gridPane.setAlignment(Pos.CENTER);
        commandeGetEtat = controleur.commandeGetEtat();
        commandeLineNumber=controleur.getLineNumber();
        this.reset = new Button("Reset");
        this.undo = new Button("Undo");
        this.redo=new Button("Redo");
        this.menu=new Button("Menu");
        this.replay=new Button("Replay");
        this.precedent=new Button("Précédent");
        if(controleur.getNiveau()<1){
            this.precedent.setDisable(true);
        }
        else{
            this.precedent.setDisable(false);
        }
        this.undo.setDisable(true);
        this.redo.setDisable(true);
        dessine();
    }

    public void dessine() {
        initMatrice();
        for (int i=0;i<commandeGetEtat.exec().length;i++) {
            if(commandeGetEtat.exec()[i]==1) { // 1 = perso
                images[i].setImage(sand);
                images[i].setImage(character);
            }
            else if(commandeGetEtat.exec()[i]==2){
                images[i].imageProperty().set(caissebrown);
            }
            else if(commandeGetEtat.exec()[i]==3){
                images[i].imageProperty().set(wall);
            }
            else if(commandeGetEtat.exec()[i]==0){
                images[i].imageProperty().set(sand);
            }
            else if(commandeGetEtat.exec()[i]==9){
                images[i].imageProperty().set(empty);
            }
            else if(commandeGetEtat.exec()[i]==4){
                images[i].setImage(sand);

                images[i].setImage(endbrown);
            }
        }
    }

    public void disableUndo(){
        undo.setDisable(true);
    }
    public void enableUndo(){
        undo.setDisable(false);
    }

    public void disableRedo() { redo.setDisable(true);}
    public void enableRedo() { redo.setDisable(false);}

    public void initMatrice(){
        System.out.println("NEW MATRICE : " + commandeGetEtat.exec().length);
        gridPane.getChildren().remove(0, gridPane.getChildren().size());
        images = new ImageView[commandeGetEtat.exec().length];
        int col=0;
        for (int i=0;i<commandeGetEtat.exec().length;i++) {
            if(i%commandeLineNumber.exec()==0){
                col++;
            }
            images[i] = new ImageView(sand);
            gridPane.add(images[i],i%commandeLineNumber.exec(),col);
        }
    }
}
