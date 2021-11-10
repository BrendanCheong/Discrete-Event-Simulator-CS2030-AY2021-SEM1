Function<? super Room, ? extends Room> takeSword = (room) -> {
    List<Item> currItemList = room.getItems();
    boolean isThereSword = false;
    Optional<Sword> theSword = Optional.empty();
    for (Item items : currItemList) {
        if (items.isItem("Sword")) {
            isThereSword = true;
            theSword = Optional.<Sword>of((Sword) items);
            break;
        }
    }

    if (isThereSword) {
        Sword chosenSword = theSword
            .map((x) -> x)
            .orElseThrow();
        if (chosenSword.checkIfPresent()) {
            System.out.println("--> You have already taken sword.");
        } else {
            System.out.println("--> You have taken sword.");
            Sword newSword = (Sword) chosenSword.equipSword();
            // update the current room list
            List<Item> newItemList = currItemList
                .stream()
                .map((x) -> x.isItem("Sword")
                    ? newSword
                    : x)
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
            return new Room(room.getName(), newItemList, room.getPastRoom());
        }
    } else {
        System.out.println("--> There is no sword.");
    }

    return room;
}

Function<? super Room, ? extends Room> killTroll = (room) -> {
    List<Item> currItemList = room.getItems();
    boolean isThereSword = false;
    boolean isThereTroll = false;
    for (Item item : currItemList) {
        if (item.isItem("Sword")) {
            isThereSword = ((Sword)item).checkIfPresent();
        } else if (item.isItem("Troll")) {
            isThereTroll = true;
        }
    }

    if (isThereTroll && isThereSword) {
        System.out.println("--> Troll is killed.");
        // after killing troll, get rid of troll from current list
        List<Item> newItemList = currItemList
            .stream()
            .filter((x) -> !x.isItem("Troll"))
            .collect(Collectors.toCollection(() -> new ArrayList<>()));
        return new Room(room.getName(), newItemList, room.getPastRoom());
    } else if (!isThereTroll) {
        System.out.println("--> There is no troll");
    } else if (!isThereSword) {
        System.out.println("--> You have no sword.");
    }
    return room;
}

Function<? super Room, ? extends Room> dropSword = (room) -> {
    List<Item> currItemList = room.getItems();
    for (Item items : currItemList) {
        if (items.isItem("Sword")) {
            Sword chosenSword = (Sword) items;
            if (chosenSword.checkIfPresent()) {
                System.out.println("--> You have dropped sword.");
                Sword newSword = (Sword) chosenSword.removeSword();
                // update the current room list
                List<Item> newItemList = currItemList
                    .stream()
                    .map((x) -> x.isItem("Sword")
                        ? newSword
                        : x)
                    .collect(Collectors.toCollection(() -> new ArrayList<>()));
                return new Room(room.getName(), newItemList, room.getPastRoom());
            }
        }
    }

    return room;
}
