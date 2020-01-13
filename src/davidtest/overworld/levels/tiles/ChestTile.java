package davidtest.overworld.levels.tiles;

public class ChestTile extends BasicTile {


    public ChestTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.solid = true;
        this.chest = true;
    }
}
