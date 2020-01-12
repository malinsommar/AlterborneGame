package davidtest.overworld.levels.tiles;

public class ChestTile extends BasicTile {
    private int[][] switchCoordinates;

    public ChestTile(int id, int[][] switchCoordinates, int tileColour, int levelColour) {
        super(id, switchCoordinates[0][0], switchCoordinates[0][1], tileColour, levelColour);
        this.solid = true;
        this.switchCoordinates = switchCoordinates;
    }
}
