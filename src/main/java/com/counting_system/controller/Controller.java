package com.counting_system.controller;

import com.counting_system.Utils;
import com.counting_system.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private Connection connection;

    public Controller(Connection connection) {
        this.connection = connection;
    }

    public void goodsInsert(int number, int price, int cost, GoodsReceipt goodsReceipt, Nomenclature nomenclature) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO GOODS (NUMBER, PRICE, COST, ID_GOODS_RECEIPT, ID_NOMENCLATURE) VALUES (?, ?, ?, ?,?)"
        );
        preparedStatement.setInt(1, number);
        preparedStatement.setInt(2, price);
        preparedStatement.setInt(3, cost);
        preparedStatement.setInt(4, goodsReceipt.getId());
        preparedStatement.setInt(5, nomenclature.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void goodsUpdate(int id, int number, int price, int cost, GoodsReceipt goodsReceipt, Nomenclature nomenclature) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE GOODS SET NUMBER = ?, PRICE = ?, COST = ?, ID_GOODS_RECEIPT = ?, ID_NOMENCLATURE = ? WHERE id = ?"
        );
        preparedStatement.setInt(1, number);
        preparedStatement.setInt(2, price);
        preparedStatement.setInt(3, cost);
        preparedStatement.setInt(4, goodsReceipt.getId());
        preparedStatement.setInt(5, nomenclature.getId());
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
    }

    public void goodsDelete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM GOODS WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Goods> goodsSelect() throws SQLException {
        List<Nomenclature> nomenclatures = nomenclatureSelect();
        List<GoodsReceipt> goodsReceipts = goodsReceiptSelect();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM GOODS"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Goods> arraySelect = new ArrayList<>();
        while (resultSet.next()) {
            Nomenclature nomenclature = Utils.getNomenclature(resultSet.getInt("ID_NOMENCLATURE"), nomenclatures);
            GoodsReceipt goodsReceipt = Utils.getGoodsReceipt(resultSet.getInt("ID_GOODS_RECEIPT"), goodsReceipts);
            arraySelect.add(new Goods(
                            resultSet.getInt("ID"),
                            resultSet.getInt("NUMBER"),
                            resultSet.getInt("PRICE"),
                            resultSet.getInt("COST"),
                            nomenclature,
                            goodsReceipt
                    )
            );
        }
        resultSet.close();
        preparedStatement.close();
        return arraySelect;
    }

    public List<Goods> selectGoodsByReceiptId(int receiptId) throws SQLException {
        List<Nomenclature> nomenclatures = nomenclatureSelect();
        List<GoodsReceipt> goodsReceipts = goodsReceiptSelect();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM GOODS WHERE ID_GOODS_RECEIPT = ?"
        );
        preparedStatement.setInt(1, receiptId);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Goods> arraySelect = new ArrayList<>();
        while (resultSet.next()) {
            Nomenclature nomenclature = Utils.getNomenclature(resultSet.getInt("ID_NOMENCLATURE"), nomenclatures);
            GoodsReceipt goodsReceipt = Utils.getGoodsReceipt(resultSet.getInt("ID_GOODS_RECEIPT"), goodsReceipts);
            arraySelect.add(new Goods(
                            resultSet.getInt("ID"),
                            resultSet.getInt("NUMBER"),
                            resultSet.getInt("PRICE"),
                            resultSet.getInt("COST"),
                            nomenclature,
                            goodsReceipt
                    )
            );
        }
        resultSet.close();
        preparedStatement.close();
        return arraySelect;
    }

    //****************************************************************************************************************//

    public void goodsReceiptInsert(Date date, Contractor contractor) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO GOODS_RECEIPT (DATE, ID_CONTRACTOR, STATUS) VALUES (?, ?, 0)"
        );
        preparedStatement.setDate(1, date);
        preparedStatement.setInt(2, contractor.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void prosecution(GoodsReceipt goodsReceipt) throws SQLException {
        goodsReceiptUpdate(goodsReceipt.getId(), goodsReceipt.getDate(), goodsReceipt.getContractor(), 1);
    }

    public void cancelProsecution(GoodsReceipt goodsReceipt) throws SQLException {
        goodsReceiptUpdate(goodsReceipt.getId(), goodsReceipt.getDate(), goodsReceipt.getContractor(), 0);
    }

    public void goodsReceiptUpdate(int id, Date date, Contractor contractor, int status) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE GOODS_RECEIPT SET DATE = ?, ID_CONTRACTOR = ?, STATUS = ? WHERE ID = ?"
        );
        preparedStatement.setDate(1, date);
        preparedStatement.setInt(2, contractor.getId());
        preparedStatement.setInt(3, status);
        preparedStatement.setInt(4, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void goodsReceiptDelete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM GOODS_RECEIPT WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<GoodsReceipt> goodsReceiptSelect() throws SQLException {
        List<Contractor> contractorList = contractorSelect();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM GOODS_RECEIPT"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<GoodsReceipt> arraySelect = new ArrayList<>();
        while (resultSet.next()) {
            for (Contractor item : contractorList) {
                if (item.getId() == resultSet.getInt("ID_CONTRACTOR")) {
                    arraySelect.add(new GoodsReceipt(
                            resultSet.getInt("ID"),
                            resultSet.getDate("DATE"),
                            item,
                            resultSet.getBoolean("STATUS")
                    ));
                }
            }
        }
        resultSet.close();
        preparedStatement.close();
        return arraySelect;
    }

    //****************************************************************************************************************//

    public void goodsRemnantsInsert(Date date, int number, int sum, Contractor contractor, GoodsReceipt goodsReceipt) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO GOODS_REMNANTS (DATE, NUMBER, SUM, ID_CONTRACTOR, ID_GOODS_RECEIPT) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setDate(1, date);
        preparedStatement.setInt(2, number);
        preparedStatement.setInt(3, sum);
        preparedStatement.setInt(4, contractor.getId());
        preparedStatement.setInt(5, goodsReceipt.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void goodsRemnantsUpdate(int id, Date date, int number, int sum, Contractor contractor, GoodsReceipt goodsReceipt) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE GOODS_REMNANTS SET DATE = ?, NUMBER = ?, SUM = ?, ID_CONTRACTOR = ?, ID_GOODS_RECEIPT = ? WHERE ID = ?"
        );
        preparedStatement.setDate(1, date);
        preparedStatement.setInt(2, number);
        preparedStatement.setInt(3, sum);
        preparedStatement.setInt(4, contractor.getId());
        preparedStatement.setInt(5, goodsReceipt.getId());
        preparedStatement.setInt(6, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void goodsRemnantsDelete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM GOODS_REMNANTS WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<GoodsRemnants> goodsRemnantsSelect() throws SQLException {
        List<Contractor> contractorList = contractorSelect();
        List<GoodsReceipt> goodsReceiptList = goodsReceiptSelect();

        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM GOODS_REMNANTS"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<GoodsRemnants> result = new ArrayList<>();
        while (resultSet.next()) {
            Contractor contractor = Utils.getContractor(resultSet.getInt("ID_CONTRACTOR"), contractorList);
            GoodsReceipt goodsReceipt = Utils.getGoodsReceipt(resultSet.getInt("ID_GOODS_RECEIPT"), goodsReceiptList);
            result.add(new GoodsRemnants(
                            resultSet.getInt("ID"),
                            resultSet.getDate("DATE"),
                            resultSet.getInt("NUMBER"),
                            resultSet.getInt("SUM"),
                            contractor,
                            goodsReceipt
                    )
            );
        }

        resultSet.close();
        preparedStatement.close();
        return result;
    }

    //****************************************************************************************************************//

    public void nomenclatureInsert(String value) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO NOMENCLATURE (TITLE) VALUES (?)"
        );
        preparedStatement.setString(1, value);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void nomenclatureUpdate(int id, String value) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE NOMENCLATURE SET TITLE = ? WHERE ID = ?"
        );
        preparedStatement.setString(1, value);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void nomenclatureDelete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM NOMENCLATURE WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Nomenclature> nomenclatureSelect() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM NOMENCLATURE"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Nomenclature> arraySelect = new ArrayList<>();
        while (resultSet.next()) {
            arraySelect.add(new Nomenclature(resultSet.getInt("ID"), resultSet.getString("TITLE")));
        }
        resultSet.close();
        preparedStatement.close();
        return arraySelect;
    }

    //****************************************************************************************************************//

    public void contractorInsert(String value) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO CONTRACTOR (NAME) VALUES (?)"
        );
        preparedStatement.setString(1, value);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void contractorUpdate(int id, String value) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE CONTRACTOR SET NAME = ? WHERE ID = ?"
        );
        preparedStatement.setString(1, value);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void contractorDelete(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM CONTRACTOR WHERE ID = ?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<Contractor> contractorSelect() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM CONTRACTOR"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Contractor> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new Contractor(resultSet.getInt("ID"), resultSet.getString("NAME")));
        }
        resultSet.close();
        preparedStatement.close();
        return result;
    }

}
