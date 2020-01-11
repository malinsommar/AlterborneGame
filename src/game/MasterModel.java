package game;

import OldClasses.Inventory;
import davidtest.overworld.map.WorldModel;
import fight.CaveController;
import fight.CaveView;
import fight.FieldController;
import fight.ForestCon;
import party.Healer;
import party.Mage;
import party.Ranger;
import party.Warrior;

public class MasterModel {

    private LootFrame lf = new LootFrame();
    private HubController hubController = new HubController();
    private TutorialController tc = new TutorialController();
    private Warrior w = new Warrior();
    private Healer h = new Healer();
    private Mage m = new Mage();
    private Ranger r = new Ranger();
    private LevelUpController luc = new LevelUpController();
    private LootController lc = new LootController();
    private ForestCon forestCon = new ForestCon();
    private CaveController caveCon = new CaveController();
    private LoseScreen loseScreen = new LoseScreen();
    private FieldController fieldCon = new FieldController();
    private CaveView cv = new CaveView();

    private int[] warriorStats = new int[3];
    private int[] mageStats = new int[3];
    private int[] healerStats = new int[3];
    private int[] rangerStats = new int[3];

    private int[] warriorLevelUpStats = new int[3];
    private int[] mageLevelUpStats = new int[3];
    private int[] healerLevelUpStats = new int[3];
    private int[] rangerLevelUpStats = new int[3];

    //Array that keeps track of how many potions you own, use this to . 1-3 = Healing potions. 4-6 = block potions. 7-9 = energy potions. 10-12 = str potions.
    private int[] ownedPotions = new int[12];

    private int currentXp;
    private int currentLevel;
    private int currentGold;

    //1=warrior, 2=mage, 3=ranger, 4=healer.
    private String[] armorNames = new String[4];
    private String[] weaponNames = new String[4];
    private int[] weaponDamage = new int[4];
    private int[] armorBlock = new int[4];

    //1=mage, 2=healer.
    private int[] currentArmorDamage = new int[6];

    //1 2 = warrior, 3 4 = mage, 5 6 = ranger, 7 8 = healer.
    private String[] rareWeaponArmorNames = new String[8];
    private String[] epicWeaponArmorNames = new String[8];
    private String[] legendaryWeaponArmorNames = new String[8];

    //1 2 3 = warrior, 4 5 6 = mage, 7 8 9 = ranger, 10 11 12= healer.
    private int[] rareWeaponArmorDamageBlock = new int[18];
    private int[] epicWeaponArmorDamageBlock = new int[18];
    private int[] legendaryWeaponArmorDamageBlock = new int[18];
    private int[] armorDamage= new int[6];

    //TODO i fights, lägg till funktion som tar bort använda potions

    //Get user input from ConHub to start game of exit game.
    public void startGame() throws InterruptedException {

        setStartNumbers();
        hubController.test();

        if (hubController.choice[0] == 1) {
            startWorldModel();
            //startForestFight();
            //startCaveFight();
        }
        else if (hubController.choice[0] == 2) {
            tc.startTutorial();
        }
        else if (hubController.choice[0] == 3) {
            System.exit(0);
        }
    }


    public void startWorldModel() throws InterruptedException {
        WorldModel worldModel = new WorldModel();
        if (worldModel.HandleOverWorld() == 1) {
            startForestFight();
        }
        if (worldModel.HandleOverWorld() == 2) {
            startCaveFight();
        }
        if (worldModel.HandleOverWorld() == 3) {
            startFieldFight();
        }
        if (worldModel.HandleOverWorld() == 4) {
            //startSwampFight();
        }
    }


