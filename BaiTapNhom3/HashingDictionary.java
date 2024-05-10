package withBug;

public class HashingDictionary extends AbstractDictionary {

	public HashingDictionary() {
		super(4);
	}

	public HashingDictionary(int n) {
		super(n);
	}
	//Đếm số lượng phàn tử key khác null
	public int size() {
		int s = 0;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) s++;
		}
		return s;
	}
	//Kiểm tra nếu mảng key có length = 0 trả về true nếu ko trả về (i / j) > 0.7
	boolean mustGrow() {
		float i = this.size();
		float j = keys.length;
		if (j == 0)
			return true;
		else
			return ((i / j) > 0.7);
	}
	//Tăng length của 2 mảng keys và values lên 5 lần
	public void grow() {
		Object[] oldKeys = keys;
		Object[] oldValues = values;
		keys = new Object[oldKeys.length + 5];
		values = new Object[oldKeys.length + 5];
		
		for (int i = 0; i < oldKeys.length; i++) {
			if (oldKeys[i] != null) {
				keys[i] = oldKeys[i];
				values[i] = oldValues[i];
			}
		}
	}
	// hash code để tính toán vị trí ban đầu để tìm kiếm key.
	// Nó sử dụng vòng lặp để xử lý va chạm và trả về vị trí của key nếu tìm thấy, hoặc -1 nếu không tìm thấy.
	public int indexOf(Object key) {
		int hash = key.hashCode();
		if (hash < 0) hash = -1 * hash;
		int i = hash % keys.length;
		//Xử lý va chạm (Collision)
		//Kiểm tra xem key đang tìm kiếm (key) có bằng với key tại vị trí i trong mảng keys[i] hay không
		//Kiểm tra xem phần tử tại vị trí i có khác null hay không. Điều này là cần thiết vì mảng có thể chứa các phần tử null để đánh dấu các vị trí trống.
		while ((!(key.equals(keys[i]))) && (keys[i] != null)) {
			i = (i + 1) % keys.length;
		}
		if (keys[i] == null) return -1;
		else return i;
	}
	//ược cải tiến bằng cách tách việc kiểm tra và nới rộng mảng ra thành các phương thức riêng (mustGrow và grow).
	// Nó sử dụng vòng lặp do-while để tìm kiếm vị trí trống phù hợp để thêm key mới, xử lý trường hợp vị trí tính toán ban đầu đã bị chiếm chỗ.
	public int newIndexOf(Object key) {
		if (this.mustGrow()) this.grow();
		int hash = key.hashCode();
		if (hash < 0) hash = -1 * hash;
		int i = hash % keys.length;
		if (keys[i] == null) return i;
		else {
			do {
				if (i + 1 < keys.length)
					i++;
				else
					i = 0;
			} while (keys[i] != null);
			return i;
		}
	}

}