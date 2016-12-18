package wordCount.visitors;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import wordCount.dsForStrings.AvlNode;
import wordCount.dsForStrings.AvlTree;

public class WordCountVisitor implements DSProcessingVisitorI{
	private AvlTree atree = null;
	File outputFile;
	
	public WordCountVisitor(File fileIn) {
		outputFile = fileIn;
	}
	
	public void visit(AvlTree atreeIn) {
		this.atree = atreeIn;
//		System.out.println("Total words: " + numberOfWord(atree));
//		System.out.println("Distinct words: " + numberOfDistinctWord(atree));
//		System.out.println("Total characters: " + numberOfCharacters(atree));
		try {
			FileWriter fw = new FileWriter(outputFile);
			fw.write("Total words: " + numberOfWord(atree) + "\n" +
					"Distinct words: " + numberOfDistinctWord(atree) + "\n" +
					"Total characters: " + numberOfCharacters(atree) + "\n");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private int getNumberOfWord(AvlNode rootIn, int result) {
		if (rootIn != null) {
			int resultLeft = getNumberOfWord(rootIn.left, result);
			int resultRight = getNumberOfWord(rootIn.right, result);
			result = result + resultLeft + resultRight + rootIn.number;
		} else {
			return 0;
		}
		return result;
	}
	
	public int numberOfWord(AvlTree atreeIn) {
		return getNumberOfWord(atreeIn.root, 0);
	}
	
	private int getNumberOfDistinctWord(AvlNode rootIn, int result) {
		if (rootIn != null) {
			int resultLeft = getNumberOfDistinctWord(rootIn.left, result);
			int resultRight = getNumberOfDistinctWord(rootIn.right, result);
			result = result + resultLeft + resultRight + 1;
		} else {
			return 0;
		}		
		return result;
	}
	
	public int numberOfDistinctWord(AvlTree atreeIn) {
		return getNumberOfDistinctWord(atreeIn.root, 0);
	}
	
	private int getNumberOfCharacters(AvlNode rootIn, int result) {
		if (rootIn != null) {
			int resultLeft = getNumberOfCharacters(rootIn.left, result);
			int resultRight = getNumberOfCharacters(rootIn.right, result);
			result = result + resultLeft + resultRight + rootIn.number * rootIn.element.length();
		} else {
			return 0;
		}
		return result;
	}
	
	public int numberOfCharacters(AvlTree atreeIn) {
		return getNumberOfCharacters(atreeIn.root, 0);
	}
}
