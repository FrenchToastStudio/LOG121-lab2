package log121.lab2.view.copyStrategyViews;

import log121.lab2.view.basicView.CustomRadioButton;


public class CopyRadioButton extends CustomRadioButton {

    private CopyStrategyEnum copyStrategyEnum;
    private static final String COPY_ALL_CHOICE = "Copy All";
    private static final String DEACTIVATE = "Deactivate";
    private static final String COPY_SIZE = "Copy Size";
    private static final String COPY_POSITION = "Copy Position";

    /**
     * constructor
     * @param copyStrategyEnum enum representing the type of copy that we want to do
     */
    public CopyRadioButton(CopyStrategyEnum copyStrategyEnum)
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

    /**
     * the type of copy that this menuItem represents
     * @return a type of copy
     */
    public CopyStrategyEnum getCopyStrategyEnum() {
        return copyStrategyEnum;
    }

    @Override
    public void execute() {
    }
}
