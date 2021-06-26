package view;

import control.MenuListener;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class RulesDisplay extends JPanel {

    private JButton leftButton;
    private JButton rightButton;
    private JButton backButton;
    private JPanel imageCont;

    /**
     * The class' constructor.
     * @param list The listener that will listen for actionsEvent on the JPanel's buttons.
     */
    public RulesDisplay(MenuListener list){

        try{

            this.imageCont = new JPanel(new CardLayout());

            //Images setting (the 6 pages of rules)
            BufferedImage page = ImageIO.read(new File("../data/images/rules1.png"));
            JLabel rulePage = new JLabel(new ImageIcon(page));
            this.imageCont.add(rulePage, "PAGE1");

            BufferedImage page2 = ImageIO.read(new File("../data/images/rules2.png"));
            JLabel rulePage2 = new JLabel(new ImageIcon(page2));
            this.imageCont.add(rulePage2, "PAGE2");

            BufferedImage page3 = ImageIO.read(new File("../data/images/rules3.png"));
            JLabel rulePage3 = new JLabel(new ImageIcon(page3));
            this.imageCont.add(rulePage3, "PAGE3");

            BufferedImage page4 = ImageIO.read(new File("../data/images/rules4.png"));
            JLabel rulePage4 = new JLabel(new ImageIcon(page4));
            this.imageCont.add(rulePage4, "PAGE4");

            BufferedImage page5 = ImageIO.read(new File("../data/images/rules5.png"));
            JLabel rulePage5 = new JLabel(new ImageIcon(page5));
            this.imageCont.add(rulePage5, "PAGE5");

            BufferedImage page6 = ImageIO.read(new File("../data/images/rules6.png"));
            JLabel rulePage6 = new JLabel(new ImageIcon(page6));
            this.imageCont.add(rulePage6, "PAGE6");

            this.leftButton = new JButton(new ImageIcon("../data/images/boutonG.png"));
            this.leftButton.addActionListener(list);

            this.rightButton = new JButton(new ImageIcon("../data/images/boutonD.png"));
            this.rightButton.addActionListener(list);

            this.backButton = new JButton(new ImageIcon("../data/images/boutonRetour.png"));
            this.backButton.addActionListener(list);

            JLabel title = new JLabel("REGLES");
            title.setBounds(600, 5, 100, 100);
            
            this.setLayout(null);

            this.add(imageCont);
            imageCont.setBounds(500,50, 932, 927);

            this.add(this.leftButton);
            this.leftButton.setBounds(130, 500, 123, 177);

            this.add(this.rightButton);
            this.rightButton.setBounds(1700, 500, 123, 177);

            this.add(this.backButton);
            this.backButton.setBounds(90,800,123,177);

            this.leftButton.setBorderPainted(false); 
            this.leftButton.setContentAreaFilled(false); 
            leftButton.setFocusPainted(false); 
            this.leftButton.setOpaque(false);

            this.rightButton.setBorderPainted(false); 
            this.rightButton.setContentAreaFilled(false); 
            rightButton.setFocusPainted(false); 
            this.rightButton.setOpaque(false);

            this.backButton.setBorderPainted(false); 
            this.backButton.setContentAreaFilled(false); 
            backButton.setFocusPainted(false); 
            this.backButton.setOpaque(false);


            /*
            //--- La contrainte qui gère la disposition du layout
            GridBagConstraints gc = new GridBagConstraints();

            //Le composant ajouté prend toute la place disponible
            gc.fill = GridBagConstraints.BOTH;

            //Définit la marge entre les composants
            gc.insets = new Insets(0, 50, 0, 10);

            //Definit où placer le composant s'il n'occuppe pas toute la place disponible
            gc.ipady = gc.anchor = GridBagConstraints.CENTER;


            //Définit le nombre d'emplacements en abscisse
            gc.weightx = 3;

            //Définit le nombre d'emplacements en ordonnée
            gc.weighty = 2;

            //We add the components to the panel
            gc.gridx = 1;
            gc.gridy = 0;
            this.add(leTitre,gc);

            gc.gridx = 1;
            gc.gridy = 1;
            this.add(rulePage,gc);

            gc.gridx = 2;
            gc.gridy = 1;
            this.add(rightButton,gc);

            gc.gridx = 0;
            gc.gridy = 1;
            this.add(leftButton,gc); */

            this.repaint();

        } catch (IOException e){

            System.out.println("RulesDisplay() - Error : " + e.getMessage());
        }
    }
    
    /**
     * This method set the background image .
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage buffImage = ImageIO.read(new File("../data/images/wooden_background.jpg"));
            g.drawImage(buffImage, 0,0,this.getWidth(),this.getHeight(),null);
        } catch (IOException e) {
            System.out.println("paintComponent() - Error : " + e.getMessage());
        }
    }

    public void repaint(){

        super.repaint();
    }

    public JButton getLeftButton(){

        return this.leftButton;
    }

    public JButton getRightButton(){

        return this.rightButton;
    }

    public JButton getBackButton(){

        return this.backButton;
    }

    public JPanel getImageCont(){

        return this.imageCont;
    }
}