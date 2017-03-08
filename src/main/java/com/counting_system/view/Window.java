package com.counting_system.view;

import com.counting_system.controller.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class Window {

    private Scanner scanner;
    private Controller controller;

    public Window(Scanner scanner, Controller controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void printMenu() {
        System.out.println();
        printWindowMenu();
        System.out.println("[0] : Вернуться / Выход");
        System.out.print("Введите номер желаемого действия: ");
    }

    abstract void printWindowMenu();

    public Window action(int command) {
        Window window;
        try {
            window = windowAction(command);
        } catch (SQLException e) {
            System.out.print("Случилась ошибка: " + e.getLocalizedMessage());
            return null;
        }
        return window;
    }

    protected abstract Window windowAction(int command) throws SQLException;

    public Scanner getScanner() {
        return scanner;
    }

    public Controller getController() {
        return controller;
    }

}
