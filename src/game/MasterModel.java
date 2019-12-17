package game;

import davidtest.overworld.map.OverWorld;

public class MasterModel {

    ConHub ch = new ConHub();
    OverWorld overworld = new OverWorld();

    //Get user input from ConHub to start game of exit game.
    public MasterModel(){
        int hubChoice = ch.startHub();

        if(hubChoice == 1){
            overworld.start();
        }
        if (hubChoice == 2){
            System.exit(0);
        }
    }
}