package model;

/**
 * This class is inherits the Card class. This class' objects are Ouvrier cards.
 * Therefore, theses objects will produce resources and will be linked to Batiment and Machines cards.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public class Ouvrier extends Card{

    //The building the worker is linked with
    private Card workOn;
    
    /**
     * The class' constructor.
     * Instanciate a new Ouvrier.
     * @param wood The amount of wood produced by the card
     * @param stone The amount of stone produced by the card
     * @param intell The amount of intelligence produced by the card
     * @param tile The amount of tile produced by the card
     * @param cost The card's cost
     */

    public Ouvrier(String name, int wood, int stone, int intell, int tile, int cost){

        super(name, wood, stone, intell, tile, cost);
    };

    /**
     * This method will make the object "product" resources for the Batiment/Machine card it is linked to.
     * @deprecated The following method is useless and don't correspond to the original game functionnment.
     */

    /*
    public void product(){

        if (this.workOn != null){

            int bois = this.workOn.getWood();
            int stone = this.workOn.getWood();
            int intell = this.workOn.getWood();
            int tile = this.workOn.getWood();
            
            this.workOn.setWood(bois-this.getWood());
            this.workOn.setStone(stone-this.getStone());
            this.workOn.setIntell(intell-this.getIntell());
            this.workOn.setTile(tile-this.getTile());

            bois = this.workOn.getWood();
            stone = this.workOn.getWood();
            intell = this.workOn.getWood();
            tile = this.workOn.getWood();

            if ( (bois <=0) && (stone <= 0) && (intell <=0) && (tile <= 0) ){

                //If the building is finished, we set the adequate status and the worker is free.
                this.workOn.setState(true);

                this.workOn = null;
                this.setState(false);
            }
        }

        else System.err.println("product() - Error : The worker can't produce anything, he isn't linked to a building.");
    }; */

    /**
     * This method set the Card where the worker send the ressources he products.
     * @param bat The Card "where" the worker works.
     */
    
    public void setWorkOn(Card bat){

        if ( (bat.getClass() == Batiment.class || bat.getClass() == Machine.class) && (!bat.getState())){

            this.workOn = bat;
            this.setState(true);
        }

        else if (bat == null) this.workOn = null;

        else if (bat.getState()) System.err.println("setWorkOn(bat) - Error : The building is already finished.");
        
        else System.err.println("setWorkOn(bat) - Error : The bat parameter is null.");
    };

    /**
     * This method returns the workOn attribute.
     * @return The workOn attribute
     */

    public Card getWorkOn(){

        return this.workOn;
    }

    /**
     * This method allows a player to free the current object, by setting the workOn attribute on null/
     */

    public void setNullWorkOn(){

        this.workOn = null;
    }

    
    public String toString(){

        String ret = super.toString();

        if (this.workOn != null) ret += "\t\t\tTravaille sur " + this.workOn.getName();

        else ret += "\t\t\tNe travaille pas";

        return ret;
    }
}