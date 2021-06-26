package view;

import control.MenuListener;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/**
 * This class instanciate the welcome screen of the game in its graphical version
 * @author T. Le Berre
 */
public class WelcomeScreen extends JPanel {

    private JButton joueur1;
    private JButton joueur2;
    private JButton joueur3;
    private JButton joueur4;
    private JButton loadButton;
    private JButton rulesButton;
    private JButton paramButton;

    /**
     * The class' constructor.
     */

    public WelcomeScreen(MenuListener leListener) {

        super(new GridBagLayout());

        try {
            
            //--- GridBagLayout configuration ---
            
            //La contrainte qui gère la disposition du layout
            GridBagConstraints gc = new GridBagConstraints();

            //Le composant ajouté prend toute la place disponible
            gc.fill = GridBagConstraints.BOTH;

            //Définit la marge entre les composants
            gc.insets = new Insets(5, 80, 10, 10);

            //Definit où placer le composant s'il n'occuppe pas toute la place disponible
            gc.ipady = gc.anchor = GridBagConstraints.CENTER;


            //Définit le nombre d'emplacements en abscisse
            gc.weightx = 3;

            //Définit le nombre d'emplacements en ordonnée
            gc.weighty = 5;

            //the screen title
            BufferedImage titre = ImageIO.read(new File("../data/images/logo_transp.png"));
            JLabel title = new JLabel(new ImageIcon(titre));

            //The 4 buttons to select the number of total players
            this.joueur1 = new JButton(new ImageIcon("../data/images/joueur1.png"));
            this.joueur1.addActionListener(leListener);
            this.joueur2 = new JButton(new ImageIcon("../data/images/joueur2.png"));
            this.joueur2.addActionListener(leListener);
            this.joueur3 = new JButton(new ImageIcon("../data/images/joueur3.png"));
            this.joueur3.addActionListener(leListener);
            this.joueur4 = new JButton(new ImageIcon("../data/images/joueur4.png"));
            this.joueur4.addActionListener(leListener);

            //The button to load a save
            this.loadButton = new JButton(new ImageIcon("../data/images/load.png"));
            this.loadButton.addActionListener(leListener);

            //The button to print the rules on-screen
            this.rulesButton = new JButton(new ImageIcon("../data/images/rules.png"));
            this.rulesButton.addActionListener(leListener);

            //The button to set the game's parameter
            this.paramButton = new JButton(new ImageIcon("../data/images/options.png"));
            this.paramButton.addActionListener(leListener);

            this.joueur1.setBorderPainted(false); 
            this.joueur1.setContentAreaFilled(false); 
            //this.joueur1.setFocusPainted(false); 
            this.joueur1.setOpaque(false);

            this.joueur2.setBorderPainted(false); 
            this.joueur2.setContentAreaFilled(false); 
            //this.joueur2.setFocusPainted(false); 
            this.joueur2.setOpaque(false);

            this.joueur3.setBorderPainted(false); 
            this.joueur3.setContentAreaFilled(false); 
            //this.joueur3.setFocusPainted(false); 
            this.joueur3.setOpaque(false);

            this.joueur4.setBorderPainted(false); 
            this.joueur4.setContentAreaFilled(false); 
            this.joueur4.setFocusPainted(false); 
            this.joueur4.setOpaque(false);

            this.loadButton.setBorderPainted(false); 
            this.loadButton.setContentAreaFilled(false); 
            this.loadButton.setFocusPainted(false); 
            this.loadButton.setOpaque(false);

            this.rulesButton.setBorderPainted(false); 
            this.rulesButton.setContentAreaFilled(false); 
            this.rulesButton.setFocusPainted(false); 
            this.rulesButton.setOpaque(false);

            this.paramButton.setBorderPainted(false); 
            this.paramButton.setContentAreaFilled(false); 
            this.paramButton.setFocusPainted(false); 
            this.paramButton.setOpaque(false);


            //We add the components to the panel
            gc.gridx = 1;
            gc.gridy = 0;
            this.add(title,gc);

            gc.gridx = 1;
            gc.gridy = 1;
            this.add(this.joueur1,gc);

            gc.gridx = 1;
            gc.gridy = 2;
            this.add(this.joueur2,gc);

            gc.gridx = 1;
            gc.gridy = 3;
            this.add(this.joueur3,gc);

            gc.gridx = 1;
            gc.gridy = 4;
            this.add(this.joueur4,gc);

            gc.gridx = 0;
            gc.gridy = 4;
            this.add(this.rulesButton,gc);

            gc.gridx = 0;
            gc.gridy = 2;
            this.add(this.loadButton,gc);

            gc.gridx = 3;
            gc.gridy = 4;
            this.add(this.paramButton,gc);

            
            //Call the method that set the background image
            this.repaint();


        } catch (IOException e){

            System.out.println("WelcomeScreen() - Error : " + e.getMessage());
        }
        
    }

    /**
     * This method set the background image .
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage buffImage = ImageIO.read(new File("../data/images/builders_wallpaper.jpg"));
            g.drawImage(buffImage, 0,0,this.getWidth(),this.getHeight(),null);
        } catch (IOException e) {
            System.out.println("paintComponent() - Error : " + e.getMessage());
        }
    }

    public void repaint(){

        super.repaint();
    }

    /**
     * This method return the "1 Joueur" JButton.
     * @return joueur1 attribute
     */
    public JButton getJoueur1(){

        return this.joueur1;
    }

    /**
     * This method return the "2 Joueurs" JButton.
     * @return joueur2 attribute
     */
    public JButton getJoueur2(){

        return this.joueur2;
    }

    /**
     * This method return the "3 Joueurs" JButton.
     * @return joueur3 attribute
     */
    public JButton getJoueur3(){

        return this.joueur3;
    }

    /**
     * This method return the "4 Joueurs" JButton.
     * @return joueur4 attribute
     */
    public JButton getJoueur4(){

        return this.joueur4;
    }

    /**
     * This method returns the rules button.
     * @return rulesButton attribute
     */
    public JButton getRulesButton(){

        return this.rulesButton;
    }

    /**
     * This method returns the options button.
     * @return paramButton attribute
     */
    public JButton getParamButton(){

        return this.paramButton;
    }

    /**
     * This method returns the "Reprendre" button.
     * @return loadButton attribute
     */
    public JButton getLoadButton(){

        return this.loadButton;
    }

}