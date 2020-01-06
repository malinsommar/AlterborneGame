package game;

import davidtest.overworld.map.WorldModel;

public class MasterModel {

    private HubController hubController = new HubController();

    //Get user input from ConHub to start game of exit game.
    public void startGame() throws InterruptedException {

        System.out.println("start test");
        hubController.test();

        if (hubController.choice[0] == 1) {
            startWorldModel();
        }
        if (hubController.choice[0] == 3) {
            System.exit(0);
        }
    }

    public void startWorldModel() throws InterruptedException {
        new WorldModel();
    }

    public void startLootModel() throws InterruptedException {
        LootModel lm = new LootModel();
        lm.startLootController();
    }
}