package davidtest.overworld.levels.tiles;

public class ForestPathTile extends BasicTile {

    public ForestPathTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.forestPath = true;
    }
}
