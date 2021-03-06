package OldClasses.simontest;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simontest extends JFrame {

    private JButton shootarrowbutton, tacklebutton, takedamagebutton, magespellbutton, chargebutton, volleybutton, wolftestbutton;

    public int cloudx = 100;
    public int cloudy = 200;
    public int startpostitionwarriorx = cloudx;
    public int startpostitionwarriory = cloudx;
    int phase = 0;
    int timepast = 0;
    boolean timerpick;

    JLabel greenarrow = new JLabel(new ImageIcon("holylight.png"));
    JLabel energy = new JLabel(new ImageIcon("energize.gif"));
    JLabel player1 = new JLabel(new ImageIcon("mage.gif"));

    public simontest() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon picShop = new ImageIcon("bakgrundMountain.png");
        setContentPane(new JLabel(picShop)); //assign background

        Dimension greenarrowSize = greenarrow.getPreferredSize();
        greenarrow.setSize(greenarrowSize.width, greenarrowSize.height);
        greenarrow.setLocation(cloudx - 200, cloudy - 500);
        add(greenarrow);
        greenarrow.setVisible(false);

        Dimension energySize = energy.getPreferredSize();
        energy.setSize(energySize.width, energySize.height);
        energy.setLocation(400, 200);
        add(energy);
        energy.setVisible(false);

        Dimension warriorSize = player1.getPreferredSize();
        player1.setSize(warriorSize.width, warriorSize.height);
        player1.setLocation(cloudx, cloudy);
        //add(player1);

        tacklebutton = new JButton("green arrow");
        tacklebutton.setSize(150, 40);
        tacklebutton.setLocation(800, 150);
        tacklebutton.setBackground(Color.white);
        add(tacklebutton);
        tacklebutton.addActionListener(e -> {
            if (timerpick) energize.start();
            else bless.start();
        });


        /* onödiga timer saker?
        tackle.setRepeats(true);
        tackle.setCoalesce(true);
        tackle.setInitialDelay(10);
         */


        player1.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //do stuff
                MusicPick.musicStart("gwyn", "music");
            }

        });
        add(player1);









        MusicPick.musicStart("allstar", "music");
        setLayout(null); //Default layout
        setSize(1900, 500);

        setVisible(true);

    }

    Timer unusedtakedamage = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                MusicPick.musicStart("warriorattacked", "");
                phase = 1;
            } else if (phase == 1) {
                timepast++;
                if (timepast > 100) {
                    timepast = 0;
                    phase = 2;
                }
            } else if (phase == 2) {
                player1.setVisible(false);
                timepast++;
                if (timepast > 10) {
                    timepast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                player1.setVisible(true);
                timepast++;
                if (timepast > 10) {
                    timepast = 0;
                    phase = 4;
                }
            } else if (phase == 4) {
                player1.setVisible(false);
                timepast++;
                if (timepast > 10) {
                    timepast = 0;
                    phase = 5;
                }
            } else if (phase == 5) {
                player1.setVisible(true);
                unusedtakedamage.stop();
                phase = 0;
            }
        }
    });

    private Timer placetap = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            greenarrow.setLocation(cloudx, cloudy);
            MusicPick.musicStart("ding", "");
            greenarrow.setVisible(true);
            }
    });

    private Timer energize = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timepast++;
            energy.setLocation(cloudx + 70, cloudy - 50);
            MusicPick.musicStart("ding", "");
            energy.setVisible(true);
            if (timepast > 135){
                timepast = 0;
                energy.setVisible(false);
                energize.stop();
                timerpick = false;
            }
        }
    });

    private Timer bless = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timepast++;
            energy.setLocation(cloudx + 70, cloudy - 50);
            MusicPick.musicStart("ding", "");
            greenarrow.setVisible(true);
            if (timepast == 100){
                timepast = 0;
                greenarrow.setVisible(false);
                bless.stop();
                timerpick = true;
            }
        }
    });

}



