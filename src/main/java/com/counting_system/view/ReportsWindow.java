package com.counting_system.view;

import com.counting_system.controller.Controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class ReportsWindow extends Window {

    public ReportsWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Отчеты ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Остатки товаров");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            System.out.println("/*~~~~~~~~~~~~~~~ Остатки товаров ~~~~~~~~~~~~~~~*/");
            System.out.println("Введите дату");

            System.out.print("Год: ");
            int year = getScanner().nextInt() - 1900;
            System.out.print("Месяц: ");
            int month = getScanner().nextInt() - 1;
            System.out.print("День: ");
            int day = getScanner().nextInt() - 1;

            Date date = new Date(year, month, day);

            System.out.println();
            System.out.println(String.format("%-12s | %-6s | %-10s | %-5s",
                    "Номенклатура", "Партия", "Количество", "Сумма"
            ));
            System.out.println("—————————————————————————————————————————————");
            getController().goodsSelect().stream()
                    .filter(item -> item.getGoodsReceipt().getDate() == date)
                    .forEach(item -> System.out.println(String.format("%-12s | %-6d | %-10d | %-5d",
                            item.getNomenclature().getTitle(),
                            item.getId(),
                            item.getNumber(),
                            item.getPrise()
                    )));
        } else {
            System.out.println("Введен некорректный номер.");
        }
        return null;
    }
}
