package com.dao;

import com.domain.Admin;
import com.domain.Book;
import com.domain.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: ziluxike
 * Time: 2022/12/26 11:10
 */
public class BookDao {
    public static int insertBook(Connection connection, Book book) throws SQLException {
        String insert = "insert into book values (null, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, book.getIdBook());
        preparedStatement.setString(2, book.getNameBook());
        preparedStatement.setFloat(3, book.getPrice());
        preparedStatement.setString(4, book.getAuthor());
        preparedStatement.setString(5, book.getPublisher());
        preparedStatement.setString(6,book.getKind());
        return preparedStatement.executeUpdate();
    }

    public static int delete(Connection connection, String idBook) throws SQLException {
        int i = 0;
        String delete = "delete from book where idBook = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1, idBook);
        i = preparedStatement.executeUpdate();
        return i;
    }

    public static int update(Connection connection, Book book) throws SQLException {
        String update = "update book set nameBook = ?, price = ?, author = ?, publisher = ?, kind = ? where idBook = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, book.getNameBook());
        preparedStatement.setFloat(2, book.getPrice());
        preparedStatement.setString(3, book.getAuthor());
        preparedStatement.setString(4, book.getPublisher());
        preparedStatement.setString(5,book.getKind());
        preparedStatement.setString(6,book.getIdBook());
        return preparedStatement.executeUpdate();
    }

    public static String SelectByIdBook(Connection connection, String idBook) throws SQLException {
        String nameBook = "";
        String select = "SELECT * FROM book WHERE idBook = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, idBook);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
             nameBook = (resultSet.getString("nameBook"));
        }
        return nameBook;
    }
}
