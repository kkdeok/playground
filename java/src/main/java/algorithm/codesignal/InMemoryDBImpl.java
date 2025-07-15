package algorithm.codesignal;


import java.util.*;

public class InMemoryDBImpl implements InMemoryDB {
    private final Map<String, TreeMap<String, String>> db = new HashMap<>();

    @Override
    public void set(String key, String field, String value) {
        db.putIfAbsent(key, new TreeMap<>());
        db.get(key).put(field, value); // field는 자동 정렬됨
    }

    @Override
    public Optional<String> get(String key, String field) {
        TreeMap<String, String> record = db.get(key);
        if (record == null || !record.containsKey(field)) {
            return Optional.empty();
        }
        return Optional.of(record.get(field));
    }

    @Override
    public boolean delete(String key, String field) {
        TreeMap<String, String> record = db.get(key);
        if (record == null || !record.containsKey(field)) {
            return false;
        }
        record.remove(field);
        if (record.isEmpty()) {
            db.remove(key);
        }
        return true;
    }

    // ✅ key에 대한 모든 field-value 출력 (자동 정렬)
    public List<String> scan(String key) {
        TreeMap<String, String> record = db.get(key);
        if (record == null) return List.of();

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : record.entrySet()) {
            result.add(entry.getKey() + "(" + entry.getValue() + ")");
        }
        return result;
    }

    // ✅ prefix로 필터링된 field-value 출력 (정렬은 자동)
    public List<String> scanByPrefix(String key, String prefix) {
        TreeMap<String, String> record = db.get(key);
        if (record == null) return List.of();

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, String> entry : record.entrySet()) {
            if (entry.getKey().startsWith(prefix)) {
                result.add(entry.getKey() + "(" + entry.getValue() + ")");
            }
        }
        return result;
    }
}