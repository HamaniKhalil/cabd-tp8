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

package spec;

public interface MyFileIO {
	
	public boolean create();
	public MyBlock getBlock(int blockId);
	public boolean putBlock(MyBlock block);
	public void removeBlock(int blockId);
	public void getAllBlocks();
}
