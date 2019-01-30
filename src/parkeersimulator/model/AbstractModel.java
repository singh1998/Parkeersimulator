/**
 * This class describes a MVC Model
 * @version 2.0
 * @Author Park-It
 */
package parkeersimulator.model;

import parkeersimulator.view.AbstractView;


import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel {
	private List<AbstractView> views;

	/**
	 * Constructor of class AbstractModel
	 */
	public AbstractModel() {
		views=new ArrayList<AbstractView>();
	}

	/**
	 * This method let's the model add a view
	 */
	public void addView(AbstractView view) {
		views.add(view);
	}

	/**
	 * This method updates all views that the model holds
	 */
	public void notifyViews() {
		for(AbstractView v: views) v.updateView();
	}
}