    public void setStartNumbers(){

        warriorStats[0] = 130; //hp
        warriorStats[1] = 3; //block
        warriorStats[2] = 2; //damage

        rangerStats[0] = 80;
        rangerStats[1] = 0;
        rangerStats[2] = 3;

        mageStats[0] = 70;
        mageStats[1] = 0;
        mageStats[2] = 4;

        healerStats[0] = 100;
        healerStats[1] = 1;
        healerStats[2] = 0;

        currentXp = 0;
        currentLevel = 1;
        currentGold = 20;

        ownedPotions[0] = 1;
        ownedPotions[1] = 0;
        ownedPotions[2] = 0;

        ownedPotions[3] = 1;
        ownedPotions[4] = 0;
        ownedPotions[5] = 0;

        ownedPotions[6] = 1;
        ownedPotions[7] = 0;
        ownedPotions[8] = 0;

        ownedPotions[9] = 1;
        ownedPotions[10] = 0;
        ownedPotions[11] = 0;

        //0 = HP, 1 = block, 2 = damage
        warriorLevelUpStats[0] = 5;
        warriorLevelUpStats[1] = 2;
        warriorLevelUpStats[2] = 2;

        rangerLevelUpStats[0] = 3;
        rangerLevelUpStats[1] = 4;
        rangerLevelUpStats[2] = 2;

        mageLevelUpStats[0] = 2;
        mageLevelUpStats[1] = 6;
        mageLevelUpStats[2] = 1;

        healerLevelUpStats[0] = 5;
        healerLevelUpStats[1] = 2;
        healerLevelUpStats[2] = 3;
    }
    //Fight methods starts here


    //This method imports all stats that the battle will need and start ForestFightController.
    public void startForestFight() throws InterruptedException {
        forestCon.getInventory(ownedPotions);
        forestCon.getPlayerStats(warriorStats,mageStats,healerStats,rangerStats);
        forestCon.startFight();

        loop1();
    }

    public void loop1() throws InterruptedException {
        int loops = 0;
        System.out.println("loop1");
        while (!forestCon.fightWon || !forestCon.fightLost) {
            System.out.println("run 1");
            if (forestCon.fightWon) {
                System.out.println("fightWon");
                startLootController(1);
                break;
            }
            else if (forestCon.fightLost){
                System.out.println("fightLost");
                loseScreen.loseScreen();
                break;
            }
            else if (loops == 100000){
                break;
            }
            loops++;
        }
        loop2();
    }

    public void loop2() throws InterruptedException {
        int loops = 0;
        System.out.println("loop2");

        while (!forestCon.fightWon || !forestCon.fightLost) {
            System.out.println("run 2");

            if (forestCon.fightWon) {
                System.out.println("fightWon");
                startLootController(1);
                break;
            }
            else if (forestCon.fightLost){
                System.out.println("fightLost");
                loseScreen.loseScreen();
                break;
            }
            else if (loops == 100000){
                loop1();
            }
            loops++;
        }
        loop1();
    }


    public void startCaveFight(){
        caveCon.getInventory(ownedPotions);
        caveCon.getPlayerStats(warriorStats,mageStats,healerStats,rangerStats);
        caveCon.startFight();
    }

    public void startFieldFight(){
        /*
        fieldCon.getInventory(ownedPotions);
        getStats();
        fieldCon.getPlayerStats(warriorStats,mageStats,healerStats,rangerStats);
        fieldCon.startFight();
         */
    }

  /*  public void startWorldModel() throws InterruptedException {
        WorldModel worldModel = new WorldModel();
        if (worldModel.HandleOverWorld() == 1) {
            startForestFight();
        }
    }*/

   //Here starts lootFrame stuff

