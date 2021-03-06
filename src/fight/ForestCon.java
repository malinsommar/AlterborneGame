package fight;

import game.MasterModel;
import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Arrays;

/**
 * ForestCon controls the lowest level fight and contain more specific comment than the other
 *
 * @author Simon Bengtsson, Malin Sommar
 *
 * @version 1
 */
public class ForestCon {

    ForestFightFrame fff = new ForestFightFrame();

    //Get hp, block and damage from OldClasses.party
    private int warriorCurrentHp, mageCurrentHp, healerCurrentHp, rangerCurrentHp;
    private int warriorMaxHp, mageMaxHp, healerMaxHp, rangerMaxHp;
    public int warriorDamage, mageDamage, healerDamage, rangerDamage, damage;
    private int warriorBlock, mageBlock, healerBlock, rangerBlock;
    private int buffDamage[] = new int[4]; //Damage increase provided by strength potion and warrior's battlecry
    private boolean debuffed = false; //Damage decrease from warrior's demoralize
    private int enemyDamage, enemyRandomDamage = 10, enemyBaseDamage = 15; //In this case an attack deals 15-24 damage
    
    private int warriorStartDamage, mageStartDamage, healerStartDamage, rangerStartDamage;
    private int warriorStartBlock, mageStartBlock, healerStartBlock, rangerStartBlock;

    //Create int's
    private int turns = 1;
    private int currentEnergy;
    private int warriorEnergyInt=5, mageEnergyInt, rangerEnergyInt, healerEnergyInt;

    //Animation variables
    //player position
    public int warriorStartX = 170, warriorStartY = 210, warriorX = warriorStartX, warriorY = warriorStartY;
    public int rangerStartX = 70, rangerStartY = 290, rangerX = rangerStartX, rangerY = rangerStartY;
    public int mageStartX = -110, mageStartY = 290, mageX = mageStartX, mageY = mageStartY;
    public int healerStartX = -30, healerStartY = 210, healerX = healerStartX, healerY = healerStartY;
    //enemy position
    private int wolf1X = 850, wolf1Y = 320, wolf1StartX = wolf1X, wolf1StartY = wolf1Y;
    private int wolf2X = 1030, wolf2Y = 320, wolf2StartX = wolf2X, wolf2StartY = wolf2Y;
    private int wolf3X = 900, wolf3Y = 400, wolf3StartX = wolf3X, wolf3StartY = wolf3Y;
    private int wolf4X = 1080, wolf4Y = 400, wolf4StartX = wolf4X, wolf4StartY = wolf4Y;

    //spells/attack
    public int swordIconX = 300, swordIconY = 300;
    public int arrowX = 120, arrowY = 360, arrowStartX = arrowX;
    public int blastX = 120, blastY = 360, blastStartX = arrowX;
    public int flameStrikeY = -400;
    public int pyroBlastX = 90;
    public int pyroblastY = 300;
    public int bombX = 250;
    public int bombY = 300;
    public int bombStartX = 250;
    public int bombStartY = 300;

    //MegaMaths are ints used in algorithms to make curved animations
    public int warriorMegaMath = 30;
    public int bombMegaMath = 36;
    public int upMegaMath = 1;
    public int rightMegaMath = 1;
    public int downMegaMath = 1;
    public int leftMegaMath = 1;

    boolean warriorattacked = false, rangerattacked = false, mageattacked = false, healerattacked = false;

    //Used to track animation time
    private int phase = 0;
    private int timePast = 0;

    //Another timePast to avoid conflict when they run simultaneously.
    public int timePastTakeDamage = 0;

    private int target;
    private int healTarget = 0;
    private boolean stealthed = false;
    private boolean followup = false;
    private boolean animationPlaying = false;

    int[] wolfHp = new int[4];
    int wolfMaxHp = 25;

    public boolean fightWon = false;
    public boolean fightLost = false;

    public int[] ownedPotions = new int[12];

    /**
     * starts the fight, sets necessary startup variables, starts listeners
     */
    public void startFight(){

        MusicPick.musicStart("forest1","music");

        turns = 1;
        currentEnergy = 5;
        warriorEnergyInt = 5;
        rangerEnergyInt = 0;
        mageEnergyInt = 0;
        healerEnergyInt = 0;

        wolfHp[0] = wolfMaxHp;
        wolfHp[1] = wolfMaxHp;
        wolfHp[2] = wolfMaxHp;
        wolfHp[3] = wolfMaxHp;

        setStartLabels();
        fff.forestFightFrame();
        hoverEffect();
        targetSystem();

            //ActionListeners
            fff.attackButton.addActionListener(e -> {
                if (!animationPlaying && fff.targetarrow.isVisible()) attackPressed();

            });
            fff.blockButton.addActionListener(e -> blockPressed());
            fff.itemButton.addActionListener(e -> {
                    fff.inventory.setVisible(true);
                    itemMenuActivate();
                });
            fff.skillButton.addActionListener(e -> spellMenuActive()); //for now
            fff.endTurnButton.addActionListener(e -> {
                        if (!animationPlaying) startNewTurn();
                    });
            fff.skill1Button.addActionListener(e -> {
                skill1();
            });
            fff.skill2Button.addActionListener(e -> {
                skill2();
            });
            fff.skill3Button.addActionListener(e -> {
                skill3();
            });
            fff.skill4Button.addActionListener(e -> {
                skill4();
            });
            fff.returnButton.addActionListener(e -> spellMenuInactive());
            fff.returnButton.addActionListener(e -> fff.inventory.setVisible(false));


        //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
            fff.potion1.addActionListener(e -> usePotion(1));
            fff.potion2.addActionListener(e -> usePotion(2));
            fff.potion3.addActionListener(e -> usePotion(3));
            fff.potion4.addActionListener(e -> usePotion(4));
            fff.potion5.addActionListener(e -> usePotion(5));
            fff.potion6.addActionListener(e -> usePotion(6));
            fff.potion7.addActionListener(e -> usePotion(7));
            fff.potion8.addActionListener(e -> usePotion(8));
            fff.potion9.addActionListener(e -> usePotion(9));
            fff.potion10.addActionListener(e -> usePotion(10));
            fff.potion11.addActionListener(e -> usePotion(11));
            fff.potion12.addActionListener(e -> usePotion(12));
        }

