package Gametest.Simontest;

import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class simontest extends JFrame {
    
    private JButton shootarrowbutton, tacklebutton, takedamagebutton, magespellbutton, chargebutton, volleybutton;

    public int cloudx = 100;
    public int cloudy = 200;
    public int startpostitionwarriorx = cloudx;
    public int startpostitionwarriory = cloudx;
    int phase = 0;
    int timepast = 0;
    int flamey = -400;
    int arrowx = 300;

    JLabel firestorm = new JLabel(new ImageIcon("bigfire.gif"));
    JLabel flame = new JLabel(new ImageIcon("flame.gif"));
    JLabel arrow = new JLabel(new ImageIcon("arrow.png"));
    JLabel volley1 = new JLabel(new ImageIcon("arrow.png"));
    JLabel volley2 = new JLabel(new ImageIcon("arrow.png"));
    JLabel volley3 = new JLabel(new ImageIcon("arrow.png"));


    JLabel player1 = new JLabel(new ImageIcon("mage.gif"));

    public simontest() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon picShop = new ImageIcon("C:\\Users\\96simben\\Documents\\GitHub\\Game\\src\\Gametest\\Simontest\\shopk.jpg");
        setContentPane(new JLabel(picShop)); //assign background

        Dimension warriorSize = player1.getPreferredSize();
        player1.setSize(warriorSize.width, warriorSize.height);
        player1.setLocation(cloudx, cloudy);
        add(player1);

        shootarrowbutton = new JButton("arrow");
        shootarrowbutton.setSize(150, 40);
        shootarrowbutton.setLocation(800, 100);
        shootarrowbutton.setBackground(Color.white);
        add(shootarrowbutton);
        shootarrowbutton.addActionListener(e -> shoot.start());

        tacklebutton = new JButton("move");
        tacklebutton.setSize(150, 40);
        tacklebutton.setLocation(800, 150);
        tacklebutton.setBackground(Color.white);
        add(tacklebutton);
        tacklebutton.addActionListener(e -> {tackle.start();});
        /* onödiga timer saker?
        tackle.setRepeats(true);
        tackle.setCoalesce(true);
        tackle.setInitialDelay(10);
         */

        takedamagebutton = new JButton("takedamage");
        takedamagebutton.setSize(150, 40);
        takedamagebutton.setLocation(800, 200);
        takedamagebutton.setBackground(Color.white);
        add(takedamagebutton);
        takedamagebutton.addActionListener(e -> {takedamage.start();});

        magespellbutton = new JButton("magespell");
        magespellbutton.setSize(150, 40);
        magespellbutton.setLocation(800, 250);
        magespellbutton.setBackground(Color.white);
        add(magespellbutton);
        magespellbutton.addActionListener(e -> { magespell.start(); });

        chargebutton = new JButton("charge");
        chargebutton.setSize(150, 40);
        chargebutton.setLocation(800, 300);
        chargebutton.setBackground(Color.white);
        add(chargebutton);
        chargebutton.addActionListener(e -> { charge.start(); });

        volleybutton = new JButton("volley");
        volleybutton.setSize(150, 40);
        volleybutton.setLocation(800, 350);
        volleybutton.setBackground(Color.white);
        add(volleybutton);
        volleybutton.addActionListener(e -> volley.start());

        //entities
        Dimension fireSize = firestorm.getPreferredSize();
        firestorm.setSize(fireSize.width, fireSize.height);
        firestorm.setLocation(700, 200);
        add(firestorm);
        firestorm.setVisible(false);

        Dimension flameSize = flame.getPreferredSize();
        flame.setSize(flameSize.width, flameSize.height);
        flame.setLocation(800, flamey);
        add(flame);
        flame.setVisible(true);

        Dimension arrowSize = arrow.getPreferredSize();
        arrow.setSize(arrowSize.width, arrowSize.height);
        arrow.setLocation(-100, arrowx);
        add(arrow);

        volley1.setSize(arrowSize.width, arrowSize.height);
        volley1.setLocation(-100, arrowx);
        volley1.setVisible(false);
        add(volley1);
        volley2.setSize(arrowSize.width, arrowSize.height);
        volley2.setLocation(-100, arrowx);
        volley2.setVisible(false);
        add(volley2);
        volley3.setSize(arrowSize.width, arrowSize.height);
        volley3.setLocation(-100, arrowx);
        volley3.setVisible(false);
        add(volley3);


        MusicPick.musicStart("allstar", "music");
        setLayout(null); //Default layout
        setSize(1900, 500);

        setVisible(true);

    }

    Timer tackle = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                if (cloudx == 100) MusicPick.musicStart("warriorattack", "");
                cloudx += 30;
                player1.setLocation(cloudx, cloudy);
                if (cloudx > 200) {
                    phase = 1;
                }
            } else if (phase == 1) {
                cloudx -= 30;
                player1.setLocation(cloudx, cloudy);
                if (cloudx < 100) {
                    phase = 0;
                    tackle.stop();
                }
            }
        }
    });

    Timer takedamage = new Timer(10, new ActionListener() {
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
                takedamage.stop();
                phase = 0;

            }
        }
    });

    Timer magespell = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            if (phase == 0) {
                MusicPick.musicStart("magespell", "");
                phase = 1;
            }
            else if (phase == 1){
                cloudy -= 3;
                player1.setLocation(100, cloudy);
                if (cloudy < 50) {
                    phase = 2;
                }}
            else if (phase == 2) {
                if (cloudy < 50) {
                    cloudy = 50;
                    cloudx -= 3;
                }
                timepast++;
                if (timepast < 100) {
                    if (timepast % 2 == 1) {
                        cloudx += 6;
                        player1.setLocation(cloudx, cloudy);
                        flamey += 10;
                        flame.setLocation(700, flamey);
                    } else {
                        cloudx -= 6;
                        player1.setLocation(cloudx, cloudy);
                    }
                }
                if (timepast == 102) {
                    player1.setLocation(cloudx - 10, cloudy);
                    firestorm.setVisible(true);
                    flamey = -400;
                    flame.setLocation(700, flamey);
                }
                if (timepast > 130) {
                    timepast = 0;
                    phase = 3;
                }
            } else if (phase == 3) {
                cloudy += 3;
                player1.setLocation(100, cloudy);
                if (cloudy > 200) {
                    startpostitionwarriorx = cloudx;
                    startpostitionwarriory = cloudy;
                    phase = 4;
                }
            } else if (phase == 4) {
                timepast++;
                if (timepast > 30) {
                    timepast = 0;
                    firestorm.setVisible(false);
                    magespell.stop();
                    phase = 0;
                }
            }
        }
    });

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
            }
            else if (phase == 5) {
                player1.setVisible(true);
                unusedtakedamage.stop();
                phase = 0;
            }
        }
    });

    Timer shoot = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                arrow.setVisible(true);
                if (arrowx == 300) {
                    MusicPick.musicStart("ding", "");
                }
                arrowx += 10;
                arrow.setLocation(arrowx, cloudy + 50);
                if (arrowx > 800) {
                    phase = 1;
                }
            } else if (phase == 1) {
                arrow.setVisible(false);
                arrowx = 300;
                arrow.setLocation(arrowx, cloudy + 50);
                    phase = 0;
                    shoot.stop();
                }
            }
    });

    Timer charge = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (phase == 0) {
                MusicPick.musicStart("charge", "");
                phase = 1;
            }
            else if (phase == 1) {
                cloudx += 20;
                player1.setLocation(cloudx, cloudy);
                if (cloudx > 2000) {
                    phase = 2;
                }
            }
            else if (phase == 2) {
                cloudx = 100;
                player1.setLocation(cloudx,cloudy);
                phase = 0;
                charge.stop();
            }
        }
    });
    Timer volley = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            arrowx += 30;
            volley1.setLocation(arrowx, cloudy + 50);
            volley2.setLocation(arrowx - 200, cloudy + 50);
            volley3.setLocation(arrowx - 400, cloudy + 50);
            if (phase == 0) {
                volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 1;
            }
            else if (phase == 1 && arrowx > cloudx + 300) {
                volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 2;
            }
            else if (phase == 2 && arrowx > cloudx + 600) {
                volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 3;
                }
            else if (phase == 3 && arrowx > 1000) {
                volley1.setVisible(false);
                phase = 4;
            }
            else if (phase == 4 && arrowx > 1200) {
                volley2.setVisible(false);
                phase = 5;
            }
            else if (phase == 5 && arrowx > 1400) {
                volley3.setVisible(false);
                phase = 6;
            }
            if (phase == 6) {
                arrowx = 300;
                volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 7;
            }
            else if (phase == 7 && arrowx > cloudx + 300) {
                volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 8;
            }
            else if (phase == 8 && arrowx > cloudx + 600) {
                volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 9;
            }
            else if (phase == 9 && arrowx > 1000) {
                volley1.setVisible(false);
                phase = 10;
            }
            else if (phase == 10 && arrowx > 1200) {
                volley2.setVisible(false);
                phase = 11;
            }
            else if (phase == 11 && arrowx > 1400) {
                volley3.setVisible(false);
                phase = 12;
                arrowx = 300;
            }
            if (phase == 12) {
                volley1.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 13;
            }
            else if (phase == 13 && arrowx > cloudx + 300) {
                volley2.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 14;
            }
            else if (phase == 14 && arrowx > cloudx + 600) {
                volley3.setVisible(true);
                MusicPick.musicStart("ding", "");
                phase = 15;
            }
            else if (phase == 15 && arrowx > 1000) {
                volley1.setVisible(false);
                phase = 16;
            }
            else if (phase == 16 && arrowx > 1200) {
                volley2.setVisible(false);
                phase = 17;
            }
            else if (phase == 17 && arrowx > 1400) {
                volley3.setVisible(false);
                phase = 18;
            }
            else if (phase == 18){
                arrowx = 300;
                phase = 0;
                volley.stop();
            }
        }
    });
}