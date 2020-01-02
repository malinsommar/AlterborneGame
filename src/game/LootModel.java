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

    //This method sends away all information lootController is going to need and starts it.
    public void startLootController(){
        getEquipment();
        lc.getInfo(currentGold, currentXp, armorNames, weaponNames, weaponDamage, currentArmorDamage, armorBlock, rareWeaponArmorNames, epicWeaponArmorNames, legendaryWeaponArmorNames, rareWeaponArmorDamageBlock, epicWeaponArmorDamageBlock, legendaryWeaponArmorDamageBlock, armorDamage);

        int forestFight = 1;
        lc.startLootScreen(forestFight); //for now, ska vara vilken fight från overworld

        //addloot
    }

    //This method saves gold, xp and weapon/armor that player got from lootController.
    public void addLoot(){

        inv.gold = lc.goldInt;
        lu.xp = lc.xpInt;

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
                inv.ownedMinorHealingPotion++;
            }
        else if (lc.whatLoot == 26){
            inv.ownedLesserHealingPotion++;
        }
        else if (lc.whatLoot == 27){
            inv.ownedMajorHealingPotion++;
        }

        else if (lc.whatLoot == 28){
            inv.ownedMinorEnergyPotion++;
        }
        else if (lc.whatLoot == 29){
            inv.ownedLesserEnergyPotion++;
        }
        else if (lc.whatLoot == 30){
            inv.ownedMajorEnergyPotion++;
        }

        else if (lc.whatLoot == 31){
            inv.ownedMinorStrengthPotion++;
        }
        else if (lc.whatLoot == 32){
            inv.ownedLesserStrengthPotion++;
        }
        else if (lc.whatLoot == 33){
            inv.ownedMajorStrengthPotion++;
        }

        else if (lc.whatLoot == 34){
            inv.ownedMinorBlockPotion++;
        }
        else if (lc.whatLoot == 35){
            inv.ownedLesserBlockPotion++;
        }
        else if (lc.whatLoot == 36){
            inv.ownedMajorBlockPotion++;
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