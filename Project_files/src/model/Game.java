package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;
import java.io.*;

import view.*;


/**
 * This class is the core of the game model.
 * It instanciates the cards, the players, the coins stock, handle the current player...
 * @author Titouan LE BERRE
 * @version 1.0
 */

public class Game implements IGame, Serializable{

    //The game's coins stock
    private int reserve;

    //The list of players
    private ArrayList<Player> players;

    //The current player
    private Player current;

    //The batch of cards
    private ArrayList<Card> cards;

    //The batch of Ouvrier objects that can be recruited
    private ArrayList<Ouvrier> stackWorkers;

    //The batch of Machine/Building that can be opened
    private ArrayList<Card> stackBuilding;

    //The original instance to access save & load methods
    private Batisseurs lesBat;

    //A turn counter
    private int turn;



    /**
     * The class' constructor, create the game main elements (players, cards).
     * @param mode The game's mode, necessary to the players instanciation.
     * @param nbPlayers The total number of players
     * @param humanPlayers The number of "real" players
     * @param bat The Batisseurs object that instanciated the current object
     */

    public Game(Mode mode, int nbPlayers, int humanPlayers, Batisseurs bat){

        this.players = new ArrayList<Player>();
        this.cards = new ArrayList<Card>();
        this.stackWorkers = new ArrayList<Ouvrier>();
        this.stackBuilding = new ArrayList<Card>();
        this.turn = 1;

        this.reserve = 100; //The coins stock is at 100 by default

        //There must be between 2 and 4 players in a game
        if ( (nbPlayers >= 2 ) && (nbPlayers < 5) && (humanPlayers >= 0) && (humanPlayers < 5) && (mode == Mode.HA || mode == Mode.HH) && (bat != null)){

            //Instanciation of the players and the batch of cards
            this.createCards("../data");
            this.createPlayers(humanPlayers, nbPlayers, mode);

            this.current = this.players.get(0);

            this.reserve -= nbPlayers*10;

            this.lesBat = bat;
        }

        else if (bat == null) throw new IllegalArgumentException("Game() - Error : There must be a Batisseurs object to instanciate a Game object !");
        else if (nbPlayers <=0) throw new IllegalArgumentException("Game() - Error : There can't be no players in this game.");
        else if ( mode != Mode.HH && mode != Mode.HA) throw new IllegalArgumentException("Game() - Error : The game Mode is invalid.");
    };

    /**
     * The class' second constructor, with an additional Difficulty parameter.
     * @param mode The game's mode, necessary to the players instanciation.
     * @param diff The game's difficulty.
     * @param nbPlayers The total number of players
     * @param HumanPlayers The number of "real" players
     */

    public Game(Mode mode, Difficulty diff, int nbPlayers, int HumanPlayers){

        //Soon to be implemented
    };


    /**
     * This method is a getter that return a player in the list.
     * @param index The position of the player in the list (0 = 1st player)
     * @return An Player object
     */

    public Player getPlayer(int index){

        return this.players.get(index);
    };

    /**
     * This method is a getter that return the entire Player ArrayList players.
     * @return An ArrayList of Player objects.
     */

    public ArrayList<Player> getPlayers(){

        return this.players;
    };

    /**
     * This method is a getter that return the current player attribute.
     * @return A Player object
     */

    public Player getCurrentPlayer(){

        return this.current;
    };

    /**
     * This method is a getter that return the amount of coins in the stock.
     * @return The amount of coins, as an integer
     */

    public int getReserve(){

        return this.reserve;
    };

    /**
     * This method is a setter that set the amount of coins in the stock.
     * @param reserve The new amount of coins in the stock.
     */

    public void setReserve(int reserve){

        if (reserve >= 0) this.reserve = reserve;
        else System.out.println("setReserve() - Error : The new value is smaller than 0.");
    };

    /**
     * This method is a getter that return the turn.
     * @return The turn attribute
     */

    public int getTurn(){

        return this.turn;
    };

    /**
     * This method create the players that will be playing during the game.
     * The name is get thanks to a Scanner.
     * @param nbH The number of HumanPlayer objects to create.
     * @param nbP The total number of players.
     * @param mode The game mode, that indicate if AutoPlayer objects have to be created.
     */

