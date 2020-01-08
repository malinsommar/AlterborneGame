package game;

import javax.swing.*;

public class TutorialController {

    TutorialView tv = new TutorialView();
    int pics = 1;

    ImageIcon bild1 = new ImageIcon("Bild 1");
    ImageIcon bild2 = new ImageIcon("Bild 2");
    ImageIcon bild3 = new ImageIcon("Bild 3");
    ImageIcon bild4 = new ImageIcon("Bild 4");
    ImageIcon bild5 = new ImageIcon("Bild 5");


    public void startTutorial(){

        System.out.println("tutorial");
        tv.tutorialFrame();

        tv.nextPic.addActionListener(e -> {nextPicture();});
    }

    public void nextPicture(){
        if (pics == 1){
            tv.tutorialJFrame.setContentPane(new JLabel(bild1));

        }
        else if (pics == 2){
            tv.tutorialJFrame.setContentPane(new JLabel(bild2));

        }
        else if (pics == 3){
            tv.tutorialJFrame.setContentPane(new JLabel(bild3));

        }
        else if (pics == 4){
            tv.tutorialJFrame.setContentPane(new JLabel(bild4));

        }
        else if (pics == 5){
            tv.tutorialJFrame.setContentPane(new JLabel(bild5));

        }
        pics++;
    }
}
