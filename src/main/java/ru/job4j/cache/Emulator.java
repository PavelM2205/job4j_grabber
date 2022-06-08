package ru.job4j.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {

    private DirFileCache cache;
    private String cashingDirectory;
    private Scanner scanner = new Scanner(System.in);
    boolean run = true;
    private static final int ACTION_ONE = 1;
    private static final int ACTION_TWO = 2;
    private static final int ACTION_THREE = 3;
    private static final int ACTION_FOUR = 4;


    private void start() {
        while (run) {
            int selection = 0;
            showMenu();
            selection = Integer.parseInt(scanner.nextLine());
            if (selection < 1 | selection > 4) {
                System.out.println("Wrong argument...\n");
                continue;
            }
            actions(selection);
        }
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("1 Specify the caching directory.");
        System.out.println("2 Load a file in the cache.");
        System.out.println("3 Get a file from the cache.");
        System.out.println("4 Exit");
        System.out.print("Select: ");
    }

    private void actions(int number) {
        switch (number) {
            case ACTION_ONE:
                specifyDirectory();
                break;
            case ACTION_TWO:
                loadFile();
                break;
            case ACTION_THREE:
                getFile();
                break;
            case ACTION_FOUR:
                run = false;
                break;
            default:
                System.out.println("This is impossible.");
        }
    }

    private void specifyDirectory() {
        System.out.print("Enter directory: ");
        String directory = scanner.nextLine();
        checkDir(directory);
        cache = new DirFileCache(directory);
        cashingDirectory = directory;
        System.out.println("Directory is saved.\n");
    }

    private void loadFile() {
        System.out.print("Enter file name: ");
        String file = scanner.nextLine();
        if (cache != null) {
            checkFile(file);
            String loaded = cache.load(file);
            cache.put(file, loaded);
            System.out.println("File is successfully loaded.\n");
        } else {
            System.out.println("Not specified caching directory\n");
        }
    }

    private void getFile() {
        System.out.print("Enter file name: ");
        String file = scanner.nextLine();
        if (cache != null) {
            checkFile(file);
            System.out.println(cache.get(file));
            System.out.println();
        } else {
            System.out.println("Not specified caching directory\n");
        }
    }

    private void checkDir(String directory) {
        Path path = Path.of(directory);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Directory %s isn't exist", directory));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("File %s isn't directory", directory));
        }
    }

    private void checkFile(String file) {
        Path path = Path.of(cashingDirectory, file);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("%s isn't exist", file));
        }
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException(String.format("%s isn't file", file));
        }
    }

    public static void main(String[] args) {
        Emulator em = new Emulator();
        em.start();
    }
}
