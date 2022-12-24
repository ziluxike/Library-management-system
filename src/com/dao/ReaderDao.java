package com.dao;

import com.domain.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: ziluxike
 * Time: 2022/12/24 20:12
 */
public class ReaderDao {
    public static Reader SelectByIdReaderAndPass(Connection connection, String idReader, String password) throws SQLException {
        Reader reader = null;
        String select = "SELECT * FROM reader WHERE idReader = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, idReader);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            reader = new Reader();
            reader.setId(resultSet.getInt("id"));
            reader.setIdReader(resultSet.getString("idReader"));
            reader.setNameReader(resultSet.getString("nameReader"));
            reader.setKind(resultSet.getString("kind"));
            reader.setSex(resultSet.getString("sex"));
            reader.setPassword(resultSet.getString("password"));
        }
        return reader;
    }
}
