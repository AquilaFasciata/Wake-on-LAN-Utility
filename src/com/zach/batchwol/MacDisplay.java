package com.zach.batchwol;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MacDisplay implements ActionListener {

    private JFrame macFrame;
    private JTextArea macs;
    private static ArrayList<String> addresses = new ArrayList<String>();
    private static ArrayList<String> ipList = new ArrayList<String>();

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

        macs = new JTextArea("Insert MAC Addresses here, seperated by a \nnew line. eg:\nA8-6D-AA-E8-70-69" +
                "\n(mac address\nmac address)");
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
        macFrame.setVisible(visibility);
    }

    public void toggleVisibility() {
        setVisibility(!isVisible());
    }

    public MacDisplay() {
        this(true);
    }

    public ArrayList<String> getAddresses() {
        return addresses;
    }

    public static ArrayList<String> getIpList() {
        return ipList;
    }

    public static void main(String[] args) {
        MacDisplay display = new MacDisplay();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("save")) {
            addresses.clear();
            ipList.clear();
            String tempAddress = macs.getText();
            String tempIP = "";
            ArrayList<String> couplets = new ArrayList<String>();
            tempAddress = tempAddress.trim();
            int recentIndex = 0;
            for (int i = 1; i < tempAddress.length(); i++)
                if (tempAddress.substring(i - 1, i).equals("\n")) {
                    couplets.add(tempAddress.substring(recentIndex, i).trim());
                    recentIndex = i;
                }

            couplets.add(tempAddress.substring(recentIndex, tempAddress.length()));


            for (String couple : couplets) {
                int endIndex = couple.indexOf('/');
                addresses.add(couple.substring(0, endIndex));
                ipList.add(couple.substring(endIndex + 1, couple.length()));
            }

            for (String ad : addresses)
                System.out.print(ad + " ");

            System.out.println("");

            for (String ad : ipList)
                System.out.print(ad + " ");
        }
    }
}
