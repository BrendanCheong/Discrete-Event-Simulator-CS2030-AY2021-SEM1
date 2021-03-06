import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

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

    // allow null values in Optional so we can catch them
    protected final Optional<V> get(String key) {
        return Optional.<V>ofNullable(this.map.get(key));
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

    @Override
    public String toString() {
        if (this.getMap().isEmpty()) {
            return String.format("%s: {}", this.getKey());
        } else {
            List<String> labs = new ArrayList<>();
            for (String key : this.getMap().keySet()) {
                labs.add(this.getMap().get(key).toString());
            }
            String processedString = labs.toString().replaceAll("\\[|\\]", "");
            return String.format("%s: {%s}", this.getKey(), processedString);
        }
    }

}
