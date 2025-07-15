package algorithm.codesignal;

public class Data {
    /**
     * Support the timeline of operations and TTL settins for records and fields. Each operation from pervious levels now has an alternative version with a timestamp parameter to represent when the operation was executed. FOr each field-value pair in DB. the TTL determines how long that value will persist before being removed.
     *
     * NOTES:
     * - Time should always flow forward, so timestamps are guaranteed to strictly increase as operations are executed.
     *
     * - Each test cannot contain both versions of operations(with and without timestamp). However, you should maintain backword compatibility, so all previously defined methods should work in the same way as before.
     *
     * - void setAt(String key, String field, String value, int timestamp): should insert a field-value paire or updates the value of the field in the record associated with key.
     *
     * - void setAtWithTtl(String key, String field, String value, int timestamp, int ttl): should insert a field-value pair or update the value of the field in the record associated with key. Also sets its TTL starting at timestamp to be ttl. The ttl is the amount of time that this field-value pair should exist in the DB, meaning it will be available during this interval: [timestamp, timstamp+ttl].
     *
     * - boolean deleteAt(String key, String field, int timestamp): the same as delete, but with timestamp of the operation specified. Shoudl return true if the field existed and was successfully deleted and false if the key didn't exists.
     *
     * - Optional<String> getAt(String key, String field, int timestamp): the same as get, but with timestamp of the operation specified.
     *
     * - List<String> scanAt(String key, int timestamp): the same as scan, but with timestamp of the operation specified.
     *
     * - List<String> scanByPrefixAt(String key, String prefix, int timestamp): the same as scanByPrefix, but with timestamp of the operation specified.
     */

    private String value;
    private int timestamp;
    private int ttl;

    Data(String value) {

    }

    Data(String value, int timestamp) {

    }

    Data(String value, int timestamp, int ttl) {

    }
}
