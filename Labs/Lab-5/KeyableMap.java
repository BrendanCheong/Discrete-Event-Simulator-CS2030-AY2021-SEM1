import java.util.Map;
import java.util.HashMap;

public class KeyableMap<V extends Keyable> implements Keyable {

    private final String key;
    private final Map<String, V> map;

    public KeyableMap(String key) {
        this(key, new HashMap<>());
    }

    private KeyableMap(String key, Map<String, V> map) {
        this.key = key;
        this.map = map;
    }

    protected final Map<String, V> getMap() {
        return this.map;
    }

    protected final V get(String key) {
        return this.map.get(key);
    }

    @Override
    public String getKey() {
        return this.key;
    }

    /**
     * Puts a key value pair into the nested HashMap.
     * @param value takes in any Type as a key value pair to be added
     * @return the same KeyableMap as it has to be mutable
     */
    public KeyableMap<V> put(V value) {
        // Make mutatble
        String valueKey = value.getKey();
        this.map.put(valueKey, value);

        return this;
    }

}
