package davidtest.overworld.entities;

import davidtest.overworld.gfx.Colours;
import davidtest.overworld.gfx.Fonts;
import davidtest.overworld.gfx.Screen;
import davidtest.overworld.levels.Level;
import davidtest.overworld.levels.tiles.Tile;

public class NPC extends Mob {

    private int colour = Colours.get(-1, 121, 555, 332); //Assign colour for character which will be calculated within the Colours-class

    private String username; //username

    public NPC(Level level1, String name, int x, int y, int speed) {
        super(level1, name, x, y, speed);
    }

    @Override
    public boolean hasCollided(int xa, int ya) {
        //top left corner
        int xMin = 0;
        //top right corner
        int xMax = 5;
        //bottom left corner
        int yMin = 3;
        //bottom right corner
        int yMax = 5;
        /*Now 4 loops will be made between the coordinates of the box, indicating where on the body of the player there
         should be a reaction*/

        //set boolean-values as true if they collide with specific tile
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin)) {
                return true;
            }
        }

        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMax)) {
                return true;
            }
        }

        for (int y = yMin; y < yMax; y++) {
            if (isSolidTile(xa, ya, xMin, y)) {
                return true;
            }
        }

        for (int y = yMin; y < yMax; y++) {
            if (isDoorTile(xa, ya, xMax, y) || isSolidTile(xa, ya, xMax, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick() {

    }

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
        //assign the isSwimming value as natively false
        boolean isSwimming = false;
        //assign the isSwampSwimming value as natively false
        boolean isSwampSwimming = false;
        if (isSwimming || isSwampSwimming) {
            int waterColour; //call the wave-effect while swimming
            yOffset += 1;//lowers the player-position while in the water

            //assign whenever during the ticCount the water-sprites will be played
            //counts the ticks since the last update
            int tickCount = 0;
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
            Fonts.render(username, screen, xOffset - ((username.length() - 1) / 2 * 8), yOffset - 10, Colours.get(-1, -1, 1, 555), 1);
        }
    }
}
