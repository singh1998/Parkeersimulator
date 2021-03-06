/**
 * This class represents the menubar of the application
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.controller;

import parkeersimulator.model.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuBarController extends JMenuBar {
    private Model model;
    /**
     * Constructor for objects of class MenuBarController
     */
    public MenuBarController(Model model) {
        this.model = model;

        //Makes a toolkit so the user can use hotkeys to interact in the simulation
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenu fileMenu = new JMenu("Bestand"); // Creates a menu for the menubar called "Bestand" B.S.
        fileMenu.setFont(new Font("Century Gothic", Font.BOLD, 11));
        add(fileMenu);

        JMenuItem doOneStep = new JMenuItem("Een minuut verdergaan"); // Creates a MenuItem for the menu(Bestand) called doOneStep
        doOneStep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, SHORTCUT_MASK)); // Creates a hotkey so when CTRL+M is pressed the method below will activate
        doOneStep.addActionListener(e -> model.oneStep()); // Adds an ActionListener to the MenuItem
        doOneStep.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        fileMenu.add(doOneStep);

        JMenuItem doHundredSteps = new JMenuItem("Honderd minuten verdergaan"); // Creates a MenuItem for the menu(Bestand) called doHundredSteps
        doHundredSteps.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, SHORTCUT_MASK)); // Creates a hotkey so when CTRL+H is pressed the method below will activate
        doHundredSteps.addActionListener(e -> model.hundredSteps()); // Adds an ActionListener to the MenuItem
        doHundredSteps.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        fileMenu.add(doHundredSteps);

        JMenuItem doOneHour = new JMenuItem("Een uur verdergaan"); // Creates a MenuItem fot the menu(Bestand) called doOneHour
        doOneHour.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, SHORTCUT_MASK)); // Creates a hotkey so when CTRL+U is pressed the method below will activate
        doOneHour.addActionListener(e -> model.oneHour()); // Adds an ActionListener to the MenuItem
        doOneHour.setFont(new Font("Century Gothic", Font.PLAIN, 11));
        fileMenu.add(doOneHour);

        JMenuItem doOneDay = new JMenuItem("Een dag verdergaan"); // Creates a MenuItem fot the menu(Bestand) called doOneDay
        doOneDay.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, SHORTCUT_MASK));
        doOneDay.addActionListener(e -> model.oneDay()); // Adds an ActionListener to the MenuItem
        doOneDay.setFont(new Font("Century Gothic",Font.PLAIN,11));
        fileMenu.add(doOneDay);

        JMenuItem quitItem = new JMenuItem("Sluiten"); // Creates a MenuItem for the menu(Bestand) called quitItem
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK)); // Creates a hotkey so when CTRL+Q is pressed the method below will activate
        quitItem.addActionListener(e -> model.quit()); // Adds an ActionListener to the MenuItem
        quitItem.setFont(new Font("Century Gothic",Font.BOLD,11));
        fileMenu.add(quitItem);





    }
}