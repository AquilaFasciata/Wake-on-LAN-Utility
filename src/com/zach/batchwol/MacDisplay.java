package com.zach.batchwol;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

public class MacDisplay implements ActionListener, Serializable {

    private JFrame macFrame;
    private JTextArea macs;
    private String tempList;
    private static ArrayList<String> addresses = new ArrayList<String>();

    /**
     * @param visible - Sets MAC Box visibility on creation
     */
    public MacDisplay(boolean visible) {
        macFrame = new JFrame("MAC Address Configuration");
        JPanel macPanel = new JPanel();

        macFrame.setSize(300, 200);
        macFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        macFrame.setLocationRelativeTo(null);
        macFrame.add(macPanel);

        macPanel.setLayout(null);

        macs = new JTextArea();
        loadField();
        macs.setBounds(10, 10, 265, 100);
        macPanel.add(macs);

        JButton addMacs = new JButton("Save Addresses");
        addMacs.setBounds(80, 115, 130, 25);
        macPanel.add(addMacs);
        addMacs.setActionCommand("save");
        addMacs.addActionListener(this);

        macFrame.setVisible(visible);
    }

    public boolean isVisible() {
        return macFrame.isVisible();
    }

    /**
     * @param visibility - Boolean representing visibility of window
     *                   true - Makes Window Visible
     *                   false - Hides window
     */
    public void setVisibility(boolean visibility) {
        loadField();
        macFrame.setVisible(visibility);
    }

    public void toggleVisibility() {
        if (isVisible() == false)
            loadField();
        setVisibility(!isVisible());
    }

    public MacDisplay() {
        this(true);
    }

    public ArrayList<String> getAddresses() {
        return addresses;
    }

    private void loadField() {
        try {
            tempList = (String) DataManager.load("mac.list");
        } catch (Exception e) {
            System.out.println("File doesn't exist");
        }

        macs.setText(tempList);
    }

    public static void main(String[] args) {
        MacDisplay display = new MacDisplay();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("save")) {
            addresses.clear();
            tempList = macs.getText();
            DataManager.save(tempList, "mac.list");
            int lastIndex = 0;
            for (int i = 0; i < tempList.length(); i++) {
                if (tempList.charAt(i) == '\n') {
                    addresses.add(tempList.substring(lastIndex, i));
                    lastIndex = i + 1;
                }
            }
            this.toggleVisibility();
        }
    }
}
