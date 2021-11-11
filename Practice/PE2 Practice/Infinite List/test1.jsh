Lazy.ofNullable(4)
//$.. ==> Lazy[4]
Lazy.ofNullable(4).get()
//$.. ==> Optional[4]
Lazy.ofNullable(4).map(x -> x + 4)
//$.. ==> Lazy[?]
Lazy.ofNullable(4).filter(x -> x > 2)
//$.. ==> Lazy[?]
Lazy.ofNullable(4).map(x -> 1).get()
//$.. ==> Optional[1]
Lazy.ofNullable(4).filter(x -> true).get()
//$.. ==> Optional[4]
Lazy.ofNullable(4).filter(x -> false).get()
//$.. ==> Optional.empty
Lazy.ofNullable(4).map(x -> 1).filter(x -> false).get()
//$.. ==> Optional.empty
Lazy.ofNullable(4).filter(x -> false).map(x -> 1).get()
//$.. ==> Optional.empty
Lazy.ofNullable(4).filter(x -> true).map(x -> 1).get()
//$.. ==> Optional[1]
Lazy.ofNullable(4).filter(x -> false).filter(x -> true).get()
//$.. ==> Optional.empty

Lazy.ofNullable(null)
//$.. ==> Lazy[null]
Lazy.ofNullable(null).get()
//$.. ==> Optional.empty
Lazy.ofNullable(null).map(x -> 1)
//$.. ==> Lazy[?]
Lazy.ofNullable(null).filter(x -> true)
//$.. ==> Lazy[?]
Lazy.ofNullable(null).filter(x -> false)
//$.. ==> Lazy[?]
Lazy.ofNullable(null).map(x -> 1).get()
//$.. ==> Optional.empty
Lazy.ofNullable(null).filter(x -> true).get()
//$.. ==> Optional.empty
Lazy.ofNullable(null).filter(x -> false).get()
//$.. ==> Optional.empty

Lazy.of(() -> 4)
//$.. ==> Lazy[?]
Lazy.of(() -> 4).get()
//$.. ==> Optional[4]
Lazy.of(() -> 4).map(x -> 1)
//$.. ==> Lazy[?]
Lazy.of(() -> 4).filter(x -> true)
//$.. ==> Lazy[?]
Lazy.of(() -> 4).map(x -> 1).get()
//$.. ==> Optional[1]
Lazy.of(() -> 4).filter(x -> true).get()
//$.. ==> Optional[4]
Lazy.of(() -> 4).filter(x -> false).get()
//$.. ==> Optional.empty
Lazy<Integer> lazy = Lazy.of(() -> 4) 
lazy
//lazy ==> Lazy[?]
lazy.get()
//$.. ==> Optional[4]
lazy
//lazy ==> Lazy[4]

Lazy.of(() -> null)
//$.. ==> Lazy[?]
Lazy.of(() -> null).get()
//$.. ==> Optional.empty
Lazy.of(() -> null).map(x -> 1)
//$.. ==> Lazy[?]
Lazy.of(() -> null).filter(x -> false)
//$.. ==> Lazy[?]
Lazy.of(() -> null).map(x -> 1).get()
//$.. ==> Optional.empty
Lazy.of(() -> null).filter(x -> true).get()
//$.. ==> Optional.empty
Lazy.of(() -> null).filter(x -> false).get()
//$.. ==> Optional.empty
Lazy<Integer> lazy = Lazy.of(() -> null) 
lazy
//lazy ==> Lazy[?]
lazy.get()
//$.. ==> Optional.empty
lazy
//lazy ==> Lazy[null]