package control;

import view.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;


/**
 * This class is the Controler part of the projet.
 * It implements the ActionListener interface, to listen to ActionEvent, when the buttons are clicked on.
 * @author Titouan Le Berre
 */

public class MenuListener implements ActionListener {

    //The window to which the controler is linked
    private GraphicalDisplay mainWindow;

    /**
     * The class' constructor
     * @param gd The window to which the current object will be linked
     */

    public MenuListener(GraphicalDisplay gd){

        if (gd != null) this.mainWindow = gd;

    }

    public void actionPerformed(ActionEvent e){

        //We get the window's main panel to determine its class
        JPanel mainP = this.mainWindow.getLAffichage();
        
        if (mainP.getClass() == WelcomeScreen.class){

            WelcomeScreen panel = (WelcomeScreen)mainP;

            if (e.getSource() == panel.getJoueur1()){

                this.mainWindow.computerChoice();
                //Implementer prise info 1 joueur
            }
    
            else if (e.getSource() == panel.getJoueur2()){
    
                this.mainWindow.computerChoice();
                //Implementer prise info 2 joueurs
            }
    
            else if (e.getSource() == panel.getJoueur3()){
    
                this.mainWindow.computerChoice();
                //Implementer prise info 3 joueurs
            }
    
            else if (e.getSource() == panel.getJoueur4()){
    
                this.mainWindow.computerChoice();
                //Implementer prise info 4 joueurs
            }
    
            else if (e.getSource() == panel.getRulesButton()){
    
                this.mainWindow.rules();
            }
    
            else if (e.getSource() == panel.getLoadButton()){
    
                //Implementer déclenchement chargement
            }

        }

        else if (mainP.getClass() == RulesDisplay.class){

            RulesDisplay panel = (RulesDisplay)mainP;

            if (e.getSource() == panel.getBackButton()){

                this.mainWindow.welcomeScreen();
                //Implementer prise info 1 joueur
            }


            else if (e.getSource() == panel.getRightButton()){

                JPanel lImage = panel.getImageCont();
                CardLayout c1 = (CardLayout)lImage.getLayout();

                c1.next(lImage);

            }

            else if (e.getSource() == panel.getLeftButton()){

                JPanel lImage = panel.getImageCont();
                CardLayout c1 = (CardLayout)lImage.getLayout();

                c1.previous(lImage);

            }
        }
        

    }
    
}