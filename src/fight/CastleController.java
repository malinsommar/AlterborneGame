package fight;

import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class CastleController {}
/*
    CastleView cv = new CastleView();
    
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

    private int[] ownedPotions = new int[12];

    int[] skeletonHp = {30,30,30,30};

    public void startFight(){

        MusicPick.musicStart("forest1","music");

        currentEnergy = 5;

        setStartLabels();
        cv.fieldFightFrame();
        hoverEffect();
        targetSystem();

        //ActionListeners
        cv.attackButton.addActionListener(e -> attackPressed());
        cv.blockButton.addActionListener(e -> blockPressed());
        cv.itemButton.addActionListener(e -> cv.itemPressed());
        cv.skillButton.addActionListener(e -> spellMenuActive()); //for now
        cv.endTurnButton.addActionListener(e-> startNewTurn());
        cv.skill1Button.addActionListener(e -> {skill1();});
        cv.skill2Button.addActionListener(e -> {skill2();});
        cv.skill3Button.addActionListener(e -> {skill3();});
        cv.skill4Button.addActionListener(e -> {skill4();});
        cv.returnButton.addActionListener(e-> spellMenuInactive());

        //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
        cv.potion1.addActionListener(e->usePotion(1));
        cv.potion2.addActionListener(e->usePotion(2));
        cv.potion3.addActionListener(e->usePotion(3));
        cv.potion4.addActionListener(e->usePotion(4));
        cv.potion5.addActionListener(e->usePotion(5));
        cv.potion6.addActionListener(e->usePotion(6));
        cv.potion7.addActionListener(e->usePotion(7));
        cv.potion8.addActionListener(e->usePotion(8));
        cv.potion9.addActionListener(e->usePotion(9));
        cv.potion10.addActionListener(e->usePotion(10));
        cv.potion11.addActionListener(e->usePotion(11));
        cv.potion12.addActionListener(e->usePotion(12));

        //Dispose the item frame.
        cv.exitInventory.addActionListener(e->cv.inventory.dispose());
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
            cv.whosTurn.setText("Warrior's turn");
            cv.playersHp.setText("Hp: "+warriorCurrentHp);
            cv.energy.setText("Energy: "+warriorEnergyInt);
            cv.block.setText("Block: "+warriorBlock);
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
            cv.whosTurn.setText("Ranger's turn");
            cv.playersHp.setText("Hp: "+rangerCurrentHp);
            cv.energy.setText("Energy: "+rangerEnergyInt);
            cv.block.setText("Block: "+rangerBlock);
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
            cv.whosTurn.setText("Mage's turn");
            cv.playersHp.setText("Hp: "+mageCurrentHp);
            cv.energy.setText("Energy: "+mageEnergyInt);
            cv.block.setText("Block: "+mageBlock);
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
            cv.whosTurn.setText("Healer's turn");
            cv.playersHp.setText("Hp: "+healerCurrentHp);
            cv.energy.setText("Energy: "+healerEnergyInt);
            cv.block.setText("Block: "+healerBlock);

        }
        //If healer is dead, skip.
        if (turns==4 && healerCurrentHp<1){
            turns=5;
        }
        //  ***ENEMIES TURN***
        if (turns==5){ac.enemyTurnTimer.start();}
    }

    public void targetSystem(){

        cv.skeleton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ac.target = 1;
                cv.targetarrow.setLocation(875, 250);
                cv.targetarrow.setVisible(true);
            }
        });
        cv.skeleton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ac.target = 2;
                cv.targetarrow.setLocation(1065, 250);
                cv.targetarrow.setVisible(true);
            }
        });
        cv.skeleton3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ac.target = 3;
                cv.targetarrow.setLocation(925, 325);
                cv.targetarrow.setVisible(true);
            }
        });
        cv.skeleton4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ac.target = 4;
                cv.targetarrow.setLocation(1100, 325);
                cv.targetarrow.setVisible(true);
            }
        });
    }

    private void skill1(){
        if (turns == 1 && warriorEnergyInt>1 && cv.targetarrow.isVisible()){
            warriorEnergyInt=warriorEnergyInt-2;
            currentEnergy=currentEnergy-2;
            cv.energy.setText("Energy: "+warriorEnergyInt);
            ac.charge.start();
            mobDeath();
            isFightOver();
        }
        if (turns==2 && rangerEnergyInt>3 && cv.targetarrow.isVisible()){
            rangerEnergyInt=rangerEnergyInt-4;
            currentEnergy=currentEnergy-4;
            cv.energy.setText("Energy: "+rangerEnergyInt);
            ac.volley.start();
            mobDeath();
            isFightOver();
        }
        if (turns==3 && mageEnergyInt>1 && cv.targetarrow.isVisible()){
            ac.pyroBlastX = 90;
            ac.pyroblastY = 300;
            ac.followup = true;
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            cv.energy.setText("Energy: "+mageEnergyInt);
            ac.fireBall.start();
            mobDeath();
            isFightOver();
        }
        if (turns==4 && healerEnergyInt>1) {
            healingTargetMenu(1);
        }
    }

    private void skill2(){
        if (turns == 1 && warriorEnergyInt>1){
            warriorEnergyInt=warriorEnergyInt-2;
            currentEnergy=currentEnergy-2;
            cv.energy.setText("Energy: "+warriorEnergyInt);
            ac.dunk.start();
            mobDeath();
            isFightOver();
        }
        if (turns==2 && rangerEnergyInt>2){
            rangerEnergyInt=rangerEnergyInt-3;
            currentEnergy=currentEnergy-3;
            cv.energy.setText("Energy: "+rangerEnergyInt);
            ac.bombthrow.start();
            mobDeath();
            isFightOver();
        }
        if (turns == 3){

        }
        if (turns == 4 && healerEnergyInt>1){
            healingTargetMenu(2);
        }
    }

    private void skill3(){
        if (turns == 1){
            ac.followup = true;
            ac.shout.start();
        }
        if(turns == 2){

        }
        if (turns==3 && mageEnergyInt>2){
            mageEnergyInt=mageEnergyInt-3;
            currentEnergy=currentEnergy-3;
            cv.energy.setText("Energy: "+mageEnergyInt);
            ac.flameStrike.start();
            mobDeath();
            isFightOver();
        }
        if (turns == 4 && healerEnergyInt>4){
            healerEnergyInt=healerEnergyInt-5;
            currentEnergy=currentEnergy-5;
            cv.energy.setText("Energy: "+healerEnergyInt);
            ac.groupHealSpell.start();
        }
    }

    private void skill4(){
        if (turns == 1){
            ac.shout.start();
        }
        if(turns == 2 && rangerEnergyInt>2){
            rangerEnergyInt=rangerEnergyInt-3;
            currentEnergy=currentEnergy-3;
            cv.energy.setText("Energy: "+rangerEnergyInt);
            ac.stealth();
        }
        if (turns == 3 && mageEnergyInt>4 && cv.targetarrow.isVisible()){
            ac.pyroBlastX = 90;
            ac.pyroblastY = 300;
            ac.followup = true;
            mageEnergyInt=mageEnergyInt-5;
            currentEnergy=currentEnergy-5;
            cv.energy.setText("Energy: "+mageEnergyInt);
            ac.pyroBlast.start();
            mobDeath();
            isFightOver();
        }
        if (turns == 4){

        }
    }

    private void healingTargetMenu(int chosenSpell) {
        cv.skill1Button.setVisible(false);
        cv.skill2Button.setVisible(false);
        cv.skill3Button.setVisible(false);
        cv.skill4Button.setVisible(false);

        cv.healWarriorButton.setVisible(true);
        cv.healRangerButton.setVisible(true);
        cv.healMageButton.setVisible(true);
        cv.healHealerButton.setVisible(true);

        cv.healWarriorButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 1;
                ac.holyLightSpell.start();}

            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 1;
                ac.smallHolyLightSpell.start();}
        });
        cv.healRangerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 2;
                ac.holyLightSpell.start();}
            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 2;
                ac.smallHolyLightSpell.start();}
        });
        cv.healMageButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 3;
                ac.holyLightSpell.start();}
            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 3;
                ac.smallHolyLightSpell.start();}
        });
        cv.healHealerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 4;
                ac.holyLightSpell.start();}
            if (chosenSpell == 2 && healerEnergyInt > 1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                cv.energy.setText("Energy: "+healerEnergyInt);
                ac.healTarget = 4;
                ac.smallHolyLightSpell.start();}
        });
    }

    private void spellMenuActive(){
        cv.attackButton.setVisible(false);
        cv.blockButton.setVisible(false);
        cv.itemButton.setVisible(false);
        cv.skillButton.setVisible(false);
        cv.endTurnButton.setVisible(false);

        cv.skill1Button.setVisible(true);
        cv.skill2Button.setVisible(true);
        cv.skill3Button.setVisible(true);
        cv.skill4Button.setVisible(true);
        cv.returnButton.setVisible(true);

        //warrior
        if (turns == 1){
            cv.skill1Button.setText("Charge");
            cv.skill2Button.setText("Slam");
            cv.skill3Button.setText("Battlecry");
            cv.skill4Button.setText("Demoralize");
        }
        //ranger
        if (turns == 2){
            cv.skill1Button.setText("Volley");
            cv.skill2Button.setText("Bomb");
            cv.skill3Button.setText("TBA");
            cv.skill4Button.setText("Stealth");
        }
        //mage
        if (turns == 3) {
            cv.skill1Button.setText("Fireball");
            cv.skill2Button.setText("TBA");
            cv.skill3Button.setText("Flamestrike");
            cv.skill4Button.setText("Pyroblast");
        }
        //healer
        if (turns == 4){
            cv.skill1Button.setText("Holy light");
            cv.skill2Button.setText("Blessed Light");
            cv.skill3Button.setText("Party heal");
            cv.skill4Button.setText("TBA");
        }
    }
    //When player press block
    private void blockPressed(){

        //If its warrior's turn and player has 2 or more energy.
        if(turns==1 && warriorEnergyInt>1){
            warriorEnergyInt=warriorEnergyInt-2;
            currentEnergy=currentEnergy-2;
            warriorBlock+=5;
            cv.energy.setText("Energy: "+warriorEnergyInt);
            cv.block.setText("Block: "+warriorBlock);
        }
        //If its ranger's turn and player has 2 or more energy.
        else if(turns==2 && rangerEnergyInt>1){
            rangerEnergyInt=rangerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            rangerBlock+=5;
            cv.energy.setText("Energy: "+rangerEnergyInt);
            cv.block.setText("Block: "+rangerBlock);
        }
        //If its mage's turn and player has 2 or more energy.
        else if(turns==3 && mageEnergyInt>1){
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            mageBlock+=5;
            cv.energy.setText("Energy: "+mageEnergyInt);
            cv.block.setText("Block: "+mageBlock);
        }
        //If its healer's turn and player has 2 or more energy.
        else if(turns==4 && healerEnergyInt>1){
            healerEnergyInt=healerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            healerBlock+=5;
            cv.energy.setText("Energy: "+healerEnergyInt);
            cv.block.setText("Block: "+healerBlock);
        }
    }
    //When you press the "attack button".
    private void attackPressed(){

        //If its warrior's turn and player has 2 or more energy.
        if(turns==1 && warriorEnergyInt>1 && cv.targetarrow.isVisible()){
            warriorEnergyInt=warriorEnergyInt-2; //Energy -2.
            currentEnergy=currentEnergy-2; // Update currentEnergy.
            cv.energy.setText("Energy: "+warriorEnergyInt); //Update energyLabel
            ac.tackle.start(); //Warrior deals damage to a wolf.
            mobDeath(); //Check if enemy died.
            isFightOver(); //Check if all enemies/party members are dead.
        }
        //If its ranger's turn and player has 2 or more energy.
        else if(turns==2 && rangerEnergyInt>1 && cv.targetarrow.isVisible()){
            rangerEnergyInt=rangerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            cv.energy.setText("Energy: "+rangerEnergyInt);
            ac.shoot.start();
            mobDeath();
            isFightOver();
        }
        //If its mage's turn and player has 2 or more energy.
        else if(turns==3 && mageEnergyInt>1 && cv.targetarrow.isVisible()){
            mageEnergyInt=mageEnergyInt-2;
            currentEnergy=currentEnergy-2;
            cv.energy.setText("Energy: "+mageEnergyInt);
            ac.blast.start();
            mobDeath();
            isFightOver();
        }
        //If its healer's turn and player has 2 or more energy.
        else if(turns==4 && healerEnergyInt>1 && cv.targetarrow.isVisible()){
            healerEnergyInt=healerEnergyInt-2;
            currentEnergy=currentEnergy-2;
            cv.energy.setText("Energy: "+healerEnergyInt);
            ac.healerAttack.start();
            mobDeath();
            isFightOver();
        }
    }

    //Checks if all of the enemies or party-members are dead.
    private void isFightOver() {
        //If all of the wolves are dead. Open lootScreen.
        if (skeletonHp[0] < 1 && skeletonHp[1] < 1 && skeletonHp[2] < 1 && skeletonHp[3] < 1) {
            MusicPick.musicStop();
            cv.fieldFightJFrame.dispose();
            //TODO This does not follow MVC
            FightModel fm = new FightModel();
            fm.fightWon(2);
        }
        //In the whole party is dead, game is over. Send to loseScreen.
        if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
            cv.fieldFightJFrame.dispose();
            //Death screen,
        }
        //If none of these are true, nothing happens and the fight goes on.
    }

    public void setStartLabels(){

        cv.potion1Label = new JLabel("" + ownedPotions[0]);
        cv.potion2Label = new JLabel("" + ownedPotions[1]);
        cv.potion3Label = new JLabel("" + ownedPotions[2]);
        cv.potion4Label = new JLabel("" + ownedPotions[3]);
        cv.potion5Label = new JLabel("" + ownedPotions[4]);
        cv.potion6Label = new JLabel("" + ownedPotions[5]);
        cv.potion7Label = new JLabel("" + ownedPotions[6]);
        cv.potion8Label = new JLabel("" + ownedPotions[7]);
        cv.potion9Label = new JLabel("" + ownedPotions[8]);
        cv.potion10Label = new JLabel("" + ownedPotions[9]);
        cv.potion11Label = new JLabel("" + ownedPotions[10]);
        cv.potion12Label = new JLabel("" + ownedPotions[11]);

        cv.skeleton1 = new JLabel("Skeleton 1: "+ skeletonHp[0]);
        cv.skeleton2 = new JLabel("Skeleton 2: "+ skeletonHp[1]);
        cv.skeleton3Hp = new JLabel("Skeleton 3: "+ skeletonHp[2]);
        cv.skeleton4Hp = new JLabel("Skeleton 4: "+ skeletonHp[3]);

        cv.playersHp = new JLabel("Hp: "+warriorCurrentHp);
        cv.player1Hp = new JLabel("Warrior: "+ warriorCurrentHp);
        cv.player2Hp = new JLabel("Mage:    "+ mageCurrentHp);
        cv.player3Hp = new JLabel("Ranger:  "+ rangerCurrentHp);
        cv.player4Hp = new JLabel("Healer:  "+ healerCurrentHp);
        cv.block = new JLabel("Block: "+warriorBlock);
    }

    //When the skeleton attacks.
    private void skeletonAttack() {
        ac.target = (int) (Math.random() * 4); //Random target, 0-3.
        int skeletonDamage = (int) (Math.random() * 10) + 15;//Generate random damage, 15-25.
        ac.takeDamage.start();

        //Loops until it reaches an alive party-member.
        while (true) {

            //Warrior, Target 2.
            if (ac.target == 0) {
                //If warrior is dead, target=1.
                if (warriorCurrentHp < 1) {
                    ac.target=1;
                }
                //If warrior is alive.
                if (warriorCurrentHp >0) {
                    skeletonDamage=skeletonDamage-warriorBlock; //Warrior take damage equal to skeleton damage.
                    warriorCurrentHp = warriorCurrentHp - skeletonDamage; //Update warrior hp.
                    cv.player1Hp.setText("Warrior: "+warriorCurrentHp); //Update hp Label.
                    break;
                }
            }
            //Mage, Target 1.
            if (ac.target == 1) {
                //If mage is dead, target=2.
                if (mageCurrentHp < 1) {
                    ac.target = 2;
                }
                //If mage is alive.
                if (mageCurrentHp >0) {
                    skeletonDamage=skeletonDamage-mageBlock;
                    mageCurrentHp = mageCurrentHp - skeletonDamage;
                    cv.player2Hp.setText("Mage:    "+mageCurrentHp);
                    break;
                }
            }
            //Ranger, target 2.
            if (ac.target == 2) {
                //If ranger is dead, target=3.
                if (rangerCurrentHp < 1) {
                    ac.target = 3;
                }
                //If ranger is alive.
                if (rangerCurrentHp >0) {
                    skeletonDamage=skeletonDamage-rangerBlock;
                    rangerCurrentHp = rangerCurrentHp - skeletonDamage;
                    cv.player3Hp.setText("Ranger:  "+rangerCurrentHp);
                    break;
                }
            }
            //Healer, target3.
            if (ac.target == 3) {
                //If healer is dead, target=0.
                if (healerCurrentHp < 1) {
                    ac.target = 0;
                }
                //If healer is alive.
                if (healerCurrentHp >0) {
                    skeletonDamage=skeletonDamage-healerBlock;
                    healerCurrentHp = healerCurrentHp - skeletonDamage;
                    cv.player4Hp.setText("Healer:   "+healerCurrentHp);
                    break;
                }
            }
        }
    }

    //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void mobDeath(){

        if(skeletonHp[0]<=0){
            cv.skeleton1Hp.setText("Skeleton 1: 0");
            cv.skeleton1.setVisible(false);
            if (ac.target == 1) {cv.targetarrow.setVisible(false);}
        }
        if(skeletonHp[1]<=0){
            cv.skeleton2Hp.setText("Skeleton 2: 0");
            cv.skeleton2.setVisible(false);
            if (ac.target == 2) {cv.targetarrow.setVisible(false);}
        }
        if(skeletonHp[2]<=0){
            cv.skeleton3Hp.setText("Skeleton 3: 0");
            cv.skeleton3.setVisible(false);
            if (ac.target == 3) {cv.targetarrow.setVisible(false);}
        }
        if(skeletonHp[3]<=0){
            cv.skeleton4Hp.setText("Skeleton 4: 0");
            cv.skeleton4.setVisible(false);
            if (ac.target == 4) {cv.targetarrow.setVisible(false);}
        }
    }

    //Checks if any party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void partyDeath(){

        if(warriorCurrentHp<=0){
            warriorCurrentHp = 0;
            cv.player1Hp.setText("Warrior: "+warriorCurrentHp);
            cv.warrior.setVisible(false);
        }
        if(mageCurrentHp<=0){
            mageCurrentHp = 0;
            cv.player2Hp.setText("Mage:    "+mageCurrentHp);
            cv.mage.setVisible(false);
        }
        if(rangerCurrentHp<=0){
            rangerCurrentHp = 0;
            cv.player3Hp.setText("Ranger:  "+rangerCurrentHp);
            cv.ranger.setVisible(false);
        }
        if(healerCurrentHp<=0){
            healerCurrentHp = 0;
            cv.player4Hp.setText("Healer:  "+healerCurrentHp);
            cv.healer.setVisible(false);
        }
    }
    private void spellMenuInactive(){
        cv.attackButton.setVisible(true);
        cv.blockButton.setVisible(true);
        cv.itemButton.setVisible(true);
        cv.skillButton.setVisible(true);
        cv.endTurnButton.setVisible(true);

        cv.skill1Button.setVisible(false);
        cv.skill2Button.setVisible(false);
        cv.skill3Button.setVisible(false);
        cv.skill4Button.setVisible(false);
        cv.returnButton.setVisible(false);
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

    //Get the effect from potions.
    private void usePotion(int potion) {

        //Warrior
        if (turns == 1) {
            //If potion 1 "minor healing potion" is pressed.
            if (potion == 1) {
                //If player own that potion.
                if (ownedPotions[0] > 0) {
                    warriorCurrentHp += 10; //Heal warrior equals to the potions heal.
                    cv.playersHp.setText("Hp: " + warriorCurrentHp); //Update Warrior's hp Label.
                    cv.player1Hp.setText("Warrior: " + warriorCurrentHp); // Update currentPlayer Hp label.
                    ownedPotions[0]-=1;
                    cv.potion1Label.setText(""+ownedPotions[0]); //Update ownedPotion Label.
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    warriorCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + warriorCurrentHp);
                    cv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[1]-=1;
                    cv.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    warriorCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + warriorCurrentHp);
                    cv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[2]-=1;
                    cv.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    warriorBlock += 5;
                    cv.block.setText("Block: " + warriorBlock);
                    ownedPotions[3]-=1;
                    cv.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    warriorBlock += 20;
                    cv.block.setText("Block: " + warriorBlock);
                    ownedPotions[4]-=1;
                    cv.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    warriorBlock += 50;
                    cv.block.setText("Block: " + warriorBlock);
                    ownedPotions[5]-=1;
                    cv.potion6Label.setText(""+ownedPotions[5]);
                }
            } else if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    warriorEnergyInt += 3;
                    cv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[6]-=1;
                    cv.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    warriorCurrentHp += 5;
                    cv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[7]-=1;
                    cv.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    warriorCurrentHp += 10;
                    cv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[8]-=1;
                    cv.potion9Label.setText(""+ownedPotions[8]);
                }
            }
            if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    warriorDamage += 5;
                    ownedPotions[9]-=1;
                    cv.potion10Label.setText(""+ownedPotions[9]);
                }
            }
            if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    warriorDamage += 10;
                    ownedPotions[10]-=1;
                    cv.potion11Label.setText(""+ownedPotions[10]);
                }
            }
            if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    warriorDamage += 20;
                    ownedPotions[11]-=1;
                    cv.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }

        //Ranger
        else if (turns == 2) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    rangerCurrentHp += 10;
                    cv.playersHp.setText("Hp: " + rangerCurrentHp);
                    cv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[0]-=1;
                    cv.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    rangerCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + rangerCurrentHp);
                    cv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[1]-=1;
                    cv.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + rangerCurrentHp);
                    cv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[2]-=1;
                    cv.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    rangerBlock += 5;
                    cv.block.setText("Block: " + rangerBlock);
                    ownedPotions[3]-=1;
                    cv.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    rangerBlock += 20;
                    cv.block.setText("Block: " + rangerBlock);
                    ownedPotions[4]-=1;
                    cv.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    rangerBlock += 50;
                    cv.block.setText("Block: " + rangerBlock);
                    ownedPotions[5]-=1;
                    cv.potion6Label.setText(""+ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    rangerEnergyInt += 3;
                    cv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[6]-=1;
                    cv.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    rangerEnergyInt += 5;
                    cv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[7]-=1;
                    cv.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    rangerEnergyInt += 10;
                    cv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[8]-=1;
                    cv.potion9Label.setText(""+ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    rangerDamage += 5;
                    ownedPotions[9]-=1;
                    cv.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    rangerDamage += 10;
                    ownedPotions[10]-=1;
                    cv.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    rangerDamage += 20;
                    ownedPotions[11]-=1;
                    cv.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
        //Mage
        else if (turns == 3) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    mageCurrentHp += 10;
                    cv.playersHp.setText("Hp: " + mageCurrentHp);
                    cv.player3Hp.setText("Mage: " + mageCurrentHp);
                    ownedPotions[0]-=1;
                    cv.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    mageCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + mageCurrentHp);
                    cv.player3Hp.setText("Mage: " + mageCurrentHp);
                    ownedPotions[1]-=1;
                    cv.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + mageCurrentHp);
                    cv.player3Hp.setText("Mage: " + mageCurrentHp);
                    cv.potion3Label.setText(""+ownedPotions[2]);
                    ownedPotions[2]-=1;
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    mageBlock += 5;
                    cv.block.setText("Block: " + mageBlock);
                    ownedPotions[3]-=1;
                    cv.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    mageBlock += 20;
                    cv.block.setText("Block: " + mageBlock);
                    ownedPotions[4]-=1;
                    cv.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    mageBlock += 50;
                    cv.block.setText("Block: " + mageBlock);
                    ownedPotions[5]-=1;
                    cv.potion6Label.setText(""+ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    mageEnergyInt += 3;
                    cv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[6]-=1;
                    cv.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    mageEnergyInt += 5;
                    cv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[7]-=1;
                    cv.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    mageEnergyInt += 10;
                    cv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[8]-=1;
                    cv.potion9Label.setText(""+ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    mageDamage += 5;
                    ownedPotions[9]-=1;
                    cv.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    mageDamage += 10;
                    ownedPotions[10]-=1;
                    cv.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    mageDamage += 20;
                    ownedPotions[11]-=1;
                    cv.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
        //Healer
        else if (turns == 4) {
            if (potion == 1) {
                if (ownedPotions[0] > 0) {
                    healerCurrentHp += 10;
                    cv.playersHp.setText("Hp: " + healerCurrentHp);
                    cv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[0]-=1;
                    cv.potion1Label.setText(""+ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    healerCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + healerCurrentHp);
                    cv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[1]-=1;
                    cv.potion2Label.setText(""+ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    healerCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + healerCurrentHp);
                    cv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[2]-=1;
                    cv.potion3Label.setText(""+ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    healerBlock += 5;
                    cv.block.setText("Block: " + healerBlock);
                    ownedPotions[3]-=1;
                    cv.potion4Label.setText(""+ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    healerBlock += 20;
                    cv.block.setText("Block: " + healerBlock);
                    ownedPotions[4]-=1;
                    cv.potion5Label.setText(""+ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    healerBlock += 50;
                    cv.block.setText("Block: " + healerBlock);
                    ownedPotions[5]-=1;
                    cv.potion6Label.setText(""+ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    healerEnergyInt += 3;
                    cv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[6]-=1;
                    cv.potion7Label.setText(""+ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    healerEnergyInt += 5;
                    cv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[7]-=1;
                    cv.potion8Label.setText(""+ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    healerEnergyInt += 10;
                    cv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[8]-=1;
                    cv.potion9Label.setText(""+ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    healerDamage += 5;
                    ownedPotions[9]-=1;
                    cv.potion10Label.setText(""+ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    healerDamage += 10;
                    ownedPotions[10]-=1;
                    cv.potion11Label.setText(""+ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    healerDamage += 20;
                    ownedPotions[11]-=1;
                    cv.potion12Label.setText(""+ownedPotions[11]);
                }
            }
        }
    }

    //Add hover effect to buttons.
    private void hoverEffect() {
        //Attack Hover
        cv.attackButton.addMouseListener(new MouseAdapter() {
            //Change button color while hovering depending on your current energy.
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(currentEnergy>=2) {
                    cv.attackButton.setBackground(Color.lightGray);
                }
                if(currentEnergy<2){
                    cv.attackButton.setBackground(Color.pink);
                }
            }
            //Change back when not hovering over button
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cv.attackButton.setBackground(Color.white);
            }
        });

        //Block Hover
        cv.blockButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(currentEnergy>=2) {
                    cv.blockButton.setBackground(Color.lightGray);
                }
                if(currentEnergy<2){
                    cv.blockButton.setBackground(Color.pink);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cv.blockButton.setBackground(Color.white);
            }
        });

        //Item Hover
        cv.itemButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cv.itemButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cv.itemButton.setBackground(Color.white);
            }
        });
        //Skill Hover
        cv.skillButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cv.skillButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cv.skillButton.setBackground(Color.white);
            }
        });

        //End turn Hover
        cv.endTurnButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cv.endTurnButton.setBackground(Color.lightGray);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cv.endTurnButton.setBackground(Color.white);
            }
        });
    }
}*/