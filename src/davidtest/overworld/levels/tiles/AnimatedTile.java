package davidtest.overworld.levels.tiles;

public class AnimatedTile extends BasicTile {

    private int[][] animationTileCoordinates;
    private int currentAnimationIndex;
    private long lastIterationTime;
    private int animationSwitchDelay; //control how fast the the program will switch between the animations

    public AnimatedTile(int id, int [][] animationCoordinates, int tileColour, int levelColour, int animationSwitchDelay) {
        super(id, animationCoordinates[0][0], animationCoordinates[0][1], tileColour, levelColour);
        this.animationTileCoordinates = animationCoordinates;
        this.currentAnimationIndex = 0;
        this.lastIterationTime = System.currentTimeMillis();
        this.animationSwitchDelay = animationSwitchDelay;

    }
    //moves the array from the first frame to the second and so on
    public void tick() {
        if ((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)) {
    lastIterationTime = System.currentTimeMillis();

    //puts a block on the loop so it does not go past the limit
    currentAnimationIndex = (currentAnimationIndex +1) % animationTileCoordinates.length;
    this.tileId = (animationTileCoordinates[currentAnimationIndex][0] + (animationTileCoordinates[currentAnimationIndex][1] * 32));
        }
    }
    }