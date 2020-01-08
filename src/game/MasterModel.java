package game;

import davidtest.overworld.map.WorldModel;

public class MasterModel {

    private HubController hubController = new HubController();
    private TutorialController tc = new TutorialController();

    //Get user input from ConHub to start game of exit game.
    public void startGame() throws InterruptedException {

        System.out.println("start test");
        hubController.test();

        if (hubController.choice[0] == 1) {
            startWorldModel();
        }
        else if (hubController.choice[0] == 2) {
            tc.startTutorial();
        }
        else if (hubController.choice[0] == 3) {
            System.exit(0);
        }
    }

    public void startWorldModel() throws InterruptedException {
        new WorldModel();
    }

    public void startLootModel(int whatFight) throws InterruptedException {
        LootModel lm = new LootModel();
        lm.startLootController(whatFight);
    }
}