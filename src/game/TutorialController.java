package game;

import fight.FightModel;

import javax.swing.*;

public class TutorialController {

    TutorialView tv = new TutorialView();
    int pics = 1;

    ImageIcon bild1 = new ImageIcon("turn.png");
    ImageIcon bild2 = new ImageIcon("hp.png");
    ImageIcon bild3 = new ImageIcon("attack.png");
    ImageIcon bild4 = new ImageIcon("block.png");
    ImageIcon bild5 = new ImageIcon("skill.png");
    ImageIcon bild6 = new ImageIcon("spells.png");
    ImageIcon bild7 = new ImageIcon("item.png");
    ImageIcon bild8 = new ImageIcon("potion.png");
    ImageIcon bild9 = new ImageIcon("endturn.png");

    public void startTutorial(){
        tv.tutorialFrame();

        tv.nextPic.addActionListener(e -> {nextPicture();});
    }

    public void nextPicture(){
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
            tv.text1.setLocation(470,20);
            tv.text1.setFont(tv.pixelMplus.deriveFont(18f));

            tv.text2.setText("To your left you can see your party's hp.");
            tv.text2.setLocation(430,55);
            tv.text2.setFont(tv.pixelMplus.deriveFont(17f));

            tv.text3.setText("And to your right you can see the enemies HP.");
            tv.text3.setLocation(430,90);
            tv.text3.setFont(tv.pixelMplus.deriveFont(17f));
        }
        else if (pics == 3){
            tv.picture.setIcon(bild3);

        }
        else if (pics == 4){
            tv.picture.setIcon(bild4);

        }
        else if (pics == 5){
            tv.picture.setIcon(bild5);

        }
        else if (pics == 6){
            tv.picture.setIcon(bild6);

        }
        else if (pics == 7){
            tv.picture.setIcon(bild7);

        }
        else if (pics == 8){
            tv.picture.setIcon(bild8);

        }
        else if (pics == 9){
            tv.picture.setIcon(bild9);

        }
        pics++;
    }
}
