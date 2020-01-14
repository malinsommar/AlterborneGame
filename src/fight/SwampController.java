package fight;

import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class SwampController {
    
        SwampView sv = new SwampView();


        //Get hp, block and damage from OldClasses.party
        private int warriorCurrentHp, mageCurrentHp, healerCurrentHp, rangerCurrentHp;
        private int warriorMaxHp, mageMaxHp, healerMaxHp, rangerMaxHp;
        private int warriorDamage, mageDamage, healerDamage, rangerDamage, damage;
        private int warriorBlock, mageBlock, healerBlock, rangerBlock;
        private int buffDamage[] = new int[4];
        private boolean debuffed = false;
        private int enemyDamage, enemyRandomDamage = 15, enemyBaseDamage = 20;

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

        public int trunk1X = 850, trunk1Y = 300, trunk1StartX = trunk1X, trunk1StartY = trunk1Y;
        public int trunk2X = 1030, trunk2Y = 300, trunk2StartX = trunk2X, trunk2StartY = trunk2Y;
        public int trunk3X = 920, trunk3Y = 370, trunk3StartX = trunk3X, trunk3StartY = trunk3Y;
        public int trunk4X = 1100, trunk4Y = 370, trunk4StartX = trunk4X, trunk4StartY = trunk4Y;

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

        private int[] trunkHp = new int[4];

        public void startFight() {

            MusicPick.musicStart("swamp", "music");

            currentEnergy = 5;
            warriorEnergyInt = 5;

            trunkHp[0] = 30;
            trunkHp[1] = 30;
            trunkHp[2] = 30;
            trunkHp[3] = 30;

            setStartLabels();
            sv.swampFightFrame();
            hoverEffect();
            targetSystem();

            //ActionListeners
            sv.attackButton.addActionListener(e -> {
                if (!animationPlaying && sv.targetarrow.isVisible()) attackPressed();
            });
            sv.blockButton.addActionListener(e -> blockPressed());
            sv.itemButton.addActionListener(e -> {
                sv.inventory.setVisible(true);
                itemMenuActivate();
            });
            sv.skillButton.addActionListener(e ->{
                if (!animationPlaying) spellMenuActive();
            }); //for now
            sv.endTurnButton.addActionListener(e -> {
                if (!animationPlaying) startNewTurn();
            });
            sv.skill1Button.addActionListener(e -> {
                if (!animationPlaying) skill1();
            });
            sv.skill2Button.addActionListener(e -> {
                if (!animationPlaying) skill2();
            });
            sv.skill3Button.addActionListener(e -> {
                if (!animationPlaying) skill3();
            });
            sv.skill4Button.addActionListener(e -> {
                if (!animationPlaying) skill4();
            });
            sv.returnButton.addActionListener(e -> spellMenuInactive());
            sv.returnButton.addActionListener(e -> sv.inventory.setVisible(false));

            //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
            sv.potion1.addActionListener(e -> usePotion(1));
            sv.potion2.addActionListener(e -> usePotion(2));
            sv.potion3.addActionListener(e -> usePotion(3));
            sv.potion4.addActionListener(e -> usePotion(4));
            sv.potion5.addActionListener(e -> usePotion(5));
            sv.potion6.addActionListener(e -> usePotion(6));
            sv.potion7.addActionListener(e -> usePotion(7));
            sv.potion8.addActionListener(e -> usePotion(8));
            sv.potion9.addActionListener(e -> usePotion(9));
            sv.potion10.addActionListener(e -> usePotion(10));
            sv.potion11.addActionListener(e -> usePotion(11));
            sv.potion12.addActionListener(e -> usePotion(12));

            //Dispose the item frame.
            //sv.returnButton.addActionListener(e -> sv.inventory.dispose());

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
                sv.whosTurn.setText("Warrior's turn");
                sv.playersHp.setText("Hp: " + warriorCurrentHp);
                sv.energy.setText("Energy: " + warriorEnergyInt);
                sv.block.setText("Block: " + warriorBlock);
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
                sv.whosTurn.setText("Ranger's turn");
                sv.playersHp.setText("Hp: " + rangerCurrentHp);
                sv.energy.setText("Energy: " + rangerEnergyInt);
                sv.block.setText("Block: " + rangerBlock);
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
                sv.whosTurn.setText("Mage's turn");
                sv.playersHp.setText("Hp: " + mageCurrentHp);
                sv.energy.setText("Energy: " + mageEnergyInt);
                sv.block.setText("Block: " + mageBlock);
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
                sv.whosTurn.setText("Healer's turn");
                sv.playersHp.setText("Hp: " + healerCurrentHp);
                sv.energy.setText("Energy: " + healerEnergyInt);
                sv.block.setText("Block: " + healerBlock);

            }
            //If healer is dead, skip.
            if (turns == 4 && healerCurrentHp < 1) {
                turns = 5;
            }
            //  ***ENEMIES TURN***
            if (turns == 5) {
                sv.whosTurn.setText(" ");
                sv.playersHp.setText(" ");
                sv.energy.setText(" ");
                sv.block.setText(" ");
                enemyTurnTimer.start();

                //removes temporary damage buffs at the end of turn
                for (int i = 0; i < buffDamage.length; i++) {
                    buffDamage[i] = 0;
                }

                turns = 0;
            }
        }

        private void enemyDamage(){
            for (int i = 0; i < trunkHp.length; i++) {
                if (trunkHp[i] > 0) {
                    trunkAttack();
                    partyDeath();
                }
            }
            isFightOver();
        }

        public void targetSystem() {

            sv.trunk1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 1;
                    sv.targetarrow.setLocation(790, 225);
                    sv.targetarrow.setVisible(true);
                }
            });
            sv.trunk2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 2;
                    sv.targetarrow.setLocation(1000, 225);
                    sv.targetarrow.setVisible(true);
                }
            });
            sv.trunk3.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 3;
                    sv.targetarrow.setLocation(880, 300);
                    sv.targetarrow.setVisible(true);
                }
            });
            sv.trunk4.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    target = 4;
                    sv.targetarrow.setLocation(1110, 300);
                    sv.targetarrow.setVisible(true);
                }
            });
        }

        private void skill1() {
            if (turns == 1 && warriorEnergyInt > 2 && sv.targetarrow.isVisible()) {
                warriorEnergyInt = warriorEnergyInt - 3;
                currentEnergy = currentEnergy - 3;
                sv.energy.setText("Energy: " + warriorEnergyInt);
                charge.start();
            }
            if (turns == 2 && rangerEnergyInt > 3 && sv.targetarrow.isVisible()) {
                rangerEnergyInt = rangerEnergyInt - 4;
                currentEnergy = currentEnergy - 4;
                sv.energy.setText("Energy: " + rangerEnergyInt);
                volley.start();
            }
            if (turns == 3 && mageEnergyInt > 1 && sv.targetarrow.isVisible()) {
                followup = true;
                mageEnergyInt = mageEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                sv.energy.setText("Energy: " + mageEnergyInt);
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
                sv.energy.setText("Energy: " + warriorEnergyInt);
                dunk.start();
            }
            if (turns == 2 && rangerEnergyInt > 2) {
                rangerEnergyInt = rangerEnergyInt - 3;
                currentEnergy = currentEnergy - 3;
                sv.energy.setText("Energy: " + rangerEnergyInt);
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
                sv.energy.setText("Energy: " + warriorEnergyInt);
                followup = true;
                shout.start();
            }
            if (turns == 2) {

            }
            if (turns == 3 && mageEnergyInt > 4) {
                mageEnergyInt = mageEnergyInt - 5;
                currentEnergy = currentEnergy - 5;
                sv.energy.setText("Energy: " + mageEnergyInt);
                flameStrike.start();
            }
            if (turns == 4 && healerEnergyInt > 4) {
                healerEnergyInt = healerEnergyInt - 5;
                currentEnergy = currentEnergy - 5;
                sv.energy.setText("Energy: " + healerEnergyInt);
                groupHealSpell.start();
            }
        }

        private void skill4() {
            if (turns == 1 && warriorEnergyInt>4){
                warriorEnergyInt=warriorEnergyInt-5;
                currentEnergy=currentEnergy-5;
                sv.energy.setText("Energy: " + warriorEnergyInt);
                shout.start();
            }
            if (turns == 2 && rangerEnergyInt > 2) {
                rangerEnergyInt = rangerEnergyInt - 3;
                currentEnergy = currentEnergy - 3;
                sv.energy.setText("Energy: " + rangerEnergyInt);
                stealth();
            }
            if (turns == 3 && mageEnergyInt > 4 && sv.targetarrow.isVisible()) {
                mageEnergyInt = mageEnergyInt - 5;
                currentEnergy = currentEnergy - 5;
                sv.energy.setText("Energy: " + mageEnergyInt);
                pyroBlast.start();
            }
            if (turns == 4) {

            }
        }

        private void healingTargetMenu(int chosenSpell) {
            sv.skill1Button.setVisible(false);
            sv.skill2Button.setVisible(false);
            sv.skill3Button.setVisible(false);
            sv.skill4Button.setVisible(false);

            sv.healWarriorButton.setVisible(true);
            sv.healRangerButton.setVisible(true);
            sv.healMageButton.setVisible(true);
            sv.healHealerButton.setVisible(true);

            sv.healWarriorButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 1;
                    holyLightSpell.start();
                }

                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 1;
                    smallHolyLightSpell.start();
                }
            });
            sv.healRangerButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 2;
                    holyLightSpell.start();
                }
                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 2;
                    smallHolyLightSpell.start();
                }
            });
            sv.healMageButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 3;
                    holyLightSpell.start();
                }
                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 3;
                    smallHolyLightSpell.start();
                }
            });
            sv.healHealerButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 4;
                    holyLightSpell.start();
                }
                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    sv.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 4;
                    smallHolyLightSpell.start();
                }
            });
        }

        private void itemMenuActivate(){
            sv.endTurnButton.setVisible(false);
            sv.returnButton.setVisible(true);
        }

        private void spellMenuActive() {
            sv.attackButton.setVisible(false);
            sv.blockButton.setVisible(false);
            sv.itemButton.setVisible(false);
            sv.skillButton.setVisible(false);
            sv.endTurnButton.setVisible(false);

            sv.skill1Button.setVisible(true);
            sv.skill2Button.setVisible(true);
            sv.skill3Button.setVisible(true);
            sv.skill4Button.setVisible(true);
            sv.returnButton.setVisible(true);

            //warrior
            if (turns == 1){
                sv.skill1Button.setText("Charge (3)");
                sv.skill2Button.setText("Slam (4)");
                sv.skill3Button.setText("Battlecry (3)");
                sv.skill4Button.setText("Demoralize (5)");
            }
            //ranger
            if (turns == 2){
                sv.skill1Button.setText("Volley (4)");
                sv.skill2Button.setText("Bomb (3)");
                sv.skill3Button.setText(" ");
                sv.skill4Button.setText("Stealth (3)");
            }
            //mage
            if (turns == 3) {
                sv.skill1Button.setText("Fireball (2)");
                sv.skill2Button.setText(" ");
                sv.skill3Button.setText("Meteor (5)");
                sv.skill4Button.setText("Pyroblast (5)");
            }
            //healer
            if (turns == 4){
                sv.skill1Button.setText("Heal (4)");
                sv.skill2Button.setText("Bless (2)");
                sv.skill3Button.setText("Restore (5)");
                sv.skill4Button.setText(" ");
            }
        }

        //When player press block
        private void blockPressed() {

            //If its warrior's turn and player has 2 or more energy.
            if (turns == 1 && warriorEnergyInt > 1) {
                warriorEnergyInt = warriorEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                warriorBlock += 5;
                sv.energy.setText("Energy: " + warriorEnergyInt);
                sv.block.setText("Block: " + warriorBlock);
            }
            //If its ranger's turn and player has 2 or more energy.
            else if (turns == 2 && rangerEnergyInt > 1) {
                rangerEnergyInt = rangerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                rangerBlock += 5;
                sv.energy.setText("Energy: " + rangerEnergyInt);
                sv.block.setText("Block: " + rangerBlock);
            }
            //If its mage's turn and player has 2 or more energy.
            else if (turns == 3 && mageEnergyInt > 1) {
                mageEnergyInt = mageEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                mageBlock += 5;
                sv.energy.setText("Energy: " + mageEnergyInt);
                sv.block.setText("Block: " + mageBlock);
            }
            //If its healer's turn and player has 2 or more energy.
            else if (turns == 4 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                healerBlock += 5;
                sv.energy.setText("Energy: " + healerEnergyInt);
                sv.block.setText("Block: " + healerBlock);
            }
        }

        //When you press the "attack button".
        private void attackPressed() {

            //If its warrior's turn and player has 2 or more energy.
            if (turns == 1 && warriorEnergyInt > 1 && sv.targetarrow.isVisible()) {
                warriorEnergyInt = warriorEnergyInt - 2; //Energy -2.
                currentEnergy = currentEnergy - 2; // Update currentEnergy.
                sv.energy.setText("Energy: " + warriorEnergyInt); //Update energyLabel
                tackle.start(); //Warrior deals damage to a trunk.
            }
            //If its ranger's turn and player has 2 or more energy.
            else if (turns == 2 && rangerEnergyInt > 1 && sv.targetarrow.isVisible()) {
                rangerEnergyInt = rangerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                sv.energy.setText("Energy: " + rangerEnergyInt);
                shoot.start();
            }
            //If its mage's turn and player has 2 or more energy.
            else if (turns == 3 && mageEnergyInt > 1 && sv.targetarrow.isVisible()) {
                mageEnergyInt = mageEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                sv.energy.setText("Energy: " + mageEnergyInt);
                blast.start();
            }
            //If its healer's turn and player has 2 or more energy.
            else if (turns == 4 && healerEnergyInt > 1 && sv.targetarrow.isVisible()) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                sv.energy.setText("Energy: " + healerEnergyInt);
                healerAttack.start();
            }
        }

        //Checks if all of the enemies or OldClasses.party-members are dead.
        private void isFightOver() {
            //If all of the wolves are dead. Open lootScreen.
            if (trunkHp[0] < 1 && trunkHp[1] < 1 && trunkHp[2] < 1 && trunkHp[3] < 1) {
                MusicPick.musicStop();
                sv.swampFightJFrame.dispose();
                fightWon = true;

            }
            //In the whole OldClasses.party is dead, game is over. Send to loseScreen.
            if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
                sv.swampFightJFrame.dispose();
                fightLost = true;
            }
            //If none of these are true, nothing happens and the fight goes on.
        }

        public void setStartLabels() {

            sv.potion1Label = new JLabel("" + ownedPotions[0]);
            sv.potion2Label = new JLabel("" + ownedPotions[1]);
            sv.potion3Label = new JLabel("" + ownedPotions[2]);
            sv.potion4Label = new JLabel("" + ownedPotions[3]);
            sv.potion5Label = new JLabel("" + ownedPotions[4]);
            sv.potion6Label = new JLabel("" + ownedPotions[5]);
            sv.potion7Label = new JLabel("" + ownedPotions[6]);
            sv.potion8Label = new JLabel("" + ownedPotions[7]);
            sv.potion9Label = new JLabel("" + ownedPotions[8]);
            sv.potion10Label = new JLabel("" + ownedPotions[9]);
            sv.potion11Label = new JLabel("" + ownedPotions[10]);
            sv.potion12Label = new JLabel("" + ownedPotions[11]);

            sv.trunk1Hp = new JLabel("trunk 1: " + trunkHp[0]);
            sv.trunk2Hp = new JLabel("trunk 2: " + trunkHp[1]);
            sv.trunk3Hp = new JLabel("trunk 3: " + trunkHp[2]);
            sv.trunk4Hp = new JLabel("trunk 4: " + trunkHp[3]);

            sv.playersHp = new JLabel("Hp: " + warriorCurrentHp);
            sv.player1Hp = new JLabel("Warrior: " + warriorCurrentHp);
            sv.player2Hp = new JLabel("Mage:    " + mageCurrentHp);
            sv.player3Hp = new JLabel("Ranger:  " + rangerCurrentHp);
            sv.player4Hp = new JLabel("Healer:  " + healerCurrentHp);
            sv.block = new JLabel("Block: " + warriorBlock);
        }

        //When the trunk attacks.
        private void trunkAttack() {
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
                        enemyDamage = enemyDamage - warriorBlock; //Warrior take damage equal to trunk damage.
                        warriorCurrentHp = warriorCurrentHp - enemyDamage; //Update warrior hp.
                        sv.player1Hp.setText("Warrior: " + warriorCurrentHp); //Update hp Label.
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
                        sv.player2Hp.setText("Mage:    " + mageCurrentHp);
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
                        sv.player3Hp.setText("Ranger:  " + rangerCurrentHp);
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
                        sv.player4Hp.setText("Healer:   " + healerCurrentHp);
                        break;
                    }
                }
            }
        }

        //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
        private void mobDeath() {

            if (trunkHp[0] <= 0) {
                sv.trunk1Hp.setText("trunk 1: 0");
                sv.trunk1.setVisible(false);
                if (target == 1) {
                    sv.targetarrow.setVisible(false);
                }
            }
            if (trunkHp[1] <= 0) {
                sv.trunk2Hp.setText("trunk 2: 0");
                sv.trunk2.setVisible(false);
                if (target == 2) {
                    sv.targetarrow.setVisible(false);
                }
            }
            if (trunkHp[2] <= 0) {
                sv.trunk3Hp.setText("trunk 3: 0");
                sv.trunk3.setVisible(false);
                if (target == 3) {
                    sv.targetarrow.setVisible(false);
                }
            }
            if (trunkHp[3] <= 0) {
                sv.trunk4Hp.setText("trunk 4: 0");
                sv.trunk4.setVisible(false);
                if (target == 4) {
                    sv.targetarrow.setVisible(false);
                }
            }
        }

        //Checks if any OldClasses.party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
        private void partyDeath() {

            if (warriorCurrentHp <= 0) {
                warriorCurrentHp = 0;
                sv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                sv.warrior.setVisible(false);
            }
            if (mageCurrentHp <= 0) {
                mageCurrentHp = 0;
                sv.player2Hp.setText("Mage:    " + mageCurrentHp);
                sv.mage.setVisible(false);
            }
            if (rangerCurrentHp <= 0) {
                rangerCurrentHp = 0;
                sv.player3Hp.setText("Ranger:  " + rangerCurrentHp);
                sv.ranger.setVisible(false);
            }
            if (healerCurrentHp <= 0) {
                healerCurrentHp = 0;
                sv.player4Hp.setText("Healer:  " + healerCurrentHp);
                sv.healer.setVisible(false);
            }
        }

        private void spellMenuInactive() {
            sv.attackButton.setVisible(true);
            sv.blockButton.setVisible(true);
            sv.itemButton.setVisible(true);
            sv.skillButton.setVisible(true);
            sv.endTurnButton.setVisible(true);

            sv.skill1Button.setVisible(false);
            sv.skill2Button.setVisible(false);
            sv.skill3Button.setVisible(false);
            sv.skill4Button.setVisible(false);
            sv.returnButton.setVisible(false);
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
                        sv.playersHp.setText("Hp: " + warriorCurrentHp); //Update Warrior's hp Label.
                        sv.player1Hp.setText("Warrior: " + warriorCurrentHp); // Update currentPlayer Hp label.
                        ownedPotions[0] -= 1;
                        sv.potion1Label.setText("" + ownedPotions[0]); //Update ownedPotion Label.
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        warriorCurrentHp += 30;
                        sv.playersHp.setText("Hp: " + warriorCurrentHp);
                        sv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                        ownedPotions[1] -= 1;
                        sv.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        warriorCurrentHp += 60;
                        sv.playersHp.setText("Hp: " + warriorCurrentHp);
                        sv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                        ownedPotions[2] -= 1;
                        sv.potion3Label.setText("" + ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        warriorBlock += 5;
                        sv.block.setText("Block: " + warriorBlock);
                        ownedPotions[3] -= 1;
                        sv.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        warriorBlock += 20;
                        sv.block.setText("Block: " + warriorBlock);
                        ownedPotions[4] -= 1;
                        sv.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        warriorBlock += 50;
                        sv.block.setText("Block: " + warriorBlock);
                        ownedPotions[5] -= 1;
                        sv.potion6Label.setText("" + ownedPotions[5]);
                    }
                } else if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        warriorEnergyInt += 3;
                        sv.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[6] -= 1;
                        sv.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        warriorCurrentHp += 5;
                        sv.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[7] -= 1;
                        sv.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        warriorCurrentHp += 10;
                        sv.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[8] -= 1;
                        sv.potion9Label.setText("" + ownedPotions[8]);
                    }
                }
                if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        sv.potion10Label.setText("" + ownedPotions[9]);
                    }
                }
                if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage [turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        sv.potion11Label.setText("" + ownedPotions[10]);
                    }
                }
                if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage [turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        sv.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }

            //Ranger
            else if (turns == 2) {
                if (potion == 1) {
                    if (ownedPotions[0] > 0) {
                        rangerCurrentHp += 10;
                        sv.playersHp.setText("Hp: " + rangerCurrentHp);
                        sv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[0] -= 1;
                        sv.potion1Label.setText("" + ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        rangerCurrentHp += 30;
                        sv.playersHp.setText("Hp: " + rangerCurrentHp);
                        sv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[1] -= 1;
                        sv.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        rangerCurrentHp += 60;
                        sv.playersHp.setText("Hp: " + rangerCurrentHp);
                        sv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[2] -= 1;
                        sv.potion3Label.setText("" + ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        rangerBlock += 5;
                        sv.block.setText("Block: " + rangerBlock);
                        ownedPotions[3] -= 1;
                        sv.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        rangerBlock += 20;
                        sv.block.setText("Block: " + rangerBlock);
                        ownedPotions[4] -= 1;
                        sv.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        rangerBlock += 50;
                        sv.block.setText("Block: " + rangerBlock);
                        ownedPotions[5] -= 1;
                        sv.potion6Label.setText("" + ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        rangerEnergyInt += 3;
                        sv.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[6] -= 1;
                        sv.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        rangerEnergyInt += 5;
                        sv.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[7] -= 1;
                        sv.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        rangerEnergyInt += 10;
                        sv.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[8] -= 1;
                        sv.potion9Label.setText("" + ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        sv.potion10Label.setText("" + ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage[turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        sv.potion11Label.setText("" + ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage[turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        sv.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }
            //Mage
            else if (turns == 3) {
                if (potion == 1) {
                    if (ownedPotions[0] > 0) {
                        mageCurrentHp += 10;
                        sv.playersHp.setText("Hp: " + mageCurrentHp);
                        sv.player3Hp.setText("Mage: " + mageCurrentHp);
                        ownedPotions[0] -= 1;
                        sv.potion1Label.setText("" + ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        mageCurrentHp += 30;
                        sv.playersHp.setText("Hp: " + mageCurrentHp);
                        sv.player3Hp.setText("Mage: " + mageCurrentHp);
                        ownedPotions[1] -= 1;
                        sv.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        rangerCurrentHp += 60;
                        sv.playersHp.setText("Hp: " + mageCurrentHp);
                        sv.player3Hp.setText("Mage: " + mageCurrentHp);
                        sv.potion3Label.setText("" + ownedPotions[2]);
                        ownedPotions[2] -= 1;
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        mageBlock += 5;
                        sv.block.setText("Block: " + mageBlock);
                        ownedPotions[3] -= 1;
                        sv.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        mageBlock += 20;
                        sv.block.setText("Block: " + mageBlock);
                        ownedPotions[4] -= 1;
                        sv.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        mageBlock += 50;
                        sv.block.setText("Block: " + mageBlock);
                        ownedPotions[5] -= 1;
                        sv.potion6Label.setText("" + ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        mageEnergyInt += 3;
                        sv.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[6] -= 1;
                        sv.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        mageEnergyInt += 5;
                        sv.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[7] -= 1;
                        sv.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        mageEnergyInt += 10;
                        sv.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[8] -= 1;
                        sv.potion9Label.setText("" + ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        sv.potion10Label.setText("" + ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage[turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        sv.potion11Label.setText("" + ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage[turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        sv.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }
            //Healer
            else if (turns == 4) {
                if (potion == 1) {
                    if (ownedPotions[0] > 0) {
                        healerCurrentHp += 10;
                        sv.playersHp.setText("Hp: " + healerCurrentHp);
                        sv.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[0] -= 1;
                        sv.potion1Label.setText("" + ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        healerCurrentHp += 30;
                        sv.playersHp.setText("Hp: " + healerCurrentHp);
                        sv.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[1] -= 1;
                        sv.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        healerCurrentHp += 60;
                        sv.playersHp.setText("Hp: " + healerCurrentHp);
                        sv.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[2] -= 1;
                        sv.potion3Label.setText("" + ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        healerBlock += 5;
                        sv.block.setText("Block: " + healerBlock);
                        ownedPotions[3] -= 1;
                        sv.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        healerBlock += 20;
                        sv.block.setText("Block: " + healerBlock);
                        ownedPotions[4] -= 1;
                        sv.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        healerBlock += 50;
                        sv.block.setText("Block: " + healerBlock);
                        ownedPotions[5] -= 1;
                        sv.potion6Label.setText("" + ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        healerEnergyInt += 3;
                        sv.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[6] -= 1;
                        sv.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        healerEnergyInt += 5;
                        sv.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[7] -= 1;
                        sv.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        healerEnergyInt += 10;
                        sv.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[8] -= 1;
                        sv.potion9Label.setText("" + ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        sv.potion10Label.setText("" + ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage[turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        sv.potion11Label.setText("" + ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage[turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        sv.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }
        }

        //Add hover effect to buttons.
        private void hoverEffect() {
            //Attack Hover
            sv.attackButton.addMouseListener(new MouseAdapter() {
                //Change button color while hovering depending on your current energy.
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (currentEnergy >= 2) {
                        sv.attackButton.setBackground(Color.lightGray);
                    }
                    if (currentEnergy < 2) {
                        sv.attackButton.setBackground(Color.pink);
                    }
                }

                //Change back when not hovering over button
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    sv.attackButton.setBackground(Color.white);
                }
            });

            //Block Hover
            sv.blockButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (currentEnergy >= 2) {
                        sv.blockButton.setBackground(Color.lightGray);
                    }
                    if (currentEnergy < 2) {
                        sv.blockButton.setBackground(Color.pink);
                    }
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    sv.blockButton.setBackground(Color.white);
                }
            });

            //Item Hover
            sv.itemButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    sv.itemButton.setBackground(Color.lightGray);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    sv.itemButton.setBackground(Color.white);
                }
            });
            //Skill Hover
            sv.skillButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    sv.skillButton.setBackground(Color.lightGray);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    sv.skillButton.setBackground(Color.white);
                }
            });

            //End turn Hover
            sv.endTurnButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    sv.endTurnButton.setBackground(Color.lightGray);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    sv.endTurnButton.setBackground(Color.white);
                }
            });
        }


        //called from spells to deal damage to enemies
        //damageTargets types: single, line, all
        public void spellDamageSystem(int unbuffedDamage, String damageTargets) {
            damage = unbuffedDamage + buffDamage[turns - 1];
            if (damageTargets.equals("single")) {
                trunkHp[target - 1] -= damage;
            }
            if (damageTargets.equals("line")) {
                if (target == 1 || target == 2) {
                    trunkHp[0] -= damage;
                    trunkHp[1] -= damage;
                }
                if (target == 3 || target == 4) {
                    trunkHp[2] -= damage;
                    trunkHp[3] -= damage;
                }
            }
            if (damageTargets.equals("all")) {
                trunkHp[0] -= damage;
                trunkHp[1] -= damage;
                trunkHp[2] -= damage;
                trunkHp[3] -= damage;
            }
            sv.trunk1Hp.setText("trunk 1: " + trunkHp[0]);
            sv.trunk2Hp.setText("trunk 2: " + trunkHp[1]);
            sv.trunk3Hp.setText("trunk 3: " + trunkHp[2]);
            sv.trunk4Hp.setText("trunk 4: " + trunkHp[3]);
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
            sv.player1Hp.setText("Warrior: " + warriorCurrentHp);
            sv.player2Hp.setText("Ranger:  " + rangerCurrentHp);
            sv.player3Hp.setText("Mage:    " + mageCurrentHp);
            sv.player4Hp.setText("Healer:  " + healerCurrentHp);
        }


        //warrior
        public Timer tackle = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    if (warriorX == 100) MusicPick.musicStart("warriorattack", "");
                    animationPlaying = true;
                    warriorX += 15;
                    sv.warrior.setLocation(warriorX, warriorY);
                    if (warriorX > 200) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    warriorX -= 15;
                    sv.warrior.setLocation(warriorX, warriorY);
                    if (warriorX <= warriorStartX) {
                        warriorX = warriorStartX;
                        sv.warrior.setLocation(warriorX, warriorY);
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
                    sv.warrior.setLocation(warriorX, warriorY);
                    if (warriorX > 2000) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    warriorX = warriorStartX;
                    warriorY = warriorStartY;
                    sv.warrior.setLocation(warriorX, warriorY);
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
                    sv.warrior.setLocation(warriorX, warriorY);
                    if (warriorY > warriorStartY) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    timePast++;
                    if (timePast == 30) {
                        warriorY = warriorStartY;
                        warriorX = warriorStartX;
                        sv.warrior.setLocation(warriorX, warriorY);
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
                    sv.warrior.setLocation(warriorX, warriorY);
                    if (warriorY < warriorStartY + 50) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    if (warriorY < 50) {
                        warriorY = 50;
                        sv.warrior.setLocation(warriorX, warriorY);
                    }
                    timePast++;
                    if (timePast < 50) {
                        if (timePast % 2 == 1) {
                            warriorX += 4;
                            sv.warrior.setLocation(warriorX, warriorY);
                        } else {
                            warriorX -= 4;
                            sv.warrior.setLocation(warriorX, warriorY);
                        }
                    }
                    if (timePast > 50) {
                        timePast = 0;
                        phase = 3;
                    }
                } else if (phase == 3) {
                    warriorY += 3;
                    sv.warrior.setLocation(warriorX, warriorY);
                    if (warriorY > warriorStartY) {
                        warriorX = warriorStartX;
                        warriorY = warriorStartY;
                        sv.warrior.setLocation(warriorX, warriorY);
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
                sv.swordIcon.setLocation(swordIconX, swordIconY);
                if (phase == 0) {
                    if (upMegaMath == 1) MusicPick.musicStart("demoshout", "");
                    sv.swordIcon.setVisible(true);
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
                    sv.swordIcon.setVisible(false);
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
                sv.demoSword1.setLocation(swordIconX + 550, swordIconY + 50);
                sv.demoSword2.setLocation(swordIconX + 650, swordIconY + 100);
                sv.demoSword3.setLocation(swordIconX + 750, swordIconY + 50);
                sv.demoSword4.setLocation(swordIconX + 850, swordIconY + 100);


                if (phase == 0) {
                    if (trunkHp[0] > 0) sv.demoSword1.setVisible(true);
                    if (trunkHp[2] > 0) sv.demoSword2.setVisible(true);
                    if (trunkHp[1] > 0) sv.demoSword3.setVisible(true);
                    if (trunkHp[3] > 0) sv.demoSword4.setVisible(true);
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
                    sv.demoSword1.setVisible(false);
                    sv.demoSword2.setVisible(false);
                    sv.demoSword3.setVisible(false);
                    sv.demoSword4.setVisible(false);
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
                    sv.arrow.setVisible(true);
                    if (arrowX == 121) {
                        animationPlaying = true;
                        MusicPick.musicStart("ding", "");
                    }
                    arrowX += 30;
                    sv.arrow.setLocation(arrowX, arrowY);
                    if (arrowX > 1000) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    sv.arrow.setVisible(false);
                    arrowX = arrowStartX;
                    sv.arrow.setLocation(arrowX, arrowY);
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
                sv.volley1.setLocation(arrowX, arrowY);
                sv.volley2.setLocation(arrowX - 200, arrowY);
                sv.volley3.setLocation(arrowX - 400, arrowY);
                if (phase == 0) {
                    sv.volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 1;
                } else if (phase == 1 && arrowX > arrowStartX + 300) {
                    sv.volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 2;
                } else if (phase == 2 && arrowX > arrowStartX + 600) {
                    sv.volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 3;
                } else if (phase == 3 && arrowX > 1000) {
                    sv.volley1.setVisible(false);
                    phase = 4;
                } else if (phase == 4 && arrowX > 1200) {
                    sv.volley2.setVisible(false);
                    phase = 5;
                } else if (phase == 5 && arrowX > 1400) {
                    sv.volley3.setVisible(false);
                    phase = 6;
                }
                if (phase == 6) {
                    arrowX = 300;
                    sv.volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 7;
                } else if (phase == 7 && arrowX > arrowStartX + 300) {
                    sv.volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 8;
                } else if (phase == 8 && arrowX > arrowStartX + 600) {
                    sv.volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 9;
                } else if (phase == 9 && arrowX > 1000) {
                    sv.volley1.setVisible(false);
                    phase = 10;
                } else if (phase == 10 && arrowX > 1200) {
                    sv.volley2.setVisible(false);
                    phase = 11;
                } else if (phase == 11 && arrowX > 1400) {
                    sv.volley3.setVisible(false);
                    phase = 12;
                    arrowX = 300;
                }
                if (phase == 12) {
                    sv.volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 13;
                } else if (phase == 13 && arrowX > arrowStartX + 300) {
                    sv.volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 14;
                } else if (phase == 14 && arrowX > arrowStartX + 600) {
                    sv.volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 15;
                } else if (phase == 15 && arrowX > 1000) {
                    sv.volley1.setVisible(false);
                    phase = 16;
                } else if (phase == 16 && arrowX > 1200) {
                    sv.volley2.setVisible(false);
                    phase = 17;
                } else if (phase == 17 && arrowX > 1400) {
                    sv.volley3.setVisible(false);
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
                sv.ranger.setVisible(false);
                sv.stealthranger.setVisible(true);
                stealthed = true;
            }
        }

        public void unstealth() {
            if (stealthed) {
                MusicPick.musicStart("unstealth", "");
                sv.ranger.setVisible(true);
                sv.stealthranger.setVisible(false);
                stealthed = false;
            }
        }

        public Timer bombthrow = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    animationPlaying = true;
                    phase = 1;
                    sv.bomb.setVisible(true);
                }
                if (phase == 1) {
                    bombMegaMath -= 2;
                    bombX += 20;
                    bombY -= bombMegaMath;
                    sv.bomb.setLocation(bombX, bombY);
                    if (bombY > bombStartY) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    timePast++;
                    if (timePast == 30) {
                        bombY = bombStartY;
                        bombX = bombStartX;
                        sv.bomb.setLocation(bombX, bombY);
                        sv.bomb.setVisible(false);
                        sv.explode.setVisible(true);
                        MusicPick.musicStart("Explosion", "");
                    }
                    if (timePast == 60) {
                        bombMegaMath = 36;
                        sv.bomb.setVisible(false);
                        sv.explode.setVisible(false);
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
                    sv.blast.setVisible(true);
                    if (blastY == 121) {
                        MusicPick.musicStart("fireball", "");
                    }
                    blastX += 30;
                    sv.blast.setLocation(blastX, blastY);
                    if (blastX > 1000) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    sv.blast.setVisible(false);
                    blastX = blastStartX;
                    sv.blast.setLocation(blastX, blastY);
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
                    sv.smallPyroBlast.setVisible(true);
                } else if (timePast < 200) {
                    sv.smallPyroBlast.setVisible(false);
                    sv.mediumPyroBlast.setVisible(true);
                } else if (timePast < 350) {
                    sv.mediumPyroBlast.setVisible(false);
                    sv.bigPyroBlast.setVisible(true);
                    pyroBlastX = 45;
                    pyroblastY = 200;
                    sv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 400) {
                    pyroBlastX += 3;
                    pyroblastY -= 1;
                    sv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 460) {
                    pyroBlastX += 3;
                    pyroblastY += 1;
                    sv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 530) {
                    pyroBlastX += 3;
                    pyroblastY -= 1;
                    sv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 590) {
                    pyroBlastX += 4;
                    pyroblastY += 1;
                    sv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else {
                    sv.bigPyroBlast.setVisible(false);
                    timePast = 0;
                    pyroBlastX = 90;
                    pyroblastY = 300;
                    sv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
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
                    sv.mage.setLocation(mageX, mageY);
                    if (mageY < 50) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    if (mageY < 50) {
                        mageY = 50;
                        sv.mage.setLocation(mageX, mageY);
                    }
                    timePast++;
                    if (timePast < 100) {
                        if (timePast % 2 == 1) {
                            mageX += 6;
                            sv.mage.setLocation(mageX, mageY);
                            flameStrikeY += 13;
                            sv.flame.setLocation(900, flameStrikeY);
                        } else {
                            mageX -= 6;
                            sv.mage.setLocation(mageX, mageY);
                        }
                    }
                    if (timePast == 102) {
                        sv.mage.setLocation(mageX, mageY);
                        flameStrikeY = -400;
                        sv.flame.setLocation(700, flameStrikeY);
                        spellDamageSystem(5, "all");
                    }
                    if (timePast > 130) {
                        timePast = 0;
                        phase = 3;
                    }
                } else if (phase == 3) {
                    mageY += 3;
                    sv.mage.setLocation(mageX, mageY);
                    if (mageY > mageStartY) {
                        mageX = mageStartX;
                        mageY = mageStartY;
                        sv.mage.setLocation(mageX, mageY);
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
                sv.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
                animationPlaying = true;
                if (followup) {
                    MusicPick.musicStart("fireball", "");
                    followup = false;
                }
                if (phase == 0) {
                    sv.smallPyroBlast.setVisible(true);
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
                    sv.smallPyroBlast.setVisible(false);
                    timePast = 0;
                    upMegaMath = 1;
                    rightMegaMath = 1;
                    downMegaMath = 1;
                    leftMegaMath = 1;
                    phase = 0;
                    fireBall.stop();
                    spellDamageSystem(8, "single");
                    animationPlaying = false;
                    sv.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
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
                    if (healTarget == 1) sv.holyLight.setLocation(warriorStartX - 220, warriorStartY - 500);
                    if (healTarget == 2) sv.holyLight.setLocation(rangerStartX - 220, rangerStartY - 450);
                    if (healTarget == 3) sv.holyLight.setLocation(mageStartX - 220, mageStartY - 450);
                    if (healTarget == 4) sv.holyLight.setLocation(healerStartX - 220, healerStartY - 500);
                    MusicPick.musicStart("holylight", "");
                    sv.holyLight.setVisible(true);
                    phase = 1;
                }
                if (timePast == 100) {
                    spellHealSystem(30, "single");
                    timePast = 0;
                    sv.holyLight.setVisible(false);
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
                    if (healTarget == 1) sv.smallHolyLight.setLocation(warriorStartX - 225, warriorStartY - 500);
                    if (healTarget == 2) sv.smallHolyLight.setLocation(rangerStartX - 225, rangerStartY - 500);
                    if (healTarget == 3) sv.smallHolyLight.setLocation(mageStartX - 225, mageStartY - 500);
                    if (healTarget == 4) sv.smallHolyLight.setLocation(healerStartX - 225, healerStartY - 500);
                    MusicPick.musicStart("holylight", "");
                    sv.smallHolyLight.setVisible(true);
                    phase = 1;
                }
                if (timePast > 100) {
                    spellHealSystem(15, "single");
                    timePast = 0;
                    sv.smallHolyLight.setVisible(false);
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
                    sv.groupHeal1.setLocation(warriorStartX + 75, warriorStartY - 500);
                    sv.groupHeal2.setLocation(rangerStartX + 75, rangerStartY - 500);
                    sv.groupHeal3.setLocation(mageStartX + 75, mageStartY - 500);
                    sv.groupHeal4.setLocation(healerStartX + 75, healerStartY - 500);
                    MusicPick.musicStart("groupheal", "");
                    sv.groupHeal1.setVisible(true);
                    sv.groupHeal2.setVisible(true);
                    sv.groupHeal3.setVisible(true);
                    sv.groupHeal4.setVisible(true);
                    phase = 1;
                }
                if (timePast > 400) {
                    timePast = 0;
                    sv.groupHeal1.setVisible(false);
                    sv.groupHeal2.setVisible(false);
                    sv.groupHeal3.setVisible(false);
                    sv.groupHeal4.setVisible(false);
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
                    sv.healer.setLocation(healerX, healerY);
                    if (healerX > 200) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    healerX -= 15;
                    sv.healer.setLocation(healerX, healerY);
                    if (healerX <= healerStartX) {
                        healerX = healerStartX;
                        sv.healer.setLocation(healerX, healerY);
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
                sv.endTurnButton.setVisible(false);
                sv.targetarrow.setVisible(false);
                if (timePast < 50) {
                    if (trunkHp[0] < 1) timePast += 100;
                } else if (timePast < 60) {
                    trunk1X -= 15;
                    sv.trunk1.setLocation(trunk1X, trunk1Y);
                } else if (timePast < 70) {
                    trunk1X += 15;
                    sv.trunk1.setLocation(trunk1X, trunk1Y);
                } else if (timePast < 150) {
                    sv.trunk1.setLocation(trunk1StartX, trunk1StartY);
                    if (trunkHp[1] < 1) timePast += 100;

                } else if (timePast < 160) {
                    trunk2X -= 15;
                    sv.trunk2.setLocation(trunk2X, trunk2Y);
                } else if (timePast < 170) {
                    trunk2X += 15;
                    sv.trunk2.setLocation(trunk2X, trunk2Y);
                } else if (timePast < 250) {
                    sv.trunk2.setLocation(trunk2StartX, trunk2StartY);
                    if (trunkHp[2] < 1) timePast += 100;

                } else if (timePast < 260) {
                    trunk3X -= 15;
                    sv.trunk3.setLocation(trunk3X, trunk3Y);
                } else if (timePast < 270) {
                    trunk3X += 15;
                    sv.trunk3.setLocation(trunk3X, trunk3Y);
                } else if (timePast < 350) {
                    sv.trunk3.setLocation(trunk3StartX, trunk3StartY);
                    if (trunkHp[3] < 1) timePast += 100;

                } else if (timePast < 360) {
                    trunk4X -= 15;
                    sv.trunk4.setLocation(trunk4X, trunk4Y);
                } else if (timePast < 370) {
                    trunk4X += 15;
                    sv.trunk4.setLocation(trunk4X, trunk4Y);
                } else if (timePast == 380) {
                    sv.trunk4.setLocation(trunk4StartX, trunk4StartY);
                    enemyDamage();
                    timePast = 0;
                    enemyTurnTimer.stop();
                    sv.endTurnButton.setVisible(true);
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
                    if (warriorattacked && warriorCurrentHp > 0) sv.warrior.setVisible(false);
                    if (rangerattacked && rangerCurrentHp > 0) sv.ranger.setVisible(false);
                    if (mageattacked && mageCurrentHp > 0) sv.mage.setVisible(false);
                    if (healerattacked && healerCurrentHp > 0) sv.healer.setVisible(false);
                } else if (timePastTakeDamage == 20) {
                    if (warriorattacked && warriorCurrentHp > 0) sv.warrior.setVisible(true);
                    if (rangerattacked && rangerCurrentHp > 0) sv.ranger.setVisible(true);
                    if (mageattacked && mageCurrentHp > 0) sv.mage.setVisible(true);
                    if (healerattacked && healerCurrentHp > 0) sv.healer.setVisible(true);
                } else if (timePastTakeDamage == 30) {
                    if (warriorattacked && warriorCurrentHp > 0) sv.warrior.setVisible(false);
                    if (rangerattacked && rangerCurrentHp > 0) sv.ranger.setVisible(false);
                    if (mageattacked  && mageCurrentHp > 0) sv.mage.setVisible(false);
                    if (healerattacked  && healerCurrentHp > 0) sv.healer.setVisible(false);
                } else if (timePastTakeDamage == 40) {
                    if (warriorattacked  && warriorCurrentHp > 0) sv.warrior.setVisible(true);
                    if (rangerattacked  && rangerCurrentHp > 0) sv.ranger.setVisible(true);
                    if (mageattacked  && mageCurrentHp > 0) sv.mage.setVisible(true);
                    if (healerattacked  && healerCurrentHp > 0) sv.healer.setVisible(true);
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
