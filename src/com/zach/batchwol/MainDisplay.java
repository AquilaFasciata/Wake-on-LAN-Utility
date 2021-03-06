package com.zach.batchwol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainDisplay implements ActionListener {

    private static MacDisplay macDisplay = new MacDisplay(false);
    private static ArrayList<String> macList;
    private static JTextArea output;

    public static JTextArea getOutput() {
        return output;
    }

    public MainDisplay() {
        // TODO Add Output Console
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        Rectangle outputBounds = new Rectangle(10, 50, 465, 100);

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

        output = new JTextArea();
        output.setBounds(outputBounds);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setEditable(false);
        panel.add(output);

        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBounds(outputBounds);
        panel.add(scrollPane);

        // Used to redirect stdout to output text area
        MessageConsole mc = new MessageConsole(output);
        mc.redirectOut();
        mc.redirectErr(Color.RED, null);
        mc.setMessageLines(1000);

        macDisplay.sortAddresses();

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "macs":
                macDisplay.setVisibility(true);
                break;
            case "wake":
                macList = macDisplay.getAddresses();
                for (String address: macList)
                    PacketSender.PacketSender(address);
                System.out.println("Magic Packets have been sent.");
                break;
        }
    }
}
