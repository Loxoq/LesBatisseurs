package model;
import java.io.Serializable;

/**
 * This class allow the creation of a Batisseur card (with attributes such as wood, sotne, tile and intell).
 * The instanciation is the reasult of the reading of a line from a .csv file.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public abstract class Card implements Serializable{

    //The card's name
    private String name;

    //The wood needed/produced by the card
    private int wood;

    //The stone needed/produced by the card
    private int stone;

    //The intell needed/produced by the card
    private int intell;

    //The tile needed/produced by the card
    private int tile;

    //The cost needed/produced by the card
    private int cost;

    //The state of the card
    private boolean state;

    //The amount of victory points (only used by Batiment and Machine)
    protected int victPoints;


    /**
     * The class' constructor.
     * Instanciate a new card.
     * @param name The card's name
     * @param wood The amount of wood needed/produced by the card
     * @param stone The amount of stone needed/produced by the card
     * @param intell The amount of intelligence needed/produced by the card
     * @param tile The amount of tile needed/produced by the card
     * @param cost The card's cost
     */

    public Card(String name, int wood, int stone, int intell, int tile, int cost){

        if ( (name != null) && (wood >=0) && (stone >= 0) && (intell >= 0) && (tile >= 0) && (cost >= 0) ){

            name = name.replace("\n", "");
            this.name = name;
            this.wood = wood;
            this.stone = stone;
            this.intell = intell;
            this.tile = tile;
            this.cost = cost;

            this.state = false;
        }

        else if(name == null) throw new IllegalArgumentException("Card() - Error : The name is null.");
        else throw new IllegalArgumentException("Card() - Error : One or several parameter is/are smaller than 0.");
    };
    
    /**
     * This method is a getter that returns the name of the card.
     * @return A String which is the card's name.
     */

    public String getName(){

        return this.name;
    };

    /**
     * This method is a getter that returns the wood attribute of the current card.
     * @return An integer that correspond to the wood attribute
     */

    public int getWood(){

        return this.wood;
    };

    /**
     * This method is a setter that set the wood attribute of the current card.
     * It is useful to update the resources that are still needed.
     * @param wood The wood attribute, which is an integer corresponding to the amount of wood needed/produced by the card.
     */

    public void setWood(int wood){

        if (wood >= 0) this.wood = wood;
        else System.err.println("setWood(wood) - Error : wood parameter is smaller than 0.");
    };

    /**
     * This method is a getter that returns the stone attribute of the current card.
     * @return An integer that correspond to the stone attribute
     */

    public int getStone(){

        return this.stone;
    };

    /**
     * This method is a setter that set the stone attribute of the current card.
     * It is useful to update the resources that are still needed.
     * @param stone The stone attribute, which is an integer corresponding to the amount of stone needed/produced by the card.
     */

    public void setStone(int stone){

        if (stone >= 0) this.stone = stone;
        else System.err.println("setStone(stone) - Error : stone parameter is smaller than 0.");
    };

    /**
     * This method is a getter that returns the intell attribute of the current card.
     * @return An integer that correspond to the intelligence attribute
     */

    public int getIntell(){

        return this.intell;
    };

    /**
     * This method is a setter that set the intell attribute of the current card.
     * It is useful to update the resources that are still needed.
     * @param intell The intelligence attribute, which is an integer corresponding to the amount of intelligence needed/produced by the card.
     */

    public void setIntell(int intell){

        if (intell >= 0) this.intell = intell;
        else System.err.println("setIntell(intell) - Error : intell parameter is smaller than 0.");
    };

    /**
     * This method is a getter that returns the tile attribute of the current card.
     * @return An integer that correspond to the tile attribute
     */

    public int getTile(){

        return this.tile;
    };

    /**
     * This method is a setter that set the tile attribute of the current card.
     * It is useful to update the resources that are still needed.
     * @param tile The tile attribute, which is an integer corresponding to the amount of tile needed/produced by the card.
     */

    public void setTile(int tile){

        if (tile >= 0) this.tile = tile;
        else System.err.println("setTile(tile) - Error : tile parameter is smaller than 0.");
    };

    /**
     * This method is a getter that returns the cost attribute of the current card.
     * @return An integer that correspond to the cost attribute
     */

    public int getCost(){

        return this.cost;
    };

    /**
     * This method is a getter that returns the state of the card (under construction or finished, at work or free).
     * @return A false boolean if the building is not finished/the worker is free, true otherwise.
     */

    public boolean getState(){

        return this.state;
    };
    
    /**
     * This method is a setter that set the state of the machine.
     * If the machine is finished, the attribute state is set at true, it stays at false otherwise.
     * @param state The (new) state of the machine
     */

    public void setState(boolean state){

        this.state = state;
    };

    /**
     * This method is a getter that returns the victory points amount of the card (onlmy for building and machine).
     * @return An integer corresponding to the building's victory points.
     */

    public int getVictPoints(){

        return this.victPoints;
    };

    /**
     * This method set the Card where the worker send the ressources he products.
     * @param bat The Card "where" the worker works.
     */

    public void setWorkOn(Card bat){};

    /**
     * This method allows the console display to print on screen this card informations.
     * @return The Card's informations, as a String.
     */

    public String toString(){

        String ret = this.name + "\t" + "Bois : " + this.wood + "  Pierre : " + this.stone + "  Intelligence : " + this.intell + "  Tuile : " + this.tile;
        ret += "  Prix : " + this.cost + "  Points : " + this.victPoints;

        return ret;
    }

}