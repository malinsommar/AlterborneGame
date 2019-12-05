package game;

public class Mage{

    String currentWeaponName = "Wooden wand";
    public int currentWeaponDamage = 5;

    String currentArmorName = "Cloth robe";
    public int currentArmorBlock = 0;
    public int currentArmorDamage = 0;

    public int hp = 100;
    public int block = 0;
    public int damage = 0;

    public int combinedBlock = block + currentArmorBlock;
    public int combinedDamage = damage + currentWeaponDamage + currentArmorDamage;

    //All mage weapons
    public void mageRareWeapon(){
        currentWeaponName = "Ivory fire wand";
        currentWeaponDamage = 7;
    }
    public void mageEpicWeapon(){
        currentWeaponName= "Enchanted mana wand";
        currentWeaponDamage = 15;
    }
    public void mageLegendaryWeapon(){
        currentWeaponName = "Pyromaniac's tinderbox";
        currentWeaponDamage = 30;
    }
    //All mage armors
    public void mageRareArmor(){
        currentArmorName = "Mooncloth robe";
        currentArmorBlock = 2;
        currentArmorDamage = 5;

    }
    public void mageEpicArmor(){
        currentArmorName = "Enchanted robe";
        currentArmorBlock = 4;
        currentArmorDamage = 10;
    }
    public void mageLegendaryArmor(){
        currentArmorName = "Robe of the archmage";
        currentArmorBlock = 6;
        currentArmorDamage = 20;
    }
}