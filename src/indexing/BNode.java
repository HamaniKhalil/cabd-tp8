package indexing;

import static system.SystemConfiguration.THE_B_TREE_M_ORDER;

import java.util.Arrays;

import spec.MyBNode;

public class BNode implements MyBNode<BKey> {

	private BNode	father;
	private BNode	firstChild;
	private BKey []	keys	= new BKey[THE_B_TREE_M_ORDER * 2];
	
	// Constructors
	
	// Setters
	public void setFather(BNode father) {
		this.father = father;
	}
	public void setKeys(BKey[] keys) {
		this.keys = keys;
	}
	public void setFirstChild(BNode firstChild) {
		this.firstChild = firstChild;
	}
	
	// Getters
	public BNode getFather() {
		return father;
	}
	public BKey[] getKeys() {
		return keys;
	}
	public BNode getFirstChild() {
		return firstChild;
	}
	
	// Ovrride methods
	@Override
	public void addKey(BKey key) {
		boolean	added	= false;
		for(int i = 0; i < keys.length; i ++) {
			if(keys[i] == null) {
				keys[i]	= key;
				added	= true;
				break;
			}
		}
		if(!added) {
			splitAndPush(key);
		}
	}
	@Override
	public BNode lookupKey(String key) {
		// TODO Auto-generated method stub
		if(key.compareTo(keys[0].getKey()) < 0) {
			return firstChild.lookupKey(key);
		}
		
		for(int i = 1; i < keys.length; i ++) {
			if((key.compareTo(keys[i].getKey()) < 0)
					|| (key.compareTo(keys[i - 1].getKey()) > 0
							&& key.compareTo(keys[i].getKey()) < 0)) {
				return keys[i - 1].getChild().lookupKey(key);
			}
			else if(key.compareTo(keys[i].getKey()) > 0) {
				if(i == keys.length - 1) {
					return keys[i].getChild().lookupKey(key);
				}
			}
			else if(key.equals(keys[i].getKey())) {
				return this;
			}
		}
		return null;
	}
	@Override
	public void splitAndPush(BKey key) {
		// TODO Auto-generated method stub
		BKey []	nodeToSplit	= new BKey[THE_B_TREE_M_ORDER * 2 + 1];
		
		for(int i = 0; i < keys.length; i ++) {
			nodeToSplit[i]	= keys[i];
		}
		
		nodeToSplit[nodeToSplit.length - 1] = key;
		
		Arrays.sort(nodeToSplit);
		
		BNode	newNode	= new BNode();
		
		int	j	= 0;
		for(int i = 0; i < nodeToSplit.length; i ++) {
			if(i < THE_B_TREE_M_ORDER) {
				keys[i]	= nodeToSplit[i];
			}
			else if(i == THE_B_TREE_M_ORDER) {
				father	= father != null ? father : new BNode();
				father.setFirstChild(this);
				nodeToSplit[i].setChild(newNode);
				father.addKey(nodeToSplit[i]);
			}
			else {
				newNode.getKeys()[j ++]		= nodeToSplit[i];
				newNode.getKeys()[i - 1]	= null;
				keys[i - 1]					= null;
			}
		}
	}
	
	public String toString() {
		String	result	= "[ '";
		
		int lastIndex	= keys.length - 1;
		
		String	key	= null;
		
		for(int i = 0; i < lastIndex; i ++) {
			key	= keys[i].getKey();
			result	+= key + "', '";
		}
		
		result	+= keys[lastIndex] + "' ]";
		return result;
	}
}
