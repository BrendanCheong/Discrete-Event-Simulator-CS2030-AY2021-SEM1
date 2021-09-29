/open Virus.java
/open AlphaCoronavirus.java
/open SARS_CoV_2.java
/open BetaCoronavirus.java
/open Person.java
/open Contact.java
/open Location.java
/open MaskedPerson.java

MaskedPerson frederick = new MaskedPerson("Frederick")
frederick.infectWith(List.of(new AlphaCoronavirus(0.5)), 0.61).test("Alpha Coronavirus")
frederick.infectWith(List.of(new AlphaCoronavirus(0.5)), 0.6).test("Alpha Coronavirus")
Arrays.toString(frederick.infectWith(List.of(new AlphaCoronavirus(0.5)), 0.61).transmit(0.61).toArray())
Arrays.toString(frederick.infectWith(List.of(new AlphaCoronavirus(0.5)), 0.61).transmit(0.6).toArray())
/exit