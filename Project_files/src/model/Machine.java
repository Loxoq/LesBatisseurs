package model;

/**
 * This class inherits the Card class.
 * This class' objects represents different types of machines that need resources to be finished.
 * When a machine is finished, it gives the player a certain amount of victory points, and adopt the same behavior as a Ouvrier card.
 * @author Titouan LE BERRE
 * @version 1.0
 */

public class Machine extends Card{

    //The amount of wood produced
    private int prodWood;

    //The amount of stone produced
    private int prodStone;

    //The amount of intell produced
    private int prodIntell;

    //The amount of tile produced
    private int prodTile;

    //The working status of the machine
    private boolean workingStatus;

    //The building the machine is linked with
    private Card workOn;

    /**
     * The class' constructor.
     * It instanciates a new Machine card, with a status at false and a certain amount of victory points.
     * @param vict The amount of victory points granted to the player when the machine is finished.
     * @param wood The amount of wood needed by the card
     * @param stone The amount of stone needed by the card
     * @param intell The amount of intelligence needed by the card
     * @param tile The amount of tile needed by the card
     * @param attr The attribute that will be produced
     * @param value The amount of ressource produced
     */

    public Machine(String name, int wood, int stone, int intell, int tile, int vict, int attr, int value){

        super(name, wood, stone, intell, tile, 0);

        if ( (wood >=0) && (stone >= 0) && (intell >= 0) && (tile >= 0) && (value > 0)){

            if (attr == 0) this.prodStone = value;
            else if (attr == 1) this.prodStone = value;
            else if (attr == 2) this.prodIntell = value;
            else if (attr == 3) this.prodTile = value;
            
            this.workingStatus = false;
        }

        else throw new IllegalArgumentException("Machine() - Error : One or several parameter is/are smaller than 0.");

    };

    /**
     * This method will make the object "product" resources for the Batiment/Machine card it is linked to.
     */

    /*
    public void product(){

        //We check that the machine is link to a building and that it is not finished yet, that the machine is finished, that the status of the machine is declared at work.
        if ( (this.workOn != null) && (this.workingStatus) && (this.getState()) && (!this.workOn.getState()) ){

            int bois = this.workOn.getWood();
            int stone = this.workOn.getWood();
            int intell = this.workOn.getWood();
            int tile = this.workOn.getWood();
            
            this.workOn.setWood(bois-this.getProdWood());
            this.workOn.setStone(stone-this.getProdStone());
            this.workOn.setIntell(intell-this.getProdIntell());
            this.workOn.setTile(tile-this.getProdTile());

            bois = this.workOn.getWood();
            stone = this.workOn.getStone();
            intell = this.workOn.getIntell();
            tile = this.workOn.getTile();

            if ( (bois <=0) && (stone <= 0) && (intell <=0) && (tile <= 0) ){

                //If the building is finished, we set the adequate status and the worker is free.
                this.workOn.setState(true);

                this.workOn = null;
                this.setWorkingStatus(false);
            }

        }

        else if (!this.workingStatus) System.err.println("product() - Error : The machine is not declared at work, workingStatus = false.");
        else if (!this.getState()) System.err.println("product() - Error : The machine is not finished.");
        else if (!this.workOn.getState()) System.err.println("product() - Error : The building is already finished.");
        else System.err.println("product() - Error : The machine is not linked to any building.");


        
    }; */

    /**
     * This method is a getter that returns the amount of wood produced by the card once its building is finished.
     * @return The prodWood attribute.
     */

    public int getProdWood(){

        return this.prodWood;
    }

    /**
     * This method is a getter that returns the amount of stones produced by the card once its building is finished.
     * @return The prodStone attribute.
     */

    public int getProdStone(){

        return this.prodStone;
    }

    /**
     * This method is a getter that returns the amount of intell produced by the card once its building is finished.
     * @return The prodIntell attribute.
     */

    public int getProdIntell(){

        return this.prodIntell;
    }

    /**
     * This method is a getter that returns the amount of tiles produced by the card once its building is finished.
     * @return The prodTile attribute.
     */

    public int getProdTile(){

        return this.prodTile;
    }

    /**
     * This method is a getter that returns the working status of the machine.
     * @return The working satus.
     */

    public boolean getWorkingStatus(){

        return this.workingStatus;
    }

    /**
     * This method is a setter that set the working status of the machine.
     * @param status The new working status of the machine.
     */

    public void setWorkingStatus(boolean status){

        this.workingStatus = status;
    }

    /**
     * This method set the Card where the worker send the ressources he products.
     * @param bat The Card "where" the worker works.
     */

    public void setWorkOn(Card bat){

        if ( (bat.getClass() == Batiment.class || bat.getClass() == Machine.class) && (!bat.getState())){

            this.workOn = bat;
        }

        else if (bat.getState()) System.err.println("setWorkOn(bat) - Error : The building is already finished.");
        
        else System.err.println("setWorkOn(bat) - Error : The bat parameter is null.");
    };

    /**
     * This method allows a player to free the current object, by setting the workOn attribute on null.
     */

    public void setNullWorkOn(){

        this.workOn = null;
    }

    /**
     * This method returns the workOn attribute.
     * @return The workOn attribute
     */

    public Card getWorkOn(){

        return this.workOn;
    }


    public String toString(){

        String ret = super.toString();

        if (!this.workingStatus) ret += "\t\tNe travaille pas.";
        else ret += "\t\t\tTravaille sur " + this.workOn.getName();
        
        ret += "\tProduction :\tBois : " + this.prodWood + "  Pierre : " + this.prodStone + "  Intelligence : " + this.prodIntell + "  Tuile : " + this.prodTile;

        return ret;
    }
}