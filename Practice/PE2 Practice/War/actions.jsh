UnaryOperator<List<Thing>> takeSword = list -> {
    for (Thing thing: list) {
        if (thing instanceof Sword) {
            Sword sword = (Sword)thing;
            if (!sword.isEquipped()) {
                System.out.println("--> You have taken sword.");
                Sword newSword = sword.equipSword();
                return list.stream()
                    .map(x -> x == thing ? newSword : x)
                    .collect(Collectors.toList());
            } else {
                System.out.println("--> You already have sword.");
                return list;
            }
        }
    } 

    System.out.println("--> There is no sword.");
    return list;
};

UnaryOperator<List<Thing>> killTroll = list -> {
    boolean swordEquipped = false;
    boolean hasTroll = false;
    for (Thing thing : list) {
        if (thing instanceof Troll) {
            hasTroll = true;
        } else if (thing instanceof Sword) {
            swordEquipped = ((Sword)thing).isEquipped();
        }
    }

    if (hasTroll && swordEquipped) {
        System.out.println("--> Troll is killed.");
        return list.stream()
            .filter(x -> !(x instanceof Troll))
            .collect(Collectors.toList());
    } else if (!hasTroll) {
        System.out.println("--> There is no troll");
    } else if (!swordEquipped) {
        System.out.println("--> You have no sword.");
    }

    return list;
};

UnaryOperator<List<Thing>> dropSword = list -> {
    for (Thing thing: list) {
        if (thing instanceof Sword) {
            Sword sword = (Sword)thing;
            if (sword.isEquipped()) {
                System.out.println("--> You have dropped sword.");
                Sword newSword = sword.unequipSword();
                return list.stream()
                    .map(x -> x == thing ? newSword : x)
                    .collect(Collectors.toList());
            } 
        }
    }

    return list;
};