package com.zach.batchwol;

import javax.swing.*;

/**
 * @author AquilaFasciata
 * @version 0.1
 */

public class MainApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        MainDisplay display = new MainDisplay();
    }
}