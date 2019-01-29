package parkeersimulator.controller;

import parkeersimulator.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
public class MenuBarController extends JMenuBar {
    private Model model;
    public MenuBarController(Model model)
    {
        this.model = model;

        //Makes a toolkit so the user can use hotkeys to interact in the simulation
        final int SHORTCUT_MASK =
                Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();


        JMenu fileMenu = new JMenu("Bestand"); // Creates a menu for the menubar called "Bestand" B.S.
        add(fileMenu);

        JMenuItem doOneStep = new JMenuItem("Een stap verdergaan"); // Creates a MenuItem for the menu(Bestand) called doOneStep
        doOneStep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK)); // Creates a hotkey so when CTRL+S is pressed the method below will activate
        doOneStep.addActionListener(e -> model.oneStep()); // Adds an ActionListener to the MenuItem
        fileMenu.add(doOneStep);

        JMenuItem doHundredSteps = new JMenuItem("Honderd stappen verdergaan"); // Creates a MenuItem for the menu(Bestand) called doHundredSteps
        doHundredSteps.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, SHORTCUT_MASK)); // Creates a hotkey so when CTRL+H is pressed the method below will activate
        doHundredSteps.addActionListener(e -> model.hundredSteps()); // Adds an ActionListener to the MenuItem
        fileMenu.add(doHundredSteps);

        JMenuItem quitItem = new JMenuItem("Sluiten"); // Creates a MenuItem for the menu(Bestand) called quitItem
        quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK)); // Creates a hotkey so when CTRL+Q is pressed the method below will activate
        quitItem.addActionListener(e -> model.quit()); // Adds an ActionListener to the MenuItem
        fileMenu.add(quitItem);
    }
}
