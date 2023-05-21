public class Train {
    private String name;
    private int capacity;
    private Queue<Passenger> passengerQueue;        
    
    public Train(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.passengerQueue = new Queue<>();
    }

    public void addPassenger(Passenger passenger) {
        passengerQueue.enqueue(passenger);
        System.out.println("Passenger " + passenger.getName() + " boarded Train " + name);
    }

    public void removePassengers() {
        while (!passengerQueue.isEmpty()) {
            Passenger passenger = passengerQueue.dequeue();
            System.out.println("Passenger " + passenger.getName() + " exited Train " + name);
        }
    }

    public int getAvailableSeats() {
        return capacity - passengerQueue.size();
    }

    public String getName() {
        return name;
    }
}