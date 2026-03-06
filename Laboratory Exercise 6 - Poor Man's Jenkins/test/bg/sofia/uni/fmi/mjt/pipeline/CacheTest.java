package bg.sofia.uni.fmi.mjt.pipeline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    private Cache testCache = new Cache();

    @Test
    void testCacheValueKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testCache.cacheValue(null, new Integer(3)),
            "Cache value method should throw IllegalArgumentException when key is null");
    }

    @Test
    void testCacheValueValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testCache.cacheValue(new Integer(3), null),
            "Cache value method should throw IllegalArgumentException when key is null");
    }

    @Test
    void testGetCachedValueKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testCache.getCachedValue(null),
            "Get cached value should throw IllegalArgumentException when key is null");
    }

    @Test
    void testGetCachedValueNormalCase() {
        testCache.cacheValue("Marco","Polo");
        assertEquals("Polo", testCache.getCachedValue("Marco"),
            "Get cached value should return the value to the key");
    }

    @Test
    void testContainsKeyKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> testCache.containsKey(null),
            "Contains key should throw IllegalArgumentException when key is null");
    }

    @Test
    void testContainsKeyNormalCase() {
        testCache.cacheValue("Marco","Polo");
        assertTrue(testCache.containsKey("Marco"), "Contains key should return true for existing key");
    }

}
