package game;

import davidtest.overworld.map.OverWorldFrame;
import davidtest.overworld.map.WorldModel;

public class MasterModel {
    private HubController hubController = new HubController();
    FightModel fightModel = new FightModel();


    //Get user input from ConHub to start game of exit game.
    public void startGame() throws InterruptedException {

        System.out.println("start test");
        hubController.test();

        if(hubController.choice[0]==1){
            new WorldModel();
        }
        if (hubController.choice[0]==3){
            System.exit(0);
        }

       /* switch (ch.choice[0]){

            case 1:
                overworld.start();
                //forestFight
                //80% normal fight
                //20% boss figth

            case 2:
                //cavefight
                //80% normal fight
                //20% boss figth
        }*/

    }
}