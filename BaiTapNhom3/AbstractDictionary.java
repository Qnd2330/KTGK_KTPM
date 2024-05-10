package withBug;

public abstract class AbstractDictionary implements IDictionary {

	protected Object[] keys;
	protected Object[] values;
	
	protected AbstractDictionary(int n) {
		keys = new Object[n];
		values = new Object[n];
	}
	//Trả về số lương phần tử của Key
	public int size() {
		return keys.length;
	}
	//Định nghĩa việc tìm kiếm vị trí để thêm một key mới
	abstract int newIndexOf(Object key);
	//Định nghĩa việc tìm kiếm một key đã tồn tại trong từ điển
	abstract int indexOf(Object key);
	//Phương thức này lấy giá trị (value) theo key
	public Object get(Object key) {
		int i = this.indexOf(key);
		if (i != -1)
			return values[i];
		else
			return null;
	}
	//Dùng để thêm hoặc cập nhật một key-value
	public Object put(Object key, Object value) {
		int j = indexOf(key);
		if (j == -1) {
			int i = this.newIndexOf(key);
			keys[i] = key;
			values[i] = value;
		} else values[j] = value;
		return this;
	}
	//Kiểm tra xem từ điển có trống hay không
	public boolean isEmpty() {
		return (this.size() == 0);
	}
	// Kiểm tra xem một key có tồn tại trong từ điển hay không
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