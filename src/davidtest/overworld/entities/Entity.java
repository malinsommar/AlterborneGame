package davidtest.overworld.entities;

import davidtest.overworld.levels.Level;
import davidtest.overworld.gfx.Screen;

//create a Entity-class as a basis for all mobs in game
public abstract class Entity {
    public int x,y; //positioning
    protected Level level1; //level the entities are assigned to

    //initiate entities onto the level

    Entity(Level level1) {
        init(level1);
    }
    //indicate that the level1-value is the same the value within the parameters
    public final void init(Level level1) {
        this.level1 = level1;
    }

    //update the Entities with every tick
    public abstract void tick();

    //render the entities onto the screen
    public abstract void render(Screen screen);
}
