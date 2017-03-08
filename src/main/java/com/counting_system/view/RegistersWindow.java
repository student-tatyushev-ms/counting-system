package com.counting_system.view;

import com.counting_system.controller.Controller;
import com.counting_system.model.Goods;
import com.counting_system.model.GoodsRemnants;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RegistersWindow extends Window {

    public RegistersWindow(Scanner scanner, Controller controller) {
        super(scanner, controller);
    }

    @Override
    void printWindowMenu() {
        System.out.println("/*~~~~~~~~~~~~~~~ Таблицы-регистры ~~~~~~~~~~~~~~~*/");
        System.out.println("[1] : Вывести учет в разрезе номенклатура/количество/остаток товара");
        System.out.println("[2] : Учет продаж в разрезе номенклатуры/контрагентов/количества/суммы выручки/себестоимости/прибыли");
    }

    @Override
    protected Window windowAction(int command) throws SQLException {
        if (command == 1) {
            System.out.println();
            System.out.println("/*~~~~~~~~~~~~~~~ Складской учет в разрезе номенклатура/количество/остаток товара ~~~~~~~~~~~~~~~*/");
            System.out.println(String.format("%-15s | %-10s | %-22s", "Номенклатура", "Количество", "Сумовой остаток товара"));
            System.out.println("————————————————————————————————");
            for (Goods item : getController().goodsSelect()) {
                System.out.println(String.format("%-15s | %-10d | %-22d",
                        item.getNomenclature().getTitle(),
                        item.getNumber(),
                        (item.getNumber() * item.getCost())
                ));
            }
        } else if (command == 2) {
            System.out.println("/*~~~~~~~~~~~~~~~ Учет продаж в разрезе номенклатуры/контрагентов/количества/суммы выручки/себестоимости/прибыли ~~~~~~~~~~~~~~~*/");

            List<Goods> goodsList = getController().goodsSelect();
            List<GoodsRemnants> goodsRemnantsList = getController().goodsRemnantsSelect();

            for (int i = 0; i < goodsRemnantsList.size(); i++) {
                System.out.print("Контрагент: " + goodsRemnantsList.get(i).getContractor().getName() + " | ");

                for (int j = 0; j < goodsList.size(); j++) {
                    if (goodsRemnantsList.get(i).getId() == goodsList.get(j).getGoodsReceipt().getId()) {
                        System.out.print("номенклатура: " + goodsList.get(j).getNomenclature().getTitle() + " | ");
                    }
                }

                System.out.print(" количество: " + goodsRemnantsList.get(i).getNumber() + " | сумма выручки: ");

                for (Goods item : goodsList) {
                    if (goodsRemnantsList.get(i).getId() == item.getGoodsReceipt().getId()) {
                        System.out.print((item.getNumber() * item.getPrise()) +
                                " | себестоимость: " + item.getPrise() +
                                " | прибыль: " + ((item.getNumber() * item.getCost()) - (item.getNumber() * item.getPrise())));
                    }
                }
            }
        } else {
            System.out.println("Введен некорректный номер.");
        }
        return null;
    }
}
