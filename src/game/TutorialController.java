package game;

import javax.swing.*;

/**
 * @author Simon Bengtsson, Malin Sommar
 */
public class TutorialController {

    TutorialView tv = new TutorialView();
    int pics = 1;
    public boolean done = false;

    ImageIcon bild1 = new ImageIcon("turn.png");
    ImageIcon bild2 = new ImageIcon("hp.png");
    ImageIcon bild3 = new ImageIcon("attack.png");
    ImageIcon bild4 = new ImageIcon("block.png");
    ImageIcon bild5 = new ImageIcon("spells.png");
    ImageIcon bild6 = new ImageIcon("item.png");
    ImageIcon bild7 = new ImageIcon("potion.png");
    ImageIcon bild8 = new ImageIcon("endturn.png");
    ImageIcon bild9 = new ImageIcon("fight.png");

    /**
     *
     */
    public void startTutorial(){
        tv.tutorialFrame();

        tv.nextPic.addActionListener(e -> {
            try {
                nextPicture();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     *
     * @throws InterruptedException
     */
    public void nextPicture() throws InterruptedException {
        System.out.println("test");

        if (pics == 1){
            tv.picture.setIcon(bild1);

            tv.text1.setText("This show you who's turn it is and their stats.");
            tv.text1.setLocation(430,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(18f));

            tv.text2.setText("You need energy to fight and gain 3 energy each turn.");
            tv.text2.setLocation(430,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(16f));

            tv.text3.setText("You can save energy for powerful skills, max 10.");
            tv.text3.setLocation(430,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(17f));

        }
        else if (pics == 2){
            tv.picture.setIcon(bild2);

            tv.text1.setText("Here you can see everyone's HP.");
            tv.text1.setLocation(485,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(18f));

            tv.text2.setText("To your left you can see your party's hp.");
            tv.text2.setLocation(460,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(17f));

            tv.text3.setText("And to your right you can see the enemies HP.");
            tv.text3.setLocation(455,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(17f));
        }
        else if (pics == 3){
            tv.picture.setIcon(bild3);
            tv.text1.setText("The menu in the lower right are fight options.");
            tv.text1.setLocation(430,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(18f));

            tv.text2.setText("Most attacks require a target. To target an enemy, simply click it.");
            tv.text2.setLocation(425,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(13f));

            tv.text3.setText("The Attack option always cost 2 energy and deal damage to 1 target.");
            tv.text3.setLocation(425,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(13f));
        }
        else if (pics == 4){
            tv.picture.setIcon(bild4);
            tv.text1.setText("Skill option takes you to the skill menu.");
            tv.text1.setLocation(445,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(20f));

            tv.text2.setText("Skills are character specific actions.");
            tv.text2.setLocation(455,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(20f));

            tv.text3.setText("Most skills deal damage. some deal damage to multiple enemies.");
            tv.text3.setLocation(435,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(13f));

        }
        else if (pics == 5){
            tv.picture.setIcon(bild5);
            tv.text1.setText("The energy cost of the skill is displayed next to it.");
            tv.text1.setLocation(430,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(15f));

            tv.text2.setText("Skills are more powerful than the normal attack, but cost more.");
            tv.text2.setLocation(433,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(13f));

            tv.text3.setText("For example: the warriors slam skill hits all enemies.");
            tv.text3.setLocation(430,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(15f));
        }
        else if (pics == 6){
            tv.picture.setIcon(bild6);
            tv.text1.setText("Item button takes you to the potion menu.");
            tv.text1.setLocation(435,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(20f));

            tv.text2.setText("Potions costs no energy to drink but disappear when used.");
            tv.text2.setLocation(445,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(13f));

            tv.text3.setText("You can exchange coins for more potions at the shop.");
            tv.text3.setLocation(435,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(15f));

        }
        else if (pics == 7){
            tv.picture.setIcon(bild7);
            tv.text1.setText("Health potion restores health. Block potion increases block.");
            tv.text1.setLocation(430,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(14f));

            tv.text2.setText("Energy potion grants energy.");
            tv.text2.setLocation(485,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(20f));

            tv.text3.setText("The strength potion makes your attack stronger.");
            tv.text3.setLocation(450,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(15f));
        }
        else if (pics == 8){
            tv.picture.setIcon(bild8);
            tv.text1.setText("Once you've run out of energy hit the end turn button.");
            tv.text1.setLocation(435,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(14f));

            tv.text2.setText("This switches to your next character.");
            tv.text2.setLocation(450,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(20f));

            tv.text3.setText("After all your characters are done, it's the enemies turn.");
            tv.text3.setLocation(430,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(14f));

        }
        else if (pics == 9){
            tv.picture.setIcon(bild9);
            tv.text1.setText("                  ");

            tv.text2.setText("Now you are ready for play mode.");
            tv.text2.setLocation(435,20);
            tv.text2.setFont(tv.pixelMplus.deriveFont(26f));

            tv.text3.setText("                  ");
        }
        else if (pics == 10){
            done = true;
            pics = 1;
            tv.tutorialJFrame.dispose();
        }
        pics++;
    }
}
