package log121.lab2.view;

import log121.lab2.controller.MainController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectViewMenuBar extends JMenuBar {

    private static final String MENU_VIEW_1 = "View 1";

    private static final String MENU_VIEW_2 = "View 2";

    private static final String MENU_VIEW_3 = "View 3";

    private List<SelectImageMenuItem> menuItemList;

    public SelectViewMenuBar(MainController mainController, List<ImageView> imageViews) {

        menuItemList  = new ArrayList<>();
        for (int i = 0; i < imageViews.size(); i++) {
            menuItemList.add(new SelectImageMenuItem(imageViews.get(i).getClass().getSimpleName()+ " " + i, i,mainController));
        }
        for (SelectImageMenuItem menuItem : menuItemList) {
            add(menuItem);
        }
    }

    public void select(int id)
    {
        menuItemList.forEach(SelectImageMenuItem::unSelect);
        menuItemList.stream().filter(selectImageMenuItem -> selectImageMenuItem.getId() == id).findFirst().get().select();
    }
}
