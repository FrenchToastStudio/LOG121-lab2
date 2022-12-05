package log121.lab2.view.basicView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Consumer;

public class RadioForm extends JFrame implements IRadioForm {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String TITLE = "SELECT COPY STATEGYs";
    private static final Dimension DIMENSION = new Dimension(400, 200);

    public ButtonGroup buttonGroup;
    private final Container container;

    public RadioForm(RadioFormBuilder builder)
    {
        this.container = getContentPane();

        String explanationText = builder.getExplanationText();

        if(explanationText != null) {
            if (!explanationText.trim().isEmpty()) {
                setUpExplanationPanel(explanationText, builder.getExplanationPanelLayout());
            }
        }
        setUpChoice(builder.getRadioButtons() ,builder.getRadioButtonPanelLayout());

        String confirmationText = builder.getConfirmTxt();
        String cancelText = builder.getCancelTxt();
        String confirmationPanelLayout = builder.getConfirmationPanelLayout();

        Consumer<CustomRadioButton> confirmAction = builder.getConfirmAction();

        if (confirmAction == null) {
            setUpConfirmation(confirmationPanelLayout, confirmationText, cancelText);
        } else {
            setUpConfirmation(confirmationPanelLayout, confirmationText, cancelText, confirmAction, null);
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(TITLE);
        setSize(DIMENSION);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }



    private void setUpExplanationPanel(String explanationText, String explanationPanelLayout)
    {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel(explanationText);

        jPanel.add(jLabel);

        this.container.add(jPanel, explanationPanelLayout);
    }

    private void setUpChoice(List<JRadioButton> radioButtons, String radioButtonPanelLayout)
    {
        JPanel radioButtonPanel = new JPanel();
        this.buttonGroup = new ButtonGroup();

        radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.PAGE_AXIS));
        radioButtonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        radioButtons.forEach(jRadioButton -> addButton(radioButtonPanel, jRadioButton, buttonGroup));

        this.container.add(radioButtonPanel, radioButtonPanelLayout);
    }

    private void setUpConfirmation(String confirmationPanelLayout, String confirmationText, String cancelText)
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        addConfirmButton(buttonPanel, confirmationText);
        addCancelButton(buttonPanel, cancelText);

        this.container.add(buttonPanel, confirmationPanelLayout);
    }

    private void setUpConfirmation(String confirmationPanelLayout, String confirmationText, String cancelText,  Consumer<CustomRadioButton> confirmAction, Consumer cancelAction) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        if (confirmAction == null)
        {
            addConfirmButton(buttonPanel, confirmationText);
        }
        else
        {
            addConfirmButton(buttonPanel, confirmationText, confirmAction);
        }
        if (cancelAction == null)
        {
            addCancelButton(buttonPanel, cancelText);
        }
        else
        {
            addCancelButton(buttonPanel, cancelText, cancelAction);
        }

        this.container.add(buttonPanel, confirmationPanelLayout);
    }


    private void addConfirmButton(JPanel panel, String confirmTxt, Consumer<CustomRadioButton> confirmAction)
    {
        JButton confirmButton = new JButton(confirmTxt);
        confirmButton.addActionListener((ActionListener) -> confirmAction.accept(getToggleButton()));
        panel.add(confirmButton);
    }

    private void addConfirmButton(JPanel panel, String confirmTxt)
    {
        JButton confirmButton = new JButton(confirmTxt);
        confirmButton.addActionListener(this::confirmAction);
        panel.add(confirmButton);
    }

    private void addCancelButton(JPanel panel, String cancelTxt)
    {
        JButton cancelButton = new JButton(cancelTxt);
        cancelButton.addActionListener(this::cancelAction);
        panel.add(cancelButton);
    }

    private void addCancelButton(JPanel panel, String cancelTxt, Consumer<ActionEvent> cancelAction)
    {
        JButton cancelButton = new JButton(cancelTxt);
        //cancelButton.addActionListener((ActionEvent) e-> cancelAction.accept(e));
        panel.add(cancelButton);
    }

    private void addButton(JPanel panel, JRadioButton button, ButtonGroup buttonGroup)
    {
        buttonGroup.add(button);
        panel.add(button);
    }

    private void confirmAction(ActionEvent event)
    {
        getToggleButton().execute();
        closeForm(event);
    }

    protected CustomRadioButton getToggleButton()
    {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            CustomRadioButton button = (CustomRadioButton) buttons.nextElement();
            if (button.isSelected()) {
                return button;
            }
        }

        return null;
    }

    private void cancelAction(ActionEvent event)
    {
        closeForm(event);
    }

    public void closeForm(ActionEvent event)
    {
        SwingUtilities.getWindowAncestor((Component) event.getSource()).dispose();
    }
}