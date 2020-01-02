package game;

import davidtest.overworld.map.WorldModel;
import fight.FightModel;

public class MasterModel {

    private HubController hubController = new HubController();
    private FightModel fightModel = new FightModel();


    //Get user input from ConHub to start game of exit game.
    public void startGame() throws InterruptedException {

        System.out.println("start test");
        hubController.test();

        if (hubController.choice[0] == 1) {
            new WorldModel();
        }
        if (hubController.choice[0] == 3) {
            System.exit(0);
        }
    }
}