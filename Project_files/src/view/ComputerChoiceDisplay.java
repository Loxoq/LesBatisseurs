package view;

import control.MenuListener;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

/**
 * This class is the JPanel that manage the choice of playing with(out) an AI.
 * @author T. Le Berre
 */
public class ComputerChoiceDisplay extends JPanel {

    private JRadioButton yesButton;
    private JRadioButton noButton;
    private JPanel lesBoutons;

    private JButton rulesButton;
    private JButton paramButton;
    private JButton proceedButton;

    /**
     * The class' constructor.
     * @param list The listener that will listen for actionsEvent on the JPanel's buttons.
     */
    public ComputerChoiceDisplay(MenuListener list){

        super(new GridBagLayout());

        try {
            
            //the screen title
            BufferedImage titre = ImageIO.read(new File("../data/images/logo_transp.png"));
            JLabel title = new JLabel(new ImageIcon(titre));

            //The two choice button (Yes / No)

            this.yesButton = new JRadioButton("Oui");
            this.yesButton.setForeground(Color.WHITE);
            this.yesButton.setOpaque(false);

            this.noButton = new JRadioButton("Non");
            this.noButton.setForeground(Color.WHITE);
            this.noButton.setOpaque(false);


            //The two buttons are linked together
            ButtonGroup choiceButtons = new ButtonGroup();
            choiceButtons.add(yesButton);
            choiceButtons.add(noButton);

            //On met les boutons dans un panel
            this.lesBoutons = new JPanel();

            this.lesBoutons.add(yesButton);
            this.lesBoutons.add(noButton);

            //The button to print the rules on-screen
            this.rulesButton = new JButton(new ImageIcon("../data/images/rules.png"));
            this.rulesButton.addActionListener(list);

            //The Label that will contain the question
            BufferedImage quIm = ImageIO.read(new File("../data/images/ordiQuestion.png"));
            JLabel question = new JLabel(new ImageIcon(quIm));

            //The button to set the game's parameter
            this.paramButton = new JButton(new ImageIcon("../data/images/options.png"));
            this.paramButton.addActionListener(list);

            //The button to proceed once the choice is made
            this.proceedButton = new JButton(new ImageIcon("../data/images/boutonValider.png"));
            this.proceedButton.addActionListener(list);

            //A blank label

            JLabel blank = new JLabel("");


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
            gc.weightx = 4;

            //Définit le nombre d'emplacements en ordonnée
            gc.weighty = 4;


            //We add the components to the panel
            gc.gridx = 1;
            gc.gridy = 0;
            this.add(title,gc);

            gc.gridx = 1;
            gc.gridy = 1;
            this.add(question,gc);

            gc.gridx = 1;
            gc.gridy = 2;
            this.add(this.lesBoutons,gc);
 
            gc.gridx = 0;
            gc.gridy = 3;
            this.add(this.rulesButton,gc);

            gc.gridx = 2;
            gc.gridy = 3;
            this.add(this.paramButton,gc);

            gc.gridx = 2;
            gc.gridy = 2;
            this.add(this.proceedButton,gc);

            gc.gridx = 3;
            gc.gridy = 2;
            this.add(blank,gc);

            //The different components are set to a transparent state

            rulesButton.setBorderPainted(false); 
            rulesButton.setContentAreaFilled(false); 
            rulesButton.setFocusPainted(false); 
            rulesButton.setOpaque(false);

            paramButton.setBorderPainted(false); 
            paramButton.setContentAreaFilled(false); 
            paramButton.setFocusPainted(false); 
            paramButton.setOpaque(false);

            proceedButton.setBorderPainted(false); 
            proceedButton.setContentAreaFilled(false); 
            proceedButton.setFocusPainted(false); 
            proceedButton.setOpaque(false);

            this.lesBoutons.setOpaque(false);

            this.repaint();

        } catch (IOException e) {
            
            System.out.println("ComputerChoiceDisplay() - Error : " + e.getMessage());
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
     * This method returns the rules button.
     * @return a JButton
     */
    public JButton getRulesButton(){

        return this.rulesButton;
    }

    /**
     * This method returns the options button.
     * @return a JButton
     */
    public JButton getParamButton(){

        return this.paramButton;
    }

    /**
     * This method returns the "proceed" button.
     * @return a JButton
     */
    public JButton getProceedButton(){

        return this.proceedButton;
    }
}