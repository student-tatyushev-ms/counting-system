package com.counting_system.view;

import com.counting_system.controller.Controller;
import com.counting_system.model.Contractor;

import java.sql.SQLException;
import java.util.Scanner;

public class ReferenceContractorWindow extends Window {

    public ReferenceContractorWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Таблицы-справочники Контрагент ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Добавить");
        System.out.println("[2] : Удалить");
        System.out.println("[3] : Изменить");
        System.out.println("[4] : Вывести список контрагентов");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            System.out.print("Введите название контрагента: ");
            String value = getScanner().next();
            getController().contractorInsert(value);
        } else if (command == 2) {
            System.out.print("Введите id контрагента: ");
            int id = getScanner().nextInt();
            getController().contractorDelete(id);
        } else if (command == 3) {
            System.out.print("Введите id контрагента: ");
            int id = getScanner().nextInt();
            System.out.print("Введите название контрагента: ");
            String value = getScanner().next();
            getController().contractorUpdate(id, value);
        } else if (command == 4) {
            System.out.println();
            System.out.println(String.format("%-4s | %-15s", "ID", "Название"));
            System.out.println("———————————————");
            for (Contractor item : getController().contractorSelect()) {
                System.out.println(String.format("%-4s | %-15s", String.valueOf(item.getId()), item.getName()));
            }
        } else {
            System.out.println("Введен некорректный номер.");
        }
        return null;
    }

}
