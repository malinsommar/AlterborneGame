
package davidtest.overworld.entities;

import davidtest.overworld.levels.Level;
import davidtest.overworld.levels.tiles.Tile;

/**
 * Extension to the Entity-class. used to define the basic values for all following Entities
 * @author David Furby
 * @version 1
 */
public abstract class Mob extends Entity {
    private String name; //name
    private int speed; //movement-speed
    int numSteps = 0; //counted numbers of steps taken by mob
    boolean isMoving; //boolean if mob is moving
    int movingDir = 1; //assign the direction the mob is facing

    /**
     * Create parameter for mob-extensions
     * Constructor with the values used for following entities
     * @param level1 What level they are active on
     * @param name Name of entity
     * @param x Position of entity on the x-axis
     * @param y Position of the entity on the y-axis
     * @param speed Entities movement-speed
     */
    public Mob(Level level1, String name, int x, int y, int speed) {
        super(level1); //map
        this.name = name; //Mobs name
        this.x = x; //X-position
        this.y = y; //Y-position
        this.speed = speed; //Movement-speed
    }

    /**
     * *Mthod to indicate how the mob is moving.
     * @param xa Movement left and right
     * @param ya Movement up and down
     */
    public void move(int xa, int ya) {
        //if either xa or ya:s value is above 0 movement is set to 0 zero
        if (xa != 0 && ya != 0)
        {
            move(0, 0);
            numSteps--; //when moving it will automatically count as two moves, and therefor we add a subtraction.
            return;
        }
        numSteps++;

        //Control the mobs movement
        if (!hasCollided(xa, ya)) //If player has not Collided
        {
            if (ya < 0)
                movingDir = 0; //Set the mobs point of view moving up
            if (ya > 0)
                movingDir = 1;  //Set the mobs point of view if moving down
            if (xa < 0)
                movingDir = 2;  //Set the mobs point of view if moving left
            if (xa > 0)
                movingDir = 3;  //Set the mobs point of view if moving right
            x += xa * speed; //Handle movement left and right
            y += ya * speed; //Handle movement up and down
        }
    }

    /**
     * create a hasCollided method used within the the player-class
     * @param xa Movement left and right
     * @param ya Movement up and down
     * @return if hasCollided is identified as true
     */
    public abstract boolean hasCollided(int xa, int ya);


    /**
     * compare tiles whether they are Basic or BasicSolid-tile (seen in Tile-class).
     * @param xa Movement left and right
     * @param ya Movement up and down
     * @param x Tile position on the X-axis
     * @param y Tile position on the Y-axis
     * @return True if the previous tile is the same as current tile (so Entity won't get stuck when loading in) and if the tile is identified as solid. If level is null, then return false
     */
    protected boolean isSolidTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        //the solid tiles
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile solidTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(solidTile) //If the tile player spawns on is an IsSolid tile won't be solid immediately
                && solidTile.isSolid(); //solidTile is Solid
        //if no difference is identified return false
    }

    /**
     * Compare if tiles are a form of door-Tile. If player interacts with these tiles the View is disposed and the shop is activated.
     * @param xa  Movement left and right
     * @param ya Movement up and down
     * @param x Tile position on the X-axis
     * @param y Tile position on the Y-axis
     * @return True if the previous tile is the same as current tile (so Entity won't get stuck when loading in) and if the tile is identified as DoorPath. If level is null, then return false
     */
    protected boolean isDoorTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile doorTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(doorTile)
                && doorTile.isDoor();

    }

    /**
     * Compare if tiles are a form of forest-Tile. If player interacts with these tiles the View is disposed and the forestFight is activated.
     * @param xa Movement left and right
     * @param ya Movement up and down
     * @param x Tile position on the X-axis
     * @param y Tile position on the Y-axis
     * @return True if the previous tile is the same as current tile (so Entity won't get stuck when loading in), and if the tile is identified as ForestPath. If level is null, then return false
     *
     */
    protected boolean isForestPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile forestPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(forestPathTile)
                && forestPathTile.isForestPath();

    }

    /**
     * @param xa Movement left and right
     * @param ya Movement up and down
     * @param x Tile position on the X-axis
     * @param y Tile position on the Y-axis
     * @return True if the previous tile is the same as current tile (so Entity won't get stuck when loading in), and if the tile is identified as FieldPath. If level is null, then return false
     */
    protected boolean isFieldPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile FieldPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(FieldPathTile)
                && FieldPathTile.isFieldPath();
    }

    /**
     * @param xa Movement left and right
     * @param ya Movement up and down
     * @param x Tile position on the X-axis
     * @param y Tile position on the Y-axis
     * @return True if the previous tile is the same as current tile (so Entity won't get stuck when loading in), and if the tile is identified as SwampPath. If level is null, then return false
     */
    protected boolean isSwampPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile SwampPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(SwampPathTile)
                && SwampPathTile.isSwampPath();
    }

    /**
     * @param xa Movement left and right
     * @param ya Movement up and down
     * @param x Tile position on the X-axis
     * @param y Tile position on the Y-axis
     * @return True if the previous tile is the same as current tile (so Entity won't get stuck when loading in), and if the tile is identified as CastlePath. If level is null, then return false
     */
    protected boolean isCastlePathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile CastlePathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(CastlePathTile)
                && CastlePathTile.isCastlePath();
    }

    /**
     * @param xa Movement left and right
     * @param ya Movement up and down
     * @param x Tile position on the X-axis
     * @param y Tile position on the Y-axis
     * @return True if the previous tile is the same as current tile (so Entity won't get stuck when loading in), and if the tile is identified as CavePath. If level is null, then return false
     */
    protected boolean isCavePathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile CavePathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(CavePathTile)
                && CavePathTile.isCavePath();
    }
}
