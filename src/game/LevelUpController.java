package game;

import javax.swing.*;

public class LevelUpController {

    LevelUpView luv = new LevelUpView();

    public int xp;
    public int level;

    private int wHp, wB, wD;
    private int rHp, rB, rD;
    private int mHp, mB, mD;
    private int hHp, hB, hD;

    public int warriorHp, warriorBlock, warriorDamage;
    public int rangerHp, rangerBlock, rangerDamage;
    public int mageHp, mageBlock, mageDamage;
    public int healerHp, healerBlock, healerDamage;

    boolean done = false;
    boolean playerNotLevelUp = false;

    //This method checks OldClasses.party xp and level OldClasses.party up. Use this method after every fight.
    public void didPlayerLevelUp(int getXp, int getLevel, int[] wUp, int[] rUp, int[] mUp, int[] hUp, int[] wStat, int[] rStat, int[] mStat, int[] hStat){

        xp = getXp;
        level = getLevel;
        
        wHp = wUp[0];
        wB = wUp[1];
        wD = wUp[2];

        warriorHp = wStat[0];
        warriorBlock = wStat[1];
        warriorDamage = wStat[2];

        rHp = rUp[0];
        rB = rUp[1];
        rD = rUp[2];

        rangerHp = rStat[0];
        rangerBlock = rStat[1];
        rangerDamage = rStat[2];

        mHp = mUp[0];
        mB = mUp[1];
        mD = mUp[2];

        mageHp = mStat[0];
        mageBlock = mStat[1];
        mageDamage = mStat[2];

        hHp = hUp[0];
        hB = hUp[1];
        hD = hUp[2];

        healerHp = hStat[0];
        healerBlock = hStat[1];
        healerDamage = hStat[2];

        if (xp>15 && level == 1){
            level = 2;
            levelUpStats();
            setLabels();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        if (xp>40 && level == 2){
            level = 3;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else if (xp>80 && level == 3){
            level = 4;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else if(xp>160 && level == 4){
            level = 5;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else if(xp>300 && level == 5){
            level = 6;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else if(xp>500 && level == 6){
            level = 7;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else if(xp>1000 && level == 7){
            level = 8;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else if(xp>2000 && level == 8){
            level = 9;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else if(xp>4000 && level == 9){
            level = 10;
            levelUpStats();
            luv.levelUpFrame();
            luv.continueButton.addActionListener(e -> done = true);
        }
        else{
            playerNotLevelUp = true;
        }
    }

    public void setLabels(){

            luv.whatLevel = new JLabel("Level "+level+"     ");

            luv.warriorHp = new JLabel("Hp: "+(warriorHp-wHp/2)+" -> "+warriorHp+"   ");
            luv.warriorDamage = new JLabel("Damage: "+(warriorDamage-wD/2)+" -> "+warriorDamage+"   ");
            luv.warriorBlock = new JLabel("Block: "+(warriorBlock-wB/2)+" -> "+warriorBlock+"   ");

            luv.rangerHp = new JLabel("Hp: "+(rangerHp-rHp/2)+" -> "+rangerHp+"   ");
            luv.rangerDamage = new JLabel("Damage: "+(rangerDamage-rD/2)+" -> "+rangerDamage+"   ");
            luv.rangerBlock = new JLabel("Block: "+(rangerBlock-(rB/2))+" -> "+rangerBlock+"   ");

            luv.mageHp = new JLabel("Hp: "+(mageHp-mHp/2)+" -> "+mageHp+"   ");
            luv.mageDamage = new JLabel("Damage: "+(mageDamage-mD/2)+" -> "+mageDamage+"   ");
            luv.mageBlock = new JLabel("Block: "+(mageBlock-mB/2)+" -> "+mageBlock+"   ");

            luv.healerHp = new JLabel("Hp: "+(healerHp-hHp/2)+" -> "+healerHp+"   ");
            luv.healerDamage = new JLabel("Damage: "+(healerDamage-hD/2)+" -> "+healerDamage+"   ");
            luv.healerBlock = new JLabel("Block: "+(healerBlock-hB/2)+" -> "+healerBlock+"   ");
        }

    //When player level up, this method adds bonuses to stats.
    public void levelUpStats(){

        warriorHp += wHp;
        warriorDamage += wD;
        warriorBlock += wB;

        wHp += wHp;
        wD += wD;
        wB += wB;

        rangerHp += rHp;
        rangerDamage += rD;
        rangerBlock += rB;

        rHp += rHp;
        rD += rD;
        rB += rB;

        mageHp += mHp;
        mageDamage += mD;
        mageBlock += mB;

        mHp += mHp;
        mD += mD;
        mB += mB;

        healerHp += hHp;
        healerDamage += hD;
        healerBlock += hB;

        hHp += hHp;
        hD += hD;
        hB += hB;
    }
}