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


package system;

public class SystemErrors {

	// Error messages
	public static final String	ERROR_MESSAGE_NOT_ADDED				= "The descriptor is already full";
	public static final String	ERROR_MESSAGE_REMOVE_OUTOFBOUNDS	= "The remove index is out of bounds";
	public static final String	ERROR_MESSAGE_FILE_NOT_EXISTS		= "The block doesn't exist on disk";
	public static final String	ERROR_MESSAGE_TUPLE_NOT_APPENDED	= "The tuple was not appended, there's no room in the current block";
	public static final String	ERROR_MESSAGE_FILENAME_NOT_SET		= "The filename have been set wrongly";
	public static final String	ERROR_MESSAGE_BLOCK_ID_NOT_SET		= "The block id have been set wrongly";
	public static final String	ERROR_MESSAGE_BLOCK_NOT_CREATED		= "The block has not been created";
	
	// Error codes
	public static final int	ERROR_CODE_FULL_BLOCK	= -101;
}
