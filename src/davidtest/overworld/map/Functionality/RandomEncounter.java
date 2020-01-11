package davidtest.overworld.map.Functionality;

import java.util.TimerTask;

public class RandomEncounter {
    public int secondsPassed;
    public int randomNr;

    public RandomEncounter() {
        getRandom(10000, 1);
    }
    private void getRandom(int max, int min) {
        randomNr = (int) (Math.random() * ((max - min) + 1));
    }
    public void getTimer() {
        secondsPassed = 0;
        java.util.Timer delay = new java.util.Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                secondsPassed++;
                System.out.println("time " +secondsPassed);
                if (secondsPassed >= 3) {
                    getRandom(5, 1);
                    secondsPassed = 0;
                    System.out.println("number " + randomNr);
                }
                }
        }; delay.scheduleAtFixedRate(task,15000, 1000);
    }
}
