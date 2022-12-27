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

    JFrame LoginMainFrame = new JFrame("用户登录");


    private JLabel usernameLabel = new JLabel("用户:");
    private JLabel passwordLabel = new JLabel("密码:");

    private TextField usernameTextField = new TextField();
    private TextField passwordTextField = new TextField();

    private JButton showPasswordButton = new JButton("显示密码");



    private Checkbox checkbox1 = new Checkbox("读者");
    private Checkbox checkbox2 = new Checkbox("管理员");

    private CheckboxGroup checkboxGroup = new CheckboxGroup();

    private JButton LoginButton = new JButton("登录");

    public void init() {
        LoginMainFrame.setSize(500,700);
        LoginMainFrame.setLayout(null);
        LoginMainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        usernameLabel.setBounds(140,200,40,20);
        LoginMainFrame.add(usernameLabel);
        passwordLabel.setBounds(140,240,40,20);
        LoginMainFrame.add(passwordLabel);

        LoginMainFrame.add(usernameTextField);
        usernameTextField.setBounds(180,200,100,20);
        LoginMainFrame.add(passwordTextField);
        passwordTextField.setBounds(180,240,100,20);
        passwordTextField.setEchoChar('*');

        showPasswordButton.setBounds(300,240,100,20);
        LoginMainFrame.add(showPasswordButton);

        showPasswordButton.addActionListener(e -> {
            if (passwordTextField.getEchoChar() == '*') {
                passwordTextField.setEchoChar((char)0);
            } else {
                passwordTextField.setEchoChar('*');
            }
        });



        checkbox1.setCheckboxGroup(checkboxGroup);
        checkbox2.setCheckboxGroup(checkboxGroup);

        checkbox1.setState(true);

        checkbox1.setBounds(170,280,58,20);
        LoginMainFrame.add(checkbox1);
        checkbox2.setBounds(235,280,100,20);
        LoginMainFrame.add(checkbox2);



        LoginButton.setBounds(150,320,150,35);

        LoginMainFrame.add(LoginButton);

        LoginButton.addActionListener(e -> {
            // 读者
            if (checkbox1.getState() == true) {
                Reader reader;
                try {
                    reader = ReaderDao.SelectByIdReaderAndPass(connection, usernameTextField.getText(), passwordTextField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


                if (reader != null) {
                    ReaderFrame readerFrame = new ReaderFrame(reader, connection, LoginMainFrame);
                    readerFrame.readerMainFrame.setLocation(LoginMainFrame.getX(), LoginMainFrame.getY());
                    LoginMainFrame.setVisible(false);
                    cleanTextFieldText();
                    readerFrame.init();
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败", "用户登录", JOptionPane. ERROR_MESSAGE);
                    cleanTextFieldText();
                }

            } else {
                Admin admin;
                try {
                    admin = AdminDao.SelectByNameAndPass(connection, usernameTextField.getText(), passwordTextField.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (admin != null) {
                    LibrarianFrame librarianFrame = new LibrarianFrame(admin, connection, LoginMainFrame);
                    librarianFrame.readerMainFrame.setLocation(LoginMainFrame.getX(), LoginMainFrame.getY());
                    LoginMainFrame.setVisible(false);
                    cleanTextFieldText();
                    librarianFrame.init();
                } else {
                    JOptionPane.showMessageDialog(null, "登录失败", "管理员登录", JOptionPane. ERROR_MESSAGE);
                    cleanTextFieldText();

                }
            }
        });


        LoginMainFrame.setVisible(true);
    }

    public void cleanTextFieldText() {
        usernameTextField.setText("");
        passwordTextField.setText("");
    }
}
