import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Searcher {
	private HashTable table;
	private String inputFile;
	
	// the constructor
	public Searcher(HashTable table, String inputFile) {
		this.table = table;
		this.inputFile = inputFile;
	}
	
	//  try to read the file
	public void findAllWords() {
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(inputFile));
			String line = in.readLine();
			// split the line by empty space and then use the function to find the word one by one
			while(line!=null) {
				String[] storeline = line.split("\\s+");
				for(int i=0;i < storeline.length;i++) {
					findWord(storeline[i]);
				}
				line = in.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/*
	 * find the position in the table of a certain key word and then find the binary tree search  in the hash table
	 * and then find the word's position in the binary tree. When it is found, outprint its corresponding linkedlinst.
	 * In the linkedlist, use the method in the filenode to get the filename and specific position.
	 */
	public void findWord(String searchWord) {
		int j = table.computeIndex(searchWord);
		BinSearchTree[] temptable = table.getTable();
		BinSearchTreeNode result = temptable[j].getWord(searchWord);
		if(result == null) {
			CustomPrinter.wordNotFound(searchWord, inputFile);
		}else {
			CustomPrinter.wordFound(searchWord, inputFile);
			LinkedList positionList = result.getFiles();
			Iterator<FileNode> iterate = positionList.iterator();
			while(iterate.hasNext()) {
				FileNode next = iterate.next();
				ArrayList<Integer> positions = next.getPositions();
				String filename = next.getFilename();
				CustomPrinter.printPositionsPerFileFound(filename, positions, inputFile);
			}
		}
		
	}
}
