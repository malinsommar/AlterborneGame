
package game;

import davidtest.overworld.map.WorldModel;
import fight.*;

/**
 * This is the "main class", this is where all variables that needs to be saved between fight are stored. <br>
 * This is also where most of the methods that generate loot, gold, xp, fight etc is located.
 *
 * @author Malin Sommar
 * @version 1
 */
public class MasterModel {

    private HubController hubController = new HubController();
    private TutorialController tc = new TutorialController();
    private LevelUpController luc = new LevelUpController();
    private LootController lc = new LootController();
    private ForestCon forestCon = new ForestCon();
    private ForestBossCon forestBossCon = new ForestBossCon();
    private CaveController caveCon = new CaveController();
    private CaveBossController caveBossCon = new CaveBossController();
    private FieldController fieldCon = new FieldController();
    private FieldBossController fieldBossCon = new FieldBossController();
    private SwampController swampcon = new SwampController();
    private SwampBossController swampbosscon = new SwampBossController();
    private CastleController castleCon = new CastleController();
    private CastleBossController castleBossCon = new CastleBossController();
    private ShopController sc = new ShopController();
    private DataBase dataBase = new DataBase();
    private UserNameController unc = new UserNameController();
    private LoseView loseView = new LoseView();

    boolean done = false;

    private int[] warriorStats = new int[3];
    private int[] mageStats = new int[3];
    private int[] healerStats = new int[3];
    private int[] rangerStats = new int[3];
    private int[] warriorLevelUpStats = new int[3];
    private int[] mageLevelUpStats = new int[3];
    private int[] healerLevelUpStats = new int[3];
    private int[] rangerLevelUpStats = new int[3];

    private static String userName;

    //Warrior weapon/armor things
    private String warriorRareWeaponName = "Iron sword", warriorEpicWeaponName = "Tempered steel blade", warriorLegendaryWeaponName = "Sword of a thousand truths", warriorRareArmorName = "Shiny Armor", warriorEpicArmorName = "Hardened Armor", warriorLegendaryArmorName = "Royal Enchanted Armor";
    private int warriorRareWeaponDamage = 8, warriorEpicWeaponDamage = 15, warriorLegendaryWeaponDamage = 27, warriorRareArmorBlock = 6, warriorEpicArmorBlock = 10, warriorLegendaryArmorBlock = 20;

    private String currentWarriorWeaponName = "Wooden Sword";
    private int currentWarriorWeaponDamage = 3;

    private String currentWarriorArmorName = "Rusty Armor";
    private int currentWarriorArmorBlock = 3;

    //Ranger weapon/armor things
    private String rangerRareWeaponName = "Elven bow", rangerEpicWeaponName = "Dragonslayer's bow", rangerLegendaryWeaponName = "Bullseye bow", rangerRareArmorName = "Fine leather armor", rangerEpicArmorName = "Elven leather armor", rangerLegendaryArmorName = "Demonskin armor";
    private int rangerRareWeaponDamage = 8, rangerEpicWeaponDamage = 16, rangerLegendaryWeaponDamage = 26, rangerRareArmorBlock = 5, rangerEpicArmorBlock = 11, rangerLegendaryArmorBlock = 18;

    private String currentRangerWeaponName = "Simple bow";
    private int currentRangerWeaponDamage = 4;

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
    private int currentHealerWeaponDamage = 2;

    private String currentHealerArmorName = "Cloth scraps";
    private int currentHealerArmorBlock = 4;
    private int currentHealerArmorDamage = 0;

    //Array that keeps track of how many potions you own.(Minor, lesser, major) 1-3 = Healing potions. 4-6 = block potions. 7-9 = energy potions. 10-12 = str potions.
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

