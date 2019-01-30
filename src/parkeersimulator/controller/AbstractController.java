/**
 * This class describes a MVC controller
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.controller;

import parkeersimulator.model.Model;


import javax.swing.*;

public abstract class AbstractController extends JPanel {
	protected Model model;
	/**
	 * Constructor of class AbstractController
	 */
	public AbstractController(Model model) {
		this.model=model;
	}
}
