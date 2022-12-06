package log121.lab2.view.components.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Can be attached as a key listener and lets people give a combination of key that have to be pressed together
 * with the method to be called baked upon
 */
public class InputHandler implements KeyListener {
    private Set<Integer> activeKeys;
    private List<KeyPressEventResult> commands;

    /**
     * Basic Constructor
     */
    public InputHandler()
    {
        this.activeKeys = new HashSet<Integer>();
        this.commands = new ArrayList<>();
    }

    /**
     * Shows a key to be type for this Handler nothing happens since we want them to be pressed
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * Add a key pressed to the key pressed Set
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if(code == 0 )
            return;
        activeKeys.add(e.getKeyCode());
        for (KeyPressEventResult command: this.commands)
        {
            if(command.equals(activeKeys))
            {
                command.execute(e);
            }
        }
    }

    /**
     * Remove a key from the currently pressed list when it is released
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        activeKeys.remove(e.getKeyCode());
    }

    /**
     * Lets a user connect a method to a set of key code
     * @param keyCodes set of key to be added
     * @param command method called with a keyEvent
     */
    public void addEvent(Set<Integer> keyCodes, Consumer<KeyEvent> command)
    {
        this.commands.add(new KeyPressEventResult(keyCodes, command));
    }

    /**
     * Command to be add that are associated to a set of keyCode
     */
    private class KeyPressEventResult
    {
        private Set<Integer> keyCodes;
        private Consumer<KeyEvent> command;

        /**
         *
         * @param keyCodes key associated to the command
         * @param command method to be called
         */
        public KeyPressEventResult(Set<Integer> keyCodes, Consumer<KeyEvent> command)
        {
            this.keyCodes = keyCodes;
            this.command = command;
        }

        /**
         * redifines
         * @param keyCodes set of key to be compared to the Set of keys of this command
         * @return if is Equals
         */
        public boolean equals(Set<Integer> keyCodes) {

            if(keyCodes.size() > this.keyCodes.size() || keyCodes.size() < this.keyCodes.size())
                return false;

            for (Integer keyPressed: keyCodes)
            {
                boolean isPressed = false;
                for (Integer keyToPress: this.keyCodes) {
                    if(keyPressed.equals(keyToPress))
                    {
                        isPressed = true;
                        break;
                    }
                }
                if(!isPressed)
                    return false;
            }

            return true;
        }

        /**
         * call the method and executes it
         * @param e the event to be processed
         */
        public void execute(KeyEvent e) {
            this.command.accept(e);
        }
    }
}
