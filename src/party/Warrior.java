package party;

public class Warrior {

    public String warriorRareWeaponName = "Iron sword",warriorEpicWeaponName = "Tempered steel blade",warriorLegendaryWeaponName = "Sword of a thousand truths",warriorRareArmorName = "Shiny Armor",warriorEpicArmorName = "Hardened Armor",warriorLegendaryArmorName = "Royal Enchanted Armor";
    public int warriorRareWeaponDamage = 8,warriorEpicWeaponDamage = 15,warriorLegendaryWeaponDamage = 27,warriorRareArmorBlock = 6,warriorEpicArmorBlock = 10,warriorLegendaryArmorBlock,mageRareArmorDamage = 20;

    public String currentWeaponName = "Wooden Sword";
    public int currentWeaponDamage = 5;

    public String currentArmorName = "Rusty Armor";
    public int currentArmorBlock = 3;

    public int hp = 150;
    public int block = 3;
    public int damage = 5;

    public int combinedBlock = block + currentArmorBlock;
    public int combinedDamage = damage + currentWeaponDamage;

    public void warriorRareWeapon(){
        currentWeaponName = warriorRareWeaponName;
        currentWeaponDamage = warriorRareWeaponDamage;
    }
    public void warriorEpicWeapon(){
        currentWeaponName = warriorEpicWeaponName;
        currentWeaponDamage = warriorEpicWeaponDamage;
    }
    public void warriorLegendaryWeapon(){
        currentWeaponName = warriorLegendaryWeaponName;
        currentWeaponDamage = warriorLegendaryWeaponDamage;
    }
    public void warriorRareArmor(){
        currentArmorName = warriorRareArmorName;
        currentArmorBlock = warriorRareArmorBlock;
    }
    public void warriorEpicArmor(){
        currentArmorName = warriorEpicArmorName;
        currentArmorBlock = warriorRareArmorBlock;
    }
    public void warriorLegendaryArmor(){
        currentArmorName = warriorLegendaryArmorName;
        currentArmorBlock = warriorLegendaryArmorBlock;
    }
}