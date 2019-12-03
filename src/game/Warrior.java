package game;

public class Warrior {

    String currentWeaponName = "Wodden Sword";
    public int currentWeaponDamage = 5;

    String currentArmorName = "Rusty Armor";
    public int currentArmorBlock = 3;

    public int hp = 150;
    public int block = 3;
    public int damage = 5;

    public int combinedBlock = block + currentArmorBlock;
    public int combinedDamage = damage + currentWeaponDamage;

    //All warrior weapons
    public void warriorRareWeapon(){
        currentWeaponName = "Iron sword";
        currentWeaponDamage = 8;
    }
    public void warriorEpicWeapon(){
        currentWeaponName = "Tempered steel blade";
        currentWeaponDamage = 15;
    }
    public void warriorLegendaryWeapon(){
        currentWeaponName = "Sword of a thousand truths";
        currentWeaponDamage = 27;
    }
    //All warrior armors
    public void warriorRareArmor(){
        currentArmorName = "Shiny Armor";
        currentArmorBlock = 6;
    }
    public void warriorEpicArmor(){
        currentArmorName = "Hardened Armor";
        currentArmorBlock = 10;
    }
    public void warriorLegendaryArmor(){
        currentArmorName = "Royal Enchanted Armor";
        currentArmorBlock = 20;
    }
}