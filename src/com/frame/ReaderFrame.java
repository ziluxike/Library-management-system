package com.frame;

import com.domain.Reader;

import javax.swing.*;
import java.sql.Connection;

/**
 * Author: ziluxike
 * Time: 2022/12/24 20:11
 */
public class ReaderFrame {
    Reader reader = new Reader();
    JFrame frame = new JFrame("用户窗口");

    private Connection connection;
    JFrame loginFrame;

    private JButton button1 = new JButton("借书");
    private JButton button2 = new JButton("还书");
    private JButton button3 = new JButton("退出");

    public ReaderFrame() {
    }


    public ReaderFrame(Reader reader, Connection connection, JFrame loginFrame) {
        this.reader = reader;
        this.connection = connection;
        this.loginFrame = loginFrame;
    }

    public void init() {
        frame.setSize(500, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button3.setBounds(380,50,100,50);

        button1.setBounds(100,150,100,50);
        button2.setBounds(100,300,100,50);

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);


        button1.addActionListener(e -> {
            // todo 借书

        });

        button2.addActionListener(e -> {
            // todo 还书
        });

        button3.addActionListener(e -> {
            reader = null;
            frame.setVisible(false);
            loginFrame.setVisible(true);
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ReaderFrame readerFrame = new ReaderFrame();
        readerFrame.init();
    }
}
