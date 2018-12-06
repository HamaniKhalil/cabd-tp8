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

public class SystemConfiguration {
	
	// For now, the memory buffer size is the same for disk
	public static final int		BUFFER_SIZE					= 10;
	public static final int		DESCRIPTOR_SIZE				= 30;
	public static final int		DISCRIMINATION_INDEX		= 0;
	public static final int		FIRST_ARRAY_ELEMENT_INDEX	= 0;
	public static final int		MAXIMUM_BLOCKS_PER_FILE		= 20;
	public static final int		MINIMUM_BLOCKS_PER_FILE		= 0;
	public static final char	THE_NONE_CHARACTER			= '\u0000';
	public static final int		THE_B_TREE_M_ORDER			= 1;
	
	// File management
	public static final String	JOIN_FILENAME	= "RS";
	public static final String	PASS_FILENAMING	= "-pass-";
	public static final String	FILENAME_SUFFIX	= ".txt";
	public static final String	PATH_SEPARATOR	= "/";
}
