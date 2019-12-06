package davidtest.overworld.levels.tiles;

public class ForestPath extends PathTile {
    public ForestPath(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.path = true;
    }
}
