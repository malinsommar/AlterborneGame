package fight;

import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForestCon {

    //TODO fix potions (labels uppdateras inte,går en stänga framen, fixa till panel)
    //TODO gör en metod som skickar en owned potions array till mastermodel.

    ForestFightFrame fff = new ForestFightFrame();

    //Get hp, block and damage from party
    private int warriorCurrentHp, mageCurrentHp, healerCurrentHp, rangerCurrentHp;
    private int warriorDamage, mageDamage, healerDamage, rangerDamage;
    private int warriorBlock, mageBlock, healerBlock, rangerBlock;

    private int warriorStartDamage, mageStartDamage, healerStartDamage, rangerStartDamage;
    private int warriorStartBlock, mageStartBlock, healerStartBlock, rangerStartBlock;

    //Create int's
    int timePast = 0;
    private int turns = 1;
    private int currentEnergy;
    private int warriorEnergyInt=5, mageEnergyInt, rangerEnergyInt, healerEnergyInt;

    //Animation variables
    //player
    private int warriorStartX = 170, warriorStartY = 210, warriorX = warriorStartX, warriorY = warriorStartY;
    private int rangerStartX = 70, rangerStartY = 290, rangerX = rangerStartX, rangerY = rangerStartY;
    private int mageStartX = -110, mageStartY = 290, mageX = mageStartX, mageY = mageStartY;
    private int healerStartX = -30, healerStartY = 210, healerX = healerStartX, healerY = healerStartY;
    //enemy
    private int wolf1X = 850, wolf1Y = 320, wolf1StartX = wolf1X, wolf1StartY = wolf1Y;
    private int wolf2X = 1030, wolf2Y = 320, wolf2StartX = wolf2X, wolf2StartY = wolf2Y;
    private int wolf3X = 900, wolf3Y = 400, wolf3StartX = wolf3X, wolf3StartY = wolf3Y;
    private int wolf4X = 1080, wolf4Y = 400, wolf4StartX = wolf4X, wolf4StartY = wolf4Y;
    //spells/attack
    private int swordIconX = 300, swordIconY = 300;
    private int arrowX = 120, arrowY = 360, arrowStartX = arrowX, arrowStartY = arrowY;
    private int flameStrikeY = -400;


    private int warriorMegaMath = 30; //används för halv cirkel anitamationer, PLEASE FOR THE LOVE OF GOD RENAME THIS MONSTOSITY
    private int bombMegaMath = 36;
    private int upMegaMath = 1;
    private int rightMegaMath = 1;
    private int downMegaMath = 1;
    private int leftMegaMath = 1;

    private int pyroBlastX = 45;
    private int pyroblastY = 150;
    private int bombX = 250;
    private int bombY = 300;
    private int bombStartX = 250;
    private int bombStartY = 300;

    private int objXTest = 300;
    private int objYTest = 300;
    private int objXTestStart = 300;
    private int objYTestStart = 300;
    private int xmmXTest = 0;
    private int xmmYTest = 20;


    //Another timePast to avoid conflict when they run simultaneously.
    private int timePastTakeDamage = 0;

    private int target;
    private int phase = 0;
    public boolean followup = false;
    private boolean stealthed = false;

    private int[] ownedPotions = new int[12];

    private int[] wolfHp = {20,20,20,20};

    public void startFight(){

        MusicPick.musicStart("forest1","music");

        currentEnergy = 5;

        setStartLabels();
        fff.forestFightFrame();
        hoverEffect();

        //ActionListeners
        fff.attackButton.addActionListener(e -> attackPressed());
        fff.blockButton.addActionListener(e -> blockPressed());
        fff.itemButton.addActionListener(e -> fff.itemPressed());
        fff.skillButton.addActionListener(e -> spellMenuActive()); //for now
        fff.endTurnButton.addActionListener(e-> startNewTurn());
        fff.skill1Button.addActionListener(e -> {skill1();});
        fff.skill2Button.addActionListener(e -> {skill2();});
        fff.skill3Button.addActionListener(e -> {skill3();});
        fff.skill4Button.addActionListener(e -> {skill4();});
        fff.returnButton.addActionListener(e-> spellMenuInactive());

        //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
        fff.potion1.addActionListener(e->usePotion(1));
        fff.potion2.addActionListener(e->usePotion(2));
        fff.potion3.addActionListener(e->usePotion(3));
        fff.potion4.addActionListener(e->usePotion(4));
        fff.potion5.addActionListener(e->usePotion(5));
        fff.potion6.addActionListener(e->usePotion(6));
        fff.potion7.addActionListener(e->usePotion(7));
        fff.potion8.addActionListener(e->usePotion(8));
        fff.potion9.addActionListener(e->usePotion(9));
        fff.potion10.addActionListener(e->usePotion(10));
        fff.potion11.addActionListener(e->usePotion(11));
        fff.potion12.addActionListener(e->usePotion(12));

        //Dispose the item frame.
        fff.exitInventory.addActionListener(e->fff.inventory.dispose());
    }

    //When you press "end turn" button.
    private void startNewTurn(){
        turns++;

        //Warrior's turn
        if (turns==1 && warriorCurrentHp>0){
            warriorEnergyInt+=5; //Get energy
            currentEnergy=warriorEnergyInt; //Update energy.
            warriorBlock=warriorStartBlock; //Update block, reset extra block.
            warriorDamage = warriorStartDamage; //Update damage, reset extra block.

            //Energy cant go over 10.
            if (warriorEnergyInt>10){
                warriorEnergyInt=10;
            }
            //Update labels.
            fff.whosTurn.setText("Warrior's turn");
            fff.playersHp.setText("Hp: "+warriorCurrentHp);
            fff.energy.setText("Energy: "+warriorEnergyInt);
            fff.block.setText("Block: "+warriorBlock);
        }
        //If warrior is dead, skip.
        if (turns==1 && warriorCurrentHp<1){
            turns=2;
        }
        //Ranger's turn
        if (turns==2 && rangerCurrentHp>0){
            rangerEnergyInt+=5;
            currentEnergy=rangerEnergyInt;
            rangerBlock=rangerStartBlock;
            rangerDamage = rangerStartDamage;

            if (rangerEnergyInt>10){
                rangerEnergyInt=10;
            }
            fff.whosTurn.setText("Ranger's turn");
            fff.playersHp.setText("Hp: "+rangerCurrentHp);
            fff.energy.setText("Energy: "+rangerEnergyInt);
            fff.block.setText("Block: "+rangerBlock);
        }
        //If ranger is dead, skip.
        if (turns==2 && rangerCurrentHp<1){
            turns=3;
        }
        //Mage's turn
        if (turns==3 && mageCurrentHp>0){
            mageEnergyInt+=5;
            currentEnergy=mageEnergyInt;
            mageBlock=mageStartBlock;
            mageDamage = mageStartDamage;

            if (mageEnergyInt>10){
                mageEnergyInt=10;
            }
            fff.whosTurn.setText("Mage's turn");
            fff.playersHp.setText("Hp: "+mageCurrentHp);
            fff.energy.setText("Energy: "+mageEnergyInt);
            fff.block.setText("Block: "+mageBlock);
        }
        //If mage is dead, skip.
        if (turns==3 && mageCurrentHp<1){
            turns=4;
        }
        //Healer's turn
        if (turns==4 && healerCurrentHp>0){
            healerEnergyInt+=5;
            currentEnergy=healerEnergyInt;
            healerBlock=healerStartBlock;
            healerDamage = healerStartDamage;

            if (healerEnergyInt>10){
                healerEnergyInt=10;
            }
            fff.whosTurn.setText("Healer's turn");
            fff.playersHp.setText("Hp: "+healerCurrentHp);
            fff.energy.setText("Energy: "+healerEnergyInt);
            fff.block.setText("Block: "+healerBlock);

        }
        //If healer is dead, skip.
        if (turns==4 && healerCurrentHp<1){
            turns=5;
        }
        //  ***ENEMIES TURN***
        if (turns==5){enemyTurnTimer.start();}
    }

    private void skill1(){
        if (turns == 1){
            charge.start();
        }
        if (turns == 2){
            volley.start();
        }
        if (turns == 3){
            pyroBlastX = 90;
            pyroblastY = 300;
            followup = true;
            fireBall.start();
        }
        if (turns == 4){
            holyLightSpell.start();
        }
    }

    private void skill2(){
        if (turns == 1){
            dunk.start();
        }
        if(turns == 2){
            bombthrow.start();
        }
        if (turns == 3){

        }
        if (turns == 4){
            smallHolyLightSpell.start();
        }
    }

    private void skill3(){
        if (turns == 1){
            followup = true;
            shout.start();
        }
        if(turns == 2){

        }
        if (turns == 3){
            flameStrike.start();
        }
        if (turns == 4){
            groupHealSpell.start();
        }
    }

    private void skill4(){
        if (turns == 1){
            shout.start();
        }
        if(turns == 2){
            stealth();
        }
        if (turns == 3){
            pyroBlast.start();
        }
        if (turns == 4){

        }
    }

    private void spellMenuActive(){
        fff.attackButton.setVisible(false);
        fff.blockButton.setVisible(false);
        fff.itemButton.setVisible(false);
        fff.skillButton.setVisible(false);
        fff.endTurnButton.setVisible(false);

        fff.skill1Button.setVisible(true);
        fff.skill2Button.setVisible(true);
        fff.skill3Button.setVisible(true);
        fff.skill4Button.setVisible(true);
        fff.returnButton.setVisible(true);

        //warrior
        if (turns == 1){
            fff.skill1Button.setText("Charge");
            fff.skill2Button.setText("Slam");
            fff.skill3Button.setText("Battlecry");
            fff.skill4Button.setText("Demoralize");
        }
        //ranger
        if (turns == 2){
            fff.skill1Button.setText("Volley");
            fff.skill2Button.setText("Bomb");
            fff.skill3Button.setText("TBA");
            fff.skill4Button.setText("Stealth");
        }
        //mage
        if (turns == 3) {
            fff.skill1Button.setText("Fireball");
            fff.skill2Button.setText("TBA");
            fff.skill3Button.setText("Flamestrike");
            fff.skill4Button.setText("Pyroblast");
        }
        //healer
        if (turns == 4){
            fff.skill1Button.setText("Holy light");
            fff.skill2Button.setText("Blessed Light");
            fff.skill3Button.setText("Party heal");
            fff.skill4Button.setText("Real");
        }
    }
    //When player press block
    private void blockPressed(){

        //If its warrior's turn and player has 2 or more energy.
        if(turns==1 && warriorEnergyInt>1){
            warriorEnergyInt=warriorEnergyInt-2;
            currentEnergy=currentEnergy-2;
            warriorBlock+=5;
            fff.energy.setText("Energy: "+warriorEnergyInt);
            fff.block.setText("Block: "+warriorBlock);
        }
        //If its ranger's turn and player has 2 or more energy.
        else if(turns==2 && rangerEnergyInt>1){
            rangerEnergyInt=rangerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            rangerBlock+=5;
            fff.energy.setText("Energy: "+rangerEnergyInt);
            fff.block.setText("Block: "+rangerBlock);
        }
        //If its mage's turn and player has 2 or more energy.
        else if(turns==3 && mageEnergyInt>1){
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            mageBlock+=5;
            fff.energy.setText("Energy: "+mageEnergyInt);
            fff.block.setText("Block: "+mageBlock);
        }
        //If its healer's turn and player has 2 or more energy.
        else if(turns==4 && healerEnergyInt>1){
            healerEnergyInt=healerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            healerBlock+=5;
            fff.energy.setText("Energy: "+healerEnergyInt);
            fff.block.setText("Block: "+healerBlock);
        }
    }
    //When you press the "attack button".
    private void attackPressed(){

        //If its warrior's turn and player has 2 or more energy.
        if(turns==1 && warriorEnergyInt>1){
            warriorEnergyInt=warriorEnergyInt-2; //Energy -2.
            currentEnergy=currentEnergy-2; // Update currentEnergy.
            fff.energy.setText("Energy: "+warriorEnergyInt); //Update energyLabel
            warriorAttackWolf(); //Warrior deals damage to a random wolf.
            mobDeath(); //Check if enemy died.
            isFightOver(); //Check if all enemies/party members are dead.
        }
        //If its ranger's turn and player has 2 or more energy.
        else if(turns==2 && rangerEnergyInt>1){
            rangerEnergyInt=rangerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            fff.energy.setText("Energy: "+rangerEnergyInt);
            rangerAttackWolf();
            mobDeath();
            isFightOver();
        }
        //If its mage's turn and player has 2 or more energy.
        else if(turns==3 && mageEnergyInt>1){
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            fff.energy.setText("Energy: "+mageEnergyInt);
            mageAttackWolf();
            mobDeath();
            isFightOver();
        }
        //If its healer's turn and player has 2 or more energy.
        else if(turns==4 && healerEnergyInt>1){
            healerEnergyInt=healerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            fff.energy.setText("Energy: "+healerEnergyInt);
            healerAttackWolf();
            mobDeath();
            isFightOver();
        }
    }

    //When warrior press the "attack button".
    private void warriorAttackWolf() {

        //Loops until it hits an live wolf.
        while (true) {
            //Randomize a number between 1-4.
            int target = (int) (Math.random() * 4)+1;
            tackle.start();

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                wolfHp[0] = wolfHp[0] - warriorDamage;//Wolf take damage equals to warriors damage.
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);//Update wolf 1 hp label.
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                wolfHp[1] = wolfHp[1] - warriorDamage;
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                wolfHp[2] = wolfHp[2] - warriorDamage;
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                wolfHp[3] = wolfHp[3] - warriorDamage;
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }
    }

    //When mage press the "attack button".
    private void mageAttackWolf(){

        //Loops until it hits an live wolf.
        while (true) {
            //Randomize a number between 1-4.
            int target = (int) (Math.random() * 4)+1;

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                wolfHp[0] = wolfHp[0] - mageDamage;//Wolf take damage equals to mage's damage.
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]); //Update wolf 1 hp label.
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                wolfHp[1] = wolfHp[1] - mageDamage;
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                wolfHp[2] = wolfHp[2] - mageDamage;
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                wolfHp[3] = wolfHp[3] - mageDamage;
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }
    }

    //When ranger press the "attack button".
    private void rangerAttackWolf(){
        //Loops until it hits an live wolf.
        while (true) {
            //Randomize a number between 1-4.
            int target = (int) (Math.random() * 4)+1;
            shoot.start();

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                }
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[1] = wolfHp[1] - rangerDamage;
                }
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[2] = wolfHp[2] - rangerDamage;
                }
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[3] = wolfHp[3] - rangerDamage;
                }
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }
    }

    //When healer press the "attack button".
    private void healerAttackWolf(){
        //Loops until it hits an live wolf.
        while (true) {
            int target = (int) (Math.random() * 4)+1;

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                wolfHp[0] = wolfHp[0] - healerDamage;//Wolf take damage equals to healers damage.
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                wolfHp[1] = wolfHp[1] - healerDamage;
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                wolfHp[2] = wolfHp[2] - healerDamage;
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                wolfHp[3] = wolfHp[3] - healerDamage;
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }
    }

    //Checks if all of the enemies or party-members are dead.
    private void isFightOver() {
        //If all of the wolves are dead. Open lootScreen.
        if (wolfHp[0] < 1 && wolfHp[1] < 1 && wolfHp[2] < 1 && wolfHp[3] < 1) {
            MusicPick.musicStop();
            //lootscreen
        }
        //In the whole party is dead, game is over. Send to loseScreen.
        if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
            fff.forestFightJFrame.dispose();
        }
        //If none of these are true, nothing happens and the fight goes on.
    }

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
                    target=1;
                }
                //If warrior is alive.
                if (warriorCurrentHp >0) {
                    wolfDamage=wolfDamage-warriorBlock; //Warrior take damage equal to wolf damage.
                    warriorCurrentHp = warriorCurrentHp - wolfDamage; //Update warrior hp.
                    fff.player1Hp.setText("Warrior: "+warriorCurrentHp); //Update hp Label.
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
                if (mageCurrentHp >0) {
                    wolfDamage=wolfDamage-mageBlock;
                    mageCurrentHp = mageCurrentHp - wolfDamage;
                    fff.player2Hp.setText("Mage:    "+mageCurrentHp);
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
                if (rangerCurrentHp >0) {
                    wolfDamage=wolfDamage-rangerBlock;
                    rangerCurrentHp = rangerCurrentHp - wolfDamage;
                    fff.player3Hp.setText("Ranger:  "+rangerCurrentHp);
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
                if (healerCurrentHp >0) {
                    wolfDamage=wolfDamage-healerBlock;
                    healerCurrentHp = healerCurrentHp - wolfDamage;
                    fff.player4Hp.setText("Healer:   "+healerCurrentHp);
                    break;
                }
            }
        }
    }

    //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void mobDeath(){

        if(wolfHp[0]<=0){
            fff.wolf1Hp.setText("Wolf 1: 0");
            fff.wolf1.setVisible(false);
        }
        if(wolfHp[1]<=0){
            fff.wolf2Hp.setText("Wolf 2: 0");
            fff.wolf2.setVisible(false);
        }
        if(wolfHp[2]<=0){
            fff.wolf3Hp.setText("Wolf 3: 0");
            fff.wolf3.setVisible(false);
        }
        if(wolfHp[3]<=0){
            fff.wolf4Hp.setText("Wolf 4: 0");
            fff.wolf4.setVisible(false);
        }
    }

    //Checks if any party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void partyDeath(){

        if(warriorCurrentHp<=0){
            warriorCurrentHp = 0;
            fff.player1Hp.setText("Warrior: "+warriorCurrentHp);
            fff.warrior.setVisible(false);
        }
        if(mageCurrentHp<=0){
            mageCurrentHp = 0;
            fff.player2Hp.setText("Mage:    "+mageCurrentHp);
            fff.mage.setVisible(false);
        }
        if(rangerCurrentHp<=0){
            rangerCurrentHp = 0;
            fff.player3Hp.setText("Ranger:  "+rangerCurrentHp);
            fff.ranger.setVisible(false);
        }
        if(healerCurrentHp<=0){
            healerCurrentHp = 0;
            fff.player4Hp.setText("Healer:  "+healerCurrentHp);
            fff.healer.setVisible(false);
        }
    }
    private void spellMenuInactive(){
        fff.attackButton.setVisible(true);
        fff.blockButton.setVisible(true);
        fff.itemButton.setVisible(true);
        fff.skillButton.setVisible(true);
        fff.endTurnButton.setVisible(true);

        fff.skill1Button.setVisible(false);
        fff.skill2Button.setVisible(false);
        fff.skill3Button.setVisible(false);
        fff.skill4Button.setVisible(false);
        fff.returnButton.setVisible(false);
    }

    public void getInventory(int[] potions){

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

    public void getPlayerStats(int[] warrior, int[] mage, int[] healer, int[] ranger){

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
    }

    public void setStartLabels(){

        fff.potion1Label = new JLabel("" + ownedPotions[0]);
        fff.potion2Label = new JLabel("" + ownedPotions[1]);
        fff.potion3Label = new JLabel("" + ownedPotions[2]);
        fff.potion4Label = new JLabel("" + ownedPotions[3]);
        fff.potion5Label = new JLabel("" + ownedPotions[4]);
        fff.potion6Label = new JLabel("" + ownedPotions[5]);
        fff.potion7Label = new JLabel("" + ownedPotions[6]);
        fff.potion8Label = new JLabel("" + ownedPotions[7]);
        fff.potion9Label = new JLabel("" + ownedPotions[8]);
        fff.potion10Label = new JLabel("" + ownedPotions[9]);
        fff.potion11Label = new JLabel("" + ownedPotions[10]);
        fff.potion12Label = new JLabel("" + ownedPotions[11]);

        fff.wolf1Hp = new JLabel("Wolf 1: "+ wolfHp[0]);
        fff.wolf2Hp = new JLabel("Wolf 2: "+ wolfHp[1]);
        fff.wolf3Hp = new JLabel("Wolf 3: "+ wolfHp[2]);
        fff.wolf4Hp = new JLabel("Wolf 4: "+ wolfHp[3]);

        fff.playersHp = new JLabel("Hp: "+warriorCurrentHp);
        fff.player1Hp = new JLabel("Warrior: "+ warriorCurrentHp);
        fff.player2Hp = new JLabel("Mage:    "+ mageCurrentHp);
        fff.player3Hp = new JLabel("Ranger:  "+ rangerCurrentHp);
        fff.player4Hp = new JLabel("Healer:  "+ healerCurrentHp);
        fff.block = new JLabel("Block: "+warriorBlock);
    }

    //Add hover effect to buttons.
    private void hoverEffect() {
        //Attack Hover
        fff.attackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            //Change button color while hovering depending on your current energy.
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(currentEnergy>=2) {
                    fff.attackButton.setBackground(Color.lightGray);
                }
                if(currentEnergy<2){
                    fff.attackButton.setBackground(Color.pink);
                }
            }
            //Change back when not hovering over button
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.attackButton.setBackground(Color.white);
            }
        });

        //Block Hover
        fff.blockButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(currentEnergy>=2) {
                    fff.blockButton.setBackground(Color.lightGray);
                }
                if(currentEnergy<2){
                    fff.blockButton.setBackground(Color.pink);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.blockButton.setBackground(Color.white);
            }
        });

        //Item Hover
        fff.itemButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fff.itemButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.itemButton.setBackground(Color.white);
            }
        });
        //Skill Hover
        fff.skillButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fff.skillButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.skillButton.setBackground(Color.white);
            }
        });

        //End turn Hover
        fff.endTurnButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fff.endTurnButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.endTurnButton.setBackground(Color.white);
            }
        });
    }

    //ANIMATIONER OCH TIMERS

    private Timer charge = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                MusicPick.musicStart("charge", "");
                phase = 1;
            }
            else if (phase == 1) {
                warriorX += 20;
                fff.warrior.setLocation(warriorX, warriorY);
                if (warriorX > 2000) {
                    phase = 2;
                }
            }
            else if (phase == 2) {
                warriorX = warriorStartX;
                warriorY = warriorStartY;
                fff.warrior.setLocation(warriorX,warriorY);
                phase = 0;
                charge.stop();
            }
        }
    });

    private Timer volley = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            arrowX += 30;
            fff.volley1.setLocation(arrowX, arrowY);
            fff.volley2.setLocation(arrowX - 200, arrowY);
            fff.volley3.setLocation(arrowX - 400, arrowY);
            if (phase == 0) {
                fff.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 1;
            }
            else if (phase == 1 && arrowX > arrowStartX + 300) {
                fff.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 2;
            }
            else if (phase == 2 && arrowX > arrowStartX + 600) {
                fff.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 3;
            }
            else if (phase == 3 && arrowX > 1000) {
                fff.volley1.setVisible(false);
                phase = 4;
            }
            else if (phase == 4 && arrowX > 1200) {
                fff.volley2.setVisible(false);
                phase = 5;
            }
            else if (phase == 5 && arrowX > 1400) {
                fff.volley3.setVisible(false);
                phase = 6;
            }
            if (phase == 6) {
                arrowX = 300;
                fff.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 7;
            }
            else if (phase == 7 && arrowX > arrowStartX + 300) {
                fff.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 8;
            }
            else if (phase == 8 && arrowX > arrowStartX + 600) {
                fff.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 9;
            }
            else if (phase == 9 && arrowX > 1000) {
                fff.volley1.setVisible(false);
                phase = 10;
            }
            else if (phase == 10 && arrowX > 1200) {
                fff.volley2.setVisible(false);
                phase = 11;
            }
            else if (phase == 11 && arrowX > 1400) {
                fff.volley3.setVisible(false);
                phase = 12;
                arrowX = 300;
            }
            if (phase == 12) {
                fff.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 13;
            }
            else if (phase == 13 && arrowX > arrowStartX + 300) {
                fff.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 14;
            }
            else if (phase == 14 && arrowX > arrowStartX + 600) {
                fff.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 15;
            }
            else if (phase == 15 && arrowX > 1000) {
                fff.volley1.setVisible(false);
                phase = 16;
            }
            else if (phase == 16 && arrowX > 1200) {
                fff.volley2.setVisible(false);
                phase = 17;
            }
            else if (phase == 17 && arrowX > 1400) {
                fff.volley3.setVisible(false);
                phase = 18;
            }
            else if (phase == 18){
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
            }
            else if (phase == 1){
                mageY -= 3;
                fff.mage.setLocation(mageX, mageY);
                if (mageY < 50) {
                    phase = 2;
                }}
            else if (phase == 2) {
                if (mageY < 50) {
                    mageY = 50;
                    fff.mage.setLocation(mageX, mageY);
                }
                timePast++;
                if (timePast < 100) {
                    if (timePast % 2 == 1) {
                        mageX += 6;
                        fff.mage.setLocation(mageX, mageY);
                        flameStrikeY += 13;
                        fff.flame.setLocation(900, flameStrikeY);
                    } else {
                        mageX -= 6;
                        fff.mage.setLocation(mageX, mageY);
                    }
                }
                if (timePast == 102) {
                    fff.mage.setLocation(mageX, mageY);
                    fff.fireStorm.setVisible(true);
                    flameStrikeY = -400;
                    fff.flame.setLocation(700, flameStrikeY);
                    wolfHp[0] = wolfHp[0] - mageDamage/2;
                    fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);
                    wolfHp[1] = wolfHp[1] - mageDamage/2;
                    fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                    wolfHp[2] = wolfHp[2] - mageDamage/2;
                    fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                    wolfHp[3] = wolfHp[3] - mageDamage/2;
                    fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                }
                if (timePast > 130) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                mageY += 3;
                fff.mage.setLocation(mageX, mageY);
                if (mageY > mageStartY) {
                    mageX = mageStartX;
                    mageY = mageStartY;
                    fff.mage.setLocation(mageX, mageY);
                    phase = 4;
                }
            } else if (phase == 4) {
                timePast++;
                if (timePast > 30) {
                    timePast = 0;
                    fff.fireStorm.setVisible(false);
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
            fff.endTurnButton.setVisible(false);
            if (timePast < 50) {
                if (wolfHp[0]<1)timePast = 140;
                fff.whosTurn.setText("Wolf 1 turn");
                fff.playersHp.setText("Hp: " + wolfHp[0]);
                fff.energy.setText("  ");
            }

            else if (timePast == 50 && wolfHp[0] > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 60) {
                wolf1X -= 15;
                fff.wolf1.setLocation(wolf1X, wolf1Y);
            } else if (timePast < 70) {
                wolf1X += 15;
                fff.wolf1.setLocation(wolf1X, wolf1Y);
            }

            else if (timePast < 150) {
                if (wolfHp[1]<1)timePast = 240;
                fff.wolf1.setLocation(wolf1StartX, wolf1StartY);
                fff.whosTurn.setText("Wolf 2 turn");
                fff.playersHp.setText("Hp: " + wolfHp[1]);
                fff.energy.setText("  ");
            } else if (timePast == 150 && wolfHp[1] > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 160) {
                wolf2X -= 15;
                fff.wolf2.setLocation(wolf2X, wolf2Y);
            } else if (timePast < 170) {
                wolf2X += 15;
                fff.wolf2.setLocation(wolf2X, wolf2Y);
            }

            else if (timePast < 250) {
                if (wolfHp[2]<1)timePast = 340;
                fff.wolf2.setLocation(wolf2StartX, wolf2StartY);
                fff.whosTurn.setText("Wolf 3 turn");
                fff.playersHp.setText("Hp: " + wolfHp[2]);
                fff.energy.setText("  ");
            } else if (timePast == 250 && wolfHp[2] > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 260) {
                wolf3X -= 15;
                fff.wolf3.setLocation(wolf3X, wolf3Y);
            } else if (timePast < 270) {
                wolf3X += 15;
                fff.wolf3.setLocation(wolf3X, wolf3Y);
            }

            else if (timePast < 350) {
                if (wolfHp[3]<1)timePast = 440;
                fff.wolf3.setLocation(wolf3StartX, wolf3StartY);
                fff.whosTurn.setText("Wolf 4 turn");
                fff.playersHp.setText("Hp: " + wolfHp[3]);
                fff.energy.setText("  ");
            } else if (timePast == 350 && wolfHp[3] > 0) {
                wolfAttack();
                partyDeath();
            } else if (timePast < 360) {
                wolf4X -= 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 370) {
                wolf4X += 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 450) {
                fff.wolf4.setLocation(wolf4StartX, wolf4StartY);
                enemyTurnTimer.stop();
                turns = 0;
                timePast = 0;
                startNewTurn();
                fff.endTurnButton.setVisible(true);
            }
        }
    });

    Timer shoot = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                fff.arrow.setVisible(true);
                if (arrowX == 121) {
                    MusicPick.musicStart("ding", "");
                }
                arrowX += 30;
                fff.arrow.setLocation(arrowX, arrowY);
                if (arrowX > 1000) {
                    phase = 1;
                }
            } else if (phase == 1) {
                fff.arrow.setVisible(false);
                arrowX = arrowStartX;
                fff.arrow.setLocation(arrowX, arrowY);
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
                warriorX += 15;
                fff.warrior.setLocation(warriorX, warriorY);
                if (warriorX > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                warriorX -= 15;
                fff.warrior.setLocation(warriorX, warriorY);
                if (warriorX <= warriorStartX) {
                    warriorX = warriorStartX;
                    fff.warrior.setLocation(warriorX, warriorY);
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
                if (target == 0) fff.warrior.setVisible(false);
                if (target == 1) fff.ranger.setVisible(false);
                if (target == 2) fff.mage.setVisible(false);
                if (target == 3) fff.healer.setVisible(false);
            } else if (timePastTakeDamage == 20) {
                if (target == 0) fff.warrior.setVisible(true);
                if (target == 1) fff.ranger.setVisible(true);
                if (target == 2) fff.mage.setVisible(true);
                if (target == 3) fff.healer.setVisible(true);
            } else if (timePastTakeDamage == 30) {
                if (target == 0) fff.warrior.setVisible(false);
                if (target == 1) fff.ranger.setVisible(false);
                if (target == 2) fff.mage.setVisible(false);
                if (target == 3) fff.healer.setVisible(false);
            } else if (timePastTakeDamage == 40) {
                if (target == 0) fff.warrior.setVisible(true);
                if (target == 1) fff.ranger.setVisible(true);
                if (target == 2) fff.mage.setVisible(true);
                if (target == 3) fff.healer.setVisible(true);
                takeDamage.stop();
                timePastTakeDamage = 0;
            }
        }
    });

    private Timer dunk = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0){
                MusicPick.musicStart("charge", "");
                phase = 1;
            }
            if (phase == 1) {
                warriorMegaMath -=2;
                warriorX += 20;
                warriorY -= warriorMegaMath;
                fff.warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    phase = 2;
                }
            } else if (phase == 2) {
                timePast++;
                if(timePast == 30) {
                    warriorY = warriorStartY;
                    warriorX = warriorStartX;
                    fff.warrior.setLocation(warriorX, warriorY);
                    timePast = 0;
                    warriorMegaMath = 30;
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
                fff.smallPyroBlast.setVisible(true);
            }
            else if (timePast < 200) {
                fff.smallPyroBlast.setVisible(false);
                fff.mediumPyroBlast.setVisible(true);
            }
            else if (timePast < 350) {
                fff.mediumPyroBlast.setVisible(false);
                fff.bigPyroBlast.setVisible(true);
            }
            else if (timePast < 400 ){
                pyroBlastX += 3;
                pyroblastY -= 1;
                fff.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else if (timePast < 460){
                pyroBlastX += 3;
                pyroblastY += 1;
                fff.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else if (timePast < 530){
                pyroBlastX += 3;
                pyroblastY -= 1;
                fff.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else if (timePast < 590){
                pyroBlastX += 4;
                pyroblastY += 1;
                fff.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else {
                fff.bigPyroBlast.setVisible(false);
                timePast = 0;
                pyroBlastX = 45;
                pyroblastY = 150;
                fff.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                pyroBlast.stop();
            }
        }
    });
    private Timer shout = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                MusicPick.musicStart("demoshout", " ");
                phase = 1;
            }
            else if (phase == 1){
                warriorY -= 5;
                fff.warrior.setLocation(warriorX, warriorY);
                if (warriorY < warriorStartY + 50) {
                    phase = 2;
                }}
            else if (phase == 2) {
                if (warriorY < 50) {
                    warriorY = 50;
                    fff.warrior.setLocation(warriorX, warriorY);
                }
                timePast++;
                if (timePast < 50) {
                    if (timePast % 2 == 1) {
                        warriorX += 4;
                        fff.warrior.setLocation(warriorX, warriorY);
                    } else {
                        warriorX -= 4;
                        fff.warrior.setLocation(warriorX, warriorY);
                    }
                }
                if (timePast > 50) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                warriorY += 3;
                fff.warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    warriorX = warriorStartX;
                    warriorY = warriorStartY;
                    fff.warrior.setLocation(warriorX, warriorY);
                    phase = 4;
                    phase = 0;
                    mobDeath();
                    shout.stop();
                    if (followup){battlecry.start();
                        followup = false;
                    }
                    else {
                        demoralized.start();
                        System.out.println("working");
                    }
                }
            }
        }
    });
    private Timer fireBall = new Timer(15, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            pyroBlastX += 16;
            fff.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            if (followup){MusicPick.musicStart("fireball", "");
                followup = false;
            }
            if (phase == 0){
                fff.smallPyroBlast.setVisible(true);
                upMegaMath *=2;
                pyroblastY -= upMegaMath;
            }
            if (timePast % 3 == 0){phase++;}
            if (phase == 5) {
                phase = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
            }
            if (phase == 2) upMegaMath = 1;

            if (phase == 1 || phase == 2) { //höger
                rightMegaMath *=2;
                pyroBlastX += rightMegaMath;
            }
            if (phase == 2 || phase == 3) { //ner
                downMegaMath *=2;
                pyroblastY += downMegaMath;
            }
            if (phase == 3 || phase == 4) { //vänster
                leftMegaMath *=2;
                pyroBlastX -= leftMegaMath;
            }
            if (phase == 4 || phase == 1) { //flyger uppåt
                upMegaMath *=2;
                pyroblastY -= upMegaMath;
            }
            if(timePast == 50) {
                pyroblastY = 150;
                pyroBlastX = 45;
                fff.smallPyroBlast.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                fireBall.stop();
                fff.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
        }
    });

    private Timer battlecry = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            fff.swordIcon.setLocation(swordIconX, swordIconY);
            if (phase == 0) {
                if (upMegaMath == 1) MusicPick.musicStart("demoshout", "");
                fff.swordIcon.setVisible(true);
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
                fff.swordIcon.setVisible(false);
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
    private Timer demoralized = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            fff.demoSword1.setLocation(swordIconX + 550, swordIconY + 50);
            fff.demoSword2.setLocation(swordIconX + 650, swordIconY + 100);
            fff.demoSword3.setLocation(swordIconX + 750, swordIconY + 50);
            fff.demoSword4.setLocation(swordIconX + 850, swordIconY + 100);


            if (phase == 0) {
                fff.demoSword1.setVisible(true);
                fff.demoSword2.setVisible(true);
                fff.demoSword3.setVisible(true);
                fff.demoSword4.setVisible(true);
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
                fff.demoSword1.setVisible(false);
                fff.demoSword2.setVisible(false);
                fff.demoSword3.setVisible(false);
                fff.demoSword4.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                demoralized.stop();
            }
        }
    });
    private Timer bombthrow = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0){
                MusicPick.musicStart("ding", "");
                phase = 1;
                fff.bomb.setVisible(true);
            }
            if (phase == 1) {
                bombMegaMath -=2;
                bombX += 20;
                bombY -= bombMegaMath;
                fff.bomb.setLocation(bombX, bombY);
                if (bombY > bombStartY) {
                    phase = 2;
                }
            } else if (phase == 2) {
                timePast++;
                if(timePast == 30) {
                    bombY = bombStartY;
                    bombX = bombStartX;
                    fff.bomb.setLocation(bombX, bombY);
                    fff.bomb.setVisible(false);
                    fff.explode.setVisible(true);
                }
                if(timePast == 60){
                    bombMegaMath = 36;
                    fff.bomb.setVisible(false);
                    fff.explode.setVisible(false);
                    timePast = 0;
                    phase = 0;
                    bombthrow.stop();
                }
            }
        }
    });

    //not used, don't remove
    private Timer bombcircletest = new Timer(5, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            fff.bomb.setLocation(objXTest, objYTest);
            timePast++;
            if (phase == 0){
                MusicPick.musicStart("ding", "");
                phase = 1;
                fff.bomb.setVisible(true);
            }
            if (phase == 1) {
                xmmYTest -=1;
                xmmXTest -=1;
                objYTest -= xmmYTest;
                objXTest -= xmmXTest;
                if (xmmYTest <= 0) {
                    phase = 2;
                }

            }
            if (phase == 2){
                xmmYTest -=1;
                xmmXTest +=1;
                objYTest -= xmmYTest;
                objXTest -= xmmXTest;
                if (xmmXTest >= 0) {
                    phase = 3;
                }
            }
            if (phase == 3){
                xmmYTest +=1;
                xmmXTest +=1;
                objYTest -= xmmYTest;
                objXTest -= xmmXTest;
                if (xmmYTest >= 0) {
                    phase = 4;
                }
            }
            if (phase == 4){
                xmmYTest +=1;
                xmmXTest -=1;
                objYTest -= xmmYTest;
                objXTest -= xmmXTest;
                if (xmmXTest <= 0) {
                    phase = 1;
                }
            }

            if(timePast > 300){
                fff.bomb.setVisible(false);
                timePast = 0;
                phase = 0;
                objXTest = objXTestStart;
                objYTest = objYTestStart;
                xmmXTest = 0;
                xmmYTest = 20;
                bombcircletest.stop();
            }
        }
    });

    private void stealth(){
        if (!stealthed){
            MusicPick.musicStart("stealth", "");
            fff.ranger.setVisible(false);
            fff.stealthranger.setVisible(true);
            stealthed = true;
        }
        else{
            MusicPick.musicStart("unstealth", "");
            fff.ranger.setVisible(true);
            fff.stealthranger.setVisible(false);
            stealthed = false;
        }
        /*
        lägg till
        if (stealthed == true){stealth();}
        i alla ranger abilities
        och öka skadan på nåt sätt
         */
    }

    private Timer holyLightSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0){
            fff.holyLight.setLocation(warriorStartX -200, warriorStartY -500);
            MusicPick.musicStart("holylight", "");
            fff.holyLight.setVisible(true);
            phase = 1;
            }
            if (timePast == 100){
                timePast = 0;
                fff.holyLight.setVisible(false);
                holyLightSpell.stop();
                phase = 0;
            }
        }
    });

    private Timer smallHolyLightSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0){
                fff.smallHolyLight.setLocation(warriorStartX -225, warriorStartY -500);
                MusicPick.musicStart("holylight", "");
                fff.smallHolyLight.setVisible(true);
                phase = 1;
            }
            if (timePast == 100){
                timePast = 0;
                fff.smallHolyLight.setVisible(false);
                smallHolyLightSpell.stop();
                phase = 0;
            }
        }
    });

    private Timer groupHealSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0){
                fff.groupHeal1.setLocation(warriorStartX, warriorStartY);
                fff.groupHeal2.setLocation(rangerStartX, rangerStartY);
                fff.groupHeal3.setLocation(mageStartX, mageStartY);
                fff.groupHeal4.setLocation(healerStartX, healerStartY);
                MusicPick.musicStart("groupheal", "");
                fff.groupHeal1.setVisible(true);
                fff.groupHeal2.setVisible(true);
                fff.groupHeal3.setVisible(true);
                fff.groupHeal4.setVisible(true);
                phase = 1;
            }
            if (timePast == 700){
                timePast = 0;
                fff.groupHeal1.setVisible(false);
                fff.groupHeal2.setVisible(false);
                fff.groupHeal3.setVisible(false);
                fff.groupHeal4.setVisible(false);
                groupHealSpell.stop();
                phase = 0;
            }
        }
    });



    //Get the effect from potions.
    private void usePotion(int potion) {

        //Warrior
        if (turns == 1) {
            //If potion 1 "minor healing potion" is pressed.
            if (potion == 1) {
                //If player own that potion.
                if (ownedPotions[0] > 0) {
                    warriorCurrentHp += 10; //Heal warrior equals to the potions heal.
                    fff.playersHp.setText("Hp: " + warriorCurrentHp); //Update Warrior's hp Label.
                    fff.player1Hp.setText("Warrior: " + warriorCurrentHp); // Update currentPlayer Hp label.
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]); //Update ownedPotion Label.
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    warriorCurrentHp += 30;
                    fff.playersHp.setText("Hp: " + warriorCurrentHp);
                    fff.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    warriorCurrentHp += 60;
                    fff.playersHp.setText("Hp: " + warriorCurrentHp);
                    fff.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[2]-=1;
                    fff.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    warriorBlock += 5;
                    fff.block.setText("Block: " + warriorBlock);
                    ownedPotions[3]-=1;
                    fff.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    warriorBlock += 20;
                    fff.block.setText("Block: " + warriorBlock);
                    ownedPotions[4]-=1;
                    fff.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    warriorBlock += 50;
                    fff.block.setText("Block: " + warriorBlock);
                    ownedPotions[5]-=1;
                    fff.potion6Label.setText(""+ownedPotions[5]);
                }
            } else if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    warriorEnergyInt += 3;
                    fff.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[6]-=1;
                    fff.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    warriorCurrentHp += 5;
                    fff.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[7]-=1;
                    fff.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    warriorCurrentHp += 10;
                    fff.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[8]-=1;
                    fff.potion9Label.setText(""+ownedPotions[8]);
                }
            }
            if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    warriorDamage += 5;
                    ownedPotions[9]-=1;
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            }
            if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    warriorDamage += 10;
                    ownedPotions[10]-=1;
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            }
            if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    warriorDamage += 20;
                    ownedPotions[11]-=1;
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }

        //Ranger
        else if (turns == 2) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    rangerCurrentHp += 10;
                    fff.playersHp.setText("Hp: " + rangerCurrentHp);
                    fff.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    rangerCurrentHp += 30;
                    fff.playersHp.setText("Hp: " + rangerCurrentHp);
                    fff.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    fff.playersHp.setText("Hp: " + rangerCurrentHp);
                    fff.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[2]-=1;
                    fff.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    rangerBlock += 5;
                    fff.block.setText("Block: " + rangerBlock);
                    ownedPotions[3]-=1;
                    fff.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    rangerBlock += 20;
                    fff.block.setText("Block: " + rangerBlock);
                    ownedPotions[4]-=1;
                    fff.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    rangerBlock += 50;
                    fff.block.setText("Block: " + rangerBlock);
                    ownedPotions[5]-=1;
                    fff.potion6Label.setText(""+ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    rangerEnergyInt += 3;
                    fff.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[6]-=1;
                    fff.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    rangerEnergyInt += 5;
                    fff.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[7]-=1;
                    fff.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    rangerEnergyInt += 10;
                    fff.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[8]-=1;
                    fff.potion9Label.setText(""+ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    rangerDamage += 5;
                    ownedPotions[9]-=1;
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    rangerDamage += 10;
                    ownedPotions[10]-=1;
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    rangerDamage += 20;
                    ownedPotions[11]-=1;
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
        //Mage
        else if (turns == 3) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    mageCurrentHp += 10;
                    fff.playersHp.setText("Hp: " + mageCurrentHp);
                    fff.player3Hp.setText("Mage: " + mageCurrentHp);
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    mageCurrentHp += 30;
                    fff.playersHp.setText("Hp: " + mageCurrentHp);
                    fff.player3Hp.setText("Mage: " + mageCurrentHp);
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    fff.playersHp.setText("Hp: " + mageCurrentHp);
                    fff.player3Hp.setText("Mage: " + mageCurrentHp);
                    fff.potion3Label.setText(""+ownedPotions[2]);
                    ownedPotions[2]-=1;
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    mageBlock += 5;
                    fff.block.setText("Block: " + mageBlock);
                    ownedPotions[3]-=1;
                    fff.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    mageBlock += 20;
                    fff.block.setText("Block: " + mageBlock);
                    ownedPotions[4]-=1;
                    fff.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    mageBlock += 50;
                    fff.block.setText("Block: " + mageBlock);
                    ownedPotions[5]-=1;
                    fff.potion6Label.setText(""+ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    mageEnergyInt += 3;
                    fff.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[6]-=1;
                    fff.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    mageEnergyInt += 5;
                    fff.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[7]-=1;
                    fff.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    mageEnergyInt += 10;
                    fff.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[8]-=1;
                    fff.potion9Label.setText(""+ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    mageDamage += 5;
                    ownedPotions[9]-=1;
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    mageDamage += 10;
                    ownedPotions[10]-=1;
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    mageDamage += 20;
                    ownedPotions[11]-=1;
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
        //Healer
        else if (turns == 4) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    healerCurrentHp += 10;
                    fff.playersHp.setText("Hp: " + healerCurrentHp);
                    fff.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    healerCurrentHp += 30;
                    fff.playersHp.setText("Hp: " + healerCurrentHp);
                    fff.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    healerCurrentHp += 60;
                    fff.playersHp.setText("Hp: " + healerCurrentHp);
                    fff.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[2]-=1;
                    fff.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    healerBlock += 5;
                    fff.block.setText("Block: " + healerBlock);
                    ownedPotions[3]-=1;
                    fff.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    healerBlock += 20;
                    fff.block.setText("Block: " + healerBlock);
                    ownedPotions[4]-=1;
                    fff.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    healerBlock += 50;
                    fff.block.setText("Block: " + healerBlock);
                    ownedPotions[5]-=1;
                    fff.potion6Label.setText(""+ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    healerEnergyInt += 3;
                    fff.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[6]-=1;
                    fff.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    healerEnergyInt += 5;
                    fff.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[7]-=1;
                    fff.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    healerEnergyInt += 10;
                    fff.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[8]-=1;
                    fff.potion9Label.setText(""+ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    healerDamage += 5;
                    ownedPotions[9]-=1;
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    healerDamage += 10;
                    ownedPotions[10]-=1;
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    healerDamage += 20;
                    ownedPotions[11]-=1;
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
    }
}