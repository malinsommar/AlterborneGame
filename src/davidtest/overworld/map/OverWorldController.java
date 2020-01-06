package davidtest.overworld.map;

import davidtest.overworld.entities.Player;
import davidtest.overworld.gfx.Screen;
import davidtest.overworld.gfx.SpriteSheet;
import davidtest.overworld.levels.Level;
import davidtest.overworld.map.Functionality.MouseClickSimulated;
import game.MusicPick;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class OverWorldController extends Canvas implements Runnable {
    public OverWorldFrame owf = new OverWorldFrame();
    public int[] Entrance = new int[1];

    //int-variables to handle Model-execution
    private int ForestEntrance = 1;
    private int ShopEntrance = 1;
    private int ForestBossEntrance = 1;
    private int MountainEntrance = 1;

     OverWorldController() throws InterruptedException {
        start();//start the program
        new MouseClickSimulated();
        while (Entrance[0] <= 0) {
            if (ForestEntrance == 1) {
                EnterForest();
            }
            if (ShopEntrance == 1) {
                EnterShop();
            }
        }
    }

    public synchronized void start() {
        running = true;//set the state of running to true
        new Thread(this).start(); //start thread
    }

    private boolean running = false; //if program ends up outside start, set programs running state
    private int tickCount = 0;


    private void init() {
        System.out.println("init start");
        int index = 0;
        //red
        for (int r = 0; r < 6; r++) {
            //green
            for (int g = 0; g < 6; g++) {
                //blue
                for (int b = 0; b < 6; b++) {
                    //transparent colors
                    int rr = (r * 255 / 5);
                    int gg = (g * 255 / 5);
                    int bb = (b * 255 / 5);
                    //handle the available colours
                    owf.colours[index++] = rr << 16 | gg << 8 | bb;
                }
            }
        }
    }

    public void run() {
        MusicPick.musicStart("intofreeshort", "music"); //implement music
        long lastTime = System.nanoTime(); //the current value of nanoseconds
        double nsPerTick = 1000000000D / 60D; //how many nanoseconds are within one tick
        int ticks = 0; //a variable for how many updates
        int frames = 0; //a variable for the current fps

        long lastTimer = System.currentTimeMillis(); //a variable for when to reset the data
        double delta = 0; //a variable of how many nano-seconds have gone by so far. Once it has hit 1 second, 1 will be subtracted

        init(); //calls the screen-render before the game-loop starts

        while (running) {
            long now = System.nanoTime(); //The current time that will be checked against lastTime
            delta += (now - lastTime) / nsPerTick; //subtract the current time with the last time and then divide the result with how many nanoseconds there are within a tick
            lastTime = now; //repeats the method by giving 'lastTime' the same value as 'now'
            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++; //adds 1 to the ticks-value
                try {
                    tick(); //calls the tick function
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                delta -= 1; // subtract the value of delta by 1 and repeats the update-loop endlessly
            }

            try {
                //A 'sleep' method that pauses the current thread to keep it from overloading the system. in this case the frames are lowered based on the assigned sleep-value
                Thread.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frames++; //Adds to the frames by one
            render(); //calls render method

            if (System.currentTimeMillis() - lastTimer > 1000) //If current time in milliseconds minus the time for the last update is greater than a thousand (one second): update.
            {
                lastTimer += 1000; //gives lastTimer the value of one second
                System.out.println(ticks + " ticks, " + frames + " frames "); //show the current ticks and frames in the console
                frames = 0; //reset value of frames
                ticks = 0; //reset value of updates
                //the variables will now be reset once every second
            }
        }
    }

    //Updates the logic of the game within all the active classes
    private synchronized void tick() throws InterruptedException {
        tickCount++; //adds to the tick-count by one. continuing the loop of updating every class
        owf.level1.tick();
        if (Entrance[0] == 1) {
            this.wait();
        }
        if (Entrance[0] == 2) {
            owf.frame.dispose();
        }
        //FIXME thread delay/pause
        // - start Malin work
        // - thread resume
    }

    private void render() //prints out what the logic in the tick-function has stated should be printed out
    {
        BufferStrategy bs = owf.getBufferStrategy(); //an Object to organize the data in the canvas
        if (bs == null) {
            owf.createBufferStrategy(3); //reducing tearing in the image. Higher value would require higher processing-power
            return;
        }

        int xOffset = owf.player.x - (owf.screen.width / 2);
        int yOffset = owf.player.y - (owf.screen.height / 2);

        //render the map into the game
        owf.level1.renderTiles(owf.screen, xOffset, yOffset);

        //render the available mobs into to game
        owf.level1.renderEntities(owf.screen);

        for (int y = 0; y < owf.screen.height; y++) {
            for (int x = 0; x < owf.screen.width; x++) {
                int colourCode = owf.screen.pixels[x + y * owf.screen.width];
                if (colourCode < 255) owf.pixels[x + y * OverWorldFrame.WIDTH] = owf.colours[colourCode];
            }
        }

        Graphics g = bs.getDrawGraphics(); //a graphic-object
        g.drawImage(owf.image, 0, 0, owf.getWidth(), owf.getHeight(), null); //draws the image on the screen
        g.dispose(); //free up space
        bs.show();//show in JFrame
    }

    public synchronized void EnterForest() throws InterruptedException {
        if (owf.player.hasEnteredForest()) {
                ForestEntrance++;
                Entrance[0] = 1;
            }
        }
        public synchronized void EnterShop() throws InterruptedException {
        if (owf.player.hasEnteredShop()) {
            ShopEntrance++;
            Entrance[0] = 2;
        }
        }
    }