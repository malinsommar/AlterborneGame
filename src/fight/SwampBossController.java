package fight;

import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

/**
 * @author Simon Bengtsson, Malin Sommar
 */

public class SwampBossController {

        SwampBossView SBV = new SwampBossView();

        //Get hp, block and damage from OldClasses.party
        private int warriorCurrentHp, mageCurrentHp, healerCurrentHp, rangerCurrentHp;
        private int warriorMaxHp, mageMaxHp, healerMaxHp, rangerMaxHp;
        private int warriorDamage, mageDamage, healerDamage, rangerDamage, damage;
        private int warriorBlock, mageBlock, healerBlock, rangerBlock;
        private int buffDamage[] = new int[4];
        private boolean debuffed = false;

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

        public int swampBossX = 800, swampBossY = 000, swampBossStartX = swampBossX, swampBossStartY = swampBossY;
        private int swampBossFlipX = -500, swampBossFlipStart = swampBossFlipX;
        private int attackPick;
        private int bossDamage;
        private int bossTarget;

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
        private int bossMegaMath = 50;

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

        private int swampBossHp = 200;

        /**
         *
         */
        public void startFight() {

            MusicPick.musicStart("allstar", "music");

            currentEnergy = 5;

            setStartLabels();
            SBV.swampBossFightFrame();
            hoverEffect();

            SBV.swampBoss.setLocation(swampBossX, swampBossY);
            SBV.swampBossflip.setLocation(swampBossFlipStart, swampBossY);
            bossDamage = 14;

            //ActionListeners
            SBV.attackButton.addActionListener(e -> {
                if (!animationPlaying) attackPressed();
            });
            SBV.blockButton.addActionListener(e -> blockPressed());
            SBV.itemButton.addActionListener(e -> {
                SBV.inventory.setVisible(true);
                itemMenuActivate();
            });
            SBV.skillButton.addActionListener(e -> {
                if (!animationPlaying) spellMenuActive();
            }); //for now
            SBV.endTurnButton.addActionListener(e -> {
                if (!animationPlaying) startNewTurn();
            });
            SBV.skill1Button.addActionListener(e -> {
                if (!animationPlaying) skill1();
            });
            SBV.skill2Button.addActionListener(e -> {
                if (!animationPlaying) skill2();
            });
            SBV.skill3Button.addActionListener(e -> {
                if (!animationPlaying) skill3();
            });
            SBV.skill4Button.addActionListener(e -> {
                if (!animationPlaying) skill4();
            });
            SBV.returnButton.addActionListener(e -> spellMenuInactive());
            SBV.returnButton.addActionListener(e -> SBV.inventory.setVisible(false));

            //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
            SBV.potion1.addActionListener(e -> usePotion(1));
            SBV.potion2.addActionListener(e -> usePotion(2));
            SBV.potion3.addActionListener(e -> usePotion(3));
            SBV.potion4.addActionListener(e -> usePotion(4));
            SBV.potion5.addActionListener(e -> usePotion(5));
            SBV.potion6.addActionListener(e -> usePotion(6));
            SBV.potion7.addActionListener(e -> usePotion(7));
            SBV.potion8.addActionListener(e -> usePotion(8));
            SBV.potion9.addActionListener(e -> usePotion(9));
            SBV.potion10.addActionListener(e -> usePotion(10));
            SBV.potion11.addActionListener(e -> usePotion(11));
            SBV.potion12.addActionListener(e -> usePotion(12));

            //Dispose the item frame.
            //SBV.returnButton.addActionListener(e -> SBV.inventory.dispose());

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
                SBV.whosTurn.setText("Warrior's turn");
                SBV.playersHp.setText("Hp: " + warriorCurrentHp);
                SBV.energy.setText("Energy: " + warriorEnergyInt);
                SBV.block.setText("Block: " + warriorBlock);
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
                SBV.whosTurn.setText("Ranger's turn");
                SBV.playersHp.setText("Hp: " + rangerCurrentHp);
                SBV.energy.setText("Energy: " + rangerEnergyInt);
                SBV.block.setText("Block: " + rangerBlock);
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
                SBV.whosTurn.setText("Mage's turn");
                SBV.playersHp.setText("Hp: " + mageCurrentHp);
                SBV.energy.setText("Energy: " + mageEnergyInt);
                SBV.block.setText("Block: " + mageBlock);
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
                SBV.whosTurn.setText("Healer's turn");
                SBV.playersHp.setText("Hp: " + healerCurrentHp);
                SBV.energy.setText("Energy: " + healerEnergyInt);
                SBV.block.setText("Block: " + healerBlock);

            }
            //If healer is dead, skip.
            if (turns == 4 && healerCurrentHp < 1) {
                turns = 5;
            }
            //  ***ENEMIES TURN***
            if (turns == 5) {
                SBV.whosTurn.setText(" ");
                SBV.playersHp.setText(" ");
                SBV.energy.setText(" ");
                SBV.block.setText(" ");


                attackPick = (int) (Math.random() * 4);
                if (attackPick == 0) bossAttack1.start();
                if (attackPick == 1) bossAttack2.start();
                if (attackPick == 2) bossAttack3.start();
                if (attackPick == 3) bossAttack4.start();


                //removes temporary damage buffs at the end of turn
                for (int i = 0; i < buffDamage.length; i++) {
                    buffDamage[i] = 0;
                }

                turns = 0;
                if (debuffed) {
                    debuffed = false;
                    bossDamage += 10;
                }
            }
        }

        /**
         *
         */
        private void skill1() {
            if (turns == 1 && warriorEnergyInt > 2) {
                warriorEnergyInt = warriorEnergyInt - 3;
                currentEnergy = currentEnergy - 3;
                SBV.energy.setText("Energy: " + warriorEnergyInt);
                charge.start();
            }
            if (turns == 2 && rangerEnergyInt > 3) {
                rangerEnergyInt = rangerEnergyInt - 4;
                currentEnergy = currentEnergy - 4;
                SBV.energy.setText("Energy: " + rangerEnergyInt);
                volley.start();
            }
            if (turns == 3 && mageEnergyInt > 1) {
                followup = true;
                mageEnergyInt = mageEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                SBV.energy.setText("Energy: " + mageEnergyInt);
                fireBall.start();
            }
            if (turns == 4 && healerEnergyInt > 1) {
                healingTargetMenu(1);
            }
        }

        /**
         *
         */
        private void skill2() {
            if (turns == 1 && warriorEnergyInt > 3) {
                warriorEnergyInt = warriorEnergyInt - 4;
                currentEnergy = currentEnergy - 4;
                SBV.energy.setText("Energy: " + warriorEnergyInt);
                dunk.start();
            }
            if (turns == 2 && rangerEnergyInt > 2) {
                rangerEnergyInt = rangerEnergyInt - 3;
                currentEnergy = currentEnergy - 3;
                SBV.energy.setText("Energy: " + rangerEnergyInt);
                bombthrow.start();
            }
            if (turns == 3) {

            }
            if (turns == 4 && healerEnergyInt > 1) {
                healingTargetMenu(2);
            }
        }

        /**
         *
         */
        private void skill3() {
            if (turns == 1 && warriorEnergyInt > 2) {
                warriorEnergyInt = warriorEnergyInt - 3;
                currentEnergy = currentEnergy - 3;
                SBV.energy.setText("Energy: " + warriorEnergyInt);
                followup = true;
                shout.start();
            }
            if (turns == 2) {

            }
            if (turns == 3 && mageEnergyInt > 4) {
                mageEnergyInt = mageEnergyInt - 5;
                currentEnergy = currentEnergy - 5;
                SBV.energy.setText("Energy: " + mageEnergyInt);
                flameStrike.start();
            }
            if (turns == 4 && healerEnergyInt > 4) {
                healerEnergyInt = healerEnergyInt - 5;
                currentEnergy = currentEnergy - 5;
                SBV.energy.setText("Energy: " + healerEnergyInt);
                groupHealSpell.start();
            }
        }

        /**
         *
         */
        private void skill4() {
            if (turns == 1 && warriorEnergyInt > 4) {
                warriorEnergyInt = warriorEnergyInt - 5;
                currentEnergy = currentEnergy - 5;
                SBV.energy.setText("Energy: " + warriorEnergyInt);
                shout.start();
            }
            if (turns == 2 && rangerEnergyInt > 2) {
                rangerEnergyInt = rangerEnergyInt - 3;
                currentEnergy = currentEnergy - 3;
                SBV.energy.setText("Energy: " + rangerEnergyInt);
                stealth();
            }
            if (turns == 3 && mageEnergyInt > 4) {
                mageEnergyInt = mageEnergyInt - 5;
                currentEnergy = currentEnergy - 5;
                SBV.energy.setText("Energy: " + mageEnergyInt);
                pyroBlast.start();
            }
            if (turns == 4) {

            }
        }

        /**
         *
         * @param chosenSpell
         */
        private void healingTargetMenu(int chosenSpell) {
            SBV.skill1Button.setVisible(false);
            SBV.skill2Button.setVisible(false);
            SBV.skill3Button.setVisible(false);
            SBV.skill4Button.setVisible(false);

            SBV.healWarriorButton.setVisible(true);
            SBV.healRangerButton.setVisible(true);
            SBV.healMageButton.setVisible(true);
            SBV.healHealerButton.setVisible(true);

            SBV.healWarriorButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 1;
                    holyLightSpell.start();
                }

                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 1;
                    smallHolyLightSpell.start();
                }
            });
            SBV.healRangerButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 2;
                    holyLightSpell.start();
                }
                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 2;
                    smallHolyLightSpell.start();
                }
            });
            SBV.healMageButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 3;
                    holyLightSpell.start();
                }
                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 3;
                    smallHolyLightSpell.start();
                }
            });
            SBV.healHealerButton.addActionListener(e -> {
                if (chosenSpell == 1 && healerEnergyInt > 3) {
                    healerEnergyInt = healerEnergyInt - 4;
                    currentEnergy = currentEnergy - 4;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 4;
                    holyLightSpell.start();
                }
                if (chosenSpell == 2 && healerEnergyInt > 1) {
                    healerEnergyInt = healerEnergyInt - 2;
                    currentEnergy = currentEnergy - 2;
                    SBV.energy.setText("Energy: " + healerEnergyInt);
                    healTarget = 4;
                    smallHolyLightSpell.start();
                }
            });
        }

        /**
         *
         */
        private void itemMenuActivate() {
            SBV.endTurnButton.setVisible(false);
            SBV.returnButton.setVisible(true);
        }

        /**
         *
         */
        private void spellMenuActive() {
            SBV.attackButton.setVisible(false);
            SBV.blockButton.setVisible(false);
            SBV.itemButton.setVisible(false);
            SBV.skillButton.setVisible(false);
            SBV.endTurnButton.setVisible(false);

            SBV.skill1Button.setVisible(true);
            SBV.skill2Button.setVisible(true);
            SBV.skill3Button.setVisible(true);
            SBV.skill4Button.setVisible(true);
            SBV.returnButton.setVisible(true);

            //warrior
            if (turns == 1) {
                SBV.skill1Button.setText("Charge (3)");
                SBV.skill2Button.setText("Slam (4)");
                SBV.skill3Button.setText("Battlecry (3)");
                SBV.skill4Button.setText("Demoralize (5)");
            }
            //ranger
            if (turns == 2) {
                SBV.skill1Button.setText("Volley (4)");
                SBV.skill2Button.setText("Bomb (3)");
                SBV.skill3Button.setText(" ");
                SBV.skill4Button.setText("Stealth (3)");
            }
            //mage
            if (turns == 3) {
                SBV.skill1Button.setText("Fireball (2)");
                SBV.skill2Button.setText(" ");
                SBV.skill3Button.setText("Meteor (5)");
                SBV.skill4Button.setText("Pyroblast (5)");
            }
            //healer
            if (turns == 4) {
                SBV.skill1Button.setText("Heal (4)");
                SBV.skill2Button.setText("Bless (2)");
                SBV.skill3Button.setText("Restore (5)");
                SBV.skill4Button.setText(" ");
            }
        }

        /**
         *
         */
        //When player press block
        private void blockPressed() {

            //If its warrior's turn and player has 2 or more energy.
            if (turns == 1 && warriorEnergyInt > 1) {
                warriorEnergyInt = warriorEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                warriorBlock += 5;
                SBV.energy.setText("Energy: " + warriorEnergyInt);
                SBV.block.setText("Block: " + warriorBlock);
            }
            //If its ranger's turn and player has 2 or more energy.
            else if (turns == 2 && rangerEnergyInt > 1) {
                rangerEnergyInt = rangerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                rangerBlock += 5;
                SBV.energy.setText("Energy: " + rangerEnergyInt);
                SBV.block.setText("Block: " + rangerBlock);
            }
            //If its mage's turn and player has 2 or more energy.
            else if (turns == 3 && mageEnergyInt > 1) {
                mageEnergyInt = mageEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                mageBlock += 5;
                SBV.energy.setText("Energy: " + mageEnergyInt);
                SBV.block.setText("Block: " + mageBlock);
            }
            //If its healer's turn and player has 2 or more energy.
            else if (turns == 4 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                healerBlock += 5;
                SBV.energy.setText("Energy: " + healerEnergyInt);
                SBV.block.setText("Block: " + healerBlock);
            }
        }

        /**
         *
         */
        //When you press the "attack button".
        private void attackPressed() {

            //If its warrior's turn and player has 2 or more energy.
            if (turns == 1 && warriorEnergyInt > 1) {
                warriorEnergyInt = warriorEnergyInt - 2; //Energy -2.
                currentEnergy = currentEnergy - 2; // Update currentEnergy.
                SBV.energy.setText("Energy: " + warriorEnergyInt); //Update energyLabel
                tackle.start(); //Warrior deals damage to a swampBoss.
            }
            //If its ranger's turn and player has 2 or more energy.
            else if (turns == 2 && rangerEnergyInt > 1) {
                rangerEnergyInt = rangerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                SBV.energy.setText("Energy: " + rangerEnergyInt);
                shoot.start();
            }
            //If its mage's turn and player has 2 or more energy.
            else if (turns == 3 && mageEnergyInt > 1) {
                mageEnergyInt = mageEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                SBV.energy.setText("Energy: " + mageEnergyInt);
                blast.start();
            }
            //If its healer's turn and player has 2 or more energy.
            else if (turns == 4 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                SBV.energy.setText("Energy: " + healerEnergyInt);
                healerAttack.start();
            }
        }

        /**
         *
         */
        //Checks if all of the enemies or OldClasses.party-members are dead.
        private void isFightOver() {
            //If all of the wolves are dead. Open lootScreen.
            if (swampBossHp < 1) {
                MusicPick.musicStop();
                SBV.swampBossFightJFrame.dispose();
                fightWon = true;

            }
            //In the whole OldClasses.party is dead, game is over. Send to loseScreen.
            if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
                SBV.swampBossFightJFrame.dispose();
                fightLost = true;
            }
            //If none of these are true, nothing happens and the fight goes on.
        }

        /**
         *
         */
        public void setStartLabels() {

            SBV.potion1Label = new JLabel("" + ownedPotions[0]);
            SBV.potion2Label = new JLabel("" + ownedPotions[1]);
            SBV.potion3Label = new JLabel("" + ownedPotions[2]);
            SBV.potion4Label = new JLabel("" + ownedPotions[3]);
            SBV.potion5Label = new JLabel("" + ownedPotions[4]);
            SBV.potion6Label = new JLabel("" + ownedPotions[5]);
            SBV.potion7Label = new JLabel("" + ownedPotions[6]);
            SBV.potion8Label = new JLabel("" + ownedPotions[7]);
            SBV.potion9Label = new JLabel("" + ownedPotions[8]);
            SBV.potion10Label = new JLabel("" + ownedPotions[9]);
            SBV.potion11Label = new JLabel("" + ownedPotions[10]);
            SBV.potion12Label = new JLabel("" + ownedPotions[11]);

            SBV.swampBossHp = new JLabel("Shrek: " + swampBossHp);

            SBV.playersHp = new JLabel("Hp: " + warriorCurrentHp);
            SBV.player1Hp = new JLabel("Warrior: " + warriorCurrentHp);
            SBV.player2Hp = new JLabel("Mage:    " + mageCurrentHp);
            SBV.player3Hp = new JLabel("Ranger:  " + rangerCurrentHp);
            SBV.player4Hp = new JLabel("Healer:  " + healerCurrentHp);
            SBV.block = new JLabel("Block: " + warriorBlock);
        }

        /**
         *
         * @param attack
         */
        //When the swampBoss attacks.
        private void swampBossAttack(String attack) {
            //charge
            if (attack.equals("attack1")) {
                int row = (int) (Math.random() * 2) + 1;
                if (row == 1) {
                    warriorCurrentHp -= bossDamage * 2;
                    healerCurrentHp -= bossDamage * 2;
                    warriorattacked = true;
                    healerattacked = true;
                }
                if (row == 2) {
                    rangerCurrentHp -= bossDamage * 2;
                    mageCurrentHp -= bossDamage * 2;
                    rangerattacked = true;
                    mageattacked = true;
                }
            }
            //dunk
            if (attack.equals("attack2")) {
                warriorCurrentHp -= bossDamage;
                rangerCurrentHp -= bossDamage;
                mageCurrentHp -= bossDamage;
                healerCurrentHp -= bossDamage;
                warriorattacked = true;
                rangerattacked = true;
                mageattacked = true;
                healerattacked = true;
            }
            //single target
            if (attack.equals("attack3")) {
                while (true) {
                    bossTarget = (int) (Math.random() * 4) + 1;
                    if (bossTarget == 1 && warriorCurrentHp > 0) break;
                    if (bossTarget == 2 && rangerCurrentHp > 0 && (!stealthed || (mageCurrentHp < 1 && healerCurrentHp < 1 && warriorCurrentHp < 1)))
                        break;
                    if (bossTarget == 3 && mageCurrentHp > 0) break;
                    if (bossTarget == 4 && healerCurrentHp > 0) break;
                }
                if (bossTarget == 1) {
                    warriorCurrentHp -= bossDamage*4;
                    warriorattacked = true;
                } else if (bossTarget == 2) {
                    rangerCurrentHp -= bossDamage*4;
                    rangerattacked = true;
                } else if (bossTarget == 3) {
                    mageCurrentHp -= bossDamage*4;
                    mageattacked = true;
                } else if (bossTarget == 4) { //always true when reached
                    healerCurrentHp -= bossDamage*4;
                    healerattacked = true;
                }
            }
            SBV.player1Hp.setText("Warrior: " + warriorCurrentHp);
            SBV.player2Hp.setText("Mage:    " + mageCurrentHp);
            SBV.player3Hp.setText("Ranger:  " + rangerCurrentHp);
            SBV.player4Hp.setText("Healer:  " + healerCurrentHp);
            partyDeath();
            isFightOver();

        }

        /**
         *
         */
        //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
        private void mobDeath() {

            if (swampBossHp <= 0) {
                SBV.swampBossHp.setText("Shrek: ");
                SBV.swampBoss.setVisible(false);
            }
        }

        /**
         *
         */
        //Checks if any OldClasses.party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
        private void partyDeath() {

            if (warriorCurrentHp <= 0) {
                warriorCurrentHp = 0;
                SBV.player1Hp.setText("Warrior: " + warriorCurrentHp);
                SBV.warrior.setVisible(false);
            }
            if (mageCurrentHp <= 0) {
                mageCurrentHp = 0;
                SBV.player2Hp.setText("Mage:    " + mageCurrentHp);
                SBV.mage.setVisible(false);
            }
            if (rangerCurrentHp <= 0) {
                rangerCurrentHp = 0;
                SBV.player3Hp.setText("Ranger:  " + rangerCurrentHp);
                SBV.ranger.setVisible(false);
            }
            if (healerCurrentHp <= 0) {
                healerCurrentHp = 0;
                SBV.player4Hp.setText("Healer:  " + healerCurrentHp);
                SBV.healer.setVisible(false);
            }
        }

        /**
         *
         */
        private void spellMenuInactive() {
            SBV.attackButton.setVisible(true);
            SBV.blockButton.setVisible(true);
            SBV.itemButton.setVisible(true);
            SBV.skillButton.setVisible(true);
            SBV.endTurnButton.setVisible(true);

            SBV.skill1Button.setVisible(false);
            SBV.skill2Button.setVisible(false);
            SBV.skill3Button.setVisible(false);
            SBV.skill4Button.setVisible(false);
            SBV.returnButton.setVisible(false);
        }

        /**
         *
         * @param potions
         */
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

        /**
         *
         * @param warrior
         * @param mage
         * @param healer
         * @param ranger
         */
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

        /**
         *
         * @param potion
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
                        SBV.playersHp.setText("Hp: " + warriorCurrentHp); //Update Warrior's hp Label.
                        SBV.player1Hp.setText("Warrior: " + warriorCurrentHp); // Update currentPlayer Hp label.
                        ownedPotions[0] -= 1;
                        SBV.potion1Label.setText("" + ownedPotions[0]); //Update ownedPotion Label.
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        warriorCurrentHp += 30;
                        SBV.playersHp.setText("Hp: " + warriorCurrentHp);
                        SBV.player1Hp.setText("Warrior: " + warriorCurrentHp);
                        ownedPotions[1] -= 1;
                        SBV.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        warriorCurrentHp += 60;
                        SBV.playersHp.setText("Hp: " + warriorCurrentHp);
                        SBV.player1Hp.setText("Warrior: " + warriorCurrentHp);
                        ownedPotions[2] -= 1;
                        SBV.potion3Label.setText("" + ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        warriorBlock += 5;
                        SBV.block.setText("Block: " + warriorBlock);
                        ownedPotions[3] -= 1;
                        SBV.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        warriorBlock += 20;
                        SBV.block.setText("Block: " + warriorBlock);
                        ownedPotions[4] -= 1;
                        SBV.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        warriorBlock += 50;
                        SBV.block.setText("Block: " + warriorBlock);
                        ownedPotions[5] -= 1;
                        SBV.potion6Label.setText("" + ownedPotions[5]);
                    }
                } else if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        warriorEnergyInt += 3;
                        SBV.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[6] -= 1;
                        SBV.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        warriorCurrentHp += 5;
                        SBV.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[7] -= 1;
                        SBV.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        warriorCurrentHp += 10;
                        SBV.energy.setText("Energy: " + warriorEnergyInt);
                        ownedPotions[8] -= 1;
                        SBV.potion9Label.setText("" + ownedPotions[8]);
                    }
                }
                if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        SBV.potion10Label.setText("" + ownedPotions[9]);
                    }
                }
                if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage [turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        SBV.potion11Label.setText("" + ownedPotions[10]);
                    }
                }
                if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage [turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        SBV.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }

            //Ranger
            else if (turns == 2) {
                if (potion == 1) {
                    if (ownedPotions[0] > 0) {
                        rangerCurrentHp += 10;
                        SBV.playersHp.setText("Hp: " + rangerCurrentHp);
                        SBV.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[0] -= 1;
                        SBV.potion1Label.setText("" + ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        rangerCurrentHp += 30;
                        SBV.playersHp.setText("Hp: " + rangerCurrentHp);
                        SBV.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[1] -= 1;
                        SBV.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        rangerCurrentHp += 60;
                        SBV.playersHp.setText("Hp: " + rangerCurrentHp);
                        SBV.player2Hp.setText("Ranger: " + rangerCurrentHp);
                        ownedPotions[2] -= 1;
                        SBV.potion3Label.setText("" + ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        rangerBlock += 5;
                        SBV.block.setText("Block: " + rangerBlock);
                        ownedPotions[3] -= 1;
                        SBV.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        rangerBlock += 20;
                        SBV.block.setText("Block: " + rangerBlock);
                        ownedPotions[4] -= 1;
                        SBV.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        rangerBlock += 50;
                        SBV.block.setText("Block: " + rangerBlock);
                        ownedPotions[5] -= 1;
                        SBV.potion6Label.setText("" + ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        rangerEnergyInt += 3;
                        SBV.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[6] -= 1;
                        SBV.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        rangerEnergyInt += 5;
                        SBV.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[7] -= 1;
                        SBV.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        rangerEnergyInt += 10;
                        SBV.energy.setText("Energy: " + rangerEnergyInt);
                        ownedPotions[8] -= 1;
                        SBV.potion9Label.setText("" + ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        SBV.potion10Label.setText("" + ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage[turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        SBV.potion11Label.setText("" + ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage[turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        SBV.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }
            //Mage
            else if (turns == 3) {
                if (potion == 1) {
                    if (ownedPotions[0] > 0) {
                        mageCurrentHp += 10;
                        SBV.playersHp.setText("Hp: " + mageCurrentHp);
                        SBV.player3Hp.setText("Mage: " + mageCurrentHp);
                        ownedPotions[0] -= 1;
                        SBV.potion1Label.setText("" + ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        mageCurrentHp += 30;
                        SBV.playersHp.setText("Hp: " + mageCurrentHp);
                        SBV.player3Hp.setText("Mage: " + mageCurrentHp);
                        ownedPotions[1] -= 1;
                        SBV.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        rangerCurrentHp += 60;
                        SBV.playersHp.setText("Hp: " + mageCurrentHp);
                        SBV.player3Hp.setText("Mage: " + mageCurrentHp);
                        SBV.potion3Label.setText("" + ownedPotions[2]);
                        ownedPotions[2] -= 1;
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        mageBlock += 5;
                        SBV.block.setText("Block: " + mageBlock);
                        ownedPotions[3] -= 1;
                        SBV.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        mageBlock += 20;
                        SBV.block.setText("Block: " + mageBlock);
                        ownedPotions[4] -= 1;
                        SBV.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        mageBlock += 50;
                        SBV.block.setText("Block: " + mageBlock);
                        ownedPotions[5] -= 1;
                        SBV.potion6Label.setText("" + ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        mageEnergyInt += 3;
                        SBV.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[6] -= 1;
                        SBV.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        mageEnergyInt += 5;
                        SBV.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[7] -= 1;
                        SBV.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        mageEnergyInt += 10;
                        SBV.energy.setText("Energy: " + mageEnergyInt);
                        ownedPotions[8] -= 1;
                        SBV.potion9Label.setText("" + ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        SBV.potion10Label.setText("" + ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage[turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        SBV.potion11Label.setText("" + ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage[turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        SBV.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }
            //Healer
            else if (turns == 4) {
                if (potion == 1) {
                    if (ownedPotions[0] > 0) {
                        healerCurrentHp += 10;
                        SBV.playersHp.setText("Hp: " + healerCurrentHp);
                        SBV.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[0] -= 1;
                        SBV.potion1Label.setText("" + ownedPotions[0]);
                    }
                } else if (potion == 2) {
                    if (ownedPotions[1] > 0) {
                        healerCurrentHp += 30;
                        SBV.playersHp.setText("Hp: " + healerCurrentHp);
                        SBV.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[1] -= 1;
                        SBV.potion2Label.setText("" + ownedPotions[1]);
                    }
                } else if (potion == 3) {
                    if (ownedPotions[2] > 0) {
                        healerCurrentHp += 60;
                        SBV.playersHp.setText("Hp: " + healerCurrentHp);
                        SBV.player4Hp.setText("Mage: " + healerCurrentHp);
                        ownedPotions[2] -= 1;
                        SBV.potion3Label.setText("" + ownedPotions[2]);
                    }
                } else if (potion == 4) {
                    if (ownedPotions[3] > 0) {
                        healerBlock += 5;
                        SBV.block.setText("Block: " + healerBlock);
                        ownedPotions[3] -= 1;
                        SBV.potion4Label.setText("" + ownedPotions[3]);
                    }
                } else if (potion == 5) {
                    if (ownedPotions[4] > 0) {
                        healerBlock += 20;
                        SBV.block.setText("Block: " + healerBlock);
                        ownedPotions[4] -= 1;
                        SBV.potion5Label.setText("" + ownedPotions[4]);
                    }
                } else if (potion == 6) {
                    if (ownedPotions[5] > 0) {
                        healerBlock += 50;
                        SBV.block.setText("Block: " + healerBlock);
                        ownedPotions[5] -= 1;
                        SBV.potion6Label.setText("" + ownedPotions[5]);
                    }
                }
                if (potion == 7) {
                    if (ownedPotions[6] > 0) {
                        healerEnergyInt += 3;
                        SBV.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[6] -= 1;
                        SBV.potion7Label.setText("" + ownedPotions[6]);
                    }
                } else if (potion == 8) {
                    if (ownedPotions[7] > 0) {
                        healerEnergyInt += 5;
                        SBV.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[7] -= 1;
                        SBV.potion8Label.setText("" + ownedPotions[7]);
                    }
                } else if (potion == 9) {
                    if (ownedPotions[8] > 0) {
                        healerEnergyInt += 10;
                        SBV.energy.setText("Energy: " + healerEnergyInt);
                        ownedPotions[8] -= 1;
                        SBV.potion9Label.setText("" + ownedPotions[8]);
                    }
                } else if (potion == 10) {
                    if (ownedPotions[9] > 0) {
                        buffDamage[turns - 1] += 5;
                        ownedPotions[9] -= 1;
                        SBV.potion10Label.setText("" + ownedPotions[9]);
                    }
                } else if (potion == 11) {
                    if (ownedPotions[10] > 0) {
                        buffDamage[turns - 1] += 10;
                        ownedPotions[10] -= 1;
                        SBV.potion11Label.setText("" + ownedPotions[10]);
                    }
                } else if (potion == 12) {
                    if (ownedPotions[11] > 0) {
                        buffDamage[turns - 1] += 20;
                        ownedPotions[11] -= 1;
                        SBV.potion12Label.setText("" + ownedPotions[11]);
                    }
                }
            }
        }

        /**
         *
         */
        //Add hover effect to buttons.
        private void hoverEffect() {
            //Attack Hover
            SBV.attackButton.addMouseListener(new MouseAdapter() {
                //Change button color while hovering depending on your current energy.
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (currentEnergy >= 2) {
                        SBV.attackButton.setBackground(Color.lightGray);
                    }
                    if (currentEnergy < 2) {
                        SBV.attackButton.setBackground(Color.pink);
                    }
                }

                //Change back when not hovering over button
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    SBV.attackButton.setBackground(Color.white);
                }
            });

            //Block Hover
            SBV.blockButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    if (currentEnergy >= 2) {
                        SBV.blockButton.setBackground(Color.lightGray);
                    }
                    if (currentEnergy < 2) {
                        SBV.blockButton.setBackground(Color.pink);
                    }
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    SBV.blockButton.setBackground(Color.white);
                }
            });

            //Item Hover
            SBV.itemButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    SBV.itemButton.setBackground(Color.lightGray);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    SBV.itemButton.setBackground(Color.white);
                }
            });
            //Skill Hover
            SBV.skillButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    SBV.skillButton.setBackground(Color.lightGray);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    SBV.skillButton.setBackground(Color.white);
                }
            });

            //End turn Hover
            SBV.endTurnButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    SBV.endTurnButton.setBackground(Color.lightGray);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    SBV.endTurnButton.setBackground(Color.white);
                }
            });
        }


        //called from spells to deal damage to enemies
        //damageTargets types: single, line, all
        public void spellDamageSystem(int unbuffedDamage, String damageTargets) {
            damage = unbuffedDamage + buffDamage[turns - 1];
            swampBossHp -= damage;

            SBV.swampBossHp.setText("Shrek: " + swampBossHp);
            mobDeath();
            isFightOver();
        }

        /**
         *
         * @param healing
         * @param healingTargets
         */
        public void spellHealSystem(int healing, String healingTargets) {
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
            SBV.player1Hp.setText("Warrior: " + warriorCurrentHp);
            SBV.player2Hp.setText("Ranger:  " + rangerCurrentHp);
            SBV.player3Hp.setText("Mage:    " + mageCurrentHp);
            SBV.player4Hp.setText("Healer:  " + healerCurrentHp);
        }

        /**
         *
         */
        //warrior
        public Timer tackle = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    if (warriorX == 100) MusicPick.musicStart("warriorattack", "");
                    animationPlaying = true;
                    warriorX += 15;
                    SBV.warrior.setLocation(warriorX, warriorY);
                    if (warriorX > 200) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    warriorX -= 15;
                    SBV.warrior.setLocation(warriorX, warriorY);
                    if (warriorX <= warriorStartX) {
                        warriorX = warriorStartX;
                        SBV.warrior.setLocation(warriorX, warriorY);
                        phase = 0;
                        tackle.stop();
                        spellDamageSystem(warriorDamage, "single");
                        animationPlaying = false;
                    }
                }
            }
        });
        /**
         *
         */
        public Timer charge = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    animationPlaying = true;
                    MusicPick.musicStart("charge", "");
                    phase = 1;
                } else if (phase == 1) {
                    warriorX += 20;
                    SBV.warrior.setLocation(warriorX, warriorY);
                    if (warriorX > 2000) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    warriorX = warriorStartX;
                    warriorY = warriorStartY;
                    SBV.warrior.setLocation(warriorX, warriorY);
                    phase = 0;
                    charge.stop();
                    spellDamageSystem(6, "line");
                    animationPlaying = false;
                }
            }
        });
        /**
         *
         */
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
                    SBV.warrior.setLocation(warriorX, warriorY);
                    if (warriorY > warriorStartY) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    timePast++;
                    if (timePast == 30) {
                        warriorY = warriorStartY;
                        warriorX = warriorStartX;
                        SBV.warrior.setLocation(warriorX, warriorY);
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
        /**
         *
         */
        public Timer shout = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (phase == 0) {
                    MusicPick.musicStart("demoshout", " ");
                    phase = 1;
                    animationPlaying = true;
                } else if (phase == 1) {
                    warriorY -= 5;
                    SBV.warrior.setLocation(warriorX, warriorY);
                    if (warriorY < warriorStartY + 50) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    if (warriorY < 50) {
                        warriorY = 50;
                        SBV.warrior.setLocation(warriorX, warriorY);
                    }
                    timePast++;
                    if (timePast < 50) {
                        if (timePast % 2 == 1) {
                            warriorX += 4;
                            SBV.warrior.setLocation(warriorX, warriorY);
                        } else {
                            warriorX -= 4;
                            SBV.warrior.setLocation(warriorX, warriorY);
                        }
                    }
                    if (timePast > 50) {
                        timePast = 0;
                        phase = 3;
                    }
                } else if (phase == 3) {
                    warriorY += 3;
                    SBV.warrior.setLocation(warriorX, warriorY);
                    if (warriorY > warriorStartY) {
                        warriorX = warriorStartX;
                        warriorY = warriorStartY;
                        SBV.warrior.setLocation(warriorX, warriorY);
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
        /**
         *
         */
        public Timer battlecry = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                SBV.swordIcon.setLocation(swordIconX, swordIconY);
                if (phase == 0) {
                    if (upMegaMath == 1) MusicPick.musicStart("demoshout", "");
                    SBV.swordIcon.setVisible(true);
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
                    SBV.swordIcon.setVisible(false);
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
         *
         */
        public Timer demoralized = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                SBV.demoSword1.setLocation(swordIconX + 550, swordIconY + 50);
                SBV.demoSword2.setLocation(swordIconX + 650, swordIconY + 100);
                SBV.demoSword3.setLocation(swordIconX + 750, swordIconY + 50);
                SBV.demoSword4.setLocation(swordIconX + 850, swordIconY + 100);


                if (phase == 0) {
                    if (swampBossHp > 0) SBV.demoSword1.setVisible(true);

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
                    SBV.demoSword1.setVisible(false);
                    SBV.demoSword2.setVisible(false);
                    SBV.demoSword3.setVisible(false);
                    SBV.demoSword4.setVisible(false);
                    timePast = 0;
                    upMegaMath = 1;
                    rightMegaMath = 1;
                    downMegaMath = 1;
                    leftMegaMath = 1;
                    if (!debuffed) {
                        debuffed = true;
                        bossDamage -= 10;
                    }
                    phase = 0;
                    animationPlaying = false;
                    demoralized.stop();
                }
            }
        });

        //ranger
        /**
         *
         */
        Timer shoot = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    SBV.arrow.setVisible(true);
                    if (arrowX == 121) {
                        animationPlaying = true;
                        MusicPick.musicStart("ding", "");
                    }
                    arrowX += 30;
                    SBV.arrow.setLocation(arrowX, arrowY);
                    if (arrowX > 1000) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    SBV.arrow.setVisible(false);
                    arrowX = arrowStartX;
                    SBV.arrow.setLocation(arrowX, arrowY);
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
        /**
         *
         */
        public Timer volley = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                arrowX += 30;
                SBV.volley1.setLocation(arrowX, arrowY);
                SBV.volley2.setLocation(arrowX - 200, arrowY);
                SBV.volley3.setLocation(arrowX - 400, arrowY);
                if (phase == 0) {
                    SBV.volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 1;
                } else if (phase == 1 && arrowX > arrowStartX + 300) {
                    SBV.volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 2;
                } else if (phase == 2 && arrowX > arrowStartX + 600) {
                    SBV.volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 3;
                } else if (phase == 3 && arrowX > 1000) {
                    SBV.volley1.setVisible(false);
                    phase = 4;
                } else if (phase == 4 && arrowX > 1200) {
                    SBV.volley2.setVisible(false);
                    phase = 5;
                } else if (phase == 5 && arrowX > 1400) {
                    SBV.volley3.setVisible(false);
                    phase = 6;
                }
                if (phase == 6) {
                    arrowX = 300;
                    SBV.volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 7;
                } else if (phase == 7 && arrowX > arrowStartX + 300) {
                    SBV.volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 8;
                } else if (phase == 8 && arrowX > arrowStartX + 600) {
                    SBV.volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 9;
                } else if (phase == 9 && arrowX > 1000) {
                    SBV.volley1.setVisible(false);
                    phase = 10;
                } else if (phase == 10 && arrowX > 1200) {
                    SBV.volley2.setVisible(false);
                    phase = 11;
                } else if (phase == 11 && arrowX > 1400) {
                    SBV.volley3.setVisible(false);
                    phase = 12;
                    arrowX = 300;
                }
                if (phase == 12) {
                    SBV.volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 13;
                } else if (phase == 13 && arrowX > arrowStartX + 300) {
                    SBV.volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 14;
                } else if (phase == 14 && arrowX > arrowStartX + 600) {
                    SBV.volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 15;
                } else if (phase == 15 && arrowX > 1000) {
                    SBV.volley1.setVisible(false);
                    phase = 16;
                } else if (phase == 16 && arrowX > 1200) {
                    SBV.volley2.setVisible(false);
                    phase = 17;
                } else if (phase == 17 && arrowX > 1400) {
                    SBV.volley3.setVisible(false);
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

        /**
         *
         */
        public void stealth() {
            if (!stealthed) {
                MusicPick.musicStart("stealth", "");
                SBV.ranger.setVisible(false);
                SBV.stealthranger.setVisible(true);
                stealthed = true;
            }
        }

        /**
         *
         */
        public void unstealth() {
            if (stealthed) {
                MusicPick.musicStart("unstealth", "");
                SBV.ranger.setVisible(true);
                SBV.stealthranger.setVisible(false);
                stealthed = false;
            }
        }

        /**
         *
         */
        public Timer bombthrow = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    animationPlaying = true;
                    phase = 1;
                    SBV.bomb.setVisible(true);
                }
                if (phase == 1) {
                    bombMegaMath -= 2;
                    bombX += 20;
                    bombY -= bombMegaMath;
                    SBV.bomb.setLocation(bombX, bombY);
                    if (bombY > bombStartY) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    timePast++;
                    if (timePast == 30) {
                        bombY = bombStartY;
                        bombX = bombStartX;
                        SBV.bomb.setLocation(bombX, bombY);
                        SBV.bomb.setVisible(false);
                        SBV.explode.setVisible(true);
                        MusicPick.musicStart("Explosion", "");
                    }
                    if (timePast == 60) {
                        bombMegaMath = 36;
                        SBV.bomb.setVisible(false);
                        SBV.explode.setVisible(false);
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
         *
         */
        //mage
        Timer blast = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    animationPlaying = true;
                    SBV.blast.setVisible(true);
                    if (blastY == 121) {
                        MusicPick.musicStart("fireball", "");
                    }
                    blastX += 30;
                    SBV.blast.setLocation(blastX, blastY);
                    if (blastX > 1000) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    SBV.blast.setVisible(false);
                    blastX = blastStartX;
                    SBV.blast.setLocation(blastX, blastY);
                    phase = 0;
                    spellDamageSystem(mageDamage, "single");
                    blast.stop();
                    animationPlaying = false;
                }
            }
        });

        /**
         *
         */
        public Timer pyroBlast = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                if (timePast < 100) {
                    animationPlaying = true;
                    SBV.smallPyroBlast.setVisible(true);
                } else if (timePast < 200) {
                    SBV.smallPyroBlast.setVisible(false);
                    SBV.mediumPyroBlast.setVisible(true);
                } else if (timePast < 350) {
                    SBV.mediumPyroBlast.setVisible(false);
                    SBV.bigPyroBlast.setVisible(true);
                    pyroBlastX = 45;
                    pyroblastY = 200;
                    SBV.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 400) {
                    pyroBlastX += 3;
                    pyroblastY -= 1;
                    SBV.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 460) {
                    pyroBlastX += 3;
                    pyroblastY += 1;
                    SBV.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 530) {
                    pyroBlastX += 3;
                    pyroblastY -= 1;
                    SBV.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else if (timePast < 590) {
                    pyroBlastX += 4;
                    pyroblastY += 1;
                    SBV.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                } else {
                    SBV.bigPyroBlast.setVisible(false);
                    timePast = 0;
                    pyroBlastX = 90;
                    pyroblastY = 300;
                    SBV.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                    spellDamageSystem(20, "single");
                    pyroBlast.stop();
                    animationPlaying = false;
                }
            }
        });

        /**
         *
         */
        public Timer flameStrike = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (phase == 0) {
                    animationPlaying = true;
                    MusicPick.musicStart("magespell", "");
                    phase = 1;
                } else if (phase == 1) {
                    mageY -= 3;
                    SBV.mage.setLocation(mageX, mageY);
                    if (mageY < 50) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    if (mageY < 50) {
                        mageY = 50;
                        SBV.mage.setLocation(mageX, mageY);
                    }
                    timePast++;
                    if (timePast < 100) {
                        if (timePast % 2 == 1) {
                            mageX += 6;
                            SBV.mage.setLocation(mageX, mageY);
                            flameStrikeY += 13;
                            SBV.flame.setLocation(900, flameStrikeY);
                        } else {
                            mageX -= 6;
                            SBV.mage.setLocation(mageX, mageY);
                        }
                    }
                    if (timePast == 102) {
                        SBV.mage.setLocation(mageX, mageY);
                        flameStrikeY = -400;
                        SBV.flame.setLocation(700, flameStrikeY);
                        spellDamageSystem(5, "all");
                    }
                    if (timePast > 130) {
                        timePast = 0;
                        phase = 3;
                    }
                } else if (phase == 3) {
                    mageY += 3;
                    SBV.mage.setLocation(mageX, mageY);
                    if (mageY > mageStartY) {
                        mageX = mageStartX;
                        mageY = mageStartY;
                        SBV.mage.setLocation(mageX, mageY);
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
         *
         */
        public Timer fireBall = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                pyroBlastX += 16;
                SBV.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
                animationPlaying = true;
                if (followup) {
                    MusicPick.musicStart("fireball", "");
                    followup = false;
                }
                if (phase == 0) {
                    SBV.smallPyroBlast.setVisible(true);
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
                    SBV.smallPyroBlast.setVisible(false);
                    timePast = 0;
                    upMegaMath = 1;
                    rightMegaMath = 1;
                    downMegaMath = 1;
                    leftMegaMath = 1;
                    phase = 0;
                    fireBall.stop();
                    spellDamageSystem(8, "single");
                    animationPlaying = false;
                    SBV.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
                }
            }
        });

        /**
         *
         */
        //healer
        public Timer holyLightSpell = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                if (phase == 0) {
                    animationPlaying = true;
                    if (healTarget == 1) SBV.holyLight.setLocation(warriorStartX - 220, warriorStartY - 500);
                    if (healTarget == 2) SBV.holyLight.setLocation(rangerStartX - 220, rangerStartY - 450);
                    if (healTarget == 3) SBV.holyLight.setLocation(mageStartX - 220, mageStartY - 450);
                    if (healTarget == 4) SBV.holyLight.setLocation(healerStartX - 220, healerStartY - 500);
                    MusicPick.musicStart("holylight", "");
                    SBV.holyLight.setVisible(true);
                    phase = 1;
                }
                if (timePast == 100) {
                    spellHealSystem(30, "single");
                    timePast = 0;
                    SBV.holyLight.setVisible(false);
                    holyLightSpell.stop();
                    phase = 0;
                    animationPlaying = false;
                }
            }
        });

        /**
         *
         */
        public Timer smallHolyLightSpell = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                if (phase == 0) {
                    animationPlaying = true;
                    if (healTarget == 1) SBV.smallHolyLight.setLocation(warriorStartX - 225, warriorStartY - 500);
                    if (healTarget == 2) SBV.smallHolyLight.setLocation(rangerStartX - 225, rangerStartY - 500);
                    if (healTarget == 3) SBV.smallHolyLight.setLocation(mageStartX - 225, mageStartY - 500);
                    if (healTarget == 4) SBV.smallHolyLight.setLocation(healerStartX - 225, healerStartY - 500);
                    MusicPick.musicStart("holylight", "");
                    SBV.smallHolyLight.setVisible(true);
                    phase = 1;
                }
                if (timePast > 100) {
                    spellHealSystem(15, "single");
                    timePast = 0;
                    SBV.smallHolyLight.setVisible(false);
                    smallHolyLightSpell.stop();
                    phase = 0;
                    animationPlaying = false;
                }
            }
        });

        /**
         *
         */
        public Timer groupHealSpell = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                if (phase == 0) {
                    animationPlaying = true;
                    SBV.groupHeal1.setLocation(warriorStartX + 75, warriorStartY - 500);
                    SBV.groupHeal2.setLocation(rangerStartX + 75, rangerStartY - 500);
                    SBV.groupHeal3.setLocation(mageStartX + 75, mageStartY - 500);
                    SBV.groupHeal4.setLocation(healerStartX + 75, healerStartY - 500);
                    MusicPick.musicStart("groupheal", "");
                    SBV.groupHeal1.setVisible(true);
                    SBV.groupHeal2.setVisible(true);
                    SBV.groupHeal3.setVisible(true);
                    SBV.groupHeal4.setVisible(true);
                    phase = 1;
                }
                if (timePast > 400) {
                    timePast = 0;
                    SBV.groupHeal1.setVisible(false);
                    SBV.groupHeal2.setVisible(false);
                    SBV.groupHeal3.setVisible(false);
                    SBV.groupHeal4.setVisible(false);
                    groupHealSpell.stop();
                    phase = 0;
                    spellHealSystem(10, "all");
                    animationPlaying = false;
                }
            }
        });

        /**
         *
         */
        public Timer healerAttack = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    animationPlaying = true;
                    if (healerX == 100) MusicPick.musicStart("ding", "");
                    healerX += 15;
                    SBV.healer.setLocation(healerX, healerY);
                    if (healerX > 200) {
                        phase = 1;
                    }
                } else if (phase == 1) {
                    healerX -= 15;
                    SBV.healer.setLocation(healerX, healerY);
                    if (healerX <= healerStartX) {
                        healerX = healerStartX;
                        SBV.healer.setLocation(healerX, healerY);
                        phase = 0;
                        spellDamageSystem(healerDamage, "single");
                        healerAttack.stop();
                        animationPlaying = false;
                        System.out.println("healer attack done");
                    }
                }
            }
        });

        /**
         *
         */
        //charge, hits 2 targets
        private Timer bossAttack1 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                animationPlaying = true;
                SBV.endTurnButton.setVisible(false);
                if (timePast < 50) {
                    swampBossX -= 30;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                } else if (timePast < 60) {
                    swampBossX += 30;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                } else if (timePast < 110) {
                    swampBossX = swampBossStartX;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                    swampBossAttack("attack1");
                    timePast = 0;
                    bossAttack1.stop();
                    SBV.endTurnButton.setVisible(true);
                    animationPlaying = false;
                    takeDamage.start();
                    animationPlaying = false;
                }
            }
        });

        /**
         *
         */
        //leap, hits all
        private Timer bossAttack2 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    animationPlaying = true;
                    SBV.endTurnButton.setVisible(false);
                    MusicPick.musicStart("Shreksound", "");
                    phase = 1;
                }
                if (phase == 1) {
                    bossMegaMath -= 2;
                    swampBossX -= 15;
                    swampBossY -= bossMegaMath;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                    if (swampBossY > swampBossStartY) {
                        swampBossY = swampBossStartY;
                        SBV.swampBoss.setLocation(swampBossX, swampBossY);
                        phase = 2;
                    }
                } else if (phase == 2) {
                    timePast++;
                    if (timePast == 90) {
                        swampBossY = swampBossStartY;
                        swampBossX = swampBossStartX;
                        SBV.swampBoss.setLocation(swampBossX, swampBossY);
                        timePast = 0;
                        bossMegaMath = 50;
                        phase = 0;
                        bossAttack2.stop();
                        swampBossAttack("attack2");
                        SBV.endTurnButton.setVisible(true);
                        animationPlaying = false;
                        takeDamage.start();

                    }
                }
            }
        });

        /**
         *
         */
        //attack 3, single target backstab
        private Timer bossAttack3 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                animationPlaying = true;
                SBV.endTurnButton.setVisible(false);
                if (timePast < 40) {
                    swampBossX += 30;
                    swampBossY -= 20;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                } else if (timePast < 48) {
                    swampBossY = swampBossStartY;
                    swampBossFlipX += 60;
                    SBV.swampBossflip.setLocation(swampBossFlipX, swampBossY);
                } else if (timePast < 56) {
                    swampBossFlipX -= 60;
                    SBV.swampBossflip.setLocation(swampBossFlipX, swampBossY);
                } else if (timePast < 150) {
                    swampBossX = swampBossStartX;
                    swampBossFlipX = swampBossFlipStart;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                    swampBossAttack("attack3");
                    timePast = 0;
                    bossAttack3.stop();
                    takeDamage.start();
                    SBV.endTurnButton.setVisible(true);
                    animationPlaying = false;
                }
            }
        });

        /**
         *
         */
        //attack 4, buff boss damage
        private Timer bossAttack4 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                if (timePast == 1) {
                    animationPlaying = true;
                    SBV.endTurnButton.setVisible(false);
                } else if (timePast < 10) {
                    swampBossX -= 10;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                } else if (timePast == 10) {
                    MusicPick.musicStart("wolfhowl", "");
                } else if (timePast < 90) {
                    //nothing
                } else if (timePast < 100) {
                    swampBossX += 5;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                } else if (timePast < 120) {
                    swampBossY = swampBossStartY;
                    swampBossX = swampBossStartX;
                    SBV.swampBoss.setLocation(swampBossX, swampBossY);
                    timePast = 0;
                    bossDamage +=5;
                    bossAttack4.stop();
                    SBV.endTurnButton.setVisible(true);
                    animationPlaying = false;
                    startNewTurn();
                }
            }
        });


        /**
         *
         */
        private Timer takeDamage = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePastTakeDamage++;
                if (timePastTakeDamage == 1) {

                } else if (timePastTakeDamage == 10) {
                    if (warriorattacked && warriorCurrentHp > 0) SBV.warrior.setVisible(false);
                    if (rangerattacked && rangerCurrentHp > 0) SBV.ranger.setVisible(false);
                    if (mageattacked && mageCurrentHp > 0) SBV.mage.setVisible(false);
                    if (healerattacked && healerCurrentHp > 0) SBV.healer.setVisible(false);
                } else if (timePastTakeDamage == 20) {
                    if (warriorattacked && warriorCurrentHp > 0) SBV.warrior.setVisible(true);
                    if (rangerattacked && rangerCurrentHp > 0) SBV.ranger.setVisible(true);
                    if (mageattacked && mageCurrentHp > 0) SBV.mage.setVisible(true);
                    if (healerattacked && healerCurrentHp > 0) SBV.healer.setVisible(true);
                } else if (timePastTakeDamage == 30) {
                    if (warriorattacked && warriorCurrentHp > 0) SBV.warrior.setVisible(false);
                    if (rangerattacked && rangerCurrentHp > 0) SBV.ranger.setVisible(false);
                    if (mageattacked  && mageCurrentHp > 0) SBV.mage.setVisible(false);
                    if (healerattacked  && healerCurrentHp > 0) SBV.healer.setVisible(false);
                } else if (timePastTakeDamage == 40) {
                    if (warriorattacked  && warriorCurrentHp > 0) SBV.warrior.setVisible(true);
                    if (rangerattacked  && rangerCurrentHp > 0) SBV.ranger.setVisible(true);
                    if (mageattacked  && mageCurrentHp > 0) SBV.mage.setVisible(true);
                    if (healerattacked  && healerCurrentHp > 0) SBV.healer.setVisible(true);
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