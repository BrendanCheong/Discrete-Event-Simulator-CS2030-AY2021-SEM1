import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Room {
    private final List<Thing> objects;
    private final String room;
    private final Optional<Room> previousRoom;

    public Room(String room) {
        this(room,new ArrayList<Thing>(),Optional.empty());
    }

    public Room(String room,List<Thing> objects) {
        this(room,objects,Optional.empty());
    }

    public Room(String room,List<Thing> objects,Optional<Room> prevRoom) {
        this.room = room;
        this.objects = objects;
        this.previousRoom = prevRoom;
    }

    public Room removeSword() {
        List<Thing> removed = this.objects.stream()
            .filter(x -> !(x instanceof Sword))
            .collect(Collectors.toList());
        return new Room(this.room,removed,this.previousRoom);
    }

    public Room add(Thing thing) {
        ArrayList<Thing> newList = new ArrayList<>();
        newList.addAll(this.objects);
        newList.add(thing);
        return new Room(this.room, newList,this.previousRoom);
    }

    public Room add(Thing thing,int index) {
        ArrayList<Thing> newList = new ArrayList<>();
        newList.addAll(this.objects);
        newList.add(index,thing);
        return new Room(this.room, newList,this.previousRoom);
    }

    public Room registerPreviousRoom(Room room) {
        return new Room(this.room,this.objects,Optional.of(room));
    }

    public Room tick() {
        return new Room(this.room,
        this.objects.stream()
            .map(Thing::tick)
            .collect(Collectors.toList()),
            this.previousRoom);
    }

    public Room tick(UnaryOperator<List<Thing>> f) {
        return new Room(this.room,f.apply(this.objects),this.previousRoom).tick();
    }

    public Room go(Function<List<Thing>,Room> f) {
        // An equipped sword is needed in order to bring it to the next room
        for (Thing thing : this.objects) {
            if (thing instanceof Sword) {
                Sword sword = (Sword)thing;
                if (sword.isEquipped()) {
                    return f.apply(this.objects)
                        .add(sword,0)
                        .registerPreviousRoom(this.removeSword());
                }
            }
        }
        return f.apply(this.objects).registerPreviousRoom(this);
    }

    public Room back() {
        Room previous = this.previousRoom.orElse(this);

        // If there was no room to go back on, return itself
        if (previous == this) {
            return previous;
        }

        // Checks if player is holding onto equipped sword
        for (Thing thing : this.objects) {
            if (thing instanceof Sword) {
                Sword sword = (Sword)thing;
                if (sword.isEquipped()) {
                    // If sword is equipped bring to the previous room
                    // If prev room has sword, replace it with this room's sword
                    return previous.removeSword().add(sword).tick();
                } else {
                    // Sword is unequipped in current room
                    // If previous room has sword, it should be removed
                    return previous.removeSword().tick();
                }
            }
        }

        return previous.tick();
    }

    @Override
    public String toString() {
        String msg = String.format("@%s",this.room);
        Iterator<Thing> obIt = this.objects.iterator();
        while(obIt.hasNext()) {
            String str = "\n" + obIt.next().toString();
            msg += str;
        }
        return msg;
    }
}
