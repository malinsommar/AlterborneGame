package game;

import davidtest.overworld.map.WorldController;
import davidtest.overworld.map.WorldModel;
import fight.CaveController;
import fight.FieldController;
import fight.ForestCon;

public class MasterModel {

    private HubController hubController = new HubController();
    private TutorialController tc = new TutorialController();
    private LevelUpController luc = new LevelUpController();
    private LootController lc = new LootController();
    private ForestCon forestCon = new ForestCon();
    private CaveController caveCon = new CaveController();
    private LoseScreen loseScreen = new LoseScreen();
    private FieldController fieldCon = new FieldController();
    private ShopController sc = new ShopController();


    private int[] warriorStats = new int[3];
    private int[] mageStats = new int[3];
    private int[] healerStats = new int[3];
    private int[] rangerStats = new int[3];
    private int[] warriorLevelUpStats = new int[3];
    private int[] mageLevelUpStats = new int[3];
    private int[] healerLevelUpStats = new int[3];
    private int[] rangerLevelUpStats = new int[3];

    //Warrior weapon/armor things

    private String warriorRareWeaponName = "Iron sword", warriorEpicWeaponName = "Tempered steel blade", warriorLegendaryWeaponName = "Sword of a thousand truths", warriorRareArmorName = "Shiny Armor", warriorEpicArmorName = "Hardened Armor", warriorLegendaryArmorName = "Royal Enchanted Armor";
    private int warriorRareWeaponDamage = 8, warriorEpicWeaponDamage = 15, warriorLegendaryWeaponDamage = 27, warriorRareArmorBlock = 6, warriorEpicArmorBlock = 10, warriorLegendaryArmorBlock;

    private String currentWarriorWeaponName = "Wooden Sword";
    private int currentWarriorWeaponDamage = 5;

    private String currentWarriorArmorName = "Rusty Armor";
    private int currentWarriorArmorBlock = 3;

    //Ranger weapon/armor things

    private String rangerRareWeaponName = "Elven bow", rangerEpicWeaponName = "Dragonslayer's bow", rangerLegendaryWeaponName = "Bullseye bow", rangerRareArmorName = "Fine leather armor", rangerEpicArmorName = "Elven leather armor", rangerLegendaryArmorName = "Demonskin armor";
    private int rangerRareWeaponDamage = 8, rangerEpicWeaponDamage = 16, rangerLegendaryWeaponDamage = 26, rangerRareArmorBlock = 5, rangerEpicArmorBlock = 11, rangerLegendaryArmorBlock = 18;

    private String currentRangerWeaponName = "Simple bow";
    private int currentRangerWeaponDamage = 5;

    private String currentRangerArmorName = "Broken leather armor";
    private int currentRangerArmorBlock = 0;

    //Mage weapon/armor stuff

    private String mageRareWeaponName = "Ivory fire wand", mageEpicWeaponName = "Enchanted mana wand", mageLegendaryWeaponName = "Pyromaniac's tinderbox", mageRareArmorName = "Mooncloth robe", mageEpicArmorName = "Enchanted robe", mageLegendaryArmorName = "Robe of the archmage";
    private int mageRareWeaponDamage = 7, mageEpicWeaponDamage = 15, mageLegendaryWeaponDamage = 30, mageRareArmorBlock = 2, mageEpicArmorBlock = 4, mageLegendaryArmorBlock = 6, mageRareArmorDamage = 4, mageEpicArmorDamage = 10, mageLegendaryArmorDamage = 20;

    private String currentMageWeaponName = "Wooden wand";
    private int currentMageWeaponDamage = 5;

    private String currentMageArmorName = "Cloth robe";
    private int currentMageArmorBlock = 0;
    private int currentMageArmorDamage = 0;

    //Healer weapon/armor stuff

    private String healerRareWeaponName = "Stick of truth", healerEpicWeaponName = "Cleric's blessed walking stick", healerLegendaryWeaponName = "Root of the world tree", healerRareArmorName = "Priests robe", healerEpicArmorName = "Clerics armor", healerLegendaryArmorName = "Plate armor of Parl'ont the crusader";
    private int healerRareWeaponDamage = 7, healerEpicWeaponDamage = 13, healerLegendaryWeaponDamage = 23, healerRareArmorBlock = 2, healerEpicArmorBlock = 8, healerLegendaryArmorBlock = 13, healerRareArmorDamage = 3, healerEpicArmorDamage = 7, healerLegendaryArmorDamage = 15;

