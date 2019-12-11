package party;

public class Ranger {

    public String currentWeaponName = "Simple bow";
    public int currentWeaponDamage = 5;

    public String currentArmorName = "Broken leather armor";
    public int currentArmorBlock = 0;

    public int hp = 70;
    public int block = 0;
    public int damage = 0;

    public int combinedBlock = block + currentArmorBlock;
    public int combinedDamage = damage + currentWeaponDamage;

    //All ranger weapons
    public void rangerRareWeapon(){
        currentWeaponName = "Elven bow";
        currentWeaponDamage = 8;
    }
    public void rangerEpicWeapon(){
        currentWeaponName = "Dragonslayer's bow";
        currentWeaponDamage = 16;
    }
    public void rangerLegendaryWeapon(){
        currentWeaponName = "Bullseye bow";
        currentWeaponDamage = 26;
    }
    //All ranger armors
    public void rangerRareArmor(){
        currentArmorName = "Fine leather armor";
        currentArmorBlock = 5;
    }
    public void rangerEpicArmor(){
        currentArmorName = "Elven leather armor";
        currentArmorBlock = 11;
    }
    public void rangerLegendaryArmor(){
        currentArmorName = "Demonskin armor";
        currentArmorBlock = 18;
    }
}