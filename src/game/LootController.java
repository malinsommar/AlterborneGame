package game;

public class LootController {

    LootFrame lf = new LootFrame();

    private int textDelay = 0, whatLoot, xp, gold;

    private String warriorWeaponName, mageWeaponName, rangerWeaponName, healerWeaponName;
    private String warriorArmorName, mageArmorName,rangerArmorName, healerArmorName;

    private String warriorRareWeaponName, mageRareWeaponName, rangerRareWeaponName, healerRareWeaponName;
    private String warriorEpicWeaponName, mageEpicWeaponName, rangerEpicWeaponName, healerEpicWeaponName;
    private String warriorLegendaryWeaponName, mageLegendaryWeaponName, rangerLegendaryWeaponName, healerLegendaryWeaponName;
    private String warriorRareArmorName, mageRareArmorName, rangerRareArmorName, healerRareArmorName;
    private String warriorEpicArmorName, mageEpicAmorName, rangerEpicArmorName, healerEpicArmorName;
    private String warriorLegendaryArmorName, mageLegendaryArmorName, rangerLegendaryArmorName, healerLegendaryArmorName;

    private int warriorRareWeaponDamage, mageRareWeaponDamage, rangerRareWeaponDamage, healerRareWeaponDamage;
    private int warriorEpicWeaponDamage, mageEpicWeaponDamage, rangerEpicWeaponDamage, healerEpicWeaponDamage;
    private int warriorLegendaryWeaponDamage, mageLegendaryWeaponDamage, rangerLegendaryWeaponDamage, healerLegendaryWeaponDamage;
    private int warriorRareArmorBlock, mageRareArmorBlock, rangerRareArmorBlock, healerRareArmorBlock;
    private int warriorEpicArmorBlock, mageEpicArmorBlock, rangerEpicArmorBlock, healerEpicArmorBlock;
    private int warriorLegendaryArmorBlock, mageLegendaryArmorBlock, rangerLegendaryArmorBlock, healerLegendaryArmorBlock;
    private int mageRareArmorDamage, mageEpicArmorDamage, mageLegendaryArmorDamage, healerRareArmorDamage, healerEpicArmorDamage, healerLegendaryArmorDamage;



    private int warriorWeaponDamage, mageWeaponDamage, rangerWeaponDamage, healerWeaponDamage;
    private int warriorArmorBlock, mageArmorBlock, rangerArmorBlock, healerArmorBlock;
    private int mageArmorDamage, healerArmorDamage;

    private boolean showEquipButton = false;


    public void startLootScreen(){

        lf.lootScreenFrame();
    }

