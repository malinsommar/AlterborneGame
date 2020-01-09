package game;

import party.Healer;
import party.Mage;
import party.Ranger;
import party.Warrior;

import javax.swing.*;

public class LevelUpController {

    //TODO this goes againt MVC. But it is by far the simplest way.
    private Mage m = new Mage();
    private Healer h = new Healer();
    private Warrior w = new Warrior();
    private Ranger r = new Ranger();
    LevelUpView luv = new LevelUpView();

    public int xp;
    public int level;

    int wHp;
    int wD;
    int wB;

    int rHp;
    int rD;
    int rB;

    int mHp;
    int mD;
    int mB;

    int hHp;
    int hD;
    int hB;

    //This method checks party xp and level party up. Use this method after every fight.
    public void didPlayerLevelUp(){

        if (xp>15 && level == 1){
            level = 2;
            levelUpStats();
            setLabels();
            luv.levelUpFrame();
        }
        if (xp>40 && level == 2){
            level = 3;
            levelUpStats();
            luv.levelUpFrame();
        }
        else if (xp>80 && level == 3){
            level = 4;
            levelUpStats();
            luv.levelUpFrame();
        }
        else if(xp>160 && level == 4){
            level = 5;
            levelUpStats();
            luv.levelUpFrame();
        }
        else if(xp>300 && level == 5){
            level = 6;
            levelUpStats();
            luv.levelUpFrame();
        }
        else if(xp>500 && level == 6){
            level = 7;
            levelUpStats();
            luv.levelUpFrame();
        }
        else if(xp>1000 && level == 7){
            level = 8;
            levelUpStats();
            luv.levelUpFrame();
        }
        else if(xp>2000 && level == 8){
            level = 9;
            levelUpStats();
            luv.levelUpFrame();
        }
        else if(xp>4000 && level == 9){
            level = 10;
            levelUpStats();
            luv.levelUpFrame();
        }
    }

    public void setLabels(){
        luv.whatLevel = new JLabel("Level "+level+"     ");

            luv.warriorHp = new JLabel("Hp: "+(w.hp-wHp/2)+" -> "+w.hp+"   ");
            luv.warriorDamage = new JLabel("Damage: "+(w.damage-wD/2)+" -> "+w.damage+"   ");
            luv.warriorBlock = new JLabel("Block: "+(w.block-wB/2)+" -> "+w.block+"   ");

            luv.rangerHp = new JLabel("Hp: "+(r.hp-rHp/2)+" -> "+r.hp+"   ");
            luv.rangerDamage = new JLabel("Damage: "+(r.damage-rD/2)+" -> "+r.damage+"   ");
            luv.rangerBlock = new JLabel("Block: "+(r.block-(rB/2))+" -> "+r.block+"   ");

            luv.mageHp = new JLabel("Hp: "+(m.hp-mHp/2)+" -> "+m.hp+"   ");
            luv.mageDamage = new JLabel("Damage: "+(m.damage-mD/2)+" -> "+m.damage+"   ");
            luv.mageBlock = new JLabel("Block: "+(m.block-mB/2)+" -> "+m.block+"   ");

            luv.healerHp = new JLabel("Hp: "+(h.hp-hHp/2)+" -> "+m.hp+"   ");
            luv.healerDamage = new JLabel("Damage: "+(h.damage-hD/2)+" -> "+h.damage+"   ");
            luv.healerBlock = new JLabel("Block: "+(h.block-hB/2)+" -> "+h.block+"   ");
        }

    //When player level up, this method adds bonuses to stats.
    public void levelUpStats(){

        w.hp += wHp;
        w.damage += wD;
        w.block += wB;

        wHp += wHp;
        wD += wD;
        wB += wB;

        r.hp += rHp;
        r.damage += rD;
        r.block += rB;

        rHp += rHp;
        rD += rD;
        rB += rB;

        m.hp += mHp;
        m.damage += mD;
        m.block += mB;

        mHp += mHp;
        mD += mD;
        mB += mB;

        h.hp += hHp;
        h.damage += hD;
        h.block += hB;

        hHp += hHp;
        hD += hD;
        hB += hB;
    }
}