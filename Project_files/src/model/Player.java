package model;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * This class allow the game engine to instanciate from 2 to 4 players.
 * This class is abstract and implement the common functions a Player must have to play the game.
 * Each object is linked to a batch of cards that represent the player's cards.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public abstract class Player implements Serializable{

    //The player's name
    private String name;

    //The player's id in the list
    private int id;

    //The player's coins
    private int coins;

    //The player's number of actions
    private int actions;

    //The player's number of bought actions during a turn
    private int actionsBought;

    //The Buildings cards owned by the player
    private ArrayList<Card> playCards;

    //The Ouvriers cards owned by the player
    private ArrayList<Ouvrier> workers;

    //The finished Machine cards owned by the player
    private ArrayList<Machine> machines;

    //The game where the object player is playing
    private Game leJeu;

    //The points that each player have
    private int vPoints;

    //A specific counter that count the number of times the current player send a worker to work
    private int counterSend;

    //An ArrayList of Batiment that is useful to save the building where a worker has been sent during a turn
    private ArrayList<Card> joueSur;


    /**
     * The class' constructor, that create a player with a name, an id, and a default
     * amount of coins and actions.
     * @param name The player's name
     * @param id The player's id, that need to be between 0 and 4 (excluded)
     * @param game The game where the object player is playing
     */

    public Player(String name, int id, Game game){

        if ( (name != null) && (id >= 0) && (game != null)){

            this.name = name;
            this.id = id;
            this.leJeu = game;

            this.playCards = new ArrayList<Card>();
            this.workers = new ArrayList<Ouvrier>();
            this.machines = new ArrayList<Machine>();
            this.joueSur = new ArrayList<Card>();

            this.actionsBought = 0;
            this.actions = 3;
            this.coins = 10;
            this.vPoints = 0;

            //Apprentice attribution
            ArrayList<Card> cartes = this.leJeu.getCards();

            boolean trouve = false;
            int i = 0;

            while ( (i < cartes.size()) && (!trouve)){

                Card laCarte = cartes.get(i);
                if ( (laCarte.getClass() == Ouvrier.class) && (laCarte.getName().contains("Apprenti")) ){

                    trouve = true;
                    this.workers.add((Ouvrier)laCarte);
                    this.leJeu.getCards().remove(laCarte);
                }

                i++;
            }
        }

        else if (name == null) throw new IllegalArgumentException("Player() - Error : name is null.");

        else throw new IllegalArgumentException("Player() - Error : id is smaller than 0.");
    };

    /**
     * This method is a getter that return the player's name.
     * @return The player's name attribute which is a String.
     */

    public String getName(){

        return this.name;
    };

    /**
     * This method is a getter that return the player's id.
     * @return The player's id attribute which is an integer.
     */

    public int getId(){

        return this.id;
    };

    /**
     * This method is a getter that return the player's cards owned.
     * @return The player's playCards attribute which is an ArrayList of Card objects.
     */

    public ArrayList<Card> getCards(){

        return this.playCards;
    };

    /**
     * This method is a getter that return the player's amount of coins.
     * @return The player's coins attribute which is an integer.
     */

    public int getCoins(){

        return this.coins;
    };

    /**
     * This method is a setter that set the value of the player's amount of coins.
     * This method is useful to update the coins attributes after the purchase of a card.
     * @param coins The new amount of coins.
     */

    public void setCoins(int coins){

        if (coins >= 0) this.coins = coins;

        else System.err.print("setCoins(coins) - Error : Parameter coins is smaller than 0.");
    };

    /**
     * This method is a getter that return the player's number of actions.
     * @return The player's actions attribute which is an integer.
     */

    public int getActions(){

        return this.actions;
    };

    /**
     * This method is a setter that set the value of the player's remaining actions.
     * This method is useful to update the actions attribute after an action has been done.
     * @param actions The new amount of actions.
     */

    public void setActions(int actions){

        if (actions >= 0) this.actions = actions;

        else System.err.print("setActions(actions) - Error : Parameter actions is smaller than 0.");
    };

    /**
     * This method is a getter that return the player's number of actions purchased during a turn.
     * @return The player's actionsBought attribute which is an integer.
     */

    public int getActionsBought(){

        return this.actionsBought;
    };

    /**
     * This method is a setter that set the value of the player's purchased actions.
     * This method is useful to update the actions attribute after an action has been bought.
     * @param actionsBought The new amount of actions bought.
     */

    public void setActionsBought(int actionsBought){

        if (actionsBought >= 0) this.actionsBought = actionsBought;

        else System.err.print("setActionsBought(actionsBought) - Error : Parameter actionsBought is smaller than 0.");

    };

    /**
     * This method is a getter that return the player's amount of points.
     * @return The player's vPoints attribute which is an integer.
     */

    public int getPoints(){

        return this.vPoints;
    };

    /**
     * This method is a setter that increase the value of the player's amount of points.
     * This method is useful to update the actions attribute after an action has been bought.
     * @param points The new amount of actions bought.
     */

    public void setVPoints(int points){

        if (points >= 0) this.vPoints += points;

        else System.err.print("setVPoints(points) - Error : Parameter points is smaller than 0.");

    };

    /**
     * This method is a getter that return the player's number of "Send to work" actions done.
     * @return The player's counterSend attribute.
     */

    public int getCounterSend(){

        return this.counterSend;
    };

    /**
     * This method is a setter that set the value of the player's "Send to work" actions played.
     * @param counter The new counter's value
     */

    public void setCounterSend(int counter){

        this.counterSend = counter;
    };

    /**
     * This method is a getter that return the player's game.
     * @return The game in which the player is playing.
     */

    public Game getLeJeu(){

        return this.leJeu;
    };

    /**
     * This method returns the workers attribute.
     * @return An ArrayList of Ouvrier.
     */

    public ArrayList<Ouvrier> getWorkers(){

        return this.workers;
    }

    /**
     * This method returns the machines attribute.
     * @return An ArrayList of Machine.
     */

    public ArrayList<Machine> getMachines(){

        return this.machines;
    }

    /**
     * This method returns the joueSur attribute.
     * @return An ArrayList of Card.
     */

    public ArrayList<Card> getJoueSur(){

        return this.joueSur;
    }

    //--------- THE 5 PLAY METHODS -------------------------------------

    /**
     * This method allows a Player object to purchase a new action in exchange of some of his coins.
     * @return true if the action has been correctly done, false otherwise.
     */

    public boolean buyAction(){

        boolean ret = false;

        if (this.coins >=5) {

            this.setCoins(this.coins-5);
            this.actionsBought++;
            ret = true;

        } else System.err.println("buyActions() - Error : Not enough coins left to buy a new action.");

        return ret;
    };

    /**
     * This method allows a Player object to recruit a new worker displayed on the board.
     * @param ouvr The Ouvrier object to add to the ArrayList of owned Ouvrier
     * @return true if the action has been correctly done, false otherwise.
     */

    public boolean recruit(Ouvrier ouvr){

        boolean ret = false;

        if ( (ouvr != null) && (this.getCoins()-ouvr.getCost() >= 0)){

            this.workers.add(ouvr);
            ret = true;
            this.setCoins(this.getCoins()-ouvr.getCost());

            this.leJeu.getCards().remove(ouvr);
            this.leJeu.getStackWorkers().remove(ouvr);
        
        } 
        
        else if (this.getCoins()-ouvr.getCost() < 0) System.err.println("recruit(ouvr) - Error : This card is too expensive.");
        
        else System.err.println("recruit(ouvr) - Error : Ths ouvr parameter is null.");

        return ret;
    };

    /**
     * This method allows a Player object to open a new construction among the Batiment/Machine displayed on the board.
     * @param bat The building to put under construction.
     * @return true if the action has been correctly done, false otherwise.
     */

    public boolean openBuild(Card bat){

        boolean ret = false;

        if ( (bat != null) &&  (bat.getClass() == Batiment.class || bat.getClass() == Machine.class) && (this.getCoins()-bat.getCost() >= 0)){

            this.playCards.add(bat);
            ret = true;
            this.setCoins(this.getCoins()-bat.getCost());

            this.leJeu.getCards().remove(bat);
            this.leJeu.getStackBuilding().remove(bat);

        }

        else if (bat == null) System.err.println("openBuild(bat) - Error : bat parameter is null.");

        else if (bat.getClass() == Ouvrier.class) System.err.println("openBuild(bat) - Error : A worker is not a building");

        else if (bat.getState()) System.err.println("openBuild(bat) - Error : The building is already finished.");

        else if (this.getCoins()-bat.getCost() < 0) System.err.println("openBuild(bat) - Error : This card is too expensive.");
        
        return ret;
    };

    /**
     * This method allows a Player object to send to work a worker.
     * It will link an Ouvrier Card to a Batiment/Machine card.
     * @param ouvr The Ouvrier object to link.
     * @param bat The building on which the Ouvrier object will work.
     * @return true if the action has been correctly done, false otherwise.
     */

    public boolean sendToWork(Card ouvr, Card bat){

        boolean ret = false;

        //We check that the parameters are initialized, that the building is under construction, that the worker is not working on an another building, and that the cards are in the current player's list of owned cards.
        if( (bat.getClass() == Batiment.class || bat.getClass() == Machine.class) && (bat != null) && (ouvr != null) && (ouvr.getClass() == Ouvrier.class || ouvr.getClass() == Machine.class) && (!ouvr.getState()) && (!bat.getState()) && (this.workers.contains(ouvr)) && (this.playCards.contains(bat))){

            ouvr.setWorkOn(bat);
            ret = true;
        }

        //We now check if the building linked is finished :

        boolean checkFin = false;

        int wood = 0;
        int stone = 0;
        int intell = 0;
        int tile = 0;

        //Ouvriers
        for (int i = 0; i < this.workers.size(); i++){

            Ouvrier LOuvr = this.workers.get(i);

            if (LOuvr.getWorkOn() == bat){

                wood += LOuvr.getWood();
                stone += LOuvr.getStone();
                intell += LOuvr.getIntell();
                tile += LOuvr.getTile();
            }
        }

        //Machines
        for (int j = 0; j < this.machines.size(); j++){

            Machine mach = this.machines.get(j);

            if (mach.getWorkOn() == bat){

                wood += mach.getWood();
                stone += mach.getStone();
                intell += mach.getIntell();
                tile += mach.getTile();
            }
        }

        //We check if the sum of all the resources produced is enough finished the building
        if ( (wood >= bat.getWood()) && (stone >= bat.getStone()) && (intell >= bat.getIntell()) && (tile >= bat.getTile())){

            bat.setState(true);
            checkFin = true;
            ret = true;

            this.setVPoints(bat.getVictPoints());

            if (bat.getClass() == Machine.class) this.machines.add((Machine)bat);
        }

        //We now set free all the workers and machines
        if(checkFin){

            //Ouvriers
            for (int i = 0; i < this.workers.size(); i++){

                Ouvrier LOuvr = this.workers.get(i);

                if (LOuvr.getWorkOn() == bat){

                    LOuvr.setState(false);
                    LOuvr.setNullWorkOn();
                }
            }

            //Machines
            for (int j = 0; j < this.machines.size(); j++){

                Machine mach = this.machines.get(j);

                if (mach.getWorkOn() == bat){

                    mach.setState(false);
                    mach.setNullWorkOn();
                }
            }

        }

        if (ouvr.getState()) System.err.println("sendToWork(ouvr, bat) - Error : The worker is already working on an another building.");
        
        else if (bat.getState()) System.err.println("sendToWork(ouvr, bat) - Error : The building is already finished.");
        
        else if (bat.getClass() == Ouvrier.class) System.err.println("sendToWork(ouvr, bat) - Error : A worker is not a building, a worker can't work on a worker.");

        else if (bat == null || ouvr == null) System.err.println("sendToWork(ouvr, bat) - Error : One of the parameter is null.");

        else System.err.println("sendToWork(ouvr, bat) - Error : The worker and / or the building is / are not owned by the player.");

        return ret;
    };

    /**
     * This method allows a Player object to exchange some of his actions for coins.
     * @param nbActions The number of actions to exchange, that must be between 1 & 3.
     * @return True if everything went well, false otherwise.
     */

    public boolean takeCoin(int nbActions){

        boolean ret = false;

        if ( (nbActions > 0) && (nbActions < 4) ) {

            if ( (nbActions == 1) && ( (this.actions+this.actionsBought) >= 1)) {
                
                if (this.actions >= 1) this.actions--;
                else this.actionsBought--;

                this.coins++;
                this.leJeu.setReserve(leJeu.getReserve()-1);
                ret = true;

            } else if ( (nbActions == 2) && ((this.actions+this.actionsBought)) >= 2 ){

                int counter = 2;

                while ( (this.actions > 0) && (counter > 0)){

                    counter--;
                    this.actions--;
                }
                
                while ( (this.actionsBought > 0) && (counter > 0)){

                    counter--;
                    this.actions--;
                }

                this.coins += 3;
                this.leJeu.setReserve(leJeu.getReserve()-3);
                ret = true;

            }

            else if ( (nbActions == 3) && (this.actions+this.actionsBought) >=3 ) {

                int counter = 3;

                while ( (this.actions > 0) && (counter > 0)){

                    counter--;
                    this.actions--;
                }
                
                while ( (this.actionsBought > 0) && (counter > 0)){

                    counter--;
                    this.actions--;
                }

                this.coins += 6;
                this.leJeu.setReserve(leJeu.getReserve()-6);
                ret = true;

            }

        } else System.err.println("takeCoin(nbActions) - Error : The number of actions to exchange must be between 1 & 3.");

        return ret;
    }

    /**
     * This method allow the player to make a move.
     */

    public abstract void play();

    /**
     * This method allows the console display to print on screen this player informations.
     * @return The player's informations, as a String.
     */

    public String toString(){

        String ret = "\n=======================================================================================================================\n\t\t\t\t\t\tPlayer n° " + this.id;
        ret += "\tName : " + this.name;
        ret += "\nPoints de victoire : " + this.vPoints;
        ret += "\nEcus : " + this.coins;
        ret += "\tActions restantes : " + (this.actions + this.actionsBought) + " dont " + this.actionsBought + " actions achetées.";

        ret += "\n\n------------------------------------------------------------\nConstructions en cours : \n\n";

        //Display of the constructions

        int i = 0;

        for (Card laCarte : this.playCards){

            if ( ((laCarte.getClass() == Batiment.class) || (laCarte.getClass() == Machine.class)) && (!laCarte.getState()) ){

                ret += "-----\n" + i + " :  " + laCarte.toString();
                i++;

                for (Ouvrier ouvr : this.workers){

                    if(ouvr.getWorkOn() == laCarte) ret += "\n" + ouvr.toString();
                }

                ret += "\n";

                for (Machine mach : this.machines){

                    if(mach.getWorkOn() == laCarte) ret += mach.toString();
                }
            }
        }

        ret += "\n------------------------------\nOuvriers et machines disponibles :\n\n";

        int j = 0;


        //Display of the free workers
        for (Ouvrier ouvr : this.workers){

            if(!ouvr.getState()) {
                
                ret += j + " : " + ouvr.toString();
                j++;
            }
        }

        //Display of the free machines
        for (Machine mach : this.machines){

            if(!mach.getWorkingStatus()) {
                
                ret += j + " : " + mach.toString();
                j++;
            }
        }

        
        return ret;
    }


}