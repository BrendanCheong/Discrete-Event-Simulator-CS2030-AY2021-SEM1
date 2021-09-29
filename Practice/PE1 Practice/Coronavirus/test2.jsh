/open Virus.java
/open AlphaCoronavirus.java
/open SARS_CoV_2.java
/open BetaCoronavirus.java
/open Person.java

Person illio = new Person("Illio");
Person phillmont = new Person("phillmont")
phillmont
illio.infectWith(List.of(new AlphaCoronavirus(1)), 0).test("Alpha Coronavirus");
Arrays.toString(illio.infectWith(List.of(new AlphaCoronavirus(1)), 0).transmit(1).toArray())
List<Virus> l = illio.infectWith(List.of(new AlphaCoronavirus(0.5)), 0).transmit(1)
Arrays.toString(l.toArray())
Arrays.toString(phillmont.infectWith(l, 1).transmit(1).toArray())
Arrays.toString(phillmont.infectWith(l, 1).transmit(0).toArray())
/exit