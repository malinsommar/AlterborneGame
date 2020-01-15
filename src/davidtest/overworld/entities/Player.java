package davidtest.overworld.entities;


import davidtest.overworld.gfx.Fonts;
import davidtest.overworld.levels.Level;
import davidtest.overworld.gfx.Colours;
import davidtest.overworld.gfx.Screen;
import davidtest.overworld.map.Functionality.InputHandler;
import davidtest.overworld.map.Functionality.RandomEncounter;
import game.UserNameController;

import java.awt.*;

/**
 * Class to create the player used in the game. Extends the values of mob as basis
 * @author David Furby
 * @version 1
 */
public class Player extends Mob {


    private InputHandler input; //assign the input-system onto the player
    private int colour = Colours.get(-1, 111, 111, 332); //Assign colour for character which will be calculated within the Colours-class
    private boolean isSwimming = false; //assign the isSwimming value as natively false
    private boolean isSwampSwimming = false;//assign the isSwampSwimming value as natively false
    private boolean isOnForestPath = false; // if player is on tile to enter forest-combat
    private boolean isOnFieldPath = false; // if player is on tile to enter Field-combat
    private boolean isOnSwampPath = false; // if player is on tile to enter Swamp-combat
    private boolean isOnCastlePath = false; // if player is on tile to enter Castle-combat
    private boolean EnterShop = false;  // if player is on tile to enter Shop (is not used)
    private boolean isOnCavePath = false; //if player is on tile to enter Cave-combat
    private int tickCount = 0; //counts the ticks since the last update
     private String username; //username

    /**
     * Constructor for player with the necessary variables to be inserted into the view
     * @param level1  Assign which level the player is used on
     * @param x Assign position of the player on x-axis
     * @param y Assign position of the player on y-axis
     * @param input Assign input-variable for movement
     * @param username Assign username
     */
    public Player(Level level1, int x, int y, InputHandler input, String username) {
        super(level1, "Player", x, y, 1); //
        this.input = input; //assign the input-class onto the player
        this.username = username; //This username is the same as the username within the parameters
    }

    /**
     * updates isPressed method based on player-input every time the tick counts up by one
     */
    @Override
    public void tick() {

        /*up, down, left and right are identified with keyboard-inputs the xa or ya based on axel. When an assigned
        button is pressed, the xa or ya subs or adds by one, updating the movement*/
        int xa = 0; //set the base-value of the x-axis to 0
        int ya = 0;
        if (input != null) {
            if (input.up.isPressed()) {
                ya--;
            }

            if (input.down.isPressed()) {
                ya++;
            }
            if (input.left.isPressed()) {
                xa--;
            }

            if (input.right.isPressed()) {
                xa++;
            }
            //sets the default value of isMoving to false if character isn't moving
            if (xa != 0 || ya != 0) {
                move(xa, ya);
                isMoving = true;
            } else {
                isMoving = false;
            }
            //identify if player is swimming
            if (level1.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
                isSwimming = true;
            }
            //identify if player is not swimming
             else {
                isSwimming = false;
            }

            if (level1.getTile(this.x >> 3, this.y >> 3).getId() == 8) {
                isSwampSwimming = true;
            }

            //identify if player is not swimming
            else {
                isSwampSwimming = false;
            }
                tickCount++; //adds to tick whenever a move is made
        }
    }

