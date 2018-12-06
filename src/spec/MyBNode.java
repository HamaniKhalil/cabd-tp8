package spec;

import indexing.BKey;

public interface MyBNode<MyBKey> {

	public void addKey(MyBKey key);
	public MyBNode lookupKey(String key);
	public void splitAndPush(MyBKey key);
}
