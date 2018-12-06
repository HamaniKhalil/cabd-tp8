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

package io;


import static system.SystemConfiguration.BUFFER_SIZE;
import static system.SystemConfiguration.FILENAME_SUFFIX;
import static system.SystemConfiguration.MAXIMUM_BLOCKS_PER_FILE;
import static system.SystemConfiguration.MINIMUM_BLOCKS_PER_FILE;
import static system.SystemConfiguration.PATH_SEPARATOR;
import static system.SystemErrors.ERROR_MESSAGE_BLOCK_ID_NOT_SET;
import static system.SystemErrors.ERROR_MESSAGE_BLOCK_NOT_CREATED;
import static system.SystemErrors.ERROR_MESSAGE_FILENAME_NOT_SET;
import static system.SystemErrors.ERROR_MESSAGE_FILE_NOT_EXISTS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import spec.MyBlock;

public class BlockHandler implements MyBlock {
	
	private int blockId;
	
	private	String	originFile;
	
	// Constructors
	public BlockHandler(int blockId) {
		this.blockId	= blockId;
	}
	
	public BlockHandler(String blockFilename) {
		blockId	= parseBlockId(blockFilename);
	}
	
	// Getters
	public int getBlockId() {
		return this.blockId;
	}
	
	public String getOriginFile() {
		return originFile;
	}

	// Setters
	public void setBlockId(int blockId) {
		this.blockId	= blockId;
	}
	
	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}

	// Interface implementation
	@Override
	public boolean create() {
		String	filename	= originFile + PATH_SEPARATOR + blockId + FILENAME_SUFFIX;
		try {
			if(filename != null && !filename.isEmpty()) {
				File	file	= new File(filename);
				if(!file.exists()) {
					return file.createNewFile();
				}
				else {
					file.delete();
					return file.createNewFile();
				}
			}
			else {
				System.out.println(ERROR_MESSAGE_FILENAME_NOT_SET);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public String[] read() {
		if(isValidBlockId()) {
			String	filename	= originFile + PATH_SEPARATOR + blockId + FILENAME_SUFFIX;
			
			String []	block	= new String[BUFFER_SIZE];
			try {
				if(filename != null && !filename.isEmpty()) {
					FileReader		fr	= new FileReader(filename);
					BufferedReader	br	= new BufferedReader(fr);
					
					int	i	= 0;
					
					String	line;
					
					while((line = br.readLine()) != null && i < BUFFER_SIZE) {
						block[i ++]	= line;
					}
					return block;
				}
				else {
					System.out.println(ERROR_MESSAGE_FILENAME_NOT_SET);
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		else {
			System.out.println(ERROR_MESSAGE_BLOCK_ID_NOT_SET);
			return null;
		}
			
	}
	
	@Override
	public boolean write(String[] block) {
		if(create()) {
			String	filename	= originFile + PATH_SEPARATOR + blockId + FILENAME_SUFFIX;
			try {
				if(filename != null && !filename.isEmpty()) {
					FileWriter		fw	= new FileWriter(filename);
					BufferedWriter	bw	= new BufferedWriter(fw);
					
					/*
					 * In order to avoid making a loop that tests each time the statement (i != 0)
					 * for the '\n' character
					 * 
					 * Previous method was
					 * 
					 * for(int i = 0; i < BUFFER_SIZE; i ++) {
					 * 	if(i != 0) {
					 * 		bw.append("\n");
					 * 	}
					 * 	bw.append(block[i]);
					 * }
					 * 
					 * The 'if' statement is usefull only once, and it's a waste to
					 * use it in each iteration
					 * 
					 */
					bw.append(block[0]);
					
					for(int i = 1; i < BUFFER_SIZE; i ++) {
						bw.append("\n" + block[i]);
					}
					
					return true;
				}
				else {
					System.out.println(ERROR_MESSAGE_FILE_NOT_EXISTS);
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		else {
			System.out.println(ERROR_MESSAGE_BLOCK_NOT_CREATED);
			return false;
		}
		
	}
	
	@Override
	public boolean remove() {
		if(isValidBlockId()) {
			String	filename	= originFile + PATH_SEPARATOR + blockId + FILENAME_SUFFIX;
			
			try {
				File	file	= new File(filename);
				if(file.exists()) {
					return file.delete();
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		else {
			System.out.println(ERROR_MESSAGE_BLOCK_ID_NOT_SET);
			return false;
		}
	}
	
	// Other methods
	public boolean isValidBlockId() {
		return blockId >= MINIMUM_BLOCKS_PER_FILE && blockId < MAXIMUM_BLOCKS_PER_FILE;
	}
	
	private int parseBlockId(String filename) {
		return Integer.parseInt(filename.substring(1, filename.indexOf(FILENAME_SUFFIX)));
	}
}
