package algorithm.codesignal;

import java.util.List;
import java.util.Optional;

public interface InMemoryDB {
    // ✅ 기존 메서드들 (변경 없음, backward compatible)
    default void set(String key, String field, String value) {}
    default Optional<String> get(String key, String field) {
        return Optional.empty();
    }
    default boolean delete(String key, String field) {
        return false;
    }
    default List<String> scan(String key) {
        return List.of();
    }
    default List<String> scanByPrefix(String key, String prefix) {
        return List.of();
    }

    // ✅ New: Timestamp-aware versions

    /**
     * Insert or update field-value at specific timestamp.
     */
    default void setAt(String key, String field, String value, int timestamp) {}

    /**
     * Insert or update field-value with TTL starting at timestamp.
     * TTL is inclusive of [timestamp, timestamp + ttl).
     */
    default void setAtWithTtl(String key, String field, String value, int timestamp, int ttl) {}

    /**
     * Return value of field at specific timestamp if it exists and not expired.
     */
    default Optional<String> getAt(String key, String field, int timestamp) {
        return Optional.empty();
    }

    /**
     * Delete the field at specific timestamp. Returns true if successfully deleted.
     */
    default boolean deleteAt(String key, String field, int timestamp) {
        return false;
    }

    /**
     * Scan all field-value pairs at a given timestamp (sorted lexicographically).
     */
    default List<String> scanAt(String key, int timestamp) {
        return List.of();
    }

    /**
     * Scan fields starting with prefix at a given timestamp.
     */
    default List<String> scanByPrefixAt(String key, String prefix, int timestamp) {
        return List.of();
    }
}
