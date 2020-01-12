package game;

import java.util.Arrays;

public class ShopController {

    ShopView sv = new ShopView();

    int currentGold;
    public int whatItemBought;

    boolean done = false;
    boolean itemBought = false;


    public void startShopView(int getGold, String[] armorNames, String[] weaponNames, int[] weaponDamage, int[] currentArmorDamage, int[] armorBlock, String[] rareWeaponArmorNames, String[] epicWeaponArmorNames, String[] legendaryWeaponArmorNames, int[] rareWeaponArmorDamageBlock, int[] epicWeaponArmorDamageBlock, int[] legendaryWeaponArmorDamageBlock, int[] armorDamage) throws InterruptedException {

        currentGold = getGold;

        sv.startShopFrame();
        hoverEffect();


        //When you press one of the armors/weapons --> buyEquipment()
        sv.warriorArmor1.addActionListener(e -> buyEquipment(1));
        sv.warriorArmor2.addActionListener(e -> buyEquipment(2));
        sv.warriorArmor3.addActionListener(e -> buyEquipment(3));
        sv.warriorWeapon1.addActionListener(e -> buyEquipment(4));
        sv.warriorWeapon2.addActionListener(e -> buyEquipment(5));
        sv.warriorWeapon3.addActionListener(e -> buyEquipment(6));

        sv.mageArmor1.addActionListener(e -> buyEquipment(7));
        sv.mageArmor2.addActionListener(e -> buyEquipment(8));
        sv.mageArmor3.addActionListener(e -> buyEquipment(9));
        sv.mageWeapon1.addActionListener(e -> buyEquipment(10));
        sv.mageWeapon2.addActionListener(e -> buyEquipment(11));
        sv.mageWeapon3.addActionListener(e -> buyEquipment(12));

        sv.rangerArmor1.addActionListener(e -> buyEquipment(13));
        sv.rangerArmor2.addActionListener(e -> buyEquipment(14));
        sv.rangerArmor3.addActionListener(e -> buyEquipment(15));
        sv.rangerWeapon1.addActionListener(e -> buyEquipment(16));
        sv.rangerWeapon2.addActionListener(e -> buyEquipment(17));
        sv.rangerWeapon3.addActionListener(e -> buyEquipment(18));

        sv.healerArmor1.addActionListener(e -> buyEquipment(19));
        sv.healerArmor2.addActionListener(e -> buyEquipment(20));
        sv.healerArmor3.addActionListener(e -> buyEquipment(21));
        sv.healerWeapon1.addActionListener(e -> buyEquipment(22));
        sv.healerWeapon2.addActionListener(e -> buyEquipment(23));
        sv.healerWeapon3.addActionListener(e -> buyEquipment(24));

        //When you press one of the potions -->buyPotions()
        sv.potion1.addActionListener(e -> buyPotion(1));
        sv.potion2.addActionListener(e -> buyPotion(2));
        sv.potion3.addActionListener(e -> buyPotion(3));
        sv.potion4.addActionListener(e -> buyPotion(4));
        sv.potion5.addActionListener(e -> buyPotion(5));
        sv.potion6.addActionListener(e -> buyPotion(6));
        sv.potion7.addActionListener(e -> buyPotion(7));
        sv.potion8.addActionListener(e -> buyPotion(8));
        sv.potion9.addActionListener(e -> buyPotion(9));
        sv.potion10.addActionListener(e -> buyPotion(10));
        sv.potion11.addActionListener(e -> buyPotion(11));
        sv.potion12.addActionListener(e -> buyPotion(12));

        sv.Exit.addActionListener(e->done = true);
    }

