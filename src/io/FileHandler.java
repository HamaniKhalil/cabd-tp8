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

import static system.SystemConfiguration.FILENAME_SUFFIX;
import static system.SystemConfiguration.MAXIMUM_BLOCKS_PER_FILE;
import static system.SystemConfiguration.PATH_SEPARATOR;
import static system.SystemErrors.ERROR_CODE_FULL_BLOCK;
import static system.SystemErrors.ERROR_MESSAGE_FILENAME_NOT_SET;

import java.io.File;

import spec.MyBlock;
import spec.MyFileIO;

public class FileHandler implements MyFileIO {

	private String	filename;
	
	// File descriptor
	private BlockHandler []	blocks	= new BlockHandler[MAXIMUM_BLOCKS_PER_FILE];
	
	// Constructors
	public FileHandler(String filename) {
		this.filename	= filename;
	}
	
	// Getters
	public BlockHandler [] getBlocks() {
		return blocks;
	}
	
	public String getFilename() {
		return filename;
	}
	
	// Setters
	public void setBlocks(BlockHandler [] blocks) {
		this.blocks	= blocks;
	}
	
	public void setFilename(String filename) {
		this.filename	= filename;
	}
	
	@Override
	public boolean create() {
		if(filename != null && !filename.isEmpty()) {
			File	file	= new File(filename);
			if(file.exists()) {
				file.delete();
			}
			return file.mkdirs();
		}
		else {
			System.out.println(ERROR_MESSAGE_FILENAME_NOT_SET);
			return false;
		}
	}

	@Override
	public BlockHandler getBlock(int blockId) {
		return blocks[blockId];
	}

	@Override
	public boolean putBlock(MyBlock block) {
		BlockHandler	bh	= (BlockHandler) block;
		bh.setOriginFile(filename);
		
		int	index	= firstEmptyIndex(); 
		if(index != ERROR_CODE_FULL_BLOCK) {
			blocks[index]	= bh;
			return true;
		}
		return false;
	}
	
	@Override
	public void removeBlock(int blockId) {
		boolean	removed	= false;
		for(int i = 0; i < blocks.length - 1; i ++) {
			if(!removed) {
				if(blocks[i].getBlockId() == blockId) {
					removed		= true;
					blocks[i].remove();
					blocks[i]	= null;
				}
			}
			if(removed) {
				blocks[i]	= blocks[i + 1];
			}
		}
		
		if(removed) {
			blocks[blocks.length - 1]	= null;
		}
	}
	
	@Override
	public void getAllBlocks() {
		File	file	= new File(filename);
		File []	fBlocks	= file.listFiles();
		
		blocks	= new BlockHandler[MAXIMUM_BLOCKS_PER_FILE];
		
		for(int i = 0; i < fBlocks.length; i ++) {
			blocks[i]	= new BlockHandler(fBlocks[i].getName());
			blocks[i].setOriginFile(filename);
		}
	}
	
	// Private methods
	private int firstEmptyIndex() {
		for(int i = 0; i < blocks.length; i ++) {
			if(blocks[i] == null) {
				return i;
			}
		}
		return ERROR_CODE_FULL_BLOCK;
	}

}
