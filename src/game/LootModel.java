package game;

import party.Healer;
import party.Mage;
import party.Ranger;
import party.Warrior;

public class LootModel {

    private LootController lc = new LootController();
    private LevelUp lu = new LevelUp();
    private Inventory inv = new Inventory();
    private Mage m = new Mage();
    private Warrior w = new Warrior();
    private Ranger r = new Ranger();
    private Healer h = new Healer();

    private int currentXp = lu.xp;
    private int currentGold = inv.gold;

    //1=warrior, 2=mage, 3=ranger, 4=healer.
    private String[] armorNames = new String[4];
    private String[] weaponNames = new String[4];
    private int[] weaponDamage = new int[4];
    private int[] armorBlock = new int[4];

    //1 2 = warrior, 3 4 = mage, 5 6 = ranger, 7 8 = healer.
    private String[] rareWeaponArmorNames = new String[8];
    private String[] epicWeaponArmorNames = new String[8];
    private String[] legendaryWeaponArmorNames = new String[8];

    //1 2 3 = warrior, 4 5 6 7 8 9 = mage, 10 11 12 = ranger, 13 14 15 16 17 18 = healer.
    private int[] rareWeaponArmorDamageBlock = new int[18];
    private int[] epicWeaponArmorDamageBlock = new int[18];
    private int[] legendaryWeaponArmorDamageBlock = new int[18];

    //1=mage, 2=healer.
    private int[] armorDamage = new int[2];

    public void startController(){

        getEquipment();
        lc.getInfo(currentGold, currentXp, armorNames, weaponNames, weaponDamage, armorDamage, armorBlock, rareWeaponArmorNames, epicWeaponArmorNames, legendaryWeaponArmorNames, rareWeaponArmorDamageBlock, epicWeaponArmorDamageBlock, legendaryWeaponArmorDamageBlock);
        lc.startLootScreen();
    }

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

        armorDamage[0] = m.currentArmorDamage;
        armorDamage[1] = h.currentArmorDamage;

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
    }
}