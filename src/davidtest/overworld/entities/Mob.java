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
        //Makes it so you can´t move two directions at once
        if (xa != 0 && ya != 0) {
            move(0, 0);
            numSteps--; //when moving it will automatically count as two moves, and therefor we add a subtraction.
            return;
        }
        numSteps++;


        //Collision detection
        if (!hasCollided(xa, ya)) {
            //add directional indicators
            if (ya < 0)
                movingDir = 0;
            if (ya > 0)
                movingDir = 1;
            if (xa < 0)
                movingDir = 2;
            if (xa > 0)
                movingDir = 3;
            //create movement
            x += xa * speed;
            y += ya * speed;
        }

        if (!hasEntered(xa, ya)) {
            //add directional indicators
            if (ya < 0)
                movingDir = 0;
            if (ya > 0)
                movingDir = 1;
            if (xa < 0)
                movingDir = 2;
            if (xa > 0)
                movingDir = 3;
        }
    }

    public abstract boolean hasCollided(int xa, int ya);

    //compare tiles whether they are Basic or BasicSolid-tile (seen in Tile-class).
    protected boolean isSolidTile(int xa, int ya, int x, int y) {
        //the tile you stand on before you start moving
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        //the tile you stand on once you've started moving
        Tile newTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(newTile) //If the tile player spawns on is an IsSolid tile it won´t be solid immediately
                && newTile.isSolid();  //newTile is Solid
        //if no difference is identified return false
    }
    public abstract boolean hasEntered(int xa, int ya);

    protected boolean isPathTile(int xa, int ya, int x, int y) {
        Tile lastTile = level1.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        //the tile you stand on once you've started moving
        Tile newTile = level1.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        return !lastTile.equals(newTile) //If the tile player spawns on is an IsSolid tile it won´t be solid immediately
                && newTile.isPath();  //newTile is Solid
    }


    public String getName() {
        return name;
    }
}
