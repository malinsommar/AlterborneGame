package fight;

import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class FieldController {
    /*
        FieldView fv = new FieldView();

        //Get hp, block and damage from OldClasses.party
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

        int[] scarecrowHp = {30,30,30,30};

        public void startFight(){

            MusicPick.musicStart("forest1","music");

            currentEnergy = 5;

            setStartLabels();
            fv.fieldFightFrame();
            hoverEffect();
            targetSystem();

            //ActionListeners
            fv.attackButton.addActionListener(e -> attackPressed());
            fv.blockButton.addActionListener(e -> blockPressed());
            fv.itemButton.addActionListener(e -> fv.itemPressed());
            fv.skillButton.addActionListener(e -> spellMenuActive()); //for now
            fv.endTurnButton.addActionListener(e-> startNewTurn());
            fv.skill1Button.addActionListener(e -> {skill1();});
            fv.skill2Button.addActionListener(e -> {skill2();});
            fv.skill3Button.addActionListener(e -> {skill3();});
            fv.skill4Button.addActionListener(e -> {skill4();});
            fv.returnButton.addActionListener(e-> spellMenuInactive());

            //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
            fv.potion1.addActionListener(e->usePotion(1));
            fv.potion2.addActionListener(e->usePotion(2));
            fv.potion3.addActionListener(e->usePotion(3));
            fv.potion4.addActionListener(e->usePotion(4));
            fv.potion5.addActionListener(e->usePotion(5));
            fv.potion6.addActionListener(e->usePotion(6));
            fv.potion7.addActionListener(e->usePotion(7));
            fv.potion8.addActionListener(e->usePotion(8));
            fv.potion9.addActionListener(e->usePotion(9));
            fv.potion10.addActionListener(e->usePotion(10));
            fv.potion11.addActionListener(e->usePotion(11));
            fv.potion12.addActionListener(e->usePotion(12));

            //Dispose the item frame.
            fv.exitInventory.addActionListener(e->fv.inventory.dispose());
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
                fv.whosTurn.setText("Warrior's turn");
                fv.playersHp.setText("Hp: "+warriorCurrentHp);
                fv.energy.setText("Energy: "+warriorEnergyInt);
                fv.block.setText("Block: "+warriorBlock);
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
                fv.whosTurn.setText("Ranger's turn");
                fv.playersHp.setText("Hp: "+rangerCurrentHp);
                fv.energy.setText("Energy: "+rangerEnergyInt);
                fv.block.setText("Block: "+rangerBlock);
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
                fv.whosTurn.setText("Mage's turn");
                fv.playersHp.setText("Hp: "+mageCurrentHp);
                fv.energy.setText("Energy: "+mageEnergyInt);
                fv.block.setText("Block: "+mageBlock);
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
                fv.whosTurn.setText("Healer's turn");
                fv.playersHp.setText("Hp: "+healerCurrentHp);
                fv.energy.setText("Energy: "+healerEnergyInt);
                fv.block.setText("Block: "+healerBlock);

            }
            //If healer is dead, skip.
            if (turns==4 && healerCurrentHp<1){
                turns=5;
            }
            //  ***ENEMIES TURN***
            if (turns==5){enemyTurnTimer.start();}
        }

        public void targetSystem(){

            fv.scarecrow1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 1;
                    fv.targetarrow.setLocation(875, 250);
                    fv.targetarrow.setVisible(true);
                }
            });
            fv.scarecrow2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 2;
                    fv.targetarrow.setLocation(1065, 250);
                    fv.targetarrow.setVisible(true);
                }
            });
            fv.scarecrow3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 3;
                    fv.targetarrow.setLocation(925, 325);
                    fv.targetarrow.setVisible(true);
                }
            });
            fv.scarecrow4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 4;
                    fv.targetarrow.setLocation(1100, 325);
                    fv.targetarrow.setVisible(true);
                }
            });
        }

        private void skill1(){
            if (turns == 1 && warriorEnergyInt>1 && fv.targetarrow.isVisible()){
                warriorEnergyInt=warriorEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fv.energy.setText("Energy: "+warriorEnergyInt);
                charge.start();
                mobDeath();
                isFightOver();
            }
            if (turns==2 && rangerEnergyInt>3 && fv.targetarrow.isVisible()){
                rangerEnergyInt=rangerEnergyInt-4;
                currentEnergy=currentEnergy-4;
                fv.energy.setText("Energy: "+rangerEnergyInt);
                volley.start();
                mobDeath();
                isFightOver();
            }
            if (turns==3 && mageEnergyInt>1 && fv.targetarrow.isVisible()){
                pyroBlastX = 90;
                pyroblastY = 300;
                followup = true;
                mageEnergyInt=mageEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fv.energy.setText("Energy: "+mageEnergyInt);
                fireBall.start();
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
                fv.energy.setText("Energy: "+warriorEnergyInt);
                dunk.start();
                mobDeath();
                isFightOver();
            }
            if (turns==2 && rangerEnergyInt>2){
                rangerEnergyInt=rangerEnergyInt-3;
                currentEnergy=currentEnergy-3;
                fv.energy.setText("Energy: "+rangerEnergyInt);
                bombthrow.start();
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
                followup = true;
                shout.start();
            }
            if(turns == 2){

            }
            if (turns==3 && mageEnergyInt>2){
                mageEnergyInt=mageEnergyInt-3;
                currentEnergy=currentEnergy-3;
                fv.energy.setText("Energy: "+mageEnergyInt);
                flameStrike.start();
                mobDeath();
                isFightOver();
            }
            if (turns == 4 && healerEnergyInt>4){
                healerEnergyInt=healerEnergyInt-5;
                currentEnergy=currentEnergy-5;
                fv.energy.setText("Energy: "+healerEnergyInt);
                groupHealSpell.start();
            }
        }

        private void skill4(){
            if (turns == 1){
                shout.start();
            }
            if(turns == 2 && rangerEnergyInt>2){
                rangerEnergyInt=rangerEnergyInt-3;
                currentEnergy=currentEnergy-3;
                fv.energy.setText("Energy: "+rangerEnergyInt);
                stealth();
            }
            if (turns == 3 && mageEnergyInt>4 && fv.targetarrow.isVisible()){
                pyroBlastX = 90;
                pyroblastY = 300;
                followup = true;
                mageEnergyInt=mageEnergyInt-5;
                currentEnergy=currentEnergy-5;
                fv.energy.setText("Energy: "+mageEnergyInt);
                pyroBlast.start();
                mobDeath();
                isFightOver();
            }
            if (turns == 4){

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
                if (chosenSpell == 1 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 1;
                    holyLightSpell.start();}

                if (chosenSpell == 2 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 1;
                    smallHolyLightSpell.start();}
            });
            fv.healRangerButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 2;
                    holyLightSpell.start();}
                if (chosenSpell == 2 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 2;
                    smallHolyLightSpell.start();}
            });
            fv.healMageButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 3;
                    holyLightSpell.start();}
                if (chosenSpell == 2 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 3;
                    smallHolyLightSpell.start();}
            });
            fv.healHealerButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 4;
                    holyLightSpell.start();}
                if (chosenSpell == 2 && healerEnergyInt > 1){
                    healerEnergyInt=healerEnergyInt-2;
                    currentEnergy=currentEnergy-2;
                    fv.energy.setText("Energy: "+healerEnergyInt);
                    healTarget = 4;
                    smallHolyLightSpell.start();}
            });
        }

        private void spellMenuActive(){
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
                fv.skill1Button.setText("Charge");
                fv.skill2Button.setText("Slam");
                fv.skill3Button.setText("Battlecry");
                fv.skill4Button.setText("Demoralize");
            }
            //ranger
            if (turns == 2){
                fv.skill1Button.setText("Volley");
                fv.skill2Button.setText("Bomb");
                fv.skill3Button.setText("TBA");
                fv.skill4Button.setText("Stealth");
            }
            //mage
            if (turns == 3) {
                fv.skill1Button.setText("Fireball");
                fv.skill2Button.setText("TBA");
                fv.skill3Button.setText("Flamestrike");
                fv.skill4Button.setText("Pyroblast");
            }
            //healer
            if (turns == 4){
                fv.skill1Button.setText("Holy light");
                fv.skill2Button.setText("Blessed Light");
                fv.skill3Button.setText("Party heal");
                fv.skill4Button.setText("TBA");
            }
        }
        //When player press block
        private void blockPressed(){

            //If its warrior's turn and player has 2 or more energy.
            if(turns==1 && warriorEnergyInt>1){
                warriorEnergyInt=warriorEnergyInt-2;
                currentEnergy=currentEnergy-2;
                warriorBlock+=5;
                fv.energy.setText("Energy: "+warriorEnergyInt);
                fv.block.setText("Block: "+warriorBlock);
            }
            //If its ranger's turn and player has 2 or more energy.
            else if(turns==2 && rangerEnergyInt>1){
                rangerEnergyInt=rangerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                rangerBlock+=5;
                fv.energy.setText("Energy: "+rangerEnergyInt);
                fv.block.setText("Block: "+rangerBlock);
            }
            //If its mage's turn and player has 2 or more energy.
            else if(turns==3 && mageEnergyInt>1){
                mageEnergyInt=mageEnergyInt-2;
                currentEnergy=currentEnergy-2;
                mageBlock+=5;
                fv.energy.setText("Energy: "+mageEnergyInt);
                fv.block.setText("Block: "+mageBlock);
            }
            //If its healer's turn and player has 2 or more energy.
            else if(turns==4 && healerEnergyInt>1){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                healerBlock+=5;
                fv.energy.setText("Energy: "+healerEnergyInt);
                fv.block.setText("Block: "+healerBlock);
            }
        }
        //When you press the "attack button".
        private void attackPressed(){

            //If its warrior's turn and player has 2 or more energy.
            if(turns==1 && warriorEnergyInt>1 && fv.targetarrow.isVisible()){
                warriorEnergyInt=warriorEnergyInt-2; //Energy -2.
                currentEnergy=currentEnergy-2; // Update currentEnergy.
                fv.energy.setText("Energy: "+warriorEnergyInt); //Update energyLabel
                tackle.start(); //Warrior deals damage to a wolf.
                mobDeath(); //Check if enemy died.
                isFightOver(); //Check if all enemies/OldClasses.party members are dead.
            }
            //If its ranger's turn and player has 2 or more energy.
            else if(turns==2 && rangerEnergyInt>1 && fv.targetarrow.isVisible()){
                rangerEnergyInt=rangerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fv.energy.setText("Energy: "+rangerEnergyInt);
                shoot.start();
                mobDeath();
                isFightOver();
            }
            //If its mage's turn and player has 2 or more energy.
            else if(turns==3 && mageEnergyInt>1 && fv.targetarrow.isVisible()){
                mageEnergyInt=mageEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fv.energy.setText("Energy: "+mageEnergyInt);
                blast.start();
                mobDeath();
                isFightOver();
            }
            //If its healer's turn and player has 2 or more energy.
            else if(turns==4 && healerEnergyInt>1 && fv.targetarrow.isVisible()){
                healerEnergyInt=healerEnergyInt-2;
                currentEnergy=currentEnergy-2;
                fv.energy.setText("Energy: "+healerEnergyInt);
                healerAttack.start();
                mobDeath();
                isFightOver();
            }
        }

        //Checks if all of the enemies or OldClasses.party-members are dead.
        private void isFightOver() {
            //If all of the wolves are dead. Open lootScreen.
            if (scarecrowHp[0] < 1 && scarecrowHp[1] < 1 && scarecrowHp[2] < 1 && scarecrowHp[3] < 1) {
                MusicPick.musicStop();
                fv.fieldFightJFrame.dispose();
                //TODO This does not follow MVC
                FightModel fm = new FightModel();
                fm.fightWon(2);
            }
            //In the whole OldClasses.party is dead, game is over. Send to loseScreen.
            if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
                fv.fieldFightJFrame.dispose();
                //Death screen,
            }
            //If none of these are true, nothing happens and the fight goes on.
        }

        public void setStartLabels(){

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

            fv.scarecrow1 = new JLabel("Scarecrow 1: "+ scarecrowHp[0]);
            fv.scarecrow2 = new JLabel("Scarecrow 2: "+ scarecrowHp[1]);
            fv.scarecrow3Hp = new JLabel("Scarecrow 3: "+ scarecrowHp[2]);
            fv.scarecrow4Hp = new JLabel("Scarecrow 4: "+ scarecrowHp[3]);

            fv.playersHp = new JLabel("Hp: "+warriorCurrentHp);
            fv.player1Hp = new JLabel("Warrior: "+ warriorCurrentHp);
            fv.player2Hp = new JLabel("Mage:    "+ mageCurrentHp);
            fv.player3Hp = new JLabel("Ranger:  "+ rangerCurrentHp);
            fv.player4Hp = new JLabel("Healer:  "+ healerCurrentHp);
            fv.block = new JLabel("Block: "+warriorBlock);
        }

        //When the scarecrow attacks.
        private void scarecrowAttack() {
            target = (int) (Math.random() * 4); //Random target, 0-3.
            int scarecrowDamage = (int) (Math.random() * 10) + 15;//Generate random damage, 15-25.
            takeDamage.start();

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
                        scarecrowDamage=scarecrowDamage-warriorBlock; //Warrior take damage equal to scarecrow damage.
                        warriorCurrentHp = warriorCurrentHp - scarecrowDamage; //Update warrior hp.
                        fv.player1Hp.setText("Warrior: "+warriorCurrentHp); //Update hp Label.
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
                        scarecrowDamage=scarecrowDamage-mageBlock;
                        mageCurrentHp = mageCurrentHp - scarecrowDamage;
                        fv.player2Hp.setText("Mage:    "+mageCurrentHp);
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
                        scarecrowDamage=scarecrowDamage-rangerBlock;
                        rangerCurrentHp = rangerCurrentHp - scarecrowDamage;
                        fv.player3Hp.setText("Ranger:  "+rangerCurrentHp);
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
                        scarecrowDamage=scarecrowDamage-healerBlock;
                        healerCurrentHp = healerCurrentHp - scarecrowDamage;
                        fv.player4Hp.setText("Healer:   "+healerCurrentHp);
                        break;
                    }
                }
            }
        }

        //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
        private void mobDeath(){

            if(scarecrowHp[0]<=0){
                fv.scarecrow1Hp.setText("Scarecrow 1: 0");
                fv.scarecrow1.setVisible(false);
                if (target == 1) {fv.targetarrow.setVisible(false);}
            }
            if(scarecrowHp[1]<=0){
                fv.scarecrow2Hp.setText("Scarecrow 2: 0");
                fv.scarecrow2.setVisible(false);
                if (target == 2) {fv.targetarrow.setVisible(false);}
            }
            if(scarecrowHp[2]<=0){
                fv.scarecrow3Hp.setText("Scarecrow 3: 0");
                fv.scarecrow3.setVisible(false);
                if (target == 3) {fv.targetarrow.setVisible(false);}
            }
            if(scarecrowHp[3]<=0){
                fv.scarecrow4Hp.setText("Scarecrow 4: 0");
                fv.scarecrow4.setVisible(false);
                if (target == 4) {fv.targetarrow.setVisible(false);}
            }
        }

        //Checks if any OldClasses.party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
        private void partyDeath(){

            if(warriorCurrentHp<=0){
                warriorCurrentHp = 0;
                fv.player1Hp.setText("Warrior: "+warriorCurrentHp);
                fv.warrior.setVisible(false);
            }
            if(mageCurrentHp<=0){
                mageCurrentHp = 0;
                fv.player2Hp.setText("Mage:    "+mageCurrentHp);
                fv.mage.setVisible(false);
            }
            if(rangerCurrentHp<=0){
                rangerCurrentHp = 0;
                fv.player3Hp.setText("Ranger:  "+rangerCurrentHp);
                fv.ranger.setVisible(false);
            }
            if(healerCurrentHp<=0){
                healerCurrentHp = 0;
                fv.player4Hp.setText("Healer:  "+healerCurrentHp);
                fv.healer.setVisible(false);
            }
        }
        private void spellMenuInactive(){
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
                        fv.playersHp.setText("Hp: " + warriorCurrentHp); //Update Warrior's hp Label.
                        fv.player1Hp.setText("Warrior: " + warriorCurrentHp); // Update currentPlayer Hp label.
                        ownedPotions[0]-=1;
                        fv.potion1Label.setText(""+ownedPotions[0]); //Update ownedPotion Label.
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        warriorCurrentHp += 30;
                        fv.playersHp.setText("Hp: " + warriorCurrentHp);
                        fv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                        ownedPotions[1]-=1;
                        fv.potion2Label.setText(""+ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        warriorCurrentHp += 60;
                        fv.playersHp.setText("Hp: " + warriorCurrentHp);
                        fv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                        ownedPotions[2]-=1;
                        fv.potion3Label.setText(""+ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        warriorBlock += 5;
                        fv.block.setText("Block: " + warriorBlock);
                        ownedPotions[3]-=1;
                        fv.potion4Label.setText(""+ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        warriorBlock += 20;
                        fv.block.setText("Block: " + warriorBlock);
                        ownedPotions[4]-=1;
                        fv.potion5Label.setText(""+ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        warriorBlock += 50;
                        fv.block.setText("Block: " + warriorBlock);
                        ownedPotions[5]-=1;
                        fv.potion6Label.setText(""+ownedPotions[5]);
                    }
                } else if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        warriorEnergyInt += 3;
                        fv.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[6]-=1;
                        fv.potion7Label.setText(""+ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        warriorCurrentHp += 5;
                        fv.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[7]-=1;
                        fv.potion8Label.setText(""+ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        warriorCurrentHp += 10;
                        fv.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[8]-=1;
                        fv.potion9Label.setText(""+ownedPotions[8]);
                    }
                }
                if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        warriorDamage += 5;
                        ownedPotions[9]-=1;
                        fv.potion10Label.setText(""+ownedPotions[9]);
                    }
                }
                if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        warriorDamage += 10;
                        ownedPotions[10]-=1;
                        fv.potion11Label.setText(""+ownedPotions[10]);
                    }
                }
                if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        warriorDamage += 20;
                        ownedPotions[11]-=1;
                        fv.potion12Label.setText(""+ownedPotions[11]);
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
                        ownedPotions[0]-=1;
                        fv.potion1Label.setText(""+ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        rangerCurrentHp += 30;
                        fv.playersHp.setText("Hp: " + rangerCurrentHp);
                        fv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[1]-=1;
                        fv.potion2Label.setText(""+ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        rangerCurrentHp += 60;
                        fv.playersHp.setText("Hp: " + rangerCurrentHp);
                        fv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[2]-=1;
                        fv.potion3Label.setText(""+ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        rangerBlock += 5;
                        fv.block.setText("Block: " + rangerBlock);
                        ownedPotions[3]-=1;
                        fv.potion4Label.setText(""+ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        rangerBlock += 20;
                        fv.block.setText("Block: " + rangerBlock);
                        ownedPotions[4]-=1;
                        fv.potion5Label.setText(""+ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        rangerBlock += 50;
                        fv.block.setText("Block: " + rangerBlock);
                        ownedPotions[5]-=1;
                        fv.potion6Label.setText(""+ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        rangerEnergyInt += 3;
                        fv.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[6]-=1;
                        fv.potion7Label.setText(""+ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        rangerEnergyInt += 5;
                        fv.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[7]-=1;
                        fv.potion8Label.setText(""+ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        rangerEnergyInt += 10;
                        fv.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[8]-=1;
                        fv.potion9Label.setText(""+ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        rangerDamage += 5;
                        ownedPotions[9]-=1;
                        fv.potion10Label.setText(""+ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        rangerDamage += 10;
                        ownedPotions[10]-=1;
                        fv.potion11Label.setText(""+ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        rangerDamage += 20;
                        ownedPotions[11]-=1;
                        fv.potion12Label.setText(""+ownedPotions[11]);
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
                        ownedPotions[0]-=1;
                        fv.potion1Label.setText(""+ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        mageCurrentHp += 30;
                        fv.playersHp.setText("Hp: " + mageCurrentHp);
                        fv.player3Hp.setText("Mage: " + mageCurrentHp);
                        ownedPotions[1]-=1;
                        fv.potion2Label.setText(""+ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        rangerCurrentHp += 60;
                        fv.playersHp.setText("Hp: " + mageCurrentHp);
                        fv.player3Hp.setText("Mage: " + mageCurrentHp);
                        fv.potion3Label.setText(""+ownedPotions[2]);
                        ownedPotions[2]-=1;
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        mageBlock += 5;
                        fv.block.setText("Block: " + mageBlock);
                        ownedPotions[3]-=1;
                        fv.potion4Label.setText(""+ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        mageBlock += 20;
                        fv.block.setText("Block: " + mageBlock);
                        ownedPotions[4]-=1;
                        fv.potion5Label.setText(""+ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        mageBlock += 50;
                        fv.block.setText("Block: " + mageBlock);
                        ownedPotions[5]-=1;
                        fv.potion6Label.setText(""+ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        mageEnergyInt += 3;
                        fv.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[6]-=1;
                        fv.potion7Label.setText(""+ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        mageEnergyInt += 5;
                        fv.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[7]-=1;
                        fv.potion8Label.setText(""+ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        mageEnergyInt += 10;
                        fv.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[8]-=1;
                        fv.potion9Label.setText(""+ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        mageDamage += 5;
                        ownedPotions[9]-=1;
                        fv.potion10Label.setText(""+ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        mageDamage += 10;
                        ownedPotions[10]-=1;
                        fv.potion11Label.setText(""+ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        mageDamage += 20;
                        ownedPotions[11]-=1;
                        fv.potion12Label.setText(""+ownedPotions[11]);
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
                        ownedPotions[0]-=1;
                        fv.potion1Label.setText(""+ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        healerCurrentHp += 30;
                        fv.playersHp.setText("Hp: " + healerCurrentHp);
                        fv.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[1]-=1;
                        fv.potion2Label.setText(""+ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        healerCurrentHp += 60;
                        fv.playersHp.setText("Hp: " + healerCurrentHp);
                        fv.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[2]-=1;
                        fv.potion3Label.setText(""+ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        healerBlock += 5;
                        fv.block.setText("Block: " + healerBlock);
                        ownedPotions[3]-=1;
                        fv.potion4Label.setText(""+ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        healerBlock += 20;
                        fv.block.setText("Block: " + healerBlock);
                        ownedPotions[4]-=1;
                        fv.potion5Label.setText(""+ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        healerBlock += 50;
                        fv.block.setText("Block: " + healerBlock);
                        ownedPotions[5]-=1;
                        fv.potion6Label.setText(""+ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        healerEnergyInt += 3;
                        fv.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[6]-=1;
                        fv.potion7Label.setText(""+ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        healerEnergyInt += 5;
                        fv.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[7]-=1;
                        fv.potion8Label.setText(""+ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        healerEnergyInt += 10;
                        fv.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[8]-=1;
                        fv.potion9Label.setText(""+ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        healerDamage += 5;
                        ownedPotions[9]-=1;
                        fv.potion10Label.setText(""+ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        healerDamage += 10;
                        ownedPotions[10]-=1;
                        fv.potion11Label.setText(""+ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        healerDamage += 20;
                        ownedPotions[11]-=1;
                        fv.potion12Label.setText(""+ownedPotions[11]);
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
                    if(currentEnergy>=2) {
                        fv.attackButton.setBackground(Color.lightGray);
                    }
                    if(currentEnergy<2){
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
                    if(currentEnergy>=2) {
                        fv.blockButton.setBackground(Color.lightGray);
                    }
                    if(currentEnergy<2){
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
     */
    }