    /**
     * Open hubController. Start masterLoop and await changes to start game, tutorial or exit game.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    void startGame() throws InterruptedException {
        setStartNumbers();
        hubController.startHubScreen();
        masterLoop1();
    }

    /**
     * Open userNameController. Start masterLoop and await actionListener to get userName. UserName is used in highscore.<br>
     * When this is done, call startWorldModel();
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    private void enterUserName() throws InterruptedException {
        unc.startUserNameFrame();

        masterLoop1();

        userName = unc.nameField.getText();
        startWorldModel();
    }

    /**
     * Open WorldModel and await position from player. Check position and open the fight or shop that is connected to the position.<br>
     * To most fights there is a 10% chance to encounter a boss fight. Generate an int between 1-100, it the int is < 10 player will encounter boss.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    private void startWorldModel() throws InterruptedException {
        WorldModel worldModel = new WorldModel(userName);
        int ran = (int)(Math.random()*100)+1;

        //Shop
        if (worldModel.HandleOverWorld() == 1) {
            startShop();
        }
        //Forest
        else if (worldModel.HandleOverWorld() == 2) {

            if(ran > 10){
                startForestFight();
            }
            else {
                startForestBossFight();
            }
        }
        //Cave
        else if (worldModel.HandleOverWorld() == 3) {

            if(ran > 10){
                startCaveFight();
            }
            else {
                startCaveBossFight();
            }
        }
        //Field
        else if (worldModel.HandleOverWorld() == 4) {
            if(ran > 10){
                startFieldFight();
            }
            else {
                startFieldBossFight();
            }
        }
        //Swamp
        else if (worldModel.HandleOverWorld() == 5) {
            if(ran > 10){
                startSwampFight();
            }
            else {
                startSwampBossFight();
            }
        }
        //Castle
        else if (worldModel.HandleOverWorld() == 6) {
            if(ran > 10){
                startCastleFight();
            }
            else {
                startCastleBossFight();
            }
        }
    }

    /**
     * This method is called when player encounter the normal enemies in forest. Send ownedPotions and playerStats to forestController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the forest fight and send necessary variables to ForestController.
    private void startForestFight() throws InterruptedException {
        forestCon.getInventory(ownedPotions);
        forestCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        forestCon.startFight();

        masterLoop1();

        forestCon.fightWon = false;

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
    }

    /**
     *This method is called when player encounter the boss in forest. Send ownedPotions and playerStats to forestBossController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the forest boss fight and send necessary variables to the controller.
    private void startForestBossFight() throws InterruptedException {
        forestBossCon.getInventory(ownedPotions);
        forestBossCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        forestBossCon.startFight();

        masterLoop1();

        forestBossCon.fightWon = false;

        forestBossCon.ownedPotions[0] = ownedPotions[0];
        forestBossCon.ownedPotions[1] = ownedPotions[1];
        forestBossCon.ownedPotions[2] = ownedPotions[2];

        forestBossCon.ownedPotions[3] = ownedPotions[3];
        forestBossCon.ownedPotions[4] = ownedPotions[4];
        forestBossCon.ownedPotions[5] = ownedPotions[5];

        forestBossCon.ownedPotions[6] = ownedPotions[6];
        forestBossCon.ownedPotions[7] = ownedPotions[7];
        forestBossCon.ownedPotions[8] = ownedPotions[8];

        forestBossCon.ownedPotions[9] = ownedPotions[9];
        forestBossCon.ownedPotions[10] = ownedPotions[10];
        forestBossCon.ownedPotions[11] = ownedPotions[11];

        startLootController(10);
    }

    /**
     * This method is called when player encounter the normal enemies in cave. Send ownedPotions and playerStats to caveController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the cave fight and send necessary variables to CaveController.
    private void startCaveFight() throws InterruptedException {
        caveCon.getInventory(ownedPotions);
        caveCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        caveCon.startFight();

        masterLoop1();

        caveCon.fightWon = false;

        caveCon.ownedPotions[0] = ownedPotions[0];
        caveCon.ownedPotions[1] = ownedPotions[1];
        caveCon.ownedPotions[2] = ownedPotions[2];

        caveCon.ownedPotions[3] = ownedPotions[3];
        caveCon.ownedPotions[4] = ownedPotions[4];
        caveCon.ownedPotions[5] = ownedPotions[5];

        caveCon.ownedPotions[6] = ownedPotions[6];
        caveCon.ownedPotions[7] = ownedPotions[7];
        caveCon.ownedPotions[8] = ownedPotions[8];

        caveCon.ownedPotions[9] = ownedPotions[9];
        caveCon.ownedPotions[10] = ownedPotions[10];
        caveCon.ownedPotions[11] = ownedPotions[11];

        startLootController(2);
    }

    /**
     *This method is called when player encounter the boss in cave. Send ownedPotions and playerStats to caveBossController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the cave boss fight and send necessary variables to the controller.
    private void startCaveBossFight() throws InterruptedException {
        caveBossCon.getInventory(ownedPotions);
        caveBossCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        caveBossCon.startFight();

        masterLoop1();

        caveBossCon.fightWon = false;

        caveBossCon.ownedPotions[0] = ownedPotions[0];
        caveBossCon.ownedPotions[1] = ownedPotions[1];
        caveBossCon.ownedPotions[2] = ownedPotions[2];

        caveBossCon.ownedPotions[3] = ownedPotions[3];
        caveBossCon.ownedPotions[4] = ownedPotions[4];
        caveBossCon.ownedPotions[5] = ownedPotions[5];

        caveBossCon.ownedPotions[6] = ownedPotions[6];
        caveBossCon.ownedPotions[7] = ownedPotions[7];
        caveBossCon.ownedPotions[8] = ownedPotions[8];

        caveBossCon.ownedPotions[9] = ownedPotions[9];
        caveBossCon.ownedPotions[10] = ownedPotions[10];
        caveBossCon.ownedPotions[11] = ownedPotions[11];

        startLootController(20);
    }

    /**
     * This method is called when player encounter the normal enemies in field. Send ownedPotions and playerStats to fieldController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the field fight and send necessary variables to FieldController.
    private void startFieldFight() throws InterruptedException {
        fieldCon.getInventory(ownedPotions);
        fieldCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        fieldCon.startFight();

        masterLoop1();

        fieldCon.fightWon = false;

        fieldCon.ownedPotions[0] = ownedPotions[0];
        fieldCon.ownedPotions[1] = ownedPotions[1];
        fieldCon.ownedPotions[2] = ownedPotions[2];

        fieldCon.ownedPotions[3] = ownedPotions[3];
        fieldCon.ownedPotions[4] = ownedPotions[4];
        fieldCon.ownedPotions[5] = ownedPotions[5];

        fieldCon.ownedPotions[6] = ownedPotions[6];
        fieldCon.ownedPotions[7] = ownedPotions[7];
        fieldCon.ownedPotions[8] = ownedPotions[8];

        fieldCon.ownedPotions[9] = ownedPotions[9];
        fieldCon.ownedPotions[10] = ownedPotions[10];
        fieldCon.ownedPotions[11] = ownedPotions[11];

        startLootController(3);
    }

    /**
     *This method is called when player encounter the boss in field. Send ownedPotions and playerStats to fieldBossController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the field boss fight and send necessary variables to the controller.
    private void startFieldBossFight() throws InterruptedException {
        fieldBossCon.getInventory(ownedPotions);
        fieldBossCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        fieldBossCon.startFight();

        masterLoop1();

        fieldBossCon.fightWon = false;

        fieldBossCon.ownedPotions[0] = ownedPotions[0];
        fieldBossCon.ownedPotions[1] = ownedPotions[1];
        fieldBossCon.ownedPotions[2] = ownedPotions[2];

        fieldBossCon.ownedPotions[3] = ownedPotions[3];
        fieldBossCon.ownedPotions[4] = ownedPotions[4];
        fieldBossCon.ownedPotions[5] = ownedPotions[5];

        fieldBossCon.ownedPotions[6] = ownedPotions[6];
        fieldBossCon.ownedPotions[7] = ownedPotions[7];
        fieldBossCon.ownedPotions[8] = ownedPotions[8];

        fieldBossCon.ownedPotions[9] = ownedPotions[9];
        fieldBossCon.ownedPotions[10] = ownedPotions[10];
        fieldBossCon.ownedPotions[11] = ownedPotions[11];

        startLootController(30);
    }

    /**
     * This method is called when player encounter the normal enemies in swamp. Send ownedPotions and playerStats to swampController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the swamp fight and send necessary variables to SwampController.
    private void startSwampFight() throws InterruptedException {
        swampcon.getInventory(ownedPotions);
        swampcon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        swampcon.startFight();

        masterLoop1();

        swampcon.fightWon = false;

        swampcon.ownedPotions[0] = ownedPotions[0];
        swampcon.ownedPotions[1] = ownedPotions[1];
        swampcon.ownedPotions[2] = ownedPotions[2];

        swampcon.ownedPotions[3] = ownedPotions[3];
        swampcon.ownedPotions[4] = ownedPotions[4];
        swampcon.ownedPotions[5] = ownedPotions[5];

        swampcon.ownedPotions[6] = ownedPotions[6];
        swampcon.ownedPotions[7] = ownedPotions[7];
        swampcon.ownedPotions[8] = ownedPotions[8];

        swampcon.ownedPotions[9] = ownedPotions[9];
        swampcon.ownedPotions[10] = ownedPotions[10];
        swampcon.ownedPotions[11] = ownedPotions[11];

        startLootController(4);
    }

    /**
     *This method is called when player encounter the boss in swamp. Send ownedPotions and playerStats to swampBossController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the swamp boss fight and send necessary variables to the controller.
    private void startSwampBossFight() throws InterruptedException {
        swampbosscon.getInventory(ownedPotions);
        swampbosscon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        swampbosscon.startFight();

        masterLoop1();

        swampbosscon.fightWon = false;

        swampbosscon.ownedPotions[0] = ownedPotions[0];
        swampbosscon.ownedPotions[1] = ownedPotions[1];
        swampbosscon.ownedPotions[2] = ownedPotions[2];

        swampbosscon.ownedPotions[3] = ownedPotions[3];
        swampbosscon.ownedPotions[4] = ownedPotions[4];
        swampbosscon.ownedPotions[5] = ownedPotions[5];

        swampbosscon.ownedPotions[6] = ownedPotions[6];
        swampbosscon.ownedPotions[7] = ownedPotions[7];
        swampbosscon.ownedPotions[8] = ownedPotions[8];

        swampbosscon.ownedPotions[9] = ownedPotions[9];
        swampbosscon.ownedPotions[10] = ownedPotions[10];
        swampbosscon.ownedPotions[11] = ownedPotions[11];

        startLootController(40);
    }

    /**
     * This method is called when player encounter the normal enemies in castle. Send ownedPotions and playerStats to castleController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the castle fight and send necessary variables to CastleController.
    private void startCastleFight() throws InterruptedException {
        castleCon.getInventory(ownedPotions);
        castleCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        castleCon.startFight();

        masterLoop1();

        castleCon.fightWon = false;

        castleCon.ownedPotions[0] = ownedPotions[0];
        castleCon.ownedPotions[1] = ownedPotions[1];
        castleCon.ownedPotions[2] = ownedPotions[2];

        castleCon.ownedPotions[3] = ownedPotions[3];
        castleCon.ownedPotions[4] = ownedPotions[4];
        castleCon.ownedPotions[5] = ownedPotions[5];

        castleCon.ownedPotions[6] = ownedPotions[6];
        castleCon.ownedPotions[7] = ownedPotions[7];
        castleCon.ownedPotions[8] = ownedPotions[8];

        castleCon.ownedPotions[9] = ownedPotions[9];
        castleCon.ownedPotions[10] = ownedPotions[10];
        castleCon.ownedPotions[11] = ownedPotions[11];

        startLootController(5);
    }

    /**
     *This method is called when player encounter the boss in castle. Send ownedPotions and playerStats to castleBossController and start fight.<br>
     * Await for the fight to be won or lost, update potions and start LootScreen.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the castle boss fight and send necessary variables to the controller.
    private void startCastleBossFight() throws InterruptedException {
        castleBossCon.getInventory(ownedPotions);
        castleBossCon.getPlayerStats(warriorStats, mageStats, healerStats, rangerStats);
        castleBossCon.startFight();

        masterLoop1();

        castleBossCon.fightWon = false;

        castleBossCon.ownedPotions[0] = ownedPotions[0];
        castleBossCon.ownedPotions[1] = ownedPotions[1];
        castleBossCon.ownedPotions[2] = ownedPotions[2];

        castleBossCon.ownedPotions[3] = ownedPotions[3];
        castleBossCon.ownedPotions[4] = ownedPotions[4];
        castleBossCon.ownedPotions[5] = ownedPotions[5];

        castleBossCon.ownedPotions[6] = ownedPotions[6];
        castleBossCon.ownedPotions[7] = ownedPotions[7];
        castleBossCon.ownedPotions[8] = ownedPotions[8];

        castleBossCon.ownedPotions[9] = ownedPotions[9];
        castleBossCon.ownedPotions[10] = ownedPotions[10];
        castleBossCon.ownedPotions[11] = ownedPotions[11];

        startLootController(50);
    }

    /**
     *This method starts the tutorialController and starts masterLoop to await for 'boolean done' to change.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    private void startTutorial() throws InterruptedException {
      tc.startTutorial();
      masterLoop1();

      startGame();
    }

    /**
     * This method starts the shopController and starts masterLoop to await for 'boolean done' to change.<br>
     * When player is done, start overWorld.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method starts the shop and send necessary variables to ShopController.
  private void startShop() throws InterruptedException {
      sc.startShopView(currentGold);

      masterLoop1();
      startWorldModel();
  }

    /**
     * This method sends away all information lootController is going to need and starts it. Depending on what fight <br>
     *  player came from, stronger or weaker loot is generated.
     *
     * @param whatFight har koll på vilken fight player kommer ifrån
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     *
     */
    private void startLootController(int whatFight) throws InterruptedException {
        getEquipment();
        lc.getInfo(currentGold, currentXp, armorNames, weaponNames, weaponDamage, currentArmorDamage, armorBlock, rareWeaponArmorNames, epicWeaponArmorNames, legendaryWeaponArmorNames, rareWeaponArmorDamageBlock, epicWeaponArmorDamageBlock, legendaryWeaponArmorDamageBlock, armorDamage);
        lc.startLootScreen(whatFight);
        masterLoop1();
        addLoot();
    }

