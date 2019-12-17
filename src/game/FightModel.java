package game;

import fight.ForestCon;
import party.Healer;
import party.Mage;
import party.Ranger;
import party.Warrior;

public class FightModel {

    private ForestCon fc = new ForestCon();
    private Inventory inv = new Inventory();

    private Warrior w = new Warrior();
    private Mage m = new Mage();
    private Healer h = new Healer();
    private Ranger r = new Ranger();

    private int[] warriorStats = new int[3];
    private int[] mageStats = new int[3];
    private int[] healerStats = new int[3];
    private int[] rangerStats = new int[3];

    //Array that keeps track of how many potions you own, use this to . 1-3 = Healing potions. 4-6 = block potions. 7-9 = energy potions. 10-12 = str potions.
    private int[] ownedPotions = {inv.ownedMinorHealingPotion,inv.ownedLesserHealingPotion,inv.ownedMajorHealingPotion,inv.ownedMinorBlockPotion,inv.ownedLesserBlockPotion,inv.ownedMajorBlockPotion,inv.ownedMinorEnergyPotion,inv.ownedLesserEnergyPotion,inv.ownedMajorEnergyPotion,inv.ownedMinorStrengthPotion,inv.ownedLesserStrengthPotion,inv.ownedMajorStrengthPotion};

    //This method imports all stats that the battle will need and start ForestFightController.
    public void startForestFight(){
        sendInventory();
        sendStats();
        fc.startFight();
    }

    //This method sends inventory info to ForestFightController.
    public void sendInventory(){
        fc.getInventory(ownedPotions);
    }

    //This method sends stats info to ForestFightController.
    public void sendStats(){
        getStats();
        fc.getPlayerStats(warriorStats,mageStats,healerStats,rangerStats);
    }

    //This method get stats from party-member classes.
    private void getStats(){

        warriorStats[0]=w.hp;
        warriorStats[1]=w.combinedBlock;
        warriorStats[2]=w.combinedDamage;

        mageStats[0]=m.hp;
        mageStats[1]=m.combinedBlock;
        mageStats[2]=m.combinedDamage;

        healerStats[0]=h.hp;
        healerStats[1]=h.combinedBlock;
        healerStats[2]=h.combinedDamage;

        rangerStats[0]=r.hp;
        rangerStats[1]=r.combinedBlock;
        rangerStats[2]=r.combinedDamage;
    }
}