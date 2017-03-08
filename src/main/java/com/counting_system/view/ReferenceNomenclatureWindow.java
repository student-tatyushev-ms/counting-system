package com.counting_system.view;

import com.counting_system.controller.Controller;
import com.counting_system.model.Nomenclature;

import java.sql.SQLException;
import java.util.Scanner;

public class ReferenceNomenclatureWindow extends Window {

    public ReferenceNomenclatureWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Таблицы-справочники Номенклатура ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Добавить");
        System.out.println("[2] : Удалить");
        System.out.println("[3] : Изменить");
        System.out.println("[4] : Вывести список номенклатуры");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            System.out.print("Введите название номенклатуры: ");
            String value = getScanner().next();
            getController().nomenclatureInsert(value);
        } else if (command == 2) {
            System.out.print("Введите id номенклатуры: ");
            int id = getScanner().nextInt();
            getController().nomenclatureDelete(id);
        } else if (command == 3) {
            System.out.print("Введите id номенклатуры: ");
            int id = getScanner().nextInt();
            System.out.print("Введите название номенклатуры: ");
            String value = getScanner().next();
            getController().nomenclatureUpdate(id, value);
        } else if (command == 4) {
            System.out.println();
            System.out.println(String.format("%-4s | %-15s", "ID", "Название"));
            System.out.println("———————————————");
            for (Nomenclature item : getController().nomenclatureSelect()) {
                System.out.println(String.format("%-4d | %-15s",
                        item.getId(),
                        item.getTitle()
                ));
            }
        } else {
            System.out.println("Введен некорректный номер.");
        }
        return null;
    }

}
