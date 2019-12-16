package fight;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ForestFight extends JFrame {

    //Objects
    private Inventory inv = new Inventory();
    private Warrior w = new Warrior();
    private Mage m = new Mage();
    private Healer h = new Healer();
    private Ranger r = new Ranger();

    //Create fonts
    private Font pixelMplus;

    //Create buttons
    private JButton attackButton, blockButton, itemButton, skillButton, endTurnButton;
    private JButton exitInventory, potion1, potion2, potion3, potion4, potion5, potion6, potion7, potion8, potion9, potion10, potion11, potion12;
    private JButton skill1Button, skill2Button, skill3Button, skill4Button, returnButton; //spells

    //Create Labels
    private JLabel inventoryHealth, inventoryBlock, inventoryEnergy, inventoryStr, potion1Label, potion2Label, potion3Label, potion4Label, potion5Label, potion6Label, potion7Label, potion8Label, potion9Label, potion10Label, potion11Label, potion12Label;
    private JLabel whosTurn, energy, block;
    private JLabel wolf1, wolf2, wolf3, wolf4;
    private JLabel wolf1Hp, wolf2Hp, wolf3Hp, wolf4Hp;
    private JLabel warrior, mage, healer, ranger;
    private JLabel playersHp, player1Hp, player2Hp, player3Hp, player4Hp;

    //Create int's
    private int turns = 1;
    private int currentEnergy;
    private int warriorEnergyInt = 5, mageEnergyInt, rangerEnergyInt, healerEnergyInt;
    private int wolf1Int, wolf2Int, wolf3Int, wolf4Int;

    //Get hp, block and damage from party
    private int warriorCurrentHp = w.hp, mageCurrentHp = m.hp, healerCurrentHp = h.hp, rangerCurrentHp = r.hp;
    private int warriorDamage = w.combinedDamage, mageDamage = m.combinedDamage, healerDamage = h.combinedDamage, rangerDamage = r.combinedDamage;
    private int warriorBlock = w.combinedBlock, mageBlock = m.combinedBlock, healerBlock = h.combinedBlock, rangerBlock = r.combinedBlock;

    //Animation variables 
    private int warriorStartX = 170, warriorStartY = 210, warriorX = warriorStartX, warriorY = warriorStartY;
    private int rangerStartX = 70, rangerStartY = 290, rangerX = rangerStartX, rangerY = rangerStartY;
    private int mageStartX = -110, mageStartY = 290, mageX = mageStartX, mageY = mageStartY;
    private int healerStartX = -30, healerStartY = 210, healerX = healerStartX, healerY = healerStartY;

    private int wolf1X = 850, wolf1Y = 320, wolf1StartX = wolf1X, wolf1StartY = wolf1Y;
    private int wolf2X = 1030, wolf2Y = 320, wolf2StartX = wolf2X, wolf2StartY = wolf2Y;
    private int wolf3X = 900, wolf3Y = 400, wolf3StartX = wolf3X, wolf3StartY = wolf3Y;
    private int wolf4X = 1080, wolf4Y = 400, wolf4StartX = wolf4X, wolf4StartY = wolf4Y;

    private int arrowX = 120, arrowY = 360, arrowStartX = arrowX, arrowStartY = arrowY;
    private int flameStrikeY = -400;

    private int superMegaMath = 30; //används för halv cirkel anitamationer, PLEASE FOR THE LOVE OF GOD RENAME THIS MONSTOSITY
    private int counterMegaMath = -30;
    private int upMegaMath = 1;
    private int rightMegaMath = 1;
    private int downMegaMath = 1;
    private int leftMegaMath = 1;

    private int pyroBlastX = 45;
    private int pyroblastY = 150;

    //Another timePast to avoid conflict when they run simultaneously.
    private int timePastTakeDamage = 0;

    private int target;
    private int phase = 0, timePast = 0;

    private JLabel arrow = new JLabel(new ImageIcon("arrow.png"));
    private JLabel volley1 = new JLabel(new ImageIcon("arrow.png"));
    private JLabel volley2 = new JLabel(new ImageIcon("arrow.png"));
    private JLabel volley3 = new JLabel(new ImageIcon("arrow.png"));
    private JLabel flame = new JLabel(new ImageIcon("flame.gif"));
    private JLabel fireStorm = new JLabel(new ImageIcon("bigfire.gif"));
    private JLabel mediumPyroBlast = new JLabel(new ImageIcon("mediumflame.gif"));
    private JLabel smallPyroBlast = new JLabel(new ImageIcon("miniflame.gif"));
    private JLabel bigPyroBlast = new JLabel(new ImageIcon("flame.gif"));
    private JLabel swordIcon = new JLabel(new ImageIcon("warriorRareWeapon.png"));

    public boolean startLootScreen = false;
    //Constructor for forestFight.
    public ForestFight() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(192, 108);
        setTitle("Forest Fight");
        importFont();

        //Background picture
        ImageIcon background = new ImageIcon("forest.jpg");
        setContentPane(new JLabel(background));

        //Set wolf hp & energy label
        wolf1Int = 20;
        wolf2Int = 20;
        wolf3Int = 20;
        wolf4Int = 20;
        currentEnergy = warriorEnergyInt;

        //Import methods
        importWolfGif();
        importPartyGif();
        importButtons();
        importLabels();
        spellMenuStartup();

        //Add all Labels, buttons etc...
        add(energy);
        add(block);
        add(whosTurn);
        add(attackButton);
        add(blockButton);
        add(itemButton);
        add(skillButton);
        add(endTurnButton);
        add(wolf3);
        add(wolf4);
        add(wolf1);
        add(wolf2);
        add(ranger);
        add(warrior);
        add(mage);
        add(healer);
        add(playersHp);
        add(wolf1Hp);
        add(wolf2Hp);
        add(wolf3Hp);
        add(wolf4Hp);
        add(player1Hp);
        add(player2Hp);
        add(player3Hp);
        add(player4Hp);
        add(skill1Button);
        add(skill2Button);
        add(skill3Button);
        add(skill4Button);
        add(returnButton);

        hoverEffect();
        MusicPick.musicStart("forest1", "music");
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(false);

        //ActionListeners
        attackButton.addActionListener(e -> attackPressed());
        blockButton.addActionListener(e -> blockPressed());
        itemButton.addActionListener(e -> itemPressed());
        skillButton.addActionListener(e -> spellMenuActive()); //for now
        endTurnButton.addActionListener(e -> startNewTurn());
        skill1Button.addActionListener(e -> {
            skill1();
        });
        skill2Button.addActionListener(e -> {
            skill2();
        });
        skill3Button.addActionListener(e -> {
            skill3();
        });
        skill4Button.addActionListener(e -> {
            skill4();
        });


        //ActionListeners spellsmenu
        returnButton.addActionListener(e -> spellMenuInactive());


        //simon saker \/
        Dimension arrowSize = arrow.getPreferredSize();
        arrow.setSize(arrowSize.width, arrowSize.height);
        arrow.setLocation(-100, arrowX);
        add(arrow);

        volley1.setSize(arrowSize.width, arrowSize.height);
        volley1.setLocation(-100, arrowX);
        volley1.setVisible(false);
        add(volley1);
        volley2.setSize(arrowSize.width, arrowSize.height);
        volley2.setLocation(-100, arrowX);
        volley2.setVisible(false);
        add(volley2);
        volley3.setSize(arrowSize.width, arrowSize.height);
        volley3.setLocation(-100, arrowX);
        volley3.setVisible(false);
        add(volley3);

        Dimension fireSize = fireStorm.getPreferredSize();
        fireStorm.setSize(fireSize.width, fireSize.height);
        fireStorm.setLocation(800, 300);
        add(fireStorm);
        fireStorm.setVisible(false);

        Dimension flameSize = flame.getPreferredSize();
        flame.setSize(flameSize.width, flameSize.height);
        flame.setLocation(900, flameStrikeY);
        add(flame);
        flame.setVisible(true);

        Dimension smallPyroBlastSize = smallPyroBlast.getPreferredSize();
        smallPyroBlast.setSize(smallPyroBlastSize.width, smallPyroBlastSize.height);
        smallPyroBlast.setLocation(125, 300);
        add(smallPyroBlast);
        smallPyroBlast.setVisible(false);

        Dimension mediumPyroBlastSize = mediumPyroBlast.getPreferredSize();
        mediumPyroBlast.setSize(mediumPyroBlastSize.width, mediumPyroBlastSize.height);
        mediumPyroBlast.setLocation(85, 200);
        add(mediumPyroBlast);
        mediumPyroBlast.setVisible(false);

        Dimension bigPyroBlastSize = bigPyroBlast.getPreferredSize();
        bigPyroBlast.setSize(bigPyroBlastSize.width, bigPyroBlastSize.height);
        bigPyroBlast.setLocation(45, 150);
        add(bigPyroBlast);
        bigPyroBlast.setVisible(false);

        Dimension swordIconSize = swordIcon.getPreferredSize();
        swordIcon.setSize(swordIconSize.width, swordIconSize.height);
        swordIcon.setLocation(125, 300);
        add(swordIcon);
        swordIcon.setVisible(false);


        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    //simons del
    public void spellMenuStartup() {

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

    private void spellMenuActive() {
        attackButton.setVisible(false);
        blockButton.setVisible(false);
        itemButton.setVisible(false);
        skillButton.setVisible(false);
        endTurnButton.setVisible(false);

        skill1Button.setVisible(true);
        skill2Button.setVisible(true);
        skill3Button.setVisible(true);
        skill4Button.setVisible(true);
        returnButton.setVisible(true);

        //warrior
        if (turns == 1) {
            skill1Button.setText("Charge");
            skill2Button.setText("Slam");
            skill3Button.setText("Battlecry");
            skill4Button.setText("Demoralize");
        }
        //ranger
        if (turns == 2) {
            skill1Button.setText("Volley");
            skill2Button.setText("");
            skill3Button.setText("");
            skill4Button.setText("");
        }
        //mage
        if (turns == 3) {
            skill1Button.setText("Fireball");
            skill2Button.setText("TBA");
            skill3Button.setText("Flamestrike");
            skill4Button.setText("Pyroblast");
        }
        //healer
        if (turns == 4) {
            skill1Button.setText("Heal");
            skill2Button.setText("Deal");
            skill3Button.setText("Feel");
            skill4Button.setText("Real");
        }
    }

    private void spellMenuInactive() {
        attackButton.setVisible(true);
        blockButton.setVisible(true);
        itemButton.setVisible(true);
        skillButton.setVisible(true);
        endTurnButton.setVisible(true);

        skill1Button.setVisible(false);
        skill2Button.setVisible(false);
        skill3Button.setVisible(false);
        skill4Button.setVisible(false);
        returnButton.setVisible(false);
    }

    private void skill1() {
        if (turns == 1) {
            charge.start();
        }
        if (turns == 2) {
            volley.start();
        }
        if (turns == 3) {
            pyroBlastX = 90;
            pyroblastY = 300;
            fireBall.start();
        }
        if (turns == 4) {

        }
    }

    private void skill2() {
        if (turns == 1) {
            dunk.start();
        }
        if (turns == 2) {

        }
        if (turns == 3) {

        }
        if (turns == 4) {

        }
    }

    private void skill3() {
        if (turns == 1) {
            battlecry.start();
        }
        if (turns == 2) {

        }
        if (turns == 3) {
            flameStrike.start();
        }
        if (turns == 4) {

        }
    }

    private void skill4() {
        if (turns == 1) {
            demoshout.start();
        }
        if (turns == 2) {

        }
        if (turns == 3) {
            pyroBlast.start();
        }
        if (turns == 4) {

        }
    }


    //When you press "end turn" button.
    private void startNewTurn() {
        turns++;

        //Warrior's turn
        if (turns == 1 && warriorCurrentHp > 0) {
            warriorEnergyInt += 5; //Get energy
            currentEnergy = warriorEnergyInt; //Update energy.
            warriorBlock = w.combinedBlock; //Update block, reset extra block.

            //Energy cant go over 10.
            if (warriorEnergyInt > 10) {
                warriorEnergyInt = 10;
            }
            //Update labels.
            whosTurn.setText("Warrior's turn");
            playersHp.setText("Hp: " + warriorCurrentHp);
            energy.setText("Energy: " + warriorEnergyInt);
            block.setText("Block: " + warriorBlock);
        }
        //If warrior is dead, skip.
        if (turns == 1 && warriorCurrentHp < 1) {
            turns = 2;
        }
        //Ranger's turn
        if (turns == 2 && rangerCurrentHp > 0) {
            rangerEnergyInt += 5;
            currentEnergy = rangerEnergyInt;
            rangerBlock = r.combinedBlock;

            if (rangerEnergyInt > 10) {
                rangerEnergyInt = 10;
            }
            whosTurn.setText("Ranger's turn");
            playersHp.setText("Hp: " + rangerCurrentHp);
            energy.setText("Energy: " + rangerEnergyInt);
            block.setText("Block: " + rangerBlock);
        }
        //If ranger is dead, skip.
        if (turns == 2 && rangerCurrentHp < 1) {
            turns = 3;
        }
        //Mage's turn
        if (turns == 3 && mageCurrentHp > 0) {
            mageEnergyInt += 5;
            currentEnergy = mageEnergyInt;
            mageBlock = m.combinedBlock;

            if (mageEnergyInt > 10) {
                mageEnergyInt = 10;
            }
            whosTurn.setText("Mage's turn");
            playersHp.setText("Hp: " + mageCurrentHp);
            energy.setText("Energy: " + mageEnergyInt);
            block.setText("Block: " + mageBlock);
        }
        //If mage is dead, skip.
        if (turns == 3 && mageCurrentHp < 1) {
            turns = 4;
        }
        //Healer's turn
        if (turns == 4 && healerCurrentHp > 0) {
            healerEnergyInt += 5;
            currentEnergy = healerEnergyInt;
            healerBlock = h.combinedBlock;

            if (healerEnergyInt > 10) {
                healerEnergyInt = 10;
            }
            whosTurn.setText("Healer's turn");
            playersHp.setText("Hp: " + healerCurrentHp);
            energy.setText("Energy: " + healerEnergyInt);
            block.setText("Block: " + healerBlock);

        }
        //If healer is dead, skip.
        if (turns == 4 && healerCurrentHp < 1) {
            turns = 5;
        }
        //  ***ENEMIES TURN***
        if (turns == 5) {
            enemyTurnTimer.start();
        }
    }

    //When player press block
    private void blockPressed() {

        //If its warrior's turn and player has 2 or more energy.
        if (turns == 1 && warriorEnergyInt > 1) {
            warriorEnergyInt = warriorEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            warriorBlock += 5;
            energy.setText("Energy: " + warriorEnergyInt);
            block.setText("Block: " + warriorBlock);
        }
        //If its ranger's turn and player has 2 or more energy.
        else if (turns == 2 && rangerEnergyInt > 1) {
            rangerEnergyInt = rangerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            energy.setText("Energy: " + rangerEnergyInt);
            block.setText("Block: " + warriorBlock);
        }
        //If its mage's turn and player has 2 or more energy.
        else if (turns == 3 && mageEnergyInt > 1) {
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            energy.setText("Energy: " + mageEnergyInt);
            block.setText("Block: " + warriorBlock);
        }
        //If its healer's turn and player has 2 or more energy.
        else if (turns == 4 && healerEnergyInt > 1) {
            healerEnergyInt = healerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            energy.setText("Energy: " + healerEnergyInt);
            block.setText("Block: " + warriorBlock);
        }
    }

    //When you press the "attack button".
    public boolean attackPressed() {

        //If its warrior's turn and player has 2 or more energy.
        if (turns == 1 && warriorEnergyInt > 1) {
            warriorEnergyInt = warriorEnergyInt - 2; //Energy -2.
            currentEnergy = currentEnergy - 2; // Update currentEnergy.
            energy.setText("Energy: " + warriorEnergyInt); //Update energyLabel
            warriorAttackWolf(); //Warrior deals damage to a random wolf.
            mobDeath(); //Check if enemy died.
            return startLootScreen;
        }
        //If its ranger's turn and player has 2 or more energy.
        else if (turns == 2 && rangerEnergyInt > 1) {
            rangerEnergyInt = rangerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            energy.setText("Energy: " + rangerEnergyInt);
            rangerAttackWolf();
            mobDeath();
            return startLootScreen;
        }
        //If its mage's turn and player has 2 or more energy.
        else if (turns == 3 && mageEnergyInt > 1) {
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            energy.setText("Energy: " + mageEnergyInt);
            mageAttackWolf();
            mobDeath();
            return startLootScreen;
        }
        //If its healer's turn and player has 2 or more energy.
        else if (turns == 4 && healerEnergyInt > 1) {
            healerEnergyInt = healerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            energy.setText("Energy: " + healerEnergyInt);
            healerAttackWolf();
            mobDeath();
            return startLootScreen;
        }
        return false;
    }

    //When warrior press the "attack button".
    private void warriorAttackWolf() {
        //Loop until one of the if-statements are true.
        while (true) {
            //Randomize a number between 1-4.
            int target = (int) (Math.random() * 4) + 1;
            tackle.start();

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolf1Int > 0) {
                wolf1Int = wolf1Int - warriorDamage;//Wolf take damage equals to warriors damage.
                wolf1Hp.setText("Wolf 1: " + wolf1Int);//Update wolf 1 hp label.
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolf2Int > 0) {
                wolf2Int = wolf2Int - warriorDamage;
                wolf2Hp.setText("Wolf 2: " + wolf2Int);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolf3Int > 0) {
                wolf3Int = wolf3Int - warriorDamage;
                wolf3Hp.setText("Wolf 3: " + wolf3Int);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolf4Int > 0) {
                wolf4Int = wolf4Int - warriorDamage;
                wolf4Hp.setText("Wolf 4: " + wolf4Int);
                break;
            }
        }
    }

    //When mage press the "attack button".
    private void mageAttackWolf() {
        //Loop until one of the if-statements are true.
        while (true) {
            //Randomize a number between 1-4.
            int target = (int) (Math.random() * 4) + 1;

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolf1Int > 0) {
                wolf1Int = wolf1Int - mageDamage;//Wolf take damage equals to mage's damage.
                wolf1Hp.setText("Wolf 1: " + wolf1Int);
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolf2Int > 0) {
                wolf2Int = wolf2Int - mageDamage;
                wolf2Hp.setText("Wolf 2: " + wolf2Int);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolf3Int > 0) {
                wolf3Int = wolf3Int - mageDamage;
                wolf3Hp.setText("Wolf 3: " + wolf3Int);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolf4Int > 0) {
                wolf4Int = wolf4Int - mageDamage;
                wolf4Hp.setText("Wolf 4: " + wolf4Int);
                break;
            }
        }
    }

    //When ranger press the "attack button".
    private void rangerAttackWolf() {
        //Loop until one of the if-statements are true.
        while (true) {
            //Randomize a number between 1-4.
            int target = (int) (Math.random() * 4) + 1;
            shoot.start();

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolf1Int > 0) {
                wolf1Int = wolf1Int - rangerDamage;//Wolf take damage equals to rangers damage.
                wolf1Hp.setText("Wolf 1: " + wolf1Int);
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolf2Int > 0) {
                wolf2Int = wolf2Int - rangerDamage;
                wolf2Hp.setText("Wolf 2: " + wolf2Int);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolf3Int > 0) {
                wolf3Int = wolf3Int - rangerDamage;
                wolf3Hp.setText("Wolf 3: " + wolf3Int);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolf4Int > 0) {
                wolf4Int = wolf4Int - rangerDamage;
                wolf4Hp.setText("Wolf 4: " + wolf4Int);
                break;
            }
        }
    }

    //When healer press the "attack button".
    private void healerAttackWolf() {
        //Loop until one of the if-statements are true.
        while (true) {
            int target = (int) (Math.random() * 4) + 1;

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolf1Int > 0) {
                wolf1Int = wolf1Int - healerDamage;//Wolf take damage equals to healers damage.
                wolf1Hp.setText("Wolf 1: " + wolf1Int);
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolf2Int > 0) {
                wolf2Int = wolf2Int - healerDamage;
                wolf2Hp.setText("Wolf 2: " + wolf2Int);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolf3Int > 0) {
                wolf3Int = wolf3Int - healerDamage;
                wolf3Hp.setText("Wolf 3: " + wolf3Int);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolf4Int > 0) {
                wolf4Int = wolf4Int - healerDamage;
                wolf4Hp.setText("Wolf 4: " + wolf4Int);
                break;
            }
        }
    }

    //Checks if all of the enemies or party-members are dead.
    public void fightIsOver() {
        //All of the wolves are dead. Victory!
            System.out.println("wolfs are dead");
            startLootScreen = true;
        //MusicPick.musicStop();
    }
            //The whole party is dead. Game over!
    public void isFightLost() {
        if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
            new LoseScreen();
            dispose();
        }
    }
            //If none of these are true, nothing happens and the fight goes on.

    //When the wolf attacks.
    private void wolfAttack() {
        target = (int) (Math.random() * 4); //Random target, 0-3.
        int wolfDamage = (int) (Math.random() * 10) + 15;//Generate random damage, 15-25.
        takeDamage.start();

        //Loops until it reaches an alive party-member.
        while (true) {

            //Warrior, Target 2.
            if (target == 0) {
                //If warrior is dead, target=1.
                if (warriorCurrentHp < 1) {
                    target = 1;
                }
                //If warrior is alive.
                if (warriorCurrentHp > 0) {
                    wolfDamage = wolfDamage - warriorBlock; //Warrior take damage equal to wolf damage.
                    warriorCurrentHp = warriorCurrentHp - wolfDamage; //Update warrior hp.
                    player1Hp.setText("Warrior: " + warriorCurrentHp); //Update hp Label.
                    break;
                }
            }
            //Mage, Target 1.
            if (target == 1) {
                //If mage is dead, target=2.
                if (mageCurrentHp < 1) {
                    target = 2;
                }
                //If mage is alive.
                if (mageCurrentHp > 0) {
                    wolfDamage = wolfDamage - mageBlock;
                    mageCurrentHp = mageCurrentHp - wolfDamage;
                    player2Hp.setText("Mage:    " + mageCurrentHp);
                    break;
                }
            }
            //Ranger, target 2.
            if (target == 2) {
                //If ranger is dead, target=3.
                if (rangerCurrentHp < 1) {
                    target = 3;
                }
                //If ranger is alive.
                if (rangerCurrentHp > 0) {
                    wolfDamage = wolfDamage - rangerBlock;
                    rangerCurrentHp = rangerCurrentHp - wolfDamage;
                    player3Hp.setText("Ranger:  " + rangerCurrentHp);
                    break;
                }
            }
            //Healer, target3.
            if (target == 3) {
                //If healer is dead, target=0.
                if (healerCurrentHp < 1) {
                    target = 0;
                }
                //If healer is alive.
                if (healerCurrentHp > 0) {
                    wolfDamage = wolfDamage - healerBlock;
                    healerCurrentHp = healerCurrentHp - wolfDamage;
                    player4Hp.setText("Healer:   " + healerCurrentHp);
                    break;
                }
            }
        }
    }

    //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
    public boolean mobDeath() {
         if (wolf1Int <= 0) {
            wolf1Hp.setText("Wolf 1: 0");
            wolf1.setVisible(false);
        }
         if (wolf2Int <= 0) {
            wolf2Hp.setText("Wolf 2: 0");
            wolf2.setVisible(false);
        }
         if (wolf3Int <= 0) {
            wolf3Hp.setText("Wolf 3: 0");
            wolf3.setVisible(false);
        }
         if (wolf4Int <= 0) {
            wolf4Hp.setText("Wolf 4: 0");
            wolf4.setVisible(false);
        }
        if (wolf1Int <= 0 && wolf2Int <= 0 && wolf3Int <= 0 && wolf4Int <=0) {
            fightIsOver();
            return startLootScreen;
        }
        return false;
    }

    //Checks if any party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void partyDeath() {

        if (warriorCurrentHp <= 0) {
            warriorCurrentHp = 0;
            player1Hp.setText("Warrior: " + warriorCurrentHp);
            warrior.setVisible(false);
        }
        if (mageCurrentHp <= 0) {
            mageCurrentHp = 0;
            player2Hp.setText("Mage:    " + mageCurrentHp);
            mage.setVisible(false);
        }
        if (rangerCurrentHp <= 0) {
            rangerCurrentHp = 0;
            player3Hp.setText("Ranger:  " + rangerCurrentHp);
            ranger.setVisible(false);
        }
        if (healerCurrentHp <= 0) {
            healerCurrentHp = 0;
            player4Hp.setText("Healer:  " + healerCurrentHp);
            healer.setVisible(false);
        }
    }

    //Get the effect from potions.
    private void usePotion(int potion) {

        //Warrior
        if (turns == 1) {
            //If potion 1 "minor healing potion" is pressed.
            if (potion == 1) {
                //If player own that potion.
                if (inv.ownedMinorHealingPotion > 0) {
                    warriorCurrentHp += inv.minorHealingPotion; //Heal warrior equals to the potions heal.
                    playersHp.setText("Hp: " + warriorCurrentHp); //Update Warrior's hp Label.
                    player1Hp.setText("Warrior: " + warriorCurrentHp); // Update currentPlayer Hp label.
                    inv.ownedMinorHealingPotion--; //Delete one potion.
                    potion1Label.setText("" + inv.ownedMinorHealingPotion); //Update ownedPotion Label.
                }
            } else if (potion == 2) {
                if (inv.ownedLesserHealingPotion > 0) {
                    warriorCurrentHp += inv.lesserHealingPotion;
                    playersHp.setText("Hp: " + warriorCurrentHp);
                    player1Hp.setText("Warrior: " + warriorCurrentHp);
                    inv.ownedLesserHealingPotion--;
                    potion2Label.setText("" + inv.ownedLesserHealingPotion);
                }
            } else if (potion == 3) {
                if (inv.ownedMajorHealingPotion > 0) {
                    warriorCurrentHp += inv.majorHealingPotion;
                    playersHp.setText("Hp: " + warriorCurrentHp);
                    player1Hp.setText("Warrior: " + warriorCurrentHp);
                    inv.ownedMajorHealingPotion--;
                    potion3Label.setText("" + inv.ownedMajorHealingPotion);
                }
            } else if (potion == 4) {
                if (inv.ownedMinorBlockPotion > 0) {
                    warriorBlock += inv.minorBlockPotion;
                    block.setText("Block: " + warriorBlock);
                    inv.ownedMinorBlockPotion--;
                    potion4Label.setText("" + inv.ownedMinorBlockPotion);
                }
            } else if (potion == 5) {
                if (inv.ownedLesserBlockPotion > 0) {
                    warriorBlock += inv.minorBlockPotion;
                    block.setText("Block: " + warriorBlock);
                    inv.ownedLesserBlockPotion--;
                    potion5Label.setText("" + inv.ownedLesserBlockPotion);
                }
            } else if (potion == 6) {
                if (inv.ownedMajorBlockPotion > 0) {
                    warriorBlock += inv.majorBlockPotion;
                    block.setText("Block: " + warriorBlock);
                    inv.ownedMajorBlockPotion--;
                    potion6Label.setText("" + inv.ownedMajorBlockPotion);
                }
            } else if (potion == 7) {
                if (inv.ownedMinorEnergyPotion > 0) {
                    warriorEnergyInt += inv.minorEnergyPotion;
                    energy.setText("Energy: " + warriorEnergyInt);
                    inv.ownedMinorEnergyPotion--;
                    potion7Label.setText("" + inv.ownedMinorEnergyPotion);
                }
            } else if (potion == 8) {
                if (inv.ownedLesserEnergyPotion > 0) {
                    warriorCurrentHp += inv.lesserEnergyPotion;
                    energy.setText("Energy: " + warriorEnergyInt);
                    inv.ownedLesserEnergyPotion--;
                    potion8Label.setText("" + inv.ownedLesserEnergyPotion);
                }
            } else if (potion == 9) {
                if (inv.ownedMajorEnergyPotion > 0) {
                    warriorCurrentHp += inv.majorEnergyPotion;
                    energy.setText("Energy: " + warriorEnergyInt);
                    inv.ownedMajorEnergyPotion--;
                    potion9Label.setText("" + inv.ownedMajorEnergyPotion);
                }
            }
            if (potion == 10) {
                if (inv.ownedMinorStrengthPotion > 0) {
                    warriorDamage += inv.minorStrengthPotion;
                    inv.ownedMinorStrengthPotion--;
                    potion10Label.setText("" + inv.ownedMinorStrengthPotion);
                }
            }
            if (potion == 11) {
                if (inv.ownedLesserStrengthPotion > 0) {
                    warriorDamage += inv.lesserStrengthPotion;
                    inv.ownedLesserStrengthPotion--;
                    potion11Label.setText("" + inv.ownedLesserStrengthPotion);
                }
            }
            if (potion == 12) {
                if (inv.ownedMajorStrengthPotion > 0) {
                    warriorDamage += inv.majorStrengthPotion;
                    inv.ownedMajorStrengthPotion--;
                    potion12Label.setText("" + inv.ownedMajorStrengthPotion);
                }
            }
        }
        //Ranger
        else if (turns == 2) {
            if (potion == 1) {
                if (inv.ownedMinorHealingPotion > 0) {
                    rangerCurrentHp += inv.minorHealingPotion;
                    playersHp.setText("Hp: " + rangerCurrentHp);
                    player2Hp.setText("Ranger: " + rangerCurrentHp);
                    inv.ownedMinorHealingPotion--;
                    potion1Label.setText("" + inv.ownedMinorHealingPotion);
                }
            } else if (potion == 2) {
                if (inv.ownedLesserHealingPotion > 0) {
                    rangerCurrentHp += inv.lesserHealingPotion;
                    playersHp.setText("Hp: " + rangerCurrentHp);
                    player2Hp.setText("Ranger: " + rangerCurrentHp);
                    inv.ownedLesserHealingPotion--;
                    potion2Label.setText("" + inv.ownedLesserHealingPotion);
                }
            } else if (potion == 3) {
                if (inv.ownedMajorHealingPotion > 0) {
                    rangerCurrentHp += inv.majorHealingPotion;
                    playersHp.setText("Hp: " + rangerCurrentHp);
                    player2Hp.setText("Ranger: " + rangerCurrentHp);
                    inv.ownedMajorHealingPotion--;
                    potion3Label.setText("" + inv.ownedMajorHealingPotion);
                }
            } else if (potion == 4) {
                if (inv.ownedMinorBlockPotion > 0) {
                    rangerBlock += inv.minorBlockPotion;
                    block.setText("Block: " + rangerBlock);
                    inv.ownedMinorBlockPotion--;
                    potion4Label.setText("" + inv.ownedMinorBlockPotion);
                }
            } else if (potion == 5) {
                if (inv.ownedLesserBlockPotion > 0) {
                    rangerBlock += inv.minorBlockPotion;
                    block.setText("Block: " + rangerBlock);
                    inv.ownedLesserBlockPotion--;
                    potion5Label.setText("" + inv.ownedLesserBlockPotion);
                }
            } else if (potion == 6) {
                if (inv.ownedMajorBlockPotion > 0) {
                    rangerBlock += inv.majorBlockPotion;
                    block.setText("Block: " + rangerBlock);
                    inv.ownedMajorBlockPotion--;
                    potion6Label.setText("" + inv.ownedMajorBlockPotion);
                }
            }
            if (potion == 7) {
                if (inv.ownedMinorEnergyPotion > 0) {
                    rangerEnergyInt += inv.minorEnergyPotion;
                    energy.setText("Energy: " + rangerEnergyInt);
                    inv.ownedMinorEnergyPotion--;
                    potion7Label.setText("" + inv.ownedMinorEnergyPotion);
                }
            } else if (potion == 8) {
                if (inv.ownedLesserEnergyPotion > 0) {
                    rangerEnergyInt += inv.lesserEnergyPotion;
                    energy.setText("Energy: " + rangerEnergyInt);
                    inv.ownedLesserEnergyPotion--;
                    potion8Label.setText("" + inv.ownedLesserEnergyPotion);
                }
            } else if (potion == 9) {
                if (inv.ownedMajorEnergyPotion > 0) {
                    rangerEnergyInt += inv.majorEnergyPotion;
                    energy.setText("Energy: " + rangerEnergyInt);
                    inv.ownedMajorEnergyPotion--;
                    potion9Label.setText("" + inv.ownedMajorEnergyPotion);
                }
            } else if (potion == 10) {
                if (inv.ownedMinorStrengthPotion > 0) {
                    rangerDamage += inv.minorStrengthPotion;
                    inv.ownedMinorStrengthPotion--;
                    potion10Label.setText("" + inv.ownedMinorStrengthPotion);
                }
            } else if (potion == 11) {
                if (inv.ownedLesserStrengthPotion > 0) {
                    rangerDamage += inv.lesserStrengthPotion;
                    inv.ownedLesserStrengthPotion--;
                    potion11Label.setText("" + inv.ownedLesserStrengthPotion);
                }
            } else if (potion == 12) {
                if (inv.ownedMajorStrengthPotion > 0) {
                    rangerDamage += inv.majorStrengthPotion;
                    inv.ownedMajorStrengthPotion--;
                    potion12Label.setText("" + inv.ownedMajorStrengthPotion);
                }
            }
        }
        //Mage
        else if (turns == 3) {
            if (potion == 1) {
                if (inv.ownedMinorHealingPotion > 0) {
                    mageCurrentHp += inv.minorHealingPotion;
                    playersHp.setText("Hp: " + mageCurrentHp);
                    player3Hp.setText("Mage: " + mageCurrentHp);
                    inv.ownedMinorHealingPotion--;
                    potion1Label.setText("" + inv.ownedMinorHealingPotion);
                }
            } else if (potion == 2) {
                if (inv.ownedLesserHealingPotion > 0) {
                    mageCurrentHp += inv.lesserHealingPotion;
                    playersHp.setText("Hp: " + mageCurrentHp);
                    player3Hp.setText("Mage: " + mageCurrentHp);
                    inv.ownedLesserHealingPotion--;
                    potion2Label.setText("" + inv.ownedLesserHealingPotion);
                }
            } else if (potion == 3) {
                if (inv.ownedMajorHealingPotion > 0) {
                    rangerCurrentHp += inv.majorHealingPotion;
                    playersHp.setText("Hp: " + mageCurrentHp);
                    player3Hp.setText("Mage: " + mageCurrentHp);
                    potion3Label.setText("" + inv.ownedMajorHealingPotion);
                    inv.ownedMajorHealingPotion--;
                }
            } else if (potion == 4) {
                if (inv.ownedMinorBlockPotion > 0) {
                    mageBlock += inv.minorBlockPotion;
                    block.setText("Block: " + mageBlock);
                    inv.ownedMinorBlockPotion--;
                    potion4Label.setText("" + inv.ownedMinorBlockPotion);
                }
            } else if (potion == 5) {
                if (inv.ownedLesserBlockPotion > 0) {
                    mageBlock += inv.minorBlockPotion;
                    block.setText("Block: " + mageBlock);
                    inv.ownedLesserBlockPotion--;
                    potion5Label.setText("" + inv.ownedLesserBlockPotion);
                }
            } else if (potion == 6) {
                if (inv.ownedMajorBlockPotion > 0) {
                    mageBlock += inv.majorBlockPotion;
                    block.setText("Block: " + mageBlock);
                    inv.ownedMajorBlockPotion--;
                    potion6Label.setText("" + inv.ownedMajorBlockPotion);
                }
            }
            if (potion == 7) {
                if (inv.ownedMinorEnergyPotion > 0) {
                    mageEnergyInt += inv.minorEnergyPotion;
                    energy.setText("Energy: " + mageEnergyInt);
                    inv.ownedMinorEnergyPotion--;
                    potion7Label.setText("" + inv.ownedMinorEnergyPotion);
                }
            } else if (potion == 8) {
                if (inv.ownedLesserEnergyPotion > 0) {
                    mageEnergyInt += inv.lesserEnergyPotion;
                    energy.setText("Energy: " + mageEnergyInt);
                    inv.ownedLesserEnergyPotion--;
                    potion8Label.setText("" + inv.ownedLesserEnergyPotion);
                }
            } else if (potion == 9) {
                if (inv.ownedMajorEnergyPotion > 0) {
                    mageEnergyInt += inv.majorEnergyPotion;
                    energy.setText("Energy: " + mageEnergyInt);
                    inv.ownedMajorEnergyPotion--;
                    potion9Label.setText("" + inv.ownedMajorEnergyPotion);
                }
            } else if (potion == 10) {
                if (inv.ownedMinorStrengthPotion > 0) {
                    mageDamage += inv.minorStrengthPotion;
                    inv.ownedMinorStrengthPotion--;
                    potion10Label.setText("" + inv.ownedMinorStrengthPotion);
                }
            } else if (potion == 11) {
                if (inv.ownedLesserStrengthPotion > 0) {
                    mageDamage += inv.lesserStrengthPotion;
                    inv.ownedLesserStrengthPotion--;
                    potion11Label.setText("" + inv.ownedLesserStrengthPotion);
                }
            } else if (potion == 12) {
                if (inv.ownedMajorStrengthPotion > 0) {
                    mageDamage += inv.majorStrengthPotion;
                    inv.ownedMajorStrengthPotion--;
                    potion12Label.setText("" + inv.ownedMajorStrengthPotion);
                }
            }
        }
        //Healer
        else if (turns == 4) {
            if (potion == 1) {
                if (inv.ownedMinorHealingPotion > 0) {
                    healerCurrentHp += inv.minorHealingPotion;
                    playersHp.setText("Hp: " + healerCurrentHp);
                    player4Hp.setText("Mage: " + healerCurrentHp);
                    inv.ownedMinorHealingPotion--;
                    potion1Label.setText("" + inv.ownedMinorHealingPotion);
                }
            } else if (potion == 2) {
                if (inv.ownedLesserHealingPotion > 0) {
                    healerCurrentHp += inv.lesserHealingPotion;
                    playersHp.setText("Hp: " + healerCurrentHp);
                    player4Hp.setText("Mage: " + healerCurrentHp);
                    inv.ownedLesserHealingPotion--;
                    potion2Label.setText("" + inv.ownedLesserHealingPotion);
                }
            } else if (potion == 3) {
                if (inv.ownedMajorHealingPotion > 0) {
                    healerCurrentHp += inv.majorHealingPotion;
                    playersHp.setText("Hp: " + healerCurrentHp);
                    player4Hp.setText("Mage: " + healerCurrentHp);
                    inv.ownedMajorHealingPotion--;
                    potion3Label.setText("" + inv.ownedMajorHealingPotion);
                }
            } else if (potion == 4) {
                if (inv.ownedMinorBlockPotion > 0) {
                    healerBlock += inv.minorBlockPotion;
                    block.setText("Block: " + healerBlock);
                    inv.ownedMinorBlockPotion--;
                    potion4Label.setText("" + inv.ownedMinorBlockPotion);
                }
            } else if (potion == 5) {
                if (inv.ownedLesserBlockPotion > 0) {
                    healerBlock += inv.minorBlockPotion;
                    block.setText("Block: " + healerBlock);
                    inv.ownedLesserBlockPotion--;
                    potion5Label.setText("" + inv.ownedLesserBlockPotion);
                }
            } else if (potion == 6) {
                if (inv.ownedMajorBlockPotion > 0) {
                    healerBlock += inv.majorBlockPotion;
                    block.setText("Block: " + healerBlock);
                    inv.ownedMajorBlockPotion--;
                    potion6Label.setText("" + inv.ownedMajorBlockPotion);
                }
            }
            if (potion == 7) {
                if (inv.ownedMinorEnergyPotion > 0) {
                    healerEnergyInt += inv.minorEnergyPotion;
                    energy.setText("Energy: " + healerEnergyInt);
                    inv.ownedMinorEnergyPotion--;
                    potion7Label.setText("" + inv.ownedMinorEnergyPotion);
                }
            } else if (potion == 8) {
                if (inv.ownedLesserEnergyPotion > 0) {
                    healerEnergyInt += inv.lesserEnergyPotion;
                    energy.setText("Energy: " + healerEnergyInt);
                    inv.ownedLesserEnergyPotion--;
                    potion8Label.setText("" + inv.ownedLesserEnergyPotion);
                }
            } else if (potion == 9) {
                if (inv.ownedMajorEnergyPotion > 0) {
                    healerEnergyInt += inv.majorEnergyPotion;
                    energy.setText("Energy: " + healerEnergyInt);
                    inv.ownedMajorEnergyPotion--;
                    potion9Label.setText("" + inv.ownedMajorEnergyPotion);
                }
            } else if (potion == 10) {
                if (inv.ownedMinorStrengthPotion > 0) {
                    healerDamage += inv.minorStrengthPotion;
                    inv.ownedMinorStrengthPotion--;
                    potion10Label.setText("" + inv.ownedMinorStrengthPotion);
                }
            } else if (potion == 11) {
                if (inv.ownedLesserStrengthPotion > 0) {
                    healerDamage += inv.lesserStrengthPotion;
                    inv.ownedLesserStrengthPotion--;
                    potion11Label.setText("" + inv.ownedLesserStrengthPotion);
                }
            } else if (potion == 12) {
                if (inv.ownedMajorStrengthPotion > 0) {
                    healerDamage += inv.majorStrengthPotion;
                    inv.ownedMajorStrengthPotion--;
                    potion12Label.setText("" + inv.ownedMajorStrengthPotion);
                }
            }
        }
    }

    //This method triggers when you press the "Item button". It opens up another JFrame that covers the hud.
    private void itemPressed() {

        //Frame settings
        JFrame inventory = new JFrame();
        inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventory.setLayout(null);
        inventory.setSize(1920, 300);
        inventory.setTitle("Inventory");
        inventory.setLocation(0, 538);

        getInventory();

        //Background
        ImageIcon backgroundInventory = new ImageIcon("white.jpg");
        inventory.setContentPane(new JLabel(backgroundInventory));

        //Add all labels//buttons.
        inventory.add(exitInventory);
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

        //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
        potion1.addActionListener(e -> usePotion(1));
        potion2.addActionListener(e -> usePotion(2));
        potion3.addActionListener(e -> usePotion(3));
        potion4.addActionListener(e -> usePotion(4));
        potion5.addActionListener(e -> usePotion(5));
        potion6.addActionListener(e -> usePotion(6));
        potion7.addActionListener(e -> usePotion(7));
        potion8.addActionListener(e -> usePotion(8));
        potion9.addActionListener(e -> usePotion(9));
        potion10.addActionListener(e -> usePotion(10));
        potion11.addActionListener(e -> usePotion(11));
        potion12.addActionListener(e -> usePotion(12));

        //Dispose the item frame.
        exitInventory.addActionListener(e -> inventory.dispose());

        inventory.setUndecorated(true);
        inventory.setVisible(true);
    }

    //Set all "stats" for each button and label to the Item Frame.
    private void getInventory() {
        //Exit button
        exitInventory = new JButton("Exit inventory");
        exitInventory.setBounds(1010, 60, 250, 80);
        exitInventory.setFont(pixelMplus.deriveFont(30f));
        exitInventory.setBackground(Color.white);
        exitInventory.setBorder(null); //Remove border around button
        exitInventory.setFocusPainted(false);//Remove border around text in button

        //Minor Health Potion
        potion1 = new JButton(inv.minorHealthGif);
        potion1.setBounds(50, 60, 41, 62);
        potion1.setBackground(Color.white);
        potion1.setBorder(null);
        potion1.setFocusPainted(false);

        //Lesser Health Potion
        potion2 = new JButton(inv.lesserHealthGif);
        potion2.setBounds(100, 60, 46, 62);
        potion2.setBackground(Color.white);
        potion2.setBorder(null);
        potion2.setFocusPainted(false);

        //Major Health Potion
        potion3 = new JButton(inv.majorHealthGif);
        potion3.setBounds(160, 60, 55, 64);
        potion3.setBackground(Color.white);
        potion3.setBorder(null);
        potion3.setFocusPainted(false);

        //Minor Block Potion
        potion4 = new JButton(inv.minorBlockGif);
        potion4.setBounds(300, 60, 42, 63);
        potion4.setBackground(Color.white);
        potion4.setBorder(null);
        potion4.setFocusPainted(false);

        //Lesser Block Potion
        potion5 = new JButton(inv.lesserBlockGif);
        potion5.setBounds(350, 60, 47, 63);
        potion5.setBackground(Color.white);
        potion5.setBorder(null);
        potion5.setFocusPainted(false);

        //Major Block Potion
        potion6 = new JButton(inv.majorBlockGif);
        potion6.setBounds(410, 60, 59, 64);
        potion6.setBackground(Color.white);
        potion6.setBorder(null);
        potion6.setFocusPainted(false);

        //Minor Energy Potion
        potion7 = new JButton(inv.minorEnergyGif);
        potion7.setBounds(550, 60, 40, 63);
        potion7.setBackground(Color.white);
        potion7.setBorder(null);
        potion7.setFocusPainted(false);

        //Lesser Energy Potion
        potion8 = new JButton(inv.lesserEnergyGif);
        potion8.setBounds(600, 60, 46, 63);
        potion8.setBackground(Color.white);
        potion8.setBorder(null);
        potion8.setFocusPainted(false);

        //Major Energy Potion
        potion9 = new JButton(inv.majorEnergyGif);
        potion9.setBounds(660, 60, 59, 64);
        potion9.setBackground(Color.white);
        potion9.setBorder(null);
        potion9.setFocusPainted(false);

        //Minor Strength Potion
        potion10 = new JButton(inv.minorStrGif);
        potion10.setBounds(800, 60, 38, 63);
        potion10.setBackground(Color.white);
        potion10.setBorder(null);
        potion10.setFocusPainted(false);

        //Lesser Energy Potion
        potion11 = new JButton(inv.lesserStrGif);
        potion11.setBounds(850, 60, 42, 63);
        potion11.setBackground(Color.white);
        potion11.setBorder(null);
        potion11.setFocusPainted(false);

        //Major Energy Potion
        potion12 = new JButton(inv.majorStrGif);
        potion12.setBounds(900, 60, 53, 64);
        potion12.setBackground(Color.white);
        potion12.setBorder(null);
        potion12.setFocusPainted(false);

        //Labels
        inventoryHealth = new JLabel("Health");
        inventoryHealth.setFont(pixelMplus.deriveFont(30f));
        inventoryHealth.setForeground(Color.black);
        Dimension inventoryHealthSize = inventoryHealth.getPreferredSize();
        inventoryHealth.setBounds(90, 25, inventoryHealthSize.width, inventoryHealthSize.height);

        inventoryStr = new JLabel("Strength");
        inventoryStr.setFont(pixelMplus.deriveFont(30f));
        inventoryStr.setForeground(Color.black);
        Dimension inventoryStrSize = inventoryStr.getPreferredSize();
        inventoryStr.setBounds(815, 25, inventoryStrSize.width, inventoryStrSize.height);

        inventoryEnergy = new JLabel("Energy");
        inventoryEnergy.setFont(pixelMplus.deriveFont(30f));
        inventoryEnergy.setForeground(Color.black);
        Dimension inventoryEnergySize = inventoryEnergy.getPreferredSize();
        inventoryEnergy.setBounds(590, 25, inventoryEnergySize.width, inventoryEnergySize.height);

        inventoryBlock = new JLabel("Block");
        inventoryBlock.setFont(pixelMplus.deriveFont(30f));
        inventoryBlock.setForeground(Color.black);
        Dimension inventoryBlockSize = inventoryBlock.getPreferredSize();
        inventoryBlock.setBounds(345, 25, inventoryBlockSize.width, inventoryBlockSize.height);

        //Potion owned
        potion1Label = new JLabel("" + inv.ownedMinorHealingPotion);
        potion1Label.setFont(pixelMplus.deriveFont(30f));
        potion1Label.setForeground(Color.black);
        Dimension potion1LabelSize = potion1Label.getPreferredSize();
        potion1Label.setBounds(60, 125, potion1LabelSize.width, potion1LabelSize.height);

        potion2Label = new JLabel("" + inv.ownedLesserHealingPotion);
        potion2Label.setFont(pixelMplus.deriveFont(30f));
        potion2Label.setForeground(Color.black);
        Dimension potion2LabelSize = potion2Label.getPreferredSize();
        potion2Label.setBounds(115, 125, potion2LabelSize.width, potion2LabelSize.height);

        potion3Label = new JLabel("" + inv.ownedMajorHealingPotion);
        potion3Label.setFont(pixelMplus.deriveFont(30f));
        potion3Label.setForeground(Color.black);
        Dimension potion3LabelSize = potion3Label.getPreferredSize();
        potion3Label.setBounds(180, 125, potion3LabelSize.width, potion3LabelSize.height);

        potion4Label = new JLabel("" + inv.ownedMinorBlockPotion);
        potion4Label.setFont(pixelMplus.deriveFont(30f));
        potion4Label.setForeground(Color.black);
        Dimension potion4LabelSize = potion4Label.getPreferredSize();
        potion4Label.setBounds(317, 125, potion4LabelSize.width, potion4LabelSize.height);

        potion5Label = new JLabel("" + inv.ownedLesserBlockPotion);
        potion5Label.setFont(pixelMplus.deriveFont(30f));
        potion5Label.setForeground(Color.black);
        Dimension potion5LabelSize = potion5Label.getPreferredSize();
        potion5Label.setBounds(370, 125, potion5LabelSize.width, potion5LabelSize.height);

        potion6Label = new JLabel("" + inv.ownedMajorBlockPotion);
        potion6Label.setFont(pixelMplus.deriveFont(30f));
        potion6Label.setForeground(Color.black);
        Dimension potion6LabelSize = potion6Label.getPreferredSize();
        potion6Label.setBounds(430, 125, potion6LabelSize.width, potion6LabelSize.height);

        potion7Label = new JLabel("" + inv.ownedMinorEnergyPotion);
        potion7Label.setFont(pixelMplus.deriveFont(30f));
        potion7Label.setForeground(Color.black);
        Dimension potion7LabelSize = potion7Label.getPreferredSize();
        potion7Label.setBounds(557, 125, potion7LabelSize.width, potion7LabelSize.height);

        potion8Label = new JLabel("" + inv.ownedLesserBlockPotion);
        potion8Label.setFont(pixelMplus.deriveFont(30f));
        potion8Label.setForeground(Color.black);
        Dimension potion8LabelSize = potion8Label.getPreferredSize();
        potion8Label.setBounds(617, 125, potion8LabelSize.width, potion8LabelSize.height);

        potion9Label = new JLabel("" + inv.ownedMajorBlockPotion);
        potion9Label.setFont(pixelMplus.deriveFont(30f));
        potion9Label.setForeground(Color.black);
        Dimension potion9LabelSize = potion9Label.getPreferredSize();
        potion9Label.setBounds(680, 125, potion9LabelSize.width, potion9LabelSize.height);

        potion10Label = new JLabel("" + inv.ownedMinorStrengthPotion);
        potion10Label.setFont(pixelMplus.deriveFont(30f));
        potion10Label.setForeground(Color.black);
        Dimension potion10LabelSize = potion10Label.getPreferredSize();
        potion10Label.setBounds(817, 125, potion10LabelSize.width, potion10LabelSize.height);

        potion11Label = new JLabel("" + inv.ownedLesserStrengthPotion);
        potion11Label.setFont(pixelMplus.deriveFont(30f));
        potion11Label.setForeground(Color.black);
        Dimension potion11LabelSize = potion11Label.getPreferredSize();
        potion11Label.setBounds(863, 125, potion11LabelSize.width, potion11LabelSize.height);

        potion12Label = new JLabel("" + inv.ownedMajorStrengthPotion);
        potion12Label.setFont(pixelMplus.deriveFont(30f));
        potion12Label.setForeground(Color.black);
        Dimension potion12LabelSize = potion12Label.getPreferredSize();
        potion12Label.setBounds(920, 125, potion12LabelSize.width, potion12LabelSize.height);
    }

    //Set al "stats" for labels.
    private void importLabels() {
        playersHp = new JLabel("Hp: " + warriorCurrentHp);
        playersHp.setFont(pixelMplus.deriveFont(30f));
        playersHp.setForeground(Color.black);
        Dimension playersHpSize = playersHp.getPreferredSize();
        playersHp.setBounds(30, 600, playersHpSize.width, playersHpSize.height);

        wolf1Hp = new JLabel("Wolf 1: " + wolf1Int);
        wolf1Hp.setFont(pixelMplus.deriveFont(30f));
        wolf1Hp.setForeground(Color.black);
        Dimension wolf1HpSize = wolf1Hp.getPreferredSize();
        wolf1Hp.setBounds(660, 560, wolf1HpSize.width, wolf1HpSize.height);

        wolf2Hp = new JLabel("Wolf 2: " + wolf2Int);
        wolf2Hp.setFont(pixelMplus.deriveFont(30f));
        wolf2Hp.setForeground(Color.black);
        Dimension wolf2HpSize = wolf2Hp.getPreferredSize();
        wolf2Hp.setBounds(660, 595, wolf2HpSize.width, wolf2HpSize.height);

        wolf3Hp = new JLabel("Wolf 3: " + wolf3Int);
        wolf3Hp.setFont(pixelMplus.deriveFont(30f));
        wolf3Hp.setForeground(Color.black);
        Dimension wolf3HpSize = wolf3Hp.getPreferredSize();
        wolf3Hp.setBounds(660, 630, wolf3HpSize.width, wolf3HpSize.height);

        wolf4Hp = new JLabel("Wolf 4: " + wolf4Int);
        wolf4Hp.setFont(pixelMplus.deriveFont(30f));
        wolf4Hp.setForeground(Color.black);
        Dimension wolf4HpSize = wolf4Hp.getPreferredSize();
        wolf4Hp.setBounds(660, 665, wolf4HpSize.width, wolf4HpSize.height);

        player1Hp = new JLabel("Warrior: " + warriorCurrentHp);
        player1Hp.setFont(pixelMplus.deriveFont(30f));
        player1Hp.setForeground(Color.black);
        Dimension player1HpSize = player1Hp.getPreferredSize();
        player1Hp.setBounds(430, 560, player1HpSize.width, player1HpSize.height);

        player2Hp = new JLabel("Mage:    " + mageCurrentHp);
        player2Hp.setFont(pixelMplus.deriveFont(30f));
        player2Hp.setForeground(Color.black);
        Dimension player2HpSize = player1Hp.getPreferredSize();
        player2Hp.setBounds(430, 595, player2HpSize.width, player2HpSize.height);

        player3Hp = new JLabel("Ranger:  " + rangerCurrentHp);
        player3Hp.setFont(pixelMplus.deriveFont(30f));
        player3Hp.setForeground(Color.black);
        Dimension player3HpSize = player3Hp.getPreferredSize();
        player3Hp.setBounds(430, 630, player3HpSize.width, player3HpSize.height);

        player4Hp = new JLabel("Healer:  " + healerCurrentHp);
        player4Hp.setFont(pixelMplus.deriveFont(30f));
        player4Hp.setForeground(Color.black);
        Dimension player4HpSize = player4Hp.getPreferredSize();
        player4Hp.setBounds(430, 665, player4HpSize.width, player4HpSize.height);

        energy = new JLabel("Energy: " + warriorEnergyInt);
        energy.setFont(pixelMplus.deriveFont(30f));
        energy.setForeground(Color.black);
        Dimension energySize = energy.getPreferredSize();
        energy.setBounds(30, 640, energySize.width, energySize.height);

        block = new JLabel("Block: " + warriorBlock);
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
    private void importButtons() {
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

    //Get all party-gif's.
    private void importPartyGif() {
        warrior = new JLabel();
        warrior.setIcon(new ImageIcon("warrior.gif"));
        Dimension warriorSize = warrior.getPreferredSize();
        warrior.setBounds(warriorStartX, warriorStartY, warriorSize.width, warriorSize.height);

        healer = new JLabel();
        healer.setIcon(new ImageIcon("healer.gif"));
        Dimension healerSize = healer.getPreferredSize();
        healer.setBounds(healerStartX, healerStartY, healerSize.width, healerSize.height);

        ranger = new JLabel();
        ranger.setIcon(new ImageIcon("ranger.gif"));
        Dimension rangerSize = ranger.getPreferredSize();
        ranger.setBounds(rangerStartX, rangerStartY, rangerSize.width, rangerSize.height);

        mage = new JLabel();
        mage.setIcon(new ImageIcon("mage.gif"));
        Dimension mageSize = mage.getPreferredSize();
        mage.setBounds(mageStartX, mageStartY, mageSize.width, mageSize.height);
    }

    //Get wolf gif.
    private void importWolfGif() {
        wolf1 = new JLabel();
        wolf1.setIcon(new ImageIcon("forestMob.gif"));
        Dimension wolfSize = wolf1.getPreferredSize();
        wolf1.setBounds(850, 320, wolfSize.width, wolfSize.height);

        wolf2 = new JLabel();
        wolf2.setIcon(new ImageIcon("forestMob.gif"));
        wolf2.setBounds(1030, 320, wolfSize.width, wolfSize.height);

        wolf3 = new JLabel();
        wolf3.setIcon(new ImageIcon("forestMob.gif"));
        wolf3.setBounds(900, 400, wolfSize.width, wolfSize.height);

        wolf4 = new JLabel();
        wolf4.setIcon(new ImageIcon("forestMob.gif"));
        wolf4.setBounds(1080, 400, wolfSize.width, wolfSize.height);
    }

    //Imports Pixel Font
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }

    //Add hover effect to buttons.
    private void hoverEffect() {

        //Attack Hover
        attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            //Change button color while hovering depending on your current energy.
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (currentEnergy > 1) {
                    attackButton.setBackground(Color.lightGray);
                }
                if (currentEnergy < 2) {
                    attackButton.setBackground(Color.pink);
                }
            }

            //Change back when not hovering over button
            public void mouseExited(java.awt.event.MouseEvent evt) {
                attackButton.setBackground(Color.white);
            }
        });

        //Block Hover
        blockButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (currentEnergy > 1) {
                    blockButton.setBackground(Color.lightGray);
                }
                if (currentEnergy < 2) {
                    blockButton.setBackground(Color.pink);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                blockButton.setBackground(Color.white);
            }
        });

        //Item Hover
        itemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                itemButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                itemButton.setBackground(Color.white);
            }
        });
        //Skill Hover
        skillButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                skillButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                skillButton.setBackground(Color.white);
            }
        });

        //End turn Hover
        endTurnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                endTurnButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                endTurnButton.setBackground(Color.white);
            }
        });
    }


    //simons animationer


    private Timer charge = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                MusicPick.musicStart("charge", "");
                phase = 1;
            } else if (phase == 1) {
                warriorX += 20;
                warrior.setLocation(warriorX, warriorY);
                if (warriorX > 2000) {
                    phase = 2;
                }
            } else if (phase == 2) {
                warriorX = warriorStartX;
                warriorY = warriorStartY;
                warrior.setLocation(warriorX, warriorY);
                phase = 0;
                charge.stop();
            }
        }
    });

    private Timer volley = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            arrowX += 30;
            volley1.setLocation(arrowX, arrowY);
            volley2.setLocation(arrowX - 200, arrowY);
            volley3.setLocation(arrowX - 400, arrowY);
            if (phase == 0) {
                volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 1;
            } else if (phase == 1 && arrowX > arrowStartX + 300) {
                volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 2;
            } else if (phase == 2 && arrowX > arrowStartX + 600) {
                volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 3;
            } else if (phase == 3 && arrowX > 1000) {
                volley1.setVisible(false);
                phase = 4;
            } else if (phase == 4 && arrowX > 1200) {
                volley2.setVisible(false);
                phase = 5;
            } else if (phase == 5 && arrowX > 1400) {
                volley3.setVisible(false);
                phase = 6;
            }
            if (phase == 6) {
                arrowX = 300;
                volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 7;
            } else if (phase == 7 && arrowX > arrowStartX + 300) {
                volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 8;
            } else if (phase == 8 && arrowX > arrowStartX + 600) {
                volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 9;
            } else if (phase == 9 && arrowX > 1000) {
                volley1.setVisible(false);
                phase = 10;
            } else if (phase == 10 && arrowX > 1200) {
                volley2.setVisible(false);
                phase = 11;
            } else if (phase == 11 && arrowX > 1400) {
                volley3.setVisible(false);
                phase = 12;
                arrowX = 300;
            }
            if (phase == 12) {
                volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 13;
            } else if (phase == 13 && arrowX > arrowStartX + 300) {
                volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 14;
            } else if (phase == 14 && arrowX > arrowStartX + 600) {
                volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 15;
            } else if (phase == 15 && arrowX > 1000) {
                volley1.setVisible(false);
                phase = 16;
            } else if (phase == 16 && arrowX > 1200) {
                volley2.setVisible(false);
                phase = 17;
            } else if (phase == 17 && arrowX > 1400) {
                volley3.setVisible(false);
                phase = 18;
            } else if (phase == 18) {
                arrowX = 270;
                phase = 0;
                volley.stop();
            }
        }
    });

    private Timer flameStrike = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                MusicPick.musicStart("magespell", "");
                phase = 1;
            } else if (phase == 1) {
                mageY -= 3;
                mage.setLocation(mageX, mageY);
                if (mageY < 50) {
                    phase = 2;
                }
            } else if (phase == 2) {
                if (mageY < 50) {
                    mageY = 50;
                    mage.setLocation(mageX, mageY);
                }
                timePast++;
                if (timePast < 100) {
                    if (timePast % 2 == 1) {
                        mageX += 6;
                        mage.setLocation(mageX, mageY);
                        flameStrikeY += 13;
                        flame.setLocation(900, flameStrikeY);
                    } else {
                        mageX -= 6;
                        mage.setLocation(mageX, mageY);
                    }
                }
                if (timePast == 102) {
                    mage.setLocation(mageX, mageY);
                    fireStorm.setVisible(true);
                    flameStrikeY = -400;
                    flame.setLocation(700, flameStrikeY);
                    wolf1Int = wolf1Int - mageDamage / 2;
                    wolf1Hp.setText("Wolf 1: " + wolf1Int);
                    wolf2Int = wolf2Int - mageDamage / 2;
                    wolf2Hp.setText("Wolf 2: " + wolf2Int);
                    wolf3Int = wolf3Int - mageDamage / 2;
                    wolf3Hp.setText("Wolf 3: " + wolf3Int);
                    wolf4Int = wolf4Int - mageDamage / 2;
                    wolf4Hp.setText("Wolf 4: " + wolf4Int);
                }
                if (timePast > 130) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                mageY += 3;
                mage.setLocation(mageX, mageY);
                if (mageY > mageStartY) {
                    mageX = mageStartX;
                    mageY = mageStartY;
                    mage.setLocation(mageX, mageY);
                    phase = 4;
                }
            } else if (phase == 4) {
                timePast++;
                if (timePast > 30) {
                    timePast = 0;
                    fireStorm.setVisible(false);
                    flameStrike.stop();
                    phase = 0;
                    mobDeath();
                }
            }
        }
    });


    private Timer enemyTurnTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            endTurnButton.setVisible(false);
            if (timePast < 50) {
                if (wolf1Int < 1) timePast = 140;
                whosTurn.setText("Wolf 1 turn");
                playersHp.setText("Hp: " + wolf1Int);
                energy.setText("  ");
            } else if (timePast == 50 && wolf1Int > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 60) {
                wolf1X -= 15;
                wolf1.setLocation(wolf1X, wolf1Y);
            } else if (timePast < 70) {
                wolf1X += 15;
                wolf1.setLocation(wolf1X, wolf1Y);
            } else if (timePast < 150) {
                if (wolf2Int < 1) timePast = 240;
                wolf1.setLocation(wolf1StartX, wolf1StartY);
                whosTurn.setText("Wolf 2 turn");
                playersHp.setText("Hp: " + wolf2Int);
                energy.setText("  ");
            } else if (timePast == 150 && wolf2Int > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 160) {
                wolf2X -= 15;
                wolf2.setLocation(wolf2X, wolf2Y);
            } else if (timePast < 170) {
                wolf2X += 15;
                wolf2.setLocation(wolf2X, wolf2Y);
            } else if (timePast < 250) {
                if (wolf3Int < 1) timePast = 340;
                wolf2.setLocation(wolf2StartX, wolf2StartY);
                whosTurn.setText("Wolf 3 turn");
                playersHp.setText("Hp: " + wolf3Int);
                energy.setText("  ");
            } else if (timePast == 250 && wolf3Int > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 260) {
                wolf3X -= 15;
                wolf3.setLocation(wolf3X, wolf3Y);
            } else if (timePast < 270) {
                wolf3X += 15;
                wolf3.setLocation(wolf3X, wolf3Y);
            } else if (timePast < 350) {
                if (wolf4Int < 1) timePast = 440;
                wolf3.setLocation(wolf3StartX, wolf3StartY);
                whosTurn.setText("Wolf 4 turn");
                playersHp.setText("Hp: " + wolf4Int);
                energy.setText("  ");
            } else if (timePast == 350 && wolf4Int > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 360) {
                wolf4X -= 15;
                wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 370) {
                wolf4X += 15;
                wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 450) {
                wolf4.setLocation(wolf4StartX, wolf4StartY);
                enemyTurnTimer.stop();
                turns = 0;
                timePast = 0;
                startNewTurn();
                endTurnButton.setVisible(true);
            }
        }
    });

    Timer shoot = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                arrow.setVisible(true);
                if (arrowX == 121) {
                    MusicPick.musicStart("ding", "");
                }
                arrowX += 30;
                arrow.setLocation(arrowX, arrowY);
                if (arrowX > 1000) {
                    phase = 1;
                }
            } else if (phase == 1) {
                arrow.setVisible(false);
                arrowX = arrowStartX;
                arrow.setLocation(arrowX, arrowY);
                phase = 0;
                shoot.stop();
            }
        }
    });

    private Timer tackle = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                if (warriorX == 100) MusicPick.musicStart("warriorattack", "");
                warriorX += 30;
                warrior.setLocation(warriorX, warriorY);
                if (warriorX > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                warriorX -= 30;
                warrior.setLocation(warriorX, warriorY);
                if (warriorX <= warriorStartX) {
                    warriorX = warriorStartX;
                    warrior.setLocation(warriorX, warriorY);
                    phase = 0;
                    tackle.stop();
                }
            }
        }
    });

    private Timer takeDamage = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePastTakeDamage++;
            if (timePastTakeDamage == 1) {
                MusicPick.musicStart("warriorattacked", "");
            } else if (timePastTakeDamage == 10) {
                if (target == 0) warrior.setVisible(false);
                if (target == 1) ranger.setVisible(false);
                if (target == 2) mage.setVisible(false);
                if (target == 3) healer.setVisible(false);
            } else if (timePastTakeDamage == 20) {
                if (target == 0) warrior.setVisible(true);
                if (target == 1) ranger.setVisible(true);
                if (target == 2) mage.setVisible(true);
                if (target == 3) healer.setVisible(true);
            } else if (timePastTakeDamage == 30) {
                if (target == 0) warrior.setVisible(false);
                if (target == 1) ranger.setVisible(false);
                if (target == 2) mage.setVisible(false);
                if (target == 3) healer.setVisible(false);
            } else if (timePastTakeDamage == 40) {
                if (target == 0) warrior.setVisible(true);
                if (target == 1) ranger.setVisible(true);
                if (target == 2) mage.setVisible(true);
                if (target == 3) healer.setVisible(true);
                takeDamage.stop();
                timePastTakeDamage = 0;
            }
        }
    });

    private Timer dunk = new Timer(10, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                MusicPick.musicStart("shout", "");
                phase = 1;
            }
            if (phase == 1) {
                superMegaMath -= 2;
                warriorX += 20;
                warriorY -= superMegaMath;
                warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    phase = 2;
                }
            } else if (phase == 2) {
                timePast++;
                if (timePast == 1) {

                }
                if (timePast == 30) {
                    warriorY = warriorStartY;
                    warriorX = warriorStartX;
                    warrior.setLocation(warriorX, warriorY);
                    timePast = 0;
                    superMegaMath = 30;
                    phase = 0;
                    dunk.stop();
                }
            }
        }
    });

    private Timer pyroBlast = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (timePast < 100) {
                smallPyroBlast.setVisible(true);
            } else if (timePast < 200) {
                smallPyroBlast.setVisible(false);
                mediumPyroBlast.setVisible(true);
            } else if (timePast < 350) {
                mediumPyroBlast.setVisible(false);
                bigPyroBlast.setVisible(true);
            } else if (timePast < 400) {
                pyroBlastX += 3;
                pyroblastY -= 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 460) {
                pyroBlastX += 3;
                pyroblastY += 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 530) {
                pyroBlastX += 3;
                pyroblastY -= 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 590) {
                pyroBlastX += 4;
                pyroblastY += 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else {
                bigPyroBlast.setVisible(false);
                timePast = 0;
                pyroBlastX = 45;
                pyroblastY = 150;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                pyroBlast.stop();
            }
        }
    });
    private Timer demoshout = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                MusicPick.musicStart("demoshout", " ");
                System.out.println("fine");
                phase = 1;
            } else if (phase == 1) {
                warriorY -= 5;
                warrior.setLocation(warriorX, warriorY);
                if (warriorY < warriorStartY + 50) {
                    phase = 2;
                }
            } else if (phase == 2) {
                if (warriorY < 50) {
                    warriorY = 50;
                    warrior.setLocation(warriorX, warriorY);
                }
                timePast++;
                if (timePast < 50) {
                    if (timePast % 2 == 1) {
                        warriorX += 4;
                        warrior.setLocation(warriorX, warriorY);
                    } else {
                        warriorX -= 4;
                        warrior.setLocation(warriorX, warriorY);
                    }
                }
                if (timePast > 50) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                warriorY += 3;
                warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    warriorX = warriorStartX;
                    warriorY = warriorStartY;
                    warrior.setLocation(warriorX, warriorY);
                    phase = 4;
                    phase = 0;
                    mobDeath();
                    demoshout.stop();
                }
            }
        }
    });
    private Timer fireBall = new Timer(15, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            pyroBlastX += 16;
            smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            if (phase == 0) {
                MusicPick.musicStart("demoshout", "");
                smallPyroBlast.setVisible(true);
                upMegaMath *= 2;
                pyroblastY -= upMegaMath;
            }
            if (timePast % 3 == 0) {
                phase++;
            }
            if (phase == 5) {
                phase = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
            }
            if (phase == 2) upMegaMath = 1;

            if (phase == 1 || phase == 2) { //höger
                rightMegaMath *= 2;
                pyroBlastX += rightMegaMath;
            }
            if (phase == 2 || phase == 3) { //ner
                downMegaMath *= 2;
                pyroblastY += downMegaMath;
            }
            if (phase == 3 || phase == 4) { //vänster
                leftMegaMath *= 2;
                pyroBlastX -= leftMegaMath;
            }
            if (phase == 4 || phase == 1) { //flyger uppåt
                upMegaMath *= 2;
                pyroblastY -= upMegaMath;
            }
            if (timePast == 50) {
                pyroblastY = 150;
                pyroBlastX = 45;
                smallPyroBlast.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                fireBall.stop();
                smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
        }
    });

    private Timer battlecry = new Timer(30, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            swordIcon.setLocation(pyroBlastX, pyroblastY);
            if (phase == 0) {
                MusicPick.musicStart("shout", "");
                swordIcon.setVisible(true);
                upMegaMath *= 2;
                pyroblastY -= upMegaMath;
            }
            if (timePast % 3 == 0) {
                phase++;
            }
            if (phase == 5) {
                phase = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
            }
            if (phase == 2) upMegaMath = 1;

            if (phase == 1 || phase == 2) { //höger
                rightMegaMath += 5;
                pyroBlastX += rightMegaMath;
            }
            if (phase == 2 || phase == 3) { //ner
                downMegaMath += 5;
                pyroblastY += downMegaMath;
            }
            if (phase == 3 || phase == 4) { //vänster
                leftMegaMath += 5;
                pyroBlastX -= leftMegaMath;
            }
            if (phase == 4 || phase == 1) { //flyger uppåt
                upMegaMath += 5;
                pyroblastY -= upMegaMath;
            }
            if (timePast == 50) {
                pyroblastY = 150;
                pyroBlastX = 45;
                swordIcon.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                battlecry.stop();
            }
        }
    });
}