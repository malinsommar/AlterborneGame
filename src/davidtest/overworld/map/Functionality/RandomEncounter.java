package davidtest.overworld.map.Functionality;

import java.util.TimerTask;

/**
 * Class to create a random chance
 */
public class RandomEncounter {
    public int secondsPassed;
    public int randomNr;

    /**
     * the max and min value that can be used
     */
    public RandomEncounter() {
        getRandom(10000, 1);
    }

    /**
     * get the method-value
     * @param max value
     * @param min value
     */
    private void getRandom(int max, int min) {
        randomNr = (int) (Math.random() * ((max - min) + 1));
    }
}
