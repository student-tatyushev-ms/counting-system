package com.counting_system;

import com.counting_system.model.Contractor;
import com.counting_system.model.Goods;
import com.counting_system.model.GoodsReceipt;
import com.counting_system.model.Nomenclature;

import java.util.List;

public class Utils {

    public static GoodsReceipt getGoodsReceipt(int goodsReceiptId, List<GoodsReceipt> goodsReceiptList) {
        for (int i = 0; i < goodsReceiptList.size(); i++) {
            if (goodsReceiptList.get(i).getId() == goodsReceiptId) {
                return goodsReceiptList.get(i);
            }
        }
        return null;
    }

    public static Contractor getContractor(int contractorId, List<Contractor> contractorList) {
        for (int i = 0; i < contractorList.size(); i++) {
            if (contractorList.get(i).getId() == contractorId) {
                return contractorList.get(i);
            }
        }
        return null;
    }

    public static Goods getGoods(int goodsId, List<Goods> goodsList) {
        for (int i = 0; i < goodsList.size(); i++) {
            if (goodsList.get(i).getId() == goodsId) {
                return goodsList.get(i);
            }
        }
        return null;
    }

    public static Nomenclature getNomenclature(int nomenclatureId, List<Nomenclature> nomenclatureList) {
        for (int i = 0; i < nomenclatureList.size(); i++) {
            if (nomenclatureList.get(i).getId() == nomenclatureId) {
                return nomenclatureList.get(i);
            }
        }
        return null;
    }

}
