package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Malin Sommar
 */
public class LevelUpView {

    JFrame levelFrame = new JFrame();

    Font pixelMplus;
    JLabel lvUp, whatLevel, warriorHp, warriorDamage, warriorBlock, rangerHp, rangerDamage, rangerBlock, mageHp, mageDamage, mageBlock, healerHp, healerDamage, healerBlock, warrior, healer, mage, ranger;
    JButton continueButton;

    /**
     *
     */
    public void levelUpFrame(){

        levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelFrame.setLayout(null);
        levelFrame.setBounds(400,138,500,400);

        importFont();
        importLabels();

        levelFrame.add(whatLevel);
        levelFrame.add(warriorHp);
        levelFrame.add(warriorDamage);
        levelFrame.add(warriorBlock);
        levelFrame.add(rangerHp);
        levelFrame.add(rangerDamage);
        levelFrame.add(rangerBlock);
        levelFrame.add(mageHp);
        levelFrame.add(mageDamage);
        levelFrame.add(mageBlock);
        levelFrame.add(healerHp);
        levelFrame.add(healerDamage);
        levelFrame.add(healerBlock);
        levelFrame.add(warrior);
        levelFrame.add(ranger);
        levelFrame.add(mage);
        levelFrame.add(healer);
        levelFrame.add(continueButton);

        levelFrame.setUndecorated(true);
        levelFrame.setVisible(true);
    }

    /**
     *
     */
    public void importLabels(){

        continueButton = new JButton("X");
        continueButton.setSize(50, 50);
        continueButton.setLocation(440, 10);
        continueButton.setForeground(Color.red);
        continueButton.setFont(pixelMplus.deriveFont(35f));
        continueButton.setBackground(Color.white);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);

        whatLevel.setForeground(Color.black);
        whatLevel.setFont((pixelMplus.deriveFont(35f)));
        Dimension whatLevelSize = whatLevel.getPreferredSize();
        whatLevel.setBounds(190, 20, whatLevelSize.width, whatLevelSize.height);

        warrior = new JLabel("Warrior");
        warrior.setForeground(Color.red);
        warrior.setFont((pixelMplus.deriveFont(35f)));
        Dimension warriorSize = warrior.getPreferredSize();
        warrior.setBounds(70, 70, warriorSize.width, warriorSize.height);

        warriorHp.setForeground(Color.red);
        warriorHp.setFont((pixelMplus.deriveFont(25f)));
        Dimension warriorHpSize = warriorHp.getPreferredSize();
        warriorHp.setBounds(40, 120, warriorHpSize.width, warriorHpSize.height);

        warriorDamage.setForeground(Color.red);
        warriorDamage.setFont((pixelMplus.deriveFont(25f)));
        Dimension warriorDamageSize = warriorDamage.getPreferredSize();
        warriorDamage.setBounds(40, 150, warriorDamageSize.width, warriorDamageSize.height);

        warriorBlock.setForeground(Color.red);
        warriorBlock.setFont((pixelMplus.deriveFont(25f)));
        Dimension warriorBlockSize = warriorBlock.getPreferredSize();
        warriorBlock.setBounds(40, 180, warriorBlockSize.width, warriorBlockSize.height);

        ranger = new JLabel("Ranger");
        ranger.setForeground(Color.green);
        ranger.setFont((pixelMplus.deriveFont(35f)));
        Dimension rangerSize = ranger.getPreferredSize();
        ranger.setBounds(70, 220, rangerSize.width, rangerSize.height);
        
        rangerHp.setForeground(Color.green);
        rangerHp.setFont((pixelMplus.deriveFont(25f)));
        Dimension rangerHpSize = rangerHp.getPreferredSize();
        rangerHp.setBounds(40, 270, rangerHpSize.width, rangerHpSize.height);

        rangerDamage.setForeground(Color.green);
        rangerDamage.setFont((pixelMplus.deriveFont(25f)));
        Dimension rangerDamageSize = rangerDamage.getPreferredSize();
        rangerDamage.setBounds(40, 300, rangerDamageSize.width, rangerDamageSize.height);

        rangerBlock.setForeground(Color.green);
        rangerBlock.setFont((pixelMplus.deriveFont(25f)));
        Dimension rangerBlockSize = rangerBlock.getPreferredSize();
        rangerBlock.setBounds(40, 330, rangerBlockSize.width, rangerBlockSize.height);

        mage = new JLabel("Mage");
        mage.setForeground(Color.blue);
        mage.setFont((pixelMplus.deriveFont(35f)));
        Dimension mageSize = mage.getPreferredSize();
        mage.setBounds(320, 70, mageSize.width, mageSize.height);
        
        mageHp.setForeground(Color.blue);
        mageHp.setFont((pixelMplus.deriveFont(25f)));
        Dimension mageHpSize = mageHp.getPreferredSize();
        mageHp.setBounds(260, 120, mageHpSize.width, mageHpSize.height);

        mageDamage.setForeground(Color.blue);
        mageDamage.setFont((pixelMplus.deriveFont(25f)));
        Dimension mageDamageSize = mageDamage.getPreferredSize();
        mageDamage.setBounds(260, 150, mageDamageSize.width, mageDamageSize.height);

        mageBlock.setForeground(Color.blue);
        mageBlock.setFont((pixelMplus.deriveFont(25f)));
        Dimension mageBlockSize = mageBlock.getPreferredSize();
        mageBlock.setBounds(260, 180, mageBlockSize.width, mageBlockSize.height);

        healer = new JLabel("Healer");
        healer.setForeground(Color.orange);
        healer.setFont((pixelMplus.deriveFont(35f)));
        Dimension healerSize = healer.getPreferredSize();
        healer.setBounds(300, 220, healerSize.width, healerSize.height);

        healerHp.setForeground(Color.orange);
        healerHp.setFont((pixelMplus.deriveFont(25f)));
        Dimension healerHpSize = healerHp.getPreferredSize();
        healerHp.setBounds(260, 270, healerHpSize.width, healerHpSize.height);

        healerDamage.setForeground(Color.orange);
        healerDamage.setFont((pixelMplus.deriveFont(25f)));
        Dimension healerDamageSize = healerDamage.getPreferredSize();
        healerDamage.setBounds(260, 300, healerDamageSize.width, healerDamageSize.height);

        healerBlock.setForeground(Color.orange);
        healerBlock.setFont((pixelMplus.deriveFont(25f)));
        Dimension healerBlockSize = healerBlock.getPreferredSize();
        healerBlock.setBounds(260, 330, healerBlockSize.width, healerBlockSize.height);
    }

    /**
     *
     */
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }

}