/*
    private Timer firespinbackup = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            if (phase == 0){
                MusicPick.musicStart("demoshout", "");
                bigPyroBlast.setVisible(true);
                phase = 1;
                pyroBlastX = 45;
                pyroblastY = 150;
            }
            if (phase == 1 || phase == 2) { //flyger uppåt
                superMegaMath -=2;
                pyroblastY -= superMegaMath;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                if (phase == 1 && superMegaMath == 0) {
                    phase = 2;
                }
            }
            if (phase == 2 || phase == 3) { //höger
                counterMegaMath +=2;
                pyroBlastX -= counterMegaMath;
                //bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                if (phase == 2 && counterMegaMath == 0) {
                    phase = 3;
                }
            }
            if (phase == 3 || phase == 4) { //ner
                superMegaMath +=2;
                pyroblastY -= superMegaMath;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                if (phase == 3 && superMegaMath == 0) {
                    phase = 4;
                }
            }
            if (phase == 4 || phase == 5) { //vänster
                counterMegaMath -=2;
                pyroBlastX -= counterMegaMath;
                //bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                if (phase == 4 && counterMegaMath == 0) {
                    phase = 5;
                }
            }
            if (phase == 5 || phase == 6) {
                superMegaMath -=2;
                pyroblastY -= superMegaMath;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                if (phase == 5 && superMegaMath == 0) {
                    phase = 6;
                }
            }
            if (phase == 6){
                phase = 7;
            }
            if(timePast == 200) {
                pyroblastY = 150;
                pyroBlastX = 45;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                bigPyroBlast.setVisible(false);
                timePast = 0;
                superMegaMath = 30;
                counterMegaMath = -30;
                phase = 0;
                fireBall.stop();
            }
        }



            /*
            timePast++;
            if (timePast < 100) {
                smallPyroBlast.setVisible(true);
            }
            else if (timePast < 200) {
                smallPyroBlast.setVisible(false);
                mediumPyroBlast.setVisible(true);
            }
            else if (timePast < 350) {
                mediumPyroBlast.setVisible(false);
                bigPyroBlast.setVisible(true);
            }
            else if (timePast < 400 ){
                pyroBlastX += 3;
                pyroblastY -= 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else if (timePast < 460){
                pyroBlastX += 3;
                pyroblastY += 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else if (timePast < 530){
                pyroBlastX += 3;
                pyroblastY -= 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else if (timePast < 590){
                pyroBlastX += 4;
                pyroblastY += 1;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
            }
            else {
                bigPyroBlast.setVisible(false);
                timePast = 0;
                pyroBlastX = 45;
                pyroblastY = 150;
                bigPyroBlast.setLocation(pyroBlastX, pyroblastY);
                pyroBlast.stop();
            }
    });
 */

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//spin animation
    /*
    private Timer spin = new Timer(15, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            timePast++;
            smallPyroBlast.setLocation(pyroBlastX, pyroblastY);
            if (phase == 0){
                MusicPick.musicStart("demoshout", "");
                smallPyroBlast.setVisible(true);
                upMegaMath *=2;
                pyroblastY -= upMegaMath;
            }
            if (timePast % 3 == 0){phase++;}
            if (phase == 5) {
                phase = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
            }
            if (phase == 2) upMegaMath = 1;

            if (phase == 1 || phase == 2) { //höger
                rightMegaMath *=2;
                pyroBlastX += rightMegaMath;
            }
            if (phase == 2 || phase == 3) { //ner
                downMegaMath *=2;
                pyroblastY += downMegaMath;
            }
            if (phase == 3 || phase == 4) { //vänster
                leftMegaMath *=2;
                pyroBlastX -= leftMegaMath;
            }
            if (phase == 4 || phase == 1) { //flyger uppåt
                upMegaMath *=2;
                pyroblastY -= upMegaMath;
            }
            if(timePast == 50) {
                pyroblastY = 150;
                pyroBlastX = 45;
                smallPyroBlast.setVisible(false);
                timePast = 0;
                upMegaMath = 1;
                rightMegaMath = 1;
                downMegaMath = 1;
                leftMegaMath = 1;
                phase = 0;
                fireBall.stop();
            }
        }

    });

     */


















































































    /* safety copy











            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                wolfHp[0] = wolfHp[0] - warriorDamage;//Wolf take damage equals to warriors damage.
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);//Update wolf 1 hp label.
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                wolfHp[1] = wolfHp[1] - warriorDamage;
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                wolfHp[2] = wolfHp[2] - warriorDamage;
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                wolfHp[3] = wolfHp[3] - warriorDamage;
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }






    //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                }
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[1] = wolfHp[1] - rangerDamage;
                }
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[2] = wolfHp[2] - rangerDamage;
                }
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                if (stealthed){
                    wolfHp[0] = wolfHp[0] - rangerDamage;//Wolf take damage equals to rangers damage.
                    wolfHp[0] = wolfHp[0] - 5;//Wolf take damage equals to rangers extra damage.
                }
                else {
                    wolfHp[3] = wolfHp[3] - rangerDamage;
                }
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }

























            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                wolfHp[0] = wolfHp[0] - mageDamage;//Wolf take damage equals to mage's damage.
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]); //Update wolf 1 hp label.
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                wolfHp[1] = wolfHp[1] - mageDamage;
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                wolfHp[2] = wolfHp[2] - mageDamage;
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                wolfHp[3] = wolfHp[3] - mageDamage;
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }









//When healer press the "attack button".
    private void healerAttackWolf(){
        //Loops until it hits an live wolf.
        while (true) {

            //If target is 1 and wolf 1 is alive.
            if (target == 1 && wolfHp[0] > 0) {
                wolfHp[0] = wolfHp[0] - healerDamage;//Wolf take damage equals to healers damage.
                fff.wolf1Hp.setText("Wolf 1: " + wolfHp[0]);
                break;
            }
            //If target is 2 and wolf 2 is alive.
            if (target == 2 && wolfHp[1] > 0) {
                wolfHp[1] = wolfHp[1] - healerDamage;
                fff.wolf2Hp.setText("Wolf 2: " + wolfHp[1]);
                break;
            }
            //If target is 3 and wolf 3 is alive.
            if (target == 3 && wolfHp[2] > 0) {
                wolfHp[2] = wolfHp[2] - healerDamage;
                fff.wolf3Hp.setText("Wolf 3: " + wolfHp[2]);
                break;
            }
            //If target is 4 and wolf 4 is alive.
            if (target == 4 && wolfHp[3] > 0) {
                wolfHp[3] = wolfHp[3] - healerDamage;
                fff.wolf4Hp.setText("Wolf 4: " + wolfHp[3]);
                break;
            }
        }
    }











     */
