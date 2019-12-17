package party;

public class Healer {

    public String currentWeaponName = "Wodden staff";
    public int currentWeaponDamage = 5;

    String currentArmorName = "Cloth scraps";
    public int currentArmorBlock = 5;
    public int currentArmorDamage = 0;

    public String getCurrentWeaponName() {
        return currentWeaponName;
    }

    public void setCurrentWeaponName(String currentWeaponName) {
        this.currentWeaponName = currentWeaponName;
    }

    public int getCurrentWeaponDamage() {
        return currentWeaponDamage;
    }

    public void setCurrentWeaponDamage(int currentWeaponDamage) {
        this.currentWeaponDamage = currentWeaponDamage;
    }

    public String getCurrentArmorName() {
        return currentArmorName;
    }

    public void setCurrentArmorName(String currentArmorName) {
        this.currentArmorName = currentArmorName;
    }

    public int getCurrentArmorBlock() {
        return currentArmorBlock;
    }

    public void setCurrentArmorBlock(int currentArmorBlock) {
        this.currentArmorBlock = currentArmorBlock;
    }

    public int getCurrentArmorDamage() {
        return currentArmorDamage;
    }

    public void setCurrentArmorDamage(int currentArmorDamage) {
        this.currentArmorDamage = currentArmorDamage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCombinedBlock() {
        return combinedBlock;
    }

    public void setCombinedBlock(int combinedBlock) {
        this.combinedBlock = combinedBlock;
    }

    public int getCombinedDamage() {
        return combinedDamage;
    }

    public void setCombinedDamage(int combinedDamage) {
        this.combinedDamage = combinedDamage;
    }

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
        currentArmorDamage = 3;
    }
    public void healerEpicArmor(){
        currentArmorName = "Clerics armor";
        currentArmorBlock = 8;
        currentArmorDamage = 7;
    }
    public void healerLegendaryArmor(){
        currentArmorName = "Plate armor of Parl'ont the crusader";
        currentArmorBlock = 13;
        currentArmorDamage = 15;
    }
}