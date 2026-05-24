package com.rms.model;

/**
 * Model class representing a Table Booking.
 */
public class Booking {

    private int bookingId;
    private String firstName;
    private String lastName;
    private String email;
    private String tableType;
    private String phoneNo;
    private String placement;
    private String bookingDate;
    private String startTime;
    private String endTime;
    private String note;

    public Booking() {}

    public Booking(String firstName, String lastName, String email, String tableType,
                   String phoneNo, String placement, String bookingDate,
                   String startTime, String endTime, String note) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tableType = tableType;
        this.phoneNo = phoneNo;
        this.placement = placement;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.note = note;
    }

    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTableType() { return tableType; }
    public void setTableType(String tableType) { this.tableType = tableType; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getPlacement() { return placement; }
    public void setPlacement(String placement) { this.placement = placement; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
