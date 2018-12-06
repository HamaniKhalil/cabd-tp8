import static system.SystemConfiguration.FILENAME_SUFFIX;

import java.io.File;

public class Application {

	public static void main(String[] args) {
		File	file	= new File("S");
		File []	blocks	= file.listFiles();
		
		for(int i = 0; i < blocks.length; i ++) {
			System.out.println(getBlockId(blocks[i].getName()));
		}
	}
	
	private static int getBlockId(String filename) {
		return Integer.parseInt(filename.substring(1, filename.indexOf(FILENAME_SUFFIX)));
	}

}
