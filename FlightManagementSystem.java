import java.util.*;
import java.time.LocalDate;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class FlightManagementSystem {

    // ===== Flight Class =====
    static class Flight {
        int id;
        String flightName, source, destination, date, departTime, arriveTime;
        double price;

        Flight(int id, String flightName, String source, String destination,
               String date, String departTime, String arriveTime, double price) {
            this.id = id;
            this.flightName = flightName;
            this.source = source;
            this.destination = destination;
            this.date = date;
            this.departTime = departTime;
            this.arriveTime = arriveTime;
            this.price = price;
        }

        void display() {
            System.out.println(id + " | " + flightName + " | " +
                    source + " → " + destination +
                    " | Date: " + date +
                    " | Time: " + departTime +
                    " | ₹" + price);
        }
    }

    // ===== Passenger Class =====
    static class Passenger {
        String name;
        int age;

        Passenger(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    // ===== Ticket Class =====
    static class Ticket {
        Flight flight;
        Passenger passenger;
        String ticketNo, seatNo, bookingDate;

        Ticket(Flight flight, Passenger passenger) {
            this.flight = flight;
            this.passenger = passenger;
            Random r = new Random();
            ticketNo = "TKT" + (10000 + r.nextInt(90000));
            seatNo = "A" + (1 + r.nextInt(30));
            bookingDate = LocalDate.now().toString();
        }

        void printTicket() {
            System.out.println("\n================ FLIGHT TICKET ================");
            System.out.println("Ticket No     : " + ticketNo);
            System.out.println("Booking Date  : " + bookingDate);
            System.out.println("Status        : CONFIRMED");
            System.out.println("----------------------------------------------");
            System.out.println("Passenger Name: " + passenger.name);
            System.out.println("Age           : " + passenger.age);
            System.out.println("Seat No       : " + seatNo);
            System.out.println("----------------------------------------------");
            System.out.println("Flight        : " + flight.flightName);
            System.out.println("Route         : " + flight.source + " → " + flight.destination);
            System.out.println("Date          : " + flight.date);
            System.out.println("Departure     : " + flight.departTime);
            System.out.println("Arrival       : " + flight.arriveTime);
            System.out.println("Fare          : ₹" + flight.price);
            System.out.println("----------------------------------------------");
            System.out.println("✨ Happy Journey ✈️ Have a safe flight!");
            System.out.println("===============================================");
        }
    }

    // ===== PDF Generator =====
    static void generatePDF(Ticket t) {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("Flight_Ticket.pdf"));
            doc.open();

            Font title = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font bold = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

            doc.add(new Paragraph("✈️ FLIGHT TICKET ✈️", title));
            doc.add(new Paragraph());

            doc.add(new Paragraph("Ticket No: " + t.ticketNo, bold));
            doc.add(new Paragraph(" ", normal));
            doc.add(new Paragraph("Status: CONFIRMED", bold));

            doc.add(new Paragraph("\nPassenger Details", bold));
            doc.add(new Paragraph("Name: " + t.passenger.name, normal));
            doc.add(new Paragraph("Age: " + t.passenger.age, normal));
            doc.add(new Paragraph("Seat No: " + t.seatNo, normal));

            doc.add(new Paragraph("\nFlight Details", bold));
            doc.add(new Paragraph("Flight: " + t.flight.flightName, normal));
            doc.add(new Paragraph("Route: " + t.flight.source + " → " + t.flight.destination, normal));
            doc.add(new Paragraph("Date: " + t.flight.date, normal));
            doc.add(new Paragraph("Departure Time: " + t.flight.departTime, normal));
            doc.add(new Paragraph("Arrival Time: " + t.flight.arriveTime, normal));
            doc.add(new Paragraph("Fare: ₹" + t.flight.price, normal));

            doc.add(new Paragraph("\n✨ Happy Journey ✈️", normal));
            doc.add(new Paragraph("Thank you for choosing our airline.", normal));

            doc.close();
            System.out.println("✅ PDF Generated: Flight_Ticket.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== Search Flights by Route =====
    static ArrayList<Flight> searchFlights(ArrayList<Flight> flights, String source, String destination) {
        ArrayList<Flight> results = new ArrayList<>();
        for (Flight f : flights) {
            if (f.source.equalsIgnoreCase(source) && f.destination.equalsIgnoreCase(destination)) {
                results.add(f);
            }
        }
        return results;
    }

    // ===== Display Flight List =====
    static void displayFlights(ArrayList<Flight> flights) {
        if (flights.isEmpty()) {
            System.out.println("❌ No flights found!\n");
            return;
        }
        System.out.println("\n========== AVAILABLE FLIGHTS ==========");
        for (Flight f : flights) {
            f.display();
        }
        System.out.println("======================================\n");
    }

    // ===== MAIN METHOD =====
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Flight> flights = new ArrayList<>();
        // Adding more flights
        flights.add(new Flight(101, "IndiGo 6E-203", "Delhi", "Mumbai",
                "15-01-2026", "10:30 AM", "12:45 PM", 5500));
        flights.add(new Flight(102, "Air India AI-441", "Delhi", "Bangalore",
                "16-01-2026", "09:00 AM", "11:50 AM", 6500));
        flights.add(new Flight(103, "Vistara UK-820", "Mumbai", "Chennai",
                "17-01-2026", "06:15 PM", "08:05 PM", 5000));
        flights.add(new Flight(104, "SpiceJet SG-512", "Delhi", "Mumbai",
                "18-01-2026", "02:00 PM", "04:30 PM", 4500));
        flights.add(new Flight(105, "GoAir G8-724", "Bangalore", "Delhi",
                "19-01-2026", "07:00 AM", "09:15 AM", 5800));
        flights.add(new Flight(106, "Air India AI-620", "Mumbai", "Bangalore",
                "20-01-2026", "11:00 AM", "01:00 PM", 6000));
        flights.add(new Flight(107, "Vistara UK-915", "Delhi", "Chennai",
                "21-01-2026", "04:30 PM", "08:00 PM", 7200));
        flights.add(new Flight(108, "IndiGo 6E-505", "Chennai", "Mumbai",
                "22-01-2026", "08:45 AM", "10:30 AM", 4800));

        boolean continueBooking = true;

        while (continueBooking) {
            System.out.println("\n✈️ WELCOME TO FLIGHT MANAGEMENT SYSTEM ✈️");
            System.out.println("1. Search Flights");
            System.out.println("2. View All Flights");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Source City: ");
                    String source = sc.nextLine();

                    System.out.print("Enter Destination City: ");
                    String destination = sc.nextLine();

                    ArrayList<Flight> searchResults = searchFlights(flights, source, destination);
                    displayFlights(searchResults);

                    if (!searchResults.isEmpty()) {
                        System.out.print("Enter Flight ID to Book (0 to cancel): ");
                        int id = sc.nextInt();

                        if (id == 0) {
                            System.out.println("Booking cancelled.\n");
                            break;
                        }

                        Flight selected = null;
                        for (Flight f : searchResults) {
                            if (f.id == id) {
                                selected = f;
                                break;
                            }
                        }

                        if (selected == null) {
                            System.out.println("❌ Invalid Flight ID\n");
                            break;
                        }

                        sc.nextLine();
                        System.out.print("Enter Passenger Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();

                        Passenger p = new Passenger(name, age);
                        Ticket ticket = new Ticket(selected, p);

                        ticket.printTicket();
                        generatePDF(ticket);

                        System.out.print("\nDo you want to book another flight? (yes/no): ");
                        sc.nextLine();
                        String response = sc.nextLine();
                        if (!response.equalsIgnoreCase("yes")) {
                            continueBooking = false;
                        }
                    }
                    break;

                case 2:
                    displayFlights(flights);
                    System.out.print("Enter Flight ID to Book (0 to cancel): ");
                    int id = sc.nextInt();

                    if (id == 0) {
                        System.out.println("Booking cancelled.\n");
                        break;
                    }

                    Flight selected = null;
                    for (Flight f : flights) {
                        if (f.id == id) {
                            selected = f;
                            break;
                        }
                    }

                    if (selected == null) {
                        System.out.println("❌ Invalid Flight ID\n");
                        break;
                    }

                    sc.nextLine();
                    System.out.print("Enter Passenger Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    Passenger p = new Passenger(name, age);
                    Ticket ticket = new Ticket(selected, p);

                    ticket.printTicket();
                    generatePDF(ticket);

                    System.out.print("\nDo you want to book another flight? (yes/no): ");
                    sc.nextLine();
                    String response = sc.nextLine();
                    if (!response.equalsIgnoreCase("yes")) {
                        continueBooking = false;
                    }
                    break;

                case 3:
                    System.out.println("\n👋 Thank you for using Flight Management System!");
                    continueBooking = false;
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please try again.\n");
            }
        }

        sc.close();
    }
}
