package davidtest.overworld.entities;

import davidtest.overworld.levels.Level;
import davidtest.overworld.gfx.Screen;

/**
 * Create a Entity-class as a basis for all mobs in game
 * @author David Furby
 * @version 1
 */
public abstract class Entity {
    public int x,y; //Positioning
    protected Level level1; //Level the entities are assigned to

    /**
     *  Initiate entities onto the level within the constructor
     * @param level1 parameter to indicate which level is used
     *
     */
    Entity(Level level1) {
        init(level1);
    }

    /**
     * Indicate that the variable level is the same as the parameter variable
     * @param level1 Assign the parameter-value
     */
    public final void init(Level level1) {
        this.level1 = level1;
    }

    /**
     * Update the Entities with every tick
     *
     */
    public abstract void tick();

    /**
     *  Render the entities onto the screen
     * @param screen Parameter to add the entities onto it
     */
    public abstract void render(Screen screen);
}
