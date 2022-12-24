package com.frame;

import com.domain.Admin;

import javax.swing.*;
import java.sql.Connection;

/**
 * Author: ziluxike
 * Time: 2022/12/25 20:49
 */
public class LibrarianFrame {
    Admin admin;

    Connection connection;

    JFrame loginFrame;

    public LibrarianFrame() {
    }

    public LibrarianFrame(Admin admin, Connection connection, JFrame loginFrame) {
        this.admin = admin;
        this.connection = connection;
        this.loginFrame = loginFrame;
    }

    JFrame frame = new JFrame("管理员界面");

    private JButton button1 = new JButton("退出");
    private JButton button2 = new JButton("读者注册");
    private JButton button3 = new JButton("书籍入库");
    private JButton button4 = new JButton("读者库管理");
    private JButton button5 = new JButton("书籍管理");
    private JButton button6 = new JButton("借阅管理");


    public void init() {
        frame.setSize(500,700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button1.setBounds(380,50,100,50);

        button1.addActionListener(e -> {
            admin = null;
            frame.setVisible(false);
            loginFrame.setVisible(true);
        });

        frame.add(button1);

        button2.setBounds(80,150,100,50);
        frame.add(button2);

        button3.setBounds(80,230,100,50);
        frame.add(button3);

        button4.setBounds(80,310,100,50);
        frame.add(button4);

        button5.setBounds(80,390,100,50);
        frame.add(button5);

        button6.setBounds(80,470,100,50);
        frame.add(button6);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LibrarianFrame().init();
    }

}
