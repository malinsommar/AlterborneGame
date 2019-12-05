package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LootScreen extends JFrame {

    private Warrior w = new Warrior();
    private Healer h = new Healer();
    private Mage m = new Mage();
    private Ranger r = new Ranger();
    private Inventory i = new Inventory();

    private Font pixelMplus;

    private JButton continueButton,equipButton;

    private JLabel vicTitle, item, xp, gold, currentEquipment, currentEquipmentStats, currentEquipmentName, newEquipment, newEquipmentStats, newEquipmentName;

    private int textDelay = 0, whatLoot;

    private boolean showEquipButton = false, showCurrentArmor = false, showCurrentWeapon = false;
    
    public LootScreen(int fight){

        super("Loot Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(500, 400);
        setLocation(400,170);

        importFont();
        generateLoot(fight);
        addButtonsLabels();
        hover();

        add(continueButton);
        add(vicTitle);

        equipButton.setVisible(true);

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(500);
        timer.start();

        //ActionListeners
        continueButton.addActionListener(e -> dispose());
        equipButton.addActionListener(e->equipLoot());

        MusicPick.musicStart("Victory","music");
        setUndecorated(true);
        setVisible(true);
    }
    //Method is called when equipButton is pressed. Changes the party-members armor/weapon.
    public void equipLoot(){
        if (whatLoot==1) {
            w.warriorRareArmor();
        }
        else if(whatLoot==2){
            w.warriorRareWeapon();
        }
        else if(whatLoot==3){
            r.rangerRareArmor();
        }
        else if(whatLoot==4){
            r.rangerRareWeapon();
        }
        else if(whatLoot==5){
            m.mageRareArmor();
        }
        else if(whatLoot==6){
            m.mageRareWeapon();
        }
        else if(whatLoot==7){
            h.healerRareArmor();
        }
        else if(whatLoot==8){
            h.healerRareWeapon();
        }
    }

    //Generate what weapon/armor/potion player get from fight. The odds differ depending what fight player came from which the parameter keeps track of.
    private void generateLoot(int fight){
        int ranLoot = (int) (Math.random() * 110) + 1;
        ranLoot = 30;

        if(fight==1){
            int intXp = 10;
            xp = new JLabel("Xp: "+intXp);
            int intGold = (int) (Math.random() * 20) + 15;
            gold = new JLabel("Gold: "+intGold);
            i.gold += intGold;

            //Random number 1-29
            if (ranLoot >1 && ranLoot <30){
                item = new JLabel("     No items found.");
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
                    item = new JLabel("Ranger found: Fine leather armor");
                    item.setForeground(Color.blue);
                    whatLoot = 3;
                    showEquipButton = true;
                }
                if (ran==2){
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
                    item = new JLabel("Mage found: Mooncloth robe");
                    item.setForeground(Color.blue);
                    whatLoot = 5;
                    showEquipButton = true;
                }
                if (ran==2){
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
                    item = new JLabel("Healer found: Priests robe");
                    item.setForeground(Color.blue);
                    whatLoot = 7;
                    showEquipButton = true;
                }
                if (ran==2){
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
            }
            //Random number 80-89, Get minor strength potion.
            else if(ranLoot >79 && ranLoot <90){
                i.ownedMinorStrengthPotion++;
                item = new JLabel("Found a minor strength potion.");
            }
            //Random number 90-99, Get minor energy potion.
            else if(ranLoot >89 && ranLoot <100){
                i.ownedMinorEnergyPotion++;
                item = new JLabel("Found a minor energy potion.");
            }
            //Random number 100-110, Get minor block potion.
            else if(ranLoot >99 && ranLoot <111){
                //minor block
                i.ownedMinorBlockPotion++;
                item = new JLabel("Found a minor block potion.");
            }
        }
    }

    private void addButtonsLabels(){

        //Label
        vicTitle = new JLabel("Victory Achieved");
        vicTitle.setForeground(Color.black);
        vicTitle.setFont((pixelMplus.deriveFont(30f)));
        Dimension size = vicTitle.getPreferredSize();
        vicTitle.setBounds(130, 30, size.width, size.height);

        xp.setForeground(Color.black);
        xp.setFont((pixelMplus.deriveFont(30f)));
        Dimension xpSize = xp.getPreferredSize();
        xp.setBounds(190, 70, xpSize.width, xpSize.height);

        gold.setForeground(Color.black);
        gold.setFont((pixelMplus.deriveFont(30f)));
        Dimension goldSize = gold.getPreferredSize();
        gold.setBounds(175, 110, goldSize.width, goldSize.height);

        item.setFont((pixelMplus.deriveFont(25f)));
        Dimension itemSize = item.getPreferredSize();
        item.setBounds(90, 150, itemSize.width, itemSize.height);

        //Current Equipment
        Dimension currentEquipmentSize = currentEquipment.getPreferredSize();
        currentEquipment.setBounds(20,140,1000,currentEquipmentSize.height+100);
        currentEquipment.setFont(pixelMplus.deriveFont(20f));

        //Current Equipment Name
        Dimension currentEquipmentNameSize = currentEquipmentName.getPreferredSize();
        currentEquipmentName.setBounds(20,160,1000,currentEquipmentNameSize.height+100);
        currentEquipmentName.setFont(pixelMplus.deriveFont(20f));

        //Current Equipment stats
        Dimension currentEquipmentStatsSize = currentEquipmentStats.getPreferredSize();
        currentEquipmentStats.setBounds(20,180,1000,currentEquipmentStatsSize.height+100);
        currentEquipmentStats.setFont(pixelMplus.deriveFont(20f));

        //New Equipment
        Dimension newEquipmentSize = newEquipment.getPreferredSize();
        newEquipment.setBounds(250,140,1000,newEquipmentSize.height+100);
        newEquipment.setFont(pixelMplus.deriveFont(20f));

        //new equipment name
        Dimension newEquipmentNameSize = newEquipmentName.getPreferredSize();
        newEquipmentName.setBounds(250,160,1000,newEquipmentNameSize.height+100);
        newEquipmentName.setFont(pixelMplus.deriveFont(20f));

        //new equipment stats
        Dimension newEquipmentStatsSize = newEquipmentStats.getPreferredSize();
        newEquipmentStats.setBounds(250,180,1000,newEquipmentStatsSize.height+100);
        newEquipmentStats.setFont(pixelMplus.deriveFont(20f));

        //Button
        continueButton = new JButton("Onwards!");
        continueButton.setSize(300, 100);
        continueButton.setLocation(100, 280);
        continueButton.setForeground(Color.white);
        continueButton.setFont(pixelMplus.deriveFont(30f));
        continueButton.setBackground(Color.gray);
        continueButton.setBorder(null);
        continueButton.setFocusPainted(false);

        equipButton = new JButton("Equip");
        equipButton.setSize(100, 60);
        equipButton.setLocation(200, 200);
        equipButton.setForeground(Color.white);
        equipButton.setFont(pixelMplus.deriveFont(30f));
        equipButton.setBackground(Color.gray);
        equipButton.setBorder(null);
        equipButton.setFocusPainted(false);
    }

    //Timer ticks every second.
    private Timer timer = new Timer(1000, new ActionListener() {
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
                timer.stop();
            }
            repaint();
        }
    });

    private void hover(){
        continueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                continueButton.setBackground(Color.darkGray);
            }

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
    public void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException e) {
        }
    }
}