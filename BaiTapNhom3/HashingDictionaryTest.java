package withBug;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashingDictionaryTest {
    private HashingDictionary dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = new HashingDictionary();
    }

    @Test
    public void testSizeInitiallyEmpty() {
        assertEquals(0, dictionary.size());
    }

    @Test
    public void testSizeAfterPut() {
        dictionary.put("key1", "value1");
        assertEquals(1, dictionary.size());
    }

    @Test
    public void testPutUpdatesExistingKey() {
        dictionary.put("key1", "value1");
        dictionary.put("key1", "value2");
        assertEquals(1, dictionary.size()); // Size không đổi vì key trùng
        assertEquals("value2", dictionary.get("key1"));
    }

    @Test
    public void testGetReturnsCorrectValue() {
        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");

        assertEquals("value1", dictionary.get("key1"));
        assertEquals("value2", dictionary.get("key2"));
    }

    @Test
    public void testGetReturnsNullForMissingKey() {
        dictionary.put("key1", "value1");

        assertNull(dictionary.get("key2"));
    }

    @Test
    public void testIndexOfFindsExistingKey() {
        dictionary.put("key1", "value1");
        dictionary.put("key2", "value2");

        int index1 = dictionary.indexOf("key1");
        int index2 = dictionary.indexOf("key2");

        assertTrue(index1 >= 0 && index1 < dictionary.size()); // Kiểm tra index hợp lệ
        assertTrue(index2 >= 0 && index2 < dictionary.size());
        assertEquals("value1", dictionary.get(index1));
        assertEquals("value2", dictionary.get(index2));
    }

    @Test
    public void testIndexOfReturnsNegativeForMissingKey() {
        dictionary.put("key1", "value1");

        int index = dictionary.indexOf("key2");

        assertEquals(-1, index);
    }

    @Test
    public void testMustGrowAfterAddingManyElements() {
        // Add enough elements to trigger resize (load factor > 0.7)
        for (int i = 0; i < dictionary.keys.length * 0.7; i++) {
            dictionary.put("key" + i, "value" + i);
        }

        assertTrue(dictionary.mustGrow()); // Kiểm tra kích hoạt resize
    }

    @Test
    public void testGrowIncreasesCapacity() {
        int initialCapacity = dictionary.keys.length;
        dictionary.grow();

        assertTrue(dictionary.keys.length > initialCapacity); // Kiểm tra dung lượng tăng
    }

    @Test
    public void testPutAfterGrowRehashesCorrectly() {
        // Add enough elements to trigger resize
        for (int i = 0; i < dictionary.keys.length * 0.7; i++) {
            dictionary.put("key" + i, "value" + i);
        }

        dictionary.grow(); // Kích hoạt resize

        // Add a new key-value pair
        dictionary.put("newKey", "newValue");

        int index = dictionary.indexOf("newKey");
        assertTrue(index >= 0 && index < dictionary.keys.length); // Kiểm tra vị trí hợp lệ
        assertEquals("newValue", dictionary.get(index));
    }
}