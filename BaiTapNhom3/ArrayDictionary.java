package withBug;

public class ArrayDictionary extends AbstractDictionary {

	public ArrayDictionary() {
		super(0);
	}
	//Đếm số lượng phàn tử key khác null
	public int size() {
		int s = 0;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null)
				s++;
		}
		return s;
	}
	//Tìm ví trí của giá trị được nhập vào trong mảng
	public int indexOf(Object key) {
		for (int i = 0; i < keys.length; i++) {
			if (key.equals(keys[i]))
				return i;
		}
		return -1;
	}
	//kiểm tra xem mảng lưu trữ đã đầy hay chưa. Nếu đầy, nó sẽ resize (nới rộng)
	// mảng keys và values lên 1 đơn vị và trả về vị trí cuối cùng của mảng mới để chuẩn bị thêm key mới.
	int newIndexOf(Object key) {
		int size = this.size();
		if (size == keys.length) {
			Object[] newKeys = new Object[keys.length + 1];
			Object[] newValues = new Object[keys.length + 1];

			for (int i = 0; i < keys.length; i++) {
				newKeys[i] = keys[i];
				newValues[i] = keys[i];
			}

			keys = newKeys;
			values = newValues;
			return keys.length - 1;} 
		else return size;
		
	}

}