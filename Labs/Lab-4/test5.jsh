/open Request.java
/open RideService.java
/open JustRide.java
/open TakeACab.java
/open ShareARide.java
/open Driver.java
/open NormalCab.java
/open PrivateCar.java
/open Booking.java

findBestBooking(new Request(20, 3, 1000), 
new Driver[]{new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)})
findBestBooking(new Request(10, 1, 900), 
new Driver[]{new NormalCab("SHA1234", 5), new PrivateCar("SMA7890", 10)})
/exit
