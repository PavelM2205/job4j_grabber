package ru.job4j.isp.menu.todo;

import ru.job4j.isp.menu.*;

import java.util.Scanner;

public class TODOList {
    private final Menu menu = new SimpleMenu();
    private final MenuPrinter printer = new ConsoleMenuPrinter();
    private final Scanner scanner = new Scanner(System.in);
    private static final int ADD_ROOT_TASK = 1;
    private static final int ADD_SUB_TASK = 2;
    private static final int SHOW_ALL_TASKS = 3;
    private static final int EXIT = 4;
    private boolean run = true;
    private final ActionDelegate actionDelegate = System.out::println;


    public void start() {
        while (run) {
            showMenu();
            int choice = 0;
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > 4) {
                System.out.println("Wrong argument");
                System.out.println();
                continue;
            }
            actions(choice);
        }
    }

    private void actions(int choice) {
        switch (choice) {
            case ADD_ROOT_TASK -> addRootTask();
            case ADD_SUB_TASK -> addSubTask();
            case SHOW_ALL_TASKS -> showTasks();
            case EXIT -> {
                run = false;
            }
            default -> System.out.println();
        }
    }

    private void addRootTask() {
        System.out.print("Enter name of the task: ");
        menu.add(Menu.ROOT, scanner.nextLine(), actionDelegate);
        System.out.println("Task is added.");
        System.out.println();
    }

    private void addSubTask() {
        System.out.print("Enter name of the parent task: ");
        String parent = scanner.nextLine();
        System.out.print("Enter name of the sub task: ");
        String child = scanner.nextLine();
        menu.add(parent, child, actionDelegate);
        System.out.println("Sub task is added.");
        System.out.println();
    }

    private void showTasks() {
        printer.print(menu);
        System.out.println();
    }

    private void showMenu() {
        System.out.println("Menu: ");
        System.out.println("1.Add the root task.");
        System.out.println("2.Add the sub task.");
        System.out.println("3.Show all tasks.");
        System.out.println("4.Exit.");
        System.out.print("Select: ");
    }

    public static void main(String[] args) {
        TODOList todo = new TODOList();
        todo.start();
    }
}