    public void createPlayers(int nbH, int nbP, Mode mode){

        Scanner leScan = new Scanner(System.in);

        if (mode == Mode.HA){

            int compteur = 0;   //Is used for id attribution
            

            while(compteur < nbH){

                System.out.println("Entrez le nom du joueur n°" + compteur + " :");
                String nom = leScan.next();

                HumanPlayer play = new HumanPlayer(nom, compteur, this);
                this.players.add(play);

                compteur++;
            }

            while(compteur < nbP){

                System.out.println("Entrez le nom du joueur n°" + compteur + " :");
                String nom = leScan.next();

                AutoPlayer play = new AutoPlayer(nom, compteur, this);
                this.players.add(play);

                compteur++;
            }

            //scan.close();
        }

        else {

            int compteur = 0;

            while(compteur < nbP){

                System.out.println("Entrez le nom du joueur n°" + compteur + " :");
                String nom = leScan.next();;

                HumanPlayer play = new HumanPlayer(nom, compteur, this);
                this.players.add(play);

                compteur++;
            }

            //leScan.close();
        }
    };

    /**
     * This method create the batch of cards needed for the game, thanks to a .csv file
     * @param path The path to the folder containing the 3 .csv files
     * @throws IOException In case the path is wrong/lead to nothing.
     */

    public void createCards(String path){

        if (path != null){

            Scanner scanBat = null;
            Scanner scanMach = null;
            Scanner scanOuvr = null;

            try {
                
                scanBat = new Scanner(new FileReader(path+"/cartes batisseurs batiments.csv"));
                scanMach = new Scanner(new FileReader(path+"/cartes batisseurs machines.csv"));
                scanOuvr = new Scanner(new FileReader(path+"/cartes batisseurs ouvriers.csv"));

                scanBat.useDelimiter(",");
                scanMach.useDelimiter(",");
                scanOuvr.useDelimiter(",");

                //Les fichiers contiennent tous une première ligne de légende.

                //Instanciations batiments.

                String info = scanBat.next();
                info = scanBat.next();
                info = scanBat.next();
                info = scanBat.next();
                info = scanBat.next();
                info = scanBat.next();
                info = scanBat.next();

                while (scanBat.hasNext()){

                    String nom = scanBat.next();
                    int coins = scanBat.nextInt();
                    int victPoints = scanBat.nextInt();
                    int stone = scanBat.nextInt();
                    int wood = scanBat.nextInt();
                    int intell = scanBat.nextInt();
                    String tiles = scanBat.next();
                    tiles = tiles.trim();
                    int tile = Integer.parseInt(tiles);

                    Batiment bat = new Batiment(nom, wood, stone, intell, tile, coins, victPoints);
                    this.cards.add(bat);
                }

                //Instanciation machines 

                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();
                info = scanMach.next();

                int i = 0;

                while (scanMach.hasNext()){

                    String nom = scanMach.next();
                    int prodStone = scanMach.nextInt();
                    int prodWood = scanMach.nextInt();
                    int prodIntell = scanMach.nextInt();
                    int prodTile = scanMach.nextInt();
                    int victPoints = scanMach.nextInt();
                    int stone = scanMach.nextInt();
                    int wood = scanMach.nextInt();
                    int intell = scanMach.nextInt();
                    String tiles = scanMach.next();
                    tiles = tiles.trim();
                    int tile = Integer.parseInt(tiles);
                    
                    Machine mach = null;
                    if (i == 0) mach = new Machine(nom, wood, stone, intell, tile, victPoints, i, prodStone);

                    else if (i == 1) mach = new Machine(nom, wood, stone, intell, tile, victPoints, i, prodWood);
                    else if (i == 2) mach = new Machine(nom, wood, stone, intell, tile, victPoints, i, prodIntell);

                    else mach = new Machine(nom, wood, stone, intell, tile, victPoints, i, prodTile);

                    i++;
                    if(i == 4) i = 0;

                    this.cards.add(mach);
                }

                //Instanciation Ouvriers

                info = scanOuvr.next();
                info = scanOuvr.next();
                info = scanOuvr.next();
                info = scanOuvr.next();
                info = scanOuvr.next();
                info = scanOuvr.next();
                
                while (scanOuvr.hasNext()){

                    String nom = scanOuvr.next();
                    int cost = scanOuvr.nextInt();
                    int stone = scanOuvr.nextInt();
                    int wood = scanOuvr.nextInt();
                    int intell = scanOuvr.nextInt();
                    String tiles = scanOuvr.next();
                    tiles = tiles.trim();
                    int tile = Integer.parseInt(tiles);

                    Ouvrier ouvr = new Ouvrier(nom, wood, stone, intell, tile, cost);

                    this.cards.add(ouvr);
                }

                //Finally, the cards are shuffled
                Collections.shuffle(this.cards);

            } catch (FileNotFoundException e) {
                
                System.err.println("createCards(path) - Error : " + e.getMessage());
            }

            finally {

                if (scanBat != null) scanBat.close();
                if (scanMach != null) scanMach.close();
                if (scanOuvr != null) scanOuvr.close();
            }
        }
    };