    /**
     * Start lose screen and send username and xp to database.
     *
     */
    private void gameLost() throws InterruptedException {
        loseView.loseScreen(currentXp);

        loseView.continueButton.addActionListener(e->done = true);
        masterLoop1();

        dataBase.sendToDataBase(currentXp, userName);
        System.exit(0);

    }

    /**
     * This method is called when player gains xp, if player has enough xp to level up stats will be upgraded and levelUpController is called.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //When player gets xp this methods check if player will level up.
    private void startLevelUp() throws InterruptedException {

        if (currentXp>15 && currentLevel == 1){
            currentLevel = 2;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if (currentXp>40 && currentLevel == 2){
            currentLevel = 3;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if (currentXp>100 && currentLevel == 3){
            currentLevel = 4;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if(currentXp>300 && currentLevel == 4){
            currentLevel = 5;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if(currentXp>800 && currentLevel == 5){
            currentLevel = 6;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if(currentXp>1500 && currentLevel == 6){
            currentLevel = 7;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if(currentXp>3000 && currentLevel == 7){
            currentLevel = 8;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if(currentXp>5000 && currentLevel == 8){
            currentLevel = 9;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
        if(currentXp>7000 && currentLevel == 9){
            currentLevel = 10;
            levelUpStats();
            luc.didPlayerLevelUp(currentXp, currentLevel, warriorLevelUpStats, rangerLevelUpStats, mageLevelUpStats, healerLevelUpStats, warriorStats, rangerStats, mageStats, healerStats);
            masterLoop1();
        }
    }
    /**
     *This method is called when player level up. Boost the party and the levelUpStat so that the next levelUp is more powerful.
     */
    //This method levels up all stats when player levels up. For each level the reward gets bigger.
    private void levelUpStats(){

        warriorStats[0] += warriorLevelUpStats[0];
        warriorStats[1] += warriorLevelUpStats[1];
        warriorStats[2] += warriorLevelUpStats[2];
        
        warriorLevelUpStats[0] += warriorLevelUpStats[0];
        warriorLevelUpStats[1] += warriorLevelUpStats[1];
        warriorLevelUpStats[2] += warriorLevelUpStats[2];

        mageStats[0] += mageLevelUpStats[0];
        mageStats[1] += mageLevelUpStats[1];
        mageStats[2] += mageLevelUpStats[2];

        mageLevelUpStats[0] += mageLevelUpStats[0];
        mageLevelUpStats[1] += mageLevelUpStats[1];
        mageLevelUpStats[2] += mageLevelUpStats[2];

        rangerStats[0] += rangerLevelUpStats[0];
        rangerStats[1] += rangerLevelUpStats[1];
        rangerStats[2] += rangerLevelUpStats[2];

        rangerLevelUpStats[0] += rangerLevelUpStats[0];
        rangerLevelUpStats[1] += rangerLevelUpStats[1];
        rangerLevelUpStats[2] += rangerLevelUpStats[2];

        healerStats[0] += healerLevelUpStats[0];
        healerStats[1] += healerLevelUpStats[1];
        healerStats[2] += healerLevelUpStats[2];

        healerLevelUpStats[0] += healerLevelUpStats[0];
        healerLevelUpStats[1] += healerLevelUpStats[1];
        healerLevelUpStats[2] += healerLevelUpStats[2];
    }

