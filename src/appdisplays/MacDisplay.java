package appdisplays;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MacDisplay implements ActionListener {

    private JFrame macFrame;
    private JPanel macPanel;
    private JTextArea macs;
    private JButton addMacs;
    private static ArrayList<String> addresses = new ArrayList<String>();

    public MacDisplay(boolean visible) {
        macFrame = new JFrame();
        macPanel = new JPanel();

        macFrame.setSize(300, 200);
        macFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        macFrame.setLocationRelativeTo(null);
        macFrame.add(macPanel);

        macPanel.setLayout(null);

        macs = new JTextArea();
        macs.setBounds(10, 10, 265, 100);
        macPanel.add(macs);

        addMacs = new JButton("Save Addresses");
        addMacs.setBounds(80, 115, 130, 25);
        macPanel.add(addMacs);
        addMacs.setActionCommand("save");
        addMacs.addActionListener(this);

        macFrame.setVisible(visible);
    }

    public MacDisplay() {this(true);}

    public ArrayList<String> getAddresses() {return addresses;}

    public static void main(String[] args) {
        MacDisplay display = new MacDisplay();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("save")) {
            String tempAddress = macs.getText();
            tempAddress = tempAddress.trim();
            int recentIndex = 0;
            for (int i = 1; i < tempAddress.length(); i++) {
                if (tempAddress.substring(i - 1, i).equals("\n")) {
                    addresses.add(tempAddress.substring(recentIndex, i).trim());
                    System.out.println(tempAddress.substring(recentIndex, i).trim());
                    recentIndex = i;
                }
            }
            String lastAddress = tempAddress.substring(recentIndex, tempAddress.length()).trim();
            addresses.add(lastAddress);
            System.out.println(lastAddress);
            for (String ad: addresses)
                System.out.print(ad + " ");
        }
    }
}