    /**
     * Starts the next character's turn, restores block and energy
     */
    //When you press "end turn" button.
    public void startNewTurn(){
        turns++;

        //Warrior's turn
        if (turns==1 && warriorCurrentHp>0){
            warriorEnergyInt+=5; //Get energy
            currentEnergy=warriorEnergyInt; //Update energy.
            warriorBlock=warriorStartBlock; //Update block, reset extra block.
            warriorDamage = warriorStartDamage; //Update damage

            //Energy cant go over 10.
            if (warriorEnergyInt>10){
                warriorEnergyInt=10;
            }
            //Update labels.
            fff.whosTurn.setText("Warrior's turn");
            fff.playerStr.setText("Strength: "+(warriorDamage+buffDamage[0]));
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
            fff.playerStr.setText("Strength: "+(rangerDamage+buffDamage[1]));
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
            fff.playerStr.setText("Strength: "+(mageDamage+buffDamage[2]));
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
            fff.playerStr.setText("Strength: "+(healerDamage+buffDamage[3]));
            fff.energy.setText("Energy: "+healerEnergyInt);
            fff.block.setText("Block: "+healerBlock);

        }
        //If healer is dead, skip.
        if (turns==4 && healerCurrentHp<1){
            turns=5;
        }
        //  ***ENEMIES TURN***
        if (turns==5){
            fff.whosTurn.setText(" ");
            fff.playerStr.setText(" ");
            fff.energy.setText(" ");
            fff.block.setText(" ");
            enemyTurnTimer.start();

            //removes temporary damage buffs at the end of turn
            for (int i = 0; i < buffDamage.length; i++) {
                buffDamage[i] = 0;
            }
                }
            }


    /**
     *makes each living wolf attack and check if it killed a player
     */
            private void enemyDamage(){
                for (int i = 0; i < wolfHp.length; i++) {
                    if (wolfHp[i] > 0) {
                        wolfAttack();
                        partyDeath();
                        isFightOver();
                    }
                }
            }

    /**
     * first spell, cast different spells based on character, manages cost, might require a target <br> not usable if an animation is playing
     */
    private void skill1(){
        if (turns == 1 && warriorEnergyInt>2 && fff.targetarrow.isVisible() && !animationPlaying){
                warriorEnergyInt=warriorEnergyInt-3;
                currentEnergy=currentEnergy-3;
                fff.energy.setText("Energy: "+warriorEnergyInt);
                charge.start();
        }
        if (turns==2 && rangerEnergyInt>3 && fff.targetarrow.isVisible() && !animationPlaying){
                rangerEnergyInt=rangerEnergyInt-4;
                currentEnergy=currentEnergy-4;
                fff.energy.setText("Energy: "+rangerEnergyInt);
                volley.start();
        }
        if (turns==3 && mageEnergyInt>1 && fff.targetarrow.isVisible() && !animationPlaying){
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            fff.energy.setText("Energy: "+mageEnergyInt);
            fireBall.start();
        }
        if (turns==4 && healerEnergyInt>1 && !animationPlaying) {
            healingTargetMenu(1);
        }
    }

    /**
     *second spell, cast different spells based on character, manages cost, might require a target <br> not usable if an animation is playing
     */
    private void skill2(){
        if (turns == 1 && warriorEnergyInt>3 && !animationPlaying){
            warriorEnergyInt=warriorEnergyInt-4;
            currentEnergy=currentEnergy-4;
            fff.energy.setText("Energy: "+warriorEnergyInt);
            dunk.start();
        }
        if (turns==2 && rangerEnergyInt>2 && !animationPlaying){
            rangerEnergyInt=rangerEnergyInt-3;
            currentEnergy=currentEnergy-3;
            fff.energy.setText("Energy: "+rangerEnergyInt);
            bombthrow.start();
        }
        if (turns == 3){

        }
        if (turns == 4 && healerEnergyInt>1 && !animationPlaying){
            healingTargetMenu(2);
        }
    }

    /**
     * third spell, cast different spells based on character, manages cost, might require a target <br> not usable if an animation is playing
     */
    private void skill3(){
        if (turns == 1 && warriorEnergyInt>2 && !animationPlaying){
            warriorEnergyInt=warriorEnergyInt-3;
            currentEnergy=currentEnergy-3;
            fff.energy.setText("Energy: " + warriorEnergyInt);
            followup = true;
            shout.start();
        }
        if(turns == 2){

        }
        if (turns==3 && mageEnergyInt>4 && !animationPlaying){
                mageEnergyInt=mageEnergyInt-5;
                currentEnergy=currentEnergy-5;
                fff.energy.setText("Energy: "+mageEnergyInt);
                flameStrike.start();
        }
        if (turns == 4 && healerEnergyInt>4 && !animationPlaying){
            healerEnergyInt=healerEnergyInt-5;
            currentEnergy=currentEnergy-5;
            fff.energy.setText("Energy: "+healerEnergyInt);
            groupHealSpell.start();
        }
    }

    /**
     * forth spell, cast different spells based on character, manages cost, might require a target <br> not usable if an animation is playing
     */
    private void skill4(){
        if (turns == 1 && warriorEnergyInt>4 && !animationPlaying){
            warriorEnergyInt=warriorEnergyInt-5;
            currentEnergy=currentEnergy-5;
            fff.energy.setText("Energy: " + warriorEnergyInt);
            shout.start();
        }
        if(turns == 2 && rangerEnergyInt>2 && !animationPlaying){
                rangerEnergyInt=rangerEnergyInt-3;
                currentEnergy=currentEnergy-3;
                fff.energy.setText("Energy: "+rangerEnergyInt);
                stealth();
        }
        if (turns == 3 && mageEnergyInt>4 && fff.targetarrow.isVisible() && !animationPlaying){
            followup = true;
                mageEnergyInt=mageEnergyInt-5;
                currentEnergy=currentEnergy-5;
                fff.energy.setText("Energy: "+mageEnergyInt);
                pyroBlast.start();
        }
        if (turns == 4){
        }
    }

    /**
     * switches the endturn button to the return button
     */
    private void itemMenuActivate(){
        fff.endTurnButton.setVisible(false);
        fff.returnButton.setVisible(true);
    }

