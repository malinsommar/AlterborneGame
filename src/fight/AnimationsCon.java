package fight;

import game.MusicPick;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationsCon {


    ForestFightFrame fff = new ForestFightFrame();

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
    public int arrowX = 120, arrowY = 360, arrowStartX = arrowX;
    public int blastX = 120, blastY = 360, blastStartX = arrowX;

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
    public int phase = 0;
    public int timePast = 0;
    public int healTarget = 0;
    public boolean followup = false;
    public boolean animationPlaying = false;
    public boolean stealthed = false;



        //ANIMATIONER OCH TIMERS


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
                    animationPlaying = false;
                }
            }
        });

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
                swordIconX = 300;
                swordIconY = 300;
                fff.swordIcon.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                animationPlaying = true;
                battlecry.stop();
            }
        }
    });

    public Timer demoralized = new Timer(20, new ActionListener() {
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
                animationPlaying = true;
                demoralized.stop();
            }
        }
    });

        //ranger

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
            }
        }
    });

        Timer volley = new Timer(10, new ActionListener() {
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
                }
            }
        });

    public void stealth() {
        if (!stealthed) {
            MusicPick.musicStart("stealth", "");
            fff.ranger.setVisible(false);
            fff.stealthranger.setVisible(true);
            stealthed = true;
        }
    }
    public void unstealth(){
        if (stealthed){
            MusicPick.musicStart("unstealth", "");
            fff.ranger.setVisible(true);
            fff.stealthranger.setVisible(false);
            stealthed = false;
        }
    }

    public Timer bombthrow = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0){
                animationPlaying = true;
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
                animationPlaying = false;
                fff.smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
        }
    });

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
            if (timePast > 500){
                timePast = 0;
                fff.groupHeal1.setVisible(false);
                fff.groupHeal2.setVisible(false);
                fff.groupHeal3.setVisible(false);
                fff.groupHeal4.setVisible(false);
                groupHealSpell.stop();
                phase = 0;
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
                    healerAttack.stop();
                    animationPlaying = false;
                }
            }
        }
    });



    //enemy
    public Timer enemyTurnTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            animationPlaying = true;
            if (timePast < 50) {
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
            }
            else if (timePast < 360) {
                wolf4X -= 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 370) {
                wolf4X += 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 450) {
                fff.wolf4.setLocation(wolf4StartX, wolf4StartY);
                enemyTurnTimer.stop();
                timePast = 0;
                animationPlaying = false;
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





    //not used, don't remove
    public Timer bombcircletest = new Timer(5, new ActionListener() {
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
    }








    //enemy
    /*
    public Timer backupenemyTurnTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            fff.endTurnButton.setVisible(false);
            fff.targetarrow.setVisible(false);
            if (timePast < 50) {
                animationPlaying = true;
                if (wolfHp[0]<1)timePast = 140;
                fff.whosTurn.setText("Wolf 1 turn");
                fff.playersHp.setText("Hp: " + wolfHp[0]);
                fff.energy.setText("  ");
            }

            else if (timePast == 50 && wolfHp[0] > 0) {
                fc.wolfAttack();
                fc.partyDeath();
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
                fc.wolfAttack();
                fc.partyDeath();
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
                fc.wolfAttack();
                fc.partyDeath();
            } else if (timePast < 360) {
                wolf4X -= 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 370) {
                wolf4X += 15;
                fff.wolf4.setLocation(wolf4X, wolf4Y);
            } else if (timePast < 450) {
                fff.wolf4.setLocation(wolf4StartX, wolf4StartY);
                enemyTurnTimer.stop();
                fc.turns = 0;
                timePast = 0;
                fc.startNewTurn();
                animationPlaying = false;
                fff.endTurnButton.setVisible(true);
            }
        }
    });
*/