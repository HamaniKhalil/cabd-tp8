package utests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import indexing.BKey;
import indexing.BNode;

public class BNodeTest {

	@Test
	public void splitAndPushTest() {
		BNode	node	= new BNode();
		
		node.setFirstChild(new BNode());
		
		BKey []	keys	= node.getKeys();
		
		keys[0]	= new BKey("CB");
		keys[1]	= new BKey("AB");
		
		Arrays.sort(keys);
		
		node.splitAndPush(new BKey("AC"));
		
		node.getFather().addKey(new BKey("DB"));
		
		node.getFather().splitAndPush(new BKey("ZX"));
		
		assertEquals("[ 'DB', 'null' ]", node.getFather().getFather().toString());
		assertEquals("[ 'AC', 'null' ]", node.getFather().toString());
		assertEquals("[ 'ZX', 'null' ]", node.getFather().getFather().getKeys()[0].getChild().toString());
		assertEquals("[ 'AB', 'null' ]", node.toString());
		assertEquals("[ 'CB', 'null' ]", node.getFather().getKeys()[0].getChild().toString());
	}

}
