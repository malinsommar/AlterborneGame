package fight;

import game.LoseScreen;
import game.MusicPick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class CaveController {

    CaveView cv = new CaveView();


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
    private int warriorEnergyInt = 5, mageEnergyInt, rangerEnergyInt, healerEnergyInt;


    //Animation variables
    //player
    public int warriorStartX = 170, warriorStartY = 210, warriorX = warriorStartX, warriorY = warriorStartY;
    public int rangerStartX = 70, rangerStartY = 290, rangerX = rangerStartX, rangerY = rangerStartY;
    public int mageStartX = -110, mageStartY = 290, mageX = mageStartX, mageY = mageStartY;
    public int healerStartX = -30, healerStartY = 210, healerX = healerStartX, healerY = healerStartY;

    public int goblin1X = 850, goblin1Y = 320, goblin1StartX = goblin1X, goblin1StartY = goblin1Y;
    public int goblin2X = 1030, goblin2Y = 320, goblin2StartX = goblin2X, goblin2StartY = goblin2Y;
    public int goblin3X = 900, goblin3Y = 400, goblin3StartX = goblin3X, goblin3StartY = goblin3Y;
    public int goblin4X = 1080, goblin4Y = 400, goblin4StartX = goblin4X, goblin4StartY = goblin4Y;

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


    private int[] ownedPotions = new int[12];

    int[] goblinHp = {30, 30, 30, 30};

    public void startFight() {

        MusicPick.musicStart("forest1", "music");

        currentEnergy = 5;

        setStartLabels();
        cv.caveFightFrame();
        hoverEffect();
        targetSystem();

        //ActionListeners
        cv.attackButton.addActionListener(e -> attackPressed());
        cv.blockButton.addActionListener(e -> blockPressed());
        cv.itemButton.addActionListener(e -> cv.itemPressed());
        cv.skillButton.addActionListener(e -> spellMenuActive()); //for now
        cv.endTurnButton.addActionListener(e -> startNewTurn());
        cv.skill1Button.addActionListener(e -> {
            skill1();
        });
        cv.skill2Button.addActionListener(e -> {
            skill2();
        });
        cv.skill3Button.addActionListener(e -> {
            skill3();
        });
        cv.skill4Button.addActionListener(e -> {
            skill4();
        });
        cv.returnButton.addActionListener(e -> spellMenuInactive());

        //Action listeners for the potions. Sends them to usePotion() with an unique number/int.
        cv.potion1.addActionListener(e -> usePotion(1));
        cv.potion2.addActionListener(e -> usePotion(2));
        cv.potion3.addActionListener(e -> usePotion(3));
        cv.potion4.addActionListener(e -> usePotion(4));
        cv.potion5.addActionListener(e -> usePotion(5));
        cv.potion6.addActionListener(e -> usePotion(6));
        cv.potion7.addActionListener(e -> usePotion(7));
        cv.potion8.addActionListener(e -> usePotion(8));
        cv.potion9.addActionListener(e -> usePotion(9));
        cv.potion10.addActionListener(e -> usePotion(10));
        cv.potion11.addActionListener(e -> usePotion(11));
        cv.potion12.addActionListener(e -> usePotion(12));

        //Dispose the item frame.
        cv.exitInventory.addActionListener(e -> cv.inventory.dispose());
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
            cv.whosTurn.setText("Warrior's turn");
            cv.playersHp.setText("Hp: " + warriorCurrentHp);
            cv.energy.setText("Energy: " + warriorEnergyInt);
            cv.block.setText("Block: " + warriorBlock);
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
            cv.whosTurn.setText("Ranger's turn");
            cv.playersHp.setText("Hp: " + rangerCurrentHp);
            cv.energy.setText("Energy: " + rangerEnergyInt);
            cv.block.setText("Block: " + rangerBlock);
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
            cv.whosTurn.setText("Mage's turn");
            cv.playersHp.setText("Hp: " + mageCurrentHp);
            cv.energy.setText("Energy: " + mageEnergyInt);
            cv.block.setText("Block: " + mageBlock);
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
            cv.whosTurn.setText("Healer's turn");
            cv.playersHp.setText("Hp: " + healerCurrentHp);
            cv.energy.setText("Energy: " + healerEnergyInt);
            cv.block.setText("Block: " + healerBlock);

        }
        //If healer is dead, skip.
        if (turns == 4 && healerCurrentHp < 1) {
            turns = 5;
        }
        //  ***ENEMIES TURN***
        if (turns == 5) {
            cv.whosTurn.setText(" ");
            cv.playersHp.setText(" ");
            cv.energy.setText(" ");
            cv.block.setText(" ");
            enemyTurnTimer.start();
        }
    }

    private void enemyDamage(){
        for (int i = 0; i < 4; i++) {
            if (goblinHp[i] > 0) {
                goblinAttack();
                partyDeath();
            }
        }
        isFightOver();
    }

    public void targetSystem() {

        cv.goblin1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 1;
                cv.targetarrow.setLocation(875, 250);
                cv.targetarrow.setVisible(true);
            }
        });
        cv.goblin2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 2;
                cv.targetarrow.setLocation(1065, 250);
                cv.targetarrow.setVisible(true);
            }
        });
        cv.goblin3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 3;
                cv.targetarrow.setLocation(925, 325);
                cv.targetarrow.setVisible(true);
            }
        });
        cv.goblin4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                target = 4;
                cv.targetarrow.setLocation(1100, 325);
                cv.targetarrow.setVisible(true);
            }
        });
    }

    private void skill1() {
        if (turns == 1 && warriorEnergyInt > 1 && cv.targetarrow.isVisible()) {
            warriorEnergyInt = warriorEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            cv.energy.setText("Energy: " + warriorEnergyInt);
            charge.start();
        }
        if (turns == 2 && rangerEnergyInt > 3 && cv.targetarrow.isVisible()) {
            rangerEnergyInt = rangerEnergyInt - 4;
            currentEnergy = currentEnergy - 4;
            cv.energy.setText("Energy: " + rangerEnergyInt);
            volley.start();
        }
        if (turns == 3 && mageEnergyInt > 1 && cv.targetarrow.isVisible()) {
            followup = true;
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            cv.energy.setText("Energy: " + mageEnergyInt);
            fireBall.start();
        }
        if (turns == 4 && healerEnergyInt > 1) {
            healingTargetMenu(1);
        }
    }

    private void skill2() {
        if (turns == 1 && warriorEnergyInt > 1) {
            warriorEnergyInt = warriorEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            cv.energy.setText("Energy: " + warriorEnergyInt);
            dunk.start();
        }
        if (turns == 2 && rangerEnergyInt > 2) {
            rangerEnergyInt = rangerEnergyInt - 3;
            currentEnergy = currentEnergy - 3;
            cv.energy.setText("Energy: " + rangerEnergyInt);
            bombthrow.start();
        }
        if (turns == 3) {

        }
        if (turns == 4 && healerEnergyInt > 1) {
            healingTargetMenu(2);
        }
    }

    private void skill3() {
        if (turns == 1) {
            followup = true;
            shout.start();
        }
        if (turns == 2) {

        }
        if (turns == 3 && mageEnergyInt > 2) {
            mageEnergyInt = mageEnergyInt - 3;
            currentEnergy = currentEnergy - 3;
            cv.energy.setText("Energy: " + mageEnergyInt);
            flameStrike.start();
        }
        if (turns == 4 && healerEnergyInt > 4) {
            healerEnergyInt = healerEnergyInt - 5;
            currentEnergy = currentEnergy - 5;
            cv.energy.setText("Energy: " + healerEnergyInt);
            groupHealSpell.start();
        }
    }

    private void skill4() {
        if (turns == 1) {
            shout.start();
        }
        if (turns == 2 && rangerEnergyInt > 2) {
            rangerEnergyInt = rangerEnergyInt - 3;
            currentEnergy = currentEnergy - 3;
            cv.energy.setText("Energy: " + rangerEnergyInt);
            stealth();
        }
        if (turns == 3 && mageEnergyInt > 4 && cv.targetarrow.isVisible()) {
            followup = true;
            mageEnergyInt = mageEnergyInt - 5;
            currentEnergy = currentEnergy - 5;
            cv.energy.setText("Energy: " + mageEnergyInt);
            pyroBlast.start();
        }
        if (turns == 4) {

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
            if (chosenSpell == 1 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 1;
                holyLightSpell.start();
            }

            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 1;
                smallHolyLightSpell.start();
            }
        });
        cv.healRangerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 2;
                holyLightSpell.start();
            }
            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 2;
                smallHolyLightSpell.start();
            }
        });
        cv.healMageButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 3;
                holyLightSpell.start();
            }
            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 3;
                smallHolyLightSpell.start();
            }
        });
        cv.healHealerButton.addActionListener(e -> {
            if (chosenSpell == 1 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 4;
                holyLightSpell.start();
            }
            if (chosenSpell == 2 && healerEnergyInt > 1) {
                healerEnergyInt = healerEnergyInt - 2;
                currentEnergy = currentEnergy - 2;
                cv.energy.setText("Energy: " + healerEnergyInt);
                healTarget = 4;
                smallHolyLightSpell.start();
            }
        });
    }

    private void spellMenuActive() {
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
            cv.skill1Button.setText("Charge (2)");
            cv.skill2Button.setText("Slam (3)");
            cv.skill3Button.setText("Battlecry");
            cv.skill4Button.setText("Demoralize");
        }
        //ranger
        if (turns == 2){
            cv.skill1Button.setText("Volley (4)");
            cv.skill2Button.setText("Bomb (3)");
            cv.skill3Button.setText(" ");
            cv.skill4Button.setText("Stealth (3)");
        }
        //mage
        if (turns == 3) {
            cv.skill1Button.setText("Fireball (2)");
            cv.skill2Button.setText(" ");
            cv.skill3Button.setText("Meteor (4)");
            cv.skill4Button.setText("Pyroblast (5)");
        }
        //healer
        if (turns == 4){
            cv.skill1Button.setText("Heal (4)");
            cv.skill2Button.setText("Bless (2)");
            cv.skill3Button.setText("Restore (5)");
            cv.skill4Button.setText(" ");
        }
    }

    //When player press block
    private void blockPressed() {

        //If its warrior's turn and player has 2 or more energy.
        if (turns == 1 && warriorEnergyInt > 1) {
            warriorEnergyInt = warriorEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            warriorBlock += 5;
            cv.energy.setText("Energy: " + warriorEnergyInt);
            cv.block.setText("Block: " + warriorBlock);
        }
        //If its ranger's turn and player has 2 or more energy.
        else if (turns == 2 && rangerEnergyInt > 1) {
            rangerEnergyInt = rangerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            rangerBlock += 5;
            cv.energy.setText("Energy: " + rangerEnergyInt);
            cv.block.setText("Block: " + rangerBlock);
        }
        //If its mage's turn and player has 2 or more energy.
        else if (turns == 3 && mageEnergyInt > 1) {
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            mageBlock += 5;
            cv.energy.setText("Energy: " + mageEnergyInt);
            cv.block.setText("Block: " + mageBlock);
        }
        //If its healer's turn and player has 2 or more energy.
        else if (turns == 4 && healerEnergyInt > 1) {
            healerEnergyInt = healerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            healerBlock += 5;
            cv.energy.setText("Energy: " + healerEnergyInt);
            cv.block.setText("Block: " + healerBlock);
        }
    }

    //When you press the "attack button".
    private void attackPressed() {

        //If its warrior's turn and player has 2 or more energy.
        if (turns == 1 && warriorEnergyInt > 1 && cv.targetarrow.isVisible()) {
            warriorEnergyInt = warriorEnergyInt - 2; //Energy -2.
            currentEnergy = currentEnergy - 2; // Update currentEnergy.
            cv.energy.setText("Energy: " + warriorEnergyInt); //Update energyLabel
            tackle.start(); //Warrior deals damage to a goblin.
        }
        //If its ranger's turn and player has 2 or more energy.
        else if (turns == 2 && rangerEnergyInt > 1 && cv.targetarrow.isVisible()) {
            rangerEnergyInt = rangerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            cv.energy.setText("Energy: " + rangerEnergyInt);
            shoot.start();
        }
        //If its mage's turn and player has 2 or more energy.
        else if (turns == 3 && mageEnergyInt > 1 && cv.targetarrow.isVisible()) {
            mageEnergyInt = mageEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            cv.energy.setText("Energy: " + mageEnergyInt);
            blast.start();
        }
        //If its healer's turn and player has 2 or more energy.
        else if (turns == 4 && healerEnergyInt > 1 && cv.targetarrow.isVisible()) {
            healerEnergyInt = healerEnergyInt - 2;
            currentEnergy = currentEnergy - 2;
            cv.energy.setText("Energy: " + healerEnergyInt);
            healerAttack.start();
        }
    }

    //Checks if all of the enemies or party-members are dead.
    private void isFightOver() {
        //If all of the wolves are dead. Open lootScreen.
        if (goblinHp[0] < 1 && goblinHp[1] < 1 && goblinHp[2] < 1 && goblinHp[3] < 1) {
            MusicPick.musicStop();
            cv.caveFightJFrame.dispose();
            /*//TODO This does not follow MVC
            FightModel fm = new FightModel();
            fm.fightWon(2);*/
        }
        //In the whole party is dead, game is over. Send to loseScreen.
        if (warriorCurrentHp < 1 && mageCurrentHp < 1 && healerCurrentHp < 1 && rangerCurrentHp < 1) {
            cv.caveFightJFrame.dispose();
            //TODO this does not follow mvc
            LoseScreen ls = new LoseScreen();
            ls.loseScreen();
        }
        //If none of these are true, nothing happens and the fight goes on.
    }

    public void setStartLabels() {

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

        cv.goblin1Hp = new JLabel("Goblin 1: " + goblinHp[0]);
        cv.goblin2Hp = new JLabel("Goblin 2: " + goblinHp[1]);
        cv.goblin3Hp = new JLabel("Goblin 3: " + goblinHp[2]);
        cv.goblin4Hp = new JLabel("Goblin 4: " + goblinHp[3]);

        cv.playersHp = new JLabel("Hp: " + warriorCurrentHp);
        cv.player1Hp = new JLabel("Warrior: " + warriorCurrentHp);
        cv.player2Hp = new JLabel("Mage:    " + mageCurrentHp);
        cv.player3Hp = new JLabel("Ranger:  " + rangerCurrentHp);
        cv.player4Hp = new JLabel("Healer:  " + healerCurrentHp);
        cv.block = new JLabel("Block: " + warriorBlock);
    }

    //When the goblin attacks.
    private void goblinAttack() {
        target = (int) (Math.random() * 4); //Random target, 0-3.
        int goblinDamage = (int) (Math.random() * 10) + 15;//Generate random damage, 15-25.
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
                    goblinDamage = goblinDamage - warriorBlock; //Warrior take damage equal to goblin damage.
                    warriorCurrentHp = warriorCurrentHp - goblinDamage; //Update warrior hp.
                    cv.player1Hp.setText("Warrior: " + warriorCurrentHp); //Update hp Label.
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
                    goblinDamage = goblinDamage - mageBlock;
                    mageCurrentHp = mageCurrentHp - goblinDamage;
                    cv.player2Hp.setText("Mage:    " + mageCurrentHp);
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
                    goblinDamage = goblinDamage - rangerBlock;
                    rangerCurrentHp = rangerCurrentHp - goblinDamage;
                    cv.player3Hp.setText("Ranger:  " + rangerCurrentHp);
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
                    goblinDamage = goblinDamage - healerBlock;
                    healerCurrentHp = healerCurrentHp - goblinDamage;
                    cv.player4Hp.setText("Healer:   " + healerCurrentHp);
                    break;
                }
            }
        }
    }

    //Checks if an enemy died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void mobDeath() {

        if (goblinHp[0] <= 0) {
            cv.goblin1Hp.setText("Goblin 1: 0");
            cv.goblin1.setVisible(false);
            if (target == 1) {
                cv.targetarrow.setVisible(false);
            }
        }
        if (goblinHp[1] <= 0) {
            cv.goblin2Hp.setText("Goblin 2: 0");
            cv.goblin2.setVisible(false);
            if (target == 2) {
                cv.targetarrow.setVisible(false);
            }
        }
        if (goblinHp[2] <= 0) {
            cv.goblin3Hp.setText("Goblin 3: 0");
            cv.goblin3.setVisible(false);
            if (target == 3) {
                cv.targetarrow.setVisible(false);
            }
        }
        if (goblinHp[3] <= 0) {
            cv.goblin4Hp.setText("Goblin 4: 0");
            cv.goblin4.setVisible(false);
            if (target == 4) {
                cv.targetarrow.setVisible(false);
            }
        }
    }

    //Checks if any party-member died. If so, set gif to "setVisible(false);" and hp label to 0.
    private void partyDeath() {

        if (warriorCurrentHp <= 0) {
            warriorCurrentHp = 0;
            cv.player1Hp.setText("Warrior: " + warriorCurrentHp);
            cv.warrior.setVisible(false);
        }
        if (mageCurrentHp <= 0) {
            mageCurrentHp = 0;
            cv.player2Hp.setText("Mage:    " + mageCurrentHp);
            cv.mage.setVisible(false);
        }
        if (rangerCurrentHp <= 0) {
            rangerCurrentHp = 0;
            cv.player3Hp.setText("Ranger:  " + rangerCurrentHp);
            cv.ranger.setVisible(false);
        }
        if (healerCurrentHp <= 0) {
            healerCurrentHp = 0;
            cv.player4Hp.setText("Healer:  " + healerCurrentHp);
            cv.healer.setVisible(false);
        }
    }

    private void spellMenuInactive() {
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
                    ownedPotions[0] -= 1;
                    cv.potion1Label.setText("" + ownedPotions[0]); //Update ownedPotion Label.
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    warriorCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + warriorCurrentHp);
                    cv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[1] -= 1;
                    cv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    warriorCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + warriorCurrentHp);
                    cv.player1Hp.setText("Warrior: " + warriorCurrentHp);
                    ownedPotions[2] -= 1;
                    cv.potion3Label.setText("" + ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    warriorBlock += 5;
                    cv.block.setText("Block: " + warriorBlock);
                    ownedPotions[3] -= 1;
                    cv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    warriorBlock += 20;
                    cv.block.setText("Block: " + warriorBlock);
                    ownedPotions[4] -= 1;
                    cv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    warriorBlock += 50;
                    cv.block.setText("Block: " + warriorBlock);
                    ownedPotions[5] -= 1;
                    cv.potion6Label.setText("" + ownedPotions[5]);
                }
            } else if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    warriorEnergyInt += 3;
                    cv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[6] -= 1;
                    cv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    warriorCurrentHp += 5;
                    cv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[7] -= 1;
                    cv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    warriorCurrentHp += 10;
                    cv.energy.setText("Energy: " + warriorEnergyInt);
                    ownedPotions[8] -= 1;
                    cv.potion9Label.setText("" + ownedPotions[8]);
                }
            }
            if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    warriorDamage += 5;
                    ownedPotions[9] -= 1;
                    cv.potion10Label.setText("" + ownedPotions[9]);
                }
            }
            if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    warriorDamage += 10;
                    ownedPotions[10] -= 1;
                    cv.potion11Label.setText("" + ownedPotions[10]);
                }
            }
            if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    warriorDamage += 20;
                    ownedPotions[11] -= 1;
                    cv.potion12Label.setText("" + ownedPotions[11]);
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
                    ownedPotions[0] -= 1;
                    cv.potion1Label.setText("" + ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    rangerCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + rangerCurrentHp);
                    cv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[1] -= 1;
                    cv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + rangerCurrentHp);
                    cv.player2Hp.setText("Ranger: " + rangerCurrentHp);
                    ownedPotions[2] -= 1;
                    cv.potion3Label.setText("" + ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    rangerBlock += 5;
                    cv.block.setText("Block: " + rangerBlock);
                    ownedPotions[3] -= 1;
                    cv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    rangerBlock += 20;
                    cv.block.setText("Block: " + rangerBlock);
                    ownedPotions[4] -= 1;
                    cv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    rangerBlock += 50;
                    cv.block.setText("Block: " + rangerBlock);
                    ownedPotions[5] -= 1;
                    cv.potion6Label.setText("" + ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    rangerEnergyInt += 3;
                    cv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[6] -= 1;
                    cv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    rangerEnergyInt += 5;
                    cv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[7] -= 1;
                    cv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    rangerEnergyInt += 10;
                    cv.energy.setText("Energy: " + rangerEnergyInt);
                    ownedPotions[8] -= 1;
                    cv.potion9Label.setText("" + ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    rangerDamage += 5;
                    ownedPotions[9] -= 1;
                    cv.potion10Label.setText("" + ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    rangerDamage += 10;
                    ownedPotions[10] -= 1;
                    cv.potion11Label.setText("" + ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    rangerDamage += 20;
                    ownedPotions[11] -= 1;
                    cv.potion12Label.setText("" + ownedPotions[11]);
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
                    ownedPotions[0] -= 1;
                    cv.potion1Label.setText("" + ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    mageCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + mageCurrentHp);
                    cv.player3Hp.setText("Mage: " + mageCurrentHp);
                    ownedPotions[1] -= 1;
                    cv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    rangerCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + mageCurrentHp);
                    cv.player3Hp.setText("Mage: " + mageCurrentHp);
                    cv.potion3Label.setText("" + ownedPotions[2]);
                    ownedPotions[2] -= 1;
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    mageBlock += 5;
                    cv.block.setText("Block: " + mageBlock);
                    ownedPotions[3] -= 1;
                    cv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    mageBlock += 20;
                    cv.block.setText("Block: " + mageBlock);
                    ownedPotions[4] -= 1;
                    cv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    mageBlock += 50;
                    cv.block.setText("Block: " + mageBlock);
                    ownedPotions[5] -= 1;
                    cv.potion6Label.setText("" + ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    mageEnergyInt += 3;
                    cv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[6] -= 1;
                    cv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    mageEnergyInt += 5;
                    cv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[7] -= 1;
                    cv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    mageEnergyInt += 10;
                    cv.energy.setText("Energy: " + mageEnergyInt);
                    ownedPotions[8] -= 1;
                    cv.potion9Label.setText("" + ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    mageDamage += 5;
                    ownedPotions[9] -= 1;
                    cv.potion10Label.setText("" + ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    mageDamage += 10;
                    ownedPotions[10] -= 1;
                    cv.potion11Label.setText("" + ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    mageDamage += 20;
                    ownedPotions[11] -= 1;
                    cv.potion12Label.setText("" + ownedPotions[11]);
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
                    ownedPotions[0] -= 1;
                    cv.potion1Label.setText("" + ownedPotions[0]);
                }
            } else if (potion == 2) {
                if (ownedPotions[1] > 0) {
                    healerCurrentHp += 30;
                    cv.playersHp.setText("Hp: " + healerCurrentHp);
                    cv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[1] -= 1;
                    cv.potion2Label.setText("" + ownedPotions[1]);
                }
            } else if (potion == 3) {
                if (ownedPotions[2] > 0) {
                    healerCurrentHp += 60;
                    cv.playersHp.setText("Hp: " + healerCurrentHp);
                    cv.player4Hp.setText("Mage: " + healerCurrentHp);
                    ownedPotions[2] -= 1;
                    cv.potion3Label.setText("" + ownedPotions[2]);
                }
            } else if (potion == 4) {
                if (ownedPotions[3] > 0) {
                    healerBlock += 5;
                    cv.block.setText("Block: " + healerBlock);
                    ownedPotions[3] -= 1;
                    cv.potion4Label.setText("" + ownedPotions[3]);
                }
            } else if (potion == 5) {
                if (ownedPotions[4] > 0) {
                    healerBlock += 20;
                    cv.block.setText("Block: " + healerBlock);
                    ownedPotions[4] -= 1;
                    cv.potion5Label.setText("" + ownedPotions[4]);
                }
            } else if (potion == 6) {
                if (ownedPotions[5] > 0) {
                    healerBlock += 50;
                    cv.block.setText("Block: " + healerBlock);
                    ownedPotions[5] -= 1;
                    cv.potion6Label.setText("" + ownedPotions[5]);
                }
            }
            if (potion == 7) {
                if (ownedPotions[6] > 0) {
                    healerEnergyInt += 3;
                    cv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[6] -= 1;
                    cv.potion7Label.setText("" + ownedPotions[6]);
                }
            } else if (potion == 8) {
                if (ownedPotions[7] > 0) {
                    healerEnergyInt += 5;
                    cv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[7] -= 1;
                    cv.potion8Label.setText("" + ownedPotions[7]);
                }
            } else if (potion == 9) {
                if (ownedPotions[8] > 0) {
                    healerEnergyInt += 10;
                    cv.energy.setText("Energy: " + healerEnergyInt);
                    ownedPotions[8] -= 1;
                    cv.potion9Label.setText("" + ownedPotions[8]);
                }
            } else if (potion == 10) {
                if (ownedPotions[9] > 0) {
                    healerDamage += 5;
                    ownedPotions[9] -= 1;
                    cv.potion10Label.setText("" + ownedPotions[9]);
                }
            } else if (potion == 11) {
                if (ownedPotions[10] > 0) {
                    healerDamage += 10;
                    ownedPotions[10] -= 1;
                    cv.potion11Label.setText("" + ownedPotions[10]);
                }
            } else if (potion == 12) {
                if (ownedPotions[11] > 0) {
                    healerDamage += 20;
                    ownedPotions[11] -= 1;
                    cv.potion12Label.setText("" + ownedPotions[11]);
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
                if (currentEnergy >= 2) {
                    cv.attackButton.setBackground(Color.lightGray);
                }
                if (currentEnergy < 2) {
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
                if (currentEnergy >= 2) {
                    cv.blockButton.setBackground(Color.lightGray);
                }
                if (currentEnergy < 2) {
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


    //called from spells to deal damage to enemies
    //damageTargets types: single, line, all
    public void spellDamageSystem(int damage, String damageTargets) {
        if (damageTargets.equals("single")) {
            goblinHp[target - 1] -= damage;
        }
        if (damageTargets.equals("line")) {
            if (target == 1 || target == 2) {
                goblinHp[0] -= damage;
                goblinHp[1] -= damage;
            }
            if (target == 3 || target == 4) {
                goblinHp[2] -= damage;
                goblinHp[3] -= damage;
            }
        }
        if (damageTargets.equals("all")) {
            goblinHp[0] -= damage;
            goblinHp[1] -= damage;
            goblinHp[2] -= damage;
            goblinHp[3] -= damage;
        }
        cv.goblin1Hp.setText("goblin 1: " + goblinHp[0]);
        cv.goblin2Hp.setText("goblin 2: " + goblinHp[1]);
        cv.goblin3Hp.setText("goblin 3: " + goblinHp[2]);
        cv.goblin4Hp.setText("goblin 4: " + goblinHp[3]);
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
        cv.player1Hp.setText("Warrior: " + warriorCurrentHp);
        cv.player2Hp.setText("Ranger:  " + rangerCurrentHp);
        cv.player3Hp.setText("Mage:    " + mageCurrentHp);
        cv.player4Hp.setText("Healer:  " + healerCurrentHp);
    }


    //warrior
    public Timer tackle = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                if (warriorX == 100) MusicPick.musicStart("warriorattack", "");
                animationPlaying = true;
                warriorX += 15;
                cv.warrior.setLocation(warriorX, warriorY);
                if (warriorX > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                warriorX -= 15;
                cv.warrior.setLocation(warriorX, warriorY);
                if (warriorX <= warriorStartX) {
                    warriorX = warriorStartX;
                    cv.warrior.setLocation(warriorX, warriorY);
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
                cv.warrior.setLocation(warriorX, warriorY);
                if (warriorX > 2000) {
                    phase = 2;
                }
            } else if (phase == 2) {
                warriorX = warriorStartX;
                warriorY = warriorStartY;
                cv.warrior.setLocation(warriorX, warriorY);
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
                cv.warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    phase = 2;
                }
            } else if (phase == 2) {
                timePast++;
                if (timePast == 30) {
                    warriorY = warriorStartY;
                    warriorX = warriorStartX;
                    cv.warrior.setLocation(warriorX, warriorY);
                    timePast = 0;
                    warriorMegaMath = 30;
                    phase = 0;
                    dunk.stop();
                    spellDamageSystem(3, "all");
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
                cv.warrior.setLocation(warriorX, warriorY);
                if (warriorY < warriorStartY + 50) {
                    phase = 2;
                }
            } else if (phase == 2) {
                if (warriorY < 50) {
                    warriorY = 50;
                    cv.warrior.setLocation(warriorX, warriorY);
                }
                timePast++;
                if (timePast < 50) {
                    if (timePast % 2 == 1) {
                        warriorX += 4;
                        cv.warrior.setLocation(warriorX, warriorY);
                    } else {
                        warriorX -= 4;
                        cv.warrior.setLocation(warriorX, warriorY);
                    }
                }
                if (timePast > 50) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                warriorY += 3;
                cv.warrior.setLocation(warriorX, warriorY);
                if (warriorY > warriorStartY) {
                    warriorX = warriorStartX;
                    warriorY = warriorStartY;
                    cv.warrior.setLocation(warriorX, warriorY);
                    phase = 0;
                    shout.stop();
                    if (followup) {
                        battlecry.start();
                        followup = false;
                    } else {
                        demoralized.start();
                        System.out.println("working");
                    }
                }
            }
        }
    });

    public Timer battlecry = new Timer(20, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            cv.swordIcon.setLocation(swordIconX, swordIconY);
            if (phase == 0) {
                if (upMegaMath == 1) MusicPick.musicStart("demoshout", "");
                cv.swordIcon.setVisible(true);
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
                cv.swordIcon.setVisible(false);
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
            cv.demoSword1.setLocation(swordIconX + 550, swordIconY + 50);
            cv.demoSword2.setLocation(swordIconX + 650, swordIconY + 100);
            cv.demoSword3.setLocation(swordIconX + 750, swordIconY + 50);
            cv.demoSword4.setLocation(swordIconX + 850, swordIconY + 100);


            if (phase == 0) {
                cv.demoSword1.setVisible(true);
                cv.demoSword2.setVisible(true);
                cv.demoSword3.setVisible(true);
                cv.demoSword4.setVisible(true);
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
                cv.demoSword1.setVisible(false);
                cv.demoSword2.setVisible(false);
                cv.demoSword3.setVisible(false);
                cv.demoSword4.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
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
                cv.arrow.setVisible(true);
                if (arrowX == 121) {
                    animationPlaying = true;
                    MusicPick.musicStart("ding", "");
                }
                arrowX += 30;
                cv.arrow.setLocation(arrowX, arrowY);
                if (arrowX > 1000) {
                    phase = 1;
                }
            } else if (phase == 1) {
                cv.arrow.setVisible(false);
                arrowX = arrowStartX;
                cv.arrow.setLocation(arrowX, arrowY);
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
            cv.volley1.setLocation(arrowX, arrowY);
            cv.volley2.setLocation(arrowX - 200, arrowY);
            cv.volley3.setLocation(arrowX - 400, arrowY);
            if (phase == 0) {
                cv.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 1;
            } else if (phase == 1 && arrowX > arrowStartX + 300) {
                cv.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 2;
            } else if (phase == 2 && arrowX > arrowStartX + 600) {
                cv.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 3;
            } else if (phase == 3 && arrowX > 1000) {
                cv.volley1.setVisible(false);
                phase = 4;
            } else if (phase == 4 && arrowX > 1200) {
                cv.volley2.setVisible(false);
                phase = 5;
            } else if (phase == 5 && arrowX > 1400) {
                cv.volley3.setVisible(false);
                phase = 6;
            }
            if (phase == 6) {
                arrowX = 300;
                cv.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 7;
            } else if (phase == 7 && arrowX > arrowStartX + 300) {
                cv.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 8;
            } else if (phase == 8 && arrowX > arrowStartX + 600) {
                cv.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 9;
            } else if (phase == 9 && arrowX > 1000) {
                cv.volley1.setVisible(false);
                phase = 10;
            } else if (phase == 10 && arrowX > 1200) {
                cv.volley2.setVisible(false);
                phase = 11;
            } else if (phase == 11 && arrowX > 1400) {
                cv.volley3.setVisible(false);
                phase = 12;
                arrowX = 300;
            }
            if (phase == 12) {
                cv.volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 13;
            } else if (phase == 13 && arrowX > arrowStartX + 300) {
                cv.volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 14;
            } else if (phase == 14 && arrowX > arrowStartX + 600) {
                cv.volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 15;
            } else if (phase == 15 && arrowX > 1000) {
                cv.volley1.setVisible(false);
                phase = 16;
            } else if (phase == 16 && arrowX > 1200) {
                cv.volley2.setVisible(false);
                phase = 17;
            } else if (phase == 17 && arrowX > 1400) {
                cv.volley3.setVisible(false);
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
            cv.ranger.setVisible(false);
            cv.stealthranger.setVisible(true);
            stealthed = true;
        }
    }

    public void unstealth() {
        if (stealthed) {
            MusicPick.musicStart("unstealth", "");
            cv.ranger.setVisible(true);
            cv.stealthranger.setVisible(false);
            stealthed = false;
        }
    }

    public Timer bombthrow = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                animationPlaying = true;
                MusicPick.musicStart("ding", "");
                phase = 1;
                cv.bomb.setVisible(true);
            }
            if (phase == 1) {
                bombMegaMath -= 2;
                bombX += 20;
                bombY -= bombMegaMath;
                cv.bomb.setLocation(bombX, bombY);
                if (bombY > bombStartY) {
                    phase = 2;
                }
            } else if (phase == 2) {
                timePast++;
                if (timePast == 30) {
                    bombY = bombStartY;
                    bombX = bombStartX;
                    cv.bomb.setLocation(bombX, bombY);
                    cv.bomb.setVisible(false);
                    cv.explode.setVisible(true);
                }
                if (timePast == 60) {
                    bombMegaMath = 36;
                    cv.bomb.setVisible(false);
                    cv.explode.setVisible(false);
                    timePast = 0;
                    phase = 0;
                    bombthrow.stop();
                    spellDamageSystem(4, "all");
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
                cv.blast.setVisible(true);
                if (blastY == 121) {
                    MusicPick.musicStart("fireball", "");
                }
                blastX += 30;
                cv.blast.setLocation(blastX, blastY);
                if (blastX > 1000) {
                    phase = 1;
                }
            } else if (phase == 1) {
                cv.blast.setVisible(false);
                blastX = blastStartX;
                cv.blast.setLocation(blastX, blastY);
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
                cv.smallPyroBlast.setVisible(true);
            } else if (timePast < 200) {
                cv.smallPyroBlast.setVisible(false);
                cv.mediumPyroBlast.setVisible(true);
            } else if (timePast < 350) {
                cv.mediumPyroBlast.setVisible(false);
                cv.bigPyroBlast.setVisible(true);
                pyroBlastX = 45;
                pyroblastY = 200;
                cv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 400) {
                pyroBlastX += 3;
                pyroblastY -= 1;
                cv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 460) {
                pyroBlastX += 3;
                pyroblastY += 1;
                cv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 530) {
                pyroBlastX += 3;
                pyroblastY -= 1;
                cv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else if (timePast < 590) {
                pyroBlastX += 4;
                pyroblastY += 1;
                cv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            } else {
                cv.bigPyroBlast.setVisible(false);
                timePast = 0;
                pyroBlastX = 90;
                pyroblastY = 300;
                cv.bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
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
                cv.mage.setLocation(mageX, mageY);
                if (mageY < 50) {
                    phase = 2;
                }
            } else if (phase == 2) {
                if (mageY < 50) {
                    mageY = 50;
                    cv.mage.setLocation(mageX, mageY);
                }
                timePast++;
                if (timePast < 100) {
                    if (timePast % 2 == 1) {
                        mageX += 6;
                        cv.mage.setLocation(mageX, mageY);
                        flameStrikeY += 13;
                        cv.flame.setLocation(900, flameStrikeY);
                    } else {
                        mageX -= 6;
                        cv.mage.setLocation(mageX, mageY);
                    }
                }
                if (timePast == 102) {
                    cv.mage.setLocation(mageX, mageY);
                    flameStrikeY = -400;
                    cv.flame.setLocation(700, flameStrikeY);
                    spellDamageSystem(5, "all");
                }
                if (timePast > 130) {
                    timePast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                mageY += 3;
                cv.mage.setLocation(mageX, mageY);
                if (mageY > mageStartY) {
                    mageX = mageStartX;
                    mageY = mageStartY;
                    cv.mage.setLocation(mageX, mageY);
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
            cv.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            animationPlaying = true;
            if (followup) {
                MusicPick.musicStart("fireball", "");
                followup = false;
            }
            if (phase == 0) {
                cv.smallPyroBlast.setVisible(true);
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
                cv.smallPyroBlast.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                fireBall.stop();
                spellDamageSystem(8, "single");
                animationPlaying = false;
                cv.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
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
                if (healTarget == 1) cv.holyLight.setLocation(warriorStartX - 220, warriorStartY - 500);
                if (healTarget == 2) cv.holyLight.setLocation(rangerStartX - 220, rangerStartY - 450);
                if (healTarget == 3) cv.holyLight.setLocation(mageStartX - 220, mageStartY - 450);
                if (healTarget == 4) cv.holyLight.setLocation(healerStartX - 220, healerStartY - 500);
                MusicPick.musicStart("holylight", "");
                cv.holyLight.setVisible(true);
                phase = 1;
            }
            if (timePast == 100) {
                timePast = 0;
                cv.holyLight.setVisible(false);
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
                if (healTarget == 1) cv.smallHolyLight.setLocation(warriorStartX - 225, warriorStartY - 500);
                if (healTarget == 2) cv.smallHolyLight.setLocation(rangerStartX - 225, rangerStartY - 500);
                if (healTarget == 3) cv.smallHolyLight.setLocation(mageStartX - 225, mageStartY - 500);
                if (healTarget == 4) cv.smallHolyLight.setLocation(healerStartX - 225, healerStartY - 500);
                MusicPick.musicStart("holylight", "");
                cv.smallHolyLight.setVisible(true);
                phase = 1;
            }
            if (timePast > 100) {
                timePast = 0;
                cv.smallHolyLight.setVisible(false);
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
                cv.groupHeal1.setLocation(warriorStartX + 75, warriorStartY - 500);
                cv.groupHeal2.setLocation(rangerStartX + 75, rangerStartY - 500);
                cv.groupHeal3.setLocation(mageStartX + 75, mageStartY - 500);
                cv.groupHeal4.setLocation(healerStartX + 75, healerStartY - 500);
                MusicPick.musicStart("groupheal", "");
                cv.groupHeal1.setVisible(true);
                cv.groupHeal2.setVisible(true);
                cv.groupHeal3.setVisible(true);
                cv.groupHeal4.setVisible(true);
                phase = 1;
            }
            if (timePast > 500) {
                timePast = 0;
                cv.groupHeal1.setVisible(false);
                cv.groupHeal2.setVisible(false);
                cv.groupHeal3.setVisible(false);
                cv.groupHeal4.setVisible(false);
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
                cv.healer.setLocation(healerX, healerY);
                if (healerX > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                healerX -= 15;
                cv.healer.setLocation(healerX, healerY);
                if (healerX <= healerStartX) {
                    healerX = healerStartX;
                    cv.healer.setLocation(healerX, healerY);
                    phase = 0;
                    spellDamageSystem(healerDamage, "single");
                    healerAttack.stop();
                    animationPlaying = false;
                }
            }
        }
    });

    //enemy
    public Timer enemyTurnTimer = new Timer(7, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            animationPlaying = true;
            cv.endTurnButton.setVisible(false);
            if (timePast < 50) {
                if (goblinHp[0] < 1) timePast += 100;
            } else if (timePast < 60) {
                goblin1X -= 15;
                cv.goblin1.setLocation(goblin1X, goblin1Y);
            } else if (timePast < 70) {
                goblin1X += 15;
                cv.goblin1.setLocation(goblin1X, goblin1Y);
            } else if (timePast < 150) {
                cv.goblin1.setLocation(goblin1StartX, goblin1StartY);
                if (goblinHp[1] < 1) timePast += 100;

            } else if (timePast < 160) {
                goblin2X -= 15;
                cv.goblin2.setLocation(goblin2X, goblin2Y);
            } else if (timePast < 170) {
                goblin2X += 15;
                cv.goblin2.setLocation(goblin2X, goblin2Y);
            } else if (timePast < 250) {
                cv.goblin2.setLocation(goblin2StartX, goblin2StartY);
                if (goblinHp[2] < 1) timePast += 100;

            } else if (timePast < 260) {
                goblin3X -= 15;
                cv.goblin3.setLocation(goblin3X, goblin3Y);
            } else if (timePast < 270) {
                goblin3X += 15;
                cv.goblin3.setLocation(goblin3X, goblin3Y);
            } else if (timePast < 350) {
                cv.goblin3.setLocation(goblin3StartX, goblin3StartY);
                if (goblinHp[3] < 1) timePast += 100;

            } else if (timePast < 360) {
                goblin4X -= 15;
                cv.goblin4.setLocation(goblin4X, goblin4Y);
            } else if (timePast < 370) {
                goblin4X += 15;
                cv.goblin4.setLocation(goblin4X, goblin4Y);
            } else if (timePast < 450) {
                cv.goblin4.setLocation(goblin4StartX, goblin4StartY);
                enemyTurnTimer.stop();
                timePast = 0;
                cv.endTurnButton.setVisible(true);
                turns = 0;
                animationPlaying = false;
                takeDamage.start();
            }
        }
    });

    public Timer takeDamage = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePastTakeDamage++;
            if (timePastTakeDamage == 1) {
                MusicPick.musicStart("warriorattacked", "");
            } else if (timePastTakeDamage == 10) {
                if (warriorattacked) cv.warrior.setVisible(false);
                if (rangerattacked) cv.ranger.setVisible(false);
                if (mageattacked) cv.mage.setVisible(false);
                if (healerattacked) cv.healer.setVisible(false);
            } else if (timePastTakeDamage == 20) {
                if (warriorattacked) cv.warrior.setVisible(true);
                if (rangerattacked) cv.ranger.setVisible(true);
                if (mageattacked) cv.mage.setVisible(true);
                if (healerattacked) cv.healer.setVisible(true);
            } else if (timePastTakeDamage == 30) {
                if (warriorattacked) cv.warrior.setVisible(false);
                if (rangerattacked) cv.ranger.setVisible(false);
                if (mageattacked) cv.mage.setVisible(false);
                if (healerattacked) cv.healer.setVisible(false);
            } else if (timePastTakeDamage == 40) {
                if (warriorattacked) cv.warrior.setVisible(true);
                if (rangerattacked) cv.ranger.setVisible(true);
                if (mageattacked) cv.mage.setVisible(true);
                if (healerattacked) cv.healer.setVisible(true);

                warriorattacked = false;
                rangerattacked = false;
                mageattacked = false;
                healerattacked = false;
                animationPlaying = false;
                takeDamage.stop();
                timePastTakeDamage = 0;
                enemyDamage();
                startNewTurn();
            }
        }
    });
}