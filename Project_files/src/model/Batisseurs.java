package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;

import view.*;

/**
 * This class acts as an intermediary between the controller and the game engine.
 * It handles the exit, save & load mecanisms, and instanciate a new game engine.
 * @version 1.0
 * @author Titouan LE BERRE
 */

public class Batisseurs implements Serializable {

    //The Game object that will be instanciated
    private Game game;

    //The gamemode
    private Mode mode;

    //The difficulty (optionnal)
    private Difficulty diff;

    //The textual display
    private ConsoleDisplay affichageTxt;

    //The graphical display
    private GraphicalDisplay affichageGr;

    /**
     * The class' constructor.
     */

    public Batisseurs(){

        //Scanner to read the user's input
        Scanner scan = new Scanner(System.in);

        System.out.println("Jouer avec la version graphique ? (y/n)");

        String result = scan.next();

        if (result.equals("n")) {
            
            this.affichageTxt = new ConsoleDisplay(this);
        }

        else if (result.equals("y")) {
            
            this.affichageGr = new GraphicalDisplay();
                SwingUtilities.invokeLater(
                new Runnable() {
                public void run() {
                    new GraphicalDisplay().setVisible(true);
                }
            });
        }
        

        System.out.println("\n\n===========================================================================================================================================");
        System.out.println("\t _                 ____        _   _                                        __  __                                               ");
        System.out.println("\t| |               |  _ \\      | | (_)                                      |  \\/  |                              /\\              ");
        System.out.println("\t| |     ___  ___  | |_) | __ _| |_ _ ___ ___  ___ _   _ _ __ ___   ______  | \\  / | ___  _   _  ___ _ __ ______ /  \\   __ _  ___ ");
        System.out.println("\t| |    / _ \\/ __| |  _ < / _` | __| / __/ __|/ _ \\ | | | '__/ __| |______| | |\\/| |/ _ \\| | | |/ _ \\ '_ \\______/ /\\ \\ / _` |/ _ \\");
        System.out.println("\t| |___|  __/\\__ \\ | |_) | (_| | |_| \\__ \\__ \\  __/ |_| | |  \\__ \\          | |  | | (_) | |_| |  __/ | | |    / ____ \\ (_| |  __/");
        System.out.println("\t|______\\___||___/ |____/ \\__,_|\\__|_|___/___/\\___|\\__,_|_|  |___/          |_|  |_|\\___/ \\__, |\\___|_| |_|   /_/    \\_\\__, |\\___|");
        System.out.println("\t                                                                                          __/ |                        __/ |     ");
        System.out.println("\t                                                                                         |___/                        |___/      ");
        System.out.println("===========================================================================================================================================");
        System.out.println("\n\n\n\n");

        //Input of the save file's path
        System.out.println("\tENTREZ LE CHEMIN VERS LA SAUVEGARDE A RESTAURER (appuyer sur n si vous souhaitez commencer une nouvelle partie) : \n\n");
        
        String savePath = scan.next();

        if (!savePath.equals("n")){

            this.load(savePath);
        }

        //--------------------

        else {

            //Rules display if 1 is entered
            System.out.println("Si vous souhaitez afficher les règles du jeu, tapez 1, sinon, appuyer sur tout autre touche.");
            String regles = scan.next();
            if (regles.equals("1")) affichageTxt.rules();


            //Mode & Number of players input
            int nbTotal = 0;
            int nbH = 0;

                
            System.out.println("\nEntrez le mode de jeu :\n- Humain vs Humain --> HH\n- Humain vs Ordi --> HA");

            String mode = scan.next();

            while ( (!mode.equals("HH")) && (!mode.equals("HA"))){

                System.out.println("Merci de choisir un mode de jeu valide : ");
                mode = scan.next();
            }

            if (mode.equals("HH")) this.mode = Mode.HH;

            else if (mode.equals("HA")) this.mode = Mode.HA;
    
            System.out.println("Entrez le nombre de joueurs total : ");
            nbTotal = scan.nextInt();

            while ( (nbTotal < 2) || (nbTotal > 4) ){

                System.out.println("Il doit y avoir entre 2 et 4 joueurs :");
                nbTotal = scan.nextInt();
            }
            
    
            System.out.println("Entrez le nombre de joueurs humains : ");
    
            nbH = scan.nextInt();

            while ( (nbH < 1) && (nbH > nbTotal)){

                System.out.println("Il doit y avoir au moins 1 joueur humain, et il ne peut y avoir plus de joueurs humains que de joueurs au total.\nRessaisisez un nombre : ");
                nbH = scan.nextInt();
            }
    
            //System.out.println("Entrez la difficulté : ");
            //String diff = scan.next();

            this.game = new Game(this.mode, nbTotal, nbH, this);
            this.game.start();

        }

        //The scanner is not closed here, because it will block any user input for the rest of the program.
        //scan.close();
    };

    /**
     * The class' second constructor.
     * @param gamemode The game's mode, human vs human(s) or human vs AI(s)
     * @param nbPlayers The number of players in the game
     * @param HumanPlayers The number of "real" players
     */

    public Batisseurs(Mode gamemode, int nbPlayers, int HumanPlayers){};

    /**
     * This method handle the game exit mecanism.
     */

    //public void exit(){};

    /**
     * This method allows the user to resume a saved game, by instanciating the objects again
     * in their correct state.
     * @param path The save file's path in the filesystem.
     * @throws IOException In case the path is wrong/lead to nothing
     */

    public void load(String path){

        ObjectInputStream OIS = null;

        try {

            if (path != null){

                OIS = new ObjectInputStream(new FileInputStream(path));

                this.game = (Game)(OIS.readObject());

                ArrayList<Player> joueurs =  this.game.getPlayers();

                for (Player p : joueurs){
                    
                    if (p.getClass() == HumanPlayer.class) {

                        HumanPlayer play = (HumanPlayer)p;
                        play.loadScanner();
                    }
                }

                this.game.start();
            }

            if (OIS != null){

                OIS.close();
            }


        } catch (IOException e) {
            
            System.err.println("load(path) - Error : " + e.getMessage());

        } catch (ClassNotFoundException e) {
                
            System.err.println("load(path) - Error : " + e.getMessage());
        }        
    };

    /**
     * This method allows the user to save the objects state of the current game.
     * @param path The path in the filesystem where the file will be written.
     * @throws IOException In case the path is wrong/lead to nothing.
     */

    public void save(String path){

        ObjectOutputStream OOS = null;

        try {

            if (path != null){

                OOS = new ObjectOutputStream(new FileOutputStream(path));

                OOS.writeObject(this.game);
            }

            if (OOS != null){

                OOS.close();
            }


        } catch (IOException e) {
            
            System.err.println("save(path) - Error : " + e.getMessage());
        }


    };

    /**
     * This method is a getter that return the Game object intialized by the current object.
     * @return The game attribute.
     */

    public Game getGame(){

        return this.game;
    }

    /**
     * This method is a getter that return the ConsoleDisplay object intialized by the current object.
     * @return The affichageTxt attribute.
     */

    public ConsoleDisplay getAffichageTxt(){

        return this.affichageTxt;
    }

}