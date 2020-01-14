
package davidtest.overworld.entities;

import davidtest.overworld.levels.Level;
import davidtest.overworld.levels.tiles.Tile;

public abstract class Mob extends Entity {

    private String name; //name
    private int speed; //movement-speed
    int numSteps = 0; //counted numbers of steps taken by mob
    boolean isMoving; //boolean if mob is moving
    int movingDir = 1; //assign the direction the mob is facing

    //create parameter for mob-extensions
    public Mob(Level level1, String name, int x, int y, int speed) {
        super(level1); //map
        this.name = name; //Mobs name
        this.x = x; //x-position
        this.y = y; //y-position
        this.speed = speed; //movement-speed
    }

    //method to indicate where the mob is moving.
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
        if (!hasCollided(xa, ya)) //if player has not Collided
        {
            if (ya < 0)
                movingDir = 0; //set the mobs point of view moving up
            if (ya > 0)
                movingDir = 1;  //set the mobs point of view if moving down
            if (xa < 0)
                movingDir = 2;  //set the mobs point of view if moving left
            if (xa > 0)
                movingDir = 3;  //set the mobs point of view if moving right
            x += xa * speed; //handle movement left and right
            y += ya * speed; //handle movement up and down
        }
    }

    //create a hasCollided method used within the the player-class
    public abstract boolean hasCollided(int xa, int ya);


    //compare tiles whether they are Basic or BasicSolid-tile (seen in Tile-class).
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

    //Compare if tiles are a form of Path-Tile. If player interacts with these tiles the View is disposed and  Otherwise same as with the solid-path method
    protected boolean isDoorTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile doorTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(doorTile)
                && doorTile.isDoor();

    }

    protected boolean isForestPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile forestPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(forestPathTile)
                && forestPathTile.isForestPath();

    }
    protected boolean isMountainPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile mountainPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(mountainPathTile)
                && mountainPathTile.isMountainPath();
    }
    protected boolean isFieldPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile FieldPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(FieldPathTile)
                && FieldPathTile.isFieldPath();
    }

    protected boolean isSwampPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile SwampPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(SwampPathTile)
                && SwampPathTile.isSwampPath();
    }

    protected boolean isCastlePathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile CastlePathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(CastlePathTile)
                && CastlePathTile.isCastlePath();
    }


    //get name
    public String getName() {
        return name;
    }
}
