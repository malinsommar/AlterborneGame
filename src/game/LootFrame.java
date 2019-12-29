package game;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LootFrame {

    JFrame lootScreenJFrame = new JFrame();

    private Font pixelMplus;

    private JButton continueButton,equipButton;
    private JLabel vicTitle, item, xp, gold, currentEquipment, currentEquipmentStats, currentEquipmentName, newEquipment, newEquipmentStats, newEquipmentName;

    public void lootScreenFrame(){

        lootScreenJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lootScreenJFrame.setLayout(null);
        lootScreenJFrame.setSize(500, 400);
        lootScreenJFrame.setLocation(400,138);

        importFont();
        addButtonsLabels();

        lootScreenJFrame.add(continueButton);
        lootScreenJFrame.add(vicTitle);

        equipButton.setVisible(true);

        lootScreenJFrame.setUndecorated(true);
        lootScreenJFrame.setVisible(true);
    }

    //Set text in labels to " " when they are not supposed to show.
    private void hideLabels(){
        currentEquipment = new JLabel(" ");
        currentEquipmentName = new JLabel(" ");
        currentEquipmentStats = new JLabel(" ");
        newEquipment = new JLabel(" ");
        newEquipmentName = new JLabel(" ");
        newEquipmentStats = new JLabel(" ");
    }

    //Add all buttons and labels
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
        xp.setBounds(160, 70, xpSize.width, xpSize.height);

        gold.setForeground(Color.black);
        gold.setFont((pixelMplus.deriveFont(30f)));
        Dimension goldSize = gold.getPreferredSize();
        gold.setBounds(175, 110, goldSize.width, goldSize.height);

        item.setFont((pixelMplus.deriveFont(25f)));
        Dimension itemSize = item.getPreferredSize();
        item.setBounds(90, 150, itemSize.width, itemSize.height);

        //Current Equipment
        Dimension currentEquipmentSize = currentEquipment.getPreferredSize();
        currentEquipment.setBounds(10,140,1000,currentEquipmentSize.height+100);
        currentEquipment.setFont(pixelMplus.deriveFont(20f));

        //Current Equipment Name
        Dimension currentEquipmentNameSize = currentEquipmentName.getPreferredSize();
        currentEquipmentName.setBounds(10,160,1000,currentEquipmentNameSize.height+100);
        currentEquipmentName.setFont(pixelMplus.deriveFont(20f));

        //Current Equipment stats
        Dimension currentEquipmentStatsSize = currentEquipmentStats.getPreferredSize();
        currentEquipmentStats.setBounds(10,180,1000,currentEquipmentStatsSize.height+100);
        currentEquipmentStats.setFont(pixelMplus.deriveFont(20f));

        //New Equipment
        Dimension newEquipmentSize = newEquipment.getPreferredSize();
        newEquipment.setBounds(320,140,1000,newEquipmentSize.height+100);
        newEquipment.setFont(pixelMplus.deriveFont(20f));

        //new equipment name
        Dimension newEquipmentNameSize = newEquipmentName.getPreferredSize();
        newEquipmentName.setBounds(320,160,1000,newEquipmentNameSize.height+100);
        newEquipmentName.setFont(pixelMplus.deriveFont(20f));

        //new equipment stats
        Dimension newEquipmentStatsSize = newEquipmentStats.getPreferredSize();
        newEquipmentStats.setBounds(320,180,1000,newEquipmentStatsSize.height+100);
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
    private void importFont() {
        try {
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("PixelMplus10-Regular.ttf"));
        } catch (IOException | FontFormatException ignored) {
        }
    }
}