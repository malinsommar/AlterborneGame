package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ShopView {

    public Font pixelMplus;
    JButton back, potion1, potion2, potion3, potion4, potion5, potion6, potion7, potion8, potion9, potion10, potion11, potion12;
    JButton healerWeapon1, healerWeapon2, healerWeapon3, warriorWeapon1, warriorWeapon2, warriorWeapon3, rangerWeapon1, rangerWeapon2, rangerWeapon3, mageWeapon1, mageWeapon2, mageWeapon3;
    JButton healerArmor1, healerArmor2, healerArmor3, warriorArmor1, warriorArmor2, warriorArmor3, rangerArmor1, rangerArmor2, rangerArmor3, mageArmor1, mageArmor2, mageArmor3;
    JLabel currentAction, goldLabel;
    private JLabel health, block, energy, strength, warrior, healer, ranger, mage;


    //Import weapon pics
    private ImageIcon warriorRareWeaponPic = new ImageIcon("warriorRareWeapon.png");
    private ImageIcon warriorEpicWeaponPic = new ImageIcon("warriorEpicWeapon.png");
    private ImageIcon warriorLegendaryWeaponPic = new ImageIcon("warriorLegendaryWeapon.png");

    private ImageIcon healerRareWeaponPic = new ImageIcon("healerRareWeapon.png");
    private ImageIcon healerEpicWeaponPic = new ImageIcon("healerEpicWeapon.png");
    private ImageIcon healerLegendaryWeaponPic = new ImageIcon("healerLegendaryWeapon.png");

    private ImageIcon mageRareWeaponPic = new ImageIcon("mageRareWeapon.png");
    private ImageIcon mageEpicWeaponPic = new ImageIcon("mageEpicWeapon.png");
    private ImageIcon mageLegendaryWeaponPic = new ImageIcon("mageLegendaryWeapon.png");

    private ImageIcon rangerRareWeaponPic = new ImageIcon("rangerRareWeapon.png");
    private ImageIcon rangerEpicWeaponPic = new ImageIcon("rangerEpicWeapon.png");
    private ImageIcon rangerLegendaryWeaponPic = new ImageIcon("rangerLegendaryWeapon.png");

    //Import weapon pics
    private ImageIcon warriorRareArmorPic = new ImageIcon("warriorRareArmor.gif");
    private ImageIcon warriorEpicArmorPic = new ImageIcon("warriorEpicArmor.gif");
    private ImageIcon warriorLegendaryArmorPic = new ImageIcon("warriorLegendaryArmor.gif");

    private ImageIcon healerRareArmorPic = new ImageIcon("healerRareArmor.gif");
    private ImageIcon healerEpicArmorPic = new ImageIcon("healerEpicArmor.gif");
    private ImageIcon healerLegendaryArmorPic = new ImageIcon("healerLegendaryArmor.gif");

    private ImageIcon mageRareArmorPic = new ImageIcon("mageRareArmor.gif");
    private ImageIcon mageEpicArmorPic = new ImageIcon("mageEpicArmor.gif");
    private ImageIcon mageLegendaryArmorPic = new ImageIcon("mageLegendaryArmor.gif");

    private ImageIcon rangerRareArmorPic = new ImageIcon("rangerRareArmor.gif");
    private ImageIcon rangerEpicArmorPic = new ImageIcon("rangerEpicArmor.gif");
    private ImageIcon rangerLegendaryArmorPic = new ImageIcon("rangerLegendaryArmor.gif");

    //Import potion gifs
    private ImageIcon minorHealthGif = new ImageIcon("minorHealth.gif");
    private ImageIcon lesserHealthGif = new ImageIcon("lesserHealth.gif");
    private ImageIcon majorHealthGif = new ImageIcon("majorHealth.gif");

    private ImageIcon minorBlockGif = new ImageIcon("minorBlock.gif");
    private ImageIcon lesserBlockGif = new ImageIcon("lesserBlock.gif");
    private ImageIcon majorBlockGif = new ImageIcon("majorBlock.gif");

    private ImageIcon minorEnergyGif = new ImageIcon("minorEnergy.gif");
    private ImageIcon lesserEnergyGif = new ImageIcon("lesserEnergy.gif");
    private ImageIcon majorEnergyGif = new ImageIcon("majorEnergy.gif");

    private ImageIcon minorStrGif = new ImageIcon("minorStrength.gif");
    private ImageIcon lesserStrGif = new ImageIcon("lesserStrength.gif");
    private ImageIcon majorStrGif = new ImageIcon("majorStrength.gif");

    JFrame shopFrame = new JFrame();

    public void startShopFrame(){
        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setLayout(null);
        shopFrame.setSize(1920, 1080);
        MusicPick.musicStart("shop", "music");

        importFont();
        addButtons();
        addLabels();

        ImageIcon background = new ImageIcon("white.jpg");
        shopFrame.setContentPane(new JLabel(background));

        shopFrame.add(back);
        shopFrame.add(currentAction);
        shopFrame.add(goldLabel);
        shopFrame.add(health);
        shopFrame.add(block);
        shopFrame.add(energy);
        shopFrame.add(strength);

        //Potion gifs
        shopFrame.add(potion1);
        shopFrame.add(potion2);
        shopFrame.add(potion3);
        shopFrame.add(potion4);
        shopFrame.add(potion5);
        shopFrame.add(potion6);
        shopFrame.add(potion7);
        shopFrame.add(potion8);
        shopFrame.add(potion9);
        shopFrame.add(potion10);
        shopFrame.add(potion11);
        shopFrame.add(potion12);

        //Armor/Weapon pics
        shopFrame.add(warriorArmor1);
        shopFrame.add(warriorArmor2);
        shopFrame.add(warriorArmor3);
        shopFrame.add(warriorWeapon1);
        shopFrame.add(warriorWeapon2);
        shopFrame.add(warriorWeapon3);
        shopFrame.add(rangerArmor1);
        shopFrame.add(rangerArmor2);
        shopFrame.add(rangerArmor3);
        shopFrame.add(rangerWeapon1);
        shopFrame.add(rangerWeapon2);
        shopFrame.add(rangerWeapon3);
        shopFrame.add(mageArmor1);
        shopFrame.add(mageArmor2);
        shopFrame.add(mageArmor3);
        shopFrame.add(mageWeapon1);
        shopFrame.add(mageWeapon2);
        shopFrame.add(mageWeapon3);
        shopFrame.add(healerArmor1);
        shopFrame.add(healerArmor2);
        shopFrame.add(healerArmor3);
        shopFrame.add(healerWeapon1);
        shopFrame.add(healerWeapon2);
        shopFrame.add(healerWeapon3);

        shopFrame.add(warrior);
        shopFrame.add(mage);
        shopFrame.add(healer);
        shopFrame.add(ranger);

        shopFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        shopFrame.setUndecorated(true);
        shopFrame.setVisible(true);
    }



    public void addLabels(){

        currentAction = new JLabel("Shopkeeper: Are you gonna buy something or what?             ");
        currentAction.setFont((pixelMplus.deriveFont(27f)));
        currentAction.setForeground(Color.black);
        Dimension currentActionSize = currentAction.getPreferredSize();
        currentAction.setBounds(40, 550, currentActionSize.width, currentActionSize.height);

        goldLabel = new JLabel("              ");
        goldLabel.setFont((pixelMplus.deriveFont(30f)));
        goldLabel.setForeground(Color.orange);
        Dimension goldLabelSize = goldLabel.getPreferredSize();
        goldLabel.setBounds(20, 20, goldLabelSize.width, goldLabelSize.height);

        health = new JLabel("Health");
        health.setFont((pixelMplus.deriveFont(30f)));
        health.setForeground(Color.black);
        Dimension healthSize = health.getPreferredSize();
        health.setBounds(955, 50, healthSize.width, healthSize.height);

        block = new JLabel("Block");
        block.setFont((pixelMplus.deriveFont(30f)));
        block.setForeground(Color.black);
        Dimension blockSize = block.getPreferredSize();
        block.setBounds(955, 200, blockSize.width, blockSize.height);

        energy = new JLabel("Energy");
        energy.setFont((pixelMplus.deriveFont(30f)));
        energy.setForeground(Color.black);
        Dimension energySize = health.getPreferredSize();
        energy.setBounds(955, 350, energySize.width, energySize.height);

        strength = new JLabel("Strength");
        strength.setFont((pixelMplus.deriveFont(30f)));
        strength.setForeground(Color.black);
        Dimension strengthSize = strength.getPreferredSize();
        strength.setBounds(950, 500, strengthSize.width, strengthSize.height);

        warrior = new JLabel("Warrior");
        warrior.setFont((pixelMplus.deriveFont(40f)));
        warrior.setForeground(Color.black);
        Dimension warriorSize = warrior.getPreferredSize();
        warrior.setBounds(200, 100, warriorSize.width, warriorSize.height);

        mage = new JLabel("Mage");
        mage.setFont((pixelMplus.deriveFont(40f)));
        mage.setForeground(Color.black);
        Dimension mageSize = mage.getPreferredSize();
        mage.setBounds(607, 100, mageSize.width, mageSize.height);

        ranger = new JLabel("Ranger");
        ranger.setFont((pixelMplus.deriveFont(40f)));
        ranger.setForeground(Color.black);
        Dimension rangerSize = ranger.getPreferredSize();
        ranger.setBounds(210, 320, rangerSize.width, rangerSize.height);

        healer = new JLabel("Healer");
        healer.setFont((pixelMplus.deriveFont(40f)));
        healer.setForeground(Color.black);
        Dimension healerSize = healer.getPreferredSize();
        healer.setBounds(585, 320, healerSize.width, healerSize.height);
    }

    private void addButtons(){

        back = new JButton("Exit shop");
        back.setBounds(20,600,300,100);
        back.setFont(pixelMplus.deriveFont(30f));
        back.setBackground(Color.lightGray);
        back.setForeground(Color.black);
        back.setBorder(null);
        back.setFocusPainted(false);

        //Minor Health Potion
        potion1 = new JButton(minorHealthGif);
        potion1.setBounds(900,100,41,62);
        potion1.setBackground(Color.white);
        potion1.setBorder(null);
        potion1.setFocusPainted(false);

        //Lesser Health Potion
        potion2 = new JButton(lesserHealthGif);
        potion2.setBounds(970,100,46,62);
        potion2.setBackground(Color.white);
        potion2.setBorder(null);
        potion2.setFocusPainted(false);

        //Major Health Potion
        potion3 = new JButton(majorHealthGif);
        potion3.setBounds(1050,100,55,64);
        potion3.setBackground(Color.white);
        potion3.setBorder(null);
        potion3.setFocusPainted(false);

        //Minor Block Potion
        potion4 = new JButton(minorBlockGif);
        potion4.setBounds(900,250,42,63);
        potion4.setBackground(Color.white);
        potion4.setBorder(null);
        potion4.setFocusPainted(false);

        //Lesser Block Potion
        potion5 = new JButton(lesserBlockGif);
        potion5.setBounds(970,250,47,63);
        potion5.setBackground(Color.white);
        potion5.setBorder(null);
        potion5.setFocusPainted(false);

        //Major Block Potion
        potion6 = new JButton(majorBlockGif);
        potion6.setBounds(1050,250,59,64);
        potion6.setBackground(Color.white);
        potion6.setBorder(null);
        potion6.setFocusPainted(false);

        //Minor Energy Potion
        potion7 = new JButton(minorEnergyGif);
        potion7.setBounds(900,400,41,63);
        potion7.setBackground(Color.white);
        potion7.setBorder(null);
        potion7.setFocusPainted(false);

        //Lesser Energy Potion
        potion8 = new JButton(lesserEnergyGif);
        potion8.setBounds(970,400,46,63);
        potion8.setBackground(Color.white);
        potion8.setBorder(null);
        potion8.setFocusPainted(false);

        //Major Energy Potion
        potion9 = new JButton(majorEnergyGif);
        potion9.setBounds(1050,400,59,64);
        potion9.setBackground(Color.white);
        potion9.setBorder(null);
        potion9.setFocusPainted(false);

        //Minor Strength Potion
        potion10 = new JButton(minorStrGif);
        potion10.setBounds(900,550,38,63);
        potion10.setBackground(Color.white);
        potion10.setBorder(null);
        potion10.setFocusPainted(false);

        //Lesser Energy Potion
        potion11 = new JButton(lesserStrGif);
        potion11.setBounds(970,550,42,63);
        potion11.setBackground(Color.white);
        potion11.setBorder(null);
        potion11.setFocusPainted(false);

        //Major Energy Potion
        potion12 = new JButton(majorStrGif);
        potion12.setBounds(1050,550,53,64);
        potion12.setBackground(Color.white);
        potion12.setBorder(null);
        potion12.setFocusPainted(false);

        //Warrior stuff
        warriorArmor1 = new JButton(warriorRareArmorPic);
        warriorArmor1.setBounds(175,155,53,64);
        warriorArmor1.setBackground(Color.white);
        warriorArmor1.setBorder(null);
        warriorArmor1.setFocusPainted(false);

        warriorArmor2 = new JButton(warriorEpicArmorPic);
        warriorArmor2.setBounds(235,155,53,64);
        warriorArmor2.setBackground(Color.white);
        warriorArmor2.setBorder(null);
        warriorArmor2.setFocusPainted(false);

        warriorArmor3 = new JButton(warriorLegendaryArmorPic);
        warriorArmor3.setBounds(295,155,53,64);
        warriorArmor3.setBackground(Color.white);
        warriorArmor3.setBorder(null);
        warriorArmor3.setFocusPainted(false);

        warriorWeapon1 = new JButton(warriorRareWeaponPic);
        warriorWeapon1.setBounds(175,225,53,64);
        warriorWeapon1.setBackground(Color.white);
        warriorWeapon1.setBorder(null);
        warriorWeapon1.setFocusPainted(false);

        warriorWeapon2 = new JButton(warriorEpicWeaponPic);
        warriorWeapon2.setBounds(235,225,53,64);
        warriorWeapon2.setBackground(Color.white);
        warriorWeapon2.setBorder(null);
        warriorWeapon2.setFocusPainted(false);

        warriorWeapon3 = new JButton(warriorLegendaryWeaponPic);
        warriorWeapon3.setBounds(295, 225,53,64);
        warriorWeapon3.setBackground(Color.white);
        warriorWeapon3.setBorder(null);
        warriorWeapon3.setFocusPainted(false);

        rangerArmor1 = new JButton(rangerRareArmorPic);
        rangerArmor1.setBounds(175, 375,53,64);
        rangerArmor1.setBackground(Color.white);
        rangerArmor1.setBorder(null);
        rangerArmor1.setFocusPainted(false);

        rangerArmor2 = new JButton(rangerEpicArmorPic);
        rangerArmor2.setBounds(235, 375,53,64);
        rangerArmor2.setBackground(Color.white);
        rangerArmor2.setBorder(null);
        rangerArmor2.setFocusPainted(false);

        rangerArmor3 = new JButton(rangerLegendaryArmorPic);
        rangerArmor3.setBounds(295, 375,53,64);
        rangerArmor3.setBackground(Color.white);
        rangerArmor3.setBorder(null);
        rangerArmor3.setFocusPainted(false);

        rangerWeapon1 = new JButton(rangerRareWeaponPic);
        rangerWeapon1.setBounds(175, 445,53,64);
        rangerWeapon1.setBackground(Color.white);
        rangerWeapon1.setBorder(null);
        rangerWeapon1.setFocusPainted(false);

        rangerWeapon2 = new JButton(rangerEpicWeaponPic);
        rangerWeapon2.setBounds(235, 445,53,64);
        rangerWeapon2.setBackground(Color.white);
        rangerWeapon2.setBorder(null);
        rangerWeapon2.setFocusPainted(false);

        rangerWeapon3 = new JButton(rangerLegendaryWeaponPic);
        rangerWeapon3.setBounds(295, 445,53,64);
        rangerWeapon3.setBackground(Color.white);
        rangerWeapon3.setBorder(null);
        rangerWeapon3.setFocusPainted(false);

        mageArmor1 = new JButton(mageRareArmorPic);
        mageArmor1.setBounds(555, 155,53,64);
        mageArmor1.setBackground(Color.white);
        mageArmor1.setBorder(null);
        mageArmor1.setFocusPainted(false);

        mageArmor2 = new JButton(mageEpicArmorPic);
        mageArmor2.setBounds(615, 155,53,64);
        mageArmor2.setBackground(Color.white);
        mageArmor2.setBorder(null);
        mageArmor2.setFocusPainted(false);

        mageArmor3 = new JButton(mageLegendaryArmorPic);
        mageArmor3.setBounds(675, 155,53,64);
        mageArmor3.setBackground(Color.white);
        mageArmor3.setBorder(null);
        mageArmor3.setFocusPainted(false);

        mageWeapon1 = new JButton(mageRareWeaponPic);
        mageWeapon1.setBounds(555, 225,53,64);
        mageWeapon1.setBackground(Color.white);
        mageWeapon1.setBorder(null);
        mageWeapon1.setFocusPainted(false);

        mageWeapon2 = new JButton(mageEpicWeaponPic);
        mageWeapon2.setBounds(615, 225,53,64);
        mageWeapon2.setBackground(Color.white);
        mageWeapon2.setBorder(null);
        mageWeapon2.setFocusPainted(false);

        mageWeapon3 = new JButton(mageLegendaryWeaponPic);
        mageWeapon3.setBounds(675, 225,53,64);
        mageWeapon3.setBackground(Color.white);
        mageWeapon3.setBorder(null);
        mageWeapon3.setFocusPainted(false);

        healerArmor1 = new JButton(healerRareArmorPic);
        healerArmor1.setBounds(555, 375,53,64);
        healerArmor1.setBackground(Color.white);
        healerArmor1.setBorder(null);
        healerArmor1.setFocusPainted(false);

        healerArmor2 = new JButton(healerEpicArmorPic);
        healerArmor2.setBounds(615, 375,53,64);
        healerArmor2.setBackground(Color.white);
        healerArmor2.setBorder(null);
        healerArmor2.setFocusPainted(false);

        healerArmor3 = new JButton(healerLegendaryArmorPic);
        healerArmor3.setBounds(675, 375,53,64);
        healerArmor3.setBackground(Color.white);
        healerArmor3.setBorder(null);
        healerArmor3.setFocusPainted(false);

        healerWeapon1 = new JButton(healerRareWeaponPic);
        healerWeapon1.setBounds(555, 445,53,64);
        healerWeapon1.setBackground(Color.white);
        healerWeapon1.setBorder(null);
        healerWeapon1.setFocusPainted(false);

        healerWeapon2 = new JButton(healerEpicWeaponPic);
        healerWeapon2.setBounds(615, 445,53,64);
        healerWeapon2.setBackground(Color.white);
        healerWeapon2.setBorder(null);
        healerWeapon2.setFocusPainted(false);

        healerWeapon3 = new JButton(healerLegendaryWeaponPic);
        healerWeapon3.setBounds(675, 445,53,64);
        healerWeapon3.setBackground(Color.white);
        healerWeapon3.setBorder(null);
        healerWeapon3.setFocusPainted(false);
    }

    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException e) {
        }
    }
}
