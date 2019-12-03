package davidtest.healthbar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthBar {
    JPanel healthBarPanel, buttonPanel;
    Container con;
    JProgressBar healthBar;
    int hp;
    DamageHandler damageHandler = new DamageHandler();
    HealingHandler healingHandler = new HealingHandler();



    public static void main(String[] args) {
        new HealthBar();
    }

    public HealthBar() {


        healthBarPanel = new JPanel();
        healthBarPanel.setBounds(250, 200, 250, 25);
        healthBarPanel.setBorder(new EmptyBorder(0,0,0,0));

        healthBar = new JProgressBar(0,100);
        healthBar.setStringPainted(true);
        healthBar.setBounds(0,0,250,25);
        healthBar.setValue(500);
        healthBar.setBackground(Color.decode("#B51B1B"));
        healthBar.setForeground(Color.green);
        healthBar.setBorder(new EmptyBorder(0,0,0,0));
        healthBarPanel.add(healthBar);
        con.add(healthBarPanel);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(250,300,300,40);
        buttonPanel.setBackground(Color.black);
        con.add(buttonPanel);

        hp = 100;

    }
    public void damageTaken() {
        hp -= 2;
        healthBar.setValue(hp);
    }
    public void healedHealth() {
        hp += 2;
        healthBar.setValue(hp);
        if(hp <= 100) {
        }
    }
    public class DamageHandler implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent event) {
            damageTaken();
        }
    }
    public class HealingHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            healedHealth();
        }
    }
}
