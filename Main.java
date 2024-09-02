import java.util.ArrayList;
import java.util.Scanner;

class Bus {
    private String busId;
    private String busName;
    private int totalSeats;
    private int bookedSeats;

    public Bus(String busId, String busName, int totalSeats) {
        this.busId = busId;
        this.busName = busName;
        this.totalSeats = totalSeats;
        this.bookedSeats = 0;
    }

    public String getBusId() {
        return busId;
    }

    public String getBusName() {
        return busName;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public int getAvailableSeats() {
        return totalSeats - bookedSeats;
    }

    public boolean reserveSeat() {
        if (bookedSeats < totalSeats) {
            bookedSeats++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Bus ID: " + busId + ", Name: " + busName + ", Total Seats: " + totalSeats + ", Available Seats: "
                + getAvailableSeats();
    }
}

class BusReservationSystem {
    private ArrayList<Bus> buses;

    public BusReservationSystem() {
        buses = new ArrayList<>();
    }

    public void insertBus(String busId, String busName, int totalSeats) {
        Bus newBus = new Bus(busId, busName, totalSeats);
        buses.add(newBus);
    }

    public void showAllBuses() {
        if (buses.isEmpty()) {
            System.out.println("No buses available.");
        } else {
            for (Bus bus : buses) {
                System.out.println(bus);
            }
        }
    }

    public void showSeatAvailability(String busId) {
        for (Bus bus : buses) {
            if (bus.getBusId().equals(busId)) {
                System.out.println("Bus ID: " + busId + " has " + bus.getAvailableSeats() + " seats available.");
                return;
            }
        }
        System.out.println("Bus ID: " + busId + " not found.");
    }

    public void reserveSeat(String busId) {
        for (Bus bus : buses) {
            if (bus.getBusId().equals(busId)) {
                if (bus.reserveSeat()) {
                    System.out.println("Seat reserved successfully in Bus ID: " + busId);
                } else {
                    System.out.println("No seats available in Bus ID: " + busId);
                }
                return;
            }
        }
        System.out.println("Bus ID: " + busId + " not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BusReservationSystem busReservationSystem = new BusReservationSystem();

        while (true) {
            System.out.println("\nBus Reservation System");
            System.out.println("1. Insert Bus Record");
            System.out.println("2. Show All Buses");
            System.out.println("3. Show Seat Availability of a Particular Bus");
            System.out.println("4. Reserve a Seat");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Bus ID: ");
                    String busId = scanner.nextLine();
                    System.out.print("Enter Bus Name: ");
                    String busName = scanner.nextLine();
                    System.out.print("Enter Total Seats: ");
                    int totalSeats = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    busReservationSystem.insertBus(busId, busName, totalSeats);
                    System.out.println("Bus record inserted successfully.");
                    break;
                case 2:
                    busReservationSystem.showAllBuses();
                    break;
                case 3:
                    System.out.print("Enter Bus ID: ");
                    busId = scanner.nextLine();
                    busReservationSystem.showSeatAvailability(busId);
                    break;
                case 4:
                    System.out.print("Enter Bus ID: ");
                    busId = scanner.nextLine();
                    busReservationSystem.reserveSeat(busId);
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}