    public void buyEquipment(int whatEquipment){

        //Warrior
        if (whatEquipment==1 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Warrior equipped the *Shiny armor*.");
            whatItemBought = 1;
            itemBought = true;
        }
        else if (whatEquipment==2 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Warrior equipped the *Hardened armor*.");
            whatItemBought = 2;
            itemBought = true;
        }
        else if (whatEquipment==3 && currentGold>149){
            currentGold-=150;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Warrior equipped the *Royal Enchanted Armor*.");
            whatItemBought = 3;
            itemBought = true;
        }
        else if (whatEquipment==4 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Warrior equipped the *Iron sword*.");
            whatItemBought = 4;
            itemBought = true;
        }
        else if (whatEquipment==5 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Warrior equipped the *Tempered steel blade*.");
            whatItemBought = 5;
            itemBought = true;
        }
        else if (whatEquipment==6 && currentGold>149){
            currentGold-=150;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Warrior equipped the *Sword of a thousand truths*.");
            whatItemBought = 6;
            itemBought = true;
        }
        //Mage
        else if (whatEquipment==7 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Mage equipped the *Mooncloth robe*.");
            whatItemBought = 7;
            itemBought = true;
        }
        else if (whatEquipment==8 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Mage equipped the *Enchanted robe*.");
            whatItemBought = 8;
            itemBought = true;
        }
        else if (whatEquipment==9 && currentGold>149){
            currentGold-=150;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Mage equipped the *Robe of the archmage*.");
            whatItemBought = 9;
            itemBought = true;
        }
        else if (whatEquipment==10 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Mage equipped the *Ivory fire wand*.");
            whatItemBought = 10;
            itemBought = true;
        }
        else if (whatEquipment==11 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Mage equipped the *Enchanted mana wand*.");
            whatItemBought = 11;
            itemBought = true;
        }
        else if (whatEquipment==12 && currentGold>149) {
            currentGold -= 150;
            sv.goldLabel.setText("Gold: " + currentGold);
            sv.currentAction.setText("Mage equipped the *Pyromaniac's tinderbox*.");
            whatItemBought = 12;
            itemBought = true;
        }
        //Ranger
        else if (whatEquipment==13 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: " + currentGold);
            sv.currentAction.setText("Ranger equipped the *Fine leather armor*.");
            whatItemBought = 13;
            itemBought = true;
        }
        else if (whatEquipment==14 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Ranger equipped the *Elven leather armor*.");
            whatItemBought = 14;
            itemBought = true;
        }
        else if (whatEquipment==15 && currentGold>149){
            currentGold-=150;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Ranger equipped the *Demonskin armor*.");
            whatItemBought = 15;
            itemBought = true;
        }
        else if (whatEquipment==16 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Ranger equipped the *Elven bow*.");
            whatItemBought = 16;
            itemBought = true;
        }
        else if (whatEquipment==17 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Ranger equipped the *Dragonslayer's bow*.");
            whatItemBought = 17;
            itemBought = true;
        }
        else if (whatEquipment==18 && currentGold>149){
            currentGold-=150;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Ranger equipped the *Bullseye bow*.");
            whatItemBought = 18;
            itemBought = true;
        }
        //Healer
        else if (whatEquipment==19 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Healer equipped the *Priests robe*.");
            whatItemBought = 19;
            itemBought = true;
        }
        else if (whatEquipment==20 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Healer equipped the *Clerics armor*.");
            whatItemBought = 20;
        }
        else if (whatEquipment==21 && currentGold>149){
            currentGold-=150;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Healer equipped the *Plate armor of Parl'ont the crusader*.");
            whatItemBought = 21;
            itemBought = true;
        }
        else if (whatEquipment==22 && currentGold>49){
            currentGold-=50;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Healer equipped the *Stick of truth*.");
            whatItemBought = 22;
            itemBought = true;
        }
        else if (whatEquipment==23 && currentGold>99){
            currentGold-=100;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Healer equipped the *Cleric's blessed walking stick*.");
            whatItemBought = 23;
            itemBought = true;
        }
        else if (whatEquipment==24 && currentGold>149){
            currentGold-=150;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("Healer equipped the *Root of the world tree*.");
            whatItemBought = 24;
            itemBought = true;
        }
        else{
            sv.currentAction.setText("Shopkeeper: You cant afford that!");
        }

    }

    public void buyPotion(int whatPotion){

        if (whatPotion==1 && currentGold>9){
            currentGold-=10;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a minor health potion");
            whatItemBought = 26;
            itemBought = true;
        }
        else if (whatPotion==2 && currentGold>19){
            currentGold-=20;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a lesser health potion");
            whatItemBought = 27;
            itemBought = true;
        }
        else if (whatPotion==3 && currentGold>29){
            currentGold-=30;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a major health potion");
            whatItemBought = 28;
            itemBought = true;
        }
        else if (whatPotion==4 && currentGold>9){
            currentGold-=10;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a minor block potion");
            whatItemBought = 29;
            itemBought = true;
        }
        else if (whatPotion==5 && currentGold>19){
           currentGold-=20;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a lesser block potion");
            whatItemBought = 30;
            itemBought = true;
        }
        else if (whatPotion==6 && currentGold>29){
            currentGold-=30;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a major block potion");
            whatItemBought = 31;
            itemBought = true;
        }
        else if (whatPotion==7 && currentGold>9){
           currentGold-=10;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a minor energy potion");
            whatItemBought = 32;
            itemBought = true;
        }
        else if (whatPotion==8 && currentGold>19){
           currentGold-=20;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a lesser energy potion");
            whatItemBought = 33;
            itemBought = true;
        }
        else if (whatPotion==9 && currentGold>29){
            currentGold-=30;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a major energy potion");
            whatItemBought = 34;
            itemBought = true;
        }
        else if (whatPotion==10 && currentGold>9){
           currentGold-=10;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a minor strength potion");
            whatItemBought = 35;
            itemBought = true;
        }
        else if (whatPotion==11 && currentGold>19){
            currentGold-=20;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a lesser strength potion");
            whatItemBought = 36;
            itemBought = true;
        }
        else if (whatPotion==12 && currentGold>29){
            currentGold-=30;
            sv.goldLabel.setText("Gold: "+currentGold);
            sv.currentAction.setText("You bought a major strength potion");
            whatItemBought = 27;
            itemBought = true;
        }
        else{
            sv.currentAction.setText("Shopkeeper: You cant afford that!");
        }
    }

