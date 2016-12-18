package wordCount.visitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wordCount.dsForStrings.AvlTree;
import wordCount.util.FileProcessor;

public class PopulateVisitor implements DSProcessingVisitorI{
	File file;
	public PopulateVisitor(File fileIn) {
		this.file = fileIn;
	}
	
	@Override
	public void visit(AvlTree avlTree) {
		String line;
		FileProcessor fp = null;
		try {
			fp = new FileProcessor(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while ((line = fp.readOneLineAsString()) != null) {
				if (line.length() != 0) {
					while (line.startsWith(" ")) {
//					line = line.substring(1, line.length()).trim();
						line = line.trim();
					}
					if (line.length() == 0) {
						continue;
					}
					Pattern p = Pattern.compile("[.,\"\\?!:']");
					Matcher m = p.matcher(line);
					line = m.replaceAll(" ");
					line = line.trim();
					String[] sp = line.split("\\s+");
					for (int i = 0; i < sp.length; i++) {
						if (sp[i] != " ") {
//						System.out.println(sp[i]);
							avlTree.insert(sp[i]);
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
