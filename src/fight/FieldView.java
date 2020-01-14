package fight;

import imports.Potions;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FieldView {

    JFrame fieldFightJFrame = new JFrame();
    Potions potions = new Potions();
    JFrame inventory = new JFrame();

    //Create fonts
    private Font pixelMplus;

    //Create buttons
    public JButton attackButton, blockButton, itemButton, skillButton, endTurnButton;
    public JButton potion1, potion2, potion3, potion4, potion5, potion6, potion7, potion8, potion9, potion10, potion11, potion12;
    public JButton skill1Button, skill2Button, skill3Button, skill4Button, returnButton; //spells
    public JButton healWarriorButton, healRangerButton, healMageButton, healHealerButton; //healing target button

    //Create Labels
    public JLabel inventoryHealth, inventoryBlock, inventoryEnergy, inventoryStr ,potion1Label,potion2Label,potion3Label,potion4Label,potion5Label,potion6Label,potion7Label,potion8Label,potion9Label,potion10Label,potion11Label,potion12Label;
    public JLabel whosTurn, energy, block;
    public JLabel scarecrow1, scarecrow2, scarecrow3, scarecrow4;
    public JLabel scarecrow1Hp, scarecrow2Hp, scarecrow3Hp, scarecrow4Hp;
    public JLabel warrior, mage, healer, ranger;
    public JLabel playersHp, player1Hp, player2Hp, player3Hp, player4Hp;

    public JLabel arrow = new JLabel(new ImageIcon("arrow.png"));
    public JLabel volley1 = new JLabel(new ImageIcon("arrow.png"));
    public JLabel volley2 = new JLabel(new ImageIcon("arrow.png"));
    public JLabel volley3 = new JLabel(new ImageIcon("arrow.png"));
    public JLabel flame = new JLabel(new ImageIcon("flame.gif"));
    public JLabel fireStorm = new JLabel(new ImageIcon("bigfire.gif"));
    public JLabel mediumPyroBlast = new JLabel(new ImageIcon("mediumflame.gif"));
    public JLabel smallPyroBlast = new JLabel(new ImageIcon("miniflame.gif"));
    public JLabel bigPyroBlast = new JLabel(new ImageIcon("flame.gif"));
    public JLabel swordIcon = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel upArrow = new JLabel(new ImageIcon("uparrow.png"));
    public JLabel demoSword1 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel demoSword2 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel demoSword3 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel demoSword4 = new JLabel(new ImageIcon("warriorRareWeapon.png"));
    public JLabel bomb = new JLabel(new ImageIcon("bomb.png"));
    public JLabel explode = new JLabel(new ImageIcon("explode.gif"));
    public JLabel stealthranger = new JLabel(new ImageIcon("stealth ranger.gif"));
    public JLabel trap = new JLabel(new ImageIcon("trap.png"));
    public JLabel holyLight = new JLabel(new ImageIcon("holylight.png"));
    public JLabel smallHolyLight = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal1 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal2 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal3 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel groupHeal4 = new JLabel(new ImageIcon("smallholylight.png"));
    public JLabel targetarrow = new JLabel(new ImageIcon("targetarrow.png"));
    public JLabel blast = new JLabel(new ImageIcon("blastgif.gif"));


    public void fieldFightFrame(){

        fieldFightJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fieldFightJFrame.setSize(1920, 1080);
        fieldFightJFrame.setLayout(null);
        fieldFightJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fieldFightJFrame.setUndecorated(true);

        ImageIcon background = new ImageIcon("field.png");
        fieldFightJFrame.setContentPane(new JLabel(background));

        importFont();
        getInventory();
        importButtons();
        importLabels();
        importPartyGif();
        importscarecrowGif();
        spellMenuStartup();
        animationStuff();
        healingTarget();

        fieldFightJFrame.add(energy);
        fieldFightJFrame.add(block);
        fieldFightJFrame.add(whosTurn);

        fieldFightJFrame.add(targetarrow);

        fieldFightJFrame.add(attackButton);
        fieldFightJFrame.add(blockButton);
        fieldFightJFrame.add(itemButton);
        fieldFightJFrame.add(skillButton);
        fieldFightJFrame.add(endTurnButton);

        fieldFightJFrame.add(playersHp);
        fieldFightJFrame.add(scarecrow1Hp);
        fieldFightJFrame.add(scarecrow2Hp);
        fieldFightJFrame.add(scarecrow3Hp);
        fieldFightJFrame.add(scarecrow4Hp);
        fieldFightJFrame.add(player1Hp);
        fieldFightJFrame.add(player2Hp);
        fieldFightJFrame.add(player3Hp);
        fieldFightJFrame.add(player4Hp);

        fieldFightJFrame.add(skill1Button);
        fieldFightJFrame.add(skill2Button);
        fieldFightJFrame.add(skill3Button);
        fieldFightJFrame.add(skill4Button);
        fieldFightJFrame.add(returnButton);

        fieldFightJFrame.add(healWarriorButton);
        fieldFightJFrame.add(healRangerButton);
        fieldFightJFrame.add(healMageButton);
        fieldFightJFrame.add(healHealerButton);

        fieldFightJFrame.add(arrow);
        fieldFightJFrame.add(volley1);
        fieldFightJFrame.add(volley2);
        fieldFightJFrame.add(volley3);
        fieldFightJFrame.add(blast);
        fieldFightJFrame.add(fireStorm);
        fieldFightJFrame.add(flame);
        fieldFightJFrame.add(smallPyroBlast);
        fieldFightJFrame.add(mediumPyroBlast);
        fieldFightJFrame.add(bigPyroBlast);
        fieldFightJFrame.add(swordIcon);
        fieldFightJFrame.add(demoSword1);
        fieldFightJFrame.add(demoSword2);
        fieldFightJFrame.add(demoSword3);
        fieldFightJFrame.add(demoSword4);
        fieldFightJFrame.add(bomb);
        fieldFightJFrame.add(explode);
        fieldFightJFrame.add(stealthranger);
        fieldFightJFrame.add(trap);
        fieldFightJFrame.add(holyLight);
        fieldFightJFrame.add(smallHolyLight);
        fieldFightJFrame.add(groupHeal1);
        fieldFightJFrame.add(groupHeal2);
        fieldFightJFrame.add(groupHeal3);
        fieldFightJFrame.add(groupHeal4);

        //enemies
        fieldFightJFrame.add(scarecrow3);
        fieldFightJFrame.add(scarecrow4);
        fieldFightJFrame.add(scarecrow1);
        fieldFightJFrame.add(scarecrow2);
        //player
        fieldFightJFrame.add(ranger);
        fieldFightJFrame.add(warrior);
        fieldFightJFrame.add(mage);
        fieldFightJFrame.add(healer);

        fieldFightJFrame.setVisible(true);
    }

    public void animationStuff(){

        Dimension arrowSize = arrow.getPreferredSize();
        arrow.setSize(arrowSize.width, arrowSize.height);
        arrow.setLocation(-100, 120);

        volley1.setSize(arrowSize.width, arrowSize.height);
        volley1.setLocation(-100, 120);
        volley1.setVisible(false);

        volley2.setSize(arrowSize.width, arrowSize.height);
        volley2.setLocation(-100, 120);
        volley2.setVisible(false);

        volley3.setSize(arrowSize.width, arrowSize.height);
        volley3.setLocation(-100, 120);
        volley3.setVisible(false);

        Dimension blastSize = blast.getPreferredSize();
        blast.setSize(blastSize.width, blastSize.height);
        blast.setLocation(100, 121);
        blast.setVisible(false);

        Dimension fireSize = fireStorm.getPreferredSize();
        fireStorm.setSize(fireSize.width, fireSize.height);
        fireStorm.setLocation(800, 300);
        fireStorm.setVisible(false);

        Dimension flameSize = flame.getPreferredSize();
        flame.setSize(flameSize.width, flameSize.height);
        flame.setLocation(900, -400);
        flame.setVisible(true);

        Dimension smallPyroBlastSize = smallPyroBlast.getPreferredSize();
        smallPyroBlast.setSize(smallPyroBlastSize.width, smallPyroBlastSize.height);
        smallPyroBlast.setLocation(125, 300);
        smallPyroBlast.setVisible(false);

        Dimension mediumPyroBlastSize = mediumPyroBlast.getPreferredSize();
        mediumPyroBlast.setSize(mediumPyroBlastSize.width, mediumPyroBlastSize.height);
        mediumPyroBlast.setLocation(85, 200);
        mediumPyroBlast.setVisible(false);

        Dimension bigPyroBlastSize = bigPyroBlast.getPreferredSize();
        bigPyroBlast.setSize(bigPyroBlastSize.width, bigPyroBlastSize.height);
        bigPyroBlast.setLocation(45, 150);
        bigPyroBlast.setVisible(false);

        Dimension swordIconSize = swordIcon.getPreferredSize();
        swordIcon.setSize(swordIconSize.width, swordIconSize.height);
        swordIcon.setLocation(125, 300);
        swordIcon.setVisible(false);

        Dimension demoSword1Size = demoSword1.getPreferredSize();
        demoSword1.setSize(demoSword1Size.width, demoSword1Size.height);
        demoSword1.setLocation(125, 300);
        demoSword1.setVisible(false);

        Dimension demoSword2Size = demoSword2.getPreferredSize();
        demoSword2.setSize(demoSword2Size.width, demoSword2Size.height);
        demoSword2.setLocation(125, 300);
        demoSword2.setVisible(false);

        Dimension demoSword3Size = demoSword3.getPreferredSize();
        demoSword3.setSize(demoSword3Size.width, demoSword3Size.height);
        demoSword3.setLocation(125, 300);
        demoSword3.setVisible(false);

        Dimension demoSword4Size = demoSword4.getPreferredSize();
        demoSword4.setSize(demoSword4Size.width, demoSword4Size.height);
        demoSword4.setLocation(125, 300);
        demoSword4.setVisible(false);

        Dimension bombSize = bomb.getPreferredSize();
        bomb.setSize(bombSize.width, bombSize.height);
        bomb.setLocation(250, 300);
        bomb.setVisible(false);

        Dimension explodeSize = explode.getPreferredSize();
        explode.setSize(explodeSize.width, explodeSize.height);
        explode.setLocation(900, 250);
        explode.setVisible(false);

        Dimension stealthrangerSize = stealthranger.getPreferredSize();
        stealthranger.setSize(stealthrangerSize.width, stealthrangerSize.height);
        stealthranger.setLocation(70, 290);
        stealthranger.setVisible(false);

        Dimension trapSize = trap.getPreferredSize();
        trap.setSize(trapSize.width, trapSize.height);
        trap.setLocation(70, 290);
        trap.setVisible(false);

        Dimension holyLightSize = holyLight.getPreferredSize();
        holyLight.setSize(holyLightSize.width, holyLightSize.height);
        holyLight.setLocation(70, 290);
        holyLight.setVisible(false);

        Dimension smallHolyLightSize = holyLight.getPreferredSize();
        smallHolyLight.setSize(smallHolyLightSize.width, smallHolyLightSize.height);
        smallHolyLight.setLocation(70, 290);
        smallHolyLight.setVisible(false);

        Dimension groupHealSize = groupHeal1.getPreferredSize();
        groupHeal1.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal1.setLocation(70, 290);
        groupHeal1.setVisible(false);

        groupHeal2.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal2.setLocation(70, 290);
        groupHeal2.setVisible(false);

        groupHeal3.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal3.setLocation(70, 290);
        groupHeal3.setVisible(false);

        groupHeal4.setSize(groupHealSize.width, groupHealSize.height);
        groupHeal4.setLocation(70, 290);
        groupHeal4.setVisible(false);

        Dimension targetarrowSize = targetarrow.getPreferredSize();
        targetarrow.setSize(targetarrowSize.width, targetarrowSize.height);
        targetarrow.setLocation(200, 500);
        targetarrow.setVisible(false);

    }

    public void spellMenuStartup(){

        //button 1
        skill1Button = new JButton("error skill 1           ");
        skill1Button.setSize(200, 70);
        skill1Button.setLocation(840, 555);
        skill1Button.setFont(pixelMplus.deriveFont(30f));
        skill1Button.setBackground(Color.white);
        skill1Button.setBorder(null); //Remove border around button
        skill1Button.setFocusPainted(false);//Remove border around text in button

        //button 2
        skill2Button = new JButton("error skill 2           ");
        skill2Button.setSize(200, 70);
        skill2Button.setLocation(1050, 555);
        skill2Button.setFont(pixelMplus.deriveFont(30f));
        skill2Button.setBackground(Color.white);
        skill2Button.setBorder(null); //Remove border around button
        skill2Button.setFocusPainted(false);//Remove border around text in button

        //button 3
        skill3Button = new JButton("error skill 3            ");
        skill3Button.setSize(200, 70);
        skill3Button.setLocation(1050, 630);
        skill3Button.setFont(pixelMplus.deriveFont(30f));
        skill3Button.setBackground(Color.white);
        skill3Button.setBorder(null); //Remove border around button
        skill3Button.setFocusPainted(false);//Remove border around text in button

        //Skill Button
        skill4Button = new JButton("error skill 4            ");
        skill4Button.setSize(200, 70);
        skill4Button.setLocation(840, 630);
        skill4Button.setFont(pixelMplus.deriveFont(30f));
        skill4Button.setBackground(Color.white);
        skill4Button.setBorder(null); //Remove border around button
        skill4Button.setFocusPainted(false);//Remove border around text in button

        returnButton = new JButton("Return");
        returnButton.setSize(200, 70);
        returnButton.setLocation(540, 468);
        returnButton.setFont(pixelMplus.deriveFont(30f));
        returnButton.setBackground(Color.white);
        returnButton.setBorder(null); //Remove border around button
        returnButton.setFocusPainted(false);//Remove border around text in button
        returnButton.setVisible(false);

    }

    //to select target for healing
    public void healingTarget(){

        //button 1
        healWarriorButton = new JButton("Warrior");
        healWarriorButton.setSize(200, 70);
        healWarriorButton.setLocation(840, 555);
        healWarriorButton.setFont(pixelMplus.deriveFont(30f));
        healWarriorButton.setBackground(Color.white);
        healWarriorButton.setBorder(null); //Remove border around button
        healWarriorButton.setFocusPainted(false);//Remove border around text in button

        //button 2
        healRangerButton = new JButton("Ranger");
        healRangerButton.setSize(200, 70);
        healRangerButton.setLocation(1050, 555);
        healRangerButton.setFont(pixelMplus.deriveFont(30f));
        healRangerButton.setBackground(Color.white);
        healRangerButton.setBorder(null); //Remove border around button
        healRangerButton.setFocusPainted(false);//Remove border around text in button

        //button 3
        healMageButton = new JButton("Mage");
        healMageButton.setSize(200, 70);
        healMageButton.setLocation(1050, 630);
        healMageButton.setFont(pixelMplus.deriveFont(30f));
        healMageButton.setBackground(Color.white);
        healMageButton.setBorder(null); //Remove border around button
        healMageButton.setFocusPainted(false);//Remove border around text in button

        //Skill Button
        healHealerButton = new JButton("Healer");
        healHealerButton.setSize(200, 70);
        healHealerButton.setLocation(840, 630);
        healHealerButton.setFont(pixelMplus.deriveFont(30f));
        healHealerButton.setBackground(Color.white);
        healHealerButton.setBorder(null); //Remove border around button
        healHealerButton.setFocusPainted(false);//Remove border around text in button

    }

    //This method triggers when you press the "Item button". It opens up another JFrame that covers the hud.
    public void itemPressed(){

        //Frame settings
        inventory = new JFrame();
        inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventory.setLayout(null);
        inventory.setSize(1920, 300);
        inventory.setTitle("Inventory");
        inventory.setLocation(0,538);

        getInventory();

        //Background
        ImageIcon backgroundInventory = new ImageIcon("white.jpg");
        inventory.setContentPane(new JLabel(backgroundInventory));

        //Add all labels//buttons.
        inventory.add(potion1);
        inventory.add(potion2);
        inventory.add(potion3);
        inventory.add(potion4);
        inventory.add(potion5);
        inventory.add(potion6);
        inventory.add(potion7);
        inventory.add(potion8);
        inventory.add(potion9);
        inventory.add(potion10);
        inventory.add(potion11);
        inventory.add(potion12);
        inventory.add(inventoryHealth);
        inventory.add(inventoryBlock);
        inventory.add(inventoryEnergy);
        inventory.add(inventoryStr);
        inventory.add(potion1Label);
        inventory.add(potion2Label);
        inventory.add(potion3Label);
        inventory.add(potion4Label);
        inventory.add(potion5Label);
        inventory.add(potion6Label);
        inventory.add(potion7Label);
        inventory.add(potion8Label);
        inventory.add(potion9Label);
        inventory.add(potion10Label);
        inventory.add(potion11Label);
        inventory.add(potion12Label);

        inventory.setUndecorated(true);
        inventory.setVisible(true);
    }

    private void getInventory() {

        //Minor Health Potion
        potion1 = new JButton(potions.minorHealthGif);
        potion1.setBounds(200, 60, 41, 62);
        potion1.setBackground(Color.white);
        potion1.setBorder(null);
        potion1.setFocusPainted(false);

        //Lesser Health Potion
        potion2 = new JButton(potions.lesserHealthGif);
        potion2.setBounds(250, 60, 46, 62);
        potion2.setBackground(Color.white);
        potion2.setBorder(null);
        potion2.setFocusPainted(false);

        //Major Health Potion
        potion3 = new JButton(potions.majorHealthGif);
        potion3.setBounds(310, 60, 55, 64);
        potion3.setBackground(Color.white);
        potion3.setBorder(null);
        potion3.setFocusPainted(false);

        //Minor Block Potion
        potion4 = new JButton(potions.minorBlockGif);
        potion4.setBounds(450, 60, 42, 63);
        potion4.setBackground(Color.white);
        potion4.setBorder(null);
        potion4.setFocusPainted(false);

        //Lesser Block Potion
        potion5 = new JButton(potions.lesserBlockGif);
        potion5.setBounds(500, 60, 47, 63);
        potion5.setBackground(Color.white);
        potion5.setBorder(null);
        potion5.setFocusPainted(false);

        //Major Block Potion
        potion6 = new JButton(potions.majorBlockGif);
        potion6.setBounds(560, 60, 59, 64);
        potion6.setBackground(Color.white);
        potion6.setBorder(null);
        potion6.setFocusPainted(false);

        //Minor Energy Potion
        potion7 = new JButton(potions.minorEnergyGif);
        potion7.setBounds(700, 60, 40, 63);
        potion7.setBackground(Color.white);
        potion7.setBorder(null);
        potion7.setFocusPainted(false);

        //Lesser Energy Potion
        potion8 = new JButton(potions.lesserEnergyGif);
        potion8.setBounds(750, 60, 46, 63);
        potion8.setBackground(Color.white);
        potion8.setBorder(null);
        potion8.setFocusPainted(false);

        //Major Energy Potion
        potion9 = new JButton(potions.majorEnergyGif);
        potion9.setBounds(810, 60, 59, 64);
        potion9.setBackground(Color.white);
        potion9.setBorder(null);
        potion9.setFocusPainted(false);

        //Minor Strength Potion
        potion10 = new JButton(potions.minorStrGif);
        potion10.setBounds(950, 60, 38, 63);
        potion10.setBackground(Color.white);
        potion10.setBorder(null);
        potion10.setFocusPainted(false);

        //Lesser Energy Potion
        potion11 = new JButton(potions.lesserStrGif);
        potion11.setBounds(1000, 60, 42, 63);
        potion11.setBackground(Color.white);
        potion11.setBorder(null);
        potion11.setFocusPainted(false);

        //Major Energy Potion
        potion12 = new JButton(potions.majorStrGif);
        potion12.setBounds(1050, 60, 53, 64);
        potion12.setBackground(Color.white);
        potion12.setBorder(null);
        potion12.setFocusPainted(false);

        //Labels
        inventoryHealth = new JLabel("Health");
        inventoryHealth.setFont(pixelMplus.deriveFont(30f));
        inventoryHealth.setForeground(Color.black);
        Dimension inventoryHealthSize = inventoryHealth.getPreferredSize();
        inventoryHealth.setBounds(240, 25, inventoryHealthSize.width, inventoryHealthSize.height);

        inventoryStr = new JLabel("Strength");
        inventoryStr.setFont(pixelMplus.deriveFont(30f));
        inventoryStr.setForeground(Color.black);
        Dimension inventoryStrSize = inventoryStr.getPreferredSize();
        inventoryStr.setBounds(965, 25, inventoryStrSize.width, inventoryStrSize.height);

        inventoryEnergy = new JLabel("Energy");
        inventoryEnergy.setFont(pixelMplus.deriveFont(30f));
        inventoryEnergy.setForeground(Color.black);
        Dimension inventoryEnergySize = inventoryEnergy.getPreferredSize();
        inventoryEnergy.setBounds(740, 25, inventoryEnergySize.width, inventoryEnergySize.height);

        inventoryBlock = new JLabel("Block");
        inventoryBlock.setFont(pixelMplus.deriveFont(30f));
        inventoryBlock.setForeground(Color.black);
        Dimension inventoryBlockSize = inventoryBlock.getPreferredSize();
        inventoryBlock.setBounds(495, 25, inventoryBlockSize.width, inventoryBlockSize.height);

        //Potion owned
        potion1Label.setFont(pixelMplus.deriveFont(30f));
        potion1Label.setForeground(Color.black);
        Dimension potion1LabelSize = potion1Label.getPreferredSize();
        potion1Label.setBounds(210, 125, potion1LabelSize.width, potion1LabelSize.height);

        potion2Label.setFont(pixelMplus.deriveFont(30f));
        potion2Label.setForeground(Color.black);
        Dimension potion2LabelSize = potion2Label.getPreferredSize();
        potion2Label.setBounds(265, 125, potion2LabelSize.width, potion2LabelSize.height);

        potion3Label.setFont(pixelMplus.deriveFont(30f));
        potion3Label.setForeground(Color.black);
        Dimension potion3LabelSize = potion3Label.getPreferredSize();
        potion3Label.setBounds(330, 125, potion3LabelSize.width, potion3LabelSize.height);

        potion4Label.setFont(pixelMplus.deriveFont(30f));
        potion4Label.setForeground(Color.black);
        Dimension potion4LabelSize = potion4Label.getPreferredSize();
        potion4Label.setBounds(470, 125, potion4LabelSize.width, potion4LabelSize.height);

        potion5Label.setFont(pixelMplus.deriveFont(30f));
        potion5Label.setForeground(Color.black);
        Dimension potion5LabelSize = potion5Label.getPreferredSize();
        potion5Label.setBounds(520, 125, potion5LabelSize.width, potion5LabelSize.height);

        potion6Label.setFont(pixelMplus.deriveFont(30f));
        potion6Label.setForeground(Color.black);
        Dimension potion6LabelSize = potion6Label.getPreferredSize();
        potion6Label.setBounds(580, 125, potion6LabelSize.width, potion6LabelSize.height);

        potion7Label.setFont(pixelMplus.deriveFont(30f));
        potion7Label.setForeground(Color.black);
        Dimension potion7LabelSize = potion7Label.getPreferredSize();
        potion7Label.setBounds(707, 125, potion7LabelSize.width, potion7LabelSize.height);

        potion8Label.setFont(pixelMplus.deriveFont(30f));
        potion8Label.setForeground(Color.black);
        Dimension potion8LabelSize = potion8Label.getPreferredSize();
        potion8Label.setBounds(767, 125, potion8LabelSize.width, potion8LabelSize.height);

        potion9Label.setFont(pixelMplus.deriveFont(30f));
        potion9Label.setForeground(Color.black);
        Dimension potion9LabelSize = potion9Label.getPreferredSize();
        potion9Label.setBounds(830, 125, potion9LabelSize.width, potion9LabelSize.height);

        potion10Label.setFont(pixelMplus.deriveFont(30f));
        potion10Label.setForeground(Color.black);
        Dimension potion10LabelSize = potion10Label.getPreferredSize();
        potion10Label.setBounds(967, 125, potion10LabelSize.width, potion10LabelSize.height);

        potion11Label.setFont(pixelMplus.deriveFont(30f));
        potion11Label.setForeground(Color.black);
        Dimension potion11LabelSize = potion11Label.getPreferredSize();
        potion11Label.setBounds(1013, 125, potion11LabelSize.width, potion11LabelSize.height);

        potion12Label.setFont(pixelMplus.deriveFont(30f));
        potion12Label.setForeground(Color.black);
        Dimension potion12LabelSize = potion12Label.getPreferredSize();
        potion12Label.setBounds(1070, 125, potion12LabelSize.width, potion12LabelSize.height);
    }

    //Set al "stats" for labels.
    private void importLabels(){
        playersHp.setFont(pixelMplus.deriveFont(30f));
        playersHp.setForeground(Color.black);
        Dimension playersHpSize = playersHp.getPreferredSize();
        playersHp.setBounds(30, 600, playersHpSize.width, playersHpSize.height);

        scarecrow1Hp.setFont(pixelMplus.deriveFont(30f));
        scarecrow1Hp.setForeground(Color.black);
        Dimension wolf1HpSize = scarecrow1Hp.getPreferredSize();
        scarecrow1Hp.setBounds(640, 560, wolf1HpSize.width, wolf1HpSize.height);

        scarecrow2Hp.setFont(pixelMplus.deriveFont(30f));
        scarecrow2Hp.setForeground(Color.black);
        Dimension wolf2HpSize = scarecrow2Hp.getPreferredSize();
        scarecrow2Hp.setBounds(640, 595, wolf2HpSize.width, wolf2HpSize.height);

        scarecrow3Hp.setFont(pixelMplus.deriveFont(30f));
        scarecrow3Hp.setForeground(Color.black);
        Dimension wolf3HpSize = scarecrow3Hp.getPreferredSize();
        scarecrow3Hp.setBounds(640, 630, wolf3HpSize.width, wolf3HpSize.height);

        scarecrow4Hp.setFont(pixelMplus.deriveFont(30f));
        scarecrow4Hp.setForeground(Color.black);
        Dimension wolf4HpSize = scarecrow4Hp.getPreferredSize();
        scarecrow4Hp.setBounds(640, 665, wolf4HpSize.width, wolf4HpSize.height);

        player1Hp.setFont(pixelMplus.deriveFont(30f));
        player1Hp.setForeground(Color.black);
        Dimension player1HpSize = player1Hp.getPreferredSize();
        player1Hp.setBounds(410, 560, player1HpSize.width, player1HpSize.height);

        player2Hp.setFont(pixelMplus.deriveFont(30f));
        player2Hp.setForeground(Color.black);
        Dimension player2HpSize = player1Hp.getPreferredSize();
        player2Hp.setBounds(410, 595, player2HpSize.width, player2HpSize.height);

        player3Hp.setFont(pixelMplus.deriveFont(30f));
        player3Hp.setForeground(Color.black);
        Dimension player3HpSize = player3Hp.getPreferredSize();
        player3Hp.setBounds(410, 630, player3HpSize.width, player3HpSize.height);

        player4Hp.setFont(pixelMplus.deriveFont(30f));
        player4Hp.setForeground(Color.black);
        Dimension player4HpSize = player4Hp.getPreferredSize();
        player4Hp.setBounds(410, 665, player4HpSize.width, player4HpSize.height);

        energy = new JLabel("Energy: 5");
        energy.setFont(pixelMplus.deriveFont(30f));
        energy.setForeground(Color.black);
        Dimension energySize = energy.getPreferredSize();
        energy.setBounds(30, 640, energySize.width, energySize.height);

        block.setFont(pixelMplus.deriveFont(30f));
        block.setForeground(Color.black);
        Dimension blockSize = energy.getPreferredSize();
        block.setBounds(30, 670, blockSize.width, blockSize.height);

        whosTurn = new JLabel("Warrior's turn");
        whosTurn.setFont(pixelMplus.deriveFont(30f));
        whosTurn.setForeground(Color.black);
        whosTurn.setBackground(Color.blue);
        Dimension whoSize = whosTurn.getPreferredSize();
        //whosTurn.setMaximumSize(new Dimension(whoSize.width+100,whoSize.height+100));
        whosTurn.setBounds(30, 560, whoSize.width, whoSize.height);
    }

    //Set all stats for buttons.
    private void importButtons(){
        //Attack button
        attackButton = new JButton("Attack");
        attackButton.setSize(200, 70);
        attackButton.setLocation(840, 555);
        attackButton.setFont(pixelMplus.deriveFont(30f));
        attackButton.setBackground(Color.white);
        attackButton.setBorder(null); //Remove border around button
        attackButton.setFocusPainted(false);//Remove border around text in button

        //Block button
        blockButton = new JButton("Block");
        blockButton.setSize(200, 70);
        blockButton.setLocation(1050, 555);
        blockButton.setFont(pixelMplus.deriveFont(30f));
        blockButton.setBackground(Color.white);
        blockButton.setBorder(null); //Remove border around button
        blockButton.setFocusPainted(false);//Remove border around text in button

        //Item Button
        itemButton = new JButton("Item");
        itemButton.setSize(200, 70);
        itemButton.setLocation(1050, 630);
        itemButton.setFont(pixelMplus.deriveFont(30f));
        itemButton.setBackground(Color.white);
        itemButton.setBorder(null); //Remove border around button
        itemButton.setFocusPainted(false);//Remove border around text in button

        //Skill Button
        skillButton = new JButton("Skill");
        skillButton.setSize(200, 70);
        skillButton.setLocation(840, 630);
        skillButton.setFont(pixelMplus.deriveFont(30f));
        skillButton.setBackground(Color.white);
        skillButton.setBorder(null); //Remove border around button
        skillButton.setFocusPainted(false);//Remove border around text in button

        //End Turn Button
        endTurnButton = new JButton("End turn");
        endTurnButton.setSize(200, 70);
        endTurnButton.setLocation(540, 468);
        endTurnButton.setFont(pixelMplus.deriveFont(30f));
        endTurnButton.setBackground(Color.white);
        endTurnButton.setBorder(null); //Remove border around button
        endTurnButton.setFocusPainted(false);//Remove border around text in button
    }

    //Get all OldClasses.party-gif's.
    private void importPartyGif(){
        warrior = new JLabel();
        warrior.setIcon(new ImageIcon("warrior.gif"));

        healer = new JLabel();
        healer.setIcon(new ImageIcon("healer.gif"));

        ranger = new JLabel();
        ranger.setIcon(new ImageIcon("ranger.gif"));

        mage = new JLabel();
        mage.setIcon(new ImageIcon("mage.gif"));

        Dimension warriorSize = warrior.getPreferredSize();
        Dimension healerSize = healer.getPreferredSize();
        Dimension rangerSize = ranger.getPreferredSize();
        Dimension mageSize = mage.getPreferredSize();

        warrior.setBounds(170, 210, warriorSize.width, warriorSize.height);
        healer.setBounds(-30, 210, healerSize.width, healerSize.height);
        ranger.setBounds(70, 290, rangerSize.width, rangerSize.height);
        mage.setBounds(-110, 290, mageSize.width, mageSize.height);
    }

    //Get scarecrow gif.
    private void importscarecrowGif(){
        scarecrow1 = new JLabel();
        scarecrow1.setIcon(new ImageIcon("scarecrow.gif"));
        Dimension scarecrowSize = scarecrow1.getPreferredSize();
        scarecrow1.setBounds(850, 300, scarecrowSize.width+50, scarecrowSize.height);

        scarecrow2 = new JLabel();
        scarecrow2.setIcon(new ImageIcon("scarecrow.gif"));
        scarecrow2.setBounds(1030, 300, scarecrowSize.width+50, scarecrowSize.height);

        scarecrow3 = new JLabel();
        scarecrow3.setIcon(new ImageIcon("scarecrow.gif"));
        scarecrow3.setBounds(920, 370, scarecrowSize.width+50, scarecrowSize.height);

        scarecrow4 = new JLabel();
        scarecrow4.setIcon(new ImageIcon("scarecrow.gif"));
        scarecrow4.setBounds(1100, 370, scarecrowSize.width+50, scarecrowSize.height);
    }

    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}
