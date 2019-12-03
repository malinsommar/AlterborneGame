package game;

import javax.swing.*;

public class Inventory {

    public int gold = 20;

    //Import potion gifs
    public ImageIcon minorHealthGif = new ImageIcon("minorHealth.gif");
    public ImageIcon lesserHealthGif = new ImageIcon("lesserHealth.gif");
    public ImageIcon majorHealthGif = new ImageIcon("majorHealth.gif");

    public ImageIcon minorBlockGif = new ImageIcon("minorBlock.gif");
    public ImageIcon lesserBlockGif = new ImageIcon("lesserBlock.gif");
    public ImageIcon majorBlockGif = new ImageIcon("majorBlock.gif");

    public ImageIcon minorEnergyGif = new ImageIcon("minorEnergy.gif");
    public ImageIcon lesserEnergyGif = new ImageIcon("lesserEnergy.gif");
    public ImageIcon majorEnergyGif = new ImageIcon("majorEnergy.gif");

    public ImageIcon minorStrGif = new ImageIcon("minorStrength.gif");
    public ImageIcon lesserStrGif = new ImageIcon("lesserStrength.gif");
    public ImageIcon majorStrGif = new ImageIcon("majorStrength.gif");

    //Import weapon pics
    public ImageIcon warriorRareWeaponPic = new ImageIcon("warriorRareWeapon.png");
    public ImageIcon warriorEpicWeaponPic = new ImageIcon("warriorEpicWeapon.png");
    public ImageIcon warriorLegendaryWeaponPic = new ImageIcon("warriorLegendaryWeapon.png");

    public ImageIcon healerRareWeaponPic = new ImageIcon("healerRareWeapon.png");
    public ImageIcon healerEpicWeaponPic = new ImageIcon("healerEpicWeapon.png");
    public ImageIcon healerLegendaryWeaponPic = new ImageIcon("healerLegendaryWeapon.png");

    public ImageIcon mageRareWeaponPic = new ImageIcon("mageRareWeapon.png");
    public ImageIcon mageEpicWeaponPic = new ImageIcon("mageEpicWeapon.png");
    public ImageIcon mageLegendaryWeaponPic = new ImageIcon("mageLegendaryWeapon.png");

    public ImageIcon rangerRareWeaponPic = new ImageIcon("rangerRareWeapon.png");
    public ImageIcon rangerEpicWeaponPic = new ImageIcon("rangerEpicWeapon.png");
    public ImageIcon rangerLegendaryWeaponPic = new ImageIcon("rangerLegendaryWeapon.png");

    //Import weapon pics
    public ImageIcon warriorRareArmorPic = new ImageIcon("warriorRareArmor.gif");
    public ImageIcon warriorEpicArmorPic = new ImageIcon("warriorEpicArmor.gif");
    public ImageIcon warriorLegendaryArmorPic = new ImageIcon("warriorLegendaryArmor.gif");

    public ImageIcon healerRareArmorPic = new ImageIcon("healerRareArmor.gif");
    public ImageIcon healerEpicArmorPic = new ImageIcon("healerEpicArmor.gif");
    public ImageIcon healerLegendaryArmorPic = new ImageIcon("healerLegendaryArmor.gif");

    public ImageIcon mageRareArmorPic = new ImageIcon("mageRareArmor.gif");
    public ImageIcon mageEpicArmorPic = new ImageIcon("mageEpicArmor.gif");
    public ImageIcon mageLegendaryArmorPic = new ImageIcon("mageLegendaryArmor.gif");

    public ImageIcon rangerRareArmorPic = new ImageIcon("rangerRareArmor.gif");
    public ImageIcon rangerEpicArmorPic = new ImageIcon("rangerEpicArmor.gif");
    public ImageIcon rangerLegendaryArmorPic = new ImageIcon("rangerLegendaryArmor.gif");

    public int minorHealingPotion = 10, lesserHealingPotion = 30, majorHealingPotion = 60;
    public int ownedMinorHealingPotion = 1, ownedLesserHealingPotion = 0, ownedMajorHealingPotion = 0;

    public int minorBlockPotion = 5, lesserBlockPotion = 20, majorBlockPotion = 50;
    public int ownedMinorBlockPotion = 1, ownedLesserBlockPotion = 0, ownedMajorBlockPotion = 0;

    public int minorEnergyPotion = 3, lesserEnergyPotion = 5, majorEnergyPotion = 10;
    public int ownedMinorEnergyPotion = 1, ownedLesserEnergyPotion = 0, ownedMajorEnergyPotion = 0;

    public int minorStrengthPotion = 5, lesserStrengthPotion = 10, majorStrengthPotion = 20;
    public int ownedMinorStrengthPotion = 1, ownedLesserStrengthPotion = 0, ownedMajorStrengthPotion = 0;

    public void addPotion(int potion) {

        switch (potion) {
            //Healing potions
            case 1:
                ownedMinorHealingPotion++;

            case 2:
                ownedLesserHealingPotion++;

            case 3:
                ownedMajorHealingPotion++;

                //Block potions
            case 4:
                ownedMinorBlockPotion++;

            case 5:
                ownedLesserBlockPotion++;

            case 6:
                ownedMajorBlockPotion++;

                //Energy potions
            case 7:
                ownedMinorEnergyPotion++;

            case 8:
                ownedLesserEnergyPotion++;

            case 9:
                ownedMajorEnergyPotion++;

                //Strength potions
            case 10:
                ownedMinorStrengthPotion++;

            case 11:
                ownedLesserStrengthPotion++;

            case 12:
                ownedMajorStrengthPotion++;
        }
    }

    public void usePotion(int use) {
        switch (use) {
            //Healing potions
            case 1:
                ownedMinorHealingPotion--;

            case 2:
                ownedLesserHealingPotion--;

            case 3:
                ownedMajorHealingPotion--;

                //Block potions
            case 4:
                ownedMinorBlockPotion--;

            case 5:
                ownedLesserBlockPotion--;

            case 6:
                ownedMajorBlockPotion--;

                //Energy potions
            case 7:
                ownedMinorEnergyPotion--;

            case 8:
                ownedLesserEnergyPotion--;

            case 9:
                ownedMajorEnergyPotion--;

                //Strength potions
            case 10:
                ownedMinorStrengthPotion--;

            case 11:
                ownedLesserStrengthPotion--;

            case 12:
                ownedMajorStrengthPotion--;
        }
    }
}