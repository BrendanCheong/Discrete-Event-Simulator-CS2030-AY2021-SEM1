package cs2030.simulator;

class Server {
    private final int id;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailableTime;

    /**
     * Server constructor.
     *
     * @param id                 server identifier
     * @param isAvailable        returns server is available to serve or not
     * @param hasWaitingCustomer returns server has a waiting customer or not
     * @param nextAvailableTime  the time that the server will be available
     **/
    public Server(int id, boolean isAvailable,
        boolean hasWaitingCustomer, double nextAvailableTime) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailableTime = nextAvailableTime;
    }

    public int getServerId() {
        return this.id;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public boolean getHasWaitingCustomer() {
        return this.hasWaitingCustomer;
    }

    public double getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    /**
     * Checks whether the Server is available.
     * @return true if the server is available, false otherwise.
     **/
    public boolean canTakeServeEvent() {
        return (this.isAvailable == true);
    }

    /**
     * Checks whether the waiting slot inside the Server has been taken.
     * <p> make sure the server is not available also, as a wait event must
     * have a server thats serving someone </p>
     * @return true if server is unavailable and does not have waiting customer
     **/
    public boolean canTakeWaitEvent() {
        return (this.isAvailable == false && this.hasWaitingCustomer == false);
    }

    @Override
    public String toString() {
        return "server " + this.id;
    }

}
