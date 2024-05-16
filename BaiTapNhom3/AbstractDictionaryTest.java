package withBug;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractDictionaryTest<D extends AbstractDictionary> {

    protected D dictionary;

    @BeforeEach
    public abstract void setUp(); // This will be overridden in subclasses

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
    public void testIsEmptyInitiallyTrue() {
        assertTrue(dictionary.isEmpty());
    }

    @Test
    public void testIsEmptyFalseAfterPut() {
        dictionary.put("key1", "value1");
        assertFalse(dictionary.isEmpty());
    }

    @Test
    public void testContainsKeyReturnsTrueForExistingKey() {
        dictionary.put("key1", "value1");
        assertTrue(dictionary.containsKey("key1"));
    }

    @Test
    public void testContainsKeyReturnsFalseForMissingKey() {
        dictionary.put("key1", "value1");
        assertFalse(dictionary.containsKey("key2"));
    }
}
