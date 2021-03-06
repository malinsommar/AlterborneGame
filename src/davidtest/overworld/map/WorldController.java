package davidtest.overworld.map;

import davidtest.overworld.gfx.Colours;
import davidtest.overworld.gfx.Fonts;
import davidtest.overworld.map.Functionality.MouseClickSimulated;
import game.MusicPick;
import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 * Canvas is a basic graphic component in java used for drawing within the frame
 * Runnable is used to automatically call the "run" method, which is necessary for the thread to work
 */
public class WorldController extends Canvas implements Runnable {
    private WorldView owf = new WorldView();
    public int[] Entrance = new int[1];

    /**
     * method start the program
     *
     * @param userName name that will be used for the player
     */
    public void startWorldController(String userName) {
        owf.startWorldView(userName);
        start();//start the program
        new MouseClickSimulated();
        //whileLoop to check that the entrance-array is less or equal to 0 before calling methods
        while (Entrance[0] <= 0) {
            EnterForest();
            EnterShop();
            EnterField();
            EnterSwamp();
            EnterCastle();
            EnterCave();
        }
    }

    /**
     * start-method that is called within StartWorldController and activates the thread
     */
    public synchronized void start() {
        running = true;//set the state of running to true
        new Thread(this).start(); //start thread. Threads are used to run multiple functionality at once
    }

    private boolean running = false; //if program ends up outside start, set programs running state


    /**
     * Initiate colours
     */
    private void init() {
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

    /**
     * run game
     */
    public void run() {
        MusicPick.musicStart("intofreeshort", "music"); //implement music
        long lastTime = System.nanoTime(); //the current value of nanoseconds
        double nsPerTick = 1000000000D / 60D; //how many nanoseconds are within one tick
        int ticks = 0; //a variable for how many updates
        int frames = 0; //a variable for the current fps

        long lastTimer = System.currentTimeMillis(); //a variable for when to reset the data
        double delta = 0; //a variable of how many nano-seconds have gone by so far.

        init(); //calls the screen-render before the game-loop starts

        //while Thread is active everything within these braces will run
        while (running) {
            long now = System.nanoTime(); //The current time that will be checked against lastTime
            delta += (now - lastTime) / nsPerTick; //subtract the current time with the last time and then divide the result with how many nanoseconds there are within a tick
            lastTime = now; //repeats the method by giving 'lastTime' the same value as 'now'
            boolean shouldRender = false;

            while (delta >= 1) {
                ticks++; //adds 1 to the ticks-value
                try {
                    tick(); //calls the tick function
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                delta -= 1; // subtract the value of delta by 1 and repeats the update-loop endlessly
                shouldRender = true;
            }
            //A sleep-function that pauses the current thread to keep it from overloading the system. in this case the frames are lowered based on the assigned sleep-value
            try {
                Thread.sleep(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (shouldRender) {
                frames++; //Adds to the frames by one
                render(); //calls render method
            }
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


    /**
     * Updates the logic of the game within all the active classes within the period of time assigned within the method
     *
     * @throws InterruptedException if the thread where to be interrupted
     */
    private synchronized void tick() throws InterruptedException {
        owf.level.tick(); //updates the screen with every tick. Without it, image will be completely frozen
        if (Entrance[0] > 0) { //if the Entrance array is assigned anything other than zero: call whatever is inside the braces
            owf.frame.dispose(); //dispose the OverWorld-frame
        }
        //FIXME thread delay/pause
        // - start Malin work
        // - thread resume
    }

    /**
     * Prints out what the logic in the tick-function has stated should be printed out
     */
    private void render() {
        BufferStrategy bs = owf.getBufferStrategy(); //an Object to organize the data in the canvas
        //if a bufferStrategy hasn't been created: make one
        if (bs == null) {
            owf.createBufferStrategy(3); //reducing tearing in the image. Higher value would require higher processing-power
            return;
        }

        int xOffset = owf.player.x - (owf.screen.width / 2); //Players position in accordance with the screens width
        int yOffset = owf.player.y - (owf.screen.height / 2);  //Players position in accordance with the screens height

        //render the map into the game
        owf.level.renderTiles(owf.screen, xOffset, yOffset);

        //render the available mobs into to game
        owf.level.renderEntities(owf.screen);

        //render signs into the game
        Fonts.render("shop", owf.screen, 216, 205, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("Forest", owf.screen, 320, 258, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("1-3", owf.screen, 332, 280, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("Cave", owf.screen, 15, 40, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("1-3", owf.screen, 20, 62, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("Field", owf.screen, 43, 450, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("5-8", owf.screen, 52, 472, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("Swamp", owf.screen, 452, 103, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("8-12", owf.screen, 456, 128, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("Castle", owf.screen, 147, 8, Colours.get(000, -1, -1, 555), 1);
        Fonts.render("???", owf.screen, 160, 50, Colours.get(000, -1, -1, 555), 1);


        //render the colours on the tiles within the screens width and height
        for (int y = 0; y < owf.screen.height; y++) {
            for (int x = 0; x < owf.screen.width; x++) {
                int colourCode = owf.screen.pixels[x + y * owf.screen.width]; //combined value of all the colours in an int
                if (colourCode < 255) //check so the colour is within the 255 parameter
                    owf.pixels[x + y * WorldView.WIDTH] = owf.colours[colourCode];
            }
        }

        Graphics g = bs.getDrawGraphics(); //a graphic-object. without it the screen would just stay white
        g.drawImage(owf.image, 0, 0, owf.getWidth(), owf.getHeight(), null); //draws the image on the screen
        g.dispose(); //free up space when the object is no longer needed
        bs.show();//show in JFrame
    }

    /**
     * List of Methods to call back to WorldModel when a a new frame should be used and the WorldView should be disposed
     */
    public synchronized void EnterShop() {
        if (owf.player.hasEnteredShop()) {
            Entrance[0] = 1;
        }
    }

    public synchronized void EnterForest() {
        if (owf.player.hasEnteredForest()) {
            Entrance[0] = 2;
        }
    }

    public synchronized void EnterCave() {
        if (owf.player.hasEnteredCave()) {
            Entrance[0] = 3;
        }
    }

    public void EnterField() {
        if (owf.player.hasEnteredField()) {
            Entrance[0] = 4;
        }
    }

    private void EnterSwamp() {
        if (owf.player.hasEnteredSwamp()) {
            Entrance[0] = 5;

        }
    }

    private void EnterCastle() {
        if (owf.player.hasEnteredCastle()) {
            Entrance[0] = 6;
        }
    }
}