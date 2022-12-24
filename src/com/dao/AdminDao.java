package com.dao;

import com.domain.Admin;
import com.tools.DBTools;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {

    public static Admin SelectByNameAndPass(Connection connection, String username, String password) throws SQLException {
        Admin admin = null;
        String select = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(select);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            admin = new Admin();
            admin.setUsername(resultSet.getString("username"));
            admin.setPassword(resultSet.getString("password"));
        }
        return admin;
    }


}
