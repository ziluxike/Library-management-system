package com.frame;

import com.dao.AdminDao;
import com.dao.ReaderDao;
import com.domain.Admin;
import com.domain.Reader;
import com.tools.DBTools;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginFrame {
    private Connection connection = DBTools.getConnection();

    JFrame frame = new JFrame("用户登录");


    private JLabel label1 = new JLabel("用户:");
    private JLabel label2 = new JLabel("密码:");

    private TextField textField1 = new TextField();
    private TextField textField2 = new TextField();

    private JButton button1 = new JButton("显示密码");



    private Checkbox checkbox1 = new Checkbox("读者");
    private Checkbox checkbox2 = new Checkbox("管理员");

    private CheckboxGroup checkboxGroup = new CheckboxGroup();

    private JButton button2 = new JButton("登录");

    public void init() {
        frame.setSize(500,700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
            // 读者
            if (checkbox1.getState() == true) {
                Reader reader;
                try {
                    reader = ReaderDao.SelectByIdReaderAndPass(connection, textField1.getText(), textField2.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                if (reader != null) {
                    ReaderFrame readerFrame = new ReaderFrame(reader, connection, frame);
                    readerFrame.frame.setLocation(frame.getX(),frame.getY());
                    frame.setVisible(false);
                    cleanTextFieldText();
                    readerFrame.init();
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败", "用户登录", JOptionPane. ERROR_MESSAGE);
                    cleanTextFieldText();
                }

            } else {
                Admin admin;
                try {
                    admin = AdminDao.SelectByNameAndPass(connection, textField1.getText(), textField2.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (admin != null) {
                    LibrarianFrame librarianFrame = new LibrarianFrame(admin, connection, frame);
                    librarianFrame.frame.setLocation(frame.getX(),frame.getY());
                    frame.setVisible(false);
                    cleanTextFieldText();
                    librarianFrame.init();
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败", "管理员登录", JOptionPane. ERROR_MESSAGE);
                    cleanTextFieldText();
                }
            }
        });


        frame.setVisible(true);
    }

    public void cleanTextFieldText() {
        textField1.setText("");
        textField2.setText("");
    }

    public static void main(String[] args) {
        new LoginFrame().init();
    }
}
