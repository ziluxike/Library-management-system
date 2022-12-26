package com.frame;

import com.dao.BookDao;
import com.dao.ReaderDao;
import com.domain.Admin;
import com.domain.Book;
import com.domain.Reader;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    JFrame readerMainFrame = new JFrame("管理员界面");

    private JButton exitButton = new JButton("退出");
    private JButton addReaderButton = new JButton("读者注册");
    private JButton addBookButton = new JButton("书籍入库");
    private JButton editReaderButton = new JButton("读者库管理");
    private JButton editBookButton = new JButton("书籍管理");
    private JButton button6 = new JButton("借阅管理");

    TextField idReaderTextField = new TextField();
    TextField nameReaderTextField = new TextField();
    TextField kindReaderTextField = new TextField();
    TextField sexTextField = new TextField();
    TextField passwordTextField = new TextField();
    TextField idBookTextField = new TextField();
    TextField nameBookTextField = new TextField();
    TextField priceTextField = new TextField();
    TextField authorTextField = new TextField();
    TextField publisherTextField = new TextField();
    TextField kindBookTextField = new TextField();



    TextField idReaderEditTextField = new TextField();
    TextField nameReaderEditTextField = new TextField();
    TextField kindReaderEditTextField = new TextField();
    TextField sexEditTextField = new TextField();
    TextField passwordEditTextField = new TextField();

    private List<JLabel> labelList = new ArrayList<>();

    public void init() {
        readerMainFrame.setSize(500,700);
        readerMainFrame.setLayout(null);
        readerMainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        exitButton.setBounds(380,50,100,50);

        exitButton.addActionListener(e -> {
            admin = null;
            readerMainFrame.setVisible(false);
            loginFrame.setVisible(true);
        });

        readerMainFrame.add(exitButton);

        addReaderButton.setBounds(80,150,100,50);
        readerMainFrame.add(addReaderButton);

        addBookButton.setBounds(80,230,100,50);
        readerMainFrame.add(addBookButton);

        editReaderButton.setBounds(80,310,100,50);
        readerMainFrame.add(editReaderButton);

        editBookButton.setBounds(80,390,100,50);
        readerMainFrame.add(editBookButton);

        button6.setBounds(80,470,100,50);
        readerMainFrame.add(button6);


        JLabel addReaderLabel = new JLabel();
        labelList.add(addReaderLabel);
        addReaderLabel.setBounds(200,150,280,400);

        initReaderEditMenu(addReaderLabel, idReaderTextField, nameReaderTextField, kindReaderTextField, sexTextField, passwordTextField);

        addReaderLabel.setVisible(false);

        readerMainFrame.add(addReaderLabel);

        JButton addReaderSubmitButton = new JButton("提交");
        addReaderSubmitButton.setBounds(100,250,100,50);
        addReaderLabel.add(addReaderSubmitButton);

        addReaderButton.addActionListener(e -> {
            for (JLabel label : labelList) {
                label.setVisible(false);
            }
            addReaderLabel.setVisible(true);
        });

        addReaderSubmitButton.addActionListener(e -> {
            int i = 0;
            Reader reader = new Reader(null, idReaderTextField.getText(), nameReaderTextField.getText(), kindReaderTextField.getText(), sexTextField.getText(), passwordTextField.getText());
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
            addReaderLabel.setVisible(false);
            cleanReaderTextFieldText();
        });

        JLabel addBookLabel = new JLabel();
        addBookLabel.setLayout(null);
        labelList.add(addBookLabel);
        addBookLabel.setVisible(false);
        addBookLabel.setBounds(200,150,280,400);


        JLabel idBookLabel = new JLabel("书号");
        JLabel nameBookLabel = new JLabel("书名");
        JLabel priceBookLabel = new JLabel("价格");
        JLabel authorBookLabel = new JLabel("作者");
        JLabel publisherBookLabel = new JLabel("出版社");
        JLabel kindBookLabel = new JLabel("类别");

        idBookLabel.setBounds(40,20,80,20);
        nameBookLabel.setBounds(40,60,80,20);
        priceBookLabel.setBounds(40,100,80,20);
        authorBookLabel.setBounds(40,140,80,20);
        publisherBookLabel.setBounds(40,180,80,20);
        kindBookLabel.setBounds(40,220,80,20);

        addBookLabel.add(idBookLabel);
        addBookLabel.add(nameBookLabel);
        addBookLabel.add(priceBookLabel);
        addBookLabel.add(authorBookLabel);
        addBookLabel.add(publisherBookLabel);
        addBookLabel.add(kindBookLabel);


        idBookTextField.setBounds(120,20,120,20);
        nameBookTextField.setBounds(120,60,120,20);
        priceTextField.setBounds(120,100,120,20);
        authorTextField.setBounds(120,140,120,20);
        publisherTextField.setBounds(120,180,120,20);
        kindBookTextField.setBounds(120,220,120,20);


        addBookLabel.add(idBookTextField);
        addBookLabel.add(nameBookTextField);
        addBookLabel.add(priceTextField);
        addBookLabel.add(authorTextField);
        addBookLabel.add(publisherTextField);
        addBookLabel.add(kindBookTextField);

        JButton addBookSubmitButton = new JButton("提交");
        addBookSubmitButton.setBounds(100,290,100,50);
        addBookLabel.add(addBookSubmitButton);

        readerMainFrame.add(addBookLabel);
        addBookButton.addActionListener(e -> {
            for (JLabel label : labelList) {
                label.setVisible(false);
            }
            addBookLabel.setVisible(true);
        });

        addBookSubmitButton.addActionListener(e -> {
            int i;
            Book book = new Book(null, idBookTextField.getText(), nameBookTextField.getText(), Float.parseFloat(priceTextField.getText()),
            authorTextField.getText(), publisherTextField.getText(), kindBookTextField.getText());

            try {
                i = BookDao.insertBook(connection, book);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (i > 0) {
                JOptionPane.showMessageDialog(null,"添加成功!");
            } else {
                JOptionPane.showMessageDialog(null, "添加失败", "消息", JOptionPane. ERROR_MESSAGE);
            }
            addBookLabel.setVisible(false);
            cleanBookTextFieldText();
        });


        JLabel editReaderLabel = new JLabel();
        editReaderLabel.setLayout(null);
        labelList.add(editReaderLabel);
        editReaderLabel.setVisible(false);
        editReaderLabel.setBounds(200,150,280,400);

        initReaderEditMenu(editReaderLabel, idReaderEditTextField, nameReaderEditTextField, kindReaderEditTextField, sexEditTextField, passwordEditTextField);

        JButton selectReaderButton = new JButton("查询");
        selectReaderButton.setBounds(60,250,80,50);
        editReaderLabel.add(selectReaderButton);

        JButton updateReaderButton = new JButton("提交");
        updateReaderButton.setBounds(160,250,80,50);
        updateReaderButton.setEnabled(false);
        editReaderLabel.add(updateReaderButton);

        editReaderButton.addActionListener(e -> {
            for (JLabel label : labelList) {
                label.setVisible(false);
            }
            updateReaderButton.setEnabled(false);

            nameReaderEditTextField.setEnabled(false);
            kindReaderEditTextField.setEnabled(false);
            sexEditTextField.setEnabled(false);
            passwordEditTextField.setEnabled(false);
            editReaderLabel.setVisible(true);
        });

        selectReaderButton.addActionListener(e -> {
            Reader reader;
            try {
                reader = ReaderDao.SelectByIdReader(connection, idReaderEditTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (reader != null) {
                idReaderEditTextField.setEnabled(false);
                nameReaderEditTextField.setText(reader.getNameReader());
                kindReaderEditTextField.setText(reader.getKind());
                sexEditTextField.setText(reader.getSex());
                passwordEditTextField.setText(reader.getPassword());

                updateReaderButton.setEnabled(true);
                selectReaderButton.setEnabled(false);

                nameReaderEditTextField.setEnabled(true);
                kindReaderEditTextField.setEnabled(true);
                sexEditTextField.setEnabled(true);
                passwordEditTextField.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "查询失败", "消息", JOptionPane. ERROR_MESSAGE);
            }
        });

        updateReaderButton.addActionListener(e -> {
            int i = 0;
            try {
                i = ReaderDao.UpdateReader(connection, nameReaderEditTextField.getText(), kindReaderEditTextField.getText(), sexEditTextField.getText(), passwordEditTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (i > 0) {
                JOptionPane.showMessageDialog(null,"更新成功!");
                cleanReaderEditTextFieldText();
            } else {
                JOptionPane.showMessageDialog(null, "更新失败", "消息", JOptionPane. ERROR_MESSAGE);
            }

        });

        readerMainFrame.add(editReaderLabel);

        readerMainFrame.setVisible(true);
    }

    public void initReaderEditMenu(JLabel editReaderLabel, TextField idReaderEditTextField, TextField nameReaderEditTextField, TextField kindReaderEditTextField, TextField sexEditTextField, TextField passwordEditTextField) {
        JLabel idEditReaderLabel = new JLabel("借阅证号");
        JLabel nameEditReaderLabel = new JLabel("姓名");
        JLabel kindEditReaderLabel = new JLabel("职位");
        JLabel sexEditReaderLabel = new JLabel("性别");
        JLabel passwordEditReaderLabel = new JLabel("登录密码");

        idEditReaderLabel.setBounds(40,20,80,20);
        nameEditReaderLabel.setBounds(40,60,80,20);
        kindEditReaderLabel.setBounds(40,100,80,20);
        sexEditReaderLabel.setBounds(40,140,80,20);
        passwordEditReaderLabel.setBounds(40,180,80,20);

        editReaderLabel.add(idEditReaderLabel);
        editReaderLabel.add(nameEditReaderLabel);
        editReaderLabel.add(kindEditReaderLabel);
        editReaderLabel.add(sexEditReaderLabel);
        editReaderLabel.add(passwordEditReaderLabel);

        idReaderEditTextField.setBounds(120,20,120,20);
        nameReaderEditTextField.setBounds(120,60,120,20);
        kindReaderEditTextField.setBounds(120,100,120,20);
        sexEditTextField.setBounds(120,140,120,20);
        passwordEditTextField.setBounds(120,180,120,20);

        editReaderLabel.add(idReaderEditTextField);
        editReaderLabel.add(nameReaderEditTextField);
        editReaderLabel.add(kindReaderEditTextField);
        editReaderLabel.add(sexEditTextField);
        editReaderLabel.add(passwordEditTextField);
    }


    public void cleanReaderTextFieldText() {
        idReaderTextField.setText("");
        nameReaderTextField.setText("");
        kindReaderTextField.setText("");
        sexTextField.setText("");
        passwordTextField.setText("");
    }

    public void cleanBookTextFieldText() {
        idBookTextField.setText("");
        nameBookTextField.setText("");
        priceTextField.setText("");
        authorTextField.setText("");
        publisherTextField.setText("");
        kindBookTextField.setText("");
    }

    public void cleanReaderEditTextFieldText() {
        idReaderEditTextField.setText("");
        nameReaderEditTextField.setText("");
        kindReaderEditTextField.setText("");
        sexEditTextField.setText("");
        passwordEditTextField.setText("");
    }
}