    private String currentHealerWeaponName = "Wooden staff";
    private int currentHealerWeaponDamage = 5;

    private String currentHealerArmorName = "Cloth scraps";
    private int currentHealerArmorBlock = 5;
    private int currentHealerArmorDamage = 0;

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
    private int[] armorDamage = new int[6];


    //Get user input from ConHub to start game of exit game.
    void startGame() throws InterruptedException {

        setStartNumbers();
        hubController.test();

        if (hubController.choice[0] == 1) {
            startWorldModel();
        } else if (hubController.choice[0] == 2) {
            tc.startTutorial();
        } else if (hubController.choice[0] == 3) {
            System.exit(0);
        }
    }

    public void startWorldModel() throws InterruptedException {
        WorldModel worldModel = new WorldModel();
        if (worldModel.HandleOverWorld() == 1) {
            startShop();
        }
        if (worldModel.HandleOverWorld() == 2) {
            startForestFight();
        }
        if (worldModel.HandleOverWorld() == 3) {
            startCaveFight();
        }
        if (worldModel.HandleOverWorld() == 4) {
            startFieldFight();
        }
        if (worldModel.HandleOverWorld() == 5) {
            //startSwampFight();
        }
        if (worldModel.HandleOverWorld() == 6) {
            //startCastleFight
        }
    }

    private void setStartNumbers() {

        warriorStats[0] = 130;
        warriorStats[1] = 3 + currentWarriorArmorBlock;
        warriorStats[2] = 2 + currentWarriorWeaponDamage;

        rangerStats[0] = 80;
        rangerStats[1] = 0 + currentRangerArmorBlock;
        rangerStats[2] = 3 + currentRangerWeaponDamage;

        mageStats[0] = 70;
        mageStats[0] = 0 + currentMageArmorBlock;
        mageStats[0] = 4 +currentMageArmorDamage + currentMageWeaponDamage;

        healerStats[0] = 100;
        healerStats[1] = 1 + currentHealerArmorBlock;
        healerStats[2] = 0 +currentHealerArmorDamage + currentHealerWeaponDamage;
        

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
        forestCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        forestCon.startFight();

        forestFightLoop1();
    }

    public void startCaveFight() {
        caveCon.getInventory(ownedPotions);
        caveCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        caveCon.startFight();
    }

    public void startFieldFight() {

    }

  /*  private void startWorldModel() throws InterruptedException {
        WorldModel worldModel = new WorldModel();
        if (worldModel.HandleOverWorld() == 1) {
            startForestFight();
        }
    }*/
  
  public void startShop() throws InterruptedException {
      sc.startShopView(currentGold, armorNames, weaponNames, weaponDamage, currentArmorDamage, armorBlock, rareWeaponArmorNames, epicWeaponArmorNames, legendaryWeaponArmorNames, rareWeaponArmorDamageBlock, epicWeaponArmorDamageBlock, legendaryWeaponArmorDamageBlock, armorDamage);

      shopLoop1();
  }

    //Here starts lootFrame stuff

    //This method sends away all information lootController is going to need and starts it.
    private void startLootController(int whatFight) throws InterruptedException {
        getEquipment();
        lc.getInfo(currentGold, currentXp, armorNames, weaponNames, weaponDamage, currentArmorDamage, armorBlock, rareWeaponArmorNames, epicWeaponArmorNames, legendaryWeaponArmorNames, rareWeaponArmorDamageBlock, epicWeaponArmorDamageBlock, legendaryWeaponArmorDamageBlock, armorDamage);

        lc.startLootScreen(whatFight);

        lootLoop1();
    }

    private void startLevelUp() throws InterruptedException {
        luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
        levelUpLoop1();
    }
    
    private void levelUpStats(){

        warriorStats[0] = luc.warriorHp;
        warriorStats[1] = luc.warriorBlock;
        warriorStats[2] = luc.warriorDamage;

        mageStats[0] = luc.mageHp;
        mageStats[1] = luc.mageBlock;
        mageStats[2] = luc.mageDamage;

        rangerStats[0] = luc.rangerHp;
        rangerStats[1] = luc.rangerBlock;
        rangerStats[2] = luc.rangerDamage;

        healerStats[0] = luc.healerHp;
        healerStats[1] = luc.healerBlock;
        healerStats[2] = luc.healerDamage;
    }

