package com.dao;

import com.domain.Book;
import com.domain.Borrow;
import com.domain.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: ziluxike
 * Time: 2022/12/26 15:21
 */
public class BorrowDao {
    public static int insertBorrow(Connection connection, String idReader, String idBook) throws SQLException {
        String insert = "insert into borrow values (null, ?, ?, ?, ?, NOW())";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, idReader);
        preparedStatement.setString(2, idBook);
        preparedStatement.setString(3, null);
        preparedStatement.setString(4, null);
        return preparedStatement.executeUpdate();
    }


    public static Borrow SelectByBookId(Connection connection, String idBook) throws SQLException {
        Borrow borrow = null;
        String select = "SELECT * FROM borrow WHERE idBook = ? order by id desc limit 1";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, idBook);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            borrow = new Borrow();
            borrow.setIdReader(resultSet.getString("idReader"));
            borrow.setIdBook(resultSet.getString("idBook"));
            borrow.setDueDate(resultSet.getString("dueDate"));
            borrow.setLendDate(resultSet.getString("lendDate"));
            borrow.setOvertime(resultSet.getString("overtime"));
        }
        return borrow;
    }

    public static Borrow SelectByReaderAndBookId(Connection connection, String idReader, String idBook) throws SQLException {
        Borrow borrow = null;
        String select = "SELECT * FROM borrow WHERE idReader = ? and idBook = ? order by id desc limit 1";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, idReader);
        preparedStatement.setString(2, idBook);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            borrow = new Borrow();
            borrow.setIdReader(resultSet.getString("idReader"));
            borrow.setIdBook(resultSet.getString("idBook"));
            borrow.setDueDate(resultSet.getString("dueDate"));
            borrow.setLendDate(resultSet.getString("lendDate"));
            borrow.setOvertime(resultSet.getString("overtime"));
        }
        return borrow;
    }


    public static int updateBorrow(Connection connection, String idBook, String idReader) throws SQLException {
        String update = "update borrow set dueDate = NOW() where idBook = ? and idReader = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, idBook);
        preparedStatement.setString(2, idReader);
        return preparedStatement.executeUpdate();
    }



}
