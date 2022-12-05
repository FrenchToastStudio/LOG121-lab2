package log121.lab2.view.copyStrategyViews;

import log121.lab2.controller.MainController;
import log121.lab2.controller.commands.ChooseCopyStrategyCommand;
import log121.lab2.view.basicView.CustomRadioButton;

import javax.swing.*;

public class CopyRadioButton extends CustomRadioButton {

    private CopyStrategyEnum copyStrategyEnum;
    private static final String COPY_ALL_CHOICE = "Copy All";
    private static final String DEACTIVATE = "Deactivate";
    private static final String COPY_SIZE = "Copy Size";
    private static final String COPY_POSITION = "Copy Position";

    public CopyRadioButton(CopyStrategyEnum copyStrategyEnum, MainController mainController)
    {
        this.copyStrategyEnum = copyStrategyEnum;
        switch (copyStrategyEnum)
        {
            case ALL -> setText(COPY_ALL_CHOICE);
            case SIZE -> setText(COPY_SIZE);
            case DEACTIVATE -> setText(DEACTIVATE);
            case POSITION -> setText(COPY_POSITION);
            default -> setText(DEACTIVATE);
        }
    }

    public CopyStrategyEnum getCopyStrategyEnum() {
        return copyStrategyEnum;
    }

    @Override
    public void execute() {
    }
}
