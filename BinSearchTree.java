
public class BinSearchTree {
	private BinSearchTreeNode root;
	
	
	// method to implement: getWord
	public BinSearchTreeNode getWord(String searchWord) {
		return search(root,searchWord);
	}
	
	private BinSearchTreeNode search(BinSearchTreeNode root, String searchWord) {
			// base case
			if(root == null) {
				return null;
			}
			
			// recursion rule
			if(root.getWord().compareTo(searchWord)==0) {
				return root;
			}else if (searchWord.compareTo(root.getWord())>0) {
				return search(root.getRight(), searchWord);
			}else {
				return search(root.getLeft(),searchWord);
			}
		}
		
	// second method
	public void insertWord(String theWord, String theFileName, int thePosition) {
		BinSearchTreeNode tempnode = search(root,theWord);
		if(tempnode == null) {
			insert(root, theWord, theFileName, thePosition);
		}else {
			LinkedList filenodelist = tempnode.getFiles();
			filenodelist.insertWord(theFileName, thePosition);	
		}
	}
	
	/*
	 *  if the root is null then we have a node in the tree, else enter the tree to find
	 *  where the node is
	 */
	private void insert(BinSearchTreeNode root, String theWord, String theFile, int thePosition) {
		if(root == null) {
			this.root = new BinSearchTreeNode(theWord, theFile, thePosition);
		}else if (theWord.compareTo(root.getWord())<0) {
			if(root.getLeft() == null) {
				BinSearchTreeNode leftnew= new BinSearchTreeNode(theWord, theFile, thePosition);
				root.setLeft(leftnew);
			}else {		
				insert(root.getLeft(), theWord, theFile,thePosition);
			}
		}else if(root.getRight() == null) {
			BinSearchTreeNode rightnew = new BinSearchTreeNode(theWord, theFile, thePosition);
			root.setRight(rightnew);
		}
		else {
			insert(root.getRight(), theWord, theFile, thePosition);
		}
		}
}

