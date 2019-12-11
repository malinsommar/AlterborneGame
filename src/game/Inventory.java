package game;

import imports.Potions;

import javax.swing.*;

public class Inventory {

    Potions potions = new Potions();

    public int gold = 20;

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