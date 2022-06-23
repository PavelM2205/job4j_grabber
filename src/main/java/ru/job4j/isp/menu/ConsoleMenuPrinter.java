package ru.job4j.isp.menu;

import java.util.Iterator;


public class ConsoleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        Iterator<Menu.MenuItemInfo> iterator = menu.iterator();
        String space = "----";
        while (iterator.hasNext()) {
            Menu.MenuItemInfo itemInfo = iterator.next();
            int repeatCount = itemInfo.getNumber().split("\\.").length - 1;
            System.out.println(String.format("%s%s%s", space.repeat(repeatCount), itemInfo.getNumber(),
                    itemInfo.getName()));

        }
    }
}


