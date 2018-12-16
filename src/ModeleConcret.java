import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ModeleConcret implements Modele {

    //1:perso
    // 0:sable
    // 2:caisse marron
    // 3:mur
    // 4:objectifs
    // 9:vide
    // 4: fin marron
    //Variables


    private int level;
    private int levelMax;
    ArrayList<Niveau> niveaux = new ArrayList<>();

    public ArrayList<int[]> getCoups(){
        return coups;
    }

    private ArrayList<int[]> coups = new ArrayList<>();
    private ArrayList<int[]> allCoups = new ArrayList<>();

    //@:perso
    //$:caisse marron
    //.:fin marron
    //*:caisse sur fin
    //+:perso sur un finish

    public int getLevel(){
        return level;
    }

    public String getNomNiveau(){
        return niveaux.get(level).getName();
    }

    public void precNiveau() {
        if(level>0){
            level--;
        }
    }


    public void loadLevels(File f) {
        traitement(f);
    }

    public int getNombreNiveaux() {
        return niveaux.size();
    }

    public void traitement(File file){
        int i=0;
        int max=0;
        int cpt=0;
        int cpt1=0;
        int fin=0;
        try{
            Scanner sc = new Scanner(file);
            Scanner sc1 = new Scanner(file);
            String elem;
            String nom;
            while(sc.hasNextLine()){
                max=0;i=0;fin=0;
                while (sc.hasNextLine() && sc.findInLine(";") == null) {
                    elem=sc.nextLine();
                    cpt++;
                    i++;
                    if(elem.length()>max){max=elem.length();}
                    for(int k=0;k<elem.length();k++){
                        switch (elem.charAt(k)) {
                            case '.':
                                fin++;
                                break;
                            case '+':
                                fin++;
                                break;
                            case '*':
                                fin++;
                                break;
                        }
                    }
                }
                nom=sc.nextLine().substring(1);
                cpt++;
                int[] etat = new int[max*i];
                int iterator=0;
                int[] finish = new int[fin];
                fin=0;
                while(cpt1<cpt-1 && sc1.hasNextLine() && sc1.findInLine(";")==null){
                    elem=sc1.nextLine();
                    for(int o=0;o<elem.length();o++){
                        switch (elem.charAt(o)) {
                            case '#':
                                etat[iterator] = 3;
                                break;
                            case ' ':
                                etat[iterator] = 0;
                                break;
                            case '@':
                                etat[iterator] = 1;
                                break;
                            case '.':
                                etat[iterator] = 4;
                                finish[fin] = iterator;
                                fin++;
                                break;
                            case '+':
                                etat[iterator] = 1;
                                finish[fin] = iterator;
                                fin++;
                                break;
                            case '*':
                                etat[iterator] = 2;
                                finish[fin] = iterator;
                                fin++;
                                break;
                            case '$':
                                etat[iterator] = 2;
                                break;
                        }
                        iterator++;
                        if (elem.length() < max && o == elem.length() - 1) {
                            int j = o;
                            while (j != max - 1) {
                                j++;
                                etat[iterator] = 0;
                                iterator++;
                            }
                        }
                    }
                    cpt1++;

                }
                if(sc.hasNextLine()) {
                    sc.nextLine();
                    cpt++;
                    cpt1+=2;
                    sc1.nextLine();sc1.nextLine();
                }
                niveaux.add(new Niveau(nom,etat.clone(),finish.clone(),max));
                level++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        levelMax=niveaux.size()-1;
    }
    public void nextNiveau() {
        if(level<levelMax){
            level++;
            setNiveau(level);
        }
    }
    public ArrayList<Niveau> getNiveaux(){
        return niveaux;
    }
    public void setNiveau(int i){
        level=i;
        coups.clear();allCoups.clear();
        coups.add(niveaux.get(level).getPlateau());
        allCoups.add(niveaux.get(level).getPlateau());
    }

    public int[] getFinish(){return niveaux.get(level).getFinish();}

    public int getLineNumber(){
        return niveaux.get(level).getModulo();
    }

    public int[] getEtat() {
        return coups.get(coups.size() - 1);
    }

    public boolean canUndo(){return getCoups().size()>1;}

    public boolean canRedo(){return coups.size()<allCoups.size();}

    public void redo(){
        coups.add(allCoups.get(coups.size()));
    }

    public void freeCoups(){
        coups.clear();
    }

    public ArrayList<int[]> getAllCoups(){
        return allCoups;
    }

    public void replay() {
        if(coups.size()<allCoups.size()){
            coups.add(allCoups.get(coups.size()));
        }
    }

    public void undo(){
        coups.remove(coups.size()-1);
    }

    public void move(int indice) {
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
        for(int i=0;i<getFinish().length;i++){
            if(newEtat[getFinish()[i]]!=1 && newEtat[getFinish()[i]]!=2){
                newEtat[getFinish()[i]]=4;
            }
        }
        coups.add(newEtat);
        allCoups.add(newEtat.clone());
    }
    @Override
    public void reset() {
        coups.clear();coups.add(getNiveaux().get(level).getPlateau().clone());
        allCoups.clear();allCoups.add(getNiveaux().get(level).getPlateau().clone());
    }
}
