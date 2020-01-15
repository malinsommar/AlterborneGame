package davidtest.overworld.levels.tiles;

//Tile used to enter ShopController
public class DoorTile extends BasicTile {
    public DoorTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.door = true;
    }
}
