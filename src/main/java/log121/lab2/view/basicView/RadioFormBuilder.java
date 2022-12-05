package log121.lab2.view.basicView;

import log121.lab2.view.copyStrategyViews.CopyRadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;

public class RadioFormBuilder {

    // REQUIRED PARAMTER
    private Class<?> type;
    private List<JRadioButton> radioButtons;

    // OPTIONAL PARAMETER
    private  String explanationText;

    private String cancelTxt = "CANCEL";
    private String confirmTxt = "CONFIRM";

    private String radioButtonPanelLayout;
    private String confirmationPanelLayout;
    private String explanationPanelLayout;

    private ActionListener cancelAction;
    private Consumer<CustomRadioButton> confirmAction;
    public RadioFormBuilder(List<JRadioButton> radioButtons)
    {
        this.type = RadioForm.class;
        this.radioButtons = radioButtons;
        this.cancelAction = this::closeWindow;
        this.radioButtonPanelLayout = BorderLayout.CENTER;
        this.confirmationPanelLayout = BorderLayout.AFTER_LAST_LINE;
    }

    public RadioFormBuilder explanationText(String text)
    {
        explanationText = text;
        this.explanationPanelLayout = BorderLayout.NORTH;
        return this;
    }

    public RadioFormBuilder confirmAction(Consumer<CustomRadioButton> radioButton)
    {
        this.confirmAction = radioButton;
        return this;
    }

    public RadioFormBuilder confirmText(String text)
    {
        this.confirmTxt = text;
        return this;
    }

    public RadioFormBuilder cancelText(String text)
    {
        this.cancelTxt = text;
        return this;
    }

    public RadioForm build()
    {

        RadioForm form = null;
        try {
           Constructor<?> constructor = type.getDeclaredConstructor(this.getClass());
           form = (RadioForm) constructor.newInstance(this);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return form;
    }

    private void closeWindow(ActionEvent e)
    {
    }


    // GETTER


    public List<JRadioButton> getRadioButtons() {
        return radioButtons;
    }

    public Consumer<CustomRadioButton> getConfirmAction() {
        return confirmAction;
    }

    public String getExplanationText() {
        return explanationText;
    }

    public String getCancelTxt() {
        return cancelTxt;
    }

    public String getConfirmTxt() {
        return confirmTxt;
    }

    public String getRadioButtonPanelLayout() {
        return radioButtonPanelLayout;
    }

    public String getConfirmationPanelLayout() {
        return confirmationPanelLayout;
    }

    public String getExplanationPanelLayout() {
        return explanationPanelLayout;
    }

    public ActionListener getCancelAction() {
        return cancelAction;
    }
}
