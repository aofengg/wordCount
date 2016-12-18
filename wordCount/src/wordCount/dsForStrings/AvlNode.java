package wordCount.dsForStrings;

import java.util.ArrayList;

public class AvlNode {
	public String element;
	public int number;
	public AvlNode left;
	public AvlNode right;
	public int height;
	private ArrayList<AvlNode> observers;

	/**
	 * Three constructors
	 */
	AvlNode(String theElement) {
		this(theElement, null, null);
		observers = new ArrayList<AvlNode>();
	}

	AvlNode(String elementIn, AvlNode lt, AvlNode rt) {
		element = elementIn;
		number = 1;
		left = lt;
		right = rt;
		height = 0;
		observers = new ArrayList<AvlNode>();
	}
	
	AvlNode(String elementIn, int numberIn) {
		element = elementIn;
		number = numberIn;
		observers = new ArrayList<AvlNode>();
	}
	
	/**
	 * @return      The backup AvlNode
	 */
	public Object clone() {
		AvlNode newNode = new AvlNode(this.element, this.number);
		registerObserver(newNode);
		return newNode;
	}
	
	public void registerObserver(AvlNode o) {
		observers.add(o);
	}
	
	public void removeObserver(AvlNode o) {
		int i = observers.indexOf(o);
		if (i > 0) {
			observers.remove(i);
		}
	}
	
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			AvlNode observer = observers.get(i);
			observer.update(this.element, this.number);
		}
	}
	
	private void update(String elementIn, int numberIn) {
		this.element = elementIn;
		this.number = numberIn;
		
	}

	public void changed() {
		notifyObservers();
	}
	
	public void setNumber(int numberIn) {
		this.number = numberIn;
		changed();
	}
	
	public void setElement(String elementIn) {
		this.element = elementIn;
		changed();
	}

}
