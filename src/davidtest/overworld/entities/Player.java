package davidtest.overworld.entities;


import davidtest.overworld.gfx.Fonts;
import davidtest.overworld.hub.OverWorld;
import davidtest.overworld.levels.Level;
import davidtest.overworld.gfx.Colours;
import davidtest.overworld.gfx.Screen;
import davidtest.overworld.hub.InputHandler;
import davidtest.overworld.levels.tiles.PathTile;
import fight.ForestFight;

import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;

public class Player extends Mob {

    private InputHandler input;
    private int colour = Colours.get(-1, 111, 111, 543); //Assign colour for character which will be calculated within the Colours-class
    private int scale = 1; //assign size to character
    protected boolean isSwimming = false; //assign the isSwimming value as natively false
    protected boolean isOnForestPath = false;
    private int tickCount = 0; //counts the ticks since the last update
    private String username;

    public Player(Level level1, int x, int y, InputHandler input, String username) {
        super(level1, "Player", x, y, 1);
        this.input = input;
        this.username = username;
    }


    @Override
    //updates isPressed method based on player-input every time the tick counts up by one
    public void tick() {

        /*up, down, left and right are identified with keyboard-inputs the xa or ya based on axel. When an assigned
        button is pressed, the xa or ya subs or adds by one, updating the movement*/
        int xa = 0;
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
            else if (level1.getTile(this.x + x >> 3, this.y >> 3).getId() != 3) {
                isSwimming = false;
            }
            if (level1.getTile( this.x + x >> 4,this.y >> 3).getId() ==5) {
                isOnForestPath = true;
            }
            else if (level1.getTile(this.x + x >> 3, this.y >> 3).getId() !=5) {

            }
                tickCount++; //adds to tick whenever a move is made
        }
    }

    @Override
    //render character on screen
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
            //moves to the third(?) 8x8 sprite
        } else if (movingDir > 1) {
            xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
            flipTop = (movingDir - 1) % 2;
        }
        int modifier = 8 * scale;
        int xOffset = x - modifier / 2;
        int yOffset = y - modifier / 2 - 4;
        if (isSwimming) {
            int waterColour = 0; //call the wave-effect while swimming
            yOffset += 2;//lowers the position while in the water

            //assign whenever during the ticCount the sprites will be played
            if (tickCount % 60 < 15) {
                waterColour = Colours.get(-1, -1, 225, -1);//
            } else if (tickCount % 60 < 30) {
                yOffset -= 1;//lowers the position of the character to create a bop-effect
                waterColour = Colours.get(-1, 225, 115, -1);
            } else if (tickCount % 60 < 45) {
                yOffset -= 2;//more bop
                waterColour = Colours.get(-1, 115, -1, 225);
            } else {
                waterColour = Colours.get(-1, 225, 115, -1);
            }
            screen.render(xOffset, yOffset + 3, 27 * 32, waterColour, 0x00, 1);
            screen.render(xOffset + 8, yOffset + 3, 27 * 32, waterColour, 0x01, 1);

        }
        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
        screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop, scale);

        //call this whenever player=isSwimming is assigned false
        if (!isSwimming) {
            screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour, flipBottom, scale);
            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale);

        }
        //add text above player
        if (username != null) {
            Fonts.render(username,screen, xOffset - ((username.length() - 1) / 2 * 8),yOffset - 10, Colours.get(-1,-1,-1,555), 1);
        }
    }

    public boolean hasEntered() {
        return isOnForestPath;
    }
    @Override

    //create a collisionBox for where a function will activate if player interacts with a thing within that box
    public boolean hasCollided(int xa, int ya) {
        //top left corner
        int xMin = 0;
        //top right corner
        int xMax = 5;
        //bottom left corner
        int yMin = 3;
        //bottom right corner
        int yMax = 5;
        //create a loop between the top left and top right corner
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin)) {
                return true;
            }
        }
        //creates a loop between bottom left and top right corner
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMax)) {
                return true;
            }
        }
        //create a loop between top left and bottom left corner
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMin, y)) {
                return true;
            }
        }
        //create a loop between top right and bottom right corner
        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMax, y)) {
                return true;
            }
        }
        return false;
    }
}
