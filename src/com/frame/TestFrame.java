package com.frame;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class TestFrame {

    JFrame frame = new JFrame("测试窗口");

    Connection connection;

    public TestFrame(Connection connection) {
        this.connection = connection;
    }

    public void init() {
        frame.setSize(500, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }


}
