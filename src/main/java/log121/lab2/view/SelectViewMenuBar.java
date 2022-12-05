package log121.lab2.view;

import log121.lab2.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectViewMenuBar extends JMenuBar {

    private List<SelectImageMenuItem> menuItemList;
    private MainController controller;

    public SelectViewMenuBar(MainController mainController) {
        this.controller = mainController;
    }

    public void setTabs(List<ImageView> imageViews)
    {
        if(menuItemList == null)
        {
            menuItemList  = new ArrayList<>();
        }
        for (JMenuItem item: menuItemList) {
            remove(item);
        }

        menuItemList  = new ArrayList<>();

        for (int i = 0; i < imageViews.size(); i++) {
            menuItemList.add(new SelectImageMenuItem("Vue " + (i+1), i,this.controller));
        }
        for (SelectImageMenuItem menuItem : menuItemList) {
            add(menuItem);
        }
    }

    public void select(int id)
    {
        if(menuItemList != null) {
            if (menuItemList.size() > 0) {
                menuItemList.forEach(SelectImageMenuItem::unSelect);
                menuItemList.stream().filter(selectImageMenuItem -> selectImageMenuItem.getId() == id).findFirst().get().select();
            }
            for (JMenuItem item : menuItemList) {
                remove(item);
            }
            for (SelectImageMenuItem menuItem : menuItemList) {
                add(menuItem);
            }
        }
    }
}
