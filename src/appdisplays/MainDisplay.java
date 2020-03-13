package appdisplays;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainDisplay implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton macAddresses;
    private JButton sendData;
    private static MacDisplay macDisplay;

    public MainDisplay() {
        // TODO Add Output Console
        frame = new JFrame();
        panel = new JPanel();

        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        macAddresses = new JButton("MAC(s) to Send");
        macAddresses.setBounds(10, 20, 150, 25);
        panel.add(macAddresses);
        macAddresses.setActionCommand("macs");
        macAddresses.addActionListener(this);


        sendData = new JButton("Wake");
        sendData.setBounds(400, 20, 75, 25);
        panel.add(sendData);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("macs")) {
            System.out.println("Opening MAC Address Panel");
            try {
                macDisplay.setVisibility(true);
            } catch (Exception e) {
                macDisplay = new MacDisplay(true);
            }
        }
    }
}
