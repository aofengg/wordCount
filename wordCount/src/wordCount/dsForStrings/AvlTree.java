package wordCount.dsForStrings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import wordCount.visitors.DSProcessingVisitorI;

public class AvlTree {
	public AvlNode root;
	private Comparator<String> cmp;

	public AvlTree() {
		root = null;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(String element) {
		root = insert(element, root);
	}

	public boolean contains(String x) {
		return contains(x, root);
	}

	private int myCompare(String lhs, String element) {
		if (cmp != null)
			return cmp.compare(lhs, element);
		else
			return ((Comparable<String>) lhs).compareTo(element);
	}

	private boolean contains(String x, AvlNode t) {
		if (t == null)
			return false;
		int compareResult = myCompare(x, t.element);
		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true;
	}

	private int height(AvlNode t) {
		return t == null ? -1 : t.height;
	}

	private AvlNode insert(String x, AvlNode t) {
		if (t == null)
			return new AvlNode(x, null, null);
		int compareResult = myCompare(x, t.element);
		if (compareResult < 0) {
			t.left = insert(x, t.left);
			if (height(t.left) - height(t.right) == 2) {
				if (myCompare(x, t.left.element) < 0) 
					t = rotateWithLeftChild(t);
				else 
					t = doubleWithLeftChild(t);
			}
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
			if (height(t.right) - height(t.left) == 2) {
				if (myCompare(x, t.right.element) < 0) 
					t = doubleWithRightChild(t);
				else 
					t = rotateWithRightChild(t);
			}
		} else {
			t.number++;
			t.changed();
		}
		t.height = Math.max(height(t.left), height(t.right)) + 1;
		return t;
	}

	private AvlNode rotateWithLeftChild(AvlNode k2) {
		AvlNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}

	private AvlNode rotateWithRightChild(AvlNode k2) {
		AvlNode k1 = k2.right;
		k2.right = k1.left;
		k1.left = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.right), k2.height) + 1;
		return k1; 
	}

	private AvlNode doubleWithLeftChild(AvlNode k3) {
		try {
			k3.left = rotateWithRightChild(k3.left);
		} catch (NullPointerException e) {
			System.out.println("k.left.right为：" + k3.left.right);
			throw e;
		}
		return rotateWithLeftChild(k3);
	}

	private AvlNode doubleWithRightChild(AvlNode k3) {
		try {
			k3.right = rotateWithLeftChild(k3.right);
		} catch (NullPointerException e) {
			System.out.println("k.right.left为：" + k3.right.left);
			throw e;
		}
		return rotateWithRightChild(k3);
	}

	private void midOrder(AvlNode rootIn, File file) { 
		if (rootIn != null) {
			midOrder(rootIn.left, file);
			try {
				FileWriter fw = new FileWriter(file, true);
				fw.write("" + rootIn.element + ": " + rootIn.number + "\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.print("" + rootIn.element + "(" + rootIn.number + ")" + "--");
			midOrder(rootIn.right, file);
		}
	}
	
	public void printMidOrderToFile(File file) {
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			fw.write("");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		midOrder(this.root, file);
	}
	
	private int getNumberOfWord(AvlNode rootIn, int result) {
		if (rootIn != null) {
			int resultLeft = getNumberOfWord(rootIn.left, result);
			int resultRight = getNumberOfWord(rootIn.right, result);
			result = result + resultLeft + resultRight + rootIn.number;
		} else {
			return 0;
		}
//		System.out.println(rootIn.element + ": " + result);
		return result;
	}
	
	/**
	 * @return      number of total words
	 */
	public int numberOfWord() {
		return getNumberOfWord(this.root, 0);
	}
	
	private int getNumberOfDistinctWord(AvlNode rootIn, int result) {
		if (rootIn != null) {
			int resultLeft = getNumberOfDistinctWord(rootIn.left, result);
			int resultRight = getNumberOfDistinctWord(rootIn.right, result);
			result = result + resultLeft + resultRight + 1;
		} else {
			return 0;
		}
//		System.out.println(rootIn.element + ": " + result);
		return result;
	}
	
	/**
	 * @return      number of distinct word
	 */
	public int numberOfDistinctWord() {
		return getNumberOfDistinctWord(this.root, 0);
	}
	
	private int getNumberOfCharacters(AvlNode rootIn, int result) {
		if (rootIn != null) {
			int resultLeft = getNumberOfCharacters(rootIn.left, result);
			int resultRight = getNumberOfCharacters(rootIn.right, result);
			result = result + resultLeft + resultRight + rootIn.number * rootIn.element.length();
		} else {
			return 0;
		}
//		System.out.println(rootIn.element + ": " + result);
		return result;
	}
	
	/**
	 * @return      number of characters
	 */
	public int numberOfCharacters() {
		return getNumberOfCharacters(this.root, 0);
	}
	
	public void accept(DSProcessingVisitorI visitor) {
		visitor.visit(this);
	}
}
