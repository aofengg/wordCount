package wordCount.visitors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import wordCount.dsForStrings.AvlNode;
import wordCount.dsForStrings.AvlTree;

public class CloneAndObserveVisitor implements DSProcessingVisitorI {
	public ArrayList<AvlNode> midOrderNodeList = new ArrayList<AvlNode>();
	private AvlTree atree = null;

	public void visit(AvlTree atreeIn) {
		this.atree = atreeIn;
		doMidOrderClone(atree);
	}

	private void midOrderClone(AvlNode rootIn) {
		if (rootIn != null) {
			midOrderClone(rootIn.left);
			this.midOrderNodeList.add((AvlNode) rootIn.clone());
			midOrderClone(rootIn.right);
		}
	}

	public void doMidOrderClone(AvlTree atreeIn) {
		midOrderClone(atreeIn.root);
//		System.out.println("clone node number: " + this.midOrderNodeList.size());
	}

	public void printMidOrderToFile(File file) {
		try {
			FileWriter fw = new FileWriter(file);
			for (int i = 0; i < midOrderNodeList.size(); i++) {
				fw.write(midOrderNodeList.get(i).element + ": " + midOrderNodeList.get(i).number + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
