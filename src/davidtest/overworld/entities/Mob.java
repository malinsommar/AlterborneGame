
package davidtest.overworld.entities;

import davidtest.overworld.levels.Level;
import davidtest.overworld.levels.tiles.Tile;

public abstract class Mob extends Entity {

    protected String name;
    protected int speed;
    protected int numSteps = 0;
    protected boolean isMoving;
    protected int movingDir = 1; //assign the direction the mob is facing
    protected int scale = 1; //size of the mob

    public Mob(Level level1, String name, int x, int y, int speed) {
        super(level1);
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    //method to indicate where the mob is moving.
    public void move(int xa, int ya) {
        //if either xa or ya:s value is above 0 movement is set to 0 zero
        if (xa != 0 && ya != 0) {
            move(0, 0);
            numSteps--; //when moving it will automatically count as two moves, and therefor we add a subtraction.
            return;
        }
        numSteps++;


        //Collision detection
        if (!hasCollided(xa, ya)) {
            if (ya < 0)
                movingDir = 0;
            if (ya > 0)
                movingDir = 1;
            if (xa < 0)
                movingDir = 2;
            if (xa > 0)
                movingDir = 3;
            x += xa * speed;
            y += ya * speed;
        }
    }

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
    protected boolean isDoorTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        //the solid path tiles
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile doorTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(doorTile) //If the tile player spawns on is an IsSolid tile won't be solid immediately
                && doorTile.isDoor(); //solidPathTile is Solid
        //if no difference is identified return false
    }
    protected boolean isForestPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        //the solid path tiles
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile forestPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(forestPathTile) //If the tile player spawns on is an IsSolid tile won't be solid immediately
                && forestPathTile.isForestPath(); //solidPathTile is Solid
        //if no difference is identified return false
    }
    protected boolean isMountainPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        //the solid path tiles
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile mountainPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(mountainPathTile) //If the tile player spawns on is an IsSolid tile won't be solid immediately
                && mountainPathTile.isMountainPath(); //solidPathTile is Solid
        //if no difference is identified return false
    }
    protected boolean isFieldPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        //the solid path tiles
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile FieldPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(FieldPathTile) //If the tile player spawns on is an IsSolid tile won't be solid immediately
                && FieldPathTile.isFieldPath(); //solidPathTile is Solid
        //if no difference is identified return false
    }

    protected boolean isSwampPathTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        //the solid path tiles
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile SwampPathTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(SwampPathTile) //If the tile player spawns on is an IsSolid tile won't be solid immediately
                && SwampPathTile.isSwampPath(); //solidPathTile is Solid
        //if no difference is identified return false
    }



    public String getName() {
        return name;
    }
}
