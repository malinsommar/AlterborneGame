package game;

/**
 * This method contains all methods needed to start hubFrame and contains actionsListeners that waits for input.
 */
public class HubController {

    private HubView hf = new HubView();

    boolean tutorialDone = false;
    boolean gameDone = false;
    boolean exitDone = false;

    /**
     *Starts the hubView and adds actionListeners
     */
    public void startHubScreen(){
        hf.hubFrame();

        hf.newRunButton.addActionListener(e -> newRunButtonClick());
        hf.startButton.addActionListener(e -> startGameClick());
        hf.tutorialButton.addActionListener(e -> tutorialButtonClick());
        hf.exitButton.addActionListener(e -> exitButtonClick());
    }

    /**
     * This method is called when newRunButton is clicked, open backstoryPanel.
     */
    //Call this method when newRunButton is clicked in hubPanel.
    private void newRunButtonClick(){
        hf.hubJFrame.add(hf.BackStoryPanel);
        hf.hubPanel.setVisible(false);
        MusicPick.musicStart("theme", "music");
    }

    /**
     * This method is called when tutorialButton is clicked, change tutorialClicked boolean.
     */
    //Call this method when exitButton is clicked in hubPanel.
    private void tutorialButtonClick(){
        hf.hubJFrame.dispose();
        tutorialDone = true;
    }

    /**
     * This method is called when exitButton is clicked, change exitClicked boolean.
     */
    //Call this method when exitButton is clicked in hubPanel.
    private void exitButtonClick(){
        hf.hubJFrame.dispose();
        exitDone = true;
    }

    /**
     * This method is called when startGame is clicked, change startGame boolean.
     */
    //Call this method when startGame is clicked in BackStoryPanel.
    private void startGameClick(){
        hf.hubJFrame.dispose();
        gameDone = true;
    }
}