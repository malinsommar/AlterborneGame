package party;

public class Healer {

    public String healerRareWeaponName = "Stick of truth", healerEpicWeaponName = "Cleric's blessed walking stick", healerLegendaryWeaponName = "Root of the world tree", healerRareArmorName = "Priests robe", healerEpicArmorName = "Clerics armor", healerLegendaryArmorName = "Plate armor of Parl'ont the crusader";
    public int healerRareWeaponDamage = 7, healerEpicWeaponDamage = 13, healerLegendaryWeaponDamage = 23, healerRareArmorBlock = 2, healerEpicArmorBlock = 8, healerLegendaryArmorBlock = 13, healerRareArmorDamage = 3, healerEpicArmorDamage = 7, healerLegendaryArmorDamage = 15;

    public String currentWeaponName = "Wooden staff";
    public int currentWeaponDamage = 5;

    public String currentArmorName = "Cloth scraps";
    public int currentArmorBlock = 5;
    public int currentArmorDamage = 0;

    public int hp = 100;
    public int block = 0;
    public int damage = 0;

    public int combinedBlock = block + currentArmorBlock;
    public int combinedDamage = damage + currentWeaponDamage;

    //All healer weapons
    public void healerRareWeapon(){
        currentWeaponName = healerRareWeaponName;
        currentWeaponDamage = healerRareWeaponDamage;
    }
    public void healerEpicWeapon(){
        currentWeaponName = healerEpicWeaponName;
        currentWeaponDamage = healerEpicWeaponDamage;
    }
    public void healerLegendaryWeapon(){
        currentWeaponName = healerLegendaryWeaponName;
        currentWeaponDamage = healerLegendaryWeaponDamage;
    }
    //All healer armors
    public void healerRareArmor(){
        currentArmorName = healerRareArmorName;
        currentArmorBlock = healerRareArmorBlock;
        currentArmorDamage = healerRareArmorDamage;
    }
    public void healerEpicArmor(){
        currentArmorName = healerEpicArmorName;
        currentArmorBlock = healerEpicArmorBlock;
        currentArmorDamage = healerEpicArmorDamage;
    }
    public void healerLegendaryArmor(){
        currentArmorName = healerLegendaryArmorName;
        currentArmorBlock = healerLegendaryArmorBlock;
        currentArmorDamage = healerLegendaryArmorDamage;
    }
}