package sample;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class ModeleConcret implements Modele {

    //1:perso
    // 0:sable
    // 2:caisse marron
    // 3:mur
    // 4:objectifs
    // 9:vide
    // 4: fin marron
    //Variables

    private int[] finish = {60};


    public ArrayList<int[]> getCoups(){
        return coups;
    }

    private int[] etat = {
            9,9,9,3,3,3,3,3,3,9,9,
            9,3,3,0,0,0,0,3,0,3,9,
            9,3,0,0,0,0,3,9,3,3,9,
            9,3,0,3,2,0,3,9,3,3,9,
            9,3,0,0,1,0,3,9,3,3,9,
            9,3,0,0,0,4,3,9,3,3,9,
            9,3,0,0,0,0,0,3,0,3,9,
            9,3,0,0,0,0,0,0,0,3,9,
            9,3,0,0,0,0,0,0,0,3,9,
            9,3,3,3,3,3,3,3,3,3,9,
            9,9,9,9,9,9,9,9,9,9,9
    };
    private ArrayList<int[]> coups = new ArrayList<int[]>(){{add(etat);}};
    private ArrayList<int[]> allCoups = new ArrayList<int[]>(){{add(etat);}};


    private int nbLine=(int)sqrt(etat.length);

    public int[] getFinish(){return finish;}

    public int getLineNumber(){
        return (int)sqrt(etat.length);
    }

    public int[] getEtat() {
        return coups.get(coups.size()-1);
    }

    public boolean canUndo(){return getCoups().size()>1;}

    public boolean canRedo(){return coups.size()<allCoups.size();}


    public void setEtat(int[] etats){etat=etats;}

    public void redo(){
        coups.add(allCoups.get(coups.size()));
    }



    public void undo(){
        System.out.println("undo");
        for(int i=0;i<coups.size();i++){
            System.out.print("TABLEAU");
            for(int j=0;j<coups.get(i).length;j++){
                System.out.print(coups.get(i)[j]+" ");
            }
        }
        System.out.println("");
        coups.remove(coups.size()-1);
    }

    public void move(int indice) {
//        if(indice==1){//RIGHT
//////            for(int i=0;i<etat.length-1;i++){
//////                if(etat[i]==1){
//////                    if(etat[i+1]!=2 && etat[i+1]!=3) {
//////                        etat[i] = 0;
//////                        etat[i + 1] = 1;
//////                        break;
//////                    }
//////                    else{
//////                        if(i<etat.length-2 && etat[i+1]!=3) {
//////                            if (etat[i + 2] != 3 && etat[i + 2] != 2) {
//////                                etat[i] = 0;
//////                                etat[i + 2] = 2;
//////                                etat[i + 1] = 1;
//////                                break;
//////                            }
//////                        }
//////                    }
//////                }
//////            }
//////        }
//////        else if(indice==-1){//LEFT
//////            for(int i=0;i<etat.length;i++){
//////                if(etat[i]==1){
//////                    if(etat[i-1]!=2 && etat[i-1]!=3) {
//////                        etat[i] = 0;
//////                        etat[i - 1] = 1;
//////                        break;
//////                    }
//////                    else{
//////                        if(i>1 && etat[i-1]!=3) {
//////                            if (etat[i - 2] != 3 && etat[i - 2] != 2) {
//////                                etat[i] = 0;
//////                                etat[i - 2] = 2;
//////                                etat[i - 1] = 1;
//////                                break;
//////                            }
//////                        }
//////                    }
//////                }
//////            }
//////        }
//////        else if(indice==2){//UP
//////            for(int i=0;i<etat.length;i++){
//////                if(etat[i]==1){
//////                    if(i>=2*getLineNumber() && etat[i-getLineNumber()]!=2 && etat[i-getLineNumber()]!=3){
//////                        etat[i]=0;etat[i-getLineNumber()]=1;
//////                    }
//////                    else{
//////                        if(i>=2*getLineNumber() && etat[i-getLineNumber()]!=2 &&  etat[i-getLineNumber()]!=3){
//////                            if(etat[i-getLineNumber()]==2){
//////                                if(etat[i-2*getLineNumber()]!=3){
//////                                    etat[i]=0;etat[i-getLineNumber()]=1;etat[i-2*getLineNumber()]=2;
//////                                }
//////                            }
//////                        }
//////                        else if(etat[i-getLineNumber()]==2){
//////                            if(etat[i-2*getLineNumber()]!=2 && etat[i-2*getLineNumber()]!=3){
//////                                etat[i]=0;etat[i-getLineNumber()]=1; etat[i-2*getLineNumber()]=2;
//////                            }
//////                        }
//////                    }
//////                    break;
//////                }
//////           }
//////        }
//////        else if(indice==3){//DOWN
//////            for(int i=0;i<etat.length;i++){
//////                if(etat[i]==1 && i<etat.length-getLineNumber() && etat[i+getLineNumber()]!=2 && etat[i+getLineNumber()]!=3){
//////                    etat[i]=0;etat[i+getLineNumber()]=1;
//////                    break;
//////                }
//////                if(etat[i]==1){
//////                    if(i>=getLineNumber() && etat[i+getLineNumber()]!=2 && etat[i+getLineNumber()]!=3){
//////                        etat[i]=0;etat[i+getLineNumber()]=1;
//////                    }
//////                    else{
//////                        if(i>=getLineNumber()){
//////                            if(etat[i+getLineNumber()]==2){
//////                                if(etat[i+2*getLineNumber()]!=3){
//////                                    etat[i]=0;etat[i+getLineNumber()]=1;etat[i+2*getLineNumber()]=2;
//////                                }
//////                            }
//////                        }
//////                    }
//////                    break;
//////                }
//////            }
//////        }
//////        System.out.println(indice);
//////        for(int i=0;i<finish.length;i++){
//////            if(etat[finish[i]]!=1 && etat[finish[i]]!=2){
//////                etat[finish[i]]=4;
//////            }
//////        }
        int[] newEtat = coups.get(coups.size()-1).clone();
        if(indice==1){//RIGHT
            for(int i=0;i<newEtat.length-1;i++){
                if(newEtat[i]==1){
                    if(newEtat[i+1]!=2 && newEtat[i+1]!=3) {
                        newEtat[i] = 0;
                        newEtat[i + 1] = 1;
                        break;
                    }
                    else{
                        if(i<newEtat.length-2 && newEtat[i+1]!=3) {
                            if (newEtat[i + 2] != 3 && newEtat[i + 2] != 2) {
                                newEtat[i] = 0;
                                newEtat[i + 2] = 2;
                                newEtat[i + 1] = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(indice==-1){//LEFT
            for(int i=0;i<newEtat.length;i++){
                if(newEtat[i]==1){
                    if(newEtat[i-1]!=2 && newEtat[i-1]!=3) {
                        newEtat[i] = 0;
                        newEtat[i - 1] = 1;
                        break;
                    }
                    else{
                        if(i>1 && newEtat[i-1]!=3) {
                            if (newEtat[i - 2] != 3 && newEtat[i - 2] != 2) {
                                newEtat[i] = 0;
                                newEtat[i - 2] = 2;
                                newEtat[i - 1] = 1;
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(indice==2){//UP
            for(int i=0;i<newEtat.length;i++){
                if(newEtat[i]==1){
                    if(i>=2*getLineNumber() && newEtat[i-getLineNumber()]!=2 && newEtat[i-getLineNumber()]!=3){
                        newEtat[i]=0;newEtat[i-getLineNumber()]=1;
                    }
                    else{
                        if(i>=2*getLineNumber() && newEtat[i-getLineNumber()]!=2 &&  newEtat[i-getLineNumber()]!=3){
                            if(newEtat[i-getLineNumber()]==2){
                                if(newEtat[i-2*getLineNumber()]!=3){
                                    newEtat[i]=0;newEtat[i-getLineNumber()]=1;newEtat[i-2*getLineNumber()]=2;
                                }
                            }
                        }
                        else if(newEtat[i-getLineNumber()]==2){
                            if(newEtat[i-2*getLineNumber()]!=2 && newEtat[i-2*getLineNumber()]!=3){
                                newEtat[i]=0;newEtat[i-getLineNumber()]=1; newEtat[i-2*getLineNumber()]=2;
                            }
                        }
                    }
                    break;
                }
           }
        }
        else if(indice==3){//DOWN
            for(int i=0;i<newEtat.length;i++){
                if(newEtat[i]==1 && i<newEtat.length-getLineNumber() && newEtat[i+getLineNumber()]!=2 && newEtat[i+getLineNumber()]!=3){
                    newEtat[i]=0;newEtat[i+getLineNumber()]=1;
                    break;
                }
                if(newEtat[i]==1){
                    if(i>=getLineNumber() && newEtat[i+getLineNumber()]!=2 && newEtat[i+getLineNumber()]!=3){
                        newEtat[i]=0;newEtat[i+getLineNumber()]=1;
                    }
                    else{
                        if(i>=getLineNumber()){
                            if(newEtat[i+getLineNumber()]==2){
                                if(newEtat[i+2*getLineNumber()]!=3){
                                    newEtat[i]=0;newEtat[i+getLineNumber()]=1;newEtat[i+2*getLineNumber()]=2;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
        System.out.println(indice);
        for(int i=0;i<finish.length;i++){
            if(newEtat[finish[i]]!=1 && newEtat[finish[i]]!=2){
                newEtat[finish[i]]=4;
            }
        }
        coups.add(newEtat);
        for(int i=0;i<coups.size();i++){
            System.out.print("TABLEAU");
            for(int j=0;j<coups.get(i).length;j++){
                System.out.print(coups.get(i)[j]+" ");
            }
            System.out.println("");
        }
        System.out.println("///////////");
        allCoups.add(newEtat.clone());
    }
    @Override
    public void reset() {
        coups.clear();coups.add(etat);
        allCoups.clear();allCoups.add(etat);
    }
}
