
package davidtest.overworld.entities;

import davidtest.overworld.levels.Level;
import davidtest.overworld.levels.tiles.Tile;

public abstract class Mob extends Entity {

    protected String name;
    protected int speed;
    protected int numSteps = 0;
    protected boolean isMoving;
    protected int movingDir = 1; //assign the direction the mob is facing

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

    //Compare if tiles are a form of Path-Tile. Otherwise same as with the solid-path method
    protected boolean isDoorTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        //the solid path tiles
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

    protected boolean isChestTile(int xa, int ya, int x, int y) {
        if (level1 == null) {
            return false;
        }
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile ChestTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(ChestTile)
                && ChestTile.isChest();
    }



    public String getName() {
        return name;
    }
}
