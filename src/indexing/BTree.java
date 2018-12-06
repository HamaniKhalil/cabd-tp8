/*
 * TP N°		: 08
 * Version N°	: 01
 * 
 * Titre du TP	: B+Tree
 * 
 * Date			: 30 Novembre 2018
 * 
 * Nom			: Hamani
 * Prenom		: Khalil
 * N° Etudiant	: 21810826
 * 
 * Email		: hamani_khalil@yahoo.fr
 * 
 * Remarques	: N/A
 * 
 * */

package indexing;

import io.FileHandler;
import spec.MyBTree;

public class BTree implements MyBTree {
	
	private BNode	root;
	
	public BTree(String filename) {
		FileHandler	file	= new FileHandler(filename);
		file.getAllBlocks();
	}
	
	@Override
	public void insertBulkData(int blockId) {
		
	}
}
