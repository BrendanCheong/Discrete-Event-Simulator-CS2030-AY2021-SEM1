/open Item.java
/open Sword.java
/open Candle.java
/open Troll.java
/open Room.java
/open actions.jsh

new Room("foyer").add(new Sword()).tick(killTroll)
new Room("foyer").add(new Sword()).add(new Troll()).tick(killTroll)
new Room("foyer").add(new Sword()).add(new Troll()).tick(takeSword).tick(killTroll)
new Room("foyer").add(new Candle()).add(new Troll()).add(new Sword()).tick().tick(takeSword)
new Room("foyer").add(new Candle()).add(new Troll()).add(new Sword()).tick().tick(takeSword).tick(killTroll)
new Room("foyer").add(new Candle()).add(new Troll()).add(new Sword()).tick().tick(killTroll)
new Room("foyer").add(new Candle()).add(new Troll()).add(new Sword()).tick().tick(killTroll).tick(takeSword)
new Room("foyer").add(new Candle()).add(new Troll()).add(new Sword()).tick().tick(killTroll).tick(takeSword).tick(killTroll)
new Room("foyer").add(new Candle()).add(new Troll()).tick(killTroll)
/exit