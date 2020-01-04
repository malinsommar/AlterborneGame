package OldClasses;

import OldClasses.Hub;
import game.Inventory;
import game.MusicPick;
import imports.ArmorWeapon;
import imports.Potions;
import party.Healer;
import party.Mage;
import party.Ranger;
import party.Warrior;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Shop extends JFrame {

    private Inventory inv = new Inventory();
    private Warrior w = new Warrior();
    private Healer h = new Healer();
    private Ranger r = new Ranger();
    private Mage m = new Mage();
    private Potions potions = new Potions();
    private ArmorWeapon aw = new ArmorWeapon();

    private Font pixelMplus;
    private JButton back, potion1, potion2, potion3, potion4, potion5, potion6, potion7, potion8, potion9, potion10, potion11, potion12;
    private JButton healerWeapon1, healerWeapon2, healerWeapon3, warriorWeapon1, warriorWeapon2, warriorWeapon3, rangerWeapon1, rangerWeapon2, rangerWeapon3, mageWeapon1, mageWeapon2, mageWeapon3;
    private JButton healerArmor1, healerArmor2, healerArmor3, warriorArmor1, warriorArmor2, warriorArmor3, rangerArmor1, rangerArmor2, rangerArmor3, mageArmor1, mageArmor2, mageArmor3;
    private JLabel currentAction, goldLabel, health, block, energy, strength, warrior, healer, ranger, mage;

    private int currentGold=inv.gold;

    public Shop(){
        super("Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1920, 1080);
        MusicPick.musicStart("shop", "music");

        importFont();
        addButtons();
        addLabels();
        hoverEffect();

        ImageIcon background = new ImageIcon("white.jpg");
        setContentPane(new JLabel(background));

        add(back);
        add(currentAction);
        add(goldLabel);
        add(health);
        add(block);
        add(energy);
        add(strength);
        //Potion gifs
        add(potion1);
        add(potion2);
        add(potion3);
        add(potion4);
        add(potion5);
        add(potion6);
        add(potion7);
        add(potion8);
        add(potion9);
        add(potion10);
        add(potion11);
        add(potion12);
        //Warrior armors
        add(warriorArmor1);
        add(warriorArmor2);
        add(warriorArmor3);
        //Warrior Weapons
        add(warriorWeapon1);
        add(warriorWeapon2);
        add(warriorWeapon3);
        //Ranger Armors
        add(rangerArmor1);
        add(rangerArmor2);
        add(rangerArmor3);
        //Ranger Weapons
        add(rangerWeapon1);
        add(rangerWeapon2);
        add(rangerWeapon3);
        //Mage Armors
        add(mageArmor1);
        add(mageArmor2);
        add(mageArmor3);
        //Mage weapons
        add(mageWeapon1);
        add(mageWeapon2);
        add(mageWeapon3);
        //Healers Armor
        add(healerArmor1);
        add(healerArmor2);
        add(healerArmor3);
        //Healers Weapons
        add(healerWeapon1);
        add(healerWeapon2);
        add(healerWeapon3);

        add(warrior);
        add(mage);
        add(healer);
        add(ranger);

        //ActionListeners
        back.addActionListener(e -> dispose());
        back.addActionListener(e -> new Hub());

        //When you press one of the potions -->buyPotions()
        potion1.addActionListener(e-> buyPotion(1));
        potion2.addActionListener(e-> buyPotion(2));
        potion3.addActionListener(e-> buyPotion(3));
        potion4.addActionListener(e-> buyPotion(4));
        potion5.addActionListener(e-> buyPotion(5));
        potion6.addActionListener(e-> buyPotion(6));
        potion7.addActionListener(e-> buyPotion(7));
        potion8.addActionListener(e-> buyPotion(8));
        potion9.addActionListener(e-> buyPotion(9));
        potion10.addActionListener(e-> buyPotion(10));
        potion11.addActionListener(e-> buyPotion(11));
        potion12.addActionListener(e-> buyPotion(12));

        //When you press one of the armors/weapons --> buyEquipment()
        warriorArmor1.addActionListener(e-> buyEquipment(1));
        warriorArmor2.addActionListener(e-> buyEquipment(2));
        warriorArmor3.addActionListener(e-> buyEquipment(3));
        warriorWeapon1.addActionListener(e-> buyEquipment(4));
        warriorWeapon2.addActionListener(e-> buyEquipment(5));
        warriorWeapon3.addActionListener(e-> buyEquipment(6));

        mageArmor1.addActionListener(e-> buyEquipment(7));
        mageArmor2.addActionListener(e-> buyEquipment(8));
        mageArmor3.addActionListener(e-> buyEquipment(9));
        mageWeapon1.addActionListener(e-> buyEquipment(10));
        mageWeapon2.addActionListener(e-> buyEquipment(11));
        mageWeapon3.addActionListener(e-> buyEquipment(12));

        rangerArmor1.addActionListener(e-> buyEquipment(13));
        rangerArmor2.addActionListener(e-> buyEquipment(14));
        rangerArmor3.addActionListener(e-> buyEquipment(15));
        rangerWeapon1.addActionListener(e-> buyEquipment(16));
        rangerWeapon2.addActionListener(e-> buyEquipment(17));
        rangerWeapon3.addActionListener(e-> buyEquipment(18));

        healerArmor1.addActionListener(e-> buyEquipment(19));
        healerArmor2.addActionListener(e-> buyEquipment(20));
        healerArmor3.addActionListener(e-> buyEquipment(21));
        healerWeapon1.addActionListener(e-> buyEquipment(22));
        healerWeapon2.addActionListener(e-> buyEquipment(23));
        healerWeapon3.addActionListener(e-> buyEquipment(24));

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    public void buyEquipment(int whatEquipment){

        //Warrior
        if (whatEquipment==1 && currentGold>49){
            w.warriorRareArmor();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Warrior equipped the *Shiny armor*.");
        }
        else if (whatEquipment==2 && currentGold>99){
            w.warriorEpicArmor();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Warrior equipped the *Hardened armor*.");
        }
        else if (whatEquipment==3 && currentGold>149){
            w.warriorLegendaryArmor();
            inv.gold-=150;
            currentGold-=150;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Warrior equipped the *Royal Enchanted Armor*.");
        }
        else if (whatEquipment==4 && currentGold>49){
            w.warriorRareWeapon();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Warrior equipped the *Iron sword*.");
        }
        else if (whatEquipment==5 && currentGold>99){
            w.warriorEpicWeapon();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Warrior equipped the *Tempered steel blade*.");
        }
        else if (whatEquipment==6 && currentGold>149){
            w.warriorEpicWeapon();
            inv.gold-=150;
            currentGold-=150;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Warrior equipped the *Sword of a thousand truths*.");
        }
        //Mage
        else if (whatEquipment==7 && currentGold>49){
            m.mageRareArmor();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Mage equipped the *Mooncloth robe*.");
        }
        else if (whatEquipment==8 && currentGold>99){
            m.mageEpicArmor();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Mage equipped the *Enchanted robe*.");
        }
        else if (whatEquipment==9 && currentGold>149){
            m.mageLegendaryArmor();
            inv.gold-=150;
            currentGold-=150;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Mage equipped the *Robe of the archmage*.");
        }
        else if (whatEquipment==10 && currentGold>49){
            m.mageRareWeapon();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Mage equipped the *Ivory fire wand*.");
        }
        else if (whatEquipment==11 && currentGold>99){
            m.mageEpicWeapon();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Mage equipped the *Enchanted mana wand*.");
        }
        else if (whatEquipment==12 && currentGold>149) {
            m.mageLegendaryWeapon();
            inv.gold -= 150;
            currentGold -= 150;
            goldLabel.setText("Gold: " + currentGold);
            currentAction.setText("Mage equipped the *Pyromaniac's tinderbox*.");
        }
        //Ranger
        else if (whatEquipment==13 && currentGold>49){
            r.rangerRareArmor();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: " + currentGold);
            currentAction.setText("Ranger equipped the *Fine leather armor*.");
        }
        else if (whatEquipment==14 && currentGold>99){
            r.rangerEpicArmor();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Ranger equipped the *Elven leather armor*.");
        }
        else if (whatEquipment==15 && currentGold>149){
            r.rangerRareArmor();
            inv.gold-=150;
            currentGold-=150;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Ranger equipped the *Demonskin armor*.");
        }
        else if (whatEquipment==16 && currentGold>49){
            r.rangerRareWeapon();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Ranger equipped the *Elven bow*.");
        }
        else if (whatEquipment==17 && currentGold>99){
            r.rangerEpicWeapon();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Ranger equipped the *Dragonslayer's bow*.");
        }
        else if (whatEquipment==18 && currentGold>149){
            r.rangerRareWeapon();
            inv.gold-=150;
            currentGold-=150;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Ranger equipped the *Bullseye bow*.");
        }
        //Healer
        else if (whatEquipment==19 && currentGold>49){
            h.healerRareArmor();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Healer equipped the *Priests robe*.");
        }
        else if (whatEquipment==20 && currentGold>99){
            h.healerEpicArmor();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Healer equipped the *Clerics armor*.");
        }
        else if (whatEquipment==21 && currentGold>149){
            h.healerLegendaryArmor();
            inv.gold-=150;
            currentGold-=150;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Healer equipped the *Plate armor of Parl'ont the crusader*.");
        }
        else if (whatEquipment==22 && currentGold>49){
            h.healerRareWeapon();
            inv.gold-=50;
            currentGold-=50;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Healer equipped the *Stick of truth*.");
        }
        else if (whatEquipment==23 && currentGold>99){
            h.healerEpicWeapon();
            inv.gold-=100;
            currentGold-=100;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Healer equipped the *Cleric's blessed walking stick*.");
        }
        else if (whatEquipment==24 && currentGold>149){
            h.healerLegendaryWeapon();
            inv.gold-=150;
            currentGold-=150;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("Healer equipped the *Root of the world tree*.");
        }

        else{
            currentAction.setText("Shopkeeper: You cant afford that!");
        }
    }

    public void buyPotion(int whatPotion){

        if (whatPotion==1 && currentGold>9){
            inv.addPotion(1);
            inv.gold-=10;
            currentGold-=10;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a minor health potion");
        }
        else if (whatPotion==2 && currentGold>19){
            inv.addPotion(2);
            inv.gold-=20;
            currentGold-=20;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a lesser health potion");
        }
        else if (whatPotion==3 && currentGold>29){
            inv.addPotion(3);
            inv.gold-=30;
            currentGold-=30;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a major health potion");
        }
        else if (whatPotion==4 && currentGold>9){
            inv.addPotion(4);
            inv.gold-=10;
            currentGold-=10;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a minor block potion");
        }
        else if (whatPotion==5 && currentGold>19){
            inv.addPotion(5);
            inv.gold-=20;
            currentGold-=20;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a lesser block potion");
        }
        else if (whatPotion==6 && currentGold>29){
            inv.addPotion(6);
            inv.gold-=30;
            currentGold-=30;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a major block potion");
        }
        else if (whatPotion==7 && currentGold>9){
            inv.addPotion(7);
            inv.gold-=10;
            currentGold-=10;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a minor energy potion");
        }
        else if (whatPotion==8 && currentGold>19){
            inv.addPotion(8);
            inv.gold-=20;
            currentGold-=20;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a lesser energy potion");
        }
        else if (whatPotion==9 && currentGold>29){
            inv.addPotion(9);
            inv.gold-=30;
            currentGold-=30;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a major energy potion");
        }
        else if (whatPotion==10 && currentGold>9){
            inv.addPotion(10);
            inv.gold-=10;
            currentGold-=10;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a minor strength potion");
        }
        else if (whatPotion==11 && currentGold>19){
            inv.addPotion(11);
            inv.gold-=20;
            currentGold-=20;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a lesser strength potion");
        }
        else if (whatPotion==12 && currentGold>29){
            inv.addPotion(12);
            inv.gold-=30;
            currentGold-=30;
            goldLabel.setText("Gold: "+currentGold);
            currentAction.setText("You bought a major strength potion");
        }
        else{
            currentAction.setText("Shopkeeper: You cant afford that!");
        }
    }

    public void addLabels(){

        currentAction = new JLabel("Shopkeeper: Are you gonna buy something or what?             ");
        currentAction.setFont((pixelMplus.deriveFont(27f)));
        currentAction.setForeground(Color.black);
        Dimension currentActionSize = currentAction.getPreferredSize();
        currentAction.setBounds(40, 550, currentActionSize.width, currentActionSize.height);

        goldLabel = new JLabel("Gold: "+currentGold);
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
        potion1 = new JButton(potions.minorHealthGif);
        potion1.setBounds(900,100,41,62);
        potion1.setBackground(Color.white);
        potion1.setBorder(null);
        potion1.setFocusPainted(false);

        //Lesser Health Potion
        potion2 = new JButton(potions.lesserHealthGif);
        potion2.setBounds(970,100,46,62);
        potion2.setBackground(Color.white);
        potion2.setBorder(null);
        potion2.setFocusPainted(false);

        //Major Health Potion
        potion3 = new JButton(potions.majorHealthGif);
        potion3.setBounds(1050,100,55,64);
        potion3.setBackground(Color.white);
        potion3.setBorder(null);
        potion3.setFocusPainted(false);

        //Minor Block Potion
        potion4 = new JButton(potions.minorBlockGif);
        potion4.setBounds(900,250,42,63);
        potion4.setBackground(Color.white);
        potion4.setBorder(null);
        potion4.setFocusPainted(false);

        //Lesser Block Potion
        potion5 = new JButton(potions.lesserBlockGif);
        potion5.setBounds(970,250,47,63);
        potion5.setBackground(Color.white);
        potion5.setBorder(null);
        potion5.setFocusPainted(false);

        //Major Block Potion
        potion6 = new JButton(potions.majorBlockGif);
        potion6.setBounds(1050,250,59,64);
        potion6.setBackground(Color.white);
        potion6.setBorder(null);
        potion6.setFocusPainted(false);

        //Minor Energy Potion
        potion7 = new JButton(potions.minorEnergyGif);
        potion7.setBounds(900,400,41,63);
        potion7.setBackground(Color.white);
        potion7.setBorder(null);
        potion7.setFocusPainted(false);

        //Lesser Energy Potion
        potion8 = new JButton(potions.lesserEnergyGif);
        potion8.setBounds(970,400,46,63);
        potion8.setBackground(Color.white);
        potion8.setBorder(null);
        potion8.setFocusPainted(false);

        //Major Energy Potion
        potion9 = new JButton(potions.majorEnergyGif);
        potion9.setBounds(1050,400,59,64);
        potion9.setBackground(Color.white);
        potion9.setBorder(null);
        potion9.setFocusPainted(false);

        //Minor Strength Potion
        potion10 = new JButton(potions.minorStrGif);
        potion10.setBounds(900,550,38,63);
        potion10.setBackground(Color.white);
        potion10.setBorder(null);
        potion10.setFocusPainted(false);

        //Lesser Energy Potion
        potion11 = new JButton(potions.lesserStrGif);
        potion11.setBounds(970,550,42,63);
        potion11.setBackground(Color.white);
        potion11.setBorder(null);
        potion11.setFocusPainted(false);

        //Major Energy Potion
        potion12 = new JButton(potions.majorStrGif);
        potion12.setBounds(1050,550,53,64);
        potion12.setBackground(Color.white);
        potion12.setBorder(null);
        potion12.setFocusPainted(false);

        //Warrior stuff
        warriorArmor1 = new JButton(aw.warriorRareArmorPic);
        warriorArmor1.setBounds(175,155,53,64);
        warriorArmor1.setBackground(Color.white);
        warriorArmor1.setBorder(null);
        warriorArmor1.setFocusPainted(false);

        warriorArmor2 = new JButton(aw.warriorEpicArmorPic);
        warriorArmor2.setBounds(235,155,53,64);
        warriorArmor2.setBackground(Color.white);
        warriorArmor2.setBorder(null);
        warriorArmor2.setFocusPainted(false);

        warriorArmor3 = new JButton(aw.warriorLegendaryArmorPic);
        warriorArmor3.setBounds(295,155,53,64);
        warriorArmor3.setBackground(Color.white);
        warriorArmor3.setBorder(null);
        warriorArmor3.setFocusPainted(false);

        warriorWeapon1 = new JButton(aw.warriorRareWeaponPic);
        warriorWeapon1.setBounds(175,225,53,64);
        warriorWeapon1.setBackground(Color.white);
        warriorWeapon1.setBorder(null);
        warriorWeapon1.setFocusPainted(false);

        warriorWeapon2 = new JButton(aw.warriorEpicWeaponPic);
        warriorWeapon2.setBounds(235,225,53,64);
        warriorWeapon2.setBackground(Color.white);
        warriorWeapon2.setBorder(null);
        warriorWeapon2.setFocusPainted(false);

        warriorWeapon3 = new JButton(aw.warriorLegendaryWeaponPic);
        warriorWeapon3.setBounds(295, 225,53,64);
        warriorWeapon3.setBackground(Color.white);
        warriorWeapon3.setBorder(null);
        warriorWeapon3.setFocusPainted(false);

        rangerArmor1 = new JButton(aw.rangerRareArmorPic);
        rangerArmor1.setBounds(175, 375,53,64);
        rangerArmor1.setBackground(Color.white);
        rangerArmor1.setBorder(null);
        rangerArmor1.setFocusPainted(false);

        rangerArmor2 = new JButton(aw.rangerEpicArmorPic);
        rangerArmor2.setBounds(235, 375,53,64);
        rangerArmor2.setBackground(Color.white);
        rangerArmor2.setBorder(null);
        rangerArmor2.setFocusPainted(false);

        rangerArmor3 = new JButton(aw.rangerLegendaryArmorPic);
        rangerArmor3.setBounds(295, 375,53,64);
        rangerArmor3.setBackground(Color.white);
        rangerArmor3.setBorder(null);
        rangerArmor3.setFocusPainted(false);

        rangerWeapon1 = new JButton(aw.rangerRareWeaponPic);
        rangerWeapon1.setBounds(175, 445,53,64);
        rangerWeapon1.setBackground(Color.white);
        rangerWeapon1.setBorder(null);
        rangerWeapon1.setFocusPainted(false);

        rangerWeapon2 = new JButton(aw.rangerEpicWeaponPic);
        rangerWeapon2.setBounds(235, 445,53,64);
        rangerWeapon2.setBackground(Color.white);
        rangerWeapon2.setBorder(null);
        rangerWeapon2.setFocusPainted(false);

        rangerWeapon3 = new JButton(aw.rangerLegendaryWeaponPic);
        rangerWeapon3.setBounds(295, 445,53,64);
        rangerWeapon3.setBackground(Color.white);
        rangerWeapon3.setBorder(null);
        rangerWeapon3.setFocusPainted(false);

        mageArmor1 = new JButton(aw.mageRareArmorPic);
        mageArmor1.setBounds(555, 155,53,64);
        mageArmor1.setBackground(Color.white);
        mageArmor1.setBorder(null);
        mageArmor1.setFocusPainted(false);

        mageArmor2 = new JButton(aw.mageEpicArmorPic);
        mageArmor2.setBounds(615, 155,53,64);
        mageArmor2.setBackground(Color.white);
        mageArmor2.setBorder(null);
        mageArmor2.setFocusPainted(false);

        mageArmor3 = new JButton(aw.mageLegendaryArmorPic);
        mageArmor3.setBounds(675, 155,53,64);
        mageArmor3.setBackground(Color.white);
        mageArmor3.setBorder(null);
        mageArmor3.setFocusPainted(false);

        mageWeapon1 = new JButton(aw.mageRareWeaponPic);
        mageWeapon1.setBounds(555, 225,53,64);
        mageWeapon1.setBackground(Color.white);
        mageWeapon1.setBorder(null);
        mageWeapon1.setFocusPainted(false);

        mageWeapon2 = new JButton(aw.mageEpicWeaponPic);
        mageWeapon2.setBounds(615, 225,53,64);
        mageWeapon2.setBackground(Color.white);
        mageWeapon2.setBorder(null);
        mageWeapon2.setFocusPainted(false);

        mageWeapon3 = new JButton(aw.mageLegendaryWeaponPic);
        mageWeapon3.setBounds(675, 225,53,64);
        mageWeapon3.setBackground(Color.white);
        mageWeapon3.setBorder(null);
        mageWeapon3.setFocusPainted(false);

        healerArmor1 = new JButton(aw.healerRareArmorPic);
        healerArmor1.setBounds(555, 375,53,64);
        healerArmor1.setBackground(Color.white);
        healerArmor1.setBorder(null);
        healerArmor1.setFocusPainted(false);

        healerArmor2 = new JButton(aw.healerEpicArmorPic);
        healerArmor2.setBounds(615, 375,53,64);
        healerArmor2.setBackground(Color.white);
        healerArmor2.setBorder(null);
        healerArmor2.setFocusPainted(false);

        healerArmor3 = new JButton(aw.healerLegendaryArmorPic);
        healerArmor3.setBounds(675, 375,53,64);
        healerArmor3.setBackground(Color.white);
        healerArmor3.setBorder(null);
        healerArmor3.setFocusPainted(false);

        healerWeapon1 = new JButton(aw.healerRareWeaponPic);
        healerWeapon1.setBounds(555, 445,53,64);
        healerWeapon1.setBackground(Color.white);
        healerWeapon1.setBorder(null);
        healerWeapon1.setFocusPainted(false);

        healerWeapon2 = new JButton(aw.healerEpicWeaponPic);
        healerWeapon2.setBounds(615, 445,53,64);
        healerWeapon2.setBackground(Color.white);
        healerWeapon2.setBorder(null);
        healerWeapon2.setFocusPainted(false);

        healerWeapon3 = new JButton(aw.healerLegendaryWeaponPic);
        healerWeapon3.setBounds(675, 445,53,64);
        healerWeapon3.setBackground(Color.white);
        healerWeapon3.setBorder(null);
        healerWeapon3.setFocusPainted(false);
    }

    /*Arraylist för alla buttons som loopar igenom och sätter in alla meninaar i en egen metod/länkar samman dem??
           JLabel allHoverItems[]
     */

    private void hoverEffect() {

        //Minor health potion
        potion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Minor health potion, heals 10hp. Cost: 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: You gonna buy something?");
            }
        });
        //Lesser health potion
        potion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Lesser health potion, heals 30hp. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: These are great prizes!");
            }
        });
        //Major health potion
        potion3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Major health potion, heals 60hp. Cost 30G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Take a look at my wares!");
            }
        });
        //Minor block potion
        potion4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Minor block potion, blocks grants 5 block. Cost 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: You gonna buy something?");
            }
        });
        //Lesser Block potion
        potion5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Lesser block potion, grants 20 block. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: ...?");
            }
        });
        //Major Block potion
        potion6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Major block potion, grants 50 block. Cost 30G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Come on! I haven't got all day.");
            }
        });
        //Minor energy potion
        potion7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Minor energy potion, grants 2 energy. Cost 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: These are the finest wares in town!");
            }
        });
        //Lesser energy potion
        potion8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Lesser energy potion, grants 5 energy. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Lets do some business!");
            }
        });
        //Major energy potion
        potion9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Major energy potion, grants 10 energy. Cost 30G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: It's getting late...");
            }
        });
        //Minor strength potion
        potion10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Minor strength potion, grants 5 strength. Cost 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Do you have any gold? ");
            }
        });
        //Lesser strength potion
        potion11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Lesser strength potion, grants 20 strength. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: I got an endless supply of potions!");
            }
        });
        //Major strength potion
        potion12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Major strength potion, grants 35 strength. Cost 30G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Buy something, you wont regret it!");
            }
        });
        //Warrior's things
        warriorArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shiny Armor, Block: 6. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: That's a real shiny armor!");
            }
        });
        warriorArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Hardened Armor, Block: 10. Cost 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: You like that one?");
            }
        });
        warriorArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Royal Enchanted Armor, Block: 20. Cost 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: No need to worry in that armor!");
            }
        });
        warriorWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Iron sword, Attack: 8. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Give it a swing!");
            }
        });
        warriorWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Tempered steel blade, Attack: 15. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Watch out, it's sharp!");
            }
        });
        warriorWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Sword of a thousand truths, Attack: 27. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: The truth hurts!");
            }
        });
        //Mage
        mageArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Mooncloth robe, Block: 2. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: You would look great in that one!");
            }
        });
        mageArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Enchanted robe, Block: 4. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Feel how soft it is!");
            }
        });
        mageArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Robe of the archmage, Block: 6. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Don't you love the look of that one?");
            }
        });
        mageWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Ivory fire wand, Attack: 7. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: You wanna try it?");
            }
        });
        mageWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Enchanted mana wand, Attack: 15. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: If you wanna try it, do it outside..");
            }
        });
        mageWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Pyromaniac's tinderbox, Attack: 30. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: That's the most powerful weapon i know of.");
            }
        });
        //Healer
        healerArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Priests robe, Block: 2. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: I love the colors on that one.");
            }
        });
        healerArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Clerics armor, Block: 8. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: That's a nice armor.");
            }
        });
        healerArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Plate armor of Parl'ont the crusader, Block: 13. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Do you want to try it on?");
            }
        });
        healerWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Stick of truth, Attack: 7. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Such a nice staff.");
            }
        });
        healerWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Cleric's blessed walking stick, Attack: 13. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: You can use it as a walking stick!");
            }
        });
        healerWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Root of the world tree, Attack: 23. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: I wonder if it's really from that tree..?");
            }
        });
        //Ranger
        rangerArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Fine leather armor, Block: 5. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: The leatherworker made that from my own cow!");
            }
        });
        rangerArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Elven leather armor, Block: 11. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: The elves are kind of #$@&%* actually..");
            }
        });
        rangerArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Demonskin armor, Block: 20. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: Does demons really have skin..?");
            }
        });
        rangerWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Elven bow, Attack: 8. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: The elves are great weapon makers!");
            }
        });
        rangerWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Dragonslayer's bow, Attack: 16. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: The tales say it killed the last dragon!");
            }
        });
        rangerWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                currentAction.setText("Bullseye's bow, Attack: 26. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                currentAction.setText("Shopkeeper: You can hit anything with that bow!");
            }
        });
    }

    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException e) {
        }
    }
}