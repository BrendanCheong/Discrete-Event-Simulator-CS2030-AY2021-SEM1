/open Item.java
/open Sword.java
/open Candle.java
/open Troll.java
/open Room.java
/open actions.jsh

new Room("foyer");
new Room("foyer").add(new Candle());
new Room("foyer").add(new Candle()).tick()
new Room("foyer").add(new Candle()).tick().tick()
new Room("foyer").add(new Candle()).tick().tick().tick()
new Room("foyer").add(new Candle()).tick().tick().tick().tick()
/exit