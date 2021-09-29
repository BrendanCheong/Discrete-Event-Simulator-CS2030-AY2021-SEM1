public class Main {

    public static void main(String[] args) {
        boolean verbose = args.length > 0 && args[0].equals("-v");
        runSimulation(false, false, verbose);
        runSimulation(false, true, verbose);
        runSimulation(true, false, verbose);
        runSimulation(true, true, verbose);
    }

    private static void runSimulation(boolean maskPolicy, boolean shnPolicy, boolean verbose) {
        RandomNumberGenerator.reset();
        Dorms d;
        if (shnPolicy) {
            d = new DormsWithShn(verbose);
        } else {
            d = new Dorms(verbose);
        }
        System.out.println("===== RUNNING SIMULATION =====");
        System.out.println(String.format("Mask policy%s implemented and SHN%s issued\n", maskPolicy ? "" : " not", shnPolicy ? "" : " not"));
        d.checkIn("Initial Disease Carrier", maskPolicy, "LT19", 0);
        d.checkIn("Prof Henry", maskPolicy, "LT19", 0);
        d.checkIn("Prof Terence", maskPolicy, "LT19", 0);
        d.checkIn("Yong Qi", maskPolicy, "LT19", 0.1);
        d.checkIn("Kevin", maskPolicy, "LT19", 0.11);
        d.checkOut("Prof Henry", "LT19", 0.2);
        d.checkIn("Sean", maskPolicy, "LT19", 0.23);
        d.checkOut("Yong Qi", "LT19", 0.3);
        d.contact("Yong Qi", maskPolicy, "Eric", maskPolicy, 0.33);
        d.contact("Eric", maskPolicy, "De Zhang", maskPolicy, 0.34);
        d.checkOut("Prof Terence", "LT19", 0.4);
        d.checkIn("Prof Henry", maskPolicy, "i3", 0.45);
        d.checkOut("Initial Disease Carrier", "LT19", 0.5);
        d.checkOut("Kevin", "LT19", 0.5);
        d.checkOut("Sean", "LT19", 0.5);
        d.checkIn("De Zhang", maskPolicy, "i3", 1.1);
        d.checkOut("Prof Henry", "i3", 1.3);
        d.presentSymptoms("Yong Qi", 1);
        d.checkIn("De Zhang", maskPolicy, "i3", 1.1);
        d.checkIn("Sean", maskPolicy, "i3", 1.2);
        d.checkIn("Initial Disease Carrier", maskPolicy, "i3", 1.3);
        d.checkOut("De Zhang", "i3", 1.3);
        d.checkOut("Initial Disease Carrier", "i3", 1.3);
        d.checkOut("Sean", "i3", 1.3);
        d.contact("Prof Henry", maskPolicy, "De Zhang", maskPolicy, 1.3);
        d.contact("Eric", maskPolicy, "Prof Henry", maskPolicy, 1.4);
        d.presentSymptoms("De Zhang", 3);
        d.checkIn("Initial Disease Carrier", maskPolicy, "COM1-B113", 3.1);
        d.checkIn("Marcus", maskPolicy, "COM1-B113", 3.1);
        d.checkIn("Jerryl", maskPolicy, "COM1-B113", 3.2);
        d.checkIn("Yong Qi", maskPolicy, "COM1-B113", 3.3);
        d.checkIn("Prof Henry", maskPolicy, "COM1-B113", 3.4);
        d.checkIn("Kevin", maskPolicy, "COM1-B114", 3.5);
        d.checkIn("Destinee", maskPolicy, "COM1-B114", 3.6);
        d.presentSymptoms("Jerryl", 3.6);
        d.checkOut("Yong Qi", "COM1-B113", 3.6);
        d.checkOut("Initial Disease Carrier", "COM1-B113", 4);
        d.checkOut("Marcus", "COM1-B113", 4);
        d.checkOut("Jerryl", "COM1-B113", 4);
        d.checkOut("Prof Henry", "COM1-B113", 4);
        d.checkOut("Kevin", "COM1-B114", 4);
        d.checkOut("Destinee", "COM1-B114", 4);
        d.contact("Siddarth Raj", maskPolicy, "Yong Qi", maskPolicy, 5);
        d.contact("Xuan Ming", maskPolicy, "Yong Qi", maskPolicy, 7);
        d.checkIn("Xuan Ming", maskPolicy, "ION Orchard", 9);
        d.checkIn("Jerryl", maskPolicy, "ION Orchard", 9.4);
        d.checkOut("Xuan Ming", "ION Orchard", 9.5);
        d.contact("Xuan Ming", maskPolicy, "Le Yang", maskPolicy, 10);
        d.contact("Le Yang", maskPolicy, "Joel", maskPolicy, 10.5);
        d.contact("Mario", maskPolicy, "Jeremy", maskPolicy, 10.5);
        d.checkIn("Bryan", maskPolicy, "ION Orchard", 10.5);
        d.checkIn("Geyu", maskPolicy, "ION Orchard", 10.5);
        d.checkOut("Jerryl", "ION Orchard", 10.5);
        d.checkOut("Geyu", "ION Orchard", 10.5);
        d.checkOut("Bryan", "ION Orchard", 10.5);
        d.presentSymptoms("Mario", 11);
        d.printStatistics();
        System.out.println("===== SIMULATION COMPLETED =====\n");
    }
}

