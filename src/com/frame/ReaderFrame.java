package com.frame;

import com.dao.BorrowDao;
import com.domain.Borrow;
import com.domain.Reader;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: ziluxike
 * Time: 2022/12/24 20:11
 */
public class ReaderFrame {
    Reader reader = new Reader();
    JFrame readerMainFrame = new JFrame("用户窗口");

    private Connection connection;
    JFrame loginFrame;

    private JButton button1 = new JButton("借书");
    private JButton button2 = new JButton("还书");
    private JButton button3 = new JButton("退出");


    TextField idBookTextField = new TextField();
    TextField statusTextField = new TextField("未知,请查询");
    TextField nameReaderTextField = new TextField();
    TextField idDueBookTextField = new TextField();
    TextField statusDueTextField = new TextField("未知,请查询");
    TextField nameDueReaderTextField = new TextField();

    List<JLabel> labels = new ArrayList<>();

    public ReaderFrame() {
    }


    public ReaderFrame(Reader reader, Connection connection, JFrame loginFrame) {
        this.reader = reader;
        this.connection = connection;
        this.loginFrame = loginFrame;
    }

    public void init() {
        readerMainFrame.setSize(500, 700);
        readerMainFrame.setLayout(null);
        readerMainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button3.setBounds(380,50,100,50);

        button1.setBounds(70,150,100,50);
        button2.setBounds(70,300,100,50);

        readerMainFrame.add(button1);
        readerMainFrame.add(button2);
        readerMainFrame.add(button3);

        JLabel lendBookLabel = new JLabel();
        labels.add(lendBookLabel);
        lendBookLabel.setVisible(false);
        lendBookLabel.setLayout(null);
        lendBookLabel.setBounds(200,150,280,400);

        JLabel idBookLabel = new JLabel("书号");
        JLabel nameBookLabel = new JLabel("状态");
        JLabel nameReaderLabel = new JLabel("用户");

        idBookLabel.setBounds(40,20,80,20);
        nameBookLabel.setBounds(40,60,80,20);
        nameReaderLabel.setBounds(40,100,80,20);

        idBookTextField.setBounds(120,20,120,20);
        statusTextField.setBounds(120,60,120,20);
        statusTextField.setEnabled(false);
        nameReaderTextField.setBounds(120,100,120,20);
        nameReaderTextField.setEnabled(false);

        lendBookLabel.add(idBookLabel);
        lendBookLabel.add(nameBookLabel);
        lendBookLabel.add(nameReaderLabel);

        lendBookLabel.add(idBookTextField);
        lendBookLabel.add(statusTextField);
        lendBookLabel.add(nameReaderTextField);

        JButton selectBookButton = new JButton("查询");
        selectBookButton.setBounds(60,150,80,50);
        lendBookLabel.add(selectBookButton);

        JButton updateBorrowButton = new JButton("借书");
        updateBorrowButton.setBounds(160,150,80,50);
        updateBorrowButton.setEnabled(false);
        lendBookLabel.add(updateBorrowButton);

        readerMainFrame.add(lendBookLabel);
        button1.addActionListener(e -> {
            for (JLabel label : labels) {
                label.setVisible(false);
            }
            nameReaderTextField.setText(reader.getNameReader());
            selectBookButton.setEnabled(true);
            updateBorrowButton.setEnabled(false);
            lendBookLabel.setVisible(true);
        });

        selectBookButton.addActionListener(e -> {
            Borrow borrow;
            try {
                borrow = BorrowDao.SelectByBookId(connection, idBookTextField.getText());

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (borrow == null) {
                statusTextField.setText("未借出");
                selectBookButton.setEnabled(false);
                updateBorrowButton.setEnabled(true);
            }

            assert borrow != null;
            if (
                    Objects.equals(borrow.getDueDate(), null)
                    || ((Objects.equals(borrow.getDueDate(), null)) && (Objects.equals(borrow.getLendDate(), null)))
            ) {
                statusTextField.setText("已借出");
            } else {
                statusTextField.setText("未借出");
                selectBookButton.setEnabled(false);
                updateBorrowButton.setEnabled(true);
            }
        });

        updateBorrowButton.addActionListener(e -> {
            int i;
            try {
                i = BorrowDao.insertBorrow(connection, reader.getIdReader(), idBookTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (i > 0) {
                JOptionPane.showMessageDialog(null,"借书成功!");
            } else {
                JOptionPane.showMessageDialog(null, "借书失败!", "消息", JOptionPane. ERROR_MESSAGE);
            }
            idBookTextField.setText("");
            statusTextField.setText("未知,请查询");
            selectBookButton.setEnabled(true);
            updateBorrowButton.setEnabled(false);
        });


        JLabel dueBookLabel = new JLabel();
        labels.add(dueBookLabel);
        dueBookLabel.setVisible(false);
        dueBookLabel.setLayout(null);
        dueBookLabel.setBounds(200,150,280,400);

        JLabel idDueBookLabel = new JLabel("书号");
        JLabel statusDueBookLabel = new JLabel("状态");
        JLabel nameDueReaderLabel = new JLabel("用户");

        idDueBookLabel.setBounds(40,20,80,20);
        statusDueBookLabel.setBounds(40,60,80,20);
        nameDueReaderLabel.setBounds(40,100,80,20);

        idDueBookTextField.setBounds(120,20,120,20);
        statusDueTextField.setBounds(120,60,120,20);
        statusDueTextField.setEnabled(false);
        nameDueReaderTextField.setBounds(120,100,120,20);
        nameDueReaderTextField.setEnabled(false);

        dueBookLabel.add(idDueBookLabel);
        dueBookLabel.add(statusDueBookLabel);
        dueBookLabel.add(nameDueReaderLabel);

        dueBookLabel.add(idDueBookTextField);
        dueBookLabel.add(statusDueTextField);
        dueBookLabel.add(nameDueReaderTextField);

        JButton selectDueBookButton = new JButton("查询");
        selectDueBookButton.setBounds(60,150,80,50);
        dueBookLabel.add(selectDueBookButton);

        JButton updateDueBorrowButton = new JButton("还书");
        updateDueBorrowButton.setBounds(160,150,80,50);
        updateDueBorrowButton.setEnabled(false);
        dueBookLabel.add(updateDueBorrowButton);

        readerMainFrame.add(dueBookLabel);

        selectDueBookButton.addActionListener(e -> {
            Borrow borrow;
            try {
                borrow = BorrowDao.SelectByReaderAndBookId(connection, reader.getIdReader(),idDueBookTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (
                    Objects.equals(borrow.getDueDate(), null)
                            || ((Objects.equals(borrow.getDueDate(), null)) && (Objects.equals(borrow.getLendDate(), null)))
            ){
                statusDueTextField.setText("已借出");
                updateDueBorrowButton.setEnabled(true);
                selectDueBookButton.setEnabled(false);
            } else {
                statusDueTextField.setText("在馆");
            }
        });

        updateDueBorrowButton.addActionListener(e -> {
            int i = 0;
            try {
                i = BorrowDao.updateBorrow(connection, idDueBookTextField.getText(), reader.getIdReader());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (i > 0) {
                JOptionPane.showMessageDialog(null,"还书成功!");
            } else {
                JOptionPane.showMessageDialog(null, "还书失败!", "消息", JOptionPane. ERROR_MESSAGE);
            }

            idDueBookTextField.setText("");
            statusDueTextField.setText("未知,请查询");
            selectDueBookButton.setEnabled(true);
            updateDueBorrowButton.setEnabled(false);
        });

        button2.addActionListener(e -> {
            for (JLabel label : labels) {
                label.setVisible(false);
            }
            nameDueReaderTextField.setText(reader.getNameReader());
            selectBookButton.setEnabled(true);
            updateBorrowButton.setEnabled(false);
            dueBookLabel.setVisible(true);
        });

        button3.addActionListener(e -> {
            reader = null;
            readerMainFrame.setVisible(false);
            loginFrame.setVisible(true);
        });
        nameReaderTextField.setText(reader.getNameReader());
        selectBookButton.setEnabled(false);
        updateBorrowButton.setEnabled(false);
        readerMainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        ReaderFrame readerFrame = new ReaderFrame();
        readerFrame.init();
    }
}