    public void getInfo(int getGold, int getXp, String[] armorNames, String[] weaponNames, int[] weaponDamage, int[] armorDamage, int[] armorBlock, String[] rareWeaponArmorNames, String[] epicWeaponArmorNames, String[] legendaryWeaponArmorNames, int[] rareWeaponArmorDamageBlock, int[] epicWeaponArmorDamageBlock, int[] legendaryWeaponArmorDamageBlock){

        gold = getGold;
        xp = getXp;

        warriorArmorName = armorNames[0];
        mageArmorName = armorNames[1];
        rangerArmorName = armorNames[2];
        healerArmorName = armorNames[3];

        warriorWeaponName = weaponNames[0];
        mageWeaponName = weaponNames[1];
        rangerWeaponName = weaponNames[2];
        healerWeaponName = weaponNames[3];

        warriorWeaponDamage = weaponDamage[0];
        mageWeaponDamage = weaponDamage[1];
        rangerWeaponDamage = weaponDamage[2];
        healerWeaponDamage = weaponDamage[3];

        warriorArmorBlock = armorBlock[0];
        mageArmorBlock = armorBlock[1];
        rangerArmorBlock = armorBlock[2];
        healerArmorBlock = armorBlock[3];

        mageArmorDamage = armorDamage[0];
        healerArmorDamage = armorDamage[1];






    }

}

    /*
    //Generate what weapon/armor/potion player get from fight. The odds differ depending what fight player came from which the parameter int keeps track of.
    private void generateLoot(int fight){
        int ranLoot = (int) (Math.random() * 110) + 1;

        if(fight==1){
            int xpUp = (int)(Math.random()*7)+10;
            int xpBefore = lu.xp;
            lu.xp += xpUp;
            xp = new JLabel("Xp: "+xpBefore+" --> "+lu.xp);


            int intGold = (int) (Math.random() * 20) + 15;
            gold = new JLabel("Gold: "+intGold);
            i.gold += intGold;

            //Random number 1-29
            if (ranLoot >1 && ranLoot <30){
                item = new JLabel("     No items found.");
                hideLabels();
            }
            //Random number 30-39. Warrior rare loot.
            else if(ranLoot >29 && ranLoot <40){
                int ran = (int)(Math.random()*2)+1;
                if (ran==1){
                    currentEquipment = new JLabel("Current Armor:");
                    currentEquipmentName = new JLabel(w.currentArmorName);
                    currentEquipmentStats = new JLabel("Block: "+w.currentArmorBlock);

                    newEquipment = new JLabel("New Armor:");
                    newEquipmentName = new JLabel("Shiny Armor");
                    newEquipmentStats = new JLabel("Block: 6");

                    item = new JLabel("Warrior found: Shiny Armor");
                    item.setForeground(Color.blue);
                    whatLoot = 1;
                    showEquipButton = true;
                }
                if (ran==2){
                    currentEquipment = new JLabel("Current Weapon:");
                    currentEquipmentName = new JLabel(w.currentWeaponName);
                    currentEquipmentStats = new JLabel("Damage: "+w.currentWeaponDamage);

                    newEquipment = new JLabel("New Weapon:");
                    newEquipmentName = new JLabel("Iron sword");
                    newEquipmentStats = new JLabel("Damage: 8");

                    item = new JLabel("Warrior found: Iron sword");
                    item.setForeground(Color.blue);
                    whatLoot = 2;
                    showEquipButton = true;
                }
            }
            //Random number 40-49, Ranger rare loot.
            else if(ranLoot >39 && ranLoot <50){
                int ran = (int)(Math.random()*2)+1;
                if (ran==1){
                    currentEquipment = new JLabel("Current Armor:");
                    currentEquipmentName = new JLabel(r.currentArmorName);
                    currentEquipmentStats = new JLabel("Block: "+r.currentArmorBlock);

                    newEquipment = new JLabel("New Armor:");
                    newEquipmentName = new JLabel("Fine leather armor");
                    newEquipmentStats = new JLabel("Block: 5");

                    item = new JLabel("Ranger found: Fine leather armor");
                    item.setForeground(Color.blue);
                    whatLoot = 3;
                    showEquipButton = true;
                }
                if (ran==2){
                    currentEquipment = new JLabel("Current Weapon:");
                    currentEquipmentName = new JLabel(r.currentWeaponName);
                    currentEquipmentStats = new JLabel("Damage: "+r.currentWeaponDamage);

                    newEquipment = new JLabel("New Weapon:");
                    newEquipmentName = new JLabel("Elven Bow");
                    newEquipmentStats = new JLabel("Damage: 8");

                    item = new JLabel("Ranger found: Elven bow");
                    item.setForeground(Color.blue);
                    whatLoot = 4;
                    showEquipButton = true;
                }
            }
            //Random number 50-59, Mage rare loot.
            else if(ranLoot >49 && ranLoot <60){
                int ran = (int)(Math.random()*2)+1;
                if (ran==1){
                    currentEquipment = new JLabel("Current Armor:");
                    currentEquipmentName = new JLabel(m.currentArmorName);
                    currentEquipmentStats = new JLabel("Block: "+m.currentArmorBlock+"Str: "+m.currentArmorDamage);

                    newEquipment = new JLabel("New Armor:");
                    newEquipmentName = new JLabel("Mooncloth robe");
                    newEquipmentStats = new JLabel("Block: 2, Str: 4");

                    item = new JLabel("Mage found: Mooncloth robe");
                    item.setForeground(Color.blue);
                    whatLoot = 5;
                    showEquipButton = true;
                }
                if (ran==2){
                    currentEquipment = new JLabel("Current Weapon:");
                    currentEquipmentName = new JLabel(m.currentWeaponName);
                    currentEquipmentStats = new JLabel("Damage: "+m.currentWeaponDamage);

                    newEquipment = new JLabel("New Weapon:");
                    newEquipmentName = new JLabel("Ivory fire wand");
                    newEquipmentStats = new JLabel("Damage: 7");

                    item = new JLabel("Mage found: Ivory fire wand");
                    item.setForeground(Color.blue);
                    whatLoot = 6;
                    showEquipButton = true;
                }
            }
            //Random number 60-69, Healer rare loot.
            else if(ranLoot >59 && ranLoot <70){
                int ran = (int)(Math.random()*2)+1;
                if (ran==1){
                    currentEquipment = new JLabel("Current Armor:");
                    currentEquipmentName = new JLabel(m.currentArmorName);
                    currentEquipmentStats = new JLabel("Block: "+m.currentArmorBlock);

                    newEquipment = new JLabel("New Armor:");
                    newEquipmentName = new JLabel("Priests robe");
                    newEquipmentStats = new JLabel("Block: 2, Str: 3");

                    item = new JLabel("Healer found: Priests robe");
                    item.setForeground(Color.blue);
                    whatLoot = 7;
                    showEquipButton = true;
                }
                if (ran==2){
                    currentEquipment = new JLabel("Current Weapon:");
                    currentEquipmentName = new JLabel(m.currentWeaponName);
                    currentEquipmentStats = new JLabel("Damage: "+m.currentWeaponDamage);

                    newEquipment = new JLabel("New Weapon:");
                    newEquipmentName = new JLabel("Stick of truth");
                    newEquipmentStats = new JLabel("Damage: 7");

                    item = new JLabel("Healer found: Stick of truth");
                    item.setForeground(Color.blue);
                    whatLoot = 8;
                    showEquipButton = true;
                }
            }
            //Random number 70-79, Get minor heal potion.
            else if(ranLoot >69 && ranLoot <80){
                i.ownedMinorHealingPotion++;
                item = new JLabel("Found a minor healing potion.");
                hideLabels();
            }
            //Random number 80-89, Get minor strength potion.
            else if(ranLoot >79 && ranLoot <90){
                i.ownedMinorStrengthPotion++;
                item = new JLabel("Found a minor strength potion.");
                hideLabels();
            }
            //Random number 90-99, Get minor energy potion.
            else if(ranLoot >89 && ranLoot <100){
                i.ownedMinorEnergyPotion++;
                item = new JLabel("Found a minor energy potion.");
                hideLabels();
            }
            //Random number 100-110, Get minor block potion.
            else if(ranLoot >99 && ranLoot <111){
                //minor block
                i.ownedMinorBlockPotion++;
                item = new JLabel("Found a minor block potion.");
                hideLabels();
            }
        }
    }

    //Method is called when equipButton is pressed. Changes the party-members armor/weapon and updates currentWeapon labels.
    private void equipLoot(){
        if (whatLoot==1) {
            w.warriorRareArmor();
            currentEquipmentName.setText(w.currentArmorName);
            currentEquipmentStats.setText("Block: "+w.currentArmorBlock);
        }
        else if(whatLoot==2){
            w.warriorRareWeapon();
            currentEquipmentName.setText(w.currentWeaponName);
            currentEquipmentStats.setText("Damage: "+w.currentWeaponDamage);
        }
        else if(whatLoot==3){
            r.rangerRareArmor();
            currentEquipmentName.setText(r.currentArmorName);
            currentEquipmentStats.setText("Block: "+r.currentArmorBlock);
        }
        else if(whatLoot==4){
            r.rangerRareWeapon();
            currentEquipmentName.setText(r.currentWeaponName);
            currentEquipmentStats.setText("Damage: "+r.currentWeaponDamage);
        }
        else if(whatLoot==5){
            m.mageRareArmor();
            currentEquipmentName.setText(m.currentArmorName);
            currentEquipmentStats.setText("Block: "+m.currentArmorBlock+"Str: "+m.currentArmorDamage);
        }
        else if(whatLoot==6){
            m.mageRareWeapon();
            currentEquipmentName.setText(m.currentWeaponName);
            currentEquipmentStats.setText("Damage: "+m.currentWeaponDamage);
        }
        else if(whatLoot==7){
            h.healerRareArmor();
            currentEquipmentName.setText(m.currentWeaponName);
            currentEquipmentStats.setText("Block: "+h.currentArmorBlock+"Str: "+h.currentArmorDamage);
        }
        else if(whatLoot==8){
            h.healerRareWeapon();
            currentEquipmentName.setText(h.currentWeaponName);
            currentEquipmentStats.setText("Damage: "+m.currentWeaponDamage);
        }
    }

    //Timer ticks every second, adding labels and buttons.
    private Timer textDelayTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            textDelay++;
            if (textDelay == 2){
                MusicPick.musicStart("ding","");
                add(xp);
            }
            else if(textDelay == 3){
                MusicPick.musicStart("ding","");
                add(gold);
            }
            else if(textDelay == 4){
                MusicPick.musicStart("ding","");
                add(item);
                if(showEquipButton){
                    add(equipButton);
                    add(currentEquipment);
                    add(currentEquipmentName);
                    add(currentEquipmentStats);
                    add(newEquipment);
                    add(newEquipmentName);
                    add(newEquipmentStats);
                }
                textDelayTimer.stop();
            }
            repaint();
        }
    });

    //Adds hover effect to buttons.
    private void hover(){
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(Color.darkGray);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) { }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(Color.gray);
            }
        });
        equipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                equipButton.setBackground(Color.darkGray);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                equipButton.setBackground(Color.gray);
            }
        });
    }
}
*/