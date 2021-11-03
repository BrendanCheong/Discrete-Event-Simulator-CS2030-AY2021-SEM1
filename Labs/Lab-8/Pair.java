public class Pair<K, V> {

    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    /**
     * Compares between 2 pairs.
     * <p> First compaires the Key to the input Key </p>
     * <p> Then compares the Value (usually a List) to the input Value (another List) </p> 
     * @param inputPair the Pair to compare against
     * @return whether both the Key and Value are equal
     */
    public boolean equals(Pair<K, V> inputPair) {
        boolean keyCheck = this.key.equals(inputPair.getKey());
        boolean valueCheck = this.value.equals(inputPair.getValue());
        return keyCheck && valueCheck;
    }

    @Override
    public String toString() {
        return String.format("\n%s -> %s", 
            getKey(), getValue());
    }

}
