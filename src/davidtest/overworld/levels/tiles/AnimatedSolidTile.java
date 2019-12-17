package davidtest.overworld.levels.tiles;

public class AnimatedSolidTile extends AnimatedTile {
    public AnimatedSolidTile(int id, int[][] animationCoordinates, int tileColour, int levelColour, int animationSwitchDelay) {
        super(id, animationCoordinates, tileColour, levelColour, animationSwitchDelay);
        this.solid = true;
    }
}
