package davidtest.overworld.map.Functionality;

import davidtest.overworld.map.WorldView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Implement the KeyListener library to read keyboard inputs
 */
public class InputHandler implements KeyListener {
    /**
     * Calls the InputHandler whenever a key is pressed
     * @param game adds input to the view
     */
    public InputHandler(WorldView game) {
        game.addKeyListener(this);
    }

    /**
     * identify if keus have been
     */
    public static class Key {
        boolean pressed = false; //sets default value of "pressed" to false

        //create a "isPressed" method that will be called as an object in game

        /**
         * identify if a key has been pressed
         * @return pressed if key has been pressed
         */
        public boolean isPressed() {
            return pressed;
        }


        /**
         * create a method that sets the value of pressed to isPressed, which will be affected by KeyInputs
         * @param isPressed gets the value from pressed
         */
        public void toggle(boolean isPressed) {
            pressed = isPressed;
        }
    }

    /**
     *  Register all the key-objects for the movement-options
     */
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();


    /**
     *         //change the keyPressed method to true when a key is pressed
     * @param e identification that its key-function
     */
    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    /**
     * change the keyPressed method to false when a key is not pressed
     * @param e identification that its key-function
     */
    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    /**
     * obligatory method to set when a key has been typed
     * @param e identification that its key-function
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Assign the key-objects with keyboard-keys which sets the "isPressed" boolean to true if used
     * @param keyCode code for every key used
     * @param isPressed whether the key has been pressed or not
     */
    public void toggleKey(int keyCode, boolean isPressed) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            up.toggle((isPressed)); //upKey
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            down.toggle((isPressed)); //downKey
        }
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            left.toggle((isPressed)); //leftKey
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            right.toggle((isPressed)); //rightKey
        }
    }
}

