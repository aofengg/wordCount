CS542 Design Patterns
Fall 2016
PROJECT <4> README FILE

Due Date: <Wednesday, November 23, 2016>
Submission Date: <Wednesday, November 23, 2016>
Grace Period Used This Project: <7> Days
Grace Period Remaining: <0> Days
Author(s): <Yunpeng Ge>
e-mail(s): <yge6@binghamton.edu>


PURPOSE:

  Apply several principles and patterns you have learned so far to develop and test code for the given problem.

PERCENT COMPLETE:

  I believe I have completed 100% of this project.

PARTS THAT ARE NOT COMPLETE:

  I believe there should not be anything uncompleted.

BUGS:

  None.

FILES:

  /src/wordCount/driver/
  Driver.java

  /src/wordCount/dsForStrings/
  AvlNode.java
  AvlTree.java

  /src/wordCount/test
  Test.java

  /src/wordCount/util
  FileProcessor.java

  /src/wordCount/visitors
  CloneAndObserveVisitor.java
  DSProcessingVisitorI.java
  PopulateVisitor.java
  WordCountVisitor.java

  build.xml

  input-extra-for-test.txt

  README.txt

DATA STRUCTURE:

  Use AVL data structure to store the content of the input file.
  The source code of AVL refer to “http://blog.csdn.net/liyong199012/article/details/29219261”. I modify some parts of the AVL source code to adapt to this assignment.
  The reason to choose AVL is that it balances itself every time a new node being inserted. This mechanism can save a lot of time.


SAMPLE OUTPUT:

  [ac:~/wordCount$ ant -buildfile build.xml run -Darg0=input-5.txt -Darg1=output.txt -Darg2=3
  Buildfile: /Users/aofengg/Documents/workspace/wordCount/build.xml

  prepare:

  wordCount:

  jar:

  run:
    [java] The PopuateVisitor and WordCoundVisitor totally cost 11 millisecond.

  BUILD SUCCESSFUL
  Total time: 0 seconds

TO COMPILE:

  ant -buildfile build.xml all

TO RUN:

  ant -buildfile build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=3

TO USE THE TEST CLASS FOR BACKUP CHECK:

  First of all, “input-extra-for-test.txt” is a very important file. The content of this file can be the first paragraph of your input file. To avoid influencing the command arguments check, I hardcode the name of the file.

  The file “input-extra-for-test.txt” has been provided for backup check. As the original tree has been constructed, now we need to change the Node.number of some nodes. The Test class will call populate visitor again to read the “input-extra-for-test.txt”, and then insert the new information into the data structure (atree). Now the original tree has been updated, and Test class will print the new original tree into “newOriginalTree.txt”, then print the backup tree into “newBackupTree.txt”. By comparing the information between “newOriginalTree.txt” and “newBackupTree.txt” using:
 
	diff --ignore-all-space newOriginalTree.txt newBackupTree.txt

we can know that the backup tree has been updated, too.

  You can use:

	diff --ignore-all-space originalTree.txt newOriginalTree.txt 

to get which nodes have been updated.


THE PROCESS OF TEST CLASS:

  1. Print original tree named “originalTree.txt”.
  2. Print backup tree named “backupTree.txt”.
  3. Read “input-extra-for-test.txt” and then update original tree.
  4. Print new original tree named “newOriginalTree.txt”.
  5. Print backup tree again named “newBackupTree.txt”.

ACKNOWLEDGEMENT:

  I finished this project totally by myself.
