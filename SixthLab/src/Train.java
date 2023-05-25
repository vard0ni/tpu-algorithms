import java.util.Random;

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
        System.out.println("Пассажир " + passenger.getName() + " сел в поезд " + name);
    }

    public void removePassengers(int numPassengersToExit) {
        /* 
        while (!passengerQueue.isEmpty()) {
            Passenger passenger = passengerQueue.dequeue();
            System.out.println("Пассажир " + passenger.getName() + " вышел из поезда " + name);
        }
        */
        if (numPassengersToExit > passengerQueue.size()) {
            numPassengersToExit = passengerQueue.size();
        }
        for (int i = 0; i < numPassengersToExit; i++) {
            Passenger passenger = passengerQueue.dequeue();
            System.out.println("Пассажир " + passenger.getName() + " вышел из поезда " + name);
            //passengerQueue.dequeue();
        }
    }

    public int getAvailableSeats() {
        return capacity - passengerQueue.size();
    }

    public String getName() {
        return name;
    }
}