    //This method sends away all information lootController is going to need and starts it.
    public void startLootController(int whatFight) throws InterruptedException {
        getEquipment();
        lc.getInfo(currentGold, currentXp, armorNames, weaponNames, weaponDamage, currentArmorDamage, armorBlock, rareWeaponArmorNames, epicWeaponArmorNames, legendaryWeaponArmorNames, rareWeaponArmorDamageBlock, epicWeaponArmorDamageBlock, legendaryWeaponArmorDamageBlock, armorDamage);

        lc.startLootScreen(whatFight);

        lf.continueButton.addActionListener(e-> {
            try {
                addLoot();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void startLevelUp(){
        luc.didPlayerLevelUp(currentXp, currentLevel,warriorLevelUpStats,rangerLevelUpStats,mageLevelUpStats,healerLevelUpStats,warriorStats,rangerStats,mageStats,healerStats);

        while (true) {
            if (luc.done) {
                break;
            }
        }
    }

    //This method saves gold, xp and weapon/armor that player got from lootController.
    public void addLoot() throws InterruptedException {

        currentGold = lc.goldInt;
        currentXp = lc.xpInt;

        startLevelUp();

        if(lc.playerWantsLoot){
            //Armor & Weapons
            if (lc.whatLoot == 1){
                w.warriorRareArmor();
            }
            else if (lc.whatLoot == 2){
                w.warriorEpicArmor();
            }
            else if (lc.whatLoot == 3){
                w.warriorLegendaryArmor();
            }

            else if (lc.whatLoot == 4){
                w.warriorRareWeapon();
            }
            else if (lc.whatLoot == 5){
                w.warriorEpicWeapon();
            }
            else if (lc.whatLoot == 6){
                w.warriorLegendaryWeapon();
            }

            else if (lc.whatLoot == 7){
                m.mageRareArmor();
            }
            else if (lc.whatLoot == 8){
                m.mageEpicArmor();
            }
            else if (lc.whatLoot == 9){
                m.mageLegendaryArmor();
            }

            else if (lc.whatLoot == 10){
                m.mageRareWeapon();
            }
            else if (lc.whatLoot == 11){
                m.mageEpicWeapon();
            }
            else if (lc.whatLoot == 12){
                m.mageLegendaryWeapon();
            }

            else if (lc.whatLoot == 13){
                r.rangerRareArmor();
            }
            else if (lc.whatLoot == 14){
                r.rangerEpicArmor();
            }
            else if (lc.whatLoot == 15){
                r.rangerLegendaryArmor();
            }

            else if (lc.whatLoot == 16){
                r.rangerRareWeapon();
            }
            else if (lc.whatLoot == 17){
                r.rangerEpicWeapon();
            }
            else if (lc.whatLoot == 18){
                r.rangerLegendaryWeapon();
            }

            else if (lc.whatLoot == 19){
                h.healerRareArmor();
            }
            else if (lc.whatLoot == 20){
                h.healerEpicArmor();
            }
            else if (lc.whatLoot == 21){
                h.healerLegendaryArmor();
            }

            else if (lc.whatLoot == 22){
                h.healerRareWeapon();
            }
            else if (lc.whatLoot == 23){
                h.healerEpicWeapon();
            }
            else if (lc.whatLoot == 24){
                h.healerLegendaryWeapon();
            }
        }
        //Potions
        if (lc.whatLoot == 25){
            ownedPotions[0]++;
        }
        else if (lc.whatLoot == 26){
            ownedPotions[1]++;
        }
        else if (lc.whatLoot == 27){
            ownedPotions[2]++;
        }

        else if (lc.whatLoot == 28){
            ownedPotions[7]++;
        }
        else if (lc.whatLoot == 29){
            ownedPotions[8]++;
        }
        else if (lc.whatLoot == 30){
            ownedPotions[9]++;
        }

        else if (lc.whatLoot == 31){
            ownedPotions[10]++;
        }
        else if (lc.whatLoot == 32){
            ownedPotions[11]++;
        }
        else if (lc.whatLoot == 33){
            ownedPotions[12]++;
        }

        else if (lc.whatLoot == 34){
            ownedPotions[4]++;
        }
        else if (lc.whatLoot == 35){
            ownedPotions[5]++;
        }
        else if (lc.whatLoot == 36){
            ownedPotions[6]++;
        }
    }

    //Collects information about armor, weapons etc.
    private void getEquipment(){
        armorNames[0] = w.currentArmorName;
        armorNames[1] = m.currentArmorName;
        armorNames[2] = r.currentArmorName;
        armorNames[3] = h.currentArmorName;

        weaponNames[0] = w.currentWeaponName;
        weaponNames[1] = m.currentWeaponName;
        weaponNames[2] = r.currentWeaponName;
        weaponNames[3] = h.currentWeaponName;

        armorBlock[0] = w.currentArmorBlock;
        armorBlock[1] = m.currentArmorBlock;
        armorBlock[2] = r.currentArmorBlock;
        armorBlock[3] = h.currentArmorBlock;

        currentArmorDamage[0] = m.currentArmorDamage;
        currentArmorDamage[1] = h.currentArmorDamage;

        weaponDamage[0] = w.currentWeaponDamage;
        weaponDamage[1] = m.currentWeaponDamage;
        weaponDamage[2] = r.currentWeaponDamage;
        weaponDamage[3] = h.currentWeaponDamage;

        rareWeaponArmorNames[0] = w.warriorRareWeaponName;
        rareWeaponArmorNames[1] = w.warriorRareArmorName;
        rareWeaponArmorNames[2] = m.mageRareWeaponName;
        rareWeaponArmorNames[3] = m.mageRareArmorName;
        rareWeaponArmorNames[4] = r.rangerRareWeaponName;
        rareWeaponArmorNames[5] = r.rangerRareArmorName;
        rareWeaponArmorNames[6] = h.healerRareWeaponName;
        rareWeaponArmorNames[7] = h.healerRareArmorName;

        epicWeaponArmorNames[0] = w.warriorEpicWeaponName;
        epicWeaponArmorNames[1] = w.warriorEpicArmorName;
        epicWeaponArmorNames[2] = m.mageEpicWeaponName;
        epicWeaponArmorNames[3] = m.mageEpicArmorName;
        epicWeaponArmorNames[4] = r.rangerEpicWeaponName;
        epicWeaponArmorNames[5] = r.rangerEpicArmorName;
        epicWeaponArmorNames[6] = h.healerEpicWeaponName;
        epicWeaponArmorNames[7] = h.healerEpicArmorName;

        legendaryWeaponArmorNames[0] = w.warriorLegendaryWeaponName;
        legendaryWeaponArmorNames[1] = w.warriorLegendaryArmorName;
        legendaryWeaponArmorNames[2] = m.mageLegendaryWeaponName;
        legendaryWeaponArmorNames[3] = m.mageLegendaryArmorName;
        legendaryWeaponArmorNames[4] = r.rangerLegendaryWeaponName;
        legendaryWeaponArmorNames[5] = r.rangerLegendaryArmorName;
        legendaryWeaponArmorNames[6] = h.healerLegendaryWeaponName;
        legendaryWeaponArmorNames[7] = h.healerLegendaryArmorName;

        rareWeaponArmorDamageBlock[0] = w.warriorRareWeaponDamage;
        rareWeaponArmorDamageBlock[1] = w.warriorRareArmorBlock;
        rareWeaponArmorDamageBlock[2] = m.mageRareWeaponDamage;
        rareWeaponArmorDamageBlock[3] = m.mageRareArmorBlock;
        rareWeaponArmorDamageBlock[4] = r.rangerRareWeaponDamage;
        rareWeaponArmorDamageBlock[5] = r.rangerRareArmorBlock;
        rareWeaponArmorDamageBlock[6] = h.healerRareWeaponDamage;
        rareWeaponArmorDamageBlock[7] = h.healerRareArmorBlock;

        epicWeaponArmorDamageBlock[0] = w.warriorEpicWeaponDamage;
        epicWeaponArmorDamageBlock[1] = w.warriorEpicArmorBlock;
        epicWeaponArmorDamageBlock[2] = m.mageEpicWeaponDamage;
        epicWeaponArmorDamageBlock[3] = m.mageEpicArmorBlock;
        epicWeaponArmorDamageBlock[4] = r.rangerEpicWeaponDamage;
        epicWeaponArmorDamageBlock[5] = r.rangerEpicArmorBlock;
        epicWeaponArmorDamageBlock[6] = h.healerEpicWeaponDamage;
        epicWeaponArmorDamageBlock[7] = h.healerEpicArmorBlock;

        legendaryWeaponArmorDamageBlock[0] = w.warriorLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[1] = w.warriorLegendaryArmorBlock;
        legendaryWeaponArmorDamageBlock[2] = m.mageLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[3] = m.mageLegendaryArmorBlock;
        legendaryWeaponArmorDamageBlock[4] = r.rangerLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[5] = r.rangerLegendaryArmorBlock;
        legendaryWeaponArmorDamageBlock[6] = h.healerLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[7] = h.healerLegendaryArmorBlock;

        armorDamage[0] = m.mageRareArmorDamage;
        armorDamage[1] = m.mageEpicArmorDamage;
        armorDamage[2] = m.mageLegendaryArmorDamage;
        armorDamage[3] = h.healerRareArmorDamage;
        armorDamage[4] = h.healerEpicArmorDamage;
        armorDamage[5] = h.healerLegendaryArmorDamage;
    }
}