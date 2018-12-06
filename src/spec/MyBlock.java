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

public interface MyBlock {
	
	public boolean create();
	public String [] read();
	public boolean write(String [] block);
	public boolean remove();
}
