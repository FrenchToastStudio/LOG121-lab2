package log121.lab2.view.components.handler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class InputHandler implements KeyListener {
    private Set<Integer> activeKeys;
    private List<KeyPressEventResult> commands;

    public InputHandler()
    {
        this.activeKeys = new HashSet<Integer>();
        this.commands = new ArrayList<>();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

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

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        activeKeys.remove(e.getKeyCode());
    }

    public void addEvent(Set<Integer> keyCodes, Consumer command)
    {
        this.commands.add(new KeyPressEventResult(keyCodes, command));
    }

    public class KeyPressEventResult
    {
        private Set<Integer> keyCodes;
        private Consumer<KeyEvent> command;
        public KeyPressEventResult(Set<Integer> keyCodes, Consumer<KeyEvent> command)
        {
            this.keyCodes = keyCodes;
            this.command = command;
        }

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

        public void execute(KeyEvent e) {
            this.command.accept(e);
        }
    }
}
