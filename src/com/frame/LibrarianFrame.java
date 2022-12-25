package com.frame;

import com.dao.ReaderDao;
import com.domain.Admin;
import com.domain.Reader;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: ziluxike
 * Time: 2022/12/25 20:49
 */
public class LibrarianFrame {
    Admin admin;

    Connection connection;

    JFrame loginFrame;
    private JLabel label1;

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

    TextField textField1 = new TextField();
    TextField textField2 = new TextField();
    TextField textField3 = new TextField();
    TextField textField4 = new TextField();
    TextField textField5 = new TextField();

    private static JLabel jLabel1;

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

        JLabel label1 = new JLabel();
        label1.setBounds(200,150,280,400);
//        label1.setOpaque(true);
//        label1.setBackground(Color.white);

        JLabel label11 = new JLabel("借阅证号");
        JLabel label12 = new JLabel("姓名");
        JLabel label13 = new JLabel("职位");
        JLabel label14 = new JLabel("性别");
        JLabel label15 = new JLabel("登录密码");

        label11.setBounds(40,20,80,20);
        label12.setBounds(40,60,80,20);
        label13.setBounds(40,100,80,20);
        label14.setBounds(40,140,80,20);
        label15.setBounds(40,180,80,20);

        label1.add(label11);
        label1.add(label12);
        label1.add(label13);
        label1.add(label14);
        label1.add(label15);



        textField1.setBounds(120,20,120,20);
        textField2.setBounds(120,60,120,20);
        textField3.setBounds(120,100,120,20);
        textField4.setBounds(120,140,120,20);
        textField5.setBounds(120,180,120,20);
        label1.add(textField1);
        label1.add(textField2);
        label1.add(textField3);
        label1.add(textField4);
        label1.add(textField5);

        label1.setVisible(false);

        frame.add(label1);

        JButton button7 = new JButton("提交");
        button7.setBounds(100,250,100,50);
        label1.add(button7);

        button2.addActionListener(e -> {
            label1.setVisible(true);
        });

        button7.addActionListener(e -> {
            int i = 0;
            Reader reader = new Reader(null, textField1.getText(), textField2.getText(),textField3.getText(),textField4.getText(),textField5.getText());
            try {
                i = ReaderDao.insertReader(connection,reader);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (i > 0) {
                JOptionPane.showMessageDialog(null,"添加成功!");
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "消息", JOptionPane. ERROR_MESSAGE);
            }
            label1.setVisible(false);
            cleanText();
        });

        frame.setVisible(true);
    }


    public void cleanText() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
    }

    public static void main(String[] args) {
        new LibrarianFrame().init();
    }

}
