/**
 * This class describes a MVC view
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.view;

import parkeersimulator.model.Model;


import javax.swing.*;
/**
 * Constructor of class AbstractView
 */
public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = 6437976554496769048L;
	protected Model model;

	public AbstractView(Model model) {
		this.model=model;
		model.addView(this);
	}

	/**
	 * This method gives the model that this view holds
	 * @return a Model object
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * This method updates the view and forces subclasses to implement this method
	 *
	 */
	public abstract void updateView();
}