    private void hoverEffect() {

        //Minor health potion
        sv.potion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Minor health potion, heals 10hp. Cost: 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: You gonna buy something?");
            }
        });
        //Lesser health potion
        sv.potion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Lesser health potion, heals 30hp. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: These are great prizes!");
            }
        });
        //Major health potion
        sv.potion3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Major health potion, heals 60hp. Cost 30G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Take a look at my wares!");
            }
        });
        //Minor block potion
        sv.potion4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Minor block potion, blocks grants 5 block. Cost 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: You gonna buy something?");
            }
        });
        //Lesser Block potion
        sv.potion5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Lesser block potion, grants 20 block. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: ...?");
            }
        });
        //Major Block potion
        sv.potion6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Major block potion, grants 50 block. Cost 30G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Come on! I haven't got all day.");
            }
        });
        //Minor energy potion
        sv.potion7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Minor energy potion, grants 2 energy. Cost 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: These are the finest wares in town!");
            }
        });
        //Lesser energy potion
        sv.potion8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Lesser energy potion, grants 5 energy. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Lets do some business!");
            }
        });
        //Major energy potion
        sv.potion9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Major energy potion, grants 10 energy. Cost 30G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: It's getting late...");
            }
        });
        //Minor strength potion
        sv.potion10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Minor strength potion, grants 5 strength. Cost 10G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Do you have any gold? ");
            }
        });
        //Lesser strength potion
        sv.potion11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Lesser strength potion, grants 20 strength. Cost 20G");

            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: I got an endless supply of potions!");
            }
        });
        //Major strength potion
        sv.potion12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Major strength potion, grants 35 strength. Cost 30G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Buy something, you wont regret it!");
            }
        });
        //Warrior's things
        sv.warriorArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shiny Armor, Block: 6. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: That's a real shiny armor!");
            }
        });
        sv.warriorArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Hardened Armor, Block: 10. Cost 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: You like that one?");
            }
        });
        sv.warriorArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Royal Enchanted Armor, Block: 20. Cost 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: No need to worry in that armor!");
            }
        });
        sv.warriorWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Iron sword, Attack: 8. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Give it a swing!");
            }
        });
        sv.warriorWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Tempered steel blade, Attack: 15. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Watch out, it's sharp!");
            }
        });
        sv.warriorWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Sword of a thousand truths, Attack: 27. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: The truth hurts!");
            }
        });
        //Mage
        sv.mageArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Mooncloth robe, Block: 2. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: You would look great in that one!");
            }
        });
        sv.mageArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Enchanted robe, Block: 4. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Feel how soft it is!");
            }
        });
        sv.mageArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Robe of the archmage, Block: 6. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Don't you love the look of that one?");
            }
        });
        sv.mageWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Ivory fire wand, Attack: 7. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: You wanna try it?");
            }
        });
        sv.mageWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Enchanted mana wand, Attack: 15. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: If you wanna try it, do it outside..");
            }
        });
        sv.mageWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Pyromaniac's tinderbox, Attack: 30. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: That's the most powerful weapon i know of.");
            }
        });
        //Healer
        sv.healerArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Priests robe, Block: 2. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: I love the colors on that one.");
            }
        });
        sv.healerArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Clerics armor, Block: 8. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: That's a nice armor.");
            }
        });
        sv.healerArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Plate armor of Parl'ont the crusader, Block: 13. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Do you want to try it on?");
            }
        });
        sv.healerWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Stick of truth, Attack: 7. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Such a nice staff.");
            }
        });
        sv.healerWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Cleric's blessed walking stick, Attack: 13. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: You can use it as a walking stick!");
            }
        });
        sv.healerWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Root of the world tree, Attack: 23. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: I wonder if it's really from that tree..?");
            }
        });
        //Ranger
        sv.rangerArmor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Fine leather armor, Block: 5. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: The leatherworker made that from my own cow!");
            }
        });
        sv.rangerArmor2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Elven leather armor, Block: 11. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: The elves are kind of #$@&%* actually..");
            }
        });
        sv.rangerArmor3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Demonskin armor, Block: 20. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: Does demons really have skin..?");
            }
        });
        sv.rangerWeapon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Elven bow, Attack: 8. Cost: 50G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: The elves are great weapon makers!");
            }
        });
        sv.rangerWeapon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Dragonslayer's bow, Attack: 16. Cost: 100G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: The tales say it killed the last dragon!");
            }
        });
        sv.rangerWeapon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Bullseye's bow, Attack: 26. Cost: 150G");

            }public void mouseExited(java.awt.event.MouseEvent evt) {
                sv.currentAction.setText("Shopkeeper: You can hit anything with that bow!");
            }
        });
    }
}