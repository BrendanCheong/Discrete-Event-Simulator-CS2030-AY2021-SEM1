import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

public class Room {

    private final List<Item> items;
    private final String name;
    private final Optional<Room> pastRoom;

    public Room(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.pastRoom = Optional.empty();
    }

    public Room(String name, List<Item> items, Optional<Room> pastRoom) {
        this.name = name;
        this.items = items;
        this.pastRoom = pastRoom;
    }

    public Room(String name, List<Item> items) {
        this(name, items, Optional.empty());
    }

    public Room add(Item item) {
        List<Item> newItemList = new ArrayList<>(this.items);
        newItemList.add(item);
        return new Room(this.name, newItemList, this.pastRoom);
    }

    public Room add(int index, Item item) {
        List<Item> newItemList = new ArrayList<>(this.items);
        newItemList.add(index, item);
        return new Room(this.name, newItemList, this.pastRoom);
    }

    // removes sword from the room
    public Room stripSword() {
        List<Item> currItemList = new ArrayList<>(this.items);
        List<Item> newItemList = currItemList
            .stream()
            .filter((x) -> !(x.isItem("Sword")))
            .collect(Collectors.toCollection(() -> new ArrayList<>()));
        return new Room(this.name, newItemList, this.pastRoom);
    }

    public Room tick() {
        List<Item> newItemList = this.items
            .stream()
            .map((x) -> x.mutate())
            .collect(Collectors.toCollection(() -> new ArrayList<>()));
        return new Room(this.name, newItemList, this.pastRoom);
    }

    public Room tick(Function<? super Room, ? extends Room> func) {
        Room newRoom = func.apply(this);
        return newRoom;
    }

    public Room go(Function<List<Item>, Room> func) {
        Room currRoom = this.pastRoom
            .map((x) -> x)
            .orElseGet(() -> this);
        Optional<Sword> chosenSword = Optional.empty();
        for (Item items : this.items) {
            if (items.isItem("Sword")) {
                chosenSword = Optional.<Sword>of((Sword) items);
                Sword theSword = chosenSword
                    .map((x) -> x)
                    .orElseThrow();
                if (theSword.checkIfPresent()) {
                    // remove the sword from current room
                    // then add theSword: which is a sword that is present, into the newRoom
                    // update the newRoom with the previous room aka current room
                    Room pastRoom = currRoom.stripSword();
                    Room newRoom = func
                        .apply(currRoom.getItems())
                        .add(0, theSword);
                    return new Room(newRoom.getName(), newRoom.getItems(),
                        Optional.<Room>of(pastRoom));
                }
            }
        }
        // if sword is not present ie: sword is not in current room
        // then just apply ad get the new Room and register the past room as current room 
        Room newRoom = func.apply(currRoom.getItems());
        return new Room(newRoom.getName(), newRoom.getItems(), Optional.<Room>of(this));
    }

    public Room back() {
        Room pastRoom = this.getPastRoomNotNull();
        
        if (pastRoom == this) {
            return this;
        }

        for (Item items : this.items) {
            if (items.isItem("Sword")) {
                Sword chosenSword = (Sword) items;
                if (chosenSword.checkIfPresent()) {
                    // if sword is present in current room
                    // replace past room sword with this room sword
                    // mutate the rest of the items
                    Room newRoom = pastRoom
                        .stripSword()
                        .add(chosenSword)
                        .tick();
                    return newRoom;
                } else {
                    // if sword not present
                    // remove past room sword just to be sure
                    return pastRoom
                        .stripSword()
                        .tick();
                }
            }
        }

        return pastRoom.tick();

    }

    public String getName() {
        return this.name;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Optional<Room> getPastRoom() {
        return this.pastRoom;
    }

    public Room getPastRoomNotNull() {
        return this.pastRoom
            .map((x) -> x)
            .orElseGet(() -> this);
    }

    @Override
    public String toString() {
        String roomName = "@" + this.name;
        String itemNames = this.items
            .stream()
            .map((x) -> x.toString())
            .reduce("", (x, y) -> x + y);
        return roomName + itemNames;
    }

}
