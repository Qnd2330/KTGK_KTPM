package withBug;

public abstract class AbstractDictionary implements IDictionary {

	protected Object[] keys;
	protected Object[] values;
	
	protected AbstractDictionary(int n) {
		keys = new Object[n];
		values = new Object[n];
	}

	public int size() {
		return keys.length;
	}

	abstract int newIndexOf(Object key);

	abstract int indexOf(Object key);

	public Object get(Object key) {
		int i = this.indexOf(key);
		if (i != -1)
			return values[i];
		else
			return null;
	}

	public Object put(Object key, Object value) {
		int j = indexOf(key);
		if (j == -1) {
			int i = this.newIndexOf(key);
			keys[i] = key;
			values[i] = value;
		} else values[j] = value;
		return this;
	}

	public boolean isEmpty() {
		return (this.size() == 0);
	}

	public boolean containsKey(Object key) {
		return (indexOf(key) == -1);
	}

	public String toString() {
		String s = "size: " + keys.length + "\n";
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				s = s + keys[i].toString() + " -> " + values[i].toString()
						+ "\n";
			}
		}
		return s;
	}
}