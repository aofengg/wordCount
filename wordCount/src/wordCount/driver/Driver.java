package wordCount.driver;

import java.io.File;
import java.io.IOException;
import wordCount.dsForStrings.AvlTree;
import wordCount.test.Test;
import wordCount.visitors.CloneAndObserveVisitor;
import wordCount.visitors.DSProcessingVisitorI;
import wordCount.visitors.PopulateVisitor;
import wordCount.visitors.WordCountVisitor;

public class Driver {
	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out.println("Please enter three command line arguments.");
			System.out.println("<input.txt> <output.txt> <NUM_ITERATIONS>.");
		} else {
			int NUM_ITERATIONS = Integer.parseInt(args[2]);
			AvlTree atree = null;

			long startTime = System.currentTimeMillis();

			for (int i = 0; i < NUM_ITERATIONS; i++) {
				// The input file
				File input_file = new File(args[0]);

				// atree is the data structure storing all the words in the file
				atree = new AvlTree();

				// Populate Visitor
				DSProcessingVisitorI populateVisitor = new PopulateVisitor(input_file);
				atree.accept(populateVisitor);

				// WordCount Visitor
				File output_file = new File(args[1]);
				DSProcessingVisitorI wordCountVisitor = new WordCountVisitor(output_file);
				atree.accept(wordCountVisitor);
			}

			long finishTime = System.currentTimeMillis();
			System.out.println("The PopuateVisitor and WordCoundVisitor totally cost "
					+ (finishTime - startTime) / NUM_ITERATIONS + " millisecond.");

			// Clone-And-Observe Visitor
			DSProcessingVisitorI cloneAndObserveVisitor = new CloneAndObserveVisitor();
			atree.accept(cloneAndObserveVisitor);

			/**
			 * atree contains the original tree; cloneAndObserveVisitor contains
			 * the backup tree. So atree and cloneAndObserveVisitor need to be
			 * passed to the Test class.
			 */
			Test.doTest(atree, cloneAndObserveVisitor);
		}
	}
}
