package com.counting_system.view;

import com.counting_system.Utils;
import com.counting_system.controller.Controller;
import com.counting_system.model.Goods;
import com.counting_system.model.GoodsReceipt;
import com.counting_system.model.Nomenclature;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DocumentsWindow extends Window {

    public DocumentsWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Таблицы-документы ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Поступление товара");
        System.out.println("[2] : Реализация товара");
        System.out.println("[3] : Проведение документа");
        System.out.println("[4] : Отменить проведение документа");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            return new DocumentsGoodsReceiptWindow(getScanner(), getController());
        } else if (command == 2) {
            return new DocumentsGoodsRemnantsWindow(getScanner(), getController());
        } else if (command == 3) {
            System.out.println("/*~~~~~~~~~~~~~~~ Провести документ ~~~~~~~~~~~~~~~*/");
            System.out.print("Введите количество товара: ");
            int number = getScanner().nextInt();
            System.out.print("Введите закупочную стоимость товара: ");
            int prise = getScanner().nextInt();
            System.out.print("Введите цену товара: ");
            int cost = getScanner().nextInt();
            System.out.print("Введите код документа поступления товара: ");
            int id_goods_receipt = getScanner().nextInt();
            System.out.print("Введите код номенклатуры: ");
            int id_nomenclature = getScanner().nextInt();

            List<GoodsReceipt> goodsReceipts = getController().goodsReceiptSelect();
            List<Nomenclature> nomenclatures = getController().nomenclatureSelect();

            GoodsReceipt goodsReceipt = Utils.getGoodsReceipt(id_goods_receipt, goodsReceipts);
            Nomenclature nomenclature = Utils.getNomenclature(id_nomenclature, nomenclatures);

            getController().goodsInsert(number, prise, cost, goodsReceipt, nomenclature);
            getController().prosecution(goodsReceipt);
            return null;
        } else if (command == 4) {
            System.out.println("/*~~~~~~~~~~~~~~~ Отмена проведения документа ~~~~~~~~~~~~~~~*/");
            System.out.print("Введите номер проведенного документа который нужно отменить: ");
            int goodsId = getScanner().nextInt();

            List<Goods> goodsList = getController().goodsSelect();

            Goods goods = Utils.getGoods(goodsId, goodsList);

            GoodsReceipt goodsReceipt = goods.getGoodsReceipt();
            getController().cancelProsecution(goodsReceipt);
            getController().goodsDelete(goods.getId());
            return null;
        } else {
            System.out.println("Введен некорректный номер.");
            return null;
        }
    }
}