    //This method saves gold, xp and weapon/armor that player got from lootController.
    private void addLoot() throws InterruptedException {

        currentGold = lc.goldInt;
        currentXp = lc.xpInt;
        startLevelUp();

        if (lc.playerWantsLoot) {
            //Armor & Weapons
            if (lc.whatLoot == 1) {
                warriorRareArmor();
            } else if (lc.whatLoot == 2) {
                warriorEpicArmor();
            } else if (lc.whatLoot == 3) {
                warriorLegendaryArmor();
            } else if (lc.whatLoot == 4) {
                warriorRareWeapon();
            } else if (lc.whatLoot == 5) {
                warriorEpicWeapon();
            } else if (lc.whatLoot == 6) {
                warriorLegendaryWeapon();
            } else if (lc.whatLoot == 7) {
                mageRareArmor();
            } else if (lc.whatLoot == 8) {
                mageEpicArmor();
            } else if (lc.whatLoot == 9) {
                mageLegendaryArmor();
            } else if (lc.whatLoot == 10) {
                mageRareWeapon();
            } else if (lc.whatLoot == 11) {
                mageEpicWeapon();
            } else if (lc.whatLoot == 12) {
                mageLegendaryWeapon();
            } else if (lc.whatLoot == 13) {
                rangerRareArmor();
            } else if (lc.whatLoot == 14) {
                rangerEpicArmor();
            } else if (lc.whatLoot == 15) {
                rangerLegendaryArmor();
            } else if (lc.whatLoot == 16) {
                rangerRareWeapon();
            } else if (lc.whatLoot == 17) {
                rangerEpicWeapon();
            } else if (lc.whatLoot == 18) {
                rangerLegendaryWeapon();
            } else if (lc.whatLoot == 19) {
                healerRareArmor();
            } else if (lc.whatLoot == 20) {
                healerEpicArmor();
            } else if (lc.whatLoot == 21) {
                healerLegendaryArmor();
            } else if (lc.whatLoot == 22) {
                healerRareWeapon();
            } else if (lc.whatLoot == 23) {
                healerEpicWeapon();
            } else if (lc.whatLoot == 24) {
                healerLegendaryWeapon();
            }
        }
        //Potions
        if (lc.whatLoot == 25) {
            ownedPotions[0]++;
        } else if (lc.whatLoot == 26) {
            ownedPotions[1]++;
        } else if (lc.whatLoot == 27) {
            ownedPotions[2]++;
        } else if (lc.whatLoot == 28) {
            ownedPotions[7]++;
        } else if (lc.whatLoot == 29) {
            ownedPotions[8]++;
        } else if (lc.whatLoot == 30) {
            ownedPotions[9]++;
        } else if (lc.whatLoot == 31) {
            ownedPotions[10]++;
        } else if (lc.whatLoot == 32) {
            ownedPotions[11]++;
        } else if (lc.whatLoot == 33) {
            ownedPotions[12]++;
        } else if (lc.whatLoot == 34) {
            ownedPotions[4]++;
        } else if (lc.whatLoot == 35) {
            ownedPotions[5]++;
        } else if (lc.whatLoot == 36) {
            ownedPotions[6]++;
        }
        startWorldModel();
    }

