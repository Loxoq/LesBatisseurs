package view;
import model.*;
import java.io.*;


public abstract class Display implements Serializable {

    //The model used by the view
    protected Batisseurs batiss;

    public Display(Batisseurs lesBat){

        if (lesBat != null) this.batiss = lesBat;

        else throw new IllegalArgumentException("Display(lesBat) - Error : Parameter is null.");
    }

}