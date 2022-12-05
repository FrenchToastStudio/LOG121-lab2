package log121.lab2.view.copyStrategyViews;

import log121.lab2.controller.Command;
import log121.lab2.controller.CommandManager;
import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.ChooseCopyStrategyCommand;
import log121.lab2.view.View;
import log121.lab2.view.basicView.CustomRadioButton;
import log121.lab2.view.basicView.RadioFormBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CopyStrategyMenuItem extends JMenuItem implements View {

    private static final String TEXT = "Choose Copy Strategy";
    private ChooseCopyStrategyCommand chooseCopyStrategyCommand;
    private List<Command> commands;
    public CopyStrategyMenuItem(MainController mainController)
    {
        commands = new ArrayList<>();
        this.chooseCopyStrategyCommand = new ChooseCopyStrategyCommand();
        commands.add(chooseCopyStrategyCommand);

        setText(TEXT);
        addActionListener((ActionEvent e) -> {
            List<JRadioButton> radioButtonList = new ArrayList<>()
            {{
                add(new CopyRadioButton(CopyStrategyEnum.ALL, mainController));
                add(new CopyRadioButton(CopyStrategyEnum.POSITION, mainController));
                add(new CopyRadioButton(CopyStrategyEnum.SIZE, mainController));
                add(new CopyRadioButton(CopyStrategyEnum.DEACTIVATE, mainController));
            }};

            new RadioFormBuilder(radioButtonList)
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
    }

    public void closeForm(ActionEvent event)
    {
        destroy();
        SwingUtilities.getWindowAncestor((Component) event.getSource()).dispose();

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
