/open Item.java
/open Burger.java
/open Snack.java
/open Drink.java
/open Menu.java
/open MenuComparator.java
/open Order.java


Burger b = new Burger("Hamburger", 399, 0)
Snack f = new Snack("Fries", 189, 1)
Drink d = new Drink("SoftDrink", 149, 2)
Snack drum = new Snack("Drumlets", 169, 3)
Burger ch = new Burger("CheeseBurger", 200, 4)
Drink oj = new Drink("OrangeJuice", 209, 5)

Menu mcdonaldsMenu = new Menu()
mcdonaldsMenu.add(b)
mcdonaldsMenu.add(f)
mcdonaldsMenu.add(d)
mcdonaldsMenu.add(drum)
mcdonaldsMenu.add(ch)
mcdonaldsMenu.add(oj)

mcdonaldsMenu.readMenu()

int[] orderNumber = {2, 1, 4, 3}
Order orderUp = new Order(mcdonaldsMenu, orderNumber)
orderUp.readOrder()
