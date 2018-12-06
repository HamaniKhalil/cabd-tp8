package indexing;

import spec.MyBKey;

public class BKey implements MyBKey, Comparable<BKey> {

	private String	key;
	private BNode	child;
	
	// Constructors
	public BKey() {
	}
	
	public BKey(String key) {
		this.key	= key;
	}
	
	public BKey(String key, BNode child) {
		this.key	= key;
		this.child	= child;
	}
	
	// Setters
	public void setKey(String key) {
		this.key = key;
	}
	public void setChild(BNode child) {
		this.child = child;
	}
	
	// Getters
	public String getKey() {
		return key;
	}
	public BNode getChild() {
		return child;
	}
	
	public String toString() {
		return key;
	}
	
	@Override
	public int compareTo(BKey bKey) {
		return key == null ? -1 : (bKey == null || bKey.getKey() == null ?
				1 :
					key.compareTo(bKey.getKey()));
	}
}