    /**
     * Render character on screen
     * @param screen Parameter to add the entities onto it
     */
    @Override
    public void render(Screen screen) {
        int xTile = 0;
        int yTile = 28;
        int walkingSpeed = 4;//character walk speed
        //the value of the top part of the 8x8 sprite

        int flipTop = (numSteps >> walkingSpeed) & 1;
        //the value of the bottom part of the 8x8 sprite
        int flipBottom = (numSteps >> walkingSpeed) & 1;

        //change which group of pixels are being presented for the character
        if (movingDir == 1) {
            //moves to the second 8x8 sprite
            xTile += 2;
            //moves to the third 8x8 sprite
        } else if (movingDir > 1) {
            xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
            flipTop = (movingDir - 1) % 2;
        }
        //assign scale to character
        int scale = 1;
        int modifier = 8 * scale;
        int xOffset = x - modifier / 2;
        int yOffset = y - modifier / 2 - 4;

        //set the render-effect of swimming
        if (isSwimming || isSwampSwimming) {
            int waterColour; //call the wave-effect while swimming
            yOffset += 1;//lowers the player-position while in the water

            //assign whenever during the ticCount the water-sprites will be played
            if (tickCount % 60 < 15) {
                waterColour = Colours.get(-1, -1, 225, -1);//assign colours to the first player-wave frame
            } else if (tickCount % 60 < 30) {
                yOffset -= 1;//lowers the position of the character to create a bop-effect when in water
                waterColour = Colours.get(-1, 225, 115, -1);//the second frame
            } else if (tickCount % 60 < 45) {
                yOffset -= 2;//more bop
                waterColour = Colours.get(-1, 115, -1, 225); //the third frame
            } else {
                waterColour = Colours.get(-1, 225, 115, -1); //the second again
            }

            screen.render(xOffset, yOffset + 3, 27 * 32, waterColour, 0x00, 1);
            screen.render(xOffset + 8, yOffset + 3, 27 * 32, waterColour, 0x01, 1);

        }
        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
        screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop, scale);

        //call this whenever player=!isSwimming is assigned false to see the bottom part of the character
        if (!isSwimming && !isSwampSwimming) {
            screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour, flipBottom, scale);
            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale);
        }
        if (username != null) {
            Fonts.render(username, screen, xOffset -((username.length()- 1) / 2 * 8), yOffset - 10, Colours.get(-1,-1,1, 555), 1);
        }
    }


    /**
     * Create a collisionBox on the player that will react if they collide with a solid tile
     * @return Set boolean-values as true if they collide with specific tile
     */
    @Override
    public boolean hasCollided(int xa, int ya) {
        //Top left corner of the collision-box
        int xMin = 0;
        //Top right corner of the collision-box
        int xMax = 3;
        //Bottom left corner of the collision-box
        int yMin = 3;
        //Bottom right corner of the collision-box
        int yMax = 3;
        /*Now 4 loops will be made between the coordinates of the box, indicating where on the body of the player there
         should be a reaction*/

        //if the tiles interact with this box, the value true will be returned
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin)) {
                return true;
            }
            if (isDoorTile(xa,ya,x,yMin)) {
                EnterShop = true;
            }
            if (isForestPathTile(xa,ya,x,yMin)) {
                isOnForestPath = true;
            }
            if (isFieldPathTile(xa,ya,x,yMin)) {
                isOnFieldPath = true;
            }
            if (isSwampPathTile(xa,ya,x,yMin)) {
                isOnSwampPath = true;
            }
            if (isCastlePathTile(xa,ya,x,yMin)) {
                isOnCastlePath = true;
            }
            if (isCavePathTile(xa,ya,x,yMin)) {
                isOnCavePath = true;
            }
        }
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMax)) {
                return true;
            }
        }

        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa,ya,xMin,y)) {
                return true;
            }
        }

        for (int y = yMin; y < yMax; y++) {
            if (isDoorTile(xa, ya, xMax, y) || isSolidTile(xa,ya,xMax,y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to return value if tile is identified as certain tiles, and their respective controllers should start
     * @return the values if the identified tile fits the category
     */
    public boolean hasEnteredShop() {
        return EnterShop;
    }
    public boolean hasEnteredForest() {
                return isOnForestPath;
    }
    public boolean hasEnteredField() {
        return isOnFieldPath;
    }
    public boolean hasEnteredSwamp() {
        return isOnSwampPath;
    }
    public boolean hasEnteredCastle() {
        return  isOnCastlePath;
    }
    public boolean hasEnteredCave() {
        return  isOnCavePath;
    }
}
