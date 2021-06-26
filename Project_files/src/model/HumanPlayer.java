package model;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class' objects are Player played by humans.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public class HumanPlayer extends Player {

    //This scanActner will be useful to read the player's action in the console version
    private transient Scanner scanAct;

    /**
     * The class' constructor.
     * @param name The player's name.
     * @param id The player's id.
     * @param game The game the player plays in.
     */

    public HumanPlayer(String name, int id, Game game){

        super(name, id, game);
        this.scanAct = new Scanner(System.in);
    };

    /**
     * This method allows the player to make a move.
     */

    public void play(){

        //We first display the possible actions and the corresponding number
        System.out.println("\n==============================");

        System.out.println("Actions : ");
        System.out.println("- Ouvrir un chantier \t\t\t--> 0");
        System.out.println("- Recruter un ouvrier \t\t\t--> 1");
        System.out.println("- Envoyer travailler un ouvrier \t--> 2");
        System.out.println("- Prendre un ou plusieurs écus \t\t--> 3");
        System.out.println("- Acheter une nouvelle action \t\t--> 4");
        System.out.println("- Sauvegarder la partie \t\t--> 5");

        boolean execCorrecte = false;
        int action = 0;

        //The Card (building or machine) where the worker will be send to work in case the corresponding action is played, the boolean tell if the building is already in the ArrayList.
        Card bat = null;
        boolean alreadyPlayed = false;


        while (!execCorrecte){

            System.out.println("\nJouer l'action n° : \t");

            //The chosen action number is get
            action = this.scanAct.nextInt();

            //While a correct action id has not been selected, a new action id is asked
            while ( (action < 0) || (action > 5)) {
            
                System.out.println("N° incorrect, veuillez entrer un nouveau n° entre 0 et 5 : ");
                action = this.scanAct.nextInt();
            }

            //The game's available cards
            ArrayList<Card> lesCartes = this.getLeJeu().getStackBuilding();
            ArrayList<Ouvrier> lesOuvr = this.getLeJeu().getStackWorkers();

            //Opening of a building
            if (action == 0) {

                System.out.println("Veuillez choisir la carte Bâtiment/Machine : ");

                int numBat = this.scanAct.nextInt();

                //We check if the chosen card is part of the 5 displayed cards on the board

                while ( (numBat < 0) || (numBat > 4) ) {

                    System.out.println("Veuillez choisir une carte posée sur le plateau :");
                    numBat = this.scanAct.nextInt();
                }

                Card build = lesCartes.get(numBat);

                execCorrecte = this.openBuild(build);
            }

            //Worker recruitement
            else if (action == 1){

                System.out.println("Veuillez choisir l'ouvrier à recruter : ");

                int numOuvr = this.scanAct.nextInt();

                while ( (numOuvr < 0) || (numOuvr > 4) ) {

                    System.out.println("Veuillez choisir une carte posée sur le plateau :");
                    numOuvr = this.scanAct.nextInt();
                }

                                
                Ouvrier ouvr = lesOuvr.get(numOuvr);

                execCorrecte = this.recruit(ouvr);

            }

            //Send a worker to work
            else if (action == 2){

                System.out.println("Veuillez choisir l'ouvrier à envoyer travailler : ");

                int idOuvr = this.scanAct.nextInt();

                //While a correct worker has not been selected, a new id is asked
                while ( (idOuvr <0 ) || (idOuvr > this.getWorkers().size()) ){

                    System.out.println("Veuillez choisir un ouvrier dans la liste des ouvriers détenus : ");
                    idOuvr = this.scanAct.nextInt();
                }

                System.out.println("Veuillez choisir le chantier sur lequel l'ouvrier doit travailler : ");

                int idChantier = this.scanAct.nextInt();

                bat = this.getCards().get(idChantier);
                Ouvrier ouvr = this.getWorkers().get(idOuvr);

                execCorrecte = this.sendToWork(ouvr, bat);

                if (execCorrecte) {
                    
                    alreadyPlayed = this.getJoueSur().contains(bat);

                    if (!alreadyPlayed) this.getJoueSur().add(bat);

                    else this.setCounterSend(this.getCounterSend()+1);

                }
            }

            //Take one or several coins
            else if (action == 3){

                System.out.println("Veuillez choisir le nombre d'actions à échanger : ");

                int actions = this.scanAct.nextInt();

                while ( (actions < 1 ) || (actions > 3 )){

                    System.out.println("Le nombre d'actions à échanger doit être entre 1 et 3, veuillez entrer à nouveau un nombre : ");
                    actions = this.scanAct.nextInt();

                }

                execCorrecte = this.takeCoin(actions);
            }

            //Buy a new action
            else if (action == 4){

                execCorrecte = this.buyAction();

                if(execCorrecte) System.out.println("Action achetée ! Il vous reste " + this.getCoins() + " pièces.");
            }

            //Save
            else {

                System.out.println("Entrez le chemin du fichier de sauvegarde, de la forme /Documents/sauvegarde/monFichier.txt : ");
                String savePath = this.scanAct.next();
                this.getLeJeu().getLesBat().save(savePath);
            }
        }

        if (execCorrecte) {
            
            //This part is made to include the cost of actions that increase with the number of workers send on the same building in a turn.
            if ( (action == 2) && (alreadyPlayed)){

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
    }

    /**
     * This method recrate a Scanner after a game load
     */

    public void loadScanner(){

        this.scanAct = new Scanner(System.in);
    }
}