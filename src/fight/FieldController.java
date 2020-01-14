package fight;

import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FieldController{


    FieldView fv = new FieldView();


    //Get hp, block and damage from OldClasses.party
    private int warriorCurrentHp, mageCurrentHp, healerCurrentHp, rangerCurrentHp;
    private int warriorMaxHp, mageMaxHp, healerMaxHp, rangerMaxHp;
    private int warriorDamage, mageDamage, healerDamage, rangerDamage, damage;
    private int warriorBlock, mageBlock, healerBlock, rangerBlock;
    private int buffDamage[] = new int[4];
    private boolean debuffed = false;
    private int enemyDamage, enemyRandomDamage = 25, enemyBaseDamage = 30;

    private int warriorStartDamage, mageStartDamage, healerStartDamage, rangerStartDamage;
    private int warriorStartBlock, mageStartBlock, healerStartBlock, rangerStartBlock;

    //Create int's
    private int timePast = 0;
    private int turns = 1;
    private int currentEnergy;
    private int warriorEnergyInt = 5, mageEnergyInt, rangerEnergyInt, healerEnergyInt;


    //Animation variables
    //player
    public int warriorStartX = 170, warriorStartY = 210, warriorX = warriorStartX, warriorY = warriorStartY;
    public int rangerStartX = 70, rangerStartY = 290, rangerX = rangerStartX, rangerY = rangerStartY;
    public int mageStartX = -110, mageStartY = 290, mageX = mageStartX, mageY = mageStartY;
    public int healerStartX = -30, healerStartY = 210, healerX = healerStartX, healerY = healerStartY;

    public int scarecrow1X = 850, scarecrow1Y = 300, scarecrow1StartX = scarecrow1X, scarecrow1StartY = scarecrow1Y;
    public int scarecrow2X = 1030, scarecrow2Y = 300, scarecrow2StartX = scarecrow2X, scarecrow2StartY = scarecrow2Y;
    public int scarecrow3X = 920, scarecrow3Y = 370, scarecrow3StartX = scarecrow3X, scarecrow3StartY = scarecrow3Y;
    public int scarecrow4X = 1100, scarecrow4Y = 370, scarecrow4StartX = scarecrow4X, scarecrow4StartY = scarecrow4Y;

    //spells/attack
    public int swordIconX = 300, swordIconY = 300;
    public int arrowX = 120, arrowY = 360, arrowStartX = arrowX;
    public int blastX = 120, blastY = 360, blastStartX = arrowX;

    public int flameStrikeY = -400;

    public int warriorMegaMath = 30; //används för halv cirkel anitamationer, PLEASE FOR THE LOVE OF GOD RENAME THIS MONSTOSITY
    public int bombMegaMath = 36;
    public int upMegaMath = 1;
    public int rightMegaMath = 1;
    public int downMegaMath = 1;
    public int leftMegaMath = 1;

    public int pyroBlastX = 90;
    public int pyroblastY = 300;
    public int bombX = 250;
    public int bombY = 300;
    public int bombStartX = 250;
    public int bombStartY = 300;

    boolean warriorattacked = false, rangerattacked = false, mageattacked = false, healerattacked = false;

    //Another timePast to avoid conflict when they run simultaneously.
    public int timePastTakeDamage = 0;

    public int target;
    public int phase = 0;
    public int healTarget = 0;
    public boolean followup = false;
    public boolean animationPlaying = false;
    public boolean stealthed = false;

    public boolean fightWon = false;
    public boolean fightLost = false;

    public int[] ownedPotions = new int[12];

    private int[] scarecrowHp = new int[4];

    public void startFight() {

        MusicPick.musicStart("field", "music");

        turns = 1;
        currentEnergy = 5;
        warriorEnergyInt = 5;
        rangerEnergyInt = 0;
        mageEnergyInt = 0;
        healerEnergyInt = 0;

        scarecrowHp[0] = 100;
        scarecrowHp[1] = 100;
        scarecrowHp[2] = 100;
        scarecrowHp[3] = 100;

        setStartLabels();
        fv.fieldFightFrame();
        hoverEffect();
        targetSystem();

        //ActionListeners
        fv.attackButton.addActionListener(e -> {
            if (!animationPlaying && fv.targetarrow.isVisible()) attackPressed();
        });
        fv.blockButton.addActionListener(e -> blockPressed());
        fv.itemButton.addActionListener(e -> {
            fv.inventory.setVisible(true);
            itemMenuActivate();
        });
        fv.skillButton.addActionListener(e ->{
            if (!animationPlaying) spellMenuActive();
        }); //for now
        fv.endTurnButton.addActionListener(e -> {
            if (!animationPlaying) startNewTurn();
        });
        fv.skill1Button.addActionListener(e -> {
            if (!animationPlaying) skill1();
        });
        fv.skill2Button.addActionListener(e -> {
            if (!animationPlaying) skill2();
        });
        fv.skill3Button.addActionListener(e -> {
            if (!animationPlaying) skill3();
        });
        fv.skill4Button.addActionListener(e -> {
            if (!animationPlaying) skill4();
        });
        fv.returnButton.addActionListener(e -> spellMenuInactive());
        fv.returnButton.addActionListener(e -> fv.inventory.setVisible(false));

        //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
        fv.potion1.addActionListener(e -> usePotion(1));
        fv.potion2.addActionListener(e -> usePotion(2));
        fv.potion3.addActionListener(e -> usePotion(3));
        fv.potion4.addActionListener(e -> usePotion(4));
        fv.potion5.addActionListener(e -> usePotion(5));
        fv.potion6.addActionListener(e -> usePotion(6));
        fv.potion7.addActionListener(e -> usePotion(7));
        fv.potion8.addActionListener(e -> usePotion(8));
        fv.potion9.addActionListener(e -> usePotion(9));
        fv.potion10.addActionListener(e -> usePotion(10));
        fv.potion11.addActionListener(e -> usePotion(11));
        fv.potion12.addActionListener(e -> usePotion(12));

        //Dispose the item frame.
        //fv.returnButton.addActionListener(e -> fv.inventory.dispose());

    }

    //When you press "end turn" button.
    private void startNewTurn() {
        turns++;

        //Warrior's turn
        if (turns == 1 && warriorCurrentHp > 0) {
            warriorEnergyInt += 5; //Get energy
            currentEnergy = warriorEnergyInt; //Update energy.
            warriorBlock = warriorStartBlock; //Update block, reset extra block.
            warriorDamage = warriorStartDamage; //Update damage, reset extra block.

            //Energy cant go over 10.
            if (warriorEnergyInt > 10) {
                warriorEnergyInt = 10;
            }
            //Update labels.
            fv.whosTurn.setText("Warrior's turn");
            fv.playersHp.setText("Hp: " + warriorCurrentHp);
            fv.energy.setText("Energy: " + warriorEnergyInt);
            fv.block.setText("Block: " + warriorBlock);
        }
        //If warrior is dead, skip.
        if (turns == 1 && warriorCurrentHp < 1) {
            turns = 2;
        }
        //Ranger's turn
        if (turns == 2 && rangerCurrentHp > 0) {
            rangerEnergyInt += 5;
            currentEnergy = rangerEnergyInt;
            rangerBlock = rangerStartBlock;
            rangerDamage = rangerStartDamage;

            if (rangerEnergyInt > 10) {
                rangerEnergyInt = 10;
            }
            fv.whosTurn.setText("Ranger's turn");
            fv.playersHp.setText("Hp: " + rangerCurrentHp);
            fv.energy.setText("Energy: " + rangerEnergyInt);
            fv.block.setText("Block: " + rangerBlock);
        }
        //If ranger is dead, skip.
        if (turns == 2 && rangerCurrentHp < 1) {
            turns = 3;
        }
        //Mage's turn
        if (turns == 3 && mageCurrentHp > 0) {
            mageEnergyInt += 5;
            currentEnergy = mageEnergyInt;
            mageBlock = mageStartBlock;
            mageDamage = mageStartDamage;

            if (mageEnergyInt > 10) {
                mageEnergyInt = 10;
            }
            fv.whosTurn.setText("Mage's turn");
            fv.playersHp.setText("Hp: " + mageCurrentHp);
            fv.energy.setText("Energy: " + mageEnergyInt);
            fv.block.setText("Block: " + mageBlock);
        }
        //If mage is dead, skip.
        if (turns == 3 && mageCurrentHp < 1) {
            turns = 4;
        }
        //Healer's turn
        if (turns == 4 && healerCurrentHp > 0) {
            healerEnergyInt += 5;
            currentEnergy = healerEnergyInt;
            healerBlock = healerStartBlock;
            healerDamage = healerStartDamage;

            if (healerEnergyInt > 10) {
                healerEnergyInt = 10;
            }
            fv.whosTurn.setText("Healer's turn");
            fv.playersHp.setText("Hp: " + healerCurrentHp);
            fv.energy.setText("Energy: " + healerEnergyInt);
            fv.block.setText("Block: " + healerBlock);

        }
        //If healer is dead, skip.
        if (turns == 4 && healerCurrentHp < 1) {
            turns = 5;
        }
        //  ***ENEMIES TURN***
        if (turns == 5) {
            fv.whosTurn.setText(" ");
            fv.playersHp.setText(" ");
            fv.energy.setText(" ");
            fv.block.setText(" ");
            enemyTurnTimer.start();

            //removes temporary damage buffs at the end of turn
            for (int i = 0; i < buffDamage.length; i++) {
                buffDamage[i] = 0;
            }

            turns = 0;
        }
    }

    private void enemyDamage(){
        for (int i = 0; i < scarecrowHp.length; i++) {
            if (scarecrowHp[i] > 0) {
                scarecrowAttack();
                partyDeath();
            }
        }
        isFightOver();
    }

    public void targetSystem() {

        fv.scarecrow1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 1;
                fv.targetarrow.setLocation(850, 175);
                fv.targetarrow.setVisible(true);
            }
        });
        fv.scarecrow2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 2;
                fv.targetarrow.setLocation(1025, 175);
                fv.targetarrow.setVisible(true);
            }
        });
        fv.scarecrow3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 3;
                fv.targetarrow.setLocation(925, 250);
                fv.targetarrow.setVisible(true);
            }
        });
        fv.scarecrow4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 4;
                fv.targetarrow.setLocation(1100, 250);
                fv.targetarrow.setVisible(true);
            }
        });
    }

    private void skill1() {
        if (turns == 1 && warriorEnergyInt > 2 && fv.targetarrow.isVisible()) {
            warriorEnergyInt = warriorEnergyInt - 3;
            currentEnergy = currentEnergy - 3;
            fv.energy.setText("Energy: " + warriorEnergyInt);
            charge.start();
        }
        if (turns == 2 && rangerEnergyInt > 3 && fv.targetarrow.isVisible()) {
            rangerEnergyInt = rangerEnergyInt - 4;
            currentEnergy = currentEnergy - 4;
            fv.energy.setText("Energy: " + rangerEnergyInt);
            volley.start();
        }
        if (turns == 3 && mageEnergyInt > 1 && fv.targetarrow.isVisible()) {
            followup = true;
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            fv.energy.setText("Energy: " + mageEnergyInt);
            fireBall.start();
        }
        if (turns == 4 && healerEnergyInt > 1) {
            healingTargetMenu(1);
        }
    }

    private void skill2() {
        if (turns == 1 && warriorEnergyInt > 3) {
            warriorEnergyInt = warriorEnergyInt - 4;
            currentEnergy = currentEnergy - 4;
            fv.energy.setText("Energy: " + warriorEnergyInt);
            dunk.start();
        }
        if (turns == 2 && rangerEnergyInt > 2) {
            rangerEnergyInt = rangerEnergyInt - 3;
            currentEnergy = currentEnergy - 3;
            fv.energy.setText("Energy: " + rangerEnergyInt);
            bombthrow.start();
        }
        if (turns == 3) {

        }
        if (turns == 4 && healerEnergyInt > 1) {
            healingTargetMenu(2);
        }
    }

    private void skill3() {
        if (turns == 1 && warriorEnergyInt>2){
            warriorEnergyInt=warriorEnergyInt-3;
            currentEnergy=currentEnergy-3;
            fv.energy.setText("Energy: " + warriorEnergyInt);
            followup = true;
            shout.start();
        }
        if (turns == 2) {

        }
        if (turns == 3 && mageEnergyInt > 4) {
            mageEnergyInt = mageEnergyInt - 5;
            currentEnergy = currentEnergy - 5;
            fv.energy.setText("Energy: " + mageEnergyInt);
            flameStrike.start();
        }
        if (turns == 4 && healerEnergyInt > 4) {
            healerEnergyInt = healerEnergyInt - 5;
            currentEnergy = currentEnergy - 5;
            fv.energy.setText("Energy: " + healerEnergyInt);
            groupHealSpell.start();
        }
    }

    private void skill4() {
        if (turns == 1 && warriorEnergyInt>4){
            warriorEnergyInt=warriorEnergyInt-5;
            currentEnergy=currentEnergy-5;
            fv.energy.setText("Energy: " + warriorEnergyInt);
            shout.start();
        }
        if (turns == 2 && rangerEnergyInt > 2) {
            rangerEnergyInt = rangerEnergyInt - 3;
            currentEnergy = currentEnergy - 3;
            fv.energy.setText("Energy: " + rangerEnergyInt);
            stealth();
        }
        if (turns == 3 && mageEnergyInt > 4 && fv.targetarrow.isVisible()) {
            mageEnergyInt = mageEnergyInt - 5;
            currentEnergy = currentEnergy - 5;
            fv.energy.setText("Energy: " + mageEnergyInt);
            pyroBlast.start();
        }
        if (turns == 4) {

        }
    }

    private void healingTargetMenu(int chosenSpell) {
        fv.skill1Button.setVisible(false);
        fv.skill2Button.setVisible(false);
        fv.skill3Button.setVisible(false);
        fv.skill4Button.setVisible(false);

        fv.healWarriorButton.setVisible(true);
        fv.healRangerButton.setVisible(true);
        fv.healMageButton.setVisible(true);
        fv.healHealerButton.setVisible(true);

        fv.healWarriorButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 3) {
                healerEnergyInt = healerEnergyInt - 4;
                currentEnergy = currentEnergy - 4;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 1;
                holyLightSpell.start();
            }

            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 1;
                smallHolyLightSpell.start();
            }
        });
        fv.healRangerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 3) {
                healerEnergyInt = healerEnergyInt - 4;
                currentEnergy = currentEnergy - 4;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 2;
                holyLightSpell.start();
            }
            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 2;
                smallHolyLightSpell.start();
            }
        });
        fv.healMageButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 3) {
                healerEnergyInt = healerEnergyInt - 4;
                currentEnergy = currentEnergy - 4;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 3;
                holyLightSpell.start();
            }
            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 3;
                smallHolyLightSpell.start();
            }
        });
        fv.healHealerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 3) {
                healerEnergyInt = healerEnergyInt - 4;
                currentEnergy = currentEnergy - 4;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 4;
                holyLightSpell.start();
            }
            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                fv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 4;
                smallHolyLightSpell.start();
            }
        });
    }

    private void itemMenuActivate(){
        fv.endTurnButton.setVisible(false);
        fv.returnButton.setVisible(true);
    }

    private void spellMenuActive() {
        fv.attackButton.setVisible(false);
        fv.blockButton.setVisible(false);
        fv.itemButton.setVisible(false);
        fv.skillButton.setVisible(false);
        fv.endTurnButton.setVisible(false);

        fv.skill1Button.setVisible(true);
        fv.skill2Button.setVisible(true);
        fv.skill3Button.setVisible(true);
        fv.skill4Button.setVisible(true);
        fv.returnButton.setVisible(true);

        //warrior
        if (turns == 1){
            fv.skill1Button.setText("Charge (3)");
            fv.skill2Button.setText("Slam (4)");
            fv.skill3Button.setText("Battlecry (3)");
            fv.skill4Button.setText("Demoralize (5)");
            fv.skill4Button.setFont(fv.pixelMplus.deriveFont(27f));

        }
        //ranger
        if (turns == 2){
            fv.skill1Button.setText("Volley (4)");
            fv.skill2Button.setText("Bomb (3)");
            fv.skill3Button.setText(" ");
            fv.skill4Button.setText("Stealth (3)");
        }
        //mage
        if (turns == 3) {
            fv.skill1Button.setText("Fireball (2)");
            fv.skill2Button.setText(" ");
            fv.skill3Button.setText("Meteor (5)");
            fv.skill4Button.setText("Pyroblast (5)");
        }
        //healer
        if (turns == 4){
            fv.skill1Button.setText("Heal (4)");
            fv.skill2Button.setText("Bless (2)");
            fv.skill3Button.setText("Restore (5)");
            fv.skill4Button.setText(" ");
        }
    }

    //When player press block
    private void blockPressed() {

        //If its warrior's turn and player has 2 or more energy.
        if (turns == 1 && warriorEnergyInt > 1) {
            warriorEnergyInt = warriorEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            warriorBlock += 5;
            fv.energy.setText("Energy: " + warriorEnergyInt);
            fv.block.setText("Block: " + warriorBlock);
        }
        //If its ranger's turn and player has 2 or more energy.
        else if (turns == 2 && rangerEnergyInt > 1) {
            rangerEnergyInt = rangerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            rangerBlock += 5;
            fv.energy.setText("Energy: " + rangerEnergyInt);
            fv.block.setText("Block: " + rangerBlock);
        }
        //If its mage's turn and player has 2 or more energy.
        else if (turns == 3 && mageEnergyInt > 1) {
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            mageBlock += 5;
            fv.energy.setText("Energy: " + mageEnergyInt);
            fv.block.setText("Block: " + mageBlock);
        }
        //If its healer's turn and player has 2 or more energy.
        else if (turns == 4 && healerEnergyInt > 1) {
            healerEnergyInt = healerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            healerBlock += 5;
            fv.energy.setText("Energy: " + healerEnergyInt);
            fv.block.setText("Block: " + healerBlock);
        }
    }

    //When you press the "attack button".
    private void attackPressed() {

        //If its warrior's turn and player has 2 or more energy.
        if (turns == 1 && warriorEnergyInt > 1 && fv.targetarrow.isVisible()) {
            warriorEnergyInt = warriorEnergyInt - 2; //Energy -2.
            currentEnergy = currentEnergy - 2; // Update currentEnergy.
            fv.energy.setText("Energy: " + warriorEnergyInt); //Update energyLabel
            tackle.start(); //Warrior deals damage to a scarecrow.
        }
        //If its ranger's turn and player has 2 or more energy.
        else if (turns == 2 && rangerEnergyInt > 1 && fv.targetarrow.isVisible()) {
            rangerEnergyInt = rangerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            fv.energy.setText("Energy: " + rangerEnergyInt);
            shoot.start();
        }
        //If its mage's turn and player has 2 or more energy.
        else if (turns == 3 && mageEnergyInt > 1 && fv.targetarrow.isVisible()) {
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            fv.energy.setText("Energy: " + mageEnergyInt);
            blast.start();
        }
        //If its healer's turn and player has 2 or more energy.
        else if (turns == 4 && healerEnergyInt > 1 && fv.targetarrow.isVisible()) {
            healerEnergyInt = healerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            fv.energy.setText("Energy: " + healerEnergyInt);
            healerAttack.start();
        }
    }

    //Checks if all of the enemies or OldClasses.party-members are dead.
    private void isFightOver() {
        //If all of the wolves are dead. Open lootScreen.
        if (scarecrowHp[0] < 1 && scarecrowHp[1] < 1 && scarecrowHp[2] < 1 && scarecrowHp[3] < 1) {
            MusicPick.musicStop();
            fv.fieldFightJFrame.dispose();
            fightWon = true;

        }
        //In the whole OldClasses.party is dead, game is over. Send to loseScreen.
        if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
            fv.fieldFightJFrame.dispose();
            fightLost = true;
        }
        //If none of these are true, nothing happens and the fight goes on.
    }

    public void setStartLabels() {

        fv.potion1Label = new JLabel("" + ownedPotions[0]);
        fv.potion2Label = new JLabel("" + ownedPotions[1]);
        fv.potion3Label = new JLabel("" + ownedPotions[2]);
        fv.potion4Label = new JLabel("" + ownedPotions[3]);
        fv.potion5Label = new JLabel("" + ownedPotions[4]);
        fv.potion6Label = new JLabel("" + ownedPotions[5]);
        fv.potion7Label = new JLabel("" + ownedPotions[6]);
        fv.potion8Label = new JLabel("" + ownedPotions[7]);
        fv.potion9Label = new JLabel("" + ownedPotions[8]);
        fv.potion10Label = new JLabel("" + ownedPotions[9]);
        fv.potion11Label = new JLabel("" + ownedPotions[10]);
        fv.potion12Label = new JLabel("" + ownedPotions[11]);

        fv.scarecrow1Hp = new JLabel("scarecrow 1: " + scarecrowHp[0]);
        fv.scarecrow2Hp = new JLabel("scarecrow 2: " + scarecrowHp[1]);
        fv.scarecrow3Hp = new JLabel("scarecrow 3: " + scarecrowHp[2]);
        fv.scarecrow4Hp = new JLabel("scarecrow 4: " + scarecrowHp[3]);

        fv.playersHp = new JLabel("Hp: " + warriorCurrentHp);
        fv.player1Hp = new JLabel("Warrior: " + warriorCurrentHp);
        fv.player2Hp = new JLabel("Mage:    " + mageCurrentHp);
        fv.player3Hp = new JLabel("Ranger:  " + rangerCurrentHp);
        fv.player4Hp = new JLabel("Healer:  " + healerCurrentHp);
        fv.block = new JLabel("Block: " + warriorBlock);
    }

    //When the scarecrow attacks.
    private void scarecrowAttack() {
        target = (int) (Math.random() * 4); //Random target, 0-3.

        enemyDamage = (int) (Math.random() * enemyRandomDamage) + enemyBaseDamage; //Generate random damage, 15-25.
        if (debuffed) enemyDamage -= 10;

        //Loops until it reaches an alive OldClasses.party-member.
        while (true) {

            //Warrior, Target 2.
            if (target == 0) {
                //If warrior is dead, target=1.
                if (warriorCurrentHp < 1) {
                    if (stealthed) target = 2;
                    else target = 1;
                }
                //If warrior is alive.
                if (warriorCurrentHp > 0) {
                    warriorattacked = true;
                    enemyDamage = enemyDamage - warriorBlock; //Warrior take damage equal to scarecrow damage.
                    warriorCurrentHp = warriorCurrentHp - enemyDamage; //Update warrior hp.
                    fv.player1Hp.setText("Warrior: " + warriorCurrentHp); //Update hp Label.
                    break;
                }
            }
            //Mage, Target 1.
            if (target == 2) {
                //If mage is dead, target=2.
                if (mageCurrentHp < 1) {
                    target = 3;
                }
                //If mage is alive.
                if (mageCurrentHp > 0) {
                    mageattacked = true;
                    enemyDamage = enemyDamage - mageBlock;
                    mageCurrentHp = mageCurrentHp - enemyDamage;
                    fv.player2Hp.setText("Mage:    " + mageCurrentHp);
                    break;
                }
            }
            //Ranger, target 2.
            if (target == 1) {
                //If ranger is dead, target=3.

                //will not be attacked if stealthed
                if (stealthed && (mageCurrentHp > 0 || healerCurrentHp > 0 || warriorCurrentHp > 0)){
                    target = 0;
                }

                else if (rangerCurrentHp < 1) {
                    target = 2;
                }
                //If ranger is alive.
                else {
                    rangerattacked = true;
                    enemyDamage = enemyDamage - rangerBlock;
                    rangerCurrentHp = rangerCurrentHp - enemyDamage;
                    fv.player3Hp.setText("Ranger:  " + rangerCurrentHp);
                    unstealth();
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
                    healerattacked = true;
                    enemyDamage = enemyDamage - healerBlock;
                    healerCurrentHp = healerCurrentHp - enemyDamage;
                    fv.player4Hp.setText("Healer:   " + healerCurrentHp);
                    break;
                }
            }
        }
    }

    //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void mobDeath() {

        if (scarecrowHp[0] <= 0) {
            fv.scarecrow1Hp.setText("scarecrow 1: 0");
            fv.scarecrow1.setVisible(false);
            if (target == 1) {
                fv.targetarrow.setVisible(false);
            }
        }
        if (scarecrowHp[1] <= 0) {
            fv.scarecrow2Hp.setText("scarecrow 2: 0");
            fv.scarecrow2.setVisible(false);
            if (target == 2) {
                fv.targetarrow.setVisible(false);
            }
        }
        if (scarecrowHp[2] <= 0) {
            fv.scarecrow3Hp.setText("scarecrow 3: 0");
            fv.scarecrow3.setVisible(false);
            if (target == 3) {
                fv.targetarrow.setVisible(false);
            }
        }
        if (scarecrowHp[3] <= 0) {
            fv.scarecrow4Hp.setText("scarecrow 4: 0");
            fv.scarecrow4.setVisible(false);
            if (target == 4) {
                fv.targetarrow.setVisible(false);
            }
        }
    }

    //Checks if any OldClasses.party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void partyDeath() {

        if (warriorCurrentHp <= 0) {
            warriorCurrentHp = 0;
            fv.player1Hp.setText("Warrior: " + warriorCurrentHp);
            fv.warrior.setVisible(false);
        }
        if (mageCurrentHp <= 0) {
            mageCurrentHp = 0;
            fv.player2Hp.setText("Mage:    " + mageCurrentHp);
            fv.mage.setVisible(false);
        }
        if (rangerCurrentHp <= 0) {
            rangerCurrentHp = 0;
            fv.player3Hp.setText("Ranger:  " + rangerCurrentHp);
            fv.ranger.setVisible(false);
        }
        if (healerCurrentHp <= 0) {
            healerCurrentHp = 0;
            fv.player4Hp.setText("Healer:  " + healerCurrentHp);
            fv.healer.setVisible(false);
        }
    }

    private void spellMenuInactive() {
        fv.attackButton.setVisible(true);
        fv.blockButton.setVisible(true);
        fv.itemButton.setVisible(true);
        fv.skillButton.setVisible(true);
        fv.endTurnButton.setVisible(true);

        fv.skill1Button.setVisible(false);
        fv.skill2Button.setVisible(false);
        fv.skill3Button.setVisible(false);
        fv.skill4Button.setVisible(false);
        fv.returnButton.setVisible(false);
    }

    public void getInventory(int[] potions) {

        ownedPotions[0] = potions[0];
        ownedPotions[1] = potions[1];
        ownedPotions[2] = potions[2];
        ownedPotions[3] = potions[3];
        ownedPotions[4] = potions[4];
        ownedPotions[5] = potions[5];
        ownedPotions[6] = potions[6];
        ownedPotions[7] = potions[7];
        ownedPotions[8] = potions[8];
        ownedPotions[9] = potions[9];
        ownedPotions[10] = potions[10];
        ownedPotions[11] = potions[11];
    }

    public void getPlayerStats(int[] warrior, int[] mage, int[] healer, int[] ranger) {

        warriorCurrentHp = warrior[0];
        warriorStartBlock = warrior[1];
        warriorStartDamage = warrior[2];

        warriorDamage = warriorStartDamage;
        warriorBlock = warriorStartBlock;

        mageCurrentHp = mage[0];
        mageStartBlock = mage[1];
        mageStartDamage = mage[2];

        healerCurrentHp = healer[0];
        healerStartBlock = healer[1];
        healerStartDamage = healer[2];

        rangerCurrentHp = ranger[0];
        rangerStartBlock = ranger[1];
        rangerStartDamage = ranger[2];

        warriorMaxHp =warriorCurrentHp;
        mageMaxHp = mageCurrentHp;
        healerMaxHp = healerCurrentHp;
        rangerMaxHp = rangerCurrentHp;
    }

    //Get the effect from potions.
    private void usePotion(int potion) {

        //Warrior
        if (turns == 1) {
            //If potion 1 "minor healing potion" is pressed.
            if (potion == 1) {
                //If player own that potion.
                if (ownedPotions[0] > 0) {
                    warriorCurrentHp += 10; //Heal warrior equals to the potions heal.
                    fv.playersHp.setText("Hp: " + warriorCurrentHp); //Update Warrior's hp Label.
                    fv.player1Hp.setText("Warrior: " + warriorCurrentHp); // Update currentPlayer Hp label.
                    ownedPotions[0] -= 1;
                    fv.potion1Label.setText("" + ownedPotions[0]); //Update ownedPotion Label.
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    warriorCurrentHp += 30;
                    fv.playersHp.setText("Hp: " + warriorCurrentHp);
                    fv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[1] -= 1;
                    fv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    warriorCurrentHp += 60;
                    fv.playersHp.setText("Hp: " + warriorCurrentHp);
                    fv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[2] -= 1;
                    fv.potion3Label.setText("" + ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    warriorBlock += 5;
                    fv.block.setText("Block: " + warriorBlock);
                    ownedPotions[3] -= 1;
                    fv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    warriorBlock += 20;
                    fv.block.setText("Block: " + warriorBlock);
                    ownedPotions[4] -= 1;
                    fv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    warriorBlock += 50;
                    fv.block.setText("Block: " + warriorBlock);
                    ownedPotions[5] -= 1;
                    fv.potion6Label.setText("" + ownedPotions[5]);
                }
            } else if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    warriorEnergyInt += 3;
                    fv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[6] -= 1;
                    fv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    warriorCurrentHp += 5;
                    fv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[7] -= 1;
                    fv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    warriorCurrentHp += 10;
                    fv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[8] -= 1;
                    fv.potion9Label.setText("" + ownedPotions[8]);
                }
            }
            if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9] -= 1;
                    fv.potion10Label.setText("" + ownedPotions[9]);
                }
            }
            if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage [turns - 1] += 10;
                    ownedPotions[10] -= 1;
                    fv.potion11Label.setText("" + ownedPotions[10]);
                }
            }
            if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage [turns - 1] += 20;
                    ownedPotions[11] -= 1;
                    fv.potion12Label.setText("" + ownedPotions[11]);
                }
            }
        }

        //Ranger
        else if (turns == 2) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    rangerCurrentHp += 10;
                    fv.playersHp.setText("Hp: " + rangerCurrentHp);
                    fv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[0] -= 1;
                    fv.potion1Label.setText("" + ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    rangerCurrentHp += 30;
                    fv.playersHp.setText("Hp: " + rangerCurrentHp);
                    fv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[1] -= 1;
                    fv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    fv.playersHp.setText("Hp: " + rangerCurrentHp);
                    fv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[2] -= 1;
                    fv.potion3Label.setText("" + ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    rangerBlock += 5;
                    fv.block.setText("Block: " + rangerBlock);
                    ownedPotions[3] -= 1;
                    fv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    rangerBlock += 20;
                    fv.block.setText("Block: " + rangerBlock);
                    ownedPotions[4] -= 1;
                    fv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    rangerBlock += 50;
                    fv.block.setText("Block: " + rangerBlock);
                    ownedPotions[5] -= 1;
                    fv.potion6Label.setText("" + ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    rangerEnergyInt += 3;
                    fv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[6] -= 1;
                    fv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    rangerEnergyInt += 5;
                    fv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[7] -= 1;
                    fv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    rangerEnergyInt += 10;
                    fv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[8] -= 1;
                    fv.potion9Label.setText("" + ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9] -= 1;
                    fv.potion10Label.setText("" + ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage[turns - 1] += 10;
                    ownedPotions[10] -= 1;
                    fv.potion11Label.setText("" + ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage[turns - 1] += 20;
                    ownedPotions[11] -= 1;
                    fv.potion12Label.setText("" + ownedPotions[11]);
                }
            }
        }
        //Mage
        else if (turns == 3) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    mageCurrentHp += 10;
                    fv.playersHp.setText("Hp: " + mageCurrentHp);
                    fv.player3Hp.setText("Mage: " + mageCurrentHp);
                    ownedPotions[0] -= 1;
                    fv.potion1Label.setText("" + ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    mageCurrentHp += 30;
                    fv.playersHp.setText("Hp: " + mageCurrentHp);
                    fv.player3Hp.setText("Mage: " + mageCurrentHp);
                    ownedPotions[1] -= 1;
                    fv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    fv.playersHp.setText("Hp: " + mageCurrentHp);
                    fv.player3Hp.setText("Mage: " + mageCurrentHp);
                    fv.potion3Label.setText("" + ownedPotions[2]);
                    ownedPotions[2] -= 1;
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    mageBlock += 5;
                    fv.block.setText("Block: " + mageBlock);
                    ownedPotions[3] -= 1;
                    fv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    mageBlock += 20;
                    fv.block.setText("Block: " + mageBlock);
                    ownedPotions[4] -= 1;
                    fv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    mageBlock += 50;
                    fv.block.setText("Block: " + mageBlock);
                    ownedPotions[5] -= 1;
                    fv.potion6Label.setText("" + ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    mageEnergyInt += 3;
                    fv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[6] -= 1;
                    fv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    mageEnergyInt += 5;
                    fv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[7] -= 1;
                    fv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    mageEnergyInt += 10;
                    fv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[8] -= 1;
                    fv.potion9Label.setText("" + ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9] -= 1;
                    fv.potion10Label.setText("" + ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage[turns - 1] += 10;
                    ownedPotions[10] -= 1;
                    fv.potion11Label.setText("" + ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage[turns - 1] += 20;
                    ownedPotions[11] -= 1;
                    fv.potion12Label.setText("" + ownedPotions[11]);
                }
            }
        }
        //Healer
        else if (turns == 4) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    healerCurrentHp += 10;
                    fv.playersHp.setText("Hp: " + healerCurrentHp);
                    fv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[0] -= 1;
                    fv.potion1Label.setText("" + ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    healerCurrentHp += 30;
                    fv.playersHp.setText("Hp: " + healerCurrentHp);
                    fv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[1] -= 1;
                    fv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    healerCurrentHp += 60;
                    fv.playersHp.setText("Hp: " + healerCurrentHp);
                    fv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[2] -= 1;
                    fv.potion3Label.setText("" + ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    healerBlock += 5;
                    fv.block.setText("Block: " + healerBlock);
                    ownedPotions[3] -= 1;
                    fv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    healerBlock += 20;
                    fv.block.setText("Block: " + healerBlock);
                    ownedPotions[4] -= 1;
                    fv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    healerBlock += 50;
                    fv.block.setText("Block: " + healerBlock);
                    ownedPotions[5] -= 1;
                    fv.potion6Label.setText("" + ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    healerEnergyInt += 3;
                    fv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[6] -= 1;
                    fv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    healerEnergyInt += 5;
                    fv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[7] -= 1;
                    fv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    healerEnergyInt += 10;
                    fv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[8] -= 1;
                    fv.potion9Label.setText("" + ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9] -= 1;
                    fv.potion10Label.setText("" + ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage[turns - 1] += 10;
                    ownedPotions[10] -= 1;
                    fv.potion11Label.setText("" + ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage[turns - 1] += 20;
                    ownedPotions[11] -= 1;
                    fv.potion12Label.setText("" + ownedPotions[11]);
                }
            }
        }
    }

    //Add hover effect to buttons.
    private void hoverEffect() {
        //Attack Hover
        fv.attackButton.addMouseListener(new MouseAdapter() {
            //Change button color while hovering depending on your current energy.
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (currentEnergy >= 2) {
                    fv.attackButton.setBackground(Color.lightGray);
                }
                if (currentEnergy < 2) {
                    fv.attackButton.setBackground(Color.pink);
                }
            }

            //Change back when not hovering over button
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fv.attackButton.setBackground(Color.white);
            }
        });

        //Block Hover
        fv.blockButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (currentEnergy >= 2) {
                    fv.blockButton.setBackground(Color.lightGray);
                }
                if (currentEnergy < 2) {
                    fv.blockButton.setBackground(Color.pink);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                fv.blockButton.setBackground(Color.white);
            }
        });

        //Item Hover
        fv.itemButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fv.itemButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                fv.itemButton.setBackground(Color.white);
            }
        });
        //Skill Hover
        fv.skillButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fv.skillButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                fv.skillButton.setBackground(Color.white);
            }
        });

        //End turn Hover
        fv.endTurnButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fv.endTurnButton.setBackground(Color.lightGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                fv.endTurnButton.setBackground(Color.white);
            }
        });
    }


    //called from spells to deal damage to enemies
    //damageTargets types: single, line, all
    public void spellDamageSystem(int unbuffedDamage, String damageTargets) {
        damage = unbuffedDamage + buffDamage[turns - 1];
        if (damageTargets.equals("single")) {
            scarecrowHp[target - 1] -= damage;
        }
        if (damageTargets.equals("line")) {
            if (target == 1 || target == 2) {
                scarecrowHp[0] -= damage;
                scarecrowHp[1] -= damage;
            }
            if (target == 3 || target == 4) {
                scarecrowHp[2] -= damage;
                scarecrowHp[3] -= damage;
            }
        }
        if (damageTargets.equals("all")) {
            scarecrowHp[0] -= damage;
            scarecrowHp[1] -= damage;
            scarecrowHp[2] -= damage;
            scarecrowHp[3] -= damage;
        }
        fv.scarecrow1Hp.setText("scarecrow 1: " + scarecrowHp[0]);
        fv.scarecrow2Hp.setText("scarecrow 2: " + scarecrowHp[1]);
        fv.scarecrow3Hp.setText("scarecrow 3: " + scarecrowHp[2]);
        fv.scarecrow4Hp.setText("scarecrow 4: " + scarecrowHp[3]);
        mobDeath();
        isFightOver();
    }

    //fixa denna
    public void spellHealSystem(int healing, String healingTargets) {
        if (healingTargets.equals("single")) {
            if (healTarget == 1) warriorCurrentHp += healing;
            if (healTarget == 2) rangerCurrentHp += healing;
            if (healTarget == 3) mageCurrentHp += healing;
            if (healTarget == 4) healerCurrentHp += healing;
        }
        if (healingTargets.equals("all")) {
            warriorCurrentHp += healing;
            healerCurrentHp += healing;
            rangerCurrentHp += healing;
            mageCurrentHp += healing;
        }
        if (warriorMaxHp < warriorCurrentHp) warriorCurrentHp = warriorMaxHp;
        if (mageMaxHp < mageCurrentHp) mageCurrentHp = mageMaxHp;
        if (healerMaxHp < healerCurrentHp) healerCurrentHp = healerMaxHp;
        if (rangerMaxHp < rangerCurrentHp) rangerCurrentHp = rangerMaxHp;
        fv.player1Hp.setText("Warrior: " + warriorCurrentHp);
        fv.player2Hp.setText("Ranger:  " + rangerCurrentHp);
        fv.player3Hp.setText("Mage:    " + mageCurrentHp);
        fv.player4Hp.setText("Healer:  " + healerCurrentHp);
    }


    //warrior
    public Timer tackle = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                if (warriorX == 100) MusicPick.musicStart("warriorattack", "");
                animationPlaying = true;
                warriorX += 15;
                fv.warrior.setLocation(warriorX, warriorY);
                if (warriorX > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                warriorX -= 15;
                fv.warrior.setLocation(warriorX, warriorY);
                if (warriorX <= warriorStartX) {
                    warriorX = warriorStartX;
                    fv.warrior.setLocation(warriorX, warriorY);
                    phase = 0;
                    tackle.stop();
                    spellDamageSystem(warriorDamage, "single");
                    animationPlaying = false;
                }
            }
        }
    });

    public Timer charge = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                MusicPick.musicStart("charge", "");
                phase = 1;
            } else if (phase == 1) {
                warriorX += 20;
                fv.warrior.setLocation(warriorX, warriorY);
                if (warriorX > 2000) {
                    phase = 2;
                }
            } else if (phase == 2) {
                warriorX = warriorStartX;
                warriorY = warriorStartY;
                fv.warrior.setLocation(warriorX, warriorY);
                phase = 0;
                charge.stop();
                spellDamageSystem(6, "line");
                animationPlaying = false;
            }
        }
    });

    public Timer dunk = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                MusicPick.musicStart("charge", "");
                phase = 1;
            }
            if (phase == 1) {
                warriorMegaMath -= 2;
                warriorX += 20;
                warriorY -= warriorMegaMath;
                fv.warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    phase = 2;
                }
            } else if (phase == 2) {
                timePast++;
                if (timePast == 30) {
                    warriorY = warriorStartY;
                    warriorX = warriorStartX;
                    fv.warrior.setLocation(warriorX, warriorY);
                    timePast = 0;
                    warriorMegaMath = 30;
                    phase = 0;
                    dunk.stop();
                    spellDamageSystem(4, "all");
                    animationPlaying = false;

                }
            }
        }
    });

    public Timer shout = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                MusicPick.musicStart("demoshout", " ");
                phase = 1;
                animationPlaying = true;
            } else if (phase == 1) {
                warriorY -= 5;
                fv.warrior.setLocation(warriorX, warriorY);
                if (warriorY < warriorStartY + 50) {
                    phase = 2;
                }
            } else if (phase == 2) {
                if (warriorY < 50) {
                    warriorY = 50;
                    fv.warrior.setLocation(warriorX, warriorY);
                }
                timePast++;
                if (timePast < 50) {
                    if (timePast % 2 == 1) {
                        warriorX += 4;
                        fv.warrior.setLocation(warriorX, warriorY);
                    } else {
                        warriorX -= 4;
                        fv.warrior.setLocation(warriorX, warriorY);
                    }
                }
                if (timePast > 50) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                warriorY += 3;
                fv.warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    warriorX = warriorStartX;
                    warriorY = warriorStartY;
                    fv.warrior.setLocation(warriorX, warriorY);
                    phase = 0;
                    shout.stop();
                    if (followup) {
                        battlecry.start();
                        followup = false;
                    } else {
                        demoralized.start();
                    }
                }
            }
        }
    });

    public Timer battlecry = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            fv.swordIcon.setLocation(swordIconX, swordIconY);
            if (phase == 0) {
                if (upMegaMath == 1) MusicPick.musicStart("demoshout", "");
                fv.swordIcon.setVisible(true);
                upMegaMath *= 2;
                swordIconY -= upMegaMath;
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
                swordIconX += rightMegaMath;
            }
            if (phase == 2 || phase == 3) { //ner
                downMegaMath += 5;
                swordIconY += downMegaMath;
            }
            if (phase == 3 || phase == 4) { //vänster
                leftMegaMath += 5;
                swordIconX -= leftMegaMath;
            }
            if (phase == 4 || phase == 1) { //flyger uppåt
                upMegaMath += 5;
                swordIconY -= upMegaMath;
            }
            if (timePast == 50) {
                for (int i = 0; i < buffDamage.length; i++) {
                    buffDamage[i] += 3;
                }
                swordIconX = 300;
                swordIconY = 300;
                fv.swordIcon.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                animationPlaying = false;
                battlecry.stop();
            }
        }
    });

    public Timer demoralized = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            fv.demoSword1.setLocation(swordIconX + 550, swordIconY + 50);
            fv.demoSword2.setLocation(swordIconX + 650, swordIconY + 100);
            fv.demoSword3.setLocation(swordIconX + 750, swordIconY + 50);
            fv.demoSword4.setLocation(swordIconX + 850, swordIconY + 100);


            if (phase == 0) {
                if (scarecrowHp[0] > 0) fv.demoSword1.setVisible(true);
                if (scarecrowHp[2] > 0) fv.demoSword2.setVisible(true);
                if (scarecrowHp[1] > 0) fv.demoSword3.setVisible(true);
                if (scarecrowHp[3] > 0) fv.demoSword4.setVisible(true);
                upMegaMath *= 2;
                swordIconY -= upMegaMath;
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
                swordIconX += rightMegaMath;
            }
            if (phase == 2 || phase == 3) { //ner
                downMegaMath += 5;
                swordIconY += downMegaMath;
            }
            if (phase == 3 || phase == 4) { //vänster
                leftMegaMath += 5;
                swordIconX -= leftMegaMath;
            }
            if (phase == 4 || phase == 1) { //flyger uppåt
                upMegaMath += 5;
                swordIconY -= upMegaMath;
            }
            if (timePast == 50) {
                swordIconX = 300;
                swordIconY = 300;
                fv.demoSword1.setVisible(false);
                fv.demoSword2.setVisible(false);
                fv.demoSword3.setVisible(false);
                fv.demoSword4.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                debuffed = true;
                phase = 0;
                animationPlaying = false;
                demoralized.stop();
            }
        }
    });

    //ranger

    Timer shoot = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                fv.arrow.setVisible(true);
                if (arrowX == 121) {
                    animationPlaying = true;
                    MusicPick.musicStart("ding", "");
                }
                arrowX += 30;
                fv.arrow.setLocation(arrowX, arrowY);
                if (arrowX > 1000) {
                    phase = 1;
                }
            } else if (phase == 1) {
                fv.arrow.setVisible(false);
                arrowX = arrowStartX;
                fv.arrow.setLocation(arrowX, arrowY);
                phase = 0;
                shoot.stop();
                if (stealthed) {
                    spellDamageSystem(rangerDamage * 2, "single");
                    unstealth();
                } else {
                    spellDamageSystem(rangerDamage, "single");
                }
            }
        }
    });

    public Timer volley = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            arrowX += 30;
            fv.volley1.setLocation(arrowX, arrowY);
            fv.volley2.setLocation(arrowX - 200, arrowY);
            fv.volley3.setLocation(arrowX - 400, arrowY);
            if (phase == 0) {
                fv.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 1;
            } else if (phase == 1 && arrowX > arrowStartX + 300) {
                fv.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 2;
            } else if (phase == 2 && arrowX > arrowStartX + 600) {
                fv.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 3;
            } else if (phase == 3 && arrowX > 1000) {
                fv.volley1.setVisible(false);
                phase = 4;
            } else if (phase == 4 && arrowX > 1200) {
                fv.volley2.setVisible(false);
                phase = 5;
            } else if (phase == 5 && arrowX > 1400) {
                fv.volley3.setVisible(false);
                phase = 6;
            }
            if (phase == 6) {
                arrowX = 300;
                fv.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 7;
            } else if (phase == 7 && arrowX > arrowStartX + 300) {
                fv.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 8;
            } else if (phase == 8 && arrowX > arrowStartX + 600) {
                fv.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 9;
            } else if (phase == 9 && arrowX > 1000) {
                fv.volley1.setVisible(false);
                phase = 10;
            } else if (phase == 10 && arrowX > 1200) {
                fv.volley2.setVisible(false);
                phase = 11;
            } else if (phase == 11 && arrowX > 1400) {
                fv.volley3.setVisible(false);
                phase = 12;
                arrowX = 300;
            }
            if (phase == 12) {
                fv.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 13;
            } else if (phase == 13 && arrowX > arrowStartX + 300) {
                fv.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 14;
            } else if (phase == 14 && arrowX > arrowStartX + 600) {
                fv.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 15;
            } else if (phase == 15 && arrowX > 1000) {
                fv.volley1.setVisible(false);
                phase = 16;
            } else if (phase == 16 && arrowX > 1200) {
                fv.volley2.setVisible(false);
                phase = 17;
            } else if (phase == 17 && arrowX > 1400) {
                fv.volley3.setVisible(false);
                phase = 18;
            } else if (phase == 18) {
                arrowX = 270;
                phase = 0;
                animationPlaying = false;
                volley.stop();
                if (stealthed) {
                    spellDamageSystem(40, "single");
                    unstealth();
                } else {
                    spellDamageSystem(20, "single");
                }
            }
        }
    });

    public void stealth() {
        if (!stealthed) {
            MusicPick.musicStart("stealth", "");
            fv.ranger.setVisible(false);
            fv.stealthranger.setVisible(true);
            stealthed = true;
        }
    }

    public void unstealth() {
        if (stealthed) {
            MusicPick.musicStart("unstealth", "");
            fv.ranger.setVisible(true);
            fv.stealthranger.setVisible(false);
            stealthed = false;
        }
    }

    public Timer bombthrow = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                phase = 1;
                fv.bomb.setVisible(true);
            }
            if (phase == 1) {
                bombMegaMath -= 2;
                bombX += 20;
                bombY -= bombMegaMath;
                fv.bomb.setLocation(bombX, bombY);
                if (bombY > bombStartY) {
                    phase = 2;
                }
            } else if (phase == 2) {
                timePast++;
                if (timePast == 30) {
                    bombY = bombStartY;
                    bombX = bombStartX;
                    fv.bomb.setLocation(bombX, bombY);
                    fv.bomb.setVisible(false);
                    fv.explode.setVisible(true);
                    MusicPick.musicStart("Explosion", "");
                }
                if (timePast == 60) {
                    bombMegaMath = 36;
                    fv.bomb.setVisible(false);
                    fv.explode.setVisible(false);
                    timePast = 0;
                    phase = 0;
                    bombthrow.stop();
                    if (stealthed) {
                        spellDamageSystem(8, "all");
                        unstealth();
                    } else {
                        spellDamageSystem(4, "all");
                    }
                    animationPlaying = false;
                }
            }
        }
    });


    //mage
    Timer blast = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                fv.blast.setVisible(true);
                if (blastY == 121) {
                    MusicPick.musicStart("fireball", "");
                }
                blastX += 30;
                fv.blast.setLocation(blastX, blastY);
                if (blastX > 1000) {
                    phase = 1;
                }
            } else if (phase == 1) {
                fv.blast.setVisible(false);
                blastX = blastStartX;
                fv.blast.setLocation(blastX, blastY);
                phase = 0;
                spellDamageSystem(mageDamage, "single");
                blast.stop();
                animationPlaying = false;
            }
        }
    });

    public Timer pyroBlast = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (timePast < 100) {
                animationPlaying = true;
                fv.smallPyroBlast.setVisible(true);
            } else if (timePast < 200) {
                fv.smallPyroBlast.setVisible(false);
                fv.mediumPyroBlast.setVisible(true);
            } else if (timePast < 350) {
                fv.mediumPyroBlast.setVisible(false);
                fv.bigPyroBlast.setVisible(true);
                pyroBlastX = 45;
                pyroblastY = 200;
                fv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 400) {
                pyroBlastX += 3;
                pyroblastY -= 1;
                fv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 460) {
                pyroBlastX += 3;
                pyroblastY += 1;
                fv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 530) {
                pyroBlastX += 3;
                pyroblastY -= 1;
                fv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 590) {
                pyroBlastX += 4;
                pyroblastY += 1;
                fv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else {
                fv.bigPyroBlast.setVisible(false);
                timePast = 0;
                pyroBlastX = 90;
                pyroblastY = 300;
                fv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                spellDamageSystem(20, "single");
                pyroBlast.stop();
                animationPlaying = false;
            }
        }
    });

    public Timer flameStrike = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                animationPlaying = true;
                MusicPick.musicStart("magespell", "");
                phase = 1;
            } else if (phase == 1) {
                mageY -= 3;
                fv.mage.setLocation(mageX, mageY);
                if (mageY < 50) {
                    phase = 2;
                }
            } else if (phase == 2) {
                if (mageY < 50) {
                    mageY = 50;
                    fv.mage.setLocation(mageX, mageY);
                }
                timePast++;
                if (timePast < 100) {
                    if (timePast % 2 == 1) {
                        mageX += 6;
                        fv.mage.setLocation(mageX, mageY);
                        flameStrikeY += 13;
                        fv.flame.setLocation(900, flameStrikeY);
                    } else {
                        mageX -= 6;
                        fv.mage.setLocation(mageX, mageY);
                    }
                }
                if (timePast == 102) {
                    fv.mage.setLocation(mageX, mageY);
                    flameStrikeY = -400;
                    fv.flame.setLocation(700, flameStrikeY);
                    spellDamageSystem(5, "all");
                }
                if (timePast > 130) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                mageY += 3;
                fv.mage.setLocation(mageX, mageY);
                if (mageY > mageStartY) {
                    mageX = mageStartX;
                    mageY = mageStartY;
                    fv.mage.setLocation(mageX, mageY);
                    phase = 4;
                }
            } else if (phase == 4) {
                timePast++;
                if (timePast > 30) {
                    timePast = 0;
                    flameStrike.stop();
                    phase = 0;
                    animationPlaying = false;
                }
            }
        }
    });


    public Timer fireBall = new Timer(15, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            pyroBlastX += 16;
            fv.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            animationPlaying = true;
            if (followup) {
                MusicPick.musicStart("fireball", "");
                followup = false;
            }
            if (phase == 0) {
                fv.smallPyroBlast.setVisible(true);
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
                pyroblastY = 300;
                pyroBlastX = 90;
                fv.smallPyroBlast.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                fireBall.stop();
                spellDamageSystem(8, "single");
                animationPlaying = false;
                fv.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
        }
    });

    //healer
    public Timer holyLightSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0) {
                animationPlaying = true;
                if (healTarget == 1) fv.holyLight.setLocation(warriorStartX - 220, warriorStartY - 500);
                if (healTarget == 2) fv.holyLight.setLocation(rangerStartX - 220, rangerStartY - 450);
                if (healTarget == 3) fv.holyLight.setLocation(mageStartX - 220, mageStartY - 450);
                if (healTarget == 4) fv.holyLight.setLocation(healerStartX - 220, healerStartY - 500);
                MusicPick.musicStart("holylight", "");
                fv.holyLight.setVisible(true);
                phase = 1;
            }
            if (timePast == 100) {
                spellHealSystem(30, "single");
                timePast = 0;
                fv.holyLight.setVisible(false);
                holyLightSpell.stop();
                phase = 0;
                animationPlaying = false;
            }
        }
    });

    public Timer smallHolyLightSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0) {
                animationPlaying = true;
                if (healTarget == 1) fv.smallHolyLight.setLocation(warriorStartX - 225, warriorStartY - 500);
                if (healTarget == 2) fv.smallHolyLight.setLocation(rangerStartX - 225, rangerStartY - 500);
                if (healTarget == 3) fv.smallHolyLight.setLocation(mageStartX - 225, mageStartY - 500);
                if (healTarget == 4) fv.smallHolyLight.setLocation(healerStartX - 225, healerStartY - 500);
                MusicPick.musicStart("holylight", "");
                fv.smallHolyLight.setVisible(true);
                phase = 1;
            }
            if (timePast > 100) {
                spellHealSystem(15, "single");
                timePast = 0;
                fv.smallHolyLight.setVisible(false);
                smallHolyLightSpell.stop();
                phase = 0;
                animationPlaying = false;
            }
        }
    });

    public Timer groupHealSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0) {
                animationPlaying = true;
                fv.groupHeal1.setLocation(warriorStartX + 75, warriorStartY - 500);
                fv.groupHeal2.setLocation(rangerStartX + 75, rangerStartY - 500);
                fv.groupHeal3.setLocation(mageStartX + 75, mageStartY - 500);
                fv.groupHeal4.setLocation(healerStartX + 75, healerStartY - 500);
                MusicPick.musicStart("groupheal", "");
                fv.groupHeal1.setVisible(true);
                fv.groupHeal2.setVisible(true);
                fv.groupHeal3.setVisible(true);
                fv.groupHeal4.setVisible(true);
                phase = 1;
            }
            if (timePast > 400) {
                timePast = 0;
                fv.groupHeal1.setVisible(false);
                fv.groupHeal2.setVisible(false);
                fv.groupHeal3.setVisible(false);
                fv.groupHeal4.setVisible(false);
                groupHealSpell.stop();
                phase = 0;
                spellHealSystem(10, "all");
                animationPlaying = false;
            }
        }
    });

    public Timer healerAttack = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                if (healerX == 100) MusicPick.musicStart("ding", "");
                healerX += 15;
                fv.healer.setLocation(healerX, healerY);
                if (healerX > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                healerX -= 15;
                fv.healer.setLocation(healerX, healerY);
                if (healerX <= healerStartX) {
                    healerX = healerStartX;
                    fv.healer.setLocation(healerX, healerY);
                    phase = 0;
                    spellDamageSystem(healerDamage, "single");
                    healerAttack.stop();
                    animationPlaying = false;
                    System.out.println("healer attack done");
                }
            }
        }
    });

    //enemy
    private Timer enemyTurnTimer = new Timer(7, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            animationPlaying = true;
            fv.endTurnButton.setVisible(false);
            fv.targetarrow.setVisible(false);
            if (timePast < 50) {
                if (scarecrowHp[0] < 1) timePast += 100;
            } else if (timePast < 60) {
                scarecrow1X -= 15;
                fv.scarecrow1.setLocation(scarecrow1X, scarecrow1Y);
            } else if (timePast < 70) {
                scarecrow1X += 15;
                fv.scarecrow1.setLocation(scarecrow1X, scarecrow1Y);
            } else if (timePast < 150) {
                fv.scarecrow1.setLocation(scarecrow1StartX, scarecrow1StartY);
                if (scarecrowHp[1] < 1) timePast += 100;

            } else if (timePast < 160) {
                scarecrow2X -= 15;
                fv.scarecrow2.setLocation(scarecrow2X, scarecrow2Y);
            } else if (timePast < 170) {
                scarecrow2X += 15;
                fv.scarecrow2.setLocation(scarecrow2X, scarecrow2Y);
            } else if (timePast < 250) {
                fv.scarecrow2.setLocation(scarecrow2StartX, scarecrow2StartY);
                if (scarecrowHp[2] < 1) timePast += 100;

            } else if (timePast < 260) {
                scarecrow3X -= 15;
                fv.scarecrow3.setLocation(scarecrow3X, scarecrow3Y);
            } else if (timePast < 270) {
                scarecrow3X += 15;
                fv.scarecrow3.setLocation(scarecrow3X, scarecrow3Y);
            } else if (timePast < 350) {
                fv.scarecrow3.setLocation(scarecrow3StartX, scarecrow3StartY);
                if (scarecrowHp[3] < 1) timePast += 100;

            } else if (timePast < 360) {
                scarecrow4X -= 15;
                fv.scarecrow4.setLocation(scarecrow4X, scarecrow4Y);
            } else if (timePast < 370) {
                scarecrow4X += 15;
                fv.scarecrow4.setLocation(scarecrow4X, scarecrow4Y);
            } else if (timePast == 380) {
                fv.scarecrow4.setLocation(scarecrow4StartX, scarecrow4StartY);
                enemyDamage();
                timePast = 0;
                enemyTurnTimer.stop();
                fv.endTurnButton.setVisible(true);
                animationPlaying = false;
                takeDamage.start();
                animationPlaying = false;
            }
        }
    });

    private Timer takeDamage = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePastTakeDamage++;
            if (timePastTakeDamage == 1) {

            } else if (timePastTakeDamage == 10) {
                if (warriorattacked && warriorCurrentHp > 0) fv.warrior.setVisible(false);
                if (rangerattacked && rangerCurrentHp > 0) fv.ranger.setVisible(false);
                if (mageattacked && mageCurrentHp > 0) fv.mage.setVisible(false);
                if (healerattacked && healerCurrentHp > 0) fv.healer.setVisible(false);
            } else if (timePastTakeDamage == 20) {
                if (warriorattacked && warriorCurrentHp > 0) fv.warrior.setVisible(true);
                if (rangerattacked && rangerCurrentHp > 0) fv.ranger.setVisible(true);
                if (mageattacked && mageCurrentHp > 0) fv.mage.setVisible(true);
                if (healerattacked && healerCurrentHp > 0) fv.healer.setVisible(true);
            } else if (timePastTakeDamage == 30) {
                if (warriorattacked && warriorCurrentHp > 0) fv.warrior.setVisible(false);
                if (rangerattacked && rangerCurrentHp > 0) fv.ranger.setVisible(false);
                if (mageattacked  && mageCurrentHp > 0) fv.mage.setVisible(false);
                if (healerattacked  && healerCurrentHp > 0) fv.healer.setVisible(false);
            } else if (timePastTakeDamage == 40) {
                if (warriorattacked  && warriorCurrentHp > 0) fv.warrior.setVisible(true);
                if (rangerattacked  && rangerCurrentHp > 0) fv.ranger.setVisible(true);
                if (mageattacked  && mageCurrentHp > 0) fv.mage.setVisible(true);
                if (healerattacked  && healerCurrentHp > 0) fv.healer.setVisible(true);
            } else if (timePastTakeDamage > 45) {
                warriorattacked = false;
                rangerattacked = false;
                mageattacked = false;
                healerattacked = false;
                animationPlaying = false;
                takeDamage.stop();
                timePastTakeDamage = 0;
                startNewTurn();
            }
        }
    });
}