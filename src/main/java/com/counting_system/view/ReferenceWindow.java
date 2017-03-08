package com.counting_system.view;

import com.counting_system.controller.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class ReferenceWindow extends Window {

    public ReferenceWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    public void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Таблицы-справочники ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Контрагенты");
        System.out.println("[2] : Номенклатура");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            return new ReferenceContractorWindow(getScanner(), getController());
        } else if (command == 2) {
            return new ReferenceNomenclatureWindow(getScanner(), getController());
        } else {
            System.out.println("Введен некорректный номер.");
            return null;
        }
    }

}
