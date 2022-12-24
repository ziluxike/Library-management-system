package com.frame;

import com.dao.AdminDao;
import com.domain.Admin;
import com.tools.DBTools;
import sun.awt.WindowClosingListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginFrame {
    Connection connection = DBTools.getConnection();

    JFrame frame = new JFrame("用户登录");

    JLabel label1 = new JLabel("用户:");
    JLabel label2 = new JLabel("密码:");

    TextField textField1 = new TextField();
    TextField textField2 = new TextField();

    JButton button1 = new JButton("显示密码");



    Checkbox checkbox1 = new Checkbox("读者");
    Checkbox checkbox2 = new Checkbox("管理员");

    CheckboxGroup checkboxGroup = new CheckboxGroup();

    JButton button2 = new JButton("登录");

    public void init() {
        frame.setSize(500,700);
        frame.setLayout(null);

        label1.setBounds(140,200,40,20);
        frame.add(label1);
        label2.setBounds(140,240,40,20);
        frame.add(label2);

        frame.add(textField1);
        textField1.setBounds(180,200,100,20);
        frame.add(textField2);
        textField2.setBounds(180,240,100,20);
        textField2.setEchoChar('*');

        button1.setBounds(300,240,100,20);
        frame.add(button1);

        button1.addActionListener(e -> {
            if (textField2.getEchoChar() == '*') {
                textField2.setEchoChar((char)0);
            } else {
                textField2.setEchoChar('*');
            }
        });



        checkbox1.setCheckboxGroup(checkboxGroup);
        checkbox2.setCheckboxGroup(checkboxGroup);

        checkbox1.setState(true);

        checkbox1.setBounds(170,280,58,20);
        frame.add(checkbox1);
        checkbox2.setBounds(235,280,100,20);
        frame.add(checkbox2);



        button2.setBounds(150,320,150,35);

        frame.add(button2);

        button2.addActionListener(e -> {
            if (checkbox1.getState() == true) {

            } else {
                Admin admin;
                try {
                    admin = AdminDao.SelectByNameAndPass(connection, textField1.getText(), textField2.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (admin != null) {
                    TestFrame testFrame = new TestFrame(connection);
                    testFrame.frame.setLocation(frame.getX(),frame.getY());
                    frame.setVisible(false);
                    testFrame.init();
                } else {
                    //todo admin 查询不到
                }
            }
        });



        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame().init();
    }
}
