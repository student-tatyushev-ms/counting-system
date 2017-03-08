package com.counting_system.view;

import com.counting_system.controller.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class MainWindow extends Window {

    public MainWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    public void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Система контроля инвентаризации ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Таблицы-справочники");
        System.out.println("[2] : Таблицы-документы");
        System.out.println("[3] : Таблицы-регистры");
        System.out.println("[4] : Отчеты");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            return new ReferenceWindow(getScanner(), getController());
        } else if (command == 2) {
            return new DocumentsWindow(getScanner(), getController());
        } else if (command == 3) {
            return new RegistersWindow(getScanner(), getController());
        } else if (command == 4) {
            return new ReportsWindow(getScanner(), getController());
        } else {
            System.out.println("Введен некорректный номер.");
            return null;
        }
    }

}
