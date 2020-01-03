package game;

import party.Healer;
import party.Mage;
import party.Ranger;
import party.Warrior;

public class ShopModel {

    private ShopController sc = new ShopController();
    private Inventory inv = new Inventory();
    private Warrior w = new Warrior();
    private Healer h = new Healer();
    private Ranger r = new Ranger();
    private Mage m = new Mage();

    private int currentGold=inv.gold;

    public void startShopController(){

        sc.startShopView(currentGold);
    }
    
    public void addBoughtItems(){

        inv.gold = sc.currentGold;

        //Armor & Weapons
        if (sc.whatLoot == 1){
            w.warriorRareArmor();
        }
        else if (sc.whatLoot == 2){
            w.warriorEpicArmor();
        }
        else if (sc.whatLoot == 3){
            w.warriorLegendaryArmor();
        }
        else if (sc.whatLoot == 4){
            w.warriorRareWeapon();
        }
        else if (sc.whatLoot == 5){
            w.warriorEpicWeapon();
        }
        else if (sc.whatLoot == 6){
            w.warriorLegendaryWeapon();
        }
        else if (sc.whatLoot == 7){
            m.mageRareArmor();
        }
        else if (sc.whatLoot == 8){
            m.mageEpicArmor();
        }
        else if (sc.whatLoot == 9){
            m.mageLegendaryArmor();
        }
        else if (sc.whatLoot == 10){
            m.mageRareWeapon();
        }
        else if (sc.whatLoot == 11){
            m.mageEpicWeapon();
        }
        else if (sc.whatLoot == 12){
            m.mageLegendaryWeapon();
        }
        else if (sc.whatLoot == 13){
            r.rangerRareArmor();
        }
        else if (sc.whatLoot == 14){
            r.rangerEpicArmor();
        }
        else if (sc.whatLoot == 15){
            r.rangerLegendaryArmor();
        }
        else if (sc.whatLoot == 16){
            r.rangerRareWeapon();
        }
        else if (sc.whatLoot == 17){
            r.rangerEpicWeapon();
        }
        else if (sc.whatLoot == 18){
            r.rangerLegendaryWeapon();
        }
        else if (sc.whatLoot == 19){
            h.healerRareArmor();
        }
        else if (sc.whatLoot == 20){
            h.healerEpicArmor();
        }
        else if (sc.whatLoot == 21){
            h.healerLegendaryArmor();
        }
        else if (sc.whatLoot == 22){
            h.healerRareWeapon();
        }
        else if (sc.whatLoot == 23){
            h.healerEpicWeapon();
        }
        else if (sc.whatLoot == 24){
            h.healerLegendaryWeapon();
        }

        //Potions
        if (sc.whatLoot == 25){
            inv.ownedMinorHealingPotion++;
        }
        else if (sc.whatLoot == 26){
            inv.ownedLesserHealingPotion++;
        }
        else if (sc.whatLoot == 27){
            inv.ownedMajorHealingPotion++;
        }
        else if (sc.whatLoot == 28){
            inv.ownedMinorEnergyPotion++;
        }
        else if (sc.whatLoot == 29){
            inv.ownedLesserEnergyPotion++;
        }
        else if (sc.whatLoot == 30){
            inv.ownedMajorEnergyPotion++;
        }
        else if (sc.whatLoot == 31){
            inv.ownedMinorStrengthPotion++;
        }
        else if (sc.whatLoot == 32){
            inv.ownedLesserStrengthPotion++;
        }
        else if (sc.whatLoot == 33){
            inv.ownedMajorStrengthPotion++;
        }
        else if (sc.whatLoot == 34){
            inv.ownedMinorBlockPotion++;
        }
        else if (sc.whatLoot == 35){
            inv.ownedLesserBlockPotion++;
        }
        else if (sc.whatLoot == 36){
            inv.ownedMajorBlockPotion++;
        }
    }
}