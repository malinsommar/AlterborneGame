package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LootController {

    private LootFrame lf = new LootFrame();



    int textDelay = 0, whatLoot, xpInt, goldInt;

    private String warriorWeaponName, mageWeaponName, rangerWeaponName, healerWeaponName;
    private String warriorArmorName, mageArmorName,rangerArmorName, healerArmorName;

    private int warriorWeaponDamage, mageWeaponDamage, rangerWeaponDamage, healerWeaponDamage;
    private int warriorArmorBlock, mageArmorBlock, rangerArmorBlock, healerArmorBlock;
    private int mageArmorDamage, healerArmorDamage;

    private String warriorRareWeaponName, mageRareWeaponName, rangerRareWeaponName, healerRareWeaponName;
    private String warriorEpicWeaponName, mageEpicWeaponName, rangerEpicWeaponName, healerEpicWeaponName;
    private String warriorLegendaryWeaponName, mageLegendaryWeaponName, rangerLegendaryWeaponName, healerLegendaryWeaponName;
    private String warriorRareArmorName, mageRareArmorName, rangerRareArmorName, healerRareArmorName;
    private String warriorEpicArmorName, mageEpicArmorName, rangerEpicArmorName, healerEpicArmorName;
    private String warriorLegendaryArmorName, mageLegendaryArmorName, rangerLegendaryArmorName, healerLegendaryArmorName;

    private int warriorRareWeaponDamage, mageRareWeaponDamage, rangerRareWeaponDamage, healerRareWeaponDamage;
    private int warriorEpicWeaponDamage, mageEpicWeaponDamage, rangerEpicWeaponDamage, healerEpicWeaponDamage;
    private int warriorLegendaryWeaponDamage, mageLegendaryWeaponDamage, rangerLegendaryWeaponDamage, healerLegendaryWeaponDamage;
    private int warriorRareArmorBlock, mageRareArmorBlock, rangerRareArmorBlock, healerRareArmorBlock;
    private int warriorEpicArmorBlock, mageEpicArmorBlock, rangerEpicArmorBlock, healerEpicArmorBlock;
    private int warriorLegendaryArmorBlock, mageLegendaryArmorBlock, rangerLegendaryArmorBlock, healerLegendaryArmorBlock;
    private int mageRareArmorDamage, mageEpicArmorDamage, mageLegendaryArmorDamage, healerRareArmorDamage, healerEpicArmorDamage, healerLegendaryArmorDamage;

    private boolean showEquipButton = false;
    boolean playerWantsLoot = false;

    public int[] status = new int[1];



    //This method starts LootFrame and implements the methods needed for LootScreen.
    public void startLootScreen(int fight) throws InterruptedException {

        lf.lootScreenFrame();
        generateLoot(fight);
        hover();

        textDelayTimer.start();

        lf.equipButton.addActionListener(e -> equipLoot());
        LootModel lm = new LootModel();  //TODO this does not follow MVC
        lf.continueButton.addActionListener(e -> {
            try {
                while (status[0] == 0) {
                    status[0] = 1;
                    lm.addLoot();
                    lf.lootScreenJFrame.dispose();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    //Method that collects info from model about gold, xp, weapons, armor etc.
    public void getInfo(int getGold, int getXp, String[] armorNames, String[] weaponNames, int[] weaponDamage, int[] currentArmorDamage, int[] armorBlock, String[] rareWeaponArmorNames, String[] epicWeaponArmorNames, String[] legendaryWeaponArmorNames, int[] rareWeaponArmorDamageBlock, int[] epicWeaponArmorDamageBlock, int[] legendaryWeaponArmorDamageBlock, int[] armorDamage){

        goldInt = getGold;
        xpInt = getXp;

        warriorArmorName = armorNames[0];
        mageArmorName = armorNames[1];
        rangerArmorName = armorNames[2];
        healerArmorName = armorNames[3];

        warriorWeaponName = weaponNames[0];
        mageWeaponName = weaponNames[1];
        rangerWeaponName = weaponNames[2];
        healerWeaponName = weaponNames[3];

        warriorWeaponDamage = weaponDamage[0];
        mageWeaponDamage = weaponDamage[1];
        rangerWeaponDamage = weaponDamage[2];
        healerWeaponDamage = weaponDamage[3];

        warriorArmorBlock = armorBlock[0];
        mageArmorBlock = armorBlock[1];
        rangerArmorBlock = armorBlock[2];
        healerArmorBlock = armorBlock[3];

        mageArmorDamage = currentArmorDamage[0];
        healerArmorDamage = currentArmorDamage[1];

        warriorRareWeaponName = rareWeaponArmorNames[0];
        warriorRareArmorName = rareWeaponArmorNames[1];
        mageRareWeaponName = rareWeaponArmorNames[2];
        mageRareArmorName = rareWeaponArmorNames[3];
        rangerRareWeaponName = rareWeaponArmorNames[4];
        rangerRareArmorName = rareWeaponArmorNames[5];
        healerRareWeaponName = rareWeaponArmorNames[6];
        healerRareArmorName = rareWeaponArmorNames[7];

        warriorEpicWeaponName = epicWeaponArmorNames[0];
        warriorEpicArmorName = epicWeaponArmorNames[1];
        mageEpicWeaponName = epicWeaponArmorNames[2];
        mageEpicArmorName = epicWeaponArmorNames[3];
        rangerEpicWeaponName = epicWeaponArmorNames[4];
        rangerEpicArmorName = epicWeaponArmorNames[5];
        healerEpicWeaponName = epicWeaponArmorNames[6];
        healerEpicArmorName = epicWeaponArmorNames[7];

        warriorLegendaryWeaponName = legendaryWeaponArmorNames[0];
        warriorLegendaryArmorName = legendaryWeaponArmorNames[1];
        mageLegendaryWeaponName = legendaryWeaponArmorNames[2];
        mageLegendaryArmorName = legendaryWeaponArmorNames[3];
        rangerLegendaryWeaponName = legendaryWeaponArmorNames[4];
        rangerLegendaryArmorName = legendaryWeaponArmorNames[5];
        healerLegendaryWeaponName = legendaryWeaponArmorNames[6];
        healerLegendaryArmorName = legendaryWeaponArmorNames[7];

        warriorRareWeaponDamage = rareWeaponArmorDamageBlock[0];
        warriorRareArmorBlock = rareWeaponArmorDamageBlock[1];
        mageRareWeaponDamage = rareWeaponArmorDamageBlock[2];
        mageRareArmorBlock = rareWeaponArmorDamageBlock[3];
        rangerRareWeaponDamage = rareWeaponArmorDamageBlock[4];
        rangerRareArmorBlock = rareWeaponArmorDamageBlock[5];
        healerRareWeaponDamage = rareWeaponArmorDamageBlock[6];
        healerRareArmorBlock = rareWeaponArmorDamageBlock[7];

        warriorEpicWeaponDamage = epicWeaponArmorDamageBlock[0];
        warriorEpicArmorBlock = epicWeaponArmorDamageBlock[1];
        mageEpicWeaponDamage = epicWeaponArmorDamageBlock[2];
        mageEpicArmorBlock = epicWeaponArmorDamageBlock[3];
        rangerEpicWeaponDamage = epicWeaponArmorDamageBlock[4];
        rangerEpicArmorBlock = epicWeaponArmorDamageBlock[5];
        healerEpicWeaponDamage = epicWeaponArmorDamageBlock[6];
        healerEpicArmorBlock = epicWeaponArmorDamageBlock[7];

        warriorLegendaryWeaponDamage = legendaryWeaponArmorDamageBlock[0];
        warriorLegendaryArmorBlock = legendaryWeaponArmorDamageBlock[1];
        mageLegendaryWeaponDamage = legendaryWeaponArmorDamageBlock[2];
        mageLegendaryArmorBlock = legendaryWeaponArmorDamageBlock[3];
        rangerLegendaryWeaponDamage = legendaryWeaponArmorDamageBlock[4];
        rangerLegendaryArmorBlock = legendaryWeaponArmorDamageBlock[5];
        healerLegendaryWeaponDamage = legendaryWeaponArmorDamageBlock[6];
        healerLegendaryArmorBlock = legendaryWeaponArmorDamageBlock[7];

        mageRareArmorDamage = armorDamage[0];
        mageEpicArmorDamage = armorDamage[1];
        mageLegendaryArmorDamage = armorDamage[2];
        healerRareArmorDamage = armorDamage[3];
        healerEpicArmorDamage = armorDamage[4];
        healerLegendaryArmorDamage = armorDamage[5];
    }

    //Generate what weapon/armor/potion player get from fight. The odds differ depending what fight player came from which the parameter int keeps track of.
    private void generateLoot(int fight){
        int ranLoot = (int) (Math.random() * 10);
        int ran = (int)(Math.random()*2)+1;

        //If player comes from forestFight.
        if(fight==1){
            int xpUp = (int)(Math.random()*7)+10;
            int xpBefore = xpInt;
            xpInt += xpUp;
            lf.xp.setText("Xp: "+xpBefore+" --> "+xpInt);

            int newGold = (int) (Math.random() * 10) + 10;
            lf.gold.setText("Gold: "+goldInt+" --> "+(newGold+goldInt));
            goldInt += newGold;

            if ( ranLoot == 0 || ranLoot == 1){
                lf.item.setText("     No items found.");
                lf.hideLabels();
            }

            else if(ranLoot == 2){
                if (ran==1){
                    getRareWarriorArmor();
                }
                if (ran==2){
                   getRareWarriorWeapon();
                }
            }
            else if(ranLoot == 3){
                if (ran==1){
                   getRareRangerArmor();
                }
                if (ran==2){
                    getRareRangerWeapon();
                }
            }
            else if(ranLoot == 4){
                if (ran==1){
                    getRareMageArmor();
                }
                if (ran==2){
                   getRareMageWeapon();
                }
            }
            else if(ranLoot == 5){
                if (ran==1){
                    getRareHealerArmor();
                }
                if (ran==2){
                    getRareHealerWeapon();
                }
            }
            else if(ranLoot == 6){
                getMinorHealingPotion();
            }
            else if(ranLoot == 7){
               getMinorStrengthPotion();
            }
            else if(ranLoot == 8){
               getMinorEnergyPotion();
            }
            else if(ranLoot == 9){
                getMinorBlockPotion();
            }
        }
        else if (fight == 2){
            //cave fight
        }
    }

    //The following 24 methods equips the loot and updates labels depending on what the player gets.
    private void getRareWarriorArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(warriorArmorName);
        lf.currentEquipmentStats.setText("Block: "+warriorArmorBlock);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(warriorRareArmorName);
        lf.newEquipmentStats.setText("Block: "+warriorRareArmorBlock);

        lf.item.setText("Warrior found: "+warriorRareArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 1;
        showEquipButton = true;
    }
    private void getEpicWarriorArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(warriorArmorName);
        lf.currentEquipmentStats.setText("Block: "+warriorArmorBlock);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(warriorEpicArmorName);
        lf.newEquipmentStats.setText("Block: "+warriorEpicArmorBlock);

        lf.item.setText("Warrior found: "+warriorEpicArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 2;
        showEquipButton = true;
    }
    private void getLegendaryWarriorArmor(){

        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(warriorArmorName);
        lf.currentEquipmentStats.setText("Block: "+warriorArmorBlock);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(warriorLegendaryArmorName);
        lf.newEquipmentStats.setText("Block: "+warriorLegendaryArmorBlock);

        lf.item.setText("Warrior found: "+warriorLegendaryArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 3;
        showEquipButton = true;
    }
    private void getRareWarriorWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(warriorWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+warriorWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(warriorRareWeaponName);
        lf.newEquipmentStats.setText("Damage: "+warriorRareWeaponDamage);

        lf.item.setText("Warrior found: "+warriorRareWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 4;
        showEquipButton = true;
    }
    private void getEpicWarriorWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(warriorWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+warriorWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(warriorEpicWeaponName);
        lf.newEquipmentStats.setText("Damage: "+warriorEpicWeaponDamage);

        lf.item.setText("Warrior found: "+warriorEpicWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 5;
        showEquipButton = true;
    }
    private void getLegendaryWarriorWeapon(){

        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(warriorWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+warriorWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(warriorLegendaryWeaponName);
        lf.newEquipmentStats.setText("Damage: "+warriorLegendaryWeaponDamage);

        lf.item.setText("Warrior found: "+warriorLegendaryWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 6;
        showEquipButton = true;
    }

    private void getRareMageArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(mageArmorName);
        lf.currentEquipmentStats.setText("Block: "+mageArmorBlock+"Str: "+mageArmorDamage);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(mageRareArmorName);
        lf.newEquipmentStats.setText("Block: "+mageRareArmorBlock+", Str: "+mageRareArmorDamage);

        lf.item.setText("Mage found: "+mageRareArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 7;
        showEquipButton = true;
    }
    private void getEpicMageArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(mageArmorName);
        lf.currentEquipmentStats.setText("Block: "+mageArmorBlock+"Str: "+mageArmorDamage);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(mageEpicArmorName);
        lf.newEquipmentStats.setText("Block: "+mageEpicArmorBlock+", Str: "+mageEpicArmorDamage);

        lf.item.setText("Mage found: "+mageEpicArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 8;
        showEquipButton = true;
    }
    private void getLegendaryMageArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(mageArmorName);
        lf.currentEquipmentStats.setText("Block: "+mageArmorBlock+"Str: "+mageArmorDamage);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(mageRareArmorName);
        lf.newEquipmentStats.setText("Block: "+mageLegendaryArmorBlock+", Str: "+mageLegendaryArmorDamage);

        lf.item.setText("Mage found: "+mageLegendaryArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 9;
        showEquipButton = true;
    }
    private void getRareMageWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(mageWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+mageWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(mageRareWeaponName);
        lf.newEquipmentStats.setText("Damage: "+mageRareWeaponDamage);

        lf.item.setText("Mage found: "+mageRareWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 10;
        showEquipButton = true;
    }
    private void getEpicMageWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(mageWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+mageWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(mageEpicWeaponName);
        lf.newEquipmentStats.setText("Damage: "+mageEpicWeaponDamage);

        lf.item.setText("Mage found: "+mageEpicWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 11;
        showEquipButton = true;
    }
    private void getLegendaryMageWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(mageWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+mageWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(mageLegendaryWeaponName);
        lf.newEquipmentStats.setText("Damage: "+mageLegendaryWeaponDamage);

        lf.item.setText("Mage found: "+mageLegendaryWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 12;
        showEquipButton = true;
    }

    private void getRareRangerArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(rangerArmorName);
        lf.currentEquipmentStats.setText("Block: "+rangerArmorBlock);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(rangerRareArmorName);
        lf.newEquipmentStats.setText("Block: "+rangerRareArmorBlock);

        lf.item.setText(rangerRareArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 13;
        showEquipButton = true;
    }
    private void getEpicRangerArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(rangerArmorName);
        lf.currentEquipmentStats.setText("Block: "+rangerArmorBlock);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(rangerEpicArmorName);
        lf.newEquipmentStats.setText("Block: "+rangerEpicArmorBlock);

        lf.item.setText(rangerEpicArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 14;
        showEquipButton = true;
    }
    private void getLegendaryRangerArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(rangerArmorName);
        lf.currentEquipmentStats.setText("Block: "+rangerArmorBlock);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(rangerLegendaryArmorName);
        lf.newEquipmentStats.setText("Block: "+rangerLegendaryArmorBlock);

        lf.item.setText(rangerLegendaryArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 15;
        showEquipButton = true;
    }
    private void getRareRangerWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(rangerWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+rangerWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(rangerRareWeaponName);
        lf.newEquipmentStats.setText("Damage: "+rangerRareWeaponDamage);

        lf.item.setText("Ranger found: "+rangerRareWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 16;
        showEquipButton = true;
    }
    private void getEpicRangerWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(rangerWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+rangerWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(rangerEpicWeaponName);
        lf.newEquipmentStats.setText("Damage: "+rangerEpicWeaponDamage);

        lf.item.setText("Ranger found: "+rangerEpicWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 17;
        showEquipButton = true;
    }
    private void getLegendaryRangerWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(rangerWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+rangerWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(rangerLegendaryWeaponName);
        lf.newEquipmentStats.setText("Damage: "+rangerLegendaryWeaponDamage);

        lf.item.setText("Ranger found: "+rangerLegendaryWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 18;
        showEquipButton = true;
    }

    private void getRareHealerArmor(){

        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(healerArmorName);
        lf.currentEquipmentStats.setText("Block: "+healerArmorBlock+", Str: "+healerArmorDamage);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(healerRareArmorName);
        lf.newEquipmentStats.setText("Block: "+healerRareArmorBlock+", Str: "+healerRareArmorDamage);


        lf.item.setText("Healer found: "+healerRareArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 19;
        showEquipButton = true;
    }
    private void getEpicHealerArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(healerArmorName);
        lf.currentEquipmentStats.setText("Block: "+healerArmorBlock+", Str: "+healerArmorDamage);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(healerEpicArmorName);
        lf.newEquipmentStats.setText("Block: "+healerEpicArmorBlock+", Str: "+healerEpicArmorDamage);


        lf.item.setText("Healer found: "+healerEpicArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 20;
        showEquipButton = true;
    }
    private void getLegendaryHealerArmor(){
        lf.currentEquipment.setText("Current Armor:");
        lf.currentEquipmentName.setText(healerArmorName);
        lf.currentEquipmentStats.setText("Block: "+healerArmorBlock+", Str: "+healerArmorDamage);

        lf.newEquipment.setText("New Armor:");
        lf.newEquipmentName.setText(healerLegendaryArmorName);
        lf.newEquipmentStats.setText("Block: "+healerLegendaryArmorBlock+", Str: "+healerLegendaryArmorDamage);


        lf.item.setText("Healer found: "+healerLegendaryArmorName);
        lf.item.setForeground(Color.blue);
        whatLoot = 21;
        showEquipButton = true;
    }
    private void getRareHealerWeapon(){

        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(healerWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+healerWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(healerRareWeaponName);
        lf.newEquipmentStats.setText("Damage: "+healerRareArmorDamage);

        lf.item.setText("Healer found: "+healerRareWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 22;
        showEquipButton = true;
    }
    private void getEpicHealerWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(healerWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+healerWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(healerEpicWeaponName);
        lf.newEquipmentStats.setText("Damage: "+healerEpicArmorDamage);

        lf.item.setText("Healer found: "+healerEpicWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 23;
        showEquipButton = true;
    }
    private void getLegendaryHealerWeapon(){
        lf.currentEquipment.setText("Current Weapon:");
        lf.currentEquipmentName.setText(healerWeaponName);
        lf.currentEquipmentStats.setText("Damage: "+healerWeaponDamage);

        lf.newEquipment.setText("New Weapon:");
        lf.newEquipmentName.setText(healerLegendaryWeaponName);
        lf.newEquipmentStats.setText("Damage: "+healerLegendaryWeaponDamage);

        lf.item.setText("Healer found: "+healerLegendaryWeaponName);
        lf.item.setForeground(Color.blue);
        whatLoot = 24;
        showEquipButton = true;
    }
    //The following 16 methods adds the potion player gets from the fight and updates labels.
    private void getMinorHealingPotion(){
        whatLoot = 25;
        lf.item.setText("Found a minor healing potion.");
        lf.hideLabels();
    }
    private void getLesserHealingPotion(){
        whatLoot = 26;
        lf.item.setText("Found a lesser healing potion.");
        lf.hideLabels();
    }
    private void getMajorHealingPotion(){
        whatLoot = 27;
        lf.item.setText("Found a major healing potion.");
        lf.hideLabels();
    }

    private void getMinorEnergyPotion(){
        whatLoot = 28;
        lf.item.setText("Found a minor energy potion.");
        lf.hideLabels();
    }
    private void getLesserEnergyPotion(){
        whatLoot = 29;
        lf.item.setText("Found a lesser energy potion.");
        lf.hideLabels();
    }
    private void getMajorEnergyPotion(){
        whatLoot = 30;
        lf.item.setText("Found a major energy potion.");
        lf.hideLabels();
    }

    private void getMinorStrengthPotion(){
        whatLoot = 31;
        lf.item.setText("Found a minor strength potion.");
        lf.hideLabels();
    }
    private void getLesserStrengthPotion(){
        whatLoot = 32;
        lf.item.setText("Found a lesser strength potion.");
        lf.hideLabels();
    }
    private void getMajorStrengthPotion(){
        whatLoot = 33;
        lf.item.setText("Found a major strength potion.");
        lf.hideLabels();
    }

    private void getMinorBlockPotion(){
        whatLoot = 34;
        lf.item.setText("Found a minor block potion.");
        lf.hideLabels();
    }
    private void getLesserBlockPotion(){
        whatLoot = 35;
        lf.item.setText("Found a lesser block potion.");
        lf.hideLabels();
    }
    private void getMajorBlockPotion(){
        whatLoot = 36;
        lf.item.setText("Found a major block potion.");
        lf.hideLabels();
    }

    //Method is called when equipButton is pressed. Changes the party-members armor/weapon and updates currentWeapon labels.
    private void equipLoot(){
        //Warrior
        if (whatLoot==1) {
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(warriorRareArmorName);
            lf.currentEquipmentStats.setText("Block: "+warriorRareArmorBlock);
        }
        else if(whatLoot==2) {
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(warriorEpicArmorName);
            lf.currentEquipmentStats.setText("Block: "+warriorEpicArmorBlock);
        }
        else if(whatLoot==3) {
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(warriorLegendaryArmorName);
            lf.currentEquipmentStats.setText("Block: "+warriorLegendaryArmorBlock);
        }
        else if(whatLoot==4){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(warriorRareWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+warriorRareWeaponDamage);
        }
        else if(whatLoot==5){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(warriorEpicWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+warriorEpicWeaponDamage);
        }
        else if(whatLoot==6){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(warriorLegendaryWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+warriorLegendaryWeaponDamage);
        }
        //Mage
        else if(whatLoot==7){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(mageRareArmorName);
            lf.currentEquipmentStats.setText("Block: "+mageRareArmorBlock+"Str: "+mageRareArmorDamage);
        }
        else if(whatLoot==8){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(mageEpicArmorName);
            lf.currentEquipmentStats.setText("Block: "+mageEpicArmorBlock+"Str: "+mageEpicArmorDamage);
        }
        else if(whatLoot==9){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(mageLegendaryArmorName);
            lf.currentEquipmentStats.setText("Block: "+mageLegendaryArmorBlock+"Str: "+mageLegendaryArmorDamage);
        }
        else if(whatLoot==10){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(mageRareWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+mageRareWeaponDamage);
        }
        else if(whatLoot==11){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(mageEpicArmorName);
            lf.currentEquipmentStats.setText("Damage: "+mageEpicWeaponDamage);
        }
        else if(whatLoot==12){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(mageLegendaryArmorName);
            lf.currentEquipmentStats.setText("Damage: "+mageLegendaryWeaponDamage);
        }
        //Ranger
        else if(whatLoot==13){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(rangerRareArmorName);
            lf.currentEquipmentStats.setText("Block: "+rangerRareArmorBlock);
        }
        else if(whatLoot==14){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(rangerEpicArmorName);
            lf.currentEquipmentStats.setText("Block: "+rangerEpicArmorBlock);
        }
        else if(whatLoot==15){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(rangerLegendaryArmorName);
            lf.currentEquipmentStats.setText("Block: "+rangerLegendaryArmorBlock);
        }
        else if(whatLoot==16){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(rangerRareWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+rangerRareWeaponDamage);
        }
        else if(whatLoot==17){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(rangerEpicWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+rangerEpicWeaponDamage);
        }
        else if(whatLoot==18){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(rangerLegendaryWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+rangerLegendaryWeaponDamage);
        }
        //Healer
        else if(whatLoot==19){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(healerRareArmorName);
            lf.currentEquipmentStats.setText("Block: "+healerRareArmorBlock+"Str: "+healerRareArmorDamage);
        }
        else if(whatLoot==20){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(healerEpicArmorName);
            lf.currentEquipmentStats.setText("Block: "+healerRareArmorBlock+"Str: "+healerRareArmorDamage);
        }
        else if(whatLoot==21){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(healerLegendaryArmorName);
            lf.currentEquipmentStats.setText("Block: "+healerRareArmorBlock+"Str: "+healerRareArmorDamage);
        }
        else if(whatLoot==22){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(healerRareWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+healerRareWeaponDamage);
        }
        else if(whatLoot==23){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(healerEpicWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+healerEpicWeaponDamage);
        }
        else if(whatLoot==24){
            playerWantsLoot = true;
            lf.currentEquipmentName.setText(healerLegendaryWeaponName);
            lf.currentEquipmentStats.setText("Damage: "+healerLegendaryWeaponDamage);
        }
    }

    //Timer ticks every second, adding labels and buttons.
    private Timer textDelayTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            textDelay++;
            if (textDelay == 2){
                MusicPick.musicStart("ding","");
                lf.lootScreenJFrame.add(lf.xp);
                System.out.println(xpInt);
            }
            else if(textDelay == 3){
                MusicPick.musicStart("ding","");
                lf.lootScreenJFrame.add(lf.gold);
                System.out.println(goldInt);
            }
            //TODO fixa lootscreen
            else if(textDelay == 4){
                MusicPick.musicStart("ding","");
                lf.lootScreenJFrame.add(lf.item);
                if(showEquipButton){
                    lf.lootScreenJFrame.add(lf.equipButton);
                    lf.lootScreenJFrame.add(lf.currentEquipment);
                    lf.lootScreenJFrame.add(lf.currentEquipmentName);
                    lf.lootScreenJFrame.add(lf.currentEquipmentStats);
                    lf.lootScreenJFrame.add(lf.newEquipment);
                    lf.lootScreenJFrame.add(lf.newEquipmentName);
                    lf.lootScreenJFrame.add(lf.newEquipmentStats);
                }
                textDelayTimer.stop();
            }
            lf.lootScreenJFrame.repaint();
        }
    });

    //Adds hover effect to buttons.
    private void hover(){
        lf.continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lf.continueButton.setBackground(Color.darkGray);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) { }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lf.continueButton.setBackground(Color.gray);
            }
        });
        lf.equipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lf.equipButton.setBackground(Color.darkGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                lf.equipButton.setBackground(Color.gray);
            }
        });
    }
}