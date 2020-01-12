package OldClasses;

public class FightModel {
/*
    private ForestCon forestCon = new ForestCon();
    private CaveController caveCon = new CaveController();
    private FieldController fieldCon = new FieldController();
    private Inventory inv = new Inventory();
    CaveView cv = new CaveView();

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
        forestCon.getInventory(ownedPotions);
        getStats();
        forestCon.getPlayerStats(warriorStats,mageStats,healerStats,rangerStats);
        forestCon.startFight();

    }

    public void startCaveFight(){
        caveCon.getInventory(ownedPotions);
        getStats();
        caveCon.getPlayerStats(warriorStats,mageStats,healerStats,rangerStats);
        caveCon.startFight();
    }

    public void startFieldFight(){
        /*
        fieldCon.getInventory(ownedPotions);
        getStats();
        fieldCon.getPlayerStats(warriorStats,mageStats,healerStats,rangerStats);
        fieldCon.startFight();*/
         /*
    }

    public void fightWon(int whatFight){
        MasterModel mm = new MasterModel();
        try {
            mm.startLootModel(whatFight);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fightLost(){
        LoseScreen ls = new LoseScreen();
        ls.loseScreen();
    }

    //This method get stats from OldClasses.party-member classes.
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
    */
}