    /**
     * This method is a getter that return the entire Card ArrayList cards.
     * @return An ArrayList of Card objects.
     */

    public ArrayList<Card> getCards(){

        return this.cards;
    };

    /**
     * This method is a getter that return the Batisseurs object that instanciated the current object.
     * @return lesbat attribute.
     */

    public Batisseurs getLesBat(){

        return this.lesBat;
    };

    /**
     * This method is a getter that return the entire Ouvrier ArrayList stackWorkers.
     * @return An ArrayList of Ouvrier objects.
     */

    public ArrayList<Ouvrier> getStackWorkers(){

        return this.stackWorkers;
    };

    /**
     * This method intialize the stack of workers with the first 5 Ouvrier objects from the batch of Card.
     */

    public void intializeStackWorkers(){

        this.stackWorkers.clear();
        int i = 0;
        boolean fin = false;

        while ( (i < this.cards.size()) && (!fin)){

            Card carte = this.cards.get(i);
            if (carte.getClass() == Ouvrier.class) {
                
                this.stackWorkers.add((Ouvrier)carte);
            }

            if (this.stackWorkers.size() == 5) fin = true;

            i++;
        }
    };

    /**
     * This method is a getter that return the entire Card ArrayList stackBuilding.
     * @return An ArrayList of Card objects.
     */

    public ArrayList<Card> getStackBuilding(){

        return this.stackBuilding;
    };

    /**
     * This method intialize the stack of building with the first 5 Card objects from the batch of Card.
     */

    public void intializeStackBuilding(){

        this.stackBuilding.clear();
        int i = 0;
        boolean fin = false;

        while ( (i < this.cards.size()) && (!fin)){

            Card carte = this.cards.get(i);
            if (carte.getClass() != Ouvrier.class) {
                
                this.stackBuilding.add((carte));
            }

            if (this.stackBuilding.size() == 5) fin = true;

            i++;
        }
    };

    public void start(){

        /*
        Reminder for the end of the game :

            - Only the points made by the building are counted
            - There is a bonus point for each 10 coins left
        */

        boolean fin = false;

        ConsoleDisplay displ = this.lesBat.getAffichageTxt();

        while (!fin){

            this.changeCurrent();
            this.current.setActions(3);
            this.current.setActionsBought(0);
            this.current.setCounterSend(0);
            this.current.getJoueSur().clear();
            this.intializeStackWorkers();
            this.intializeStackBuilding();

            displ.printGame();

            while ( (this.current.getActions() > 0) || (this.current.getActionsBought() > 0) ){

                this.current.play();
                this.intializeStackWorkers();
                this.intializeStackBuilding();
                displ.printGame();
            }

            this.turn++;

            if (this.current.getPoints() >= 17) {

                fin = true;

                //Fin du tour après un vainqueur
                Player vainqueur = this.current;

                this.changeCurrent();

                while (this.current != vainqueur){

                    this.current.setActions(3);
                    this.current.setActionsBought(0);
                    this.current.setCounterSend(0);
                    this.current.getJoueSur().clear();
                    this.intializeStackWorkers();
                    this.intializeStackBuilding();

                    displ.printGame();

                    while ( (this.current.getActions() > 0) || (this.current.getActionsBought() > 0) ){

                        this.current.play();
                        this.intializeStackWorkers();
                        this.intializeStackBuilding();
                        displ.printGame();
                    }

                    this.changeCurrent();

                    this.turn++;
                }
                
            }
        }

        displ.endOfGame();
    };

    public HashMap<String, Integer> endOfGame(){

        HashMap<String, Integer> ret = new HashMap<String, Integer>();

        return ret;

    };

    /**
     * This method change the player that is currently playing.
     */

    public void changeCurrent(){

        int indexC = this.players.indexOf(this.current);
        System.out.println(indexC);
        int newIndex = indexC++;

        if (newIndex >= this.players.size()) newIndex = 0;

        this.current = this.players.get(newIndex);
    }

    /**
     * This method is used to represent the Game current informations in a String.
     */

    public String toString(){

        String ret = "*******************************************\nBatiments & Machines disponibles : \n";

        int j = 0;
        
        for (Card carte : this.stackBuilding){

            ret += "\n----";
            ret += "\n" + j + " :\t" + carte.toString();

            j++;
        }

        ret += "\n\n*******************************************\nOuvriers pouvant être recrutés : \n";
        j = 0;

        for (Ouvrier ouvr : this.stackWorkers){

            ret += "\n----\n";
            ret += j + " :\t";
            ret += ouvr.toString();
            

            j++;
        }

        return ret;
    }

}