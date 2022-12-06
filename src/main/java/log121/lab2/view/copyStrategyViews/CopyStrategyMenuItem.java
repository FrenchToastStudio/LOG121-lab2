package log121.lab2.view.copyStrategyViews;

import log121.lab2.controller.commands.Command;
import log121.lab2.controller.commands.CommandManager;
import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.ChooseCopyStrategyCommand;
import log121.lab2.view.View;
import log121.lab2.view.basicView.CustomRadioButton;
import log121.lab2.view.basicView.RadioForm;
import log121.lab2.view.basicView.RadioFormBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class CopyStrategyMenuItem extends JMenuItem implements View {

    private static final String TEXT = "Choose Copy Strategy";
    private ChooseCopyStrategyCommand chooseCopyStrategyCommand;
    private List<Command> commands;
    private RadioForm frame;
    public CopyStrategyMenuItem(MainController mainController)
    {
        commands = new ArrayList<>();
        this.chooseCopyStrategyCommand = new ChooseCopyStrategyCommand();
        commands.add(chooseCopyStrategyCommand);

        setText(TEXT);
        addActionListener((ActionEvent e) -> {
            List<JRadioButton> radioButtonList = new ArrayList<>()
            {{
                add(new CopyRadioButton(CopyStrategyEnum.ALL));
                add(new CopyRadioButton(CopyStrategyEnum.POSITION));
                add(new CopyRadioButton(CopyStrategyEnum.SIZE));
                add(new CopyRadioButton(CopyStrategyEnum.DEACTIVATE));
            }};

            frame = new RadioFormBuilder(radioButtonList)
                    .explanationText("This will change the behavior of 'ctrl-c + ctrl-v'")
                    .confirmAction(this::buttonSucceed)
                    .build();
        });

        activate();
    }

    private void buttonSucceed(CustomRadioButton button)
    {
        CopyRadioButton radioButton = (CopyRadioButton) button;
        chooseCopyStrategyCommand.buttonToggle(radioButton.getCopyStrategyEnum());
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    public void closeForm(Component component)
    {
        destroy();

        SwingUtilities.getWindowAncestor(component).dispose();

    }

    @Override
    public void activate() {
        CommandManager.getInstance().attachCommand(this, commands);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void destroy() {
        CommandManager.getInstance().detachCommand(this);
    }
}
