package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Collecting reservation data
            Scanner sc = new Scanner(System.in);
            System.out.print("Room number: ");
            Integer roomNumber = sc.nextInt();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.print("Checkin date (DD/MM/YYYY): ");
            LocalDateTime checkin = LocalDateTime.parse(sc.next() + " 13:00:00", dtf);
            System.out.print("Checkout date (DD/MM/YYYY): ");
            LocalDateTime checkout = LocalDateTime.parse(sc.next() + " 10:00:00", dtf);

            // Instancing new reservation object
            Reservation reservation = new Reservation(roomNumber, checkin, checkout);
            System.out.println(reservation);

            // Collecting data to update the reservation
            System.out.println();
            System.out.println("Enter data to update the reservation:");
            System.out.print("Checkin date (DD/MM/YYYY): ");
            checkin = LocalDateTime.parse(sc.next() + " 13:00:00", dtf);
            System.out.print("Checkout date (DD/MM/YYYY): ");
            checkout = LocalDateTime.parse(sc.next() + " 10:00:00", dtf);

            // Updating
            reservation.updateDates(checkin, checkout);
            System.out.println(reservation);

        } catch (DomainException e) {
            System.out.println("Error in reservation: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format.");
        }
    }
}

