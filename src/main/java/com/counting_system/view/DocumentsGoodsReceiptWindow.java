package com.counting_system.view;

import com.counting_system.Utils;
import com.counting_system.controller.Controller;
import com.counting_system.model.Contractor;
import com.counting_system.model.Goods;
import com.counting_system.model.GoodsReceipt;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DocumentsGoodsReceiptWindow extends Window {

    public DocumentsGoodsReceiptWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Таблицы-документы Поступление товара ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Добавить");
        System.out.println("[2] : Вывести список поступления товаров");
        System.out.println("[3] : Вывести табличную часть документа");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            System.out.println("Введите дату");
            System.out.print("Год: ");
            int year = getScanner().nextInt() - 1900;
            System.out.print("Месяц: ");
            int month = getScanner().nextInt() - 1;
            System.out.print("День: ");
            int day = getScanner().nextInt() - 1;
            System.out.print("Введите номер контрагента: ");
            int id = getScanner().nextInt();

            List<Contractor> contractorList = getController().contractorSelect();
            Contractor contractor = Utils.getContractor(id, contractorList);
            getController().goodsReceiptInsert(new Date(year, month, day), contractor);
        } else if (command == 2) {
            System.out.println();
            System.out.println(String.format("%-4s | %-10s | %-15s | %-10s",
                    "ID", "Дата", "Код контрагента", "Проведение")
            );
            System.out.println("—————————————————————————————");
            for (GoodsReceipt item : getController().goodsReceiptSelect()) {
                System.out.println(String.format("%1$-4s | %2$2td-%2$2tm-%2$4tY | %3$-15s | %4$-10s",
                        String.valueOf(item.getId()),
                        item.getDate(),
                        item.getContractor().getId(),
                        (item.getStatus() ? "+" : "")
                ));
            }
        } else if (command == 3){
            System.out.println("Введите номер документа");
            int receiptId = getScanner().nextInt();

            System.out.println();
            System.out.println(String.format("%-10s | %-5s | %-9s | %-15s | %-21s",
                    "Количество", "Цена", "Стоимость", "ID номенклатуры", "Название номенклатуры")
            );
            System.out.println("——--------------------———————————————————————————");
            for (Goods item : getController().selectGoodsByReceiptId(receiptId)) {
                System.out.println(String.format("%-10d | %-5d | %-9d | %-15s | %-21s",
                        item.getNumber(),
                        item.getPrise(),
                        item.getCost(),
                        item.getNomenclature().getId(),
                        item.getNomenclature().getTitle()
                ));
            }

        } else {
            System.out.println("Введен некорректный номер.");
        }
        return null;
    }
}
