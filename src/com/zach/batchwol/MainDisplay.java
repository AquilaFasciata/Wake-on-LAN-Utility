package com.zach.batchwol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDisplay implements ActionListener {

    private static MacDisplay macDisplay;

    public MainDisplay() {
        // TODO Add Output Console
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        JButton macAddresses = new JButton("MAC(s) to Send");
        macAddresses.setBounds(10, 20, 150, 25);
        panel.add(macAddresses);
        macAddresses.setActionCommand("macs");
        macAddresses.addActionListener(this);


        JButton sendData = new JButton("Wake");
        sendData.setBounds(400, 20, 75, 25);
        panel.add(sendData);
        sendData.setActionCommand("wake");
        sendData.addActionListener(this);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
