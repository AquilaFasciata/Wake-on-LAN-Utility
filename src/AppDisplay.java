import javax.swing.*;
import java.awt.*;

public class AppDisplay {

    public AppDisplay() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Wake-on-LAN Utility");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AppDisplay display = new AppDisplay();
    }
}