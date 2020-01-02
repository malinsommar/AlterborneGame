package game;

import davidtest.overworld.hub.OverWorld;

public class MasterModel {

    OverWorld overworld = new OverWorld();
    private ConHub ch = new ConHub();
    FightModel fm = new FightModel();


    //Get user input from ConHub to start game of exit game.
    public void startGame(){

        ch.test();

        if(ch.choice[0]==1){
            overworld.start();
        }
        if (ch.choice[0]==3){
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

       //LootScreen model here, skicka med xp

    }
}