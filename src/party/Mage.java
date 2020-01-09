package party;

public class Mage{

    public String mageRareWeaponName = "Ivory fire wand", mageEpicWeaponName = "Enchanted mana wand", mageLegendaryWeaponName = "Pyromaniac's tinderbox", mageRareArmorName = "Mooncloth robe", mageEpicArmorName = "Enchanted robe", mageLegendaryArmorName = "Robe of the archmage";
    public int mageRareWeaponDamage = 7, mageEpicWeaponDamage = 15, mageLegendaryWeaponDamage = 30, mageRareArmorBlock = 2, mageEpicArmorBlock = 4, mageLegendaryArmorBlock = 6, mageRareArmorDamage = 4, mageEpicArmorDamage = 10, mageLegendaryArmorDamage = 20;

    public String currentWeaponName = "Wooden wand";
    public int currentWeaponDamage = 5;

    public String currentArmorName = "Cloth robe";
    public int currentArmorBlock = 0;
    public int currentArmorDamage = 0;

    public int hp;
    public int block;
    public int damage;

    public int combinedBlock = block + currentArmorBlock;
    public int combinedDamage = damage + currentWeaponDamage + currentArmorDamage;

    //All mage weapons
    public void mageRareWeapon(){
        currentWeaponName = mageRareWeaponName;
        currentWeaponDamage = mageRareWeaponDamage;
    }
    public void mageEpicWeapon(){
        currentWeaponName= mageEpicWeaponName;
        currentWeaponDamage = mageEpicWeaponDamage;
    }
    public void mageLegendaryWeapon(){
        currentWeaponName = mageLegendaryWeaponName;
        currentWeaponDamage = mageLegendaryWeaponDamage;
    }
    //All mage armors
    public void mageRareArmor(){
        currentArmorName = mageRareArmorName;
        currentArmorBlock = mageRareArmorBlock;
        currentArmorDamage = mageRareArmorDamage;

    }
    public void mageEpicArmor(){
        currentArmorName = mageEpicArmorName;
        currentArmorBlock = mageEpicArmorBlock;
        currentArmorDamage = mageEpicArmorDamage;
    }
    public void mageLegendaryArmor(){
        currentArmorName = mageLegendaryArmorName;
        currentArmorBlock = mageLegendaryArmorBlock;
        currentArmorDamage = mageLegendaryArmorDamage;
    }
}