    /**
     * replace the standard actionbuttons with skillbuttons, set button text appropriate to skill it casts
     */
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
            fff.skill1Button.setText("Charge (3)");
            fff.skill2Button.setText("Slam (4)");
            fff.skill3Button.setText("Battlecry (3)");
            fff.skill4Button.setText("Demoralize (5)");
            fff.skill4Button.setFont(fff.pixelMplus.deriveFont(27f));
        }
        //ranger
        if (turns == 2){
            fff.skill1Button.setText("Volley (4)");
            fff.skill2Button.setText("Bomb (3)");
            fff.skill3Button.setText(" ");
            fff.skill4Button.setText("Stealth (3)");
        }
        //mage
        if (turns == 3) {
            fff.skill1Button.setText("Fireball (2)");
            fff.skill2Button.setText(" ");
            fff.skill3Button.setText("Meteor (5)");
            fff.skill4Button.setText("Pyroblast (5)");
        }
        //healer
        if (turns == 4){
            fff.skill1Button.setText("Heal (4)");
            fff.skill2Button.setText("Bless (2)");
            fff.skill3Button.setText("Restore (5)");
            fff.skill4Button.setText(" ");
        }
    }

    /**
     * increase block for current character
     */
    //When player press block
    private void blockPressed(){

        //If its warrior's turn and player has 2 or more energy.
        if(turns==1 && warriorEnergyInt>1 && !animationPlaying){
            warriorEnergyInt=warriorEnergyInt-2;
            currentEnergy=currentEnergy-2;
            warriorBlock+=5;
            fff.energy.setText("Energy: "+warriorEnergyInt);
            fff.block.setText("Block: "+warriorBlock);
        }
        //If its ranger's turn and player has 2 or more energy.
        else if(turns==2 && rangerEnergyInt>1 && !animationPlaying){
            rangerEnergyInt=rangerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            rangerBlock+=5;
            fff.energy.setText("Energy: "+rangerEnergyInt);
            fff.block.setText("Block: "+rangerBlock);
        }
        //If its mage's turn and player has 2 or more energy.
        else if(turns==3 && mageEnergyInt>1 && !animationPlaying){
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            mageBlock+=5;
            fff.energy.setText("Energy: "+mageEnergyInt);
            fff.block.setText("Block: "+mageBlock );
        }
        //If its healer's turn and player has 2 or more energy.
        else if(turns==4 && healerEnergyInt>1 && !animationPlaying){
            healerEnergyInt=healerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            healerBlock+=5;
            fff.energy.setText("Energy: "+healerEnergyInt);
            fff.block.setText("Block: "+healerBlock);
        }
    }

    /**
     * use standard attack, every character has this but damage differ, always single target and costs 2 energy
     */
    //When you press the "attack button".
    private void attackPressed() {

        //If its warrior's turn and player has 2 or more energy.
        if(turns==1 && warriorEnergyInt>1 && fff.targetarrow.isVisible() && !animationPlaying){
            warriorEnergyInt=warriorEnergyInt-2; //Energy -2.
            currentEnergy=currentEnergy-2; // Update currentEnergy.
            fff.energy.setText("Energy: "+warriorEnergyInt); //Update energyLabel
            tackle.start(); //Warrior deals damage to a wolf.
        }
        //If its ranger's turn and player has 2 or more energy.
        else if(turns==2 && rangerEnergyInt>1 && fff.targetarrow.isVisible() && !animationPlaying){
            rangerEnergyInt=rangerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            fff.energy.setText("Energy: "+rangerEnergyInt);
            shoot.start();
        }
        //If its mage's turn and player has 2 or more energy.
        else if(turns==3 && mageEnergyInt>1 && fff.targetarrow.isVisible() && !animationPlaying){
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            fff.energy.setText("Energy: "+mageEnergyInt);
            blast.start();
        }
        //If its healer's turn and player has 2 or more energy.
        else if(turns==4 && healerEnergyInt>1 && fff.targetarrow.isVisible() && !animationPlaying){
            healerEnergyInt=healerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            fff.energy.setText("Energy: "+healerEnergyInt);
            healerAttack.start();
        }
    }

    /**
     * checks if all players or all enemies are dead
     */
    //Checks if all of the enemies or OldClasses.party-members are dead.
    private void isFightOver(){
        if (wolfHp[0] < 1 && wolfHp[1] < 1 && wolfHp[2] < 1 && wolfHp[3] < 1) {
            MusicPick.musicStop();
            fff.forestFightJFrame.dispose();
            fightWon = true;

        }
        else if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
            fff.forestFightJFrame.dispose();
            fightLost = true;
        }
        //If none of these are true, nothing happens and the fight goes on.
    }

    /**
     *  wolf attacks random living player, can only hit stealthed ranger if everybody else is dead
     */
    //When the wolf attacks.
    public void wolfAttack() {
        target = (int) (Math.random() * 4); //Random target, 0-3.
        enemyDamage = (int) (Math.random() * enemyRandomDamage) + enemyBaseDamage; //Generate random damage, 15-25.
        if (debuffed) enemyDamage -= 10;

    //Loops until it reaches an alive OldClasses.party-member.
        while (true) {

            //Warrior, Target 2.
            if (target == 0) {
                //If warrior is dead, target=1.
                if (warriorCurrentHp < 1) {
                    target=1;
                }
                //If warrior is alive.
                if (warriorCurrentHp >0) {
                    enemyDamage=enemyDamage-warriorBlock; //Warrior take damage equal to wolf damage.
                    warriorCurrentHp = warriorCurrentHp - enemyDamage; //Update warrior hp.
                    fff.player1Hp.setText("Warrior: "+ warriorCurrentHp+" /"+warriorMaxHp); //Update hp Label.
                    warriorattacked = true;
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
                if (mageCurrentHp >0) {
                    enemyDamage=enemyDamage-mageBlock;
                    mageCurrentHp = mageCurrentHp - enemyDamage;
                    fff.player3Hp.setText("Mage:    "+ mageCurrentHp+" /"+mageMaxHp);
                    mageattacked = true;
                    break;
                }
            }
            //Ranger, target 2.
            if (target ==1) {
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
                    enemyDamage=enemyDamage-rangerBlock;
                    rangerCurrentHp = rangerCurrentHp - enemyDamage;
                    fff.player2Hp.setText("Ranger:  "+ rangerCurrentHp+" /"+rangerMaxHp);
                    rangerattacked = true;
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
                if (healerCurrentHp >0) {
                    enemyDamage=enemyDamage-healerBlock;
                    healerCurrentHp = healerCurrentHp - enemyDamage;
                    fff.player4Hp.setText("Healer:  "+healerCurrentHp+" /"+healerMaxHp);
                    healerattacked = true;
                    break;
                }
            }
        }
    }

    /**
     *  check for dead wolf and remove any, also removes targetarrow if pointed at dead wolf
     */
    //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
    public void mobDeath(){

        if(wolfHp[0]<=0){
            fff.wolf1Hp.setText("Wolf 1: 0");
            fff.wolf1.setVisible(false);
            if (target == 1) {
                fff.targetarrow.setVisible(false);
                target = 0;
            }
        }
        if(wolfHp[1]<=0){
            fff.wolf2Hp.setText("Wolf 2: 0");
            fff.wolf2.setVisible(false);
            if (target == 2) {
                fff.targetarrow.setVisible(false);
                target = 0;
            }
        }
        if(wolfHp[2]<=0){
            fff.wolf3Hp.setText("Wolf 3: 0");
            fff.wolf3.setVisible(false);
            if (target == 3) {
                fff.targetarrow.setVisible(false);
                target = 0;
            }
        }
        if(wolfHp[3]<=0){
            fff.wolf4Hp.setText("Wolf 4: 0");
            fff.wolf4.setVisible(false);
            if (target == 4) {
                fff.targetarrow.setVisible(false);
                target = 0;
            }
        }
    }

    /**
     *  check for dead players and remove any
     */
    //Checks if any OldClasses.party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
    public void partyDeath(){

        if(warriorCurrentHp<=0){
            warriorCurrentHp = 0;
            fff.player1Hp.setText("Warrior: "+ warriorCurrentHp+" /"+warriorMaxHp);
            fff.warrior.setVisible(false);
        }
        if(mageCurrentHp<=0){
            mageCurrentHp = 0;
            fff.player3Hp.setText("Mage:    "+ mageCurrentHp+" /"+mageMaxHp);
            fff.mage.setVisible(false);
        }
        if(rangerCurrentHp<=0){
            rangerCurrentHp = 0;
            fff.player2Hp.setText("Ranger:  "+ rangerCurrentHp+" /"+rangerMaxHp);
            fff.ranger.setVisible(false);
        }
        if(healerCurrentHp<=0){
            healerCurrentHp = 0;
            fff.player4Hp.setText("Healer:  "+healerCurrentHp+" /"+healerMaxHp);
            fff.healer.setVisible(false);
        }
    }

    /**
     * switches skillbuttons to standard action buttons
     */
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

    /**
     *Gets ownedPotions from masterModel.
     *
     * @param potions
     */
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

    /**
     * Gets all the party-stats from masterModel.
     *
     * @param warrior Warrior current stats
     * @param mage Mage current stats
     * @param healer Healer current stats
     * @param ranger Ranger current stats
     */
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

        warriorMaxHp =warriorCurrentHp;
        mageMaxHp = mageCurrentHp;
        healerMaxHp = healerCurrentHp;
        rangerMaxHp = rangerCurrentHp;
    }

    /**
     * set all HUD label text including potions
     */
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

        fff.playerStr = new JLabel("Strength: "+ (warriorDamage+buffDamage[1]));
        fff.player1Hp = new JLabel("Warrior: "+ warriorCurrentHp+" /"+warriorMaxHp);
        fff.player2Hp = new JLabel("Ranger:  "+ rangerCurrentHp+" /"+rangerMaxHp);
        fff.player3Hp = new JLabel("Mage:    "+ mageCurrentHp+" /"+mageMaxHp);
        fff.player4Hp = new JLabel("Healer:  "+healerCurrentHp+" /"+healerMaxHp);
        fff.block = new JLabel("Block: "+warriorBlock);
    }

    /**
     *  add hover effect to standard action buttons
     */
    //Add hover effect to buttons.
    private void hoverEffect() {
        //Attack Hover
        fff.attackButton.addMouseListener(new MouseAdapter() {
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
        fff.blockButton.addMouseListener(new MouseAdapter() {
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
        fff.itemButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fff.itemButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.itemButton.setBackground(Color.white);
            }
        });
        //Skill Hover
        fff.skillButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fff.skillButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.skillButton.setBackground(Color.white);
            }
        });

        //End turn Hover
        fff.endTurnButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fff.endTurnButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fff.endTurnButton.setBackground(Color.white);
            }
        });
    }

    /**
     * use potion and applies it's effect to current player
     *
     * @param potion compares to ownedPotions array to see what type the potion is, and thus it's effect
     */
    //Get the effect from potions.
    private void usePotion(int potion) {

        //Warrior
        if (turns == 1) {
            //If potion 1 "minor healing potion" is pressed.
            if (potion == 1) {
                //If player own that potion.
                if (ownedPotions[0] > 0) {
                    warriorCurrentHp += 10; //Heal warrior equals to the potions heal.
                    if (warriorCurrentHp > warriorMaxHp) warriorCurrentHp = warriorMaxHp;
                    fff.player1Hp.setText("Warrior: "+ warriorCurrentHp+" /"+warriorMaxHp); // Update currentPlayer Hp label.
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]); //Update ownedPotion Label.
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    warriorCurrentHp += 30;
                    if (warriorCurrentHp > warriorMaxHp) warriorCurrentHp = warriorMaxHp;
                    fff.player1Hp.setText("Warrior: "+ warriorCurrentHp+" /"+warriorMaxHp);;
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    warriorCurrentHp += 60;
                    if (warriorCurrentHp > warriorMaxHp) warriorCurrentHp = warriorMaxHp;
                    fff.player1Hp.setText("Warrior: "+ warriorCurrentHp+" /"+warriorMaxHp);
                    ownedPotions[2]-=1;
                    fff.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    warriorBlock += 5;
                    fff.block.setText("Block: "+warriorBlock);
                    ownedPotions[3]-=1;
                    fff.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    warriorBlock += 20;
                    fff.block.setText("Block: "+warriorBlock);
                    ownedPotions[4]-=1;
                    fff.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    warriorBlock += 50;
                    fff.block.setText("Block: "+warriorBlock);
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
            else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9]-=1;
                    fff.playerStr.setText("Strength: "+(warriorDamage+buffDamage[0]));
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            }
            else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage[turns - 1] += 10;
                    ownedPotions[10]-=1;
                    fff.playerStr.setText("Strength: "+(warriorDamage+buffDamage[0]));
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            }
            else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage[turns - 1] += 20;
                    ownedPotions[11]-=1;
                    fff.playerStr.setText("Strength: "+(warriorDamage+buffDamage[0]));
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }

        //Ranger
        else if (turns == 2) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    rangerCurrentHp += 10;
                    if (rangerCurrentHp > rangerMaxHp) rangerCurrentHp = rangerMaxHp;
                    fff.player2Hp.setText("Ranger:  "+ rangerCurrentHp+" /"+rangerMaxHp);
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    rangerCurrentHp += 30;
                    if (rangerCurrentHp > rangerMaxHp) rangerCurrentHp = rangerMaxHp;
                    fff.player2Hp.setText("Ranger:  "+ rangerCurrentHp+" /"+rangerMaxHp);
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    if (rangerCurrentHp > rangerMaxHp) rangerCurrentHp = rangerMaxHp;
                    fff.player2Hp.setText("Ranger:  "+ rangerCurrentHp+" /"+rangerMaxHp);
                    ownedPotions[2]-=1;
                    fff.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    rangerBlock += 5;
                    fff.block.setText("Block: "+warriorBlock);
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
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9]-=1;
                    fff.playerStr.setText("Strength: "+(rangerDamage+buffDamage[1]));
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage[turns - 1] += 10;
                    ownedPotions[10]-=1;
                    fff.playerStr.setText("Strength: "+(rangerDamage+buffDamage[1]));
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage[turns - 1] += 20;
                    ownedPotions[11]-=1;
                    fff.playerStr.setText("Strength: "+(rangerDamage+buffDamage[1]));
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
        //Mage
        else if (turns == 3) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    mageCurrentHp += 10;
                    if (mageCurrentHp > mageMaxHp) mageCurrentHp = mageMaxHp;
                    fff.player3Hp.setText("Mage:    "+ mageCurrentHp+" /"+mageMaxHp);
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    mageCurrentHp += 30;
                    if (mageCurrentHp > mageMaxHp) mageCurrentHp = mageMaxHp;
                    fff.player3Hp.setText("Mage:    "+ mageCurrentHp+" /"+mageMaxHp);
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    if (mageCurrentHp > mageMaxHp) mageCurrentHp = mageMaxHp;
                    fff.player3Hp.setText("Mage:    "+ mageCurrentHp+" /"+mageMaxHp);
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
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9]-=1;
                    fff.playerStr.setText("Strength: "+(mageDamage+buffDamage[2]));
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage[turns - 1] += 10;
                    ownedPotions[10]-=1;
                    fff.playerStr.setText("Strength: "+(mageDamage+buffDamage[2]));
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage[turns - 1] += 20;
                    ownedPotions[11]-=1;
                    fff.playerStr.setText("Strength: "+(mageDamage+buffDamage[2]));
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
        //Healer
        else if (turns == 4) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    healerCurrentHp += 10;
                    if (healerCurrentHp > healerMaxHp) healerCurrentHp = healerMaxHp;
                    fff.player4Hp.setText("Healer:  "+healerCurrentHp+" /"+healerMaxHp);
                    ownedPotions[0]-=1;
                    fff.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    healerCurrentHp += 30;
                    if (healerCurrentHp > healerMaxHp) healerCurrentHp = healerMaxHp;
                    fff.player4Hp.setText("Healer:  "+healerCurrentHp+" /"+healerMaxHp);
                    ownedPotions[1]-=1;
                    fff.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    healerCurrentHp += 60;
                    if (healerCurrentHp > healerMaxHp) healerCurrentHp = healerMaxHp;
                    fff.player4Hp.setText("Healer:  "+healerCurrentHp+" /"+healerMaxHp);
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
                    buffDamage[turns - 1] += 5;
                    ownedPotions[9]-=1;
                    fff.playerStr.setText("Strength: "+(healerDamage+buffDamage[3]));
                    fff.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    buffDamage[turns - 1] += 10;
                    ownedPotions[10]-=1;
                    fff.playerStr.setText("Strength: "+(healerDamage+buffDamage[3]));
                    fff.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    buffDamage[turns - 1] += 20;
                    ownedPotions[11]-=1;
                    fff.playerStr.setText("Strength: "+(healerDamage+buffDamage[3]));
                    fff.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
        if (warriorCurrentHp>warriorMaxHp){
            warriorCurrentHp = warriorMaxHp;
        }
        if (rangerCurrentHp>rangerMaxHp){
            rangerCurrentHp = rangerMaxHp;
        }
        if (mageCurrentHp>mageMaxHp){
            mageCurrentHp = mageMaxHp;
        }
        if (healerCurrentHp>healerMaxHp){
            healerCurrentHp = healerMaxHp;
        }
    }

    /**
     * listener. if you click an enemy it is selected as a target for further attacks and an arrow appears above it
     */
    public void targetSystem(){

        fff.wolf1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 1;
                fff.targetarrow.setLocation(875, 250);
                fff.targetarrow.setVisible(true);
            }
        });
        fff.wolf2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 2;
                fff.targetarrow.setLocation(1065, 250);
                fff.targetarrow.setVisible(true);
            }
        });
        fff.wolf3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 3;
                fff.targetarrow.setLocation(925, 325);
                fff.targetarrow.setVisible(true);
            }
        });
        fff.wolf4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 4;
                fff.targetarrow.setLocation(1100, 325);
                fff.targetarrow.setVisible(true);
            }
        });
    }

    /**
     * switches the skill menu to a menu with friendly targets to heal
     * @param chosenSpell used to track which spell called the method
     */
    private void healingTargetMenu(int chosenSpell) {
        fff.skill1Button.setVisible(false);
        fff.skill2Button.setVisible(false);
        fff.skill3Button.setVisible(false);
        fff.skill4Button.setVisible(false);

        fff.healWarriorButton.setVisible(true);
        fff.healRangerButton.setVisible(true);
        fff.healMageButton.setVisible(true);
        fff.healHealerButton.setVisible(true);

        fff.healWarriorButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 3){
                healerEnergyInt=healerEnergyInt-4;
                currentEnergy=currentEnergy-4;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 1;
                holyLightSpell.start();
                spellHealSystem(30, "single");
            }

            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 1;
                smallHolyLightSpell.start();
                spellHealSystem(15, "single");
            }
        });
        fff.healRangerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 2;
                holyLightSpell.start();
                spellHealSystem(30, "single");
            }
            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 2;
                smallHolyLightSpell.start();
                spellHealSystem(15, "single");
            }
        });
        fff.healMageButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 3;
                holyLightSpell.start();
                spellHealSystem(30, "single");
            }
            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 3;
                smallHolyLightSpell.start();
                spellHealSystem(15, "single");
            }
        });
        fff.healHealerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 4;
                holyLightSpell.start();
                spellHealSystem(30, "single");
            }
            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fff.energy.setText("Energy: "+healerEnergyInt);
                healTarget = 4;
                smallHolyLightSpell.start();
                spellHealSystem(15, "single");
            }
        });
    }


    /**
     * dishes out damage to the appropriate enemy/enemies, and check if it's dead
     * @param unbuffedDamage standard damage value
     * @param damageTargets who should take damage, one, two or all four enemies by sending single line or all respectively <br> line is always the target and the enemy in front of it or behind
     */
    //called from spells to deal damage to enemies
    //damageTargets types: single, line, all
    public void spellDamageSystem(int unbuffedDamage, String damageTargets){
        damage = unbuffedDamage + buffDamage[turns - 1];
        if (damageTargets.equals("single")){
            wolfHp[target-1] -= damage;
        }
        if (damageTargets.equals("line")){
            if (target == 1 || target == 2){
                wolfHp[0] -= damage;
                wolfHp[1] -= damage;
            }
            if (target == 3 || target == 4){
                wolfHp[2] -= damage;
                wolfHp[3] -= damage;
            }
        }
        if (damageTargets.equals("all")){
            wolfHp[0] -= damage;
            wolfHp[1] -= damage;
            wolfHp[2] -= damage;
            wolfHp[3] -= damage;
        }
        fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);
        fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
        fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
        fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
        mobDeath();
        isFightOver();
    }

    /**
     * restores health to appropriate player(s)
     * @param healing how much health the healing spell should restore
     * @param healingTargets if the spell heals one or all players
     */
    //fixa denna
    public void spellHealSystem(int healing, String healingTargets){
        if (healingTargets.equals("single")){
            if (healTarget == 1 && warriorCurrentHp > 0) warriorCurrentHp += healing;
            if (healTarget == 2 && rangerCurrentHp > 0) rangerCurrentHp += healing;
            if (healTarget == 3 && mageCurrentHp > 0) mageCurrentHp += healing;
            if (healTarget == 4 && healerCurrentHp > 0) healerCurrentHp += healing;
        }
        if (healingTargets.equals("all")){
            if (warriorCurrentHp > 0) warriorCurrentHp += healing;
            if (rangerCurrentHp > 0) rangerCurrentHp += healing;
            if (mageCurrentHp > 0) mageCurrentHp += healing;
            if (healerCurrentHp > 0) healerCurrentHp += healing;
        }
        if (warriorMaxHp < warriorCurrentHp) warriorCurrentHp = warriorMaxHp;
        if (mageMaxHp < mageCurrentHp) mageCurrentHp = mageMaxHp;
        if (healerMaxHp < healerCurrentHp) healerCurrentHp = healerMaxHp;
        if (rangerMaxHp < rangerCurrentHp) rangerCurrentHp = rangerMaxHp;
        fff.player1Hp.setText("Warrior: "+ warriorCurrentHp+" /"+warriorMaxHp);;
        fff.player2Hp.setText("Ranger:  "+ rangerCurrentHp+" /"+rangerMaxHp);
        fff.player3Hp.setText("Mage:    "+ mageCurrentHp+" /"+mageMaxHp);
        fff.player4Hp.setText("Healer:  "+healerCurrentHp+" /"+healerMaxHp);
    }

    /**
     * warrior's standard attack, moves to the right and then left quickly
     */
    //warrior
    public Timer tackle = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                if (warriorX == 100) MusicPick.musicStart("warriorattack", "");
                animationPlaying = true;
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
                    spellDamageSystem(warriorDamage,"single");
                    animationPlaying = false;
                }
            }
        }
    });

    /**
     * warrior spell, charges the enemies then instantly returns to it's starting location, hits the target and one more
     */
    public Timer charge = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
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
                spellDamageSystem(6,"line");
                animationPlaying = false;
            }
        }
    });

    /**
     * warrior spell, uses MegaMath to form a curved path to the enemies, simulating a jump
     */
    public Timer dunk = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0){
                animationPlaying = true;
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
                    spellDamageSystem(3,"all");
                    animationPlaying = false;

                }
            }
        }
    });

    /**
     * start of both warrior shout animations, moves rapidly right and left,
     */
    public Timer shout = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                MusicPick.musicStart("demoshout", " ");
                phase = 1;
                animationPlaying = true;
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
                    phase = 0;
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

    /**
     * warrior shout animation, sword icon spins in circles at warrior, boost entire parties damage until end of turn
     */
    public Timer battlecry = new Timer(20, new ActionListener() {
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
                for (int i = 0; i < buffDamage.length; i++) {
                    buffDamage[i] += 3;
                }
                swordIconX = 300;
                swordIconY = 300;
                fff.swordIcon.setVisible(false);
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

    /**
     * warrior shout animation, sword icons spins in circles at all enemies, decreases all enemies damage until end of turn
     */
    public Timer demoralized = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            fff.demoSword1.setLocation(swordIconX + 550, swordIconY + 50);
            fff.demoSword2.setLocation(swordIconX + 650, swordIconY + 100);
            fff.demoSword3.setLocation(swordIconX + 750, swordIconY + 50);
            fff.demoSword4.setLocation(swordIconX + 850, swordIconY + 100);


            if (phase == 0) {
                if (wolfHp[0] > 0) fff.demoSword1.setVisible(true);
                if (wolfHp[2] > 0) fff.demoSword2.setVisible(true);
                if (wolfHp[1] > 0) fff.demoSword3.setVisible(true);
                if (wolfHp[3] > 0) fff.demoSword4.setVisible(true);
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
                debuffed = true;
                animationPlaying = false;
                demoralized.stop();
            }
        }
    });

    //ranger

    /**
     * standard ranger attack, fires an arrow
     */
    Timer shoot = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                fff.arrow.setVisible(true);
                if (arrowX == 121) {
                    animationPlaying = true;
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
                if (stealthed){
                    spellDamageSystem(rangerDamage * 2,"single");
                    unstealth();
                }
                else {
                    spellDamageSystem(rangerDamage, "single");
                }
            }
        }
    });

    /**
     * ranger spell, fires multiple arrows at a single enemy
     */
    public Timer volley = new Timer(10, new ActionListener() {
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
                animationPlaying = false;
                volley.stop();
                if (stealthed){
                    spellDamageSystem(40,"single");
                    unstealth();
                }
                else {
                    spellDamageSystem(20, "single");
                }
            }
        }
    });

    /**
     * ranger spell, ranger goes into stealth, turns grey and become untargetable by enemies under most conditions
     */
    public void stealth() {
        if (!stealthed) {
            MusicPick.musicStart("stealth", "");
            fff.ranger.setVisible(false);
            fff.stealthranger.setVisible(true);
            stealthed = true;
        }
    }

    /**
     * ranger goes out of stealth, becoming normal
     */
    public void unstealth(){
        if (stealthed){
            MusicPick.musicStart("unstealth", "");
            fff.ranger.setVisible(true);
            fff.stealthranger.setVisible(false);
            stealthed = false;
        }
    }

    /**
     * ranger spell, throws a bomb that deal damage to all enemies
     */
    public Timer bombthrow = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0){
                animationPlaying = true;
                MusicPick.musicStart("Explosion", "");
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
                    MusicPick.musicStart("Explosion", "");
                }
                if(timePast == 60){
                    bombMegaMath = 36;
                    fff.bomb.setVisible(false);
                    fff.explode.setVisible(false);
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


    /**
     * standard mage attack
     */
    //mage
    Timer blast = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                fff.blast.setVisible(true);
                if (blastY == 121) {
                    MusicPick.musicStart("fireball", "");
                }
                blastX += 30;
                fff.blast.setLocation(blastX, blastY);
                if (blastX > 1000) {
                    phase = 1;
                }
            } else if (phase == 1) {
                fff.blast.setVisible(false);
                blastX = blastStartX;
                fff.blast.setLocation(blastX, blastY);
                phase = 0;
                spellDamageSystem(mageDamage, "single");
                blast.stop();
                animationPlaying = false;
            }
        }
    });

    /**
     * mage spell, charges i giant fireball and launch it slowly at the enemy, dealing high damage
     */
    public Timer pyroBlast = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (timePast < 100) {
                animationPlaying = true;
                fff.smallPyroBlast.setVisible(true);
            }
            else if (timePast < 200) {
                fff.smallPyroBlast.setVisible(false);
                fff.mediumPyroBlast.setVisible(true);
            }
            else if (timePast < 350) {
                fff.mediumPyroBlast.setVisible(false);
                fff.bigPyroBlast.setVisible(true);
                pyroBlastX = 45;
                pyroblastY = 200;
                fff.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
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
                pyroBlastX = 90;
                pyroblastY = 300;
                fff.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                spellDamageSystem(20,"single");
                pyroBlast.stop();
                animationPlaying = false;
            }
        }
    });

    /**
     * mage spell, fly into the air and call down a meteor at the enemies
     */
    public Timer flameStrike = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                animationPlaying = true;
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
                    flameStrikeY = -400;
                    fff.flame.setLocation(700, flameStrikeY);
                    spellDamageSystem(5, "all");
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
                    flameStrike.stop();
                    phase = 0;
                    animationPlaying = false;
                }
            }
        }
    });

    /**
     * mage spell, fast moving ball of fire, spinning in cicles while flying toward the enemy
     */
    public Timer fireBall = new Timer(15, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            pyroBlastX += 16;
            fff.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            animationPlaying = true;
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
                pyroblastY = 300;
                pyroBlastX = 90;
                fff.smallPyroBlast.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                fireBall.stop();
                spellDamageSystem(8,"single");
                animationPlaying = false;
                fff.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
        }
    });

    /**
     * healer spell, wide beam that heals the target alot
     */
    //healer
    public Timer holyLightSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0){
                animationPlaying = true;
                if (healTarget == 1)fff.holyLight.setLocation(warriorStartX -220, warriorStartY -500);
                if (healTarget == 2)fff.holyLight.setLocation(rangerStartX -220, rangerStartY -450);
                if (healTarget == 3)fff.holyLight.setLocation(mageStartX -220, mageStartY -450);
                if (healTarget == 4)fff.holyLight.setLocation(healerStartX -220, healerStartY -500);
                MusicPick.musicStart("holylight", "");
                fff.holyLight.setVisible(true);
                phase = 1;
            }
            if (timePast == 100){
                timePast = 0;
                fff.holyLight.setVisible(false);
                holyLightSpell.stop();
                phase = 0;
                animationPlaying = false;
            }
        }
    });

    /**
     * healer spell, thin beam that heals the target a little
     */
    public Timer smallHolyLightSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0){
                animationPlaying = true;
                if (healTarget == 1)fff.smallHolyLight.setLocation(warriorStartX -225, warriorStartY -500);
                if (healTarget == 2)fff.smallHolyLight.setLocation(rangerStartX -225, rangerStartY -500);
                if (healTarget == 3)fff.smallHolyLight.setLocation(mageStartX -225, mageStartY -500);
                if (healTarget == 4)fff.smallHolyLight.setLocation(healerStartX -225, healerStartY -500);
                MusicPick.musicStart("holylight", "");
                fff.smallHolyLight.setVisible(true);
                phase = 1;
            }
            if (timePast > 100){
                timePast = 0;
                fff.smallHolyLight.setVisible(false);
                smallHolyLightSpell.stop();
                phase = 0;
                animationPlaying = false;
            }
        }
    });

    /**
     * create a thin beam at all friendly targets and heal them
     */
    public Timer groupHealSpell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0){
                animationPlaying = true;
                fff.groupHeal1.setLocation(warriorStartX + 75, warriorStartY - 500);
                fff.groupHeal2.setLocation(rangerStartX + 75, rangerStartY - 500);
                fff.groupHeal3.setLocation(mageStartX + 75, mageStartY - 500);
                fff.groupHeal4.setLocation(healerStartX + 75, healerStartY - 500);
                MusicPick.musicStart("groupheal", "");
                fff.groupHeal1.setVisible(true);
                fff.groupHeal2.setVisible(true);
                fff.groupHeal3.setVisible(true);
                fff.groupHeal4.setVisible(true);
                phase = 1;
            }
            if (timePast > 400){
                timePast = 0;
                fff.groupHeal1.setVisible(false);
                fff.groupHeal2.setVisible(false);
                fff.groupHeal3.setVisible(false);
                fff.groupHeal4.setVisible(false);
                groupHealSpell.stop();
                phase = 0;
                spellHealSystem(10, "all");
                animationPlaying = false;
            }
        }
    });

    /**
     * standard healer attack
     */
    public Timer healerAttack = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                if (healerX == 100) MusicPick.musicStart("ding", "");
                healerX += 15;
                fff.healer.setLocation(healerX, healerY);
                if (healerX > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                healerX -= 15;
                fff.healer.setLocation(healerX, healerY);
                if (healerX <= healerStartX) {
                    healerX = healerStartX;
                    fff.healer.setLocation(healerX, healerY);
                    phase = 0;
                    spellDamageSystem(healerDamage,"single");
                    healerAttack.stop();
                    animationPlaying = false;
                    System.out.println("healer attack done");
                }
            }
        }
    });

    /**
     * enemy attack animation, wolves take turns to move slightly to the left and then back
     */
    //enemy
    private Timer enemyTurnTimer = new Timer(7, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            animationPlaying = true;
            fff.endTurnButton.setVisible(false);
            fff.targetarrow.setVisible(false);
            if (timePast < 50) {
                if (wolfHp[0] < 1) timePast += 100;
            }
            else if (timePast < 60) {
                wolf1X -= 15;
                fff.wolf1.setLocation(wolf1X, wolf1Y);
            }
            else if (timePast < 70) {
                wolf1X += 15;
                fff.wolf1.setLocation(wolf1X, wolf1Y);
            }
            else if (timePast < 150) {
                fff.wolf1.setLocation(wolf1StartX, wolf1StartY);
                if (wolfHp[1] < 1) timePast += 100;

            }
            else if (timePast < 160) {
                wolf2X -= 15;
                fff.wolf2.setLocation(wolf2X, wolf2Y);
            }
            else if (timePast < 170) {
                wolf2X += 15;
                fff.wolf2.setLocation(wolf2X, wolf2Y);
            }
            else if (timePast < 250) {
                fff.wolf2.setLocation(wolf2StartX, wolf2StartY);
                if (wolfHp[2] < 1) timePast += 100;

            }
            else if (timePast < 260) {
                wolf3X -= 15;
                fff.wolf3.setLocation(wolf3X, wolf3Y);
            }
            else if (timePast < 270) {
                wolf3X += 15;
                fff.wolf3.setLocation(wolf3X, wolf3Y);
            }
            else if (timePast < 350) {
                fff.wolf3.setLocation(wolf3StartX, wolf3StartY);
                if (wolfHp[3] < 1) timePast += 100;

            }
            else if (timePast < 360) {
                wolf4X -= 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 370) {
                wolf4X += 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 450) {
                fff.wolf4.setLocation(wolf4StartX, wolf4StartY);
                enemyDamage();
                enemyTurnTimer.stop();
                timePast = 0;
                fff.endTurnButton.setVisible(true);
                turns = 0;
                animationPlaying = false;
                takeDamage.start();
                debuffed = false;
            }
        }
    });

    /**
     * player characters that took damage this turn blinks for a short time
     */
    private Timer takeDamage = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePastTakeDamage++;
            if (timePastTakeDamage == 1) {
                MusicPick.musicStart("warriorattacked", "");
            } else if (timePastTakeDamage == 10) {
                if (warriorattacked && warriorCurrentHp > 0) fff.warrior.setVisible(false);
                if (rangerattacked && rangerCurrentHp > 0) fff.ranger.setVisible(false);
                if (mageattacked && mageCurrentHp > 0) fff.mage.setVisible(false);
                if (healerattacked && healerCurrentHp > 0) fff.healer.setVisible(false);
            } else if (timePastTakeDamage == 20) {
                if (warriorattacked && warriorCurrentHp > 0) fff.warrior.setVisible(true);
                if (rangerattacked && rangerCurrentHp > 0) fff.ranger.setVisible(true);
                if (mageattacked && mageCurrentHp > 0) fff.mage.setVisible(true);
                if (healerattacked && healerCurrentHp > 0) fff.healer.setVisible(true);
            } else if (timePastTakeDamage == 30) {
                if (warriorattacked && warriorCurrentHp > 0) fff.warrior.setVisible(false);
                if (rangerattacked && rangerCurrentHp > 0) fff.ranger.setVisible(false);
                if (mageattacked  && mageCurrentHp > 0) fff.mage.setVisible(false);
                if (healerattacked  && healerCurrentHp > 0) fff.healer.setVisible(false);
            } else if (timePastTakeDamage == 40) {
                if (warriorattacked  && warriorCurrentHp > 0) fff.warrior.setVisible(true);
                if (rangerattacked  && rangerCurrentHp > 0) fff.ranger.setVisible(true);
                if (mageattacked  && mageCurrentHp > 0) fff.mage.setVisible(true);
                if (healerattacked  && healerCurrentHp > 0) fff.healer.setVisible(true);
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