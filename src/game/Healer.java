package game;

public class Healer {

    String currentWeaponName = "Wodden staff";
    public int currentWeaponDamage = 5;

    String currentArmorName = "Cloth scraps";
    public int currentArmorBlock = 5;

    public int hp = 100;
    public int block = 0;
    public int damage = 0;

    public int combinedBlock = block + currentArmorBlock;
    public int combinedDamage = damage + currentWeaponDamage;

    //All healer weapons
    public void healerRareWeapon(){
        currentWeaponName = "Stick of truth";
        currentWeaponDamage = 7;
    }
    public void healerEpicWeapon(){
        currentWeaponName = "Cleric's blessed walking stick";
        currentWeaponDamage = 13;
    }
    public void healerLegendaryWeapon(){
        currentWeaponName = "Root of the world tree";
        currentWeaponDamage = 23;
    }
    //All healer armors
    public void healerRareArmor(){
        currentArmorName = "Priests robe";
        currentArmorBlock = 2;
    }
    public void healerEpicArmor(){
        currentArmorName = "Clerics armor";
        currentArmorBlock = 8;
    }
    public void healerLegendaryArmor(){
        currentArmorName = "Plate armor of Parl'ont the crusader";
        currentArmorBlock = 13;
    }
}