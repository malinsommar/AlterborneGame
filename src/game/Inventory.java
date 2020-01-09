package game;

public class Inventory {

    public int gold;

    public int minorHealingPotion = 10, lesserHealingPotion = 30, majorHealingPotion = 60;
    public int ownedMinorHealingPotion, ownedLesserHealingPotion, ownedMajorHealingPotion;

    public int minorBlockPotion = 5, lesserBlockPotion = 20, majorBlockPotion = 50;
    public int ownedMinorBlockPotion, ownedLesserBlockPotion, ownedMajorBlockPotion;

    public int minorEnergyPotion, lesserEnergyPotion, majorEnergyPotion = 10;
    public int ownedMinorEnergyPotion, ownedLesserEnergyPotion, ownedMajorEnergyPotion;

    public int minorStrengthPotion = 5, lesserStrengthPotion = 10, majorStrengthPotion = 20;
    public int ownedMinorStrengthPotion, ownedLesserStrengthPotion, ownedMajorStrengthPotion;


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