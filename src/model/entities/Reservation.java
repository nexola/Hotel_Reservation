package model.entities;

import model.exceptions.DomainException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private Integer roomNumber;
    private LocalDateTime checkin;
    private LocalDateTime checkout;

    // Constructors
    public Reservation(){}

    // with Exception
    public Reservation(Integer roomNumber, LocalDateTime checkin, LocalDateTime checkout) throws DomainException {
        if (checkin.isBefore(LocalDateTime.now()) || checkout.isBefore(LocalDateTime.now())) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkout.isAfter(checkin)) {
            throw new DomainException("Error in update. Checkout date must be after checkin date.");
        }
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    // Getters and setters
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    // Return duration between checkin and checkout in days
    public Duration duration() {
        return Duration.between(checkin, checkout);
    }

    // Update the reservation dates, throwing exceptions if the new dates are not from the future or if the checkout date is not after the checkin date
    public void updateDates(LocalDateTime checkin, LocalDateTime checkout) throws DomainException {
        if (checkin.isBefore(LocalDateTime.now()) || checkout.isBefore(LocalDateTime.now())) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkout.isAfter(checkin)) {
            throw new DomainException("Error in update. Checkout date must be after checkin date.");
        } else {
            this.checkin = checkin;
            this.checkout = checkout;
            System.out.println("Data updated!");
        }
    }

    // To string
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Room number: " + roomNumber + " - Checkin: " + checkin.format(dtf) + " - Checkout: " + checkout.format(dtf) + " - " + (duration().toDays() + 1) + " nights";
    }
}
