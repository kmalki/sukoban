package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueIHMFX {
    Button redo;
    Button reset;
    Button undo;
    CommandeTabInt commandeGetEtat;
    CommandeInt commandeLineNumber;
    ImageView[] images;
    GridPane gridPane = new GridPane();

    Image character = new Image(new FileInputStream("character.png"),80,80,false,false);
    Image caissebrown = new Image(new FileInputStream("caissebrown.png"),80,80,false,false);
    Image wall = new Image(new FileInputStream("wall.png"),80,80,false,false);
    Image sand = new Image(new FileInputStream("sand.png"),80,80,false,false);
    Image empty = new Image(new FileInputStream("empty.png"),80,80,false,false);
    Image endbrown = new Image(new FileInputStream("endbrown.png"),80,80,false,false);


    public VueIHMFX(Controleur controleur) throws FileNotFoundException {
        //controleur.setTerrain();
        commandeGetEtat = controleur.commandeGetEtat();
        commandeLineNumber=controleur.getLineNumber();
        initMatrice();
        this.reset = new Button("Reset");
        this.undo = new Button("Undo");
        this.redo=new Button("Redo");
        this.undo.setDisable(true);
        this.redo.setDisable(true);
        dessine();
    }

    public void dessine() {
        for (int i=0;i<commandeGetEtat.exec().length;i++) {
            if(commandeGetEtat.exec()[i]==1) { // 1 = perso
                images[i].setImage(character);
            }
            else if(commandeGetEtat.exec()[i]==2){
                images[i].setImage(caissebrown);
            }
            else if(commandeGetEtat.exec()[i]==3){
                images[i].setImage(wall);
            }
            else if(commandeGetEtat.exec()[i]==0){
                images[i].setImage(sand);
            }
            else if(commandeGetEtat.exec()[i]==9){
                images[i].setImage(empty);
            }
            else if(commandeGetEtat.exec()[i]==4){
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
