package parkeersimulator.view;

import parkeersimulator.model.Model;


import javax.swing.*;

public abstract class AbstractView extends JPanel {
	private static final long serialVersionUID = 6437976554496769048L;
	protected Model model;

	public AbstractView(Model model) {
		this.model=model;
		model.addView(this);
	}
	
	public Model getModel() {
		return model;
	}
	
	public void updateView() {
		repaint();
	}
}
