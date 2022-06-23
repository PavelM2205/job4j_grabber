package ru.job4j.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class ConsoleMenuPrinterTest {

    private final ByteArrayOutputStream tempOut = new ByteArrayOutputStream();
    private final PrintStream stdOut = System.out;

    @Before
    public void setOut() {
        System.setOut(new PrintStream(tempOut));
    }

    @After
    public void returnOut() {
        System.setOut(stdOut);
    }

    @Test
    public void whenAddMenuPointsThenPrintSame() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter();
        ActionDelegate action = System.out::println;
        menu.add(Menu.ROOT, "Сходить в магазин", action);
        menu.add(Menu.ROOT, "Покормить собаку", action);
        menu.add("Сходить в магазин", "Купить продукты", action);
        menu.add("Купить продукты", "Купить хлеб", action);
        menu.add("Купить продукты", "Купить молоко", action);
        StringBuilder expectedOut = new StringBuilder();
        String ln = System.lineSeparator();
        expectedOut.append("1.Сходить в магазин")
                .append(ln)
                .append("----1.1.Купить продукты")
                .append(ln)
                .append("--------1.1.1.Купить хлеб")
                .append(ln)
                .append("--------1.1.2.Купить молоко")
                .append(ln)
                .append("2.Покормить собаку")
                .append(ln);
        printer.print(menu);
        assertEquals(tempOut.toString(), expectedOut.toString());
    }

    @Test
    public void whenEmptyMenuThenEmptyPrint() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new ConsoleMenuPrinter();
        printer.print(menu);
        String expected = "";
        assertEquals(tempOut.toString(), expected);
    }
}