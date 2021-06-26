package model;

/**
 * This class inherits the Card class.
 * This class' objects represents different types of building that need resources to be finished.
 * When a building is finished, it gives the player a certain amount of victory points.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public class Batiment extends Card {

    /**
     * The class' constructor.
     * It instanciates a new Batiment card, with a status at false and a certain amount of victory points.
     * @param vict The amount of victory points granted to the player when the building is finished.
     * @param wood The amount of wood needed by the card
     * @param stone The amount of stone needed by the card
     * @param intell The amount of intelligence needed by the card
     * @param tile The amount of tile needed by the card
     * @param cost The card's cost
     */

    public Batiment(String name, int wood, int stone, int intell, int tile, int cost, int vict){

        super(name, wood, stone, intell, tile, cost);
        
        if (vict >= 0) this.victPoints = vict;

        else throw new IllegalArgumentException("Batiment() - Error : The amount of victory point specified is smaller than 0");
        
    };
}