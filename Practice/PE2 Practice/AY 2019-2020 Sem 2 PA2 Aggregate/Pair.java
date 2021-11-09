public class Pair<K, V> {

    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<K, V>(key, value);
    }

    public K first() {
        return this.key;
    }

    public V second() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first(), second());
    }

}
