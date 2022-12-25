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

    public static int insertReader(Connection connection, Reader reader) throws SQLException {
        String insert = "insert into reader values (null, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setString(1, reader.getIdReader());
        preparedStatement.setString(2, reader.getNameReader());
        preparedStatement.setString(3, reader.getKind());
        preparedStatement.setString(4, reader.getSex());
        preparedStatement.setString(5, reader.getPassword());
        return preparedStatement.executeUpdate();
    }
}
