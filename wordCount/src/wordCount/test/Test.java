package wordCount.test;

import java.io.File;
import wordCount.dsForStrings.AvlTree;
import wordCount.visitors.CloneAndObserveVisitor;
import wordCount.visitors.DSProcessingVisitorI;
import wordCount.visitors.PopulateVisitor;

public class Test {
	public static void doTest(AvlTree atree, DSProcessingVisitorI cloneAndObserveVisitor) {
		File originalTreeF = new File("originalTree.txt");
		File cloneTreeF = new File("backupTree.txt");
		
		//Print the original tree and backup tree into two files
		atree.printMidOrderToFile(originalTreeF);
		((CloneAndObserveVisitor)cloneAndObserveVisitor).printMidOrderToFile(cloneTreeF);
		
		/**
		 * There are some words in input-extra.txt.
		 * Insert all the word in this file into the original tree (atree),
		 *   so the original tree (atree) has been modified.
		 * The numbers of some words in the original tree have been increased.
		 */
		File fileTest = new File("input-extra-for-test.txt");
		DSProcessingVisitorI populateVisitorTest = new PopulateVisitor(fileTest);
		atree.accept(populateVisitorTest);
		
		/**
		 * Print the original tree and backup tree into two other files.
		 * By comparing the two trees, we can see the backup tree also has been updated. 
		 */
		File newOriginalTreeF = new File("newOriginalTree.txt");
		File newCloneTreeF = new File("newBackupTree.txt");
		
		atree.printMidOrderToFile(newOriginalTreeF);
		((CloneAndObserveVisitor)cloneAndObserveVisitor).printMidOrderToFile(newCloneTreeF);
	}
	
}
