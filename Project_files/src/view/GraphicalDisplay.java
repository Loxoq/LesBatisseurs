package view;

import model.*;
import control.MenuListener;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/**
 * This class is the main frame of the graphical displaY.
 * @author T. Le Berre
 */
public class GraphicalDisplay extends JFrame {

    //The Panel that will contain all the components
    private JPanel lAffichage;

    //The Listener
    private MenuListener listener;

    /**
     * The class' constructor.
     */
    public GraphicalDisplay(){

        this.listener = new MenuListener(this);
        this.setTitle("Les Bâtisseurs - Moyen Âge");
        //this.welcomeScreen();
        //this.rules();
        this.computerChoice();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * This method display the welcome screen, by creating a new WelcomeScreen object.
     */
    public void welcomeScreen(){

        this.lAffichage = new WelcomeScreen(this.listener);
        this.add(this.lAffichage);

        this.revalidate();
        this.pack();
        this.repaint();
    }

    /**
     * This method display the rules, by creating a new RulesDisplay object.
     */
    public void rules(){


        this.lAffichage = new RulesDisplay(this.listener);
        this.add(this.lAffichage);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);

        this.revalidate();
        this.repaint();

    }

    /**
     * This method display the choice of playing with a computer or not, by creating a new ComputerChoiceDisplay object.
     */
    public void computerChoice(){

        this.lAffichage = new ComputerChoiceDisplay(this.listener);
        this.add(this.lAffichage);
        
        this.revalidate();
        this.pack();
        this.repaint();
        
    }

    /**
     * This method return the main JPanel
     * @return lAffichage attribute
     */

    public JPanel getLAffichage(){

        return this.lAffichage;
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(
            new Runnable() {
            public void run() {
                new GraphicalDisplay().setVisible(true);
            }
        });
    }

}