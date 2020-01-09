package game;

import davidtest.overworld.map.WorldModel;
import party.Healer;
import party.Mage;
import party.Ranger;
import party.Warrior;

public class MasterModel {

    private HubController hubController = new HubController();
    private TutorialController tc = new TutorialController();
    private Warrior w = new Warrior();
    private Healer h = new Healer();
    private Mage m = new Mage();
    private Ranger r = new Ranger();
    private LevelUpController luc = new LevelUpController();
    private Inventory inv = new Inventory();

    //TODO i fights, lägg till funktion som tar bort använda potions

    //Get user input from ConHub to start game of exit game.
    public void startGame() throws InterruptedException {

        System.out.println("start test");
        hubController.test();

        if (hubController.choice[0] == 1) {
            setStartNumbers();
            startWorldModel();
        }
        else if (hubController.choice[0] == 2) {
            tc.startTutorial();
        }
        else if (hubController.choice[0] == 3) {
            System.exit(0);
        }
    }
    
    public void setStartNumbers(){
        w.hp = 130;
        w.damage = 2;
        w.block = 3;

        r.hp = 80;
        r.damage = 3;
        r.block = 0;

        m.hp = 70;
        m.damage = 4;
        m.block = 0;

        h.hp = 100;
        h.damage = 0;
        h.block = 1;
        
        luc.level = 1;
        inv.gold = 20;
        
        inv.ownedMinorHealingPotion = 1;
        inv.ownedLesserHealingPotion = 0;
        inv.ownedMajorHealingPotion = 0;

        inv.ownedMinorBlockPotion = 1;
        inv.ownedLesserBlockPotion = 0;
        inv.ownedMajorBlockPotion = 0;

        inv.ownedMinorEnergyPotion = 1;
        inv.ownedLesserEnergyPotion = 0;
        inv.ownedMajorEnergyPotion = 0;

        inv.ownedMinorStrengthPotion = 1;
        inv.ownedLesserStrengthPotion = 0;
        inv.ownedMajorStrengthPotion = 0;

        luc.wHp = 5;
        luc.wD = 2;
        luc.wB = 2;

        luc.rHp = 3;
        luc.rD = 4;
        luc.rB = 2;

        luc.mHp = 2;
        luc.mD = 6;
        luc.mB = 1;

        luc.hHp = 5;
        luc.hD = 2;
        luc.hB = 3;
        
    }

    public void startWorldModel() throws InterruptedException {
        new WorldModel();
    }

    public void startLootModel(int whatFight) throws InterruptedException {
        LootModel lm = new LootModel();
        lm.startLootController(whatFight);
    }
}