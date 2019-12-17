/*package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animations {

    //Animation variables
    //player
    public int warriorStartX = 170, warriorStartY = 210, warriorX = warriorStartX, warriorY = warriorStartY;
    public int rangerStartX = 70, rangerStartY = 290, rangerX = rangerStartX, rangerY = rangerStartY;
    public int mageStartX = -110, mageStartY = 290, mageX = mageStartX, mageY = mageStartY;
    public int healerStartX = -30, healerStartY = 210, healerX = healerStartX, healerY = healerStartY;
    //enemy
    public int wolf1X = 850, wolf1Y = 320, wolf1StartX = wolf1X, wolf1StartY = wolf1Y;
    public int wolf2X = 1030, wolf2Y = 320, wolf2StartX = wolf2X, wolf2StartY = wolf2Y;
    public int wolf3X = 900, wolf3Y = 400, wolf3StartX = wolf3X, wolf3StartY = wolf3Y;
    public int wolf4X = 1080, wolf4Y = 400, wolf4StartX = wolf4X, wolf4StartY = wolf4Y;
    //spells/attack
    public int swordIconX = 300, swordIconY = 300;
    public int arrowX = 120, arrowY = 360, arrowStartX = arrowX, arrowStartY = arrowY;
    public int flameStrikeY = -400;

    public int warriorMegaMath = 30; //används för halv cirkel anitamationer, PLEASE FOR THE LOVE OF GOD RENAME THIS MONSTOSITY
    public int bombMegaMath = 36;
    public int upMegaMath = 1;
    public int rightMegaMath = 1;
    public int downMegaMath = 1;
    public int leftMegaMath = 1;

    public int pyroBlastX = 45;
    public int pyroblastY = 150;
    public int bombX = 250;
    public int bombY = 300;
    public int bombStartX = 250;
    public int bombStartY = 300;

    public int objXTest = 300;
    public int objYTest = 300;
    public int objXTestStart = 300;
    public int objYTestStart = 300;
    public int xmmXTest = 0;
    public int xmmYTest = 20;


    //Another timePast to avoid conflict when they run simultaneously.
    public int timePastTakeDamage = 0;

    public int target;
    public int phase = 0, timePast = 0;
    public boolean followup = false;
    public boolean stealthed = false;

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

    public void animations(){
        Timer charge = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0) {
                    MusicPick.musicStart("charge", "");
                    phase = 1;
                }
                else if (phase == 1) {
                    warriorX += 20;
                    warrior.setLocation(warriorX, warriorY);
                    if (warriorX > 2000) {
                        phase = 2;
                    }
                }
                else if (phase == 2) {
                    warriorX = warriorStartX;
                    warriorY = warriorStartY;
                    warrior.setLocation(warriorX,warriorY);
                    phase = 0;
                    charge.stop();
                }
            }
        });

        Timer volley = new Timer(10, new ActionListener() {
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
                }
                else if (phase == 1 && arrowX > arrowStartX + 300) {
                    volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 2;
                }
                else if (phase == 2 && arrowX > arrowStartX + 600) {
                    volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 3;
                }
                else if (phase == 3 && arrowX > 1000) {
                    volley1.setVisible(false);
                    phase = 4;
                }
                else if (phase == 4 && arrowX > 1200) {
                    volley2.setVisible(false);
                    phase = 5;
                }
                else if (phase == 5 && arrowX > 1400) {
                    volley3.setVisible(false);
                    phase = 6;
                }
                if (phase == 6) {
                    arrowX = 300;
                    volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 7;
                }
                else if (phase == 7 && arrowX > arrowStartX + 300) {
                    volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 8;
                }
                else if (phase == 8 && arrowX > arrowStartX + 600) {
                    volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 9;
                }
                else if (phase == 9 && arrowX > 1000) {
                    volley1.setVisible(false);
                    phase = 10;
                }
                else if (phase == 10 && arrowX > 1200) {
                    volley2.setVisible(false);
                    phase = 11;
                }
                else if (phase == 11 && arrowX > 1400) {
                    volley3.setVisible(false);
                    phase = 12;
                    arrowX = 300;
                }
                if (phase == 12) {
                    volley1.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 13;
                }
                else if (phase == 13 && arrowX > arrowStartX + 300) {
                    volley2.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 14;
                }
                else if (phase == 14 && arrowX > arrowStartX + 600) {
                    volley3.setVisible(true);
                    MusicPick.musicStart("ding", "");
                    phase = 15;
                }
                else if (phase == 15 && arrowX > 1000) {
                    volley1.setVisible(false);
                    phase = 16;
                }
                else if (phase == 16 && arrowX > 1200) {
                    volley2.setVisible(false);
                    phase = 17;
                }
                else if (phase == 17 && arrowX > 1400) {
                    volley3.setVisible(false);
                    phase = 18;
                }
                else if (phase == 18){
                    arrowX = 270;
                    phase = 0;
                    volley.stop();
                }
            }
        });

        Timer flameStrike = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (phase == 0) {
                    MusicPick.musicStart("magespell", "");
                    phase = 1;
                }
                else if (phase == 1){
                    mageY -= 3;
                    mage.setLocation(mageX, mageY);
                    if (mageY < 50) {
                        phase = 2;
                    }}
                else if (phase == 2) {
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
                        wolf1Int = wolf1Int - mageDamage/2;
                        wolf1Hp.setText("Wolf 1: " + wolf1Int);
                        wolf2Int = wolf2Int - mageDamage/2;
                        wolf2Hp.setText("Wolf 2: " + wolf2Int);
                        wolf3Int = wolf3Int - mageDamage/2;
                        wolf3Hp.setText("Wolf 3: " + wolf3Int);
                        wolf4Int = wolf4Int - mageDamage/2;
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



        Timer enemyTurnTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                endTurnButton.setVisible(false);
                if (timePast < 50) {
                    if (wolf1Int<1)timePast = 140;
                    whosTurn.setText("Wolf 1 turn");
                    playersHp.setText("Hp: " + wolf1Int);
                    energy.setText("  ");
                }

                else if (timePast == 50 && wolf1Int > 0) {
                    wolfAttack();
                    partyDeath();
                } else if (timePast < 60) {
                    wolf1X -= 15;
                    wolf1.setLocation(wolf1X, wolf1Y);
                } else if (timePast < 70) {
                    wolf1X += 15;
                    wolf1.setLocation(wolf1X, wolf1Y);
                }

                else if (timePast < 150) {
                    if (wolf2Int<1)timePast = 240;
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
                }

                else if (timePast < 250) {
                    if (wolf3Int<1)timePast = 340;
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
                }

                else if (timePast < 350) {
                    if (wolf4Int<1)timePast = 440;
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

        Timer tackle = new Timer(10, new ActionListener() {
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

        Timer takeDamage = new Timer(10, new ActionListener() {
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

        Timer dunk = new Timer(10, new ActionListener() {
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
                    warrior.setLocation(warriorX, warriorY);
                    if (warriorY > warriorStartY) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    timePast++;
                    if(timePast == 30) {
                        warriorY = warriorStartY;
                        warriorX = warriorStartX;
                        warrior.setLocation(warriorX, warriorY);
                        timePast = 0;
                        warriorMegaMath = 30;
                        phase = 0;
                        dunk.stop();
                    }
                }
            }
        });

        Timer pyroBlast = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                if (timePast < 100) {
                    smallPyroBlast.setVisible(true);
                }
                else if (timePast < 200) {
                    smallPyroBlast.setVisible(false);
                    mediumPyroBlast.setVisible(true);
                }
                else if (timePast < 350) {
                    mediumPyroBlast.setVisible(false);
                    bigPyroBlast.setVisible(true);
                }
                else if (timePast < 400 ){
                    pyroBlastX += 3;
                    pyroblastY -= 1;
                    bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                }
                else if (timePast < 460){
                    pyroBlastX += 3;
                    pyroblastY += 1;
                    bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                }
                else if (timePast < 530){
                    pyroBlastX += 3;
                    pyroblastY -= 1;
                    bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                }
                else if (timePast < 590){
                    pyroBlastX += 4;
                    pyroblastY += 1;
                    bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                }
                else {
                    bigPyroBlast.setVisible(false);
                    timePast = 0;
                    pyroBlastX = 45;
                    pyroblastY = 150;
                    bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                    pyroBlast.stop();
                }
            }
        });
        Timer shout = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (phase == 0) {
                    MusicPick.musicStart("demoshout", " ");
                    phase = 1;
                }
                else if (phase == 1){
                    warriorY -= 5;
                    warrior.setLocation(warriorX, warriorY);
                    if (warriorY < warriorStartY + 50) {
                        phase = 2;
                    }}
                else if (phase == 2) {
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
        Timer fireBall = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                pyroBlastX += 16;
                smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
                if (followup){MusicPick.musicStart("fireball", "");
                    followup = false;
                }
                if (phase == 0){
                    smallPyroBlast.setVisible(true);
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

        Timer battlecry = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                swordIcon.setLocation(swordIconX, swordIconY);
                if (phase == 0) {
                    //MusicPick.musicStart("demoshout", "");
                    swordIcon.setVisible(true);
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
        Timer demoralized = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                timePast++;
                demoSword1.setLocation(swordIconX + 550, swordIconY + 50);
                demoSword2.setLocation(swordIconX + 650, swordIconY + 100);
                demoSword3.setLocation(swordIconX + 750, swordIconY + 50);
                demoSword4.setLocation(swordIconX + 850, swordIconY + 100);


                if (phase == 0) {
                    demoSword1.setVisible(true);
                    demoSword2.setVisible(true);
                    demoSword3.setVisible(true);
                    demoSword4.setVisible(true);
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
                    demoSword1.setVisible(false);
                    demoSword2.setVisible(false);
                    demoSword3.setVisible(false);
                    demoSword4.setVisible(false);
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
        public Timer bombthrow = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (phase == 0){
                    MusicPick.musicStart("ding", "");
                    phase = 1;
                    bomb.setVisible(true);
                }
                if (phase == 1) {
                    bombMegaMath -=2;
                    bombX += 20;
                    bombY -= bombMegaMath;
                    bomb.setLocation(bombX, bombY);
                    if (bombY > bombStartY) {
                        phase = 2;
                    }
                } else if (phase == 2) {
                    timePast++;
                    if(timePast == 30) {
                        bombY = bombStartY;
                        bombX = bombStartX;
                        bomb.setLocation(bombX, bombY);
                        bomb.setVisible(false);
                        explode.setVisible(true);
                    }
                    if(timePast == 60){
                        bombMegaMath = 36;
                        bomb.setVisible(false);
                        explode.setVisible(false);
                        timePast = 0;
                        phase = 0;
                        bombthrow.stop();
                    }
                }
            }
        });

        Timer bombcircletest = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bomb.setLocation(objXTest, objYTest);
                timePast++;
                if (phase == 0){
                    MusicPick.musicStart("ding", "");
                    phase = 1;
                    bomb.setVisible(true);
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
                    bomb.setVisible(false);
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

        public void stealth(){
            if (!stealthed){
                MusicPick.musicStart("stealth", "");
                ranger.setVisible(false);
                stealthranger.setVisible(true);
                stealthed = true;
            }
            else{
                MusicPick.musicStart("unstealth", "");
                ranger.setVisible(true);
                stealthranger.setVisible(false);
                stealthed = false;
            }
        /*
        lägg till
        if (stealthed == true){stealth();}
        i alla ranger abilities
        och öka skadan på nåt sätt
         *//*
        }

        Timer placetrap = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                bomb.setLocation(objXTest, objYTest);
                timePast++;
                if (phase == 0){
                    MusicPick.musicStart("ding", "");
                    phase = 1;
                    trap.setVisible(true);
                }

            }
        });
    }
    
    
}
*/