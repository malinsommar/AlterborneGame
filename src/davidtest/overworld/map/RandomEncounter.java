package davidtest.overworld.map;

import java.util.TimerTask;

public class RandomEncounter {
    int secondsPassed;
    int randomNr;

    public RandomEncounter() {
getRandom(200, 1);
    }
    public int getRandom(int max, int min) {
        return ((randomNr = (int) (Math.random() * ((max - min) + 1))) + min);
    }
    public void getTimer() {
        secondsPassed = 10;
        java.util.Timer delay = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                --secondsPassed;
                }
        }; delay.schedule(task,100000000, 1000000000);
    }

}
