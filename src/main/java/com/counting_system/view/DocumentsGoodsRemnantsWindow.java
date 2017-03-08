package com.counting_system.view;

import com.counting_system.Utils;
import com.counting_system.controller.Controller;
import com.counting_system.model.Contractor;
import com.counting_system.model.GoodsReceipt;
import com.counting_system.model.GoodsRemnants;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DocumentsGoodsRemnantsWindow extends Window {

    public DocumentsGoodsRemnantsWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Таблицы-документы Реализация товара ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Добавить");
        System.out.println("[2] : Вывести список реализации товаров");
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
            System.out.print("Введите количество: ");
            int number = getScanner().nextInt();
            System.out.print("Введите сумму: ");
            int value = getScanner().nextInt();

            System.out.print("Введите id контрагента: ");
            int contractorId = getScanner().nextInt();

            System.out.print("Введите id документа получения товара: ");
            int goodsReceiptId = getScanner().nextInt();

            List<Contractor> contractorList = getController().contractorSelect();
            List<GoodsReceipt> goodsReceipts = getController().goodsReceiptSelect();

            Contractor contractor = Utils.getContractor(contractorId, contractorList);
            GoodsReceipt goodsReceipt = Utils.getGoodsReceipt(goodsReceiptId, goodsReceipts);

            getController().goodsRemnantsInsert(new Date(year, month, day), number, value, contractor, goodsReceipt);
        } else if (command == 2) {
            System.out.println();
            System.out.println(String.format("%-4s | %-10s | %-10s | %-5s | %-15s | %-33s",
                    "ID", "Дата", "Количество", "Сумма", "Код контрагента", "Код документа поступления товаров"));
            System.out.println("———————————————————————————————————————————————————————");
            for (GoodsRemnants item : getController().goodsRemnantsSelect()) {
                System.out.println(String.format("%1$-4d | %2$2td-%2$2tm-%2$4tY | %3$-10s | %4$-5s | %5$-15s | %6$-33s",
                        item.getId(),
                        item.getDate(),
                        item.getNumber(),
                        item.getSum(),
                        item.getContractor().getId(),
                        item.getGoodsReceipt().getId()
                ));
            }
        } else {
            System.out.println("Введен некорректный номер.");
        }
        return null;
    }

}
