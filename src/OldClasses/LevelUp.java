package OldClasses;

import OldClasses.party.Healer;
import OldClasses.party.Mage;
import OldClasses.party.Ranger;
import OldClasses.party.Warrior;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LevelUp {
/*
    private Mage m = new Mage();
    private Healer h = new Healer();
    private Warrior w = new Warrior();
    private Ranger r = new Ranger();

    Font pixelMplus;
    public int xp = 10;
    public int level = 1;


    //This method checks OldClasses.party xp and level OldClasses.party up. Use this method after every fight.
    public void levelSystem(){

        if (xp>15 && level == 1){
            level = 2;
            levelUpStats();
            levelUpFrame();
        }
        else if (xp>40 && level == 2){
            level = 3;
            levelUpStats();
            levelUpFrame();
        }
        else if (xp>80 && level == 3){
            level = 4;
            levelUpStats();
            levelUpFrame();
        }
        else if(xp>160 && level == 4){
            level = 5;
            levelUpStats();
            levelUpFrame();
        }
        else if(xp>300 && level == 5){
            level = 6;
            levelUpStats();
            levelUpFrame();
        }
        else if(xp>500 && level == 6){
            level = 7;
            levelUpStats();
            levelUpFrame();
        }
        else if(xp>1000 && level == 7){
            level = 8;
            levelUpStats();
            levelUpFrame();
        }
        else if(xp>2000 && level == 8){
            level = 9;
            levelUpStats();
            levelUpFrame();
        }
        else if(xp>4000 && level == 9){
            level = 10;
            levelUpStats();
            levelUpFrame();
        }
    }

    //When player level up, this method adds bonuses to stats.
    public void levelUpStats(){
        w.hp += 10;
        w.damage += 3;
        w.block += 3;

        r.hp += 5;
        r.damage += 5;
        r.block += 2;

        m.hp += 5;
        m.damage += 6;
        m.block += 1;

        h.hp += 6;
        h.damage += 3;
        h.block += 2;
    }

    public void levelUpFrame(){

        JFrame levelFrame = new JFrame();
        levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelFrame.setLayout(null);
        levelFrame.setBounds(500,400,400,138);

        importFont();

        //Labels
        JLabel lvUp = new JLabel("Level up!");
        lvUp.setForeground(Color.black);
        lvUp.setFont((pixelMplus.deriveFont(30f)));
        Dimension lvUpSize = lvUp.getPreferredSize();
        lvUp.setBounds(160, 50, lvUpSize.width, lvUpSize.height);

        JLabel whatLevel = new JLabel("Level: "+level);
        whatLevel.setForeground(Color.black);
        whatLevel.setFont((pixelMplus.deriveFont(30f)));
        Dimension whatLevelSize = whatLevel.getPreferredSize();
        whatLevel.setBounds(200, 80, whatLevelSize.width, whatLevelSize.height);

        //Button
        JButton continueButton = new JButton("Continue");
        continueButton.setSize(300, 100);
        continueButton.setLocation(100, 280);
        continueButton.setForeground(Color.white);
        continueButton.setFont(pixelMplus.deriveFont(30f));
        continueButton.setBackground(Color.gray);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);

        levelFrame.add(lvUp);
        levelFrame.add(whatLevel);

        levelFrame.setUndecorated(true);
        levelFrame.setVisible(true);
    }

    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
*/
}