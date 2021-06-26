package model;

import java.util.ArrayList;

/**
 * This class' objects are Player played by a basic AI.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public class AutoPlayer extends Player {

    /**
     * The class' constructor.
     * @param name The player's name.
     * @param id The player's id.
     * @param game The game where the object player is playing
     */

    public AutoPlayer(String name, int id /*,Difficulty diff*/, Game game){

        super(name, id, game);
    };

    /**
     * This method allows the object to do automatic plays.
     */

    public void play(){

        boolean execCorrecte = false;

        //The game's available cards
        ArrayList<Card> lesCartes = this.getLeJeu().getStackBuilding();
        ArrayList<Ouvrier> lesOuvr = this.getLeJeu().getStackWorkers();

        int numAction = (int)(Math.random()*6);
        boolean alreadyPlayed = false;

        while (!execCorrecte){
            
            if (numAction == 0){

                int numBat = (int)(Math.random()*4);

                Card build = lesCartes.get(numBat);

                execCorrecte = this.openBuild(build);

            }

            else if (numAction == 1){

                int numOuvr = (int)(Math.random()*4);

                Ouvrier ouvr = lesOuvr.get(numOuvr);

                execCorrecte = this.recruit(ouvr);

            }

            else if (numAction == 2){

                int idOuvr = (int)(Math.random()*5);
                int idChantier = (int)(Math.random()*5);

                Card bat = this.getCards().get(idChantier);
                Ouvrier ouvr = this.getWorkers().get(idOuvr);

                execCorrecte = this.sendToWork(ouvr, bat);

                if (execCorrecte) {
                    
                    alreadyPlayed = this.getJoueSur().contains(bat);

                    if (!alreadyPlayed) this.getJoueSur().add(bat);

                    else this.setCounterSend(this.getCounterSend()+1);

                }
            }

            else if (numAction == 3){

                int actions = (int)((Math.random()*3)+1);

                execCorrecte = this.takeCoin(actions);
            }

            else if (numAction == 4){

                execCorrecte = this.buyAction();
            }
        }

        if (execCorrecte) {
            
            //This part is made to include the cost of actions that increase with the number of workers send on the same building in a turn.
            if ( (numAction == 2) && (alreadyPlayed)){

                int costActions = this.getCounterSend();

                while (this.getActions() > 0 && costActions > 0){

                    this.setActions(this.getActions()-1);
                    costActions--;
                }

                while (this.getActionsBought() > 0 && costActions > 0){

                    this.setActionsBought(this.getActionsBought()-1);
                    costActions--;
                }

            }

            else{
                
                if (this.getActions() > 0) this.setActions(this.getActions()-1);
                
                else if (this.getActionsBought() > 0 ) this.setActionsBought(this.getActionsBought()-1);
            }
        }
        
    };
}