public class Station {
    private String name;
    private Queue<Passenger> passengerQueue;

    public Station(String name) {
        this.name = name;
        this.passengerQueue = new Queue<>();
    }

    public void addPassenger(Passenger passenger) {
        passengerQueue.enqueue(passenger);
        System.out.println("Passenger " + passenger.getName() + " arrived at Station " + name);
    }

    public Queue<Passenger> getPassengerQueue() {
        return passengerQueue;
    }

    public String getName() {
        return name;
    }

    public int getQueueSize() {
        return passengerQueue.size();
    }
}