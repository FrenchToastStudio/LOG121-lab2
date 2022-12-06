package log121.lab2.view.components.menuBar;

import log121.lab2.controller.MainController;
import log121.lab2.view.ImageView;
import log121.lab2.view.components.menuItem.SelectImageMenuItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Selectable view MenuBar
 */
public class SelectViewMenuBar extends JMenuBar {

    private List<SelectImageMenuItem> menuItemList;
    private MainController controller;

    public SelectViewMenuBar(MainController mainController) {
        this.controller = mainController;
    }

    /**
     * sets up the tabs depending on the image views
     * @param imageViews the images-view that can be tabbed in
     */
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

    /**
     * Selects the right tab to update the coloring
     * @param id id of the tab that we want to select
     */
    public void select(int id)
    {
        if(menuItemList != null) {
            if (menuItemList.size() > 0) {
                menuItemList.forEach(SelectImageMenuItem::unSelect);
                menuItemList.stream().filter(selectImageMenuItem -> selectImageMenuItem.getId() == id).findFirst().get().select();
            }

            //resets menu item not sure if it work without
            // TODO: confirm if we can remove this
            for (JMenuItem item : menuItemList) {
                remove(item);
            }
            for (SelectImageMenuItem menuItem : menuItemList) {
                add(menuItem);
            }
        }
    }
}