    //Collects information about armor, weapons etc.
    private void getEquipment() {
        armorNames[0] = currentWarriorArmorName;
        armorNames[1] = currentMageArmorName;
        armorNames[2] = currentRangerArmorName;
        armorNames[3] = currentHealerArmorName;

        weaponNames[0] = currentWarriorWeaponName;
        weaponNames[1] = currentMageWeaponName;
        weaponNames[2] = currentRangerWeaponName;
        weaponNames[3] = currentHealerWeaponName;

        armorBlock[0] = currentWarriorArmorBlock;
        armorBlock[1] = currentMageArmorBlock;
        armorBlock[2] = currentRangerArmorBlock;
        armorBlock[3] = currentHealerArmorBlock;

        currentArmorDamage[0] = currentMageArmorDamage;
        currentArmorDamage[1] = currentHealerArmorDamage;

        weaponDamage[0] = currentWarriorWeaponDamage;
        weaponDamage[1] = currentMageWeaponDamage;
        weaponDamage[2] = currentRangerWeaponDamage;
        weaponDamage[3] = currentHealerWeaponDamage;

        rareWeaponArmorNames[0] = warriorRareWeaponName;
        rareWeaponArmorNames[1] = warriorRareArmorName;
        rareWeaponArmorNames[2] = mageRareWeaponName;
        rareWeaponArmorNames[3] = mageRareArmorName;
        rareWeaponArmorNames[4] = rangerRareWeaponName;
        rareWeaponArmorNames[5] = rangerRareArmorName;
        rareWeaponArmorNames[6] = healerRareWeaponName;
        rareWeaponArmorNames[7] = healerRareArmorName;

        epicWeaponArmorNames[0] = warriorEpicWeaponName;
        epicWeaponArmorNames[1] = warriorEpicArmorName;
        epicWeaponArmorNames[2] = mageEpicWeaponName;
        epicWeaponArmorNames[3] = mageEpicArmorName;
        epicWeaponArmorNames[4] = rangerEpicWeaponName;
        epicWeaponArmorNames[5] = rangerEpicArmorName;
        epicWeaponArmorNames[6] = healerEpicWeaponName;
        epicWeaponArmorNames[7] = healerEpicArmorName;

        legendaryWeaponArmorNames[0] = warriorLegendaryWeaponName;
        legendaryWeaponArmorNames[1] = warriorLegendaryArmorName;
        legendaryWeaponArmorNames[2] = mageLegendaryWeaponName;
        legendaryWeaponArmorNames[3] = mageLegendaryArmorName;
        legendaryWeaponArmorNames[4] = rangerLegendaryWeaponName;
        legendaryWeaponArmorNames[5] = rangerLegendaryArmorName;
        legendaryWeaponArmorNames[6] = healerLegendaryWeaponName;
        legendaryWeaponArmorNames[7] = healerLegendaryArmorName;

        rareWeaponArmorDamageBlock[0] = warriorRareWeaponDamage;
        rareWeaponArmorDamageBlock[1] = warriorRareArmorBlock;
        rareWeaponArmorDamageBlock[2] = mageRareWeaponDamage;
        rareWeaponArmorDamageBlock[3] = mageRareArmorBlock;
        rareWeaponArmorDamageBlock[4] = rangerRareWeaponDamage;
        rareWeaponArmorDamageBlock[5] = rangerRareArmorBlock;
        rareWeaponArmorDamageBlock[6] = healerRareWeaponDamage;
        rareWeaponArmorDamageBlock[7] = healerRareArmorBlock;

        epicWeaponArmorDamageBlock[0] = warriorEpicWeaponDamage;
        epicWeaponArmorDamageBlock[1] = warriorEpicArmorBlock;
        epicWeaponArmorDamageBlock[2] = mageEpicWeaponDamage;
        epicWeaponArmorDamageBlock[3] = mageEpicArmorBlock;
        epicWeaponArmorDamageBlock[4] = rangerEpicWeaponDamage;
        epicWeaponArmorDamageBlock[5] = rangerEpicArmorBlock;
        epicWeaponArmorDamageBlock[6] = healerEpicWeaponDamage;
        epicWeaponArmorDamageBlock[7] = healerEpicArmorBlock;

        legendaryWeaponArmorDamageBlock[0] = warriorLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[1] = warriorLegendaryArmorBlock;
        legendaryWeaponArmorDamageBlock[2] = mageLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[3] = mageLegendaryArmorBlock;
        legendaryWeaponArmorDamageBlock[4] = rangerLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[5] = rangerLegendaryArmorBlock;
        legendaryWeaponArmorDamageBlock[6] = healerLegendaryWeaponDamage;
        legendaryWeaponArmorDamageBlock[7] = healerLegendaryArmorBlock;

        armorDamage[0] = mageRareArmorDamage;
        armorDamage[1] = mageEpicArmorDamage;
        armorDamage[2] = mageLegendaryArmorDamage;
        armorDamage[3] = healerRareArmorDamage;
        armorDamage[4] = healerEpicArmorDamage;
        armorDamage[5] = healerLegendaryArmorDamage;
    }

    private void addLootFromShop(int whatLoot){
        if (whatLoot == 1){
            warriorRareArmor();
        }
        else if (whatLoot == 2){
            warriorEpicArmor();
        }
        else if (whatLoot == 3){
            warriorLegendaryArmor();
        }
        else if (whatLoot == 4){
            warriorRareWeapon();
        }
        else if (whatLoot == 5){
            warriorEpicWeapon();
        }
        else if (whatLoot == 6){
            warriorLegendaryWeapon();
        }
        else if (whatLoot == 7){
            mageRareArmor();
        }
        else if (whatLoot == 8){
            mageEpicArmor();
        }
        else if (whatLoot == 9){
            mageLegendaryArmor();
        }
        else if (whatLoot == 10){
            mageRareWeapon();
        }
        else if (whatLoot == 11){
            mageEpicWeapon();
        }
        else if (whatLoot == 12){
            mageLegendaryWeapon();
        }
        else if (whatLoot == 13){
            rangerRareArmor();
        }
        else if (whatLoot == 14){
            rangerEpicArmor();
        }
        else if (whatLoot == 15){
            rangerLegendaryArmor();
        }
        else if (whatLoot == 16){
            rangerRareWeapon();
        }
        else if (whatLoot == 17){
            rangerEpicWeapon();
        }
        else if (whatLoot == 18){
            rangerLegendaryWeapon();
        }
        else if (whatLoot == 19){
            healerRareArmor();
        }
        else if (whatLoot == 20){
            healerEpicArmor();
        }
        else if (whatLoot == 21){
            healerLegendaryArmor();
        }
        else if (whatLoot == 22){
            healerRareWeapon();
        }
        else if (whatLoot == 23){
            healerEpicWeapon();
        }
        else if (whatLoot == 24){
            healerLegendaryWeapon();
        }
        //Potions
        if (whatLoot == 25){
            ownedPotions[0]++;
        }
        else if (whatLoot == 26){
            ownedPotions[2]++;
        }
        else if (whatLoot == 27){
            ownedPotions[3]++;
        }
        else if (whatLoot == 28){
            ownedPotions[7]++;
        }
        else if (whatLoot == 29){
            ownedPotions[8]++;
        }
        else if (whatLoot == 30){
            ownedPotions[9]++;
        }
        else if (whatLoot == 31){
            ownedPotions[10]++;
        }
        else if (whatLoot == 32){
            ownedPotions[11]++;
        }
        else if (whatLoot == 33){
            ownedPotions[12]++;
        }
        else if (whatLoot == 34){
            ownedPotions[4]++;
        }
        else if (whatLoot == 35){
            ownedPotions[5]++;
        }
        else if (whatLoot == 36){
            ownedPotions[6]++;
        }
    }

    //Loops

    private void forestFightLoop1() throws InterruptedException {
        int loops = 0;
        boolean broken = false;

        System.out.println("loop1");
        while (true) {
            System.out.println("run 1");

            if (forestCon.fightWon) {
                System.out.println("fightWon");

                forestCon.ownedPotions[0] = ownedPotions[0];
                forestCon.ownedPotions[1] = ownedPotions[1];
                forestCon.ownedPotions[2] = ownedPotions[2];

                forestCon.ownedPotions[3] = ownedPotions[3];
                forestCon.ownedPotions[4] = ownedPotions[4];
                forestCon.ownedPotions[5] = ownedPotions[5];

                forestCon.ownedPotions[6] = ownedPotions[6];
                forestCon.ownedPotions[7] = ownedPotions[7];
                forestCon.ownedPotions[8] = ownedPotions[8];

                forestCon.ownedPotions[9] = ownedPotions[9];
                forestCon.ownedPotions[10] = ownedPotions[10];
                forestCon.ownedPotions[11] = ownedPotions[11];

                startLootController(1);
                broken = true;
                break;
            } else if (forestCon.fightLost) {
                System.out.println("fightLost");
                loseScreen.loseScreen();
                broken = true;
                break;
            } else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            forestFightLoop2();
        }
    }

    private void forestFightLoop2() throws InterruptedException {
        int loops = 0;
        boolean broken = false;
        System.out.println("loop2");

        while (true) {
            System.out.println("run 2");

            if (forestCon.fightWon) {
                System.out.println("fightWon");

                forestCon.ownedPotions[0] = ownedPotions[0];
                forestCon.ownedPotions[1] = ownedPotions[1];
                forestCon.ownedPotions[2] = ownedPotions[2];

                forestCon.ownedPotions[3] = ownedPotions[3];
                forestCon.ownedPotions[4] = ownedPotions[4];
                forestCon.ownedPotions[5] = ownedPotions[5];

                forestCon.ownedPotions[6] = ownedPotions[6];
                forestCon.ownedPotions[7] = ownedPotions[7];
                forestCon.ownedPotions[8] = ownedPotions[8];

                forestCon.ownedPotions[9] = ownedPotions[9];
                forestCon.ownedPotions[10] = ownedPotions[10];
                forestCon.ownedPotions[11] = ownedPotions[11];

                startLootController(1);
                broken = true;
                break;
            } else if (forestCon.fightLost) {
                System.out.println("fightLost");
                loseScreen.loseScreen();
                broken = true;
                break;
            } else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            forestFightLoop1();
        }
    }

    private void addShopItems(){

    }


    private void levelUpLoop1() throws InterruptedException {

        int loops = 0;
        boolean broken = false;
        System.out.println("loop1");

        while (true) {
            System.out.println("running loop 1");

            if (luc.done) {
                System.out.println("xp ready");
                levelUpStats();
                broken = true;
                break;
            } else if (luc.playerNotLevelUp) {
                System.out.println("Didnt level up");
                broken = true;
                break;
            } else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            levelUpLoop2();
        }
    }

    private void levelUpLoop2() throws InterruptedException {

        int loops = 0;
        boolean broken = false;
        System.out.println("loop1");

        while (true) {
            System.out.println("running loop 1");

            if (lc.done) {
                System.out.println("loot ready, start loot");
                levelUpStats();
                broken = true;
                break;
            } else if (luc.playerNotLevelUp) {
                System.out.println("Didnt level up");
                broken = true;
                break;
            } else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            levelUpLoop1();
        }
    }
    
    private void shopLoop1() throws InterruptedException {
        int loops = 0;
        boolean broken = false;
        System.out.println("ShopLoop1");

        while (true) {
            System.out.println("running loop 1");

            if (sc.done) {
                System.out.println("loot ready, start loot");
                addShopItems();
                broken = true;
                break;
            }
            else if (sc.itemBought){
                addLootFromShop(sc.whatItemBought);
                currentGold = sc.currentGold;
                sc.itemBought = false;
            }
            else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            shopLoop2();
        }
        sc.done = false;
        startWorldModel();
    }

    private void shopLoop2() throws InterruptedException {
        int loops = 0;
        boolean broken = false;
        System.out.println("ShopLoop2");

        while (true) {
            System.out.println("running loop 2");

            if (sc.done) {
                System.out.println("loot ready, start loot");
                broken = true;
                break;
            }
            else if (sc.itemBought){
                addLootFromShop(sc.whatItemBought);
                currentGold = sc.currentGold;
                sc.itemBought = false;
            }
            else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            shopLoop1();
        }
        sc.done = false;
        startWorldModel();
    }

    private void lootLoop1() throws InterruptedException {
        int loops = 0;
        boolean broken = false;
        System.out.println("loop1");

        while (true) {
            System.out.println("running loop 1");

            if (lc.done) {
                System.out.println("loot ready, start loot");
                addLoot();
                broken = true;
                break;
            } else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            lootLoop2();
        }
        lc.done = false;
    }

    private void lootLoop2() throws InterruptedException {

        int loops = 0;
        boolean broken = false;
        System.out.println("loop2");

        while (true) {
            System.out.println("running loop 2");

            if (lc.done) {
                System.out.println("loot ready, start loot");
                addLoot();
                broken = true;
                break;
            } else if (loops == 100000) {
                break;
            }
            loops++;
        }
        if (!broken) {
            lootLoop1();
        }
        lc.done = false;
    }

    //Equip weapon/armors

    private void warriorRareWeapon() {
        currentWarriorWeaponName = warriorRareWeaponName;
        currentWarriorWeaponDamage = warriorRareWeaponDamage;
    }

    private void warriorEpicWeapon() {
        currentWarriorWeaponName = warriorEpicWeaponName;
        currentWarriorWeaponDamage = warriorEpicWeaponDamage;
    }

    private void warriorLegendaryWeapon() {
        currentWarriorWeaponName = warriorLegendaryWeaponName;
        currentWarriorWeaponDamage = warriorLegendaryWeaponDamage;
    }

    private void warriorRareArmor() {
        currentWarriorArmorName = warriorRareArmorName;
        currentWarriorArmorBlock = warriorRareArmorBlock;
    }

    private void warriorEpicArmor() {
        currentWarriorArmorName = warriorEpicArmorName;
        currentWarriorArmorBlock = warriorRareArmorBlock;
    }

    private void warriorLegendaryArmor() {
        currentWarriorArmorName = warriorLegendaryArmorName;
        currentWarriorArmorBlock = warriorLegendaryArmorBlock;
    }

    private void rangerRareWeapon() {
        currentRangerWeaponName = rangerRareWeaponName;
        currentRangerWeaponDamage = rangerRareWeaponDamage;
    }

    private void rangerEpicWeapon() {
        currentRangerWeaponName = rangerEpicWeaponName;
        currentRangerWeaponDamage = rangerEpicWeaponDamage;
    }

    private void rangerLegendaryWeapon() {
        currentRangerWeaponName = rangerLegendaryWeaponName;
        currentRangerWeaponDamage = rangerLegendaryWeaponDamage;
    }

    private void rangerRareArmor() {
        currentRangerArmorName = rangerRareArmorName;
        currentRangerArmorBlock = rangerRareArmorBlock;
    }

    private void rangerEpicArmor() {
        currentRangerArmorName = rangerEpicArmorName;
        currentRangerArmorBlock = rangerEpicArmorBlock;
    }

    private void rangerLegendaryArmor() {
        currentRangerArmorName = rangerLegendaryArmorName;
        currentRangerArmorBlock = rangerLegendaryArmorBlock;
    }

    private void mageRareWeapon() {
        currentMageWeaponName = mageRareWeaponName;
        currentMageWeaponDamage = mageRareWeaponDamage;
    }

    private void mageEpicWeapon() {
        currentMageWeaponName = mageEpicWeaponName;
        currentMageWeaponDamage = mageEpicWeaponDamage;
    }

    private void mageLegendaryWeapon() {
        currentMageWeaponName = mageLegendaryWeaponName;
        currentMageWeaponDamage = mageLegendaryWeaponDamage;
    }

    private void mageRareArmor() {
        currentMageArmorName = mageRareArmorName;
        currentMageArmorBlock = mageRareArmorBlock;
        currentMageArmorDamage = mageRareArmorDamage;

    }

    private void mageEpicArmor() {
        currentMageArmorName = mageEpicArmorName;
        currentMageArmorBlock = mageEpicArmorBlock;
        currentMageArmorDamage = mageEpicArmorDamage;
    }

    private void mageLegendaryArmor() {
        currentMageArmorName = mageLegendaryArmorName;
        currentMageArmorBlock = mageLegendaryArmorBlock;
        currentMageArmorDamage = mageLegendaryArmorDamage;
    }

    private void healerRareWeapon() {
        currentHealerWeaponName = healerRareWeaponName;
        currentHealerWeaponDamage = healerRareWeaponDamage;
    }

    private void healerEpicWeapon() {
        currentHealerWeaponName = healerEpicWeaponName;
        currentHealerWeaponDamage = healerEpicWeaponDamage;
    }

    private void healerLegendaryWeapon() {
        currentHealerWeaponName = healerLegendaryWeaponName;
        currentHealerWeaponDamage = healerLegendaryWeaponDamage;
    }

    //All healer armors
    private void healerRareArmor() {
        currentHealerArmorName = healerRareArmorName;
        currentHealerArmorBlock = healerRareArmorBlock;
        currentHealerArmorDamage = healerRareArmorDamage;
    }

    private void healerEpicArmor() {
        currentHealerArmorName = healerEpicArmorName;
        currentHealerArmorBlock = healerEpicArmorBlock;
        currentHealerArmorDamage = healerEpicArmorDamage;
    }

    private void healerLegendaryArmor() {
        currentHealerArmorName = healerLegendaryArmorName;
        currentHealerArmorBlock = healerLegendaryArmorBlock;
        currentHealerArmorDamage = healerLegendaryArmorDamage;
    }
}