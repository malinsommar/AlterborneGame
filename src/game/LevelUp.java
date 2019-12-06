package game;

public class LevelUp {

    public int xp;
    public int level = 1;

    public void levelSystem(){

        if (xp>15 && level == 1){
            level = 2;
            //Lägg in vad som händer
        }
        else if (xp>30 && level == 2){
            level = 3;
        }
        else if (xp>80 && level == 3){
            level = 4;
        }
        else if(xp>160 && level == 4){
            level = 5;
        }
        else if(xp>300 && level == 5){

        }

    }

}