    /**
     * When player has exited the lootScreen this method is called. Check how much gold and xp player got and save it.<br>
     * Check if player got any potions, armors or weapons, if so, equip or add the loot.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
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
            ownedPotions[6]++;
        } else if (lc.whatLoot == 29) {
            ownedPotions[7]++;
        } else if (lc.whatLoot == 30) {
            ownedPotions[8]++;
        } else if (lc.whatLoot == 31) {
            ownedPotions[9]++;
        } else if (lc.whatLoot == 32) {
            ownedPotions[10]++;
        } else if (lc.whatLoot == 33) {
            ownedPotions[11]++;
        } else if (lc.whatLoot == 34) {
            ownedPotions[3]++;
        } else if (lc.whatLoot == 35) {
            ownedPotions[4]++;
        } else if (lc.whatLoot == 36) {
            ownedPotions[5]++;
        }
        startWorldModel();
    }

    /**
     *This is the first method to be called. This sets all the start numbers like warrior Hp or damage.
     */
    //This method sets all beginning stats such as hp, damage etc.
    private void setStartNumbers() {

        warriorStats[0] = 110;
        warriorStats[1] = 3 + currentWarriorArmorBlock;
        warriorStats[2] = 2 + currentWarriorWeaponDamage;

        rangerStats[0] = 80;
        rangerStats[1] = 1 + currentRangerArmorBlock;
        rangerStats[2] = 3 + currentRangerWeaponDamage;

        mageStats[0] = 70;
        mageStats[1] = 1 + currentMageArmorBlock;
        mageStats[2] = 4 +currentMageArmorDamage + currentMageWeaponDamage;

        healerStats[0] = 90;
        healerStats[1] = 1 + currentHealerArmorBlock;
        healerStats[2] = 1 +currentHealerArmorDamage + currentHealerWeaponDamage;

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
        warriorLevelUpStats[0] = 6;
        warriorLevelUpStats[1] = 2;
        warriorLevelUpStats[2] = 2;

        rangerLevelUpStats[0] = 3;
        rangerLevelUpStats[1] = 2;
        rangerLevelUpStats[2] = 4;

        mageLevelUpStats[0] = 2;
        mageLevelUpStats[1] = 1;
        mageLevelUpStats[2] = 5;

        healerLevelUpStats[0] = 5;
        healerLevelUpStats[1] = 5;
        healerLevelUpStats[2] = 2;
    }

    /**
     * This method puts the equipped armors, weapons and party stats into arrays before a fight, so that it's easier to send between classes.
     */
    //Puts variables into arrays so that it is easier to send to other classes.
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

    /**
     *This method is called when player buys something in the shop and adds it to inventory.
     *
     * @param whatLoot Indicates what the player bought in the shop.
     */
    //This method adds the item player bought in the store to inventory or equips armor or weapons.
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

    /**
     *This method is a loop that loops 100000 times, then sends the program to masterLoop2 if none of the statements are true.<br>
     * This is an alternative to a thread as we didn't have enough time to implement a thread to keep the program alive.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    //This method loops the program until 'something' happens. Use this on all fights and frames that waits for input. Works like a homemade thread.
    private void masterLoop1() throws InterruptedException {
      int loops = 0;
      boolean broken = false;
        System.out.println("Loop 1");

        while (true){
            System.out.println("Running loop 1");

            //Fights won
            if (forestCon.fightWon||caveCon.fightWon||fieldCon.fightWon||swampcon.fightWon||castleCon.fightWon||forestBossCon.fightWon||caveBossCon.fightWon||fieldBossCon.fightWon||swampbosscon.fightWon||castleBossCon.fightWon){
                System.out.println("Forest fight won 1");
                broken = true;
                break;
            }
            //Fights lost
            else if (forestCon.fightLost||forestBossCon.fightLost||caveCon.fightLost||caveBossCon.fightLost||fieldCon.fightLost||fieldBossCon.fightLost||swampcon.fightLost||swampbosscon.fightLost||castleCon.fightLost||castleBossCon.fightLost) {
                System.out.println("fightLost loop 1");
                forestCon.fightLost = false;
                caveCon.fightLost = false;
                forestBossCon.fightLost = false;
                caveBossCon.fightLost = false;
                fieldCon.fightLost = false;
                fieldBossCon.fightLost = false;
                swampcon.fightLost = false;
                swampbosscon.fightLost = false;
                castleCon.fightLost = false;
                castleBossCon.fightLost = false;

                gameLost();
                broken = true;
                break;
            }
            //Loot, tutorial, shop, levelUp,
            else if (lc.done||tc.done||sc.done||luc.done||unc.done||done) {
                System.out.println("Done");

                done = false;
                unc.done = false;
                luc.done = false;
                sc.done = false;
                tc.done = false;
                lc.done = false;
                broken = true;
                break;
            }
            else if (hubController.gameDone){
                hubController.gameDone =false;
                enterUserName();
                broken = true;
                break;
            }
            else if (hubController.tutorialDone){
                hubController.tutorialDone = false;
                startTutorial();
                broken = true;
                break;
            }
            else if (hubController.exitDone){
                System.exit(0);
            }
            //Bought item
            else if (sc.itemBought){
                System.out.println("Player bought something");
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
            masterLoop2();
        }
  }

    /**
     *This method is a loop that loops 100000 times, then sends the program to masterLoop1 if none of the statements are true.<br>
     * This is an alternative to a thread as we didn't have enough time to implement a thread to keep the program alive.
     *
     * @throws InterruptedException Most methods that are in touch with the thread with overWorld.
     */
    private void masterLoop2() throws InterruptedException {
        int loops = 0;
        boolean broken = false;
        System.out.println("Loop 1");

        while (true){
            System.out.println("Running loop 2");

            //Fights won
            if (forestCon.fightWon||caveCon.fightWon||fieldCon.fightWon||swampcon.fightWon||castleCon.fightWon||forestBossCon.fightWon||caveBossCon.fightWon||fieldBossCon.fightWon||swampbosscon.fightWon||castleBossCon.fightWon){
                System.out.println("Forest fight won 2");
                broken = true;
                break;
            }

            //Fights lost
            else if (forestCon.fightLost||forestBossCon.fightLost||caveCon.fightLost||caveBossCon.fightLost||fieldCon.fightLost||fieldBossCon.fightLost||swampcon.fightLost||swampbosscon.fightLost||castleCon.fightLost||castleBossCon.fightLost) {
                System.out.println("fightLost loop 2");
                forestCon.fightLost = false;
                caveCon.fightLost = false;
                forestBossCon.fightLost = false;
                caveBossCon.fightLost = false;
                fieldCon.fightLost = false;
                fieldBossCon.fightLost = false;
                swampcon.fightLost = false;
                swampbosscon.fightLost = false;
                castleCon.fightLost = false;
                castleBossCon.fightLost = false;
                broken = true;
                gameLost();
                break;
            }
            //Loot, tutorial, shop, levelUp,
            else if (lc.done||tc.done||sc.done||luc.done||unc.done||done) {
                System.out.println("Done");

                done = false;
                unc.done = false;
                luc.done = false;
                sc.done = false;
                tc.done = false;
                lc.done = false;
                broken = true;
                break;
            }
            else if (hubController.gameDone){
                hubController.gameDone =false;
                enterUserName();
                broken = true;
                break;
            }
            else if (hubController.tutorialDone){
                hubController.tutorialDone = false;
                startTutorial();
                broken = true;
                break;
            }
            else if (hubController.exitDone){
                System.exit(0);
            }
            //Bought item
            else if (sc.itemBought){
                System.out.println("Player bought something");
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
            masterLoop1();
        }
    }

    /**
     * Update warriors weapon to rareWarriorWeapon
     */
    //The following methods equip new weapons or armors to party members.
    private void warriorRareWeapon() {
        currentWarriorWeaponName = warriorRareWeaponName;
        currentWarriorWeaponDamage = warriorRareWeaponDamage;
    }

    /**
     * Update warriors weapon to epicWarriorWeapon
     */
    private void warriorEpicWeapon() {
        currentWarriorWeaponName = warriorEpicWeaponName;
        currentWarriorWeaponDamage = warriorEpicWeaponDamage;
    }

    /**
     * Update warriors weapon to epicWarriorWeapon
     */
    private void warriorLegendaryWeapon() {
        currentWarriorWeaponName = warriorLegendaryWeaponName;
        currentWarriorWeaponDamage = warriorLegendaryWeaponDamage;
    }

    /**
     * Update warriors armor to rareWarriorArmor
     */
    private void warriorRareArmor() {
        currentWarriorArmorName = warriorRareArmorName;
        currentWarriorArmorBlock = warriorRareArmorBlock;
    }

    /**
     * Update warriors armor to epicWarriorArmor
     */
    private void warriorEpicArmor() {
        currentWarriorArmorName = warriorEpicArmorName;
        currentWarriorArmorBlock = warriorEpicArmorBlock;
    }

    /**
     * Update warriors armor to legendaryWarriorArmor
     */
    private void warriorLegendaryArmor() {
        currentWarriorArmorName = warriorLegendaryArmorName;
        currentWarriorArmorBlock = warriorLegendaryArmorBlock;
    }

    /**
     * Update rangers weapon to rareRangerWeapon
     */
    private void rangerRareWeapon() {
        currentRangerWeaponName = rangerRareWeaponName;
        currentRangerWeaponDamage = rangerRareWeaponDamage;
    }

    /**
     * Update rangers weapon to epicRangerWeapon
     */
    private void rangerEpicWeapon() {
        currentRangerWeaponName = rangerEpicWeaponName;
        currentRangerWeaponDamage = rangerEpicWeaponDamage;
    }

    /**
     * Update rangers weapon to legendaryRangerWeapon
     */
    private void rangerLegendaryWeapon() {
        currentRangerWeaponName = rangerLegendaryWeaponName;
        currentRangerWeaponDamage = rangerLegendaryWeaponDamage;
    }

    /**
     * Update rangers armor to rareRangerArmor
     */
    private void rangerRareArmor() {
        currentRangerArmorName = rangerRareArmorName;
        currentRangerArmorBlock = rangerRareArmorBlock;
    }

    /**
     * Update rangers armor to epicRangerArmor
     */
    private void rangerEpicArmor() {
        currentRangerArmorName = rangerEpicArmorName;
        currentRangerArmorBlock = rangerEpicArmorBlock;
    }

    /**
     * Update rangers armor to legendaryRangerArmor
     */
    private void rangerLegendaryArmor() {
        currentRangerArmorName = rangerLegendaryArmorName;
        currentRangerArmorBlock = rangerLegendaryArmorBlock;
    }

    /**
     * Update mage's weapon to rareMageWeapon
     */
    private void mageRareWeapon() {
        currentMageWeaponName = mageRareWeaponName;
        currentMageWeaponDamage = mageRareWeaponDamage;
    }

    /**
     * Update mage's weapon to epicMageWeapon

     */
    private void mageEpicWeapon() {
        currentMageWeaponName = mageEpicWeaponName;
        currentMageWeaponDamage = mageEpicWeaponDamage;
    }

    /**
     * Update mage's weapon to LegendaryMageWeapon
     */
    private void mageLegendaryWeapon() {
        currentMageWeaponName = mageLegendaryWeaponName;
        currentMageWeaponDamage = mageLegendaryWeaponDamage;
    }

    /**
     * Update mage's armor to rareMageArmor
     */
    private void mageRareArmor() {
        currentMageArmorName = mageRareArmorName;
        currentMageArmorBlock = mageRareArmorBlock;
        currentMageArmorDamage = mageRareArmorDamage;

    }

    /**
     * Update mage's armor to epicMageArmor
     */
    private void mageEpicArmor() {
        currentMageArmorName = mageEpicArmorName;
        currentMageArmorBlock = mageEpicArmorBlock;
        currentMageArmorDamage = mageEpicArmorDamage;
    }

    /**
     * Update mage's armor to legendaryMageArmor
     */
    private void mageLegendaryArmor() {
        currentMageArmorName = mageLegendaryArmorName;
        currentMageArmorBlock = mageLegendaryArmorBlock;
        currentMageArmorDamage = mageLegendaryArmorDamage;
    }

    /**
     * Update healers weapon to rareHealerWeapon
     */
    private void healerRareWeapon() {
        currentHealerWeaponName = healerRareWeaponName;
        currentHealerWeaponDamage = healerRareWeaponDamage;
    }

    /**
     * Update healers weapon to epicHealerWeapon
     */
    private void healerEpicWeapon() {
        currentHealerWeaponName = healerEpicWeaponName;
        currentHealerWeaponDamage = healerEpicWeaponDamage;
    }

    /**
     * Update healers weapon to legendaryHealerWeapon
     */
    private void healerLegendaryWeapon() {
        currentHealerWeaponName = healerLegendaryWeaponName;
        currentHealerWeaponDamage = healerLegendaryWeaponDamage;
    }

    /**
     * Update healers armor to rareHealerArmor
     */
    //All healer armors
    private void healerRareArmor() {
        currentHealerArmorName = healerRareArmorName;
        currentHealerArmorBlock = healerRareArmorBlock;
        currentHealerArmorDamage = healerRareArmorDamage;
    }

    /**
     * Update healers armor to epicHealerArmor
     */
    private void healerEpicArmor() {
        currentHealerArmorName = healerEpicArmorName;
        currentHealerArmorBlock = healerEpicArmorBlock;
        currentHealerArmorDamage = healerEpicArmorDamage;
    }

    /**
     * Update healers armor to legendaryHealerArmor
     */
    private void healerLegendaryArmor() {
        currentHealerArmorName = healerLegendaryArmorName;
        currentHealerArmorBlock = healerLegendaryArmorBlock;
        currentHealerArmorDamage = healerLegendaryArmorDamage;
    }
}