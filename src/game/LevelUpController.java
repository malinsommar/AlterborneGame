package game;

import javax.swing.*;

public class LevelUpController {

    LevelUpView luv;

    public int xp;
    public int level;

    private int wHp, wB, wD;
    private int rHp, rB, rD;
    private int mHp, mB, mD;
    private int hHp, hB, hD;

    private int warriorHp, warriorBlock, warriorDamage;
    private int rangerHp, rangerBlock, rangerDamage;
    private int mageHp, mageBlock, mageDamage;
    private int healerHp, healerBlock, healerDamage;

    boolean done = false;
    boolean playerNotLevelUp = false;

    //This method checks OldClasses.party xp and level OldClasses.party up. Use this method after every fight.
    public void didPlayerLevelUp(int getXp, int getLevel, int[] wUp, int[] rUp, int[] mUp, int[] hUp, int[] wStat, int[] rStat, int[] mStat, int[] hStat){
        luv = new LevelUpView();

        done = false;

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

        setLabels();
        luv.levelUpFrame();
        luv.continueButton.addActionListener(e -> done = true);
        luv.continueButton.addActionListener(e -> luv.levelFrame.dispose());

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
}