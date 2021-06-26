package view;
import model.*;
import java.util.ArrayList;
import java.io.*;

/**
 * This class will display the game informations on a terminal.
 * @author T. Le Berre
 */

public class ConsoleDisplay extends Display implements Serializable {

    /**
     * The class' constructor.
     * @param lesBat The Batisseurs object which informations will be displayed
     */
    public ConsoleDisplay(Batisseurs lesBat) {

        super(lesBat);

    }

    /**
     * This method display the rules of the game.
     */
    public void rules(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\t\t\t\t\t\t\t\t\t\tBienvenue dans \"Les Batisseurs - Moyen Âge\" !");

        System.out.println("\nBUT DU JEU : Les joueurs doivent accumuler le plus de points de victoire en construisant des bâtiments.\n");

        System.out.println("Déroulement du jeu : Lors de son tour de jeu, un joueur dispose de 3 actions gratuites auxquelles il peut, s’il le souhaite, ajouter une ou plusieurs actions payantes. \nUne action payante coûte 5 Écus.");

        System.out.println("\n---------\nUn joueur a 5 actions possibles : Ouvrir un chantier, recruter un ouvrier, envoyer travailler un ouvrier, échanger une ou plusieurs actions contre des écus, ou acheter une ou plusieurs actions.\n");

        System.out.println("\n* OUVRIR UN CHANTIER : Le joueur prend un Bâtiment parmi les cinq disponibles dans la ligne et le place devant lui. On dit qu’il « ouvre » un Chantier. Il le remplace alors immédiatement dans la ligne par le premier Bâtiment du paquet.");
        System.out.println("Il est possible d’Ouvrir plusieurs Chantiers en parallèle. Le joueur peut répéter l’opération tant qu’il a des actions pour le faire.");

        System.out.println("\n* RECRUTER UN OUVRIER :  Le joueur prend un Ouvrier parmi les cinq disponibles et le place devant lui. On dit qu’il « recrute » un Ouvrier. \nIl le remplace alors immédiatement dans la ligne par le premier Ouvrier du paquet. Le joueur peut répéter l’opération tant qu’il a des actions pour le faire.");

        System.out.println("\n* ENVOYER TRAVAILLER UN OUVRIER : Le joueur pose un de ses Ouvriers à côté d’un Bâtiment en Chantier. Au moment de poser l’Ouvrier, le joueur doit payer le nombre d’Écus indiqué en haut à droite decelui-ci dans la Réserve.\nUne fois un Ouvrier engagé sur un Chantier, il ne peut plus être déplacé tant que le Chantier n’est pas terminé.");
        System.out.println("A chaque fois qu’un nouvel Ouvrier rejoint ses camarades sur un Chantier on le pose de façon à mettre ses Ressources en face de celles des autres .\nAttention, envoyer un deuxième ouvrier travailler sur le même tour coûter deux actions, même chose pour un troisième ouvrier.");
        
        System.out.println("\n* PRENDRE DES ECUS : Pour 1 action, un joueur peut prendre 1 écu ; 2 actions, 3 écus; 3 actions, 6 écus");

        System.out.println("\n* ECHANGER DES ECUS POUR UNE ACTION : une action payante coûte 5 écus.");

        System.out.println("\n--------\nLes machines : Les Machines sont des Bâtiments un peu particulier, puisqu’une fois terminées, elles sont considérées comme des Ouvriers non rémunérés pour leur travail (leur coût en Écus lorsqu’on les pose sur un Chantier est de zéro)."); 
        System.out.println("Une Machine se construit exactement comme un Bâtiment classique et rapporte des points de victoire lorsqu’elle est terminée."); 
        System.out.println("Mais, au lieu d’être mise de côté comme un Bâtiment classique, elle reste en jeu une fois la carte retournée et vient rejoindre l’équipe d’Ouvriers du joueur.");

        System.out.println("\nFIN DE PARTIE : Lorsqu’un joueur, à la fin de son tour, atteint ou dépasse 17 points de victoire (en comptant les Bâtiments et les Machines mais pas les Écus), il déclenche la fin de partie. \nSeuls les joueurs qui n’ont pas encore joué lors de ce tour peuvent encore le faire, de façon à ce que tout le monde ait joué autant de tours."); 
        
        System.out.println("Ainsi, si c’est le premier joueur qui déclenche la fin de partie, tous les autres joueurs doivent encore jouer une fois. A l’opposé, si c’est le dernier joueur qui déclenche la fin de partie, le jeu s’arrête à la fin de son tour.");
        System.out.println("Chaque joueur additionne alors les points de victoire des Bâtiments et des Machines terminés, auxquels il ajoute 1 point par tranche complète de 10 Écus encore en sa possession. Celui qui a le plus de points de victoire devient le Premier Bâtisseur du Royaume et est déclaré vainqueur !");
        System.out.println("En cas d’égalité, c’est le joueur avec le plus de points de victoire grâce à ses bâtiments qui est le vainqueur. S’il y a encore égalité, c’est celui qui a le plus de pièces qui l’emporte.\n");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");

    }

    /**
     * This method display the game's informations (available cards, player informations, etc.)
     */
    public void printGame(){

        Game jeu = this.batiss.getGame();

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n=================================================================================================================================================================================================================");

        //Display the available cards

        Player curr = jeu.getCurrentPlayer();

        System.out.println("\t\t\t\t\t\t\t\t\t\tTour n°" + jeu.getTurn());
        System.out.println(jeu.toString());
        System.out.println(curr.toString());
        
    }

    /**
     * This method display the winner at the end of the game.
     */
    public void endOfGame(){

        System.out.println("WINNER : " + this.batiss.getGame().getCurrentPlayer());